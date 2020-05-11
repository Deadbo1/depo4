package application.security;

public enum TokenData {
    TOKEN("token"),
    ID("id"),
    EMAIL("email"),
    ROLE("role");

    private final String value;

    TokenData(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
