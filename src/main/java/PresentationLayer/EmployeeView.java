package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Observable;

/**
 * The type Employee view.
 */
public class EmployeeView extends Observable {
    private JFrame frame = new JFrame("Employee frame");
    private JPanel pGeneral = new JPanel();
    private JLabel titleLabel = new JLabel("Welcome employee!");
    private JLabel notifLabel = new JLabel("Notificari:");
    private JLabel orderLabel = new JLabel("");

    /**
     * Instantiates a new Employee view.
     */
    public EmployeeView(){
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(250,250));

        pGeneral.setLayout(new BoxLayout(pGeneral, BoxLayout.Y_AXIS));
        pGeneral.add(titleLabel);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        pGeneral.add(notifLabel);
        notifLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pGeneral.add(orderLabel);
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.setContentPane(pGeneral);
        frame.setVisible(false);
        frame.pack();
    }

    /**
     * Gets general.
     *
     * @return the general
     */
    public JPanel getpGeneral() {
        return pGeneral;
    }

    /**
     * Gets order label.
     *
     * @return the order label
     */
    public JLabel getOrderLabel() {
        return orderLabel;
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }
}
