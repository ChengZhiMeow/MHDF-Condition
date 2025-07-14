package cn.chengzhiya.mhdfcondition.condition.impl.string;

import cn.chengzhiya.mhdfcondition.condition.ArgumentKey;
import cn.chengzhiya.mhdfcondition.condition.Condition;

import java.util.Map;

public final class EqualsImpl extends Condition {
    @ArgumentKey(keys = {"string", "s"})
    private String string;

    @ArgumentKey(keys = {"value", "v"})
    private String value;

    public EqualsImpl(Map<String, Object> pramHashMap) {
        super(pramHashMap);
    }

    @Override
    public boolean onCheck() {
        return this.string.equals(this.value);
    }
}
