package models.cart;


import java.io.Serializable;

public class Product {
    private String productId;
    private String productName;
    private int availableItems;
    private double price;
    private int needItems;


    public Product() {
    }

    public Product(String productId, String productName, int availableItems, double price, int needItems) {
        this.productId = productId;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
        this.needItems = needItems;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNeedItems() {
        return needItems;
    }

    public void setNeedItems(int needItems) {
        this.needItems = needItems;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", availableItems=" + availableItems +
                ", price=" + price +
                ", needItems=" + needItems +
                '}';
    }
}

