package PresentationLayer;

import BusinessLayer.*;
import BusinessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Admin controller.
 */
public class AdminController {
    private AdminView view;
    private DeliveryService ds;
    private ArrayList<Integer> idCompProd = new ArrayList<>();

    /**
     * Instantiates a new Admin controller.
     *
     * @param v  the v
     * @param ds the ds
     */
    public AdminController(AdminView v, DeliveryService ds){
        view = v;
        this.ds = ds;


        //radio buttons for selecting admin operation
        view.getImportRadio().addActionListener(new ImportSelActionListener());
        view.getManageRadio().addActionListener(new ManageProdActionListener());
        view.getReportsRadio().addActionListener(new ReportsBttnActionListener());

        //radio buttons for selecting product operations
        view.getAddRadio().addActionListener(new AddProdRadioActionListener());
        view.getEditRadio().addActionListener(new EditProdRadioActionListener());
        view.getDeleteRadio().addActionListener(new DelProdRadioActionListener());
        view.getAddCompositeRadio().addActionListener(new AddCompRadioActionListener());

        //button for importing from products.csv
        view.getImportBttn().addActionListener(new ImportActionListener());

        //button for adding new product
        view.getAddProdBttn().addActionListener(new AddProdActionListener());
        //button for editing product title
        view.getEditProdBttn().addActionListener(new EditProdActionListener());
        //button for deleting product
        view.getDelBttn().addActionListener(new DelProdActionListener());

        //button for adding to temporary list to compose a composite product
        view.getAddProdToTempListBttn().addActionListener(new AddTempListActionListener());
        //button for adding new composite product
        view.getAddCompBttn().addActionListener(new AddCompProdActionListener());

        //button for report1
        view.getOrderByHourBttn().addActionListener(new OrderByHourActionListener());

        //button for report2
        view.getProdByOrdersBttn().addActionListener(new ProdByOrdersActionListener());

        //button for report 3
        view.getClientsByNbOrdersBttn().addActionListener(new ClientsByNbOrders());

        //button for report 4
        view.getProdByDateBttn().addActionListener(new ProdByDateActionListener());
    }

