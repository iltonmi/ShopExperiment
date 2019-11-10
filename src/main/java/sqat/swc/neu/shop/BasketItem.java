package sqat.swc.neu.shop;

public class BasketItem {

    /** The product */
    private Product product;

    /** The number of items of the product that are in the basket */
    private int quantity;

    /**
     * Create a new item that specifies the product and the initial
     * quantity.
     *
     * @param product The product.
     * @param quantity The number of items of this product in the basket.
     *
     * @throws NullPointerException if the product is null.
     * @throws IllegalArgumentException if the quantity is 0 or less
     */
    public BasketItem(Product product, int quantity) {
        this.setProduct(product);
        this.increaseQuantity(quantity);
    }

    /**
     * Set the product. The product must not be null.
     * @param product
     * @throws NullPointerException if the product is null.
     */
    private void setProduct(Product product) {
        if(product == null) {
            throw new NullPointerException("Product must not be null");
        }

        this.product = product;
    }

    /**
     * Checks if the product in this item matches the specified product.
     *
     * @param product
     * @return True if the products match, otherwise false.
     */
    public boolean containsProduct(Product product)  {
        return this.product.equals(product);
    }

    /**
     * Increase the quantity for this product. The quantity should be 1 or more.
     *
     * @param quantity The quantity.
     * @throws IllegalArgumentException if the quantity is less than 1.
     */
    public void increaseQuantity(int quantity) {
        if(quantity < 1) {
            throw new IllegalArgumentException("The quantity to add should be 1 or more");
        }

        this.quantity += quantity;
    }

    /**
     * Decrease the quantity for this product. The value should be 1 or more.
     *
     * @param quantity The quantity.
     * @throws IllegalArgumentException if the quantity is less than 1.
     */
    public void decreaseQuantity(int quantity) {
        if(quantity <= 1) {
            throw new IllegalArgumentException("The quantity to remove should be 1 or more");
        }

        this.quantity -= quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the price for this item, which is the price of the product, multiplied
     * by the quantity.
     *
     * @return The price * quantity.
     */
    public int getPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.toString() + " with quantity: " + quantity;
    }

}
