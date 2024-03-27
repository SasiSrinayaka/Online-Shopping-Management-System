import models.ShoppingCart;
import models.User;
import view.WestminsterShoppingCenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class LoginOrSingInPage extends JFrame {

    private JPasswordField pWordTxt;
    private JPasswordField newPwordTxt;
    private JPasswordField conformPwordTxt;
    private JTextField newUnameTxt;
    private JTextField uNameTxt;
    private final WestminsterShoppingManager wsm = new WestminsterShoppingManager();


    public LoginOrSingInPage() {
        // Set Window
        setWindow(760,440,"Westminster Shopping Center");

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

        JSeparator jSeparator2 = new JSeparator();
        JLabel uNameLbl = new JLabel();
        JLabel pwordLbl = new JLabel();
        JLabel newUnameLbl = new JLabel();
        JLabel newPwordLbl = new JLabel();
        JLabel conformPwordLbt = new JLabel();
        uNameTxt = new JTextField();
        newUnameTxt = new JTextField();
        JLabel loginLbl = new JLabel();
        JLabel singLbl = new JLabel();
        JButton loginBtn = new JButton();
        JButton singInBtn = new JButton();
        pWordTxt = new JPasswordField();
        newPwordTxt = new JPasswordField();
        conformPwordTxt = new JPasswordField();

        jSeparator2.setOrientation(SwingConstants.VERTICAL);

        uNameLbl.setText("User name:");

        pwordLbl.setText("Password:");

        newUnameLbl.setText("User name:");

        newPwordLbl.setText("Password:");

        conformPwordLbt.setText("Conform Password");

        uNameTxt.setText("");

        newUnameTxt.setText("");

        loginLbl.setText("Login");

        singLbl.setText("Sign in");

        loginBtn.setText("Login");
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        singInBtn.setText("Sing in");
        singInBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                singInBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(61, 61, 61).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(pwordLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(uNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(uNameTxt).addComponent(pWordTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))).addGroup(layout.createSequentialGroup().addGap(135, 135, 135).addComponent(loginLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(loginBtn))).addGap(18, 18, 18).addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(123, 123, 123).addComponent(singLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(singInBtn).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(conformPwordLbt, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE).addComponent(newPwordLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(newUnameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(newUnameTxt).addComponent(newPwordTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE).addComponent(conformPwordTxt)))))).addContainerGap(25, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap(40, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addComponent(loginLbl).addGap(65, 65, 65).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(uNameLbl).addComponent(uNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pwordLbl).addComponent(pWordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(63, 63, 63).addComponent(loginBtn)).addGroup(layout.createSequentialGroup().addComponent(singLbl).addGap(61, 61, 61).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(newUnameLbl).addComponent(newUnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(newPwordLbl).addComponent(newPwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(conformPwordLbt).addComponent(conformPwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(28, 28, 28).addComponent(singInBtn)).addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(50, 50, 50)));
    }

    private void loginBtnActionPerformed(ActionEvent evt) {
        String name = uNameTxt.getText();
        String pass = pWordTxt.getText();
        boolean invalideLogin = false;


        if(name != null && !name.isEmpty() && pass != null && !pass.isEmpty()) {
            if(wsm.getUserList() != null && !wsm.getUserList().isEmpty()) {
                for (User u : wsm.getUserList()) {
                    if(u.getUserName().equalsIgnoreCase(name.toLowerCase()) &&
                            u.getPassword().equalsIgnoreCase(pass.toLowerCase())) {
                        new WestminsterShoppingCenter(wsm.getProductList(), u).setVisible(true);
                        invalideLogin = false;
                        setVisible(false);
                        break;

                    } else {
                        invalideLogin = true;
                    }
                }

            } else {
                invalideLogin = true;
            }

            if(invalideLogin) {
                showWarningDialog("Warning", "Invalid login details!");
            }

        } else {
            showWarningDialog("Warning", "Insert input correctly!");
        }
    }

    private void singInBtnActionPerformed(ActionEvent evt) {
        String name = newUnameTxt.getText();
        String pass = newPwordTxt.getText();
        String conPass = conformPwordTxt.getText();

        if(name != null && !name.isEmpty() && pass != null
                && !pass.isEmpty() && conPass != null && !conPass.isEmpty()) {

            if(wsm.getUserList() != null && !wsm.getUserList().isEmpty()) {
                for (User u : wsm.getUserList()) {
                    if(!u.getUserName().equalsIgnoreCase(name.toLowerCase())) {
                        if(pass.equalsIgnoreCase(conPass.toLowerCase())) {
                            wsm.addNewUser(new User(name, pass, new ShoppingCart(), true));
                            newUnameTxt.setText("");
                            newPwordTxt.setText("");
                            conformPwordTxt.setText("");
                            showMessageDialog("Information", "Successfully sing in you... " +
                                    "\nNow try to login");

                        } else {
                            showWarningDialog("Warning", "This password and conform password are not equal!");
                        }

                    } else {
                        showWarningDialog("Warning", "This user name is already used!");
                    }
                }

            } else {
                if(pass.equalsIgnoreCase(conPass.toLowerCase())) {
                    wsm.addNewUser(new User(name, pass, new ShoppingCart(), true));
                    newUnameTxt.setText("");
                    newPwordTxt.setText("");
                    conformPwordTxt.setText("");
                    showMessageDialog("Information", "Successfully sing in you... " +
                            "\nNow try to login");

                } else {
                    showWarningDialog("Warning", "This password and conform password are not equal!");
                }
            }

        } else {
            showWarningDialog("Warning", "Insert input correctly!");
        }
    }

    private static void showWarningDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    private static void showMessageDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }


}
