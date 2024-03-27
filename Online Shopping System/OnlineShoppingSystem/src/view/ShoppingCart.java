package view;

import models.Clothing;
import models.Electronics;
import models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ShoppingCart extends JFrame {
    JTable cartTbl;
    JLabel totalValueLbl;
    JLabel firstPurchaseDiscountValueLbl;
    JLabel threeItemDiscountValueLbl;
    JLabel finalTotalValueLbl;
    private static ArrayList<Product> productList = new ArrayList<>();
    private static ArrayList<models.cart.Product> productCartList;
    private boolean usersFirstTime = true;
    private UpdateQuantity uq;


    public ShoppingCart(ArrayList<Product> productList) {
        ShoppingCart.productList = productList;

        // Set Window
        setWindow(700,480,"Shopping Cart");

        // Set Body
        try {
            GUIBody(productList);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        setTotalPrice();
    }

    public ShoppingCart(ArrayList<Product> productList, boolean usersFirstTime) {
        ShoppingCart.productList = productList;
        this.usersFirstTime = usersFirstTime;

        // Set Window
        setWindow(700,480,"Shopping Cart");

        // Set Body
        try {
            GUIBody(productList);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        setTotalPrice();
    }

    public ShoppingCart() {}

    private void setWindow(int width, int height, String name) {
        setSize(width,height);
        setTitle(name);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE or 2
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(2, 1));
    }

    private void GUIBody(ArrayList<Product> productList) throws ParseException {

        // Create table model with column names and 0 rows initially
        DefaultTableModel tableModel = new DefaultTableModel(
                new String[]{"Product", "Quantity", "Price"}, 0);


        JScrollPane jScrollPane1 = new JScrollPane();
        JLabel totalLbl = new JLabel();
        JLabel firstPurchaseDiscountLbl = new JLabel();
        JLabel threeItemDiscountLbl = new JLabel();
        JLabel finalTotalLbl = new JLabel();
        totalValueLbl = new JLabel();
        firstPurchaseDiscountValueLbl = new JLabel();
        threeItemDiscountValueLbl = new JLabel();
        finalTotalValueLbl = new JLabel();

        // Create JTable with the populated table model
        cartTbl = new JTable(tableModel) {
            Class[] types = new Class[] { String.class, Integer.class, String.class };
            boolean[] canEdit = new boolean [] {false, false, false};
            public Class getColumnClass(int columnIndex) {return types [columnIndex];}
            public boolean isCellEditable(int rowIndex, int columnIndex) {return canEdit [columnIndex];}
        };
        cartTbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                updateQuantityInTable(evt);
            }
        });
        loadDataIntoTable();
        jScrollPane1.setViewportView(cartTbl);

        totalLbl.setText("Total");

        firstPurchaseDiscountLbl.setText("First Purchase Discount (10%)");

        threeItemDiscountLbl.setText("Three Items in Same Category Discount (20%)");

        finalTotalLbl.setText("Final Total");

        totalValueLbl.setText("");

        firstPurchaseDiscountValueLbl.setText("");

        threeItemDiscountValueLbl.setText("");

        finalTotalValueLbl.setText("");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(29, 29, 29).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(31, Short.MAX_VALUE)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(finalTotalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(threeItemDiscountLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(totalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(firstPurchaseDiscountLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))).addGap(27, 27, 27).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(totalValueLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(firstPurchaseDiscountValueLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(threeItemDiscountValueLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(finalTotalValueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(57, 57, 57)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(24, 24, 24).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(totalLbl).addComponent(totalValueLbl)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(firstPurchaseDiscountLbl).addComponent(firstPurchaseDiscountValueLbl)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(threeItemDiscountLbl).addComponent(threeItemDiscountValueLbl)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(finalTotalLbl).addComponent(finalTotalValueLbl)).addContainerGap(27, Short.MAX_VALUE)));
    }

    private void loadDataIntoTable() throws ParseException {
        convertProductIntoProductCart();

        // Get table model
        DefaultTableModel tableModel = (DefaultTableModel) cartTbl.getModel();

        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        // Populate table model with product data
        for (Product product : productList) {

            String productInfo;

            if (product.getClass().getName().substring(product.getClass().getName().
                    lastIndexOf('.')+1).equalsIgnoreCase("electronics")) {
                Electronics e = (Electronics) product;
                productInfo = e.getBrand() + " " + new SimpleDateFormat("yyyy-MM-dd").
                        format(new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",
                                Locale.ENGLISH).parse(String.valueOf(e.getWarranty())));
            } else {
                Clothing c = (Clothing) product;
                productInfo = c.getSize() + " " + c.getColour();
            }

            for (models.cart.Product p : productCartList) {
                if(p.getProductId().equalsIgnoreCase(product.getProductId().toLowerCase())) {
                    Object[] rowData = {
                            product.getProductId()  + ", " + product.getProductName() + ", " + productInfo,
                            p.getNeedItems(),
                            product.getPrice()
                    };

                    tableModel.addRow(rowData);
                    break;
                }
            }
        }
    }

    private void convertProductIntoProductCart() {
        ArrayList<models.cart.Product> tempCartList = new ArrayList<>();

        if(productCartList != null) {
            for (Product p : productList) {
                tempCartList.add(new models.cart.Product(p.getProductId(), p.getProductName(),
                        p.getAvailableItems(), p.getPrice(), 1));
            }

            for (models.cart.Product p : productCartList) {
                for (models.cart.Product pp : tempCartList) {
                    if(p.getProductId().equalsIgnoreCase(pp.getProductId().toLowerCase())) {
                        pp.setNeedItems(p.getNeedItems());
                    }
                }
            }

        } else {
            productCartList = new ArrayList<>();

            for (Product p : productList) {
                tempCartList.add(new models.cart.Product(p.getProductId(), p.getProductName(),
                        p.getAvailableItems(), p.getPrice(), 1));
            }
        }

        productCartList = tempCartList;
    }

    public void refreshTable() throws ParseException {
        loadDataIntoTable();
        setTotalPrice();
    }

    private void setTotalPrice() {
        double total = 0;
        double firstPurchaseDiscount = 0;
        double threeItemInSameCategoryDiscount = 0;

        int electricItemCount = 0;
        int clothingitemCount = 0;

        for (models.cart.Product p : productCartList) {
            total += (p.getPrice() * p.getNeedItems());

            if(p.getClass().getName().substring(p.getClass().getName().lastIndexOf('.')+1).
                    equalsIgnoreCase("electronics")) {
                electricItemCount += 1;

            } else {
                clothingitemCount += 1;
            }
        }

        if(usersFirstTime) {
            firstPurchaseDiscount = total * 0.1;
        } else {
            firstPurchaseDiscountValueLbl.setText("- 0.00 $");
        }

        if(electricItemCount >= 3 || clothingitemCount >= 3) {
            threeItemInSameCategoryDiscount = total * 0.2;

        } else {
            threeItemDiscountValueLbl.setText("- 0.00 $");
        }

        totalValueLbl.setText(String.format("%.2f $", total));
        firstPurchaseDiscountValueLbl.setText(String.format("- %.2f $", firstPurchaseDiscount));
        threeItemDiscountValueLbl.setText(String.format("- %.2f $", threeItemInSameCategoryDiscount));
        finalTotalValueLbl.setText(String.format("%.2f $", (total -
                (firstPurchaseDiscount + threeItemInSameCategoryDiscount))));
    }

    private void updateQuantityInTable(MouseEvent evt) {
        DefaultTableModel model = (DefaultTableModel) cartTbl.getModel();
        int selectedRow = cartTbl.getSelectedRow();

        for (models.cart.Product p : productCartList) {
            if (p.getProductId().equalsIgnoreCase(model.getValueAt(selectedRow, 0).
                    toString().split(",")[0])) {
                uq = new UpdateQuantity(p, this);
                uq.setVisible(true);
            }
        }
    }

    public void updateProductQuantity(models.cart.Product product) throws ParseException {
        for (models.cart.Product p : productCartList) {
            if(p.getProductId().equalsIgnoreCase(product.getProductId().toLowerCase())) {
                p.setNeedItems(product.getNeedItems());
            }
        }

        loadDataIntoTable();
    }

}
