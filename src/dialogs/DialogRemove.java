package dialogs;

import bg.tu_varna.sit.Remove;
import bg.tu_varna.sit.Warehouse;
import еxceptions.MissingProductException;
import еxceptions.QuantityException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class DialogRemove extends JFrame{
    private JTextField name;
    private JTextField quantity;
    private JButton removeButton;
    private JPanel panelRemove;

    private ValidateDialogRemove validateDialogRemove = new ValidateDialogRemove();

    private Remove remove = new options.Remove();

    public DialogRemove(){}

    public DialogRemove(Warehouse warehouse) {

        setContentPane(panelRemove);
        setTitle("Please remove product");
        setBounds(650, 400, 550, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        preventBreaking();

            removeButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    String[] attributes = {name.getText(), quantity.getText()};

                    if(validateDialogRemove.validateAll(attributes))
                    {
                        boolean flag=true;
                        boolean optionNo=false;
                        try
                        {
                            optionNo = remove.remove(name.getText(), Double.parseDouble(quantity.getText()), warehouse.getProductList());
                        }
                        catch (MissingProductException customException)
                        {
                            flag=false;
                            JOptionPane.showMessageDialog(removeButton, (customException).getMessage());
                        }

                        if(flag && !optionNo)
                        {
                            JOptionPane.showMessageDialog(removeButton, "Operation \"remove\" has been successful.");
                            dispose();
                        }

                        else if(flag && optionNo)
                        {
                            dispose();
                        }
                    }


                }
            });
        }

    public JButton getRemoveButton()
    {
        return removeButton;
    }

    public JPanel getPanelRemove()
    {
        return panelRemove;
    }

    private void preventBreaking()
    {
        name.setTransferHandler(null);
        quantity.setTransferHandler(null);
    }

}