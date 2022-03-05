package PresentationLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * The type Customer controller.
 */
public class CustomerController extends Observable {
    private DeliveryService ds;
    private CustomerView view;
    private ArrayList<Integer> cartIds = new ArrayList<>();
    private String username = "";

    /**
     * Instantiates a new Customer controller.
     *
     * @param v  the v
     * @param ds the ds
     */
    public CustomerController(CustomerView v, DeliveryService ds){
        view = v;
        this.ds = ds;

        view.getSearchBttn().addActionListener(new SearchActionListener());
        view.getResetFilterBttn().addActionListener(new ResetFiltersActionListener());

        view.getAddToCartBttn().addActionListener(new AddToCartActionListener());
        view.getPlaceOrderBttn().addActionListener(new AddOrderActionListener());
    }

    /**
     * Set username.
     *
     * @param username the username
     */
    public void setUsername(String username){
        this.username = username;
    }

    private class AddOrderActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<MenuItem> orderedItems = new ArrayList<>();
            LocalDateTime date = LocalDateTime.now();
            float price = 0;
            for(int i : cartIds){
                price += ds.findItemByID(i).getPrice();
                orderedItems.add(ds.findItemByID(i));
            }

            Order order = new Order(username, date, price);
            setChanged();
            notifyObservers("Order number " + order.getId() + " placed by user " + order.getClientUsername() +
                            " time " + date + " price " + price);
            try {
                ds.addOrder(order, orderedItems);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            cartIds.clear();
            view.getCartContents().setText("");
            view.getIdToAddCartTF().setText("");
            view.getFrame().pack();
        }
    }

    private class AddToCartActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!view.getIdToAddCartTF().getText().equals("")){
                int id = Integer.parseInt(view.getIdToAddCartTF().getText());
                if(ds.findItemByID(id) != null){
                    cartIds.add(id);
                    String aux = "";
                    if(cartIds.size() > 1){
                        aux = ", ";
                    }
                    aux += String.valueOf(id);
                    view.getCartContents().setText(view.getCartContents().getText() + aux);
                }
                view.getIdToAddCartTF().setText("");
            }
            view.getFrame().pack();
        }
    }

    private class ResetFiltersActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTabel(ds.getMenuItems());
            view.getTitleTF().setText("");
            view.getRatingTF().setText("");
            view.getCalsTF().setText("");
            view.getProteinTF().setText("");
            view.getFatTF().setText("");
            view.getSodiumTF().setText("");
            view.getPriceTF().setText("");
        }
    }

    private class SearchActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = view.getTitleTF().getText();
            String aux;
            aux = view.getRatingTF().getText();
            float rating;
            if (aux.equals("")){
                rating = Float.MIN_VALUE;
            }else{
                rating = Float.parseFloat(aux);
            }
            float cals;
            aux = view.getCalsTF().getText();
            if (aux.equals("")){
                cals = Float.MAX_VALUE;
            }else{
                cals = Float.parseFloat(aux);
            }
            float proteins;
            aux = view.getProteinTF().getText();
            if (aux.equals("")){
                proteins = Float.MAX_VALUE;
            }else{
                proteins = Float.parseFloat(aux);
            }
            float fats;
            aux = view.getFatTF().getText();
            if (aux.equals("")){
                fats = Float.MAX_VALUE;
            }else{
                fats = Float.parseFloat(aux);
            }
            float sodium;
            aux = view.getSodiumTF().getText();
            if (aux.equals("")){
                sodium = Float.MAX_VALUE;
            }else{
                sodium = Float.parseFloat(aux);
            }
            float price;
            aux = view.getPriceTF().getText();
            if (aux.equals("")){
                price = Float.MAX_VALUE;
            }else{
                price = Float.parseFloat(aux);
            }
            updateTabel(ds.findMenuItems(title, rating, cals, proteins, fats, sodium, price));
        }
    }

    /**
     * Set visible.
     */
    public void setVisible(){
        view.getFrame().setVisible(true);
    }

    /**
     * Update tabel.
     *
     * @param list the list
     */
    protected void updateTabel(List<MenuItem> list){
        JScrollPane scrollPane;
        view.getpTable().remove(view.getScrollPane());
        view.setTable(view.populateTable(list));
        scrollPane = new JScrollPane(view.getTable());
        scrollPane.setPreferredSize(new Dimension(500, 300));
        view.setScrollPane(scrollPane);
        view.getpTable().add(view.getScrollPane());

        view.getFrame().pack();
    }
}
