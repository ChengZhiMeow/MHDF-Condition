package cn.chengzhiya.mhdfcondition.condition.impl.string;

import cn.chengzhiya.mhdfcondition.condition.ArgumentKey;
import cn.chengzhiya.mhdfcondition.condition.Condition;

import java.util.Map;

public final class ContainsImpl extends Condition {
    @ArgumentKey(keys = {"string", "s"})
    private String string;

    @ArgumentKey(keys = {"value", "v"})
    private String value;

    public ContainsImpl(Map<String, Object> pramHashMap) {
        super(pramHashMap);
    }

    @Override
    public boolean onCheck() {
        return this.string.contains(this.value);
    }
}
