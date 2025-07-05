import javax.swing.*;
import java.awt.*;

public class LoginSignupApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private DatabaseManager db;

    public LoginSignupApp() {
        db = new DatabaseManager();

        setTitle("Login & Signup");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(), "login");
        mainPanel.add(createSignupPanel(), "signup");

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("Login", JLabel.CENTER);
        title.setBounds(120, 10, 150, 30);
        panel.add(title);

        JTextField userField = new JTextField();
        userField.setBounds(130, 60, 150, 25);
        panel.add(new JLabel("Username:")).setBounds(50, 60, 80, 25);
        panel.add(userField);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(130, 100, 150, 25);
        panel.add(new JLabel("Password:")).setBounds(50, 100, 80, 25);
        panel.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(130, 140, 100, 25);
        panel.add(loginBtn);

        JButton toSignup = new JButton("Go to Signup");
        toSignup.setBounds(130, 180, 120, 25);
        panel.add(toSignup);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (db.validateUser(user, pass)) {
                JOptionPane.showMessageDialog(this, "Login Success 🎉");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials ❌");
            }
        });

        toSignup.addActionListener(e -> cardLayout.show(mainPanel, "signup"));

        return panel;
    }

    private JPanel createSignupPanel() {
        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("Signup", JLabel.CENTER);
        title.setBounds(120, 10, 150, 30);
        panel.add(title);

        JTextField userField = new JTextField();
        userField.setBounds(130, 60, 150, 25);
        panel.add(new JLabel("Username:")).setBounds(50, 60, 80, 25);
        panel.add(userField);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(130, 100, 150, 25);
        panel.add(new JLabel("Password:")).setBounds(50, 100, 80, 25);
        panel.add(passField);

        JButton signupBtn = new JButton("Signup");
        signupBtn.setBounds(130, 140, 100, 25);
        panel.add(signupBtn);

        JButton toLogin = new JButton("Go to Login");
        toLogin.setBounds(130, 180, 120, 25);
        panel.add(toLogin);

        signupBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields!");
            } else if (db.addUser(user, pass)) {
                JOptionPane.showMessageDialog(this, "Signup successful ✅");
                cardLayout.show(mainPanel, "login");
            } else {
                JOptionPane.showMessageDialog(this, "Username exists ⚠️");
            }
        });

        toLogin.addActionListener(e -> cardLayout.show(mainPanel, "login"));

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginSignupApp::new);
    }
}
