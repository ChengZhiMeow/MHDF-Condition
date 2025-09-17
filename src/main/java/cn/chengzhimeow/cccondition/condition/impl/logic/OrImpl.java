package cn.chengzhimeow.cccondition.condition.impl.logic;

import cn.chengzhimeow.cccondition.CCCondition;
import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import cn.chengzhimeow.cccondition.condition.ArgumentKey;

import java.util.List;
import java.util.Map;

public final class OrImpl extends AbstractCondition {
    @ArgumentKey(keys = {"conditionList", "conditions"})
    private List<AbstractCondition> conditionList;

    public OrImpl(CCCondition ccCondition, Map<String, Object> prams) {
        super(ccCondition, prams);
    }

    @Override
    public boolean onCheck() {
        for (AbstractCondition condition : this.conditionList) {
            if (condition.checkCondition()) {
                return true;
            }
        }

        return false;
    }
}
