package Start;

import BusinessLayer.*;
import DataLayer.Serializator;
import PresentationLayer.*;

import java.io.FileNotFoundException;

/**
 * The type Start.
 */
public class Start {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args){
        Serializator s = new Serializator();
        DeliveryService ds = s.deserialize("serialize.txt");
        if(ds == null){
            ds = new DeliveryService();
        }
        ds.addUser(new User("admin", "admin", userType.ADMIN));
        ds.addUser(new User("employee", "employee", userType.EMPLOYEE));
        Order o = new Order();
        o.setIdCount(ds.getMaxOrderId()+1);

        AdminView adminView = new AdminView();
        AdminController adminController = new AdminController(adminView, ds);

        CustomerView customerView = new CustomerView();
        CustomerController customerController = new CustomerController(customerView, ds);

        EmployeeView employeeView = new EmployeeView();
        EmployeeController employeeController = new EmployeeController(employeeView);

        LoginView view = new LoginView();
        LoginController loginController = new LoginController(view, ds, adminController, customerController, employeeController);


    }

}
