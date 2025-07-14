package cn.chengzhiya.mhdfcondition.condition.impl.list;

import cn.chengzhiya.mhdfcondition.condition.ArgumentKey;
import cn.chengzhiya.mhdfcondition.condition.Condition;

import java.util.List;
import java.util.Map;

public final class ContainsValueImpl extends Condition {
    @ArgumentKey(keys = {"list"})
    private List<Object> list;

    @ArgumentKey(keys = {"value", "v"})
    private Object value;

    public ContainsValueImpl(Map<String, Object> pramHashMap) {
        super(pramHashMap);
    }

    @Override
    public boolean onCheck() {
        return this.list.contains(this.value);
    }
}
