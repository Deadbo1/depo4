package application.models;

public class NewObj {

    private Categories categoriesId;
    private float Price;

    public NewObj() {
    }

    public NewObj(Categories categoriesId, Long price) {
        this.categoriesId = categoriesId;
        Price = price;
    }

    public Categories getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Categories categoriesId) {
        this.categoriesId = categoriesId;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }
}
