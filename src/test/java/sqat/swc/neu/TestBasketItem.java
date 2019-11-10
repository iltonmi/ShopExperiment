package sqat.swc.neu;

import org.junit.jupiter.api.Test;
import sqat.swc.neu.shop.BasketItem;
import sqat.swc.neu.shop.Product;

import static org.junit.jupiter.api.Assertions.*;

public class TestBasketItem {

    @Test
    public void testCreate_WithProductAndPrice() {
        Product product = new Product("iPhone 11", 9000);
        BasketItem item = new BasketItem(product, 2);

        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());
    }

    @Test
    public void testCreate_WithNullProduct() {
        assertThrows(NullPointerException.class, () -> {
            BasketItem item = new BasketItem(null, 1);
        });
    }

    @Test
    public void testCreate_WithQuantityZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            Product product = new Product("iPhone 11", 9000);
            BasketItem item = new BasketItem(product, 0);
        });
    }

}
