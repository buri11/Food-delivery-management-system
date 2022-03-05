package PresentationLayer;

import BusinessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.BitSet;
import java.util.List;

/**
 * The type Customer view.
 */
public class CustomerView {
    private JFrame frame = new JFrame("Customer operations");
    private JPanel pGeneral = new JPanel();

    //pGeneral will contain pTitle and pContents
    private JPanel pTitle = new JPanel();
    private JPanel pContents = new JPanel();

    private JPanel pSearch = new JPanel();
    private JPanel pPlaceOrder = new JPanel();
    private JPanel pTable = new JPanel();
    //pContents will contain the search, place order and table panels, each one with its components

    //title panel
    private JLabel titleLabel = new JLabel("Welcome customer!");

    //search panel contains 3 panels: one for the title, one for the criteria of search and one for the buttons
    //search title panel
    private JPanel pSearchTitle = new JPanel();
    private JLabel searchTitleLabel = new JLabel("Search criteria");

    //search criteria panel
    private JPanel pCriteria = new JPanel();
    private JLabel searchCriteriaTitleLabel = new JLabel("Title:");
    private JTextField titleTF = new JTextField(15);
    private JLabel ratingLabel = new JLabel("Min rating:");
    private JTextField ratingTF = new JTextField(10);
    private JLabel calsLabel = new JLabel("Max calories:");
    private JTextField calsTF = new JTextField(10);
    private JLabel proteinLabel = new JLabel("Max proteins:");
    private JTextField proteinTF = new JTextField(10);
    private JLabel fatLabel = new JLabel("Max fats:");
    private JTextField fatTF = new JTextField(10);
    private JLabel sodiumLabel = new JLabel("Max sodium:");
    private JTextField sodiumTF = new JTextField(10);
    private JLabel priceLabel = new JLabel("Max price:");
    private JTextField priceTF = new JTextField(10);

    //search buttons panel
    private JPanel pSearchBttns = new JPanel();
    private JButton resetFilterBttn = new JButton("Reset filters");
    private JButton searchBttn = new JButton("Search");

    //place order panel contains 3 panels, one for adding the products to the cart, another one for the butons
    //and one more for the title
    private JPanel pPlaceOrderTitle = new JPanel();
    private JLabel orderTitleLabel = new JLabel("Place an order");
    private JPanel pCart = new JPanel();
    private JLabel prodIdLabel = new JLabel("Product id to add to cart:");
    private JTextField idToAddCartTF = new JTextField(10);
    private JLabel cartLabel = new JLabel("Shopping cart: ");
    private JLabel cartContents = new JLabel("");
    private JPanel pOrderBttns = new JPanel();
    private JButton addToCartBttn = new JButton("Add to cart");
    private JButton placeOrderBttn = new JButton("Place order");

    //table panel
    private String []columns = {"id", "title", "rating", "calories", "protein", "fat", "sodium", "price"};
    private JTable table;
    private JScrollPane scrollPane;

    /**
     * Instantiates a new Customer view.
     */
    public CustomerView(){
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(400,350));

        pGeneral.setLayout(new BoxLayout(pGeneral, BoxLayout.Y_AXIS));
        pGeneral.add(pTitle);
        pGeneral.add(pContents);

        pTitle.add(titleLabel);

        pContents.setLayout(new BoxLayout(pContents, BoxLayout.X_AXIS));
        pContents.add(pSearch);
        pContents.add(pPlaceOrder);
        pContents.add(pTable);

        //search panel
        pSearch.setLayout(new BoxLayout(pSearch, BoxLayout.Y_AXIS));
        pSearch.add(pSearchTitle);
        pSearch.add(pCriteria);
        pSearch.add(pSearchBttns);

        //pSearchTitle panel
        pSearchTitle.add(searchTitleLabel);
        searchTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //search criteria panel
        pCriteria.setLayout(new GridLayout(7,2));
        pCriteria.add(searchCriteriaTitleLabel);
        searchCriteriaTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pCriteria.add(titleTF);
        pCriteria.add(ratingLabel);
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pCriteria.add(ratingTF);
        pCriteria.add(calsLabel);
        calsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pCriteria.add(calsTF);
        pCriteria.add(proteinLabel);
        proteinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pCriteria.add(proteinTF);
        pCriteria.add(fatLabel);
        fatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pCriteria.add(fatTF);
        pCriteria.add(sodiumLabel);
        sodiumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pCriteria.add(sodiumTF);
        pCriteria.add(priceLabel);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pCriteria.add(priceTF);

