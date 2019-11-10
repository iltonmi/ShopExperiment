package sqat.swc.neu.shop;

/**
 *
 * @author Neil Taylor (nst@aber.ac.uk)
 */
public class Product {

    /** The product name */
    private String name;

    /** The price of the product. This should be greater than or equal to 1. */
    private int price;

    /**
     * Sets the name and price for the Product.
     *
     * @param name
     * @throws NullPointerException If the name is null.
     * @throws IllegalArgumentException If the name has a length of 0 or the price is less than 0.
     */
    public Product(String name, int price) {

        setName(name);
        setPrice(price);

    }

    public void setName(String name) {

        if(name == null) {
            throw new NullPointerException("The name must not be null");
        }

        if(name.length() == 0) {
            throw new IllegalArgumentException("Name must have at least one character");
        }

        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price for the product.
     *
     * @return The price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * @param price
     * @throws IllegalArgumentException if the price is less than 0.
     */
    public void setPrice(int price) {
        if(price < 1) {
            throw new IllegalArgumentException("Price must be 1 or higher.");
        }

        this.price = price;
    }

    /**
     * Return a string description of the Product.
     * @return
     */
    @Override
    public String toString() {
        return name + "\t" + price;
    }

    @Override
    public boolean equals(Object other) {

        if(other instanceof Product) {
            Product otherProduct = (Product)other;
            if(name.equals(otherProduct.name) &&
               price == otherProduct.price) {
                return true;
            }
        }

        return false;
    }
}
