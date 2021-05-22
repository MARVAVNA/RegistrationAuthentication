package exception;

public class EnumException extends Exception {
    private String enumName;
    private String value;

    public EnumException(String enumName, String value) {
        this.enumName = enumName;
        this.value = value;
    }
    @Override
    public String toString() {
        return new StringBuilder(enumName)
                .append(" is irrelevant ")
                .append(value).toString();
    }
}
