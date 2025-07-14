package cn.chengzhiya.mhdfcondition.condition.impl.logic;

import cn.chengzhiya.mhdfcondition.condition.ArgumentKey;
import cn.chengzhiya.mhdfcondition.condition.Condition;

import java.util.List;
import java.util.Map;

public final class AndImpl extends Condition {
    @ArgumentKey(keys = {"conditionList", "conditions"})
    private List<Condition> conditionList;

    public AndImpl(Map<String, Object> pramHashMap) {
        super(pramHashMap);
    }

    @Override
    public boolean onCheck() {
        for (Condition condition : this.conditionList) {
            if (!condition.check()) {
                return false;
            }
        }

        return true;
    }
}
