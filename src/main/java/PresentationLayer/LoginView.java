package PresentationLayer;

import javax.swing.*;
import java.awt.*;

/**
 * The type Login view.
 */
public class LoginView {
    /**
     * The Frame.
     */
    public JFrame frame = new JFrame("Login window");
    /**
     * The P general.
     */
    public JPanel pGeneral = new JPanel();

    /**
     * The P title.
     */
    public JPanel pTitle = new JPanel();
    /**
     * The Title label.
     */
    public JLabel titleLabel = new JLabel("Login to our app");

    /**
     * The P login fields.
     */
    public JPanel pLoginFields = new JPanel();
    /**
     * The User label.
     */
    public JLabel userLabel = new JLabel("Username:");
    /**
     * The Username tf.
     */
    public JTextField usernameTF = new JTextField(20);
    /**
     * The Pass label.
     */
    public JLabel passLabel = new JLabel("Password:");
    /**
     * The Password field.
     */
    public JPasswordField passwordField = new JPasswordField(20);

    /**
     * The P warning.
     */
    public JPanel pWarning = new JPanel();
    /**
     * The Invalid user label.
     */
    public JLabel invalidUserLabel = new JLabel("Invalid username/password/type!");

    /**
     * The P types.
     */
    public JPanel pTypes = new JPanel();
    /**
     * The Types label.
     */
    public JLabel typesLabel = new JLabel("Type of user:");
    /**
     * The Admin radio.
     */
    public JRadioButton adminRadio = new JRadioButton("admin");
    /**
     * The Employee radio.
     */
    public JRadioButton employeeRadio = new JRadioButton("employee");
    /**
     * The Customer radio.
     */
    public JRadioButton customerRadio = new JRadioButton("customer");
    /**
     * The Button group.
     */
    public ButtonGroup buttonGroup = new ButtonGroup();

    /**
     * The P buttons.
     */
    public JPanel pButtons = new JPanel();
    /**
     * The Login bttn.
     */
    public JButton loginBttn = new JButton("Login");
    /**
     * The Register bttn.
     */
    public JButton registerBttn = new JButton("Register");

    /**
     * Instantiates a new Login view.
     */
    public LoginView(){
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(250,250));
        frame.setLocationRelativeTo(null);


        pTitle.add(titleLabel);

        pLoginFields.setLayout(new GridLayout(2,2));
        pLoginFields.add(userLabel);
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pLoginFields.add(usernameTF);
        pLoginFields.add(passLabel);
        passLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pLoginFields.add(passwordField);

        pWarning.add(getInvalidUserLabel());

        pTypes.setLayout(new GridLayout(1,0));
        pTypes.add(typesLabel);
        buttonGroup.add(adminRadio);
        buttonGroup.add(employeeRadio);
        buttonGroup.add(customerRadio);
        pTypes.add(adminRadio);
        pTypes.add(employeeRadio);
        pTypes.add(customerRadio);

        pButtons.setLayout(new GridLayout(1,0));
        pButtons.add(loginBttn);
        pButtons.add(registerBttn);


        pGeneral.setLayout(new BoxLayout(pGeneral, BoxLayout.Y_AXIS));
        pGeneral.add(pTitle);
        pGeneral.add(pLoginFields);
        pGeneral.add(pTypes);
        pGeneral.add(pButtons);

        frame.setContentPane(pGeneral);
        frame.setVisible(true);
        frame.pack();
    }


    /**
     * Gets username tf.
     *
     * @return the username tf
     */
    public JTextField getUsernameTF() {
        return usernameTF;
    }

    /**
     * Gets password field.
     *
     * @return the password field
     */
    public JPasswordField getPasswordField() {
        return passwordField;
    }

    /**
     * Gets admin radio.
     *
     * @return the admin radio
     */
    public JRadioButton getAdminRadio() {
        return adminRadio;
    }

    /**
     * Gets employee radio.
     *
     * @return the employee radio
     */
    public JRadioButton getEmployeeRadio() {
        return employeeRadio;
    }

    /**
     * Gets customer radio.
     *
     * @return the customer radio
     */
    public JRadioButton getCustomerRadio() {
        return customerRadio;
    }

    /**
     * Gets buttons.
     *
     * @return the buttons
     */
    public JPanel getpButtons() {
        return pButtons;
    }

    /**
     * Gets login bttn.
     *
     * @return the login bttn
     */
    public JButton getLoginBttn() {
        return loginBttn;
    }

    /**
     * Gets register bttn.
     *
     * @return the register bttn
     */
    public JButton getRegisterBttn() {
        return registerBttn;
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets invalid user label.
     *
     * @return the invalid user label
     */
    public JLabel getInvalidUserLabel() {
        return invalidUserLabel;
    }

    /**
     * Gets login fields.
     *
     * @return the login fields
     */
    public JPanel getpLoginFields() {
        return pLoginFields;
    }

    /**
     * Gets warning.
     *
     * @return the warning
     */
    public JPanel getpWarning() {
        return pWarning;
    }

    /**
     * Gets general.
     *
     * @return the general
     */
    public JPanel getpGeneral() {
        return pGeneral;
    }
}
