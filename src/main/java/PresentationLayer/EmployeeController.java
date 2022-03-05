package PresentationLayer;

import java.util.Observable;
import java.util.Observer;

/**
 * The type Employee controller.
 */
public class EmployeeController implements Observer {
    private EmployeeView view;

    /**
     * Instantiates a new Employee controller.
     *
     * @param view the view
     */
    public EmployeeController(EmployeeView view){
        this.view = view;
    }

    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        view.getOrderLabel().setText(message);
        view.getFrame().pack();
        view.getpGeneral().updateUI();
    }

    /**
     * Set visible.
     */
    public void setVisible(){
        view.getFrame().setVisible(true);
    }
}
