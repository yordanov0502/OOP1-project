package dialogs;

import еxceptions.*;

import javax.swing.*;
import java.util.Map;
import java.util.Objects;

public class ValidateDialogRemove {

    public ValidateDialogRemove(){}

    public boolean validateAll(String[] attributes) {

        DialogRemove d = new DialogRemove();
        boolean enetered = false;

        try
        {
            checkForEmptyFields(attributes);
            validateQuantity(attributes);
        }
        catch (EmptyFieldException | QuantityException customException)
        {
            enetered=true;

            if(customException instanceof EmptyFieldException)
            {
                JOptionPane.showMessageDialog(d.getRemoveButton(), (customException).getMessage());
            }

            else if(customException instanceof QuantityException)
            {
                JOptionPane.showMessageDialog(d.getRemoveButton(),(customException).getMessage());
            }

        }

        if(!enetered) {return true;}
        else {return false;}
    }

    private void checkForEmptyFields(String[] attributes) throws EmptyFieldException
    {
        if ( Objects.equals(attributes[0], "") || Objects.equals(attributes[1], "") )
        {
            throw new EmptyFieldException("All fields should be filled!");
        }
    }

    private void validateQuantity(String[] attributes) throws QuantityException
    {
        try
        {
            Double.parseDouble(attributes[1]);
        }
        catch (NumberFormatException customException)
        {
            throw new QuantityException("You have entered invalid quantity!");
        }


        double quantity = Double.parseDouble(attributes[1]);

         if (quantity <= 0)
        {
            throw new QuantityException("Quantity of product \"" + attributes[0] + "\" should be a positive number!");
        }

    }

    public boolean isNullOrEmptyMap(Map<? , ?> map)
    {
        return (map == null || map.isEmpty());
    }

}
