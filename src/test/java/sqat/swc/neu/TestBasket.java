package sqat.swc.neu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sqat.swc.neu.shop.Basket;
import sqat.swc.neu.shop.Discount;
import sqat.swc.neu.shop.Product;
import static org.junit.jupiter.api.Assertions.*;

public class TestBasket {

    private Basket basket;

    private Product product = new Product("product", 1);

    private Product product2 = new Product("product2", 2);

    @BeforeEach
    public void setupBeforeEachTest(){
        basket = new Basket();
    }

    /**
     * Bug was found!
     * When {@code null} was passed as the first parameter of the addProduct() method,
     * the "product" won't be added and should return {@code false}.
     * But a {@link NullPointerException} was thrown with message: Product must not be null
     */
    @Test
    public void testAddProduct_AddNull_BeforePay(){
        //problem happened below
        assertFalse(basket.addProduct(null, 1));
    }

    @Test
    public void testAddProduct_AddNewProduct_WithPositiveQuantity_BeforePay(){
        assertTrue(basket.addProduct(product, 1));
        assertEquals(1, basket.getNumberOfItems());
    }

    /**
     * Bug was found!
     * Add a new product with 0 quantity into the basket,
     * the product shouldn't be added and should return {@code false}.
     * But a {@link IllegalArgumentException} was thrown with message: The quantity to add should be 1 or more
     */
    @Test
    public void testAddProduct_AddNewProduct_WithZeroQuantity_BeforePay(){
        assertFalse(basket.addProduct(product, 0));
    }

    /**
     * Bug was found!
     * Add a new product with negative quantity into the basket,
     * the product shouldn't be added and should return {@code false}.
     * But a {@link IllegalArgumentException} was thrown with message: The quantity to add should be 1 or more
     */
    @Test
    public void testAddProduct_AddNewProduct_WithNegativeQuantity_BeforePay(){
        assertFalse(basket.addProduct(product, -1));
    }

    @Test
    public void testAddProduct_AddExistProduct_WithPositiveQuantity_BeforePay(){
        assertTrue(basket.addProduct(product, 1));
        assertTrue(basket.addProduct(product, 1));
        assertEquals(1, basket.getNumberOfItems());
    }

    /**
     * Bug was found!
     * Add product already exist in the basket with 0 quantity,
     * the product shouldn't be added and should return {@code false}.
     * But a {@link IllegalArgumentException} was thrown with message: The quantity to add should be 1 or more
     */
    @Test
    public void testAddProduct_AddExistProduct_WithZeroQuantity_BeforePay(){
        assertTrue(basket.addProduct(product, 1));
        //problem happened below
        assertFalse(basket.addProduct(product, 0));
    }

    /**
     * Bug was found!
     * Add product already exist in the basket with negative quantity,
     * the product shouldn't be be added and should return {@code false}.
     * But a {@link IllegalArgumentException} was thrown with message: The quantity to add should be 1 or more
     */
    @Test
    public void testAddProduct_AddExistProduct_WithNegativeQuantity_BeforePay(){
        assertTrue(basket.addProduct(product, 1));
        //problem happened below
        assertFalse(basket.addProduct(product, -1));
    }

    @Test
    public void testAddProduct_AddSomething_AfterPay(){
        basket.pay(0);
        assertFalse(basket.addProduct(null, 1));
        assertFalse(basket.addProduct(product, 1));
        assertFalse(basket.addProduct(product, 0));
        assertFalse(basket.addProduct(product, -1));
    }

    @Test
    public void testRemoveAllProductItems_RemoveProductWithMatchedItems_BeforePay(){
        basket.addProduct(product, 1);
        basket.addProduct(product2,1);
        assertTrue(basket.removeAllProductItems(product));
        assertEquals(1, basket.getNumberOfItems());
        assertTrue(basket.removeAllProductItems(product2));
        assertEquals(0, basket.getNumberOfItems());
    }

    @Test
    public void testRemoveAllProductItems_RemoveProductWithoutMatchedItems_BeforePay(){
        assertFalse(basket.removeAllProductItems(product2));
        basket.addProduct(product,  1);
        assertFalse(basket.removeAllProductItems(product2));
        assertEquals(1, basket.getNumberOfItems());
    }

    @Test
    public void testRemoveAllProductItems_RemoveNull_BeforePay(){
        assertFalse(basket.removeAllProductItems(null));
        basket.addProduct(product,  1);
        assertFalse(basket.removeAllProductItems(null));
        assertEquals(1, basket.getNumberOfItems());
    }

    /**
     * Bug was found!
     * After basket was paid, removing product already exist in the basket,
     * the product shouldn't be be removed and should return {@code false}.
     * But a {@link org.opentest4j.AssertionFailedError} was thrown because the actual return value is {@code true}.
     */
    @Test
    public void testRemoveAllProductItems_AfterPay(){
        basket.addProduct(product, 1);
        basket.pay(1);

        assertFalse(basket.removeAllProductItems(null));
        //problem happened below
        assertFalse(basket.removeAllProductItems(product));
        assertEquals(1, basket.getNumberOfItems());
    }

