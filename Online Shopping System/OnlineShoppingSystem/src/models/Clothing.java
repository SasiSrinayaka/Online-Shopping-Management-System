package models;

import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    private String size;
    private String colour;


    public Clothing() {
    }

    public Clothing(String productId, String productName, int availableItems, double price, String size, String colour) {
        super(productId, productName, availableItems, price);
        this.size = size;
        this.colour = colour;
    }

    public Clothing(Product product, String size, String colour) {
        super.setProductId(product.getProductId());
        super.setProductName(product.getProductName());
        super.setAvailableItems(product.getAvailableItems());
        super.setPrice(product.getPrice());
        this.size = size;
        this.colour = colour;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }


    @Override
    public String toString() {
        return "Clothing{" +
                "productId='" + super.getProductId() + '\'' +
                ", productName='" + super.getProductName() + '\'' +
                ", availableItems=" + super.getAvailableItems() +
                ", price=" + super.getPrice() +
                ", size=" + size +
                ", colour='" + colour + '\'' +
                '}';
    }
}
