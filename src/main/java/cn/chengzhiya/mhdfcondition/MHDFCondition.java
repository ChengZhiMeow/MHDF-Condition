package cn.chengzhiya.mhdfcondition;

import cn.chengzhiya.mhdfcondition.condition.Condition;
import cn.chengzhiya.mhdfcondition.condition.impl.list.ContainsValueImpl;
import cn.chengzhiya.mhdfcondition.condition.impl.logic.AndImpl;
import cn.chengzhiya.mhdfcondition.condition.impl.logic.OrImpl;
import cn.chengzhiya.mhdfcondition.condition.impl.math.*;
import cn.chengzhiya.mhdfcondition.condition.impl.player.PermissionImpl;
import cn.chengzhiya.mhdfcondition.condition.impl.string.ContainsImpl;
import cn.chengzhiya.mhdfcondition.condition.impl.string.EqualsImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public final class MHDFCondition {
    private final JavaPlugin plugin;
    @Getter(AccessLevel.PRIVATE)
    private final ConcurrentHashMap<String, Class<? extends Condition>> conditionHashMap = new ConcurrentHashMap<>();

    public MHDFCondition(JavaPlugin plugin) {
        this.plugin = plugin;
        this.registerDefaultCondition();
    }

    /**
     * 注册默认条件
     */
    private void registerDefaultCondition() {
        this.registerCondition("contains_value", ContainsValueImpl.class);

        this.registerCondition("&&", AndImpl.class);
        this.registerCondition("||", OrImpl.class);

        this.registerCondition("<", LessThenImpl.class);
        this.registerCondition("<=", LessThenOrEqualImpl.class);
        this.registerCondition("==", EqualImpl.class);
        this.registerCondition(">=", GreaterThanOrEqualImpl.class);
        this.registerCondition(">", GreaterThanImpl.class);

        this.registerCondition("permission", PermissionImpl.class);

        this.registerCondition("contains", ContainsImpl.class);
        this.registerCondition("equals", EqualsImpl.class);
    }

    /**
     * 注册条件
     *
     * @param type      条件类型
     * @param condition 条件类实例
     */
    public void registerCondition(String type, Class<? extends Condition> condition) {
        if (this.getConditionHashMap().containsKey(type)) {
            throw new IllegalArgumentException("条件类型 " + type + " 已经在 " + condition.getName() + " 中注册了");
        }
        this.getConditionHashMap().put(type, condition);
    }

    /**
     * 取消注册条件
     *
     * @param type 条件类型
     */
    public void unregisterCondition(String type) {
        this.getConditionHashMap().remove(type);
    }

    /**
     * 获取条件列表
     *
     * @return 条件列表
     */
    public List<Class<? extends Condition>> getConditionList() {
        return new ArrayList<>(this.getConditionHashMap().values());
    }

    /**
     * 获取条件类实例
     *
     * @param type 条件类型
     * @return 条件类实例
     */
    public Class<? extends Condition> getCondition(String type) {
        return this.getConditionHashMap().get(type);
    }

    /**
     * 检查条件是否满足
     *
     * @param condition 聊天实例
     * @return 结果
     */
    public boolean checkCondition(Condition condition) {
        return condition.check();
    }

    /**
     * 检查条件是否满足
     *
     * @param conditionClass 条件类实例
     * @param pramHashMap    参数
     * @return 结果
     */
    @SneakyThrows
    public boolean checkCondition(Class<? extends Condition> conditionClass, Map<String, Object> pramHashMap) {
        Condition condition = conditionClass.getConstructor(Map.class).newInstance(pramHashMap);
        return this.checkCondition(condition);
    }

    /**
     * 检查条件是否满足
     *
     * @param type        条件类型
     * @param pramHashMap 参数
     * @return 结果
     */
    public boolean checkCondition(String type, Map<String, Object> pramHashMap) {
        return this.checkCondition(this.getCondition(type), pramHashMap);
    }
}
