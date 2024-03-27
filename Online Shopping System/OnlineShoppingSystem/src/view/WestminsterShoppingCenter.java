package view;

import models.Product;
import models.Electronics;
import models.Clothing;
import models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


public class WestminsterShoppingCenter extends JFrame {

    JTable productTbl;
    JComboBox<String> productCategoryCmBx;
    JLabel productIdShowLbl;
    JLabel categoryShowLbl;
    JLabel nameShowLbl;
    JLabel sizeOrBrandShowLbl;
    JLabel colourOrWarrantyShowLbl;
    JLabel availableItemsShowLbl;
    JLabel sizeOrBrandLbl;
    JLabel colourOrWarrantyLbl;


    private static ArrayList<Product> productList;
    private static ArrayList<Product> productCartList = new ArrayList<>();
    private User user;
    private ShoppingCart cart;


    public WestminsterShoppingCenter(ArrayList<Product> productList) {
        // Set product list
        this.productList = productList;

        // Set Window
        setWindow(700,680,"Westminster Shopping Center");

        // Set Body
        try {
            GUIBody();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public WestminsterShoppingCenter(ArrayList<Product> productList, User user) {
        // Set product list
        WestminsterShoppingCenter.productList = productList;
        this.user = user;
        productCartList = user.getShoppingCart().getProductList();

        // Set Window
        setWindow(700,680,"Westminster Shopping Center");

        // Set Body
        try {
            GUIBody();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void setWindow(int width, int height, String name) {
        setSize(width,height);
        setTitle(name);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE or 2
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(2, 1));
    }

    private void GUIBody() throws ParseException {

        // Create table model with column names and 0 rows initially
        DefaultTableModel tableModel = new DefaultTableModel(
                new String[]{"ID", "Name", "Category", "Price", "Info"}, 0);

        // Create JTable with the populated table model
        productTbl = new JTable(tableModel) {
            final Class[] types = new Class[]{String.class, String.class, String.class, String.class, String.class};
            final boolean[] canEdit = new boolean[]{false, false, false, false, false};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        loadDataIntoTable(0);
        productTbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    selectTableRow(evt);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Create scroll pane for table
        JScrollPane productScrollPane = new JScrollPane();
        productScrollPane.setViewportView(productTbl);
        if (productTbl.getColumnModel().getColumnCount() > 0) {
            productTbl.getColumnModel().getColumn(0).setResizable(false);
            productTbl.getColumnModel().getColumn(0).setPreferredWidth(50);

            productTbl.getColumnModel().getColumn(1).setResizable(false);
            productTbl.getColumnModel().getColumn(1).setPreferredWidth(100);

            productTbl.getColumnModel().getColumn(2).setResizable(false);
            productTbl.getColumnModel().getColumn(2).setPreferredWidth(100);

            productTbl.getColumnModel().getColumn(3).setResizable(false);
            productTbl.getColumnModel().getColumn(3).setPreferredWidth(50);

            productTbl.getColumnModel().getColumn(4).setResizable(false);
            productTbl.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        JLabel productCategoryLbl = new JLabel("Select product category");

        productCategoryCmBx = new JComboBox<>(new DefaultComboBoxModel<>(
                new String[] { "All", "Electronics", "Clothing" }));
        productCategoryCmBx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    productCategoryComboBoxActionPerformed(evt);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        JButton shoppingCartBtn = new JButton("Shopping cart");
        shoppingCartBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                shoppingCartActionPerformed(evt);
            }
        });

        JLabel selectedProductDetailsTbl = new JLabel("Selected Product - Details");

        JLabel productIdLbl = new JLabel("Product ID: ");

        JLabel categoryLbl = new JLabel("Category:");

        JLabel nameLbl = new JLabel("Name:");

        sizeOrBrandLbl = new JLabel("");

        colourOrWarrantyLbl = new JLabel("");

        JLabel availableItemsLbl = new JLabel("Items Available:");

        JButton addShoppingCartBtn = new JButton("Add to Shopping Cart");
        addShoppingCartBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    addShoppingCartButtonActionPerformed(evt);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        productIdShowLbl = new JLabel("");

        categoryShowLbl = new JLabel("");

        nameShowLbl = new JLabel("");

        sizeOrBrandShowLbl = new JLabel("");

        colourOrWarrantyShowLbl = new JLabel("");

        availableItemsShowLbl = new JLabel("");

        JSeparator separator = new JSeparator();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(separator)).addGroup(layout.createSequentialGroup().addGap(29, 29, 29).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(54, 54, 54).addComponent(productCategoryLbl, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(productCategoryCmBx, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(shoppingCartBtn).addComponent(productScrollPane, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE))).addGap(0, 27, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addGap(31, 31, 31).addComponent(selectedProductDetailsTbl, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))).addContainerGap()).addGroup(layout.createSequentialGroup().addGap(44, 44, 44).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(availableItemsLbl, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE).addComponent(productIdLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(categoryLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(nameLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(sizeOrBrandLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(colourOrWarrantyLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(32, 32, 32).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(productIdShowLbl, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE).addComponent(categoryShowLbl, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE).addComponent(nameShowLbl, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addComponent(sizeOrBrandShowLbl, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE).addComponent(colourOrWarrantyShowLbl, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addComponent(availableItemsShowLbl, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(addShoppingCartBtn).addGap(39, 39, 39)))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(shoppingCartBtn).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(productCategoryCmBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(productCategoryLbl)).addGap(28, 28, 28).addComponent(productScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(32, 32, 32).addComponent(selectedProductDetailsTbl).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(productIdLbl).addComponent(productIdShowLbl)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(categoryLbl).addComponent(categoryShowLbl)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(nameLbl).addComponent(nameShowLbl)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(sizeOrBrandLbl).addComponent(sizeOrBrandShowLbl)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(colourOrWarrantyLbl).addComponent(colourOrWarrantyShowLbl)).addGap(15, 15, 15).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(availableItemsLbl).addComponent(availableItemsShowLbl).addComponent(addShoppingCartBtn)).addContainerGap(38, Short.MAX_VALUE)));
    }

    private void loadDataIntoTable(int category) throws ParseException {
        // Category
        // 0 = all
        // 1 = Electronics
        // 2 = Clothing

        // Get table model
        DefaultTableModel tableModel = (DefaultTableModel) productTbl.getModel();

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

            Object[] rowData = {
                    product.getProductId(),
                    product.getProductName(),
                    product.getClass().getName().substring(product.getClass().getName()
                            .lastIndexOf('.')+1).equalsIgnoreCase("electronics") ?
                            "Electronics" : "Clothing",
                    product.getPrice(),
                    productInfo
            };

            switch (category) {
                case 0:
                    tableModel.addRow(rowData);
                    break;

                case 1:
                    if(product.getClass().getName().substring(product.getClass().getName()
                            .lastIndexOf('.')+1).equalsIgnoreCase("electronics")) {
                        tableModel.addRow(rowData);
                    }
                    break;

                case 2:
                    if(product.getClass().getName().substring(product.getClass().getName()
                            .lastIndexOf('.')+1).equalsIgnoreCase("clothing")) {
                        tableModel.addRow(rowData);
                    }
                    break;
            }
        }
    }

    private void selectTableRow(MouseEvent e) throws ParseException {
        DefaultTableModel model = (DefaultTableModel) productTbl.getModel();
        int selectedRow = productTbl.getSelectedRow();

        productIdShowLbl.setText(model.getValueAt(selectedRow, 0).toString());
        categoryShowLbl.setText(model.getValueAt(selectedRow, 1).toString());
        nameShowLbl.setText(model.getValueAt(selectedRow, 2).toString());

        for (Product p : productList) {
            if(p.getProductId().equalsIgnoreCase(model.getValueAt(selectedRow, 0).toString().toLowerCase())) {
                if(model.getValueAt(selectedRow, 2).toString().equalsIgnoreCase("electronics")) {
                    Electronics ele = (Electronics) p;
                    sizeOrBrandLbl.setText("Brand:");
                    sizeOrBrandShowLbl.setText(ele.getBrand());
                    colourOrWarrantyLbl.setText("Warranty:");
                    colourOrWarrantyShowLbl.setText(new SimpleDateFormat("yyyy-MM-dd").
                            format(new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).
                                    parse(String.valueOf(ele.getWarranty()))));
                    availableItemsShowLbl.setText(String.valueOf(ele.getAvailableItems()));

                } else {
                    Clothing c = (Clothing) p;
                    sizeOrBrandLbl.setText("Size:");
                    sizeOrBrandShowLbl.setText(c.getSize());
                    colourOrWarrantyLbl.setText("Colour:");
                    colourOrWarrantyShowLbl.setText(c.getColour());
                    availableItemsShowLbl.setText(String.valueOf(c.getAvailableItems()));
                }
            }
        }
    }

    private void productCategoryComboBoxActionPerformed(ActionEvent e) throws ParseException {
        if(e.getSource() == productCategoryCmBx) {
            switch (Objects.requireNonNull(productCategoryCmBx.getSelectedItem()).toString().toLowerCase()) {
                case "all":
                    loadDataIntoTable(0);
                    break;

                case "electronics":
                    loadDataIntoTable(1);
                    break;

                case "clothing":
                    loadDataIntoTable(2);
                    break;
            }
        }
    }

    private void shoppingCartActionPerformed(ActionEvent e) {
        if(user != null) {
            cart = new ShoppingCart(productCartList, user.isFirstPurchase());
        } else  {
            cart = new ShoppingCart(productCartList);
        }
        cart.setVisible(true);
    }

    private void addShoppingCartButtonActionPerformed(ActionEvent e) throws ParseException {
        DefaultTableModel model = (DefaultTableModel) productTbl.getModel();
        int selectedRow = productTbl.getSelectedRow();
        boolean alreadyAdded = false;

        if(selectedRow > -1) {
            for (Product pp : productCartList) {
                if(model.getValueAt(selectedRow, 0).toString().equalsIgnoreCase(
                        pp.getProductId().toLowerCase())) {
                    alreadyAdded = true;
                    break;
                }
            }

            for (Product p : productList) {
                if(!alreadyAdded) {
                    if(model.getValueAt(selectedRow, 0).toString().equalsIgnoreCase(
                            p.getProductId().toLowerCase())) {
                        productCartList.add(p);

                        productIdShowLbl.setText("");
                        categoryShowLbl.setText("");
                        nameShowLbl.setText("");
                        sizeOrBrandShowLbl.setText("");
                        colourOrWarrantyShowLbl.setText("");
                        availableItemsShowLbl.setText("");

                        sizeOrBrandLbl.setText("");
                        colourOrWarrantyLbl.setText("");

                        if(cart != null) {
                            cart.refreshTable();
                        }
                        loadDataIntoTable(productCategoryCmBx.getSelectedIndex());
                    }

                } else {
                    showWarningDialog("Warning", "This item is already added!");
                    break;
                }
            }
        }
    }

    private static void showWarningDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
}