package application.models;

import java.util.List;

public class Offers {
    private Long id;
    private String name;
    private float price;
    private Categories categoryId;
    private Long PaidTypeId;
    private List<Characteristics> characteristics;

    public Offers() {
    }

    public Offers(Long id, List<Characteristics> characteristics, String name, float price,  Long paidTypeId, Categories categoryId) {
        this.id = id;
        this.characteristics = characteristics;
        this.name = name;
        this.price = price;
        this.PaidTypeId = paidTypeId;
        this.categoryId = categoryId;
    }

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

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPaidTypeId() {
        return PaidTypeId;
    }

    public void setPaidTypeId(Long paidTypeId) {
        PaidTypeId = paidTypeId;
    }

    public List<Characteristics> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristics> characteristics) {
        this.characteristics = characteristics;
    }
}
