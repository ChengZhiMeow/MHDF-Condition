package cn.chengzhiya.mhdfcondition.condition.impl.math;

import cn.chengzhiya.mhdfcondition.condition.ArgumentKey;
import cn.chengzhiya.mhdfcondition.condition.Condition;

import java.util.Map;

public final class LessThenImpl extends Condition {
    @ArgumentKey(keys = {"value1", "v1"})
    private double value1;

    @ArgumentKey(keys = {"value2", "v2"})
    private double value2;

    public LessThenImpl(Map<String, Object> pramHashMap) {
        super(pramHashMap);
    }

    @Override
    public boolean onCheck() {
        return value1 < value2;
    }
}
