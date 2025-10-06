package cn.chengzhimeow.cccondition.condition.impl.logic;

import cn.chengzhimeow.cccondition.CCCondition;
import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import cn.chengzhimeow.cccondition.condition.ArgumentKey;
import cn.chengzhimeow.cccondition.condition.ConditionBuilder;

import java.util.List;
import java.util.Map;

public final class AndImpl extends AbstractCondition {
    @ArgumentKey(keys = {"conditionList", "conditions"})
    private List<ConditionBuilder.Builder> conditionList;

    public AndImpl(CCCondition ccCondition, Map<String, Object> params) {
        super(ccCondition, params);
    }

    @Override
    public boolean onCheck() {
        for (ConditionBuilder.Builder b : this.conditionList) {
            ConditionBuilder.Builder builder = b.clone();
            // 继承参数
            for (Map.Entry<String, Object> entry : this.getParams().entrySet())
                builder.getParams().putIfAbsent(entry.getKey(), entry.getValue());

            AbstractCondition condition = builder.build();
            condition.init();
            if (!condition.checkCondition()) return false;
        }

        return true;
    }
}
