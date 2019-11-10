package sqat.swc.neu.shop;

public class Shop {

    /**
     * This is an example of using the Shopping Basket.
     * This shows you how to call some of the operations on the Basket.
     * You can run this to see what happens when you do call some items.
     *
     * You need to write tests for the methods in the Basket class. You do not
     * need to write tests for the other classes.
     *
     * @param args The command line arguments. These are not used in the program.
     */
    public static void main(String[] args) {
        Basket basket = new Basket();

        Product phoneProduct =
                new Product("Samsung S10", 9000);
        Product printerProduct =
                new Product("Samsung M254dw", 1700);
        Product watchProduct =
                new Product("Apple Watch", 5200);

        basket.addDiscount(
                new Discount("PH0301", phoneProduct,
                        3, 1));
        basket.addDiscount(new Discount("PR0202",
                printerProduct, 2, 2));

        basket.addProduct(phoneProduct, 4);
        basket.addProduct(printerProduct, 10);
        basket.addProduct(watchProduct, 3);
        basket.addProduct(phoneProduct, 1);

        basket.removeSomeProductItems(phoneProduct, 1);

        System.out.println("Total: " + basket.getTotal());

        PaymentResult result = basket.pay(57000);
        System.out.println(result);

    }
}
