package cn.chengzhimeow.cccondition.exception;

public final class CastException extends Exception {
    public final Object value;
    public final Class<?> type;

    public CastException(Object value, Class<?> type) {
        super("无法转换变量 " + value + " 为 " + type.getName() + " 类型");
        this.value = value;
        this.type = type;
    }
}
