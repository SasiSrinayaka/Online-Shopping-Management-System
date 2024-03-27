package view;

import models.cart.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;


public class UpdateQuantity extends JFrame  {
    private javax.swing.JLabel availableQuantityLbl;
    private javax.swing.JLabel quantityLbl;
    private javax.swing.JTextField quantityTxt;
    private javax.swing.JButton updateBtn;
    private Product product;
    private ShoppingCart shoppingCart;


    public UpdateQuantity(Product product, ShoppingCart shoppingCart) {
        this.product = product;
        this.shoppingCart = shoppingCart;

        // Set Window
        setWindow(300,200,"Update Quantity");

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
        availableQuantityLbl = new JLabel(String.valueOf(product.getAvailableItems()));
        quantityLbl = new JLabel();
        quantityTxt = new JTextField();
        updateBtn = new JButton();

        availableQuantityLbl.setText("Available Quantity");

        quantityLbl.setText("Quantity");

        updateBtn.setText("Update");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(24, 24, 24).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(quantityLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(94, 94, 94).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(updateBtn).addComponent(quantityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))).addComponent(availableQuantityLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(45, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(31, 31, 31).addComponent(availableQuantityLbl).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(quantityLbl).addComponent(quantityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(updateBtn).addContainerGap(22, Short.MAX_VALUE)));
    }

    private void updateBtnActionPerformed(ActionEvent evt) {
        if(quantityTxt.getText() != null && !quantityTxt.getText().isEmpty()) {
            try {
                int quantity = Integer.parseInt(quantityTxt.getText().trim());


                if(0 < quantity && quantity <= product.getAvailableItems()) {
                    product.setNeedItems(quantity);
                    shoppingCart.updateProductQuantity(product);
                    shoppingCart.refreshTable();
                    setVisible(false);

                } else {
                    showWarningDialog("Warning", "Invalid quantity!");
                }

            } catch (Exception e) {
                showWarningDialog("Warning", "Invalid data type use integer type!");
            }

        } else {
            showWarningDialog("Warning", "Set new quantity!");
        }
    }

    private static void showWarningDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
}
