package application.data;

import java.util.List;

public class AuthAnswerCustomer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private UserRole role;
    private Addresses addressId;
    private List<PaidType> paidTypeList;

    public AuthAnswerCustomer() {
    }

    public AuthAnswerCustomer(Long id, String firstName, String lastName, String email, String password, String phone, UserRole role, Addresses addressId, List<PaidType> paidTypeList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.addressId = addressId;
        this.paidTypeList = paidTypeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Addresses getAddressId() {
        return addressId;
    }

    public void setAddressId(Addresses addressId) {
        this.addressId = addressId;
    }

    public List<PaidType> getPaidTypeList() {
        return paidTypeList;
    }

    public void setPaidTypeList(List<PaidType> paidTypeList) {
        this.paidTypeList = paidTypeList;
    }
}
