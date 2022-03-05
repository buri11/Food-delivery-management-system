package PresentationLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type Admin view.
 */
public class AdminView {
    private JFrame frame = new JFrame("Admin Operations");
    private JPanel pGeneral = new JPanel();

    private JPanel pOperations = new JPanel();
    private JPanel pTable = new JPanel();

    //table panel
    private JTable table;
    private JScrollPane scrollPane;


    //titles pane
    private JPanel pTitle = new JPanel();
    private JLabel titleLabel = new JLabel("You are logged in as admin");
    private JLabel selectOpLabel = new JLabel("Select the operation you want to perform:");

    //select operation panel
    private JPanel pSelectOp = new JPanel();
    private JRadioButton importRadio = new JRadioButton("import products");
    private JRadioButton manageRadio = new JRadioButton("manage products");
    private JRadioButton reportsRadio = new JRadioButton("generate reports");
    private ButtonGroup radioGroup = new ButtonGroup();

    //import button panel
    private JPanel pImportBttn = new JPanel();
    private JButton importBttn = new JButton("Import items from .csv file");

    //manage products panel
    //here will be product operation panel and all the adjacent panels depending on the operation
    private JPanel pManageProd = new JPanel();

    //select product operation panel
    private JPanel pSelectProdOp = new JPanel();
    private JRadioButton addRadio = new JRadioButton("add");
    private JRadioButton editRadio = new JRadioButton("edit");
    private JRadioButton deleteRadio = new JRadioButton("delete");
    private JRadioButton addCompositeRadio = new JRadioButton("add new composite product");
    private ButtonGroup manageProdGroup = new ButtonGroup();

    //add product panel
    private JPanel pAddProd = new JPanel();
    private JLabel prodTitleLabel = new JLabel("title:");
    private JTextField titleTF = new JTextField(10);
    private JLabel ratingLabel = new JLabel("rating:");
    private JTextField ratingTF = new JTextField(10);
    private JLabel calsLabel = new JLabel("calories:");
    private JTextField calsTF = new JTextField(10);
    private JLabel proteinLabel = new JLabel("protein:");
    private JTextField proteinTF = new JTextField(10);
    private JLabel fatLabel = new JLabel("fat:");
    private JTextField fatTF = new JTextField(10);
    private JLabel sodiumLabel = new JLabel("sodium:");
    private JTextField sodiumTF = new JTextField(10);
    private JLabel priceLabel = new JLabel("price:");
    private JTextField priceTF = new JTextField(10);
    private JButton addProdBttn = new JButton("Add product");

    //edit product panel
    private JPanel pEditProd = new JPanel();
    private JLabel idToEditLabel = new JLabel("Type the product's id you want to edit");
    private JTextField idEditTF = new JTextField(10);
    private JLabel fieldToEditLabel = new JLabel("Select field to edit");
    private String[] cbOptions = {"title", "rating", "calories", "protein", "fat", "sodium", "price"};
    private JComboBox cb = new JComboBox(cbOptions);
    private JLabel newValLabel = new JLabel("New value:");
    private JTextField newValTF = new JTextField(15);
    private JButton editProdBttn = new JButton("Edit");

    //delete product panel
    private JPanel pDelProd = new JPanel();
    private JLabel idToDelLabel = new JLabel("Type the product's id you want to delete");
    private JTextField idDelTF = new JTextField(10);
    private JButton delBttn = new JButton("Delete");

    //add composite panel
    private JPanel pAddComp = new JPanel();
    private JLabel idCompLabel = new JLabel("Product id to add to composite product:");
    private JTextField idCompTF = new JTextField(10);
    private JLabel productListLabel = new JLabel("Products added: ");
    private JButton addProdToTempListBttn = new JButton("Add product");
    private JLabel titleCompLabel = new JLabel("Title:");
    private JTextField compTitleTF = new JTextField(15);
    private JButton addCompBttn = new JButton("Create composite product");

