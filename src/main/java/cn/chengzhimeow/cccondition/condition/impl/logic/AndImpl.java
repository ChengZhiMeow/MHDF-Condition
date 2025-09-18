package cn.chengzhimeow.cccondition.condition.impl.logic;

import cn.chengzhimeow.cccondition.CCCondition;
import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import cn.chengzhimeow.cccondition.condition.ArgumentKey;

import java.util.List;
import java.util.Map;

public final class AndImpl extends AbstractCondition {
    @ArgumentKey(keys = {"conditionList", "conditions"})
    private List<AbstractCondition> conditionList;

    public AndImpl(CCCondition ccCondition, Map<String, Object> params) {
        super(ccCondition, params);
    }

    @Override
    public boolean onCheck() {
        for (AbstractCondition condition : this.conditionList) {
            if (!condition.checkCondition()) {
                return false;
            }
        }

        return true;
    }
}
