import models.Product;
import models.User;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WestminsterShoppingManagerTest {

    @Test
    public void addItem() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        assertEquals(1, productList.size());
    }

    @Test
    public void removeItem() {
        ArrayList<Product> productList = new ArrayList<>();

        Product product = new Product();
        product.setProductId("Ab5");

        productList.add(product);
        productList.removeIf(p -> p.getProductId().equalsIgnoreCase(product.getProductId()));

        assertEquals(0, productList.size());
    }

    @Test
    public void addNewUser()  {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User());
        assertEquals(1, userList.size());
    }

}