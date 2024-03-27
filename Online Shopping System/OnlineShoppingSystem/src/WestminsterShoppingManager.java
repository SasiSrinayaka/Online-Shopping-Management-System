import models.*;
import view.WestminsterShoppingCenter;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Product> productList;
    private static ArrayList<User> userList;


    public WestminsterShoppingManager() {
        if(productList == null || productList.isEmpty()) {
            productList = new ArrayList<>();
        }

        if(userList == null || userList.isEmpty()) {
            userList = new ArrayList<>();
        }
    }

//    public WestminsterShoppingManager(){
//
//    }

    @Override
    public void addItem(Product product) {
        productList.add(product);
    }

    @Override
    public void removeItem(String productId) {
        productList.removeIf(p -> p.getProductId().equalsIgnoreCase(productId.toLowerCase()));
    }

    @Override
    public ArrayList<Product> displayItems() {
        return productList;
    }

    @Override
    public void saveInFile() {
        saveProductListIntoFile("productListData");
        saveProductListIntoFile("userListData");
    }


    private String getStrInput(String txt) {
        String input;

        while (true) {
            System.out.print(txt);
            input = scanner.nextLine();

            if(!input.isEmpty()) {
                break;
            } else {
                System.out.println("Invalid input try again !");
            }
        }

        return input;
    }

    private int getIntInput(String txt) {
        boolean validInput = true;
        int input = -1;

        while (validInput) {
            try {
                String userInput = getStrInput(txt);
                input = Integer.parseInt(userInput);
                validInput = false;

            } catch (Exception e) {
                System.out.println("invade input data type, try again with integer !");
            }
        }

        return input;
    }

    private double getDoubleInput(String txt) {
        boolean validInput = true;
        double input = -1;

        while (validInput) {
            try {
                String userInput = getStrInput(txt);
                input = Double.parseDouble(userInput);
                validInput = false;

            } catch (Exception e) {
                System.out.println("invade input data type, try again with float !");
            }
        }

        return input;
    }

    private Product getProductDetails() {
        int loopbreak = -1;
        String id = null;
        String name = null;
        int itemCount = 0;
        double price = 0;

        while (loopbreak != 0) {
            id = getStrInput("Product ID > ");

            if(!productList.isEmpty()) {
                for (Product p : productList) {
                    if(id.equalsIgnoreCase(p.getProductId().toLowerCase())) {
                        System.out.println("This ID is already use enter another ne ID !\n");
                        loopbreak = 1;
                        break;

                    } else {
                        loopbreak = 0;
                    }
                }

            } else {
                loopbreak = 0;
            }


        }

        name = getStrInput("Product name > ");

        loopbreak = -1;
        while (loopbreak != 0) {
            itemCount = getIntInput("Available item count > ");

            if (itemCount > 0) {
                loopbreak = 0;

            } else {
                System.out.println("Invade item count, try again !\n");
            }
        }

        loopbreak = -1;
        while (loopbreak != 0) {
            price = getDoubleInput("Price (per item) > ");

            if (price > 0) {
                loopbreak = 0;

            } else {
                System.out.println("Invade price, try again !\n");
            }
        }

        return new Product(id, name, itemCount, price);
    }

    private void addNewProduct() {
        Electronics electronics;
        Clothing clothing;
        boolean loopbreak = true;

        while (loopbreak) {
            System.out.println("********************************************************");
            System.out.println("\t\tAdd a new product\n");
            System.out.println("\t1. Electronics");
            System.out.println("\t2. Clothing");
            System.out.println("\t0. Back to main menu");

            switch (getIntInput("\nSelect > ")) {
                case 0:
                    loopbreak = false;
                    break;

                case 1:
                    electronics = new Electronics(getProductDetails() ,null, null);
                    electronics.setBrand(getStrInput("Brand > "));

                    // Validate the input format if needed
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false);

                    while (true) {
                        try {
                            electronics.setWarranty(dateFormat.parse(getStrInput("Warranty date > ")));
                            break;

                        } catch (ParseException e) {
                            System.out.println("Invalid date. Please enter in the format YYYY-MM-DD !\n");
                        }
                    }

                    productList.add(electronics);
                    System.out.println("Successfully add Electronic product");
                    break;

                case 2:
                    clothing = new Clothing(getProductDetails() ,null, null);

                    while (true) {
                        String size = getStrInput("Size > ");
                        if(size != null && !size.isEmpty()) {
                            clothing.setSize(size);
                            break;

                        } else {
                            System.out.println("Invalid input. try again !\n");
                        }
                    }

                    clothing.setColour(getStrInput("Colour > "));

                    productList.add(clothing);
                    System.out.println("Successfully add Clothing product");
                    break;

                default:
                    System.out.println("Invalid input try again !");
                    break;
            }

            System.out.println("\n");
        }
    }

    public void addNewUser(User user) {
        userList.add(user);
        saveProductListIntoFile("userListData");
    }

    private void showProductAlphabetically(ArrayList<Product> tempProductList) throws ParseException {
        tempProductList.sort(Comparator.comparing(Product::getProductId));

        for (int i=0;i<172;i++) {
            System.out.print("*");
        }
        System.out.printf("\n| %-15s | %-15s | %-30s | %-18s | %-8s | %-8s | %-15s | %-20s | %-15s |\n",
                "Product ID", "Product", "Product name", "Available items", "Price", "Size",
                "Colour", "Brand", "Warranty");
        for (int i=0;i<172;i++) {
            System.out.print("*");
        }
        for (Product p : tempProductList) {
            System.out.println();
            if (p.getClass().getName().substring(p.getClass().getName().
                    lastIndexOf('.')+1).equalsIgnoreCase("electronics")) {
                Electronics e = (Electronics) p;
                System.out.printf("| %-15s | %-15s | %-30s | %-18s | %-8s | %-8s | %-15s | %-20s | %-15s |",
                        p.getProductId(), "Electronics", p.getProductName(), String.valueOf(p.getAvailableItems()),
                        String.valueOf(p.getPrice()), "   --   ", "      ---      ",
                        e.getBrand(), new SimpleDateFormat("yyyy-MM-dd").
                                format(new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).
                                        parse(String.valueOf(e.getWarranty()))));

            } else {
                Clothing c = (Clothing) p;
                System.out.printf("| %-15s | %-15s | %-30s | %-18s | %-8s | %-8s | %-15s | %-20s | %-15s |",
                        p.getProductId(), "Clothing", p.getProductName(), String.valueOf(p.getAvailableItems()),
                        String.valueOf(p.getPrice()), String.valueOf(c.getSize()), c.getColour(),
                        "         --         ", "      ---      ");
            }
        }
        System.out.println();
        for (int i=0;i<172;i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    private void deleteProduct() throws ParseException {
        boolean loopBreak = true;

        while (loopBreak) {
            System.out.println("********************************************************");
            System.out.println("\t\tDelete a product\n");
            System.out.println("\t0. Back to main menu\n");

            String id = getStrInput("Product ID > ");

            if(id.equalsIgnoreCase("0")) {
                break;

            } else {
                boolean available = false;

                for (Product p : productList) {
                    if (p.getProductId().equalsIgnoreCase(id.toLowerCase())) {

                        showProductAlphabetically(new ArrayList<>(List.of(p)));

                        if ("y".equalsIgnoreCase(getStrInput(
                                "Are you sure you want to delete this product (y/n) ? > "))) {
                            productList.remove(p);
                            System.out.println();
                        }

                        available = true;
                        break;
                    }
                }

                if (!available) {
                    System.out.println("Invade ID number, try again !\n");
                }
            }
        }


    }

    private void saveProductListIntoFile(String saveFileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFileName + ".dat"))) {
            if(saveFileName.equalsIgnoreCase("productlistdata")) {
                oos.writeObject(productList);
            } else {
                oos.writeObject(userList);
            }
            System.out.println("Successfully save data into file: " + saveFileName +".dat");

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void loadProductListDataFromFile(String saveFileName) {
        if (Files.exists(Paths.get(saveFileName + ".dat"))) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFileName + ".dat"))) {
                Object obj = ois.readObject();
                if (obj instanceof ArrayList) {
                    if(saveFileName.equalsIgnoreCase("productlistdata")) {
                        productList = (ArrayList<Product>) obj;

                    } else {
                        userList = (ArrayList<User>) obj;
                    }

                    System.out.println("Successfully loaded data from file: " + saveFileName + ".dat");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading: " + e.getMessage());
            }
        } else {
            System.out.println("File '" + saveFileName + ".dat' does not exist in the current working directory. " +
                    "No data loaded.");
        }
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    private int mainMenu() {
        System.out.println("********************************************************");
        System.out.println("\t\tOnline Shopping System\n");
        System.out.println("\t1. Add a new product");
        System.out.println("\t2. Delete a product");
        System.out.println("\t3. Print the list of the products");
        System.out.println("\t4. Save in a file");
        System.out.println("\t5. Open GUI");
        System.out.println("\t0. Exit");
        return getIntInput("\nSelect > ");
    }

    public void startConsoleMenu() throws ParseException {
        loadProductListDataFromFile("productListData");
        loadProductListDataFromFile("userListData");
//        addproduct();

        boolean loopbreak = true;
        while (loopbreak) {
           switch (mainMenu()) {
               case 0:
                   loopbreak = false;
                   break;

               case 1:
                   addNewProduct();
                   break;

               case 2:
                   deleteProduct();
                   break;

               case 3:
                   System.out.println();
                   showProductAlphabetically(productList);
                   break;

               case 4:
                   saveInFile();
                   break;

               case 5:
                   new LoginOrSingInPage().setVisible(true);
//                   new WestminsterShoppingCenter(productList).setVisible(true);
                   break;

               default:
                   System.out.println("Invalid input try again !");
                   break;
           }
            System.out.println("\n");
        }
    }

    public void addproduct() throws ParseException {
        productList.add(new Electronics("4", "aaaaa", 100, 50,
                "djhhsjdh", new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-09")));
        productList.add(new Electronics("2", "aaaaa", 100, 50,
                "djhhsjdh", new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-09")));
        productList.add(new Clothing("1", "aaaaa", 100, 50,
                "S", "dssdsd"));
        productList.add(new Electronics("5", "aaaaa", 100, 50,
                "djhhsjdh", new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-09")));
        productList.add(new Clothing("3", "aaaaa", 100, 50,
                "M", "hcbjsdb"));


    }

    public static void main(String[] args) {
        WestminsterShoppingManager wsm = new WestminsterShoppingManager();

       try {
           wsm.startConsoleMenu();

       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }

}
