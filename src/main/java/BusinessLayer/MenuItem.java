package BusinessLayer;

import java.io.Serializable;

/**
 * The type Menu item.
 */
public abstract class MenuItem implements Serializable {
    private static int idCount = 1;
    private int id;

    private int orderedFor = 0;

    /**
     * Gets title.
     *
     * @return the title
     */
    public abstract String getTitle();

    /**
     * Sets title.
     *
     * @param title the title
     */
    public abstract void setTitle(String title);

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public abstract float getRating();

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public abstract void setRating(float rating);

    /**
     * Gets calories.
     *
     * @return the calories
     */
    public abstract float getCalories();

    /**
     * Sets calories.
     *
     * @param calories the calories
     */
    public abstract void setCalories(float calories);

    /**
     * Gets protein.
     *
     * @return the protein
     */
    public abstract float getProtein();

    /**
     * Sets protein.
     *
     * @param protein the protein
     */
    public abstract void setProtein(float protein);

    /**
     * Gets fat.
     *
     * @return the fat
     */
    public abstract float getFat();

    /**
     * Sets fat.
     *
     * @param fat the fat
     */
    public abstract void setFat(float fat);

    /**
     * Gets sodium.
     *
     * @return the sodium
     */
    public abstract float getSodium();

    /**
     * Sets sodium.
     *
     * @param sodium the sodium
     */
    public abstract void setSodium(float sodium);

    /**
     * Gets price.
     *
     * @return the price
     */
    public abstract float getPrice();

    /**
     * Sets price.
     *
     * @param price the price
     */
    public abstract void setPrice(float price);

    /**
     * Set id.
     */
    public void setID(){
        id = idCount;
        idCount++;
    }

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getId(){
        return id;
    }

    /**
     * Increment ordered nb.
     */
    public void incrementOrderedNb(){
        orderedFor++;
    }

    /**
     * Gets ordered for.
     *
     * @return the ordered for
     */
    public int getOrderedFor() {
        return orderedFor;
    }

    @Override
    public abstract boolean equals(Object o);//pentru distinct()

    @Override
    public abstract int hashCode();//pentru distinct()

}
