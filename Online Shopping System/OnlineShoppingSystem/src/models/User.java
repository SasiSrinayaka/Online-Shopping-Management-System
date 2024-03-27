package models;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;
    private ShoppingCart shoppingCart;
    private boolean firstPurchase;


    public User() {
    }

    public User(String userName, String password, boolean firstPurchase) {
        this.userName = userName;
        this.password = password;
        this.firstPurchase = firstPurchase;
    }

    public User(String userName, String password, ShoppingCart shoppingCart, boolean firstPurchase) {
        this.userName = userName;
        this.password = password;
        this.shoppingCart = shoppingCart;
        this.firstPurchase = firstPurchase;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public boolean isFirstPurchase() {
        return firstPurchase;
    }

    public void setFirstPurchase(boolean firstPurchase) {
        this.firstPurchase = firstPurchase;
    }
}
