package cn.chengzhiya.mhdfcondition.condition;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public abstract class Condition {
    @SneakyThrows
    public Condition(Map<String, Object> pramHashMap) {
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            ArgumentKey annotation = field.getAnnotation(ArgumentKey.class);
            if (annotation == null) {
                continue;
            }

            Object value = null;
            for (String key : annotation.keys()) {
                Object val = pramHashMap.get(key);
                if (val == null) {
                    continue;
                }

                value = val;
            }

            if (value == null && annotation.required()) {
                throw new IllegalArgumentException("在调用 " + this.getClass().getName() + " 条件时,找不到变量 " + field.getName() + " 的对应参数, 参数数据如下: " + pramHashMap + ",但所需参数名如下: " + Arrays.toString(annotation.keys()));
            }

            field.set(this, value);
        }
    }

    /**
     * 条件前置需求是否完成
     *
     * @return 条件前置需求是否完成
     */
    public boolean require() {
        return true;
    }

    /**
     * 检查是否满足条件
     *
     * @return 结果
     */
    public boolean check() {
        if (!this.require()) {
            return false;
        }

        return this.onCheck();
    }

    /**
     * 是否满足条件时
     *
     * @return 结果
     */
    abstract public boolean onCheck();
}
