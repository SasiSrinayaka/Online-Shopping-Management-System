package models;

import java.io.IOException;
import java.util.ArrayList;

public interface ShoppingManager {
    void addItem(Product product);
    void removeItem(String productId);
    ArrayList<Product> displayItems();
    void saveInFile() throws IOException;
}
