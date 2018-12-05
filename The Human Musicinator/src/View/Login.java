package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class Login extends JFrame {
    private JButton loginButton;
    private JTextField firstNameTextBox;
    private JTextField lastNameTextBox;
    private JTextField idTextBox;
    private JLabel titleLable;
    private JLabel firstNameLable;
    private JLabel lastNameLable;
    private JLabel idLable;
    private JPanel jPanel1;
    private Map users;

    public Login() {
        initComponents();

        this.setTitle("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!firstNameTextBox.getText().equals("") && !lastNameTextBox.getText().equals("") && !idTextBox.getText().equals("")) {
                    JOptionPane.showMessageDialog(Login.this, "Done!");
                    //add the user to DB
                    users.put(idTextBox.getText(), firstNameTextBox.getText()+" "+lastNameTextBox.getText());
                }
            }
        });
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.titleLable = new JLabel();
        this.firstNameLable = new JLabel();
        this.lastNameLable = new JLabel();
        this.idLable = new JLabel();
        this.loginButton = new JButton();
        this.firstNameTextBox = new JTextField();
        this.lastNameTextBox = new JTextField();
        this.idTextBox = new JTextField();
        this.users = new HashMap<String,String>();
        this.users = new HashMap<String,String>();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        loginButton.setText("login");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        titleLable.setPreferredSize(new Dimension(50, 75));

        titleLable.setText("Login");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addComponent(titleLable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(180, 180, 180))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(titleLable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(34, 34, 34))
        );

        firstNameLable.setText("First name : ");

        lastNameLable.setText("Last name : ");

        idLable.setText("ID Number : ");

        loginButton.setText("login");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(firstNameLable)
                                                        .addComponent(lastNameLable)
                                                        .addComponent(idLable))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(firstNameTextBox)
                                                        .addComponent(lastNameTextBox)
                                                        .addComponent(idTextBox)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
                                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(firstNameLable)
                                        .addComponent(firstNameTextBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lastNameLable)
                                        .addComponent(lastNameTextBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(idLable)
                                        .addComponent(idTextBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(loginButton)
                                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}
