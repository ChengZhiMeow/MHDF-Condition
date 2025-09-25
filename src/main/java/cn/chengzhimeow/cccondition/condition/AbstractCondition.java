package cn.chengzhimeow.cccondition.condition;

import cn.chengzhimeow.cccondition.CCCondition;
import cn.chengzhimeow.cccondition.exception.CastException;
import cn.chengzhimeow.cccondition.exception.ConditionIllegalArgumentException;
import cn.chengzhimeow.cccondition.manager.CastManager;
import cn.chengzhimeow.cccondition.manager.PreProcessManager;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;

public abstract class AbstractCondition {
    protected final CCCondition ccCondition;
    protected final Map<String, Object> params;

    public AbstractCondition(CCCondition ccCondition, Map<String, Object> params) {
        this.ccCondition = ccCondition;

        Map<String, Object> finalParams = new HashMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();

            List<PreProcessManager> list = ccCondition.getPreProcessRegistry().getOrDefault(value.getClass(), new ArrayList<>());
            for (PreProcessManager pp : list) value = pp.handle(this, ccCondition);
            finalParams.put(entry.getKey(), value);
        }
        this.params = finalParams;
    }

    /**
     * 获取参数
     *
     * @param key  参数键
     * @param type 参数类型
     * @return 参数值
     */
    @SneakyThrows
    public final <T> T getPram(String key, Class<T> type) {
        Object value = this.params.get(key);
        if (value == null) return null;

        CastManager castManager = this.ccCondition.getCastRegistry().get(type);
        // noinspection unchecked
        return (T) castManager.cast(value, type);
    }

    /**
     * 檢查參數
     */
    public final void check() throws ConditionIllegalArgumentException {
        List<ConditionIllegalArgumentException.ErrorKey> errors = new ArrayList<>();

        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            ArgumentKey annotation = field.getAnnotation(ArgumentKey.class);
            if (annotation == null) continue;
            if (annotation.disabledCheck()) continue;

            boolean notFound = true;
            Class<?> type = field.getType();
            CastManager castManager = this.ccCondition.getCastRegistry().get(type);
            for (String key : annotation.keys()) {
                Object value = this.params.get(key);
                if (value == null) continue;

                notFound = false;
                try {
                    if (castManager == null) {
                        errors.add(new ConditionIllegalArgumentException.ErrorKey(annotation, ConditionIllegalArgumentException.ErrorCaused.NO_CAST_IMPLEMENTATION, null));
                        continue;
                    }

                    castManager.cast(value, type);
                } catch (NumberFormatException | IndexOutOfBoundsException | ClassCastException | CastException e) {
                    errors.add(new ConditionIllegalArgumentException.ErrorKey(annotation, ConditionIllegalArgumentException.ErrorCaused.CAST_ERROR, e));
                }
                break;
            }

            if (notFound)
                errors.add(new ConditionIllegalArgumentException.ErrorKey(annotation, ConditionIllegalArgumentException.ErrorCaused.NOT_FOUND, null));
        }

        if (errors.isEmpty()) return;
        throw new ConditionIllegalArgumentException(this.getClass(), errors);
    }

    /**
     * 初始化參數
     */
    @SneakyThrows
    public final void init() {
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            ArgumentKey annotation = field.getAnnotation(ArgumentKey.class);
            if (annotation == null) continue;

            Object v = null;
            Class<?> type = field.getType();
            CastManager castManager = this.ccCondition.getCastRegistry().get(type);
            for (String key : annotation.keys()) {
                Object value = this.params.get(key);
                if (value == null) continue;

                v = castManager.cast(value, type);
                break;
            }

            if (v == null && annotation.required()) {
                throw new NullPointerException("Cannot find any key in " + Arrays.toString(annotation.keys()));
            }

            if (v != null) field.set(this, v);
        }
    }

    /**
     * 前置需求是否完成
     *
     * @return 前置需求是否完成
     */
    public boolean require() {
        return true;
    }

    /**
     * 檢查條件
     */
    public final boolean checkCondition() {
        if (!this.require()) return false;
        return this.onCheck();
    }

    abstract protected boolean onCheck();
}
