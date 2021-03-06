package application.security;

public final class TokenCustomer {
    private final Long id;
    private final String email;
    private final String role;

    public TokenCustomer(Long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
