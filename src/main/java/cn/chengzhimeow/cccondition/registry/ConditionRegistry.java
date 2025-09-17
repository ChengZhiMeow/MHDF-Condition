package cn.chengzhimeow.cccondition.registry;

import cn.chengzhimeow.cccondition.CCCondition;
import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import cn.chengzhimeow.cccondition.condition.impl.list.ContainsValueImpl;
import cn.chengzhimeow.cccondition.condition.impl.logic.AndImpl;
import cn.chengzhimeow.cccondition.condition.impl.logic.OrImpl;
import cn.chengzhimeow.cccondition.condition.impl.math.*;
import cn.chengzhimeow.cccondition.condition.impl.player.PermissionImpl;
import cn.chengzhimeow.cccondition.condition.impl.string.ContainsImpl;
import cn.chengzhimeow.cccondition.condition.impl.string.EqualsImpl;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.Map;

public final class ConditionRegistry extends Registry<String, Constructor<? extends AbstractCondition>> {
    @SneakyThrows
    public Constructor<? extends AbstractCondition> getConstructor(Class<? extends AbstractCondition> clazz) {
        return clazz.getConstructor(CCCondition.class, Map.class);
    }

    @Override
    public Map<String, Constructor<? extends AbstractCondition>> defaultRegistry() {
        Map<String, Constructor<? extends AbstractCondition>> map = super.defaultRegistry();

        map.put("contains_value", this.getConstructor(ContainsValueImpl.class));

        map.put("&&", this.getConstructor(AndImpl.class));
        map.put("||", this.getConstructor(OrImpl.class));

        map.put("<", this.getConstructor(LessThenImpl.class));
        map.put("<=", this.getConstructor(LessThenOrEqualImpl.class));
        map.put("==", this.getConstructor(EqualImpl.class));
        map.put(">=", this.getConstructor(GreaterThanOrEqualImpl.class));
        map.put(">", this.getConstructor(GreaterThanImpl.class));

        map.put("permission", this.getConstructor(PermissionImpl.class));

        map.put("contains", this.getConstructor(ContainsImpl.class));
        map.put("equals", this.getConstructor(EqualsImpl.class));

        return map;
    }

    public void register(String key, Class<? extends AbstractCondition> value) {
        super.register(key, this.getConstructor(value));
    }
}
