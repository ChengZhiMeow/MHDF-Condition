package cn.chengzhimeow.cccondition.condition.impl.math;

import cn.chengzhimeow.cccondition.CCCondition;
import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import cn.chengzhimeow.cccondition.condition.ArgumentKey;

import java.util.Map;

public final class GreaterThanImpl extends AbstractCondition {
    @ArgumentKey(keys = {"value1", "v1"})
    private double value1;

    @ArgumentKey(keys = {"value2", "v2"})
    private double value2;

    public GreaterThanImpl(CCCondition ccCondition, Map<String, Object> prams) {
        super(ccCondition, prams);
    }

    @Override
    public boolean onCheck() {
        return this.value1 > this.value2;
    }
}