    //reports panel
    private JPanel pReports = new JPanel();
    private JPanel pOrdersByHour = new JPanel();
    private JPanel pOrdersByHourBttn = new JPanel();
    private JPanel pProdByOrders = new JPanel();
    private JPanel pProdByOrdersBttn = new JPanel();
    private JPanel pClientsByOrder = new JPanel();
    private JPanel pClientsByOrderBttn = new JPanel();
    private JPanel pProdByDate = new JPanel();
    private JPanel pProdByDateBttn = new JPanel();

    private JLabel startHourLabel = new JLabel("Start hour:");
    private JTextField startHourTF = new JTextField(10);
    private JLabel endHourLabel = new JLabel("End hour:");
    private JTextField endHourTF = new JTextField(10);
    private JButton orderByHourBttn = new JButton("Search for orders between specified hours");

    private JLabel nbOfOrdersLabel = new JLabel("Min number of orders per product:");
    private JTextField ordersPerProdTF = new JTextField(10);
    private JButton prodByOrdersBttn = new JButton("Search for products");

    private JLabel minNbOrdersLabel = new JLabel("Min number of orders:");
    private JTextField minNbOrdersTF = new JTextField(10);
    private JLabel minValOrderLabel = new JLabel("Min value for orders:");
    private JTextField minValOrderTF = new JTextField(15);
    private JButton clientsByNbOrdersBttn = new JButton("Search for clients");

    private JLabel prodByDateLabel = new JLabel("Products ordered in date (format yyyy-mm-dd):");
    private JTextField dateTF = new JTextField(20);
    private JButton prodByDateBttn = new JButton("Search for products by date");

    /**
     * Instantiates a new Admin view.
     */
    public AdminView(){
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(200,200));

