package sqat.swc.neu.shop;

public class Discount {

    private String discountCode;

    private Product product;

    private int quantityForDiscount = 0;

    private int priceMultiplier = 1;

    public Discount(String code, Product product,
                    int discountQuantity,
                    int priceMultiplier) {

        this.discountCode = code;
        this.product = product;
        this.quantityForDiscount = discountQuantity;
        this.priceMultiplier = priceMultiplier;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantityForDiscount() {
        return quantityForDiscount;
    }

    public int getPriceMultiplier() {
        return priceMultiplier;
    }
}