    //report 4
    private class ProdByDateActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!view.getDateTF().equals("")){
                String strDate = view.getDateTF().getText();
                String []splitDate = strDate.split("-");

                LocalDate date = LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]),
                        Integer.parseInt(splitDate[2]));

                updateMenuItemTableOrdersNb(ds.findItemByDate(date));

                view.getDateTF().setText("");
            }
        }
    }

    //report 3
    private class ClientsByNbOrders implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!view.getMinNbOrdersTF().equals("") || !view.getMinValOrderTF().equals("")){
                int minNbOrders = Integer.parseInt(view.getMinNbOrdersTF().getText());
                int minValOrder = Integer.parseInt(view.getMinValOrderTF().getText());
                updateClientTable(ds.findUserByTimesOrdered(minNbOrders, minValOrder));

                view.getMinNbOrdersTF().setText("");
                view.getMinValOrderTF().setText("");
            }
        }
    }

    //report 2
    private class ProdByOrdersActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!view.getOrdersPerProdTF().getText().equals("")){
                int minNb = Integer.parseInt(view.getOrdersPerProdTF().getText());
                updateMenuItemTableOrdersNb(ds.findProdByTimesOrdered(minNb));

                view.getOrdersPerProdTF().setText("");
            }
        }
    }

    //report 1
    private class OrderByHourActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!view.getStartHourTF().getText().equals("") || !view.getEndHourTF().getText().equals("")){
                List<Order> orders = ds.findOrdersByHour(view.getStartHourTF().getText(), view.getEndHourTF().getText());
                updateOrderTable(orders);
                view.getStartHourTF().setText("");
                view.getEndHourTF().setText("");
            }
        }
    }

    private class AddCompProdActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!view.getCompTitleTF().getText().equals("") || !idCompProd.isEmpty()){
                ArrayList<BaseProduct> baseProducts = new ArrayList<>();
                String title = view.getCompTitleTF().getText();
                for(int i : idCompProd){
                    BaseProduct b = (BaseProduct) ds.findItemByID(i);
                    baseProducts.add(b);
                }
                CompositeProduct comp = new CompositeProduct(title, baseProducts);
                ds.addMenuItem(comp);
                updateMenuItemTable(ds.getMenuItems());
                idCompProd.clear();
                view.getIdCompTF().setText("");
                view.getCompTitleTF().setText("");
                view.getProductListLabel().setText("Products added: ");
            }
        }
    }

    private class AddTempListActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!view.getIdCompTF().getText().equals("")){
                int id = Integer.parseInt(view.getIdCompTF().getText());
                if(ds.findItemByID(id) != null){
                    idCompProd.add(id);
                    String toAddToList = "";
                    if(idCompProd.size() > 1){
                        toAddToList = ", ";
                    }
                    toAddToList += String.valueOf(id);
                    view.getProductListLabel().setText(view.getProductListLabel().getText() + toAddToList);
                    view.getIdCompLabel().setText("Product id to add to composite product:");
                }
                else{
                    view.getIdCompLabel().setText("Product id to add to composite product: no product with this id!");
                }
            }
        }
    }

    private class DelProdActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(view.getIdDelTF().getText());
            if(!view.getIdDelTF().getText().equals("")){
                ds.delMenuItem(id);
                updateMenuItemTable(ds.getMenuItems());
            }
        }
    }

    private class EditProdActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if( !view.getIdEditTF().equals("") || !view.getNewValTF().equals("") ){
                String fieldToUpdate = view.getCb().getSelectedItem().toString();
                int id = Integer.parseInt(view.getIdEditTF().getText());
                String newVal = view.getNewValTF().getText();
                float newValFl = 0;
                if (fieldToUpdate.equals("title")){
                    ds.editItemTitle(id, newVal);
                }
                else{
                    newValFl = Float.parseFloat(newVal);
                }
                if (fieldToUpdate.equals("rating")){
                    ds.editItemRating(id, newValFl);
                }
                if (fieldToUpdate.equals("calories")){
                    ds.editItemCals(id, newValFl);
                }
                if (fieldToUpdate.equals("protein")){
                    ds.editItemProtein(id, newValFl);
                }
                if (fieldToUpdate.equals("fat")){
                    ds.editItemFat(id, newValFl);
                }
                if (fieldToUpdate.equals("sodium")){
                    ds.editItemSodium(id, newValFl);
                }
                if (fieldToUpdate.equals("price")){
                    ds.editItemPrice(id, newValFl);
                }
                updateMenuItemTable(ds.getMenuItems());
            }
        }
    }

    private class AddProdActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!view.getTitleTF().getText().equals("") || !view.getRatingTF().getText().equals("") ||
                    !view.getCalsTF().getText().equals("") || !view.getProteinTF().getText().equals("") ||
                    !view.getFatTF().getText().equals("") || !view.getSodiumTF().getText().equals("") ||
                    !view.getPriceTF().getText().equals("")){
                String title = view.getTitleTF().getText();

                float rating = Float.parseFloat(view.getRatingTF().getText());
                float cals = Float.parseFloat(view.getCalsTF().getText());
                float protein = Float.parseFloat(view.getProteinTF().getText());
                float fat = Float.parseFloat(view.getFatTF().getText());
                float sodium = Float.parseFloat(view.getSodiumTF().getText());
                float price = Float.parseFloat(view.getPriceTF().getText());

                BaseProduct product = new BaseProduct(title, rating, cals, protein, fat, sodium, price);
                ds.addMenuItem(product);
                updateMenuItemTable(ds.getMenuItems());
            }
        }
    }

    /**
     * Set visible.
     */
    protected void setVisible(){
        view.getFrame().setVisible(true);
    }

    private class ImportActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ds.readCSV("products.csv");
            updateMenuItemTable(ds.getMenuItems());
        }
    }

    private void updateClientTable(List<User> list){
        JScrollPane scrollPane;
        view.getpTable().remove(view.getScrollPane());
        view.setTable(view.populateClientTable(list));
        scrollPane = new JScrollPane(view.getTable());
        scrollPane.setPreferredSize(new Dimension(600, 300));
        view.setScrollPane(scrollPane);
        view.getpTable().add(view.getScrollPane());
        view.getFrame().pack();
    }

    private void updateOrderTable(List<Order> orders){
        JScrollPane scrollPane;
        view.getpTable().remove(view.getScrollPane());
        view.setTable(view.populateOrderTable(orders));
        scrollPane = new JScrollPane(view.getTable());
        scrollPane.setPreferredSize(new Dimension(600, 300));
        view.setScrollPane(scrollPane);
        view.getpTable().add(view.getScrollPane());

        view.getFrame().pack();
    }

    private void updateMenuItemTableOrdersNb(List<MenuItem> list){
        JScrollPane scrollPane;
        view.getpTable().remove(view.getScrollPane());
        view.setTable(view.populateMenuItemTableOrdersNb(list));
        scrollPane = new JScrollPane(view.getTable());
        scrollPane.setPreferredSize(new Dimension(600, 300));
        view.setScrollPane(scrollPane);
        view.getpTable().add(view.getScrollPane());

        view.getFrame().pack();
    }

    /**
     * Update menu item table.
     *
     * @param list the list
     */
    public void updateMenuItemTable(List<MenuItem> list){
        JScrollPane scrollPane;
        view.getpTable().remove(view.getScrollPane());
        view.setTable(view.populateMenuItemTable(list));
        scrollPane = new JScrollPane(view.getTable());
        scrollPane.setPreferredSize(new Dimension(600, 300));
        view.setScrollPane(scrollPane);
        view.getpTable().add(view.getScrollPane());

        view.getFrame().pack();
    }

    private class AddCompRadioActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getAddCompositeRadio().isSelected()){
                view.getpManageProd().remove(view.getpAddProd());
                view.getpManageProd().remove(view.getpEditProd());
                view.getpManageProd().remove(view.getpDelProd());
                view.getpManageProd().add(view.getpAddComp(), 1);
            }
            view.getpOperations().updateUI();
            JFrame frame = view.getFrame();
            frame.pack();
        }
    }

    private class DelProdRadioActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getDeleteRadio().isSelected()){
                view.getpManageProd().remove(view.getpAddProd());
                view.getpManageProd().remove(view.getpEditProd());
                view.getpManageProd().remove(view.getpAddComp());
                view.getpManageProd().add(view.getpDelProd(), 1);
            }
            view.getpOperations().updateUI();
            JFrame frame = view.getFrame();
            frame.pack();
        }
    }

    private class EditProdRadioActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getEditRadio().isSelected()){
                view.getpManageProd().remove(view.getpAddProd());
                view.getpManageProd().remove(view.getpDelProd());
                view.getpManageProd().remove(view.getpAddComp());
                view.getpManageProd().add(view.getpEditProd(), 1);
            }
            view.getpOperations().updateUI();
            JFrame frame = view.getFrame();
            frame.pack();
        }
    }

    private class AddProdRadioActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getAddRadio().isSelected()){
                view.getpManageProd().remove(view.getpEditProd());
                view.getpManageProd().remove(view.getpDelProd());
                view.getpManageProd().remove(view.getpAddComp());
                view.getpManageProd().add(view.getpAddProd(), 1);
            }
            view.getpOperations().updateUI();
            JFrame frame = view.getFrame();
            frame.pack();
        }
    }

    private class ReportsBttnActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getReportsRadio().isSelected()){
                view.getpOperations().remove(view.getpManageProd());
                view.getpOperations().remove(view.getpImportBttn());
                view.getpOperations().add(view.getpReports(), 2);
            }
            view.getpOperations().updateUI();
            JFrame frame = view.getFrame();
            frame.pack();
        }
    }

    private class ManageProdActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getManageRadio().isSelected()){
                view.getpOperations().remove(view.getpReports());
                view.getpOperations().remove(view.getpImportBttn());
                view.getpOperations().add(view.getpManageProd(), 2);
            }
            updateMenuItemTable(ds.getMenuItems());
            view.getManageProdGroup().clearSelection();
            view.getpOperations().updateUI();
            JFrame frame = view.getFrame();
            frame.pack();
        }
    }

    private class ImportSelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getImportRadio().isSelected()){
                view.getpOperations().remove(view.getpManageProd());
                view.getpOperations().remove(view.getpReports());
                view.getpOperations().add(view.getpImportBttn(), 2);
            }
            updateMenuItemTable(ds.getMenuItems());
            JFrame frame = view.getFrame();
            view.getpOperations().updateUI();
            frame.pack();
        }
    }
}