        //table panel
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600,300));
        pTable.add(scrollPane);


        //title panel
        pTitle.setLayout(new GridLayout(0,1));
        pTitle.add(titleLabel);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pTitle.add(selectOpLabel);
        selectOpLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //select operations panel
        radioGroup.add(importRadio);
        radioGroup.add(manageRadio);
        radioGroup.add(reportsRadio);
        pSelectOp.setLayout(new GridLayout(1,0));
        pSelectOp.add(importRadio);
        pSelectOp.add(manageRadio);
        pSelectOp.add(reportsRadio);

        //import button panel
        pImportBttn.add(importBttn);

        //manage products panel
        pManageProd.setLayout(new GridLayout(0,1));
        pManageProd.add(pSelectProdOp);

        //select prod operation panel
        pSelectProdOp.setLayout(new GridLayout(1,0));
        manageProdGroup.add(addRadio);
        manageProdGroup.add(editRadio);
        manageProdGroup.add(deleteRadio);
        manageProdGroup.add(addCompositeRadio);
        pSelectProdOp.add(addRadio);
        pSelectProdOp.add(editRadio);
        pSelectProdOp.add(deleteRadio);
        pSelectProdOp.add(addCompositeRadio);

        //add products panel
        pAddProd.setLayout(new GridLayout(0,2));
        pAddProd.add(prodTitleLabel);
        prodTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddProd.add(titleTF);
        pAddProd.add(ratingLabel);
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddProd.add(ratingTF);
        pAddProd.add(calsLabel);
        calsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddProd.add(calsTF);
        pAddProd.add(proteinLabel);
        proteinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddProd.add(proteinTF);
        pAddProd.add(fatLabel);
        fatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddProd.add(fatTF);
        pAddProd.add(sodiumLabel);
        sodiumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddProd.add(sodiumTF);
        pAddProd.add(priceLabel);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddProd.add(priceTF);
        pAddProd.add(addProdBttn);

        //edit product panel
        pEditProd.setLayout(new GridLayout(0,2));
        pEditProd.add(idToEditLabel);
        pEditProd.add(idEditTF);
        pEditProd.add(fieldToEditLabel);
        pEditProd.add(cb);
        pEditProd.add(newValLabel);
        pEditProd.add(newValTF);
        pEditProd.add(editProdBttn);

        //delete product panel
        pDelProd.setLayout(new GridLayout(1,0));
        pDelProd.add(idToDelLabel);
        pDelProd.add(idDelTF);
        pDelProd.add(delBttn);

        //add composite product panel
        pAddComp.setLayout(new GridLayout(0,2));
        pAddComp.add(idCompLabel);
        pAddComp.add(idCompTF);
        pAddComp.add(productListLabel);
        pAddComp.add(addProdToTempListBttn);
        pAddComp.add(titleCompLabel);
        pAddComp.add(compTitleTF);
        pAddComp.add(addCompBttn);

        //reports panel
        pReports.setLayout(new BoxLayout(pReports, BoxLayout.Y_AXIS));
        pReports.add(pOrdersByHour);
        pReports.add(pOrdersByHourBttn);
        pReports.add(pProdByOrders);
        pReports.add(pProdByOrdersBttn);
        pReports.add(pClientsByOrder);
        pReports.add(pClientsByOrderBttn);
        pReports.add(pProdByDate);
        pReports.add(pProdByDateBttn);

        pOrdersByHour.setLayout(new GridLayout(1,4));
        pOrdersByHour.add(startHourLabel);
        startHourLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pOrdersByHour.add(startHourTF);
        pOrdersByHour.add(endHourLabel);
        endHourLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pOrdersByHour.add(endHourTF);
        pOrdersByHourBttn.add(orderByHourBttn);

        pProdByOrders.setLayout(new GridLayout(1,2));
        pProdByOrders.add(nbOfOrdersLabel);
        nbOfOrdersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pProdByOrders.add(ordersPerProdTF);
        pProdByOrdersBttn.add(prodByOrdersBttn);

        pClientsByOrder.setLayout(new GridLayout(1,4));
        pClientsByOrder.add(minNbOrdersLabel);
        minNbOrdersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pClientsByOrder.add(minNbOrdersTF);
        pClientsByOrder.add(minValOrderLabel);
        minValOrderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pClientsByOrder.add(minValOrderTF);
        pClientsByOrderBttn.add(clientsByNbOrdersBttn);

        pProdByDate.setLayout(new GridLayout(1,2));
        pProdByDate.add(prodByDateLabel);
        prodByDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pProdByDate.add(dateTF);
        pProdByDateBttn.add(prodByDateBttn);


        pOperations.setLayout(new BoxLayout(pOperations, BoxLayout.Y_AXIS));
        pOperations.add(pTitle);
        pOperations.add(pSelectOp);

        pGeneral.setLayout(new BoxLayout(pGeneral, BoxLayout.X_AXIS));
        pGeneral.add(pOperations);
        pGeneral.add(pTable);

        frame.setContentPane(pGeneral);
        frame.setVisible(false);
        frame.pack();
    }

    /**
     * Populate client table j table.
     *
     * @param list the list
     * @return the j table
     */
    protected JTable populateClientTable(List<User> list){
        String []columns = {"username", "times ordered"};
        String [][]data = new String[list.size()][2];
        int index = 0;
        for(User u : list){
            data[index][0] = u.getUserName();
            data[index][1] = String.valueOf(u.getTimesOrdered());
            index++;
        }
        return new JTable(data, columns);
    }

    /**
     * Populate order table j table.
     *
     * @param list the list
     * @return the j table
     */
    protected JTable populateOrderTable(List<Order> list){
        String []columns = {"id", "client username", "date and time", "price"};
        String [][]data = new String[list.size()][4];
        int index = 0;
        for(Order o : list){
            data[index][0] = String.valueOf(o.getId());
            data[index][1] = o.getClientUsername();
            data[index][2] = o.getOrderDate().toString();
            data[index][3] = String.valueOf(o.getPrice());
            index++;
        }
        return new JTable(data, columns);
    }

    /**
     * Populate menu item table orders nb j table.
     *
     * @param list the list
     * @return the j table
     */
    protected JTable populateMenuItemTableOrdersNb(List<MenuItem> list){
        String [][]data = new String[list.size()][9];
        String []columns = {"id", "title", "times ordered", "rating", "calories", "protein", "fat", "sodium", "price"};
        int index = 0;
        for(MenuItem m : list){
            data[index][0] = String.valueOf(m.getId());
            data[index][1] = m.getTitle();
            data[index][2] = String.valueOf(m.getOrderedFor());
            data[index][3] = String.valueOf(m.getRating());
            data[index][4] = String.valueOf(m.getCalories());
            data[index][5] = String.valueOf(m.getProtein());
            data[index][6] = String.valueOf(m.getFat());
            data[index][7] = String.valueOf(m.getSodium());
            data[index][8] = String.valueOf(m.getPrice());
            index++;
        }
        JTable table = new JTable(data, columns);
        return table;
    }

    /**
     * Populate menu item table j table.
     *
     * @param list the list
     * @return the j table
     */
    protected JTable populateMenuItemTable(List<MenuItem> list){
        String [][]data = new String[list.size()][8];
        String []columns = {"id", "title", "rating", "calories", "protein", "fat", "sodium", "price"};
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
     * Gets general.
     *
     * @return the general
     */
    public JPanel getpGeneral() {
        return pGeneral;
    }

    /**
     * Gets start hour tf.
     *
     * @return the start hour tf
     */
    public JTextField getStartHourTF() {
        return startHourTF;
    }

    /**
     * Gets end hour tf.
     *
     * @return the end hour tf
     */
    public JTextField getEndHourTF() {
        return endHourTF;
    }

    /**
     * Gets order by hour bttn.
     *
     * @return the order by hour bttn
     */
    public JButton getOrderByHourBttn() {
        return orderByHourBttn;
    }

    /**
     * Gets orders per prod tf.
     *
     * @return the orders per prod tf
     */
    public JTextField getOrdersPerProdTF() {
        return ordersPerProdTF;
    }

    /**
     * Gets prod by orders bttn.
     *
     * @return the prod by orders bttn
     */
    public JButton getProdByOrdersBttn() {
        return prodByOrdersBttn;
    }

    /**
     * Gets min nb orders tf.
     *
     * @return the min nb orders tf
     */
    public JTextField getMinNbOrdersTF() {
        return minNbOrdersTF;
    }

    /**
     * Gets min val order tf.
     *
     * @return the min val order tf
     */
    public JTextField getMinValOrderTF() {
        return minValOrderTF;
    }

    /**
     * Gets clients by nb orders bttn.
     *
     * @return the clients by nb orders bttn
     */
    public JButton getClientsByNbOrdersBttn() {
        return clientsByNbOrdersBttn;
    }

    /**
     * Gets date tf.
     *
     * @return the date tf
     */
    public JTextField getDateTF() {
        return dateTF;
    }

    /**
     * Gets prod by date bttn.
     *
     * @return the prod by date bttn
     */
    public JButton getProdByDateBttn() {
        return prodByDateBttn;
    }

    /**
     * Gets id comp label.
     *
     * @return the id comp label
     */
    public JLabel getIdCompLabel() {
        return idCompLabel;
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
     * Sets table.
     *
     * @param table the table
     */
    public void setTable(JTable table) {
        this.table = table;
    }

    /**
     * Sets scroll pane.
     *
     * @param scrollPane the scroll pane
     */
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
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
     * Gets manage prod group.
     *
     * @return the manage prod group
     */
    public ButtonGroup getManageProdGroup() {
        return manageProdGroup;
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
     * Gets operations.
     *
     * @return the operations
     */
    public JPanel getpOperations() {
        return pOperations;
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
     * Gets select op.
     *
     * @return the select op
     */
    public JPanel getpSelectOp() {
        return pSelectOp;
    }

    /**
     * Gets import radio.
     *
     * @return the import radio
     */
    public JRadioButton getImportRadio() {
        return importRadio;
    }

    /**
     * Gets manage radio.
     *
     * @return the manage radio
     */
    public JRadioButton getManageRadio() {
        return manageRadio;
    }

    /**
     * Gets reports radio.
     *
     * @return the reports radio
     */
    public JRadioButton getReportsRadio() {
        return reportsRadio;
    }

    /**
     * Gets import bttn.
     *
     * @return the import bttn
     */
    public JPanel getpImportBttn() {
        return pImportBttn;
    }

    /**
     * Gets import bttn.
     *
     * @return the import bttn
     */
    public JButton getImportBttn() {
        return importBttn;
    }

    /**
     * Gets manage prod.
     *
     * @return the manage prod
     */
    public JPanel getpManageProd() {
        return pManageProd;
    }

    /**
     * Gets add radio.
     *
     * @return the add radio
     */
    public JRadioButton getAddRadio() {
        return addRadio;
    }

    /**
     * Gets edit radio.
     *
     * @return the edit radio
     */
    public JRadioButton getEditRadio() {
        return editRadio;
    }

    /**
     * Gets delete radio.
     *
     * @return the delete radio
     */
    public JRadioButton getDeleteRadio() {
        return deleteRadio;
    }

    /**
     * Gets add composite radio.
     *
     * @return the add composite radio
     */
    public JRadioButton getAddCompositeRadio() {
        return addCompositeRadio;
    }

    /**
     * Gets add prod.
     *
     * @return the add prod
     */
    public JPanel getpAddProd() {
        return pAddProd;
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
     * Gets add prod bttn.
     *
     * @return the add prod bttn
     */
    public JButton getAddProdBttn() {
        return addProdBttn;
    }

    /**
     * Gets edit prod.
     *
     * @return the edit prod
     */
    public JPanel getpEditProd() {
        return pEditProd;
    }

    /**
     * Gets id edit tf.
     *
     * @return the id edit tf
     */
    public JTextField getIdEditTF() {
        return idEditTF;
    }

    /**
     * Gets cb.
     *
     * @return the cb
     */
    public JComboBox getCb() {
        return cb;
    }

    /**
     * Gets new val tf.
     *
     * @return the new val tf
     */
    public JTextField getNewValTF() {
        return newValTF;
    }

    /**
     * Gets edit prod bttn.
     *
     * @return the edit prod bttn
     */
    public JButton getEditProdBttn() {
        return editProdBttn;
    }

    /**
     * Gets del prod.
     *
     * @return the del prod
     */
    public JPanel getpDelProd() {
        return pDelProd;
    }

    /**
     * Gets id del tf.
     *
     * @return the id del tf
     */
    public JTextField getIdDelTF() {
        return idDelTF;
    }

    /**
     * Gets del bttn.
     *
     * @return the del bttn
     */
    public JButton getDelBttn() {
        return delBttn;
    }

    /**
     * Gets add comp.
     *
     * @return the add comp
     */
    public JPanel getpAddComp() {
        return pAddComp;
    }

    /**
     * Gets id comp tf.
     *
     * @return the id comp tf
     */
    public JTextField getIdCompTF() {
        return idCompTF;
    }

    /**
     * Gets product list label.
     *
     * @return the product list label
     */
    public JLabel getProductListLabel() {
        return productListLabel;
    }

    /**
     * Gets add prod to temp list bttn.
     *
     * @return the add prod to temp list bttn
     */
    public JButton getAddProdToTempListBttn() {
        return addProdToTempListBttn;
    }

    /**
     * Gets comp title tf.
     *
     * @return the comp title tf
     */
    public JTextField getCompTitleTF() {
        return compTitleTF;
    }

    /**
     * Gets add comp bttn.
     *
     * @return the add comp bttn
     */
    public JButton getAddCompBttn() {
        return addCompBttn;
    }

    /**
     * Gets reports.
     *
     * @return the reports
     */
    public JPanel getpReports() {
        return pReports;
    }
}