        //search buttons panel
        pSearchBttns.setLayout(new GridLayout(1,2));
        pSearchBttns.add(resetFilterBttn);
        pSearchBttns.add(searchBttn);
        pSearchBttns.setLayout(new BoxLayout(pSearchBttns, BoxLayout.X_AXIS));

        //place order panel
        pPlaceOrder.setLayout(new BoxLayout(pPlaceOrder, BoxLayout.Y_AXIS));
        pPlaceOrder.add(pPlaceOrderTitle);
        pPlaceOrderTitle.add(orderTitleLabel);
        pPlaceOrderTitle.setMaximumSize(new Dimension(300,70));

        orderTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pPlaceOrder.add(pCart);
        pPlaceOrder.add(pOrderBttns);

        pCart.setLayout(new GridLayout(2,2));
        pCart.setMaximumSize(new Dimension(600,200));
        pCart.add(prodIdLabel);
        prodIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pCart.add(idToAddCartTF);
        pCart.add(cartLabel);
        pCart.add(cartContents);

        pOrderBttns.setLayout(new BoxLayout(pOrderBttns, BoxLayout.X_AXIS));
        pOrderBttns.add(addToCartBttn);
        pOrderBttns.add(placeOrderBttn);

        //table view
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500,300));
        pTable.add(scrollPane);

        frame.setContentPane(pGeneral);
        frame.setVisible(false);
        frame.pack();
    }

    /**
     * Populate table j table.
     *
     * @param list the list
     * @return the j table
     */
    protected JTable populateTable(List<MenuItem> list){
        String [][]data = new String[list.size()][8];
        int index = 0;
        for(MenuItem m : list){
            data[index][0] = String.valueOf(m.getId());
            data[index][1] = m.getTitle();
            data[index][2] = String.valueOf(m.getRating());
            data[index][3] = String.valueOf(m.getCalories());
            data[index][4] = String.valueOf(m.getProtein());
            data[index][5] = String.valueOf(m.getFat());
            data[index][6] = String.valueOf(m.getSodium());
            data[index][7] = String.valueOf(m.getPrice());
            index++;
        }
        JTable table = new JTable(data, columns);
        return table;
    }

    /**
     * Gets cart contents.
     *
     * @return the cart contents
     */
    public JLabel getCartContents() {
        return cartContents;
    }

    /**
     * Gets prod id label.
     *
     * @return the prod id label
     */
    public JLabel getProdIdLabel() {
        return prodIdLabel;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public JPanel getpTable() {
        return pTable;
    }

    /**
     * Sets table.
     *
     * @param table the table
     */
    public void setTable(JTable table) {
        this.table = table;
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
     * Gets title tf.
     *
     * @return the title tf
     */
    public JTextField getTitleTF() {
        return titleTF;
    }

    /**
     * Gets rating tf.
     *
     * @return the rating tf
     */
    public JTextField getRatingTF() {
        return ratingTF;
    }

    /**
     * Gets cals tf.
     *
     * @return the cals tf
     */
    public JTextField getCalsTF() {
        return calsTF;
    }

    /**
     * Gets protein tf.
     *
     * @return the protein tf
     */
    public JTextField getProteinTF() {
        return proteinTF;
    }

    /**
     * Gets fat tf.
     *
     * @return the fat tf
     */
    public JTextField getFatTF() {
        return fatTF;
    }

    /**
     * Gets sodium tf.
     *
     * @return the sodium tf
     */
    public JTextField getSodiumTF() {
        return sodiumTF;
    }

    /**
     * Gets price tf.
     *
     * @return the price tf
     */
    public JTextField getPriceTF() {
        return priceTF;
    }

    /**
     * Gets reset filter bttn.
     *
     * @return the reset filter bttn
     */
    public JButton getResetFilterBttn() {
        return resetFilterBttn;
    }

    /**
     * Gets search bttn.
     *
     * @return the search bttn
     */
    public JButton getSearchBttn() {
        return searchBttn;
    }

    /**
     * Gets id to add cart tf.
     *
     * @return the id to add cart tf
     */
    public JTextField getIdToAddCartTF() {
        return idToAddCartTF;
    }

    /**
     * Gets cart label.
     *
     * @return the cart label
     */
    public JLabel getCartLabel() {
        return cartLabel;
    }

    /**
     * Gets add to cart bttn.
     *
     * @return the add to cart bttn
     */
    public JButton getAddToCartBttn() {
        return addToCartBttn;
    }

    /**
     * Gets place order bttn.
     *
     * @return the place order bttn
     */
    public JButton getPlaceOrderBttn() {
        return placeOrderBttn;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Gets scroll pane.
     *
     * @return the scroll pane
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Sets scroll pane.
     *
     * @param scrollPane the scroll pane
     */
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
}