    /**
     * Removing null from the basket, {@code false} should be returned.
     * But a {@link NullPointerException} was thrown.
     */
    @Test
    public void testRemoveSomeProductItems_RemoveNull_BeforePay(){
        assertFalse(basket.removeSomeProductItems(null, 1));
    }

    /**
     * 2 Bug was found!
     * Removing some amount of a product already exist from the basket, {@code false} should be returned.
     * First bug, a {@link NullPointerException} was thrown.
     * Second bug, after fix the first bug a {@link IllegalArgumentException} was thrown with message:
     * "The quantity to remove should be 1 or more"
     * because of the bug of the decreaseQuantity() method in {@link sqat.swc.neu.shop.BasketItem}.
     */
    @Test
    public void testRemoveSomeProductItems_RemovePositiveAmountOfProductWithEnoughMatchedItems_BeforePay(){
        basket.addProduct(product, 2);
        //problem happened below
        assertFalse(basket.removeSomeProductItems(product, 0));
        assertTrue(basket.removeSomeProductItems(product, 1));
        assertFalse(basket.removeSomeProductItems(product, -1));
        assertEquals(1, basket.getNumberOfItems());
    }

    /**
     * Bug was found!
     * Removing some amount of a product already exist from the basket, {@code false} should be returned.
     * But a {@link IllegalArgumentException} was thrown with message:
     * "The quantity to remove should be 1 or more".
     */
    @Test
    public void testRemoveSomeProductItems_RemoveZeroAmountOfProductWithEnoughMatchedItems_BeforePay(){
        basket.addProduct(product, 2);
        //problem happened below
        assertFalse(basket.removeSomeProductItems(product, 0));
        assertEquals(1, basket.getNumberOfItems());
    }

    /**
     * Bug was found!
     * Removing some amount of a product already exist from the basket, {@code false} should be returned.
     * But a {@link IllegalArgumentException} was thrown with message:
     * "The quantity to remove should be 1 or more".
     */
    @Test
    public void testRemoveSomeProductItems_RemoveNegativeAmountOfProductWithEnoughMatchedItems_BeforePay(){
        basket.addProduct(product, 2);
        //problem happened below
        assertFalse(basket.removeSomeProductItems(product, -1));
        assertEquals(1, basket.getNumberOfItems());
    }

    /**
     * Bug was found!
     * Removing some amount of a product already exist from the basket, {@code false} should be returned
     * because the removed amount is less than the exist amount.
     * But a {@link org.opentest4j.AssertionFailedError} was thrown because the actual return value is {@code true}.
     */
    @Test
    public void testRemoveSomeProductItems_RemoveProductWithoutEnoughMatchedItems_BeforePay(){
        basket.addProduct(product, 1);
        assertFalse(basket.removeSomeProductItems(product, 2));
    }

    @Test
    public void testRemoveSomeProductItems_RemoveNonexistentProduct_BeforePay(){
        assertFalse(basket.removeSomeProductItems(product, 1));
        assertFalse(basket.removeSomeProductItems(product, 0));
        assertFalse(basket.removeSomeProductItems(product, -1));
    }

    @Test
    public void testRemoveSomeProductItems_AfterPay(){
        basket.addProduct(product, 1);
        basket.pay(1);

        assertFalse(basket.removeSomeProductItems(null, 1));
        assertFalse(basket.removeSomeProductItems(product, 1));
        assertFalse(basket.removeSomeProductItems(product, 0));
        assertFalse(basket.removeSomeProductItems(product, -1));
        assertEquals(1, basket.getNumberOfItems());
    }

    @Test
    public void testFindBasketItemWithProduct_FindNull(){
        assertNull(basket.findBasketItemWithProduct(null));
        basket.addProduct(product, 1);
        assertNull(basket.findBasketItemWithProduct(null));
    }

    @Test
    public void testFindBasketItemWithProduct_FindExistProduct(){
        basket.addProduct(product, 1);
        basket.addProduct(product2, 1);
        assertNotNull(basket.findBasketItemWithProduct(product));
        assertNotNull(basket.findBasketItemWithProduct(product2));
    }

    @Test
    public void testFindBasketItemWithProduct_FindNonexistentProduct(){
        assertNull(basket.findBasketItemWithProduct(product));
        assertNull(basket.findBasketItemWithProduct(product2));
    }

    @Test
    void testAddDiscount_AddNull() {
        assertThrows(NullPointerException.class, () -> basket.addDiscount(null));
    }

    /**
     * Bug was found!
     * Get the total price of the basket.
     * {@link org.opentest4j.AssertionFailedError} was thrown because
     * the actual return value is 1 while the expected value is 3.
     */
    @Test
    public void testGetTotal_WithoutDiscount(){
        basket.addProduct(product, 1);
        basket.addProduct(product2, 1);
        assertEquals(3, basket.getTotal());
    }

    @Test
    public void testGetTotal_WithAnyDiscount(){
        basket.addProduct(product, 2);
        basket.addProduct(product2, 1);
        basket.addDiscount(new Discount("product1 discount", product, 2, 1));
        assertEquals((1 * 2 + 2) - 1, basket.getTotal());
    }

    @Test
    void testPay() {

    }
}
