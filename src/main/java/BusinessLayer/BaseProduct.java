package BusinessLayer;

import java.util.Objects;

/**
 * The type Base product.
 */
public class BaseProduct extends MenuItem{
    private String title;
    private float rating;
    private float calories;
    private float protein;
    private float fat;
    private float sodium;
    private float price;

    /**
     * Instantiates a new Base product.
     */
    public BaseProduct(){

    }

    /**
     * Instantiates a new Base product.
     *
     * @param title    the title
     * @param rating   the rating
     * @param calories the calories
     * @param protein  the protein
     * @param fat      the fat
     * @param sodium   the sodium
     * @param price    the price
     */
    public BaseProduct(String title, float rating, float calories, float protein, float fat, float sodium, float price){
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }



    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        BaseProduct prod = (BaseProduct)o;
        if(prod == this){
            return true;
        }

        if(prod.getTitle().equals(title)){
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
