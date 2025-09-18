package cn.chengzhimeow.cccondition.condition.impl.list;

import cn.chengzhimeow.cccondition.CCCondition;
import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import cn.chengzhimeow.cccondition.condition.ArgumentKey;

import java.util.List;
import java.util.Map;

public final class ContainsValueImpl extends AbstractCondition {
    @ArgumentKey(keys = {"list"})
    private List<Object> list;

    @ArgumentKey(keys = {"value", "v"})
    private Object value;

    public ContainsValueImpl(CCCondition ccCondition, Map<String, Object> params) {
        super(ccCondition, params);
    }

    @Override
    public boolean onCheck() {
        return this.list.contains(this.value);
    }
}
