package application.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "characteristics_offers",
            joinColumns = {@JoinColumn(name = "offer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "characteristics_id", referencedColumnName = "id")})
    private List<Characteristics> characteristics;

    public Offers() {
    }

    public Offers(String name, float price, Long paidTypeId, Categories categoryId) {
        this.name = name;
        this.price = price;
        this.paidTypeId = paidTypeId;
        this.categoryId = categoryId;
    }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "paidtype_id", nullable = false)
    private Long paidTypeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Categories categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getPaidTypeId() {
        return paidTypeId;
    }

    public void setPaidTypeId(Long paidTypeId) {
        this.paidTypeId = paidTypeId;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public List<Characteristics> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristics> characteristics) {
        this.characteristics = characteristics;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }
}
