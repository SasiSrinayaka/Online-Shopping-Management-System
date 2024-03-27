package models;

import java.io.Serializable;
import java.util.Date;

public class Electronics extends Product implements Serializable {
    private String brand;
    private Date warranty;


    public Electronics() {
    }

    public Electronics(Product product, String brand, Date warranty) {
        super.setProductId(product.getProductId());
        super.setProductName(product.getProductName());
        super.setAvailableItems(product.getAvailableItems());
        super.setPrice(product.getPrice());
        this.brand = brand;
        this.warranty = warranty;
    }

    public Electronics(String productId, String productName, int availableItems, double price, String brand, Date warranty) {
        super(productId, productName, availableItems, price);
        this.brand = brand;
        this.warranty = warranty;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getWarranty() {
        return warranty;
    }

    public void setWarranty(Date warranty) {
        this.warranty = warranty;
    }


    @Override
    public String toString() {
        return "Electronics{" +
                "productId='" + super.getProductId() + '\'' +
                ", productName='" + super.getProductName() + '\'' +
                ", availableItems=" + super.getAvailableItems() +
                ", price=" + super.getPrice() +
                ", brand='" + brand + '\'' +
                ", warranty=" + warranty +
                '}';
    }
}
