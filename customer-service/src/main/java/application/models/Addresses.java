package application.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "addresses")
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Addresses() {
    }

    public Addresses(String city, String state, String country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "state",nullable = false)
    private String state;

    @Column(name = "country",nullable = false)
    private String country;

    @OneToMany(mappedBy = "addressId", cascade = CascadeType.ALL)
    private List<Customer> customers;

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addresses addresses = (Addresses) o;
        return city.equals(addresses.city) &&
                state.equals(addresses.state) &&
                country.equals(addresses.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, state, country);
    }
}
