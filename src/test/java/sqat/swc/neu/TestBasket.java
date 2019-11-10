package sqat.swc.neu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sqat.swc.neu.shop.Basket;
import sqat.swc.neu.shop.Product;
import static org.junit.jupiter.api.Assertions.*;

public class TestBasket {

    private Basket basket;

    private Product product = new Product("product", 1);

    @BeforeEach
    public void setupBeforeEachTest(){
        basket = new Basket();
    }

    /**
     * Bug was found!
     * When {@code null} was passed as the first parameter of the addProduct() method,
     * the "product" won't be added and should return {@code false}.
     * But a {@code NullPointerException} was thrown with message: Product must not be null
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
     * But a {@code IllegalArgumentException} was thrown with message: The quantity to add should be 1 or more
     */
    @Test
    public void testAddProduct_AddNewProduct_WithZeroQuantity_BeforePay(){
        assertFalse(basket.addProduct(product, 0));
    }

    /**
     * Bug was found!
     * Add a new product with negative quantity into the basket,
     * the product shouldn't be added and should return {@code false}.
     * But a {@code IllegalArgumentException} was thrown with message: The quantity to add should be 1 or more
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
     * But a {@code IllegalArgumentException} was thrown with message: The quantity to add should be 1 or more
     */
    @Test
    public void testAddProduct_AddExistProduct_WithZeroQuantity_BeforePay(){
        assertTrue(basket.addProduct(product, 1));
        //problem happened below
        assertFalse(basket.addProduct(product, 0));
    }

    /**
     * Bug was found!
     * Add product already exist in the basket with negative quantity,,
     * the product shouldn't be be added and should return {@code false}.
     * But a {@code IllegalArgumentException} was thrown with message: The quantity to add should be 1 or more
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
    public void testRemoveAllProductItems(){

    }

    @Test
    public void testRemoveSomeProductItems(){

    }

    @Test
    public void testFindBasketItemWithProduct(){

    }

    @Test
    public void testGetTotal(){

    }

    @Test
    void testPay() {

    }

    @Test
    void testAddDiscount() {

    }
}
