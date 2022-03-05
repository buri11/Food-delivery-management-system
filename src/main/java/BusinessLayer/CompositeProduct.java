package BusinessLayer;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Composite product.
 */
public class CompositeProduct extends MenuItem{
    private ArrayList<BaseProduct> meniu;
    private String title;
    private float rating;
    private float calories;
    private float protein;
    private float fat;
    private float sodium;
    private float price;

    /**
     * Instantiates a new Composite product.
     *
     * @param title the title
     * @param meniu the meniu
     */
    public CompositeProduct(String title, ArrayList<BaseProduct> meniu){
        this.title = title;
        this.meniu = meniu;
        float avgRating = 0, totalCals = 0, totalProt = 0, totalFat = 0, totalSodium = 0, totalPrice = 0;
        for(BaseProduct b : meniu){
            avgRating += b.getRating();
            totalCals += b.getCalories();
            totalProt += b.getProtein();
            totalFat += b.getFat();
            totalSodium += b.getSodium();
            totalPrice += b.getPrice();
        }
        avgRating /= meniu.size();
        this.rating = avgRating;
        this.calories = totalCals;
        this.protein = totalProt;
        this.fat = totalFat;
        this.sodium = totalSodium;
        this.price = totalPrice;
    }

    /**
     * Gets meniu.
     *
     * @return the meniu
     */
    public ArrayList<BaseProduct> getMeniu() {
        return meniu;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public float getRating() {
        return rating;
    }

    @Override
    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public float getCalories() {
        return calories;
    }

    @Override
    public void setCalories(float calories) {
        this.calories = calories;
    }

    @Override
    public float getProtein() {
        return protein;
    }

    @Override
    public void setProtein(float protein) {
        this.protein = protein;
    }

    @Override
    public float getFat() {
        return fat;
    }

    @Override
    public void setFat(float fat) {
        this.fat = fat;
    }

    @Override
    public float getSodium() {
        return sodium;
    }

    @Override
    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    @Override
    public float getPrice(){
        return price;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        CompositeProduct prod = (CompositeProduct) o;
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
}
