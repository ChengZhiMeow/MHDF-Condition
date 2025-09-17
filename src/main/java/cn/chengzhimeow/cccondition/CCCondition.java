package cn.chengzhimeow.cccondition;

import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import cn.chengzhimeow.cccondition.registry.CastRegistry;
import cn.chengzhimeow.cccondition.registry.ConditionRegistry;
import cn.chengzhimeow.cccondition.registry.PreProcessRegistry;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.util.Map;

@Getter
public final class CCCondition {
    private final JavaPlugin plugin;
    private final PreProcessRegistry preProcessRegistry;
    private final CastRegistry castRegistry;
    private final ConditionRegistry conditionRegistry;

    public CCCondition(JavaPlugin plugin) {
        this.plugin = plugin;
        this.preProcessRegistry = new PreProcessRegistry();
        this.castRegistry = new CastRegistry();
        this.conditionRegistry = new ConditionRegistry();
    }

    /**
     * 获取条件实例
     *
     * @param id  条件ID
     * @param map 条件参数
     * @return 条件实例
     */
    @SneakyThrows
    public AbstractCondition getCondition(String id, Map<String, Object> map) {
        Constructor<? extends AbstractCondition> constructor = this.conditionRegistry.get(id);
        if (constructor == null) {
            throw new IllegalArgumentException("Cannot find the condition with id " + id);
        }
        return constructor.newInstance(this, map);
    }
}
