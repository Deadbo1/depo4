package application.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "paid_type")
public class PaidType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "paidTypeList")
    private List<Customer> customerList;

    public PaidType() {
    }

    public PaidType(String name) {
        this.name = name;
    }

    @Column(name = "name",nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
