/* This program is a simple calculator with basic operations and log in system
 * Written by: Janelie S. Blanco    CS 2A */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

public class GUI {
    private static List accounts = new List();
    public static void main(String[] args){
        List.loadFromFile();
        final int width = 300;
        final int length = 300;
        JLabel username, password;
        JTextField user;
        JPasswordField pass;
        JButton login, createAccount;
        JCheckBox showPass;
        
        JFrame frame = new JFrame("Simple Cal");
        frame.setSize(width, length);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        username = new JLabel("Username: ", SwingConstants.CENTER);
        user = new JTextField(10);
        
        password = new JLabel("Password: ");
        pass = new JPasswordField(10);
    
        showPass = new JCheckBox("Show Password");
        showPass.addActionListener(new ShowPassword(pass));
             
 
        login = new JButton("Log In"); // here
        //login.setBounds(300, 320, 100, 40);
        login.addActionListener(new Login(user, pass, frame));

        createAccount = new JButton("Create Account");
        createAccount.addActionListener(new CreateAccountAction(user, pass, frame));
        

        frame.add(username);
        frame.add(user);
        frame.add(password);
        frame.add(pass);
        frame.add(showPass);
        frame.add(login);
        frame.add(createAccount);

        frame.setVisible(true);
    }
}
class ShowPassword implements ActionListener{
    private JPasswordField password;

    public ShowPassword(JPasswordField password){
        this.password = password;
    }

    public void actionPerformed(ActionEvent e) {
        JCheckBox checkBox = (JCheckBox) e.getSource();
        if (checkBox.isSelected()) {
            password.setEchoChar((char) 0); //to show its password
        } else {
            password.setEchoChar('*'); 
        }
    }
}
class Login implements ActionListener{
    private JTextField userF;
    private JPasswordField passwordF;
    private JFrame frame;

    public Login(JTextField userF, JPasswordField passwordF, JFrame frame) {
        this.userF = userF;
        this.passwordF = passwordF;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        String username = userF.getText();
        String password = new String(passwordF.getPassword());

        if (List.login(username, password)){
            JOptionPane.showMessageDialog(frame, "Log in Successful");
            frame.dispose();
            new Calculator();
        } else {
            JOptionPane.showMessageDialog(frame, "This account does not exist!");
        }
        userF.setText("");
        passwordF.setText("");
        
    }
}
class CreateAccountAction implements ActionListener{
    private JTextField userF;
    private JPasswordField passwordF;
    private JFrame frame;

    public CreateAccountAction(JTextField userF, JPasswordField passwordF, JFrame frame) {
        this.userF = userF;
        this.passwordF = passwordF;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {

        JFrame ccaFrame = new JFrame("Create Account");
        ccaFrame.setSize(300,300);
        ccaFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        ccaFrame.setLocationRelativeTo(null);
        ccaFrame.setResizable(true);
        ccaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel usernameLabel = new JLabel("Username: ");
        JTextField userField = new JTextField(10);
        
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(10);
    
        JButton saveButton = new JButton("Create Account");
        saveButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        saveButton.addActionListener(ev -> {
            String username = userField.getText();
            String password = new String(passwordField.getPassword());

            if (List.contains(username,password)){
                JOptionPane.showMessageDialog(ccaFrame, "This account already exists!");
                List.deleteContact(username, password);
            } else {
                List.add(username, password);
                JOptionPane.showMessageDialog(ccaFrame, "Account successfully created!");
                userField.setText("");
                passwordField.setText("");
                ccaFrame.dispose(); //this means closing the create account frame 
            }
            frame.setVisible(true); //then going back to log in frame
        });

        ccaFrame.add(usernameLabel);
        ccaFrame.add(userField);
        ccaFrame.add(passwordLabel);
        ccaFrame.add(passwordField);
        ccaFrame.add(saveButton); 

        ccaFrame.setVisible(true);

    }
}
