package dialogs;

import bg.tu_varna.sit.Add;
import bg.tu_varna.sit.Location;
import bg.tu_varna.sit.Product;
import bg.tu_varna.sit.Warehouse;
import еxceptions.LocationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DialogAdd extends JFrame{

    private JTextField name;
    private JTextField expiryDate;
    private JTextField entryDate;
    private JTextField manufacturer;
    private JTextField unit;
    private JTextField quantity;
    private JTextField sector;
    private JTextField shelf;
    private JTextField number;
    private JTextField comment;
    private JButton addButton;
    private JPanel panelAdd;

    private ValidateDialogAdd validateDialogAdd = new ValidateDialogAdd();

    private Add add = new options.Add();

    public DialogAdd(){}


    public DialogAdd(Warehouse warehouse)  {

        setContentPane(panelAdd);
        setTitle("Please add product");
        setBounds(700, 200, 500, 570);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        preventBreaking();

        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {

                String[] attributes = {name.getText(),expiryDate.getText(),entryDate.getText(),manufacturer.getText(),
                                       unit.getText(), quantity.getText(),sector.getText(),shelf.getText(),number.getText(),
                                       comment.getText()};


                       if(validateDialogAdd.validateAll(attributes))
                       {
                           Location location = new Location(sector.getText(), Integer.parseInt(shelf.getText()), Integer.parseInt(number.getText()));

                           Product product = new Product(name.getText(), org.joda.time.LocalDate.parse(expiryDate.getText()), org.joda.time.LocalDate.parse(entryDate.getText()),
                                                         manufacturer.getText(), unit.getText(), Double.parseDouble(quantity.getText()),
                                                         location, comment.getText());


                           boolean flag = true;
                           try
                           {
                               add.add(product, warehouse.getProductList());
                           }
                           catch (LocationException customException)
                           {
                               flag=false;
                               JOptionPane.showMessageDialog(addButton, (customException).getMessage());
                           }

                           if(flag)
                           {
                           JOptionPane.showMessageDialog(addButton, name.getText() + " has been added to the warehouse successfully.");
                           dispose();
                           }



                       }
            }
        });
    }


    public JButton getAddButton() {return addButton;}

    private void preventBreaking()
    {
        name.setTransferHandler(null);
        expiryDate.setTransferHandler(null);
        entryDate.setTransferHandler(null);
        manufacturer.setTransferHandler(null);
        unit.setTransferHandler(null);
        quantity.setTransferHandler(null);
        sector.setTransferHandler(null);
        shelf.setTransferHandler(null);
        number.setTransferHandler(null);
        comment.setTransferHandler(null);
    }

}