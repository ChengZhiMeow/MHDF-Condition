package cn.chengzhimeow.cccondition.exception;

import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import cn.chengzhimeow.cccondition.condition.ArgumentKey;

import java.util.List;

public final class ConditionIllegalArgumentException extends Exception {
    public final Class<? extends AbstractCondition> condition;
    public final List<ErrorKey> errorKeys;

    public ConditionIllegalArgumentException(Class<? extends AbstractCondition> condition, List<ErrorKey> errorKeys) {
        this.condition = condition;
        this.errorKeys = errorKeys;
    }

    public enum ErrorCaused {
        NO_CAST_IMPLEMENTATION,
        CAST_ERROR,
        NOT_FOUND
    }

    public record ErrorKey(ArgumentKey key, ErrorCaused caused) {
    }
}
