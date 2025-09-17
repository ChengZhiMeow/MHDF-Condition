package cn.chengzhimeow.cccondition.condition.impl.string;

import cn.chengzhimeow.cccondition.CCCondition;
import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import cn.chengzhimeow.cccondition.condition.ArgumentKey;

import java.util.Map;

public final class ContainsImpl extends AbstractCondition {
    @ArgumentKey(keys = {"string", "s"})
    private String string;

    @ArgumentKey(keys = {"value", "v"})
    private String value;

    public ContainsImpl(CCCondition ccCondition, Map<String, Object> prams) {
        super(ccCondition, prams);
    }

    @Override
    public boolean onCheck() {
        return this.string.contains(this.value);
    }
}
