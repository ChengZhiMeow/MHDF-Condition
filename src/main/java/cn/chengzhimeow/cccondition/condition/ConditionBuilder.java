package cn.chengzhimeow.cccondition.condition;

import cn.chengzhimeow.cccondition.CCCondition;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public final class ConditionBuilder {
    public static Builder builder(CCCondition ccCondition) {
        return new Builder(ccCondition);
    }

    private ConditionBuilder() {
    }

    @ToString
    @EqualsAndHashCode
    public static class Builder {
        private final CCCondition ccCondition;
        @Getter
        private Constructor<? extends AbstractCondition> condition;
        @Getter
        private Map<String, Object> params;

        private Builder(CCCondition ccCondition) {
            this.ccCondition = ccCondition;
        }

        public Builder condition(Constructor<? extends AbstractCondition> condition) {
            this.condition = condition;
            return this;
        }

        public Builder params(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        @SuppressWarnings("MethodDoesntCallSuperMethod")
        public Builder clone() {
            Builder builder = new Builder(ccCondition);
            builder.condition = condition;
            builder.params = new HashMap<>(params);
            return builder;
        }

        @SneakyThrows
        public AbstractCondition build() {
            return condition.newInstance(ccCondition, params);
        }
    }
}
