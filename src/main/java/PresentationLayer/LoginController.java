package PresentationLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.User;
import BusinessLayer.userType;
import DataLayer.Serializator;

import javax.swing.*;
import java.awt.event.*;

/**
 * The type Login controller.
 */
public class LoginController {
    private LoginView view;
    private DeliveryService ds;
    private AdminController adminController;
    private CustomerController customerController;
    private EmployeeController employeeController;
    /**
     * The S.
     */
    Serializator s = new Serializator();

    /**
     * Instantiates a new Login controller.
     *
     * @param v                  the v
     * @param ds                 the ds
     * @param adminController    the admin controller
     * @param customerController the customer controller
     * @param employeeController the employee controller
     */
    public LoginController(LoginView v, DeliveryService ds,
                           AdminController adminController, CustomerController customerController,
                           EmployeeController employeeController){
        view = v;
        this.ds = ds;
        this.adminController = adminController;
        this.customerController = customerController;
        this.employeeController = employeeController;

        v.getAdminRadio().addActionListener(new AdminRadioActionListener());
        v.getEmployeeRadio().addActionListener(new EmployeeRadioActionListener());
        v.getCustomerRadio().addActionListener(new CustomerRadioActionListener());

        v.getLoginBttn().addActionListener(new LoginActionListener());
        v.getRegisterBttn().addActionListener(new RegisterActionListener());

        v.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                s.serialize(getDs(), "serialize.txt");
            }
        });
    }

    /**
     * Gets ds.
     *
     * @return the ds
     */
    public DeliveryService getDs() {
        return ds;
    }

    /**
     * Sets ds.
     *
     * @param ds the ds
     */
    public void setDs(DeliveryService ds) {
        this.ds = ds;
    }

    /**
     * Sets s.
     *
     * @param s the s
     */
    public void setS(Serializator s) {
        this.s = s;
    }

    private class RegisterActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = view.getUsernameTF().getText();
            String pass = new String(view.getPasswordField().getPassword());
            userType type = null;
            if(view.getAdminRadio().isSelected()){
                type = userType.ADMIN;
            }
            if(view.getEmployeeRadio().isSelected()){
                type = userType.EMPLOYEE;
            }
            if(view.getCustomerRadio().isSelected()){
                type = userType.CLIENT;
            }
            if (ds.addUser(new User(user, pass, type))){
                view.getInvalidUserLabel().setText("Registration successful!");
            }
            else{
                view.getInvalidUserLabel().setText("Registration failed! Check username");
            }
            view.getpGeneral().add(view.getpWarning(), 2);
            JFrame frame = view.getFrame();
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
    }

    private class LoginActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = view.getUsernameTF().getText();
            String pass = new String(view.getPasswordField().getPassword());
            userType type = null;
            if(view.getAdminRadio().isSelected()){
                type = userType.ADMIN;
            }
            if(view.getEmployeeRadio().isSelected()){
                type = userType.EMPLOYEE;
            }
            if(view.getCustomerRadio().isSelected()){
                type = userType.CLIENT;
            }
            
            if (!ds.verifyUser(user, pass, type)){
                view.getInvalidUserLabel().setText("Invalid username/password/type!");
            }
            else{
                view.getInvalidUserLabel().setText("Login successful!");
                if(type.equals(userType.ADMIN)){
                    adminController.setVisible();
                    adminController.updateMenuItemTable(ds.getMenuItems());
                }
                if(type.equals(userType.CLIENT)){
                    customerController.setVisible();
                    customerController.setUsername(user);
                    customerController.updateTabel(ds.getMenuItems());
                }
                if(type.equals(userType.EMPLOYEE)){
                    if(customerController != null){
                        customerController.addObserver(employeeController);
                    }
                    employeeController.setVisible();
                }
            }
            view.getpGeneral().add(view.getpWarning(), 2);

            JFrame frame = view.getFrame();
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
    }

    private class AdminRadioActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getAdminRadio().isSelected()){
                view.getpButtons().remove(view.getRegisterBttn());
            }
            JFrame frame = view.getFrame();
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
    }

    private class EmployeeRadioActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getEmployeeRadio().isSelected()){
                view.getpButtons().remove(view.getRegisterBttn());
            }
            JFrame frame = view.getFrame();
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
    }

    private class CustomerRadioActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getCustomerRadio().isSelected()){
                view.getpButtons().add(view.getRegisterBttn());
            }
            JFrame frame = view.getFrame();
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
    }
}
