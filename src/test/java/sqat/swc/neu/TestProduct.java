package sqat.swc.neu;

import org.junit.jupiter.api.Test;
import sqat.swc.neu.shop.Product;

import static org.junit.jupiter.api.Assertions.*;

public class TestProduct {

    @Test
    public void testCreateProduct_withNameAndPrice() {
        Product p = new Product("iPhone 11", 9000);

        assertEquals("iPhone 11", p.getName());
        assertEquals(9000, p.getPrice());
    }

    @Test
    public void testCreateProduct_ThrowException_ForNameNull() {
        assertThrows(NullPointerException.class, () -> {
            Product p = new Product(null, 9000);
        });
    }

    @Test
    public void testCreateProduct_ThrowException_ForNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Product p = new Product("", 9000);
        });
    }

    @Test
    public void testCreateProduct_ThrowException_ForPriceZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            Product p = new Product("iPhone 11", 0);
        });
    }

    @Test
    public void testSetPrice_ForPriceOne() {
        Product p = new Product("iPhone 11", 10);
        p.setPrice(2);
        assertEquals(2, p.getPrice());
    }

    @Test
    public void testSetPrice_ThrowException_ForPriceZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            Product p = new Product("iPhone 11", 10);
            p.setPrice(0);
        });
    }


}
