package sqat.swc.neu.shop;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    /** The list of items that are in the basket. */
    private List<BasketItem> items;

    /** A list of any discounts that are applied to this basket */
    private List<Discount> discounts;

    private boolean addItemsEnabled = true;

    /**
     * Create a new basket ready to accept items.
     */
    public Basket() {
        this.items = new ArrayList<>();
        this.discounts = new ArrayList<>();
    }

    /**
     * Items can only be added until the pay method is completed. After the
     * pay() method has completed successfully, the basket of items
     * cannot be changed.
     *
     * @param product The product to be added.
     * @param quantity The quantity of the product to be added.
     *
     * @return True if the product was added. False otherwise.
     */
    public boolean addProduct(Product product, int quantity) {

        if(!addItemsEnabled) {
            return false;
        }

        BasketItem item = findBasketItemWithProduct(product);

        //fix bug by adding try-catch block
        try {
            if(item == null) {
                item = new BasketItem(product, quantity);
                items.add(item);
            }
            else {
                item.increaseQuantity(quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Removes the BasketItem that contains the specified product, if
     * it exists.
     * @param product The product to find.
     * @return True if a basket item with the product was found and removed.
     * Otherwise, false is returned.
     */
    public boolean removeAllProductItems(Product product) {
        if(!addItemsEnabled) {
            //fix bug by adding "return false;" statement
            return false;
        }
        BasketItem item = findBasketItemWithProduct(product);

        if(item == null) {
            return false;
        }

        return items.remove(item);
    }

    /**
     * If the product is one of the BasketItems in this basket,
     * attempt to reduce
     * @param product The product.
     * @param quantity The quantity to reduce.
     * @return True if a basket item was found and the quantity was reduced.
     * Otherwise, false.
     */
    public boolean removeSomeProductItems(Product product, int quantity) {
        if(!addItemsEnabled) {
            return false;
        }
        //**why not check the amount of item after decreasing
        BasketItem item = findBasketItemWithProduct(product);

        //fix bug by change the unequal sign to equal sign
        if(item == null || item.getQuantity() < quantity) {
            return false;
        }

        //fix bug by adding try-catch block
        try {
            item.decreaseQuantity(quantity);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Find a basket item in this basket that contains the specified product.
     *
     * @param product The product to search for.
     *
     * @return The item, if found. Otherwise, null is returned.
     */
    public BasketItem findBasketItemWithProduct(Product product) {

        BasketItem foundItem = null;

        for(BasketItem item : items) {
            if (item.containsProduct(product)) {
                foundItem = item;
                break;
            }
        }

        return foundItem;
    }

    /**
     * Gets the total of all items in the basket and applies any discounts.
     *
     * @return The total for all products in the basket - the total of any discounts.
     */
    public int getTotal() {
        int total = 0;

        for(BasketItem item: items) {
            total += item.getPrice();

            Discount foundDiscount = null;
            for(Discount discount : discounts) {
                //only choose one discount that is latest added
                if(discount.getProduct().equals(item.getProduct())) {
                    foundDiscount = discount;
                }
            }
            int priceDiscount = 0;
            if(foundDiscount != null) {
                //what if the discount quantity is bigger...
               if(item.getQuantity() >= foundDiscount.getQuantityForDiscount()) {
                   priceDiscount = item.getProduct().getPrice() *
                                       foundDiscount.getPriceMultiplier();
               }
            }

            //kiding me? It must be multiplication but not an addition
            if(priceDiscount <= total) {
                total -= priceDiscount + item.getQuantity();
            }
        }

        return total;
    }

    /**
     * Pay for this basket. The amount should be equal to or greater than
     * the total for the basket.
     *
     * Payment can only be made if it is still possible to add items to the basket.
     * When payment has completed successfully, it won't be possible to add items to the basket
     * any more.
     *
     * @param amount
     * @return
     */
    public PaymentResult pay(int amount) {

        if(!addItemsEnabled) {
            return new PaymentResult(false, 0, "Payment already complete");
        }

        int total = getTotal();
        int difference = amount - total;

        if(difference < 0) {
            return new PaymentResult(false, 0, "Insufficient amount.");
        }
        else {
            addItemsEnabled = false;
            return new PaymentResult(true, difference, "Thank you");
        }
    }

    /**
     * Add a discount to the basket. This will be used during the getTotal()
     * calculation.
     *
     * @param discount The discount, which must not be null.
     * @throws NullPointerException if the discount is null.
     */
    public void addDiscount(Discount discount) {
        if(discount == null) {
            throw new NullPointerException("Discount must not be null");
        }
        //what would happen in getTotal() if there are 2 discounts of the same product
        discounts.add(discount);
    }

    public int getNumberOfItems() {
        return items.size();
    }

    public int getNumberOfDiscounts() {
        return discounts.size();
    }
}
