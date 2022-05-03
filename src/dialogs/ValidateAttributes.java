package dialogs;

import еxceptions.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;

public class ValidateAttributes  {


    public boolean validateAll(String[] attributes) {

        DialogAdd d = new DialogAdd();
        boolean enetered = false;

        try {
            checkForEmptyFields(attributes);
            validateExpiryDate(attributes[1]);
            validateEntryDate(attributes[2]);
            validateUnit(attributes[4]);
            validateQuantity(attributes);
            validateLocation(attributes);
        }
        catch (EmptyFieldException | DateException | UnitException | QuantityException | LocationException customException)
        {
            enetered=true;

            if(customException instanceof EmptyFieldException)
            {
                JOptionPane.showMessageDialog(d.getAddButton(), (customException).getMessage());
            }

            else if(customException instanceof DateException)
            {
                JOptionPane.showMessageDialog(d.getAddButton(), (customException).getMessage());
            }

            else if(customException instanceof UnitException)
           {
            JOptionPane.showMessageDialog(d.getAddButton(), (customException).getMessage());
           }

            else if(customException instanceof QuantityException)
            {
                JOptionPane.showMessageDialog(d.getAddButton(),(customException).getMessage());
            }

            else if(customException instanceof LocationException)
            {
                JOptionPane.showMessageDialog(d.getAddButton(), (customException).getMessage());
            }

        }

        if(!enetered) {return true;}
        else {return false;}
    }

    private void checkForEmptyFields(String[] attributes) throws EmptyFieldException
    {
        if (    Objects.equals(attributes[0], "") || Objects.equals(attributes[1], "") ||
                Objects.equals(attributes[2], "") || Objects.equals(attributes[3], "") ||
                Objects.equals(attributes[4], "") || Objects.equals(attributes[5], "") ||
                Objects.equals(attributes[6], "") || Objects.equals(attributes[7], "") ||
                Objects.equals(attributes[8], "") || Objects.equals(attributes[9], "")
           )
        {
            throw new EmptyFieldException("All fields should be filled!");
        }
    }

    private void validateExpiryDate(String date) throws DateException
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try
        {
            LocalDate.parse(date, formatter);
        }
        catch (DateTimeParseException e)
        {
            throw new DateException("You have entered invalid expiry date!");
        }
    }

    private void validateEntryDate(String date) throws DateException
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try
        {
            LocalDate.parse(date, formatter);
        }
        catch (DateTimeParseException e)
        {
            throw new DateException("You have entered invalid entry date!");
        }

        org.joda.time.LocalDate currDate = org.joda.time.LocalDate.now(); //Current date
        org.joda.time.LocalDate entryDae = org.joda.time.LocalDate.parse(date);
        if(currDate.compareTo(entryDae) < 0)
        {
            throw new DateException("You are not allowed to enter \"entry date\"\nwhich has not passed yet!");
        }
    }

    private void validateUnit(String unit) throws UnitException
    {
        if(!(unit.equals("kg") || unit.equals("Kg") || unit.equals("KG") || unit.equals("l") || unit.equals("L")))
        {
            throw new UnitException("You have entered invalid unit type!");
        }
    }

    private void validateQuantity(String[] attributes) throws QuantityException
    {
        try
        {
             Double.parseDouble(attributes[5]);
        }
        catch (NumberFormatException customException)
        {
            throw new QuantityException("You have entered invalid quantity!");
        }


            double quantity = Double.parseDouble(attributes[5]);

            if (quantity > 1000)
            {
                throw new QuantityException("Quantity of product \"" + attributes[0] + "\" shouldn't exceed 1000" + attributes[4] + ".");
            }
            else if (quantity <= 0)
            {
                throw new QuantityException("Quantity of product \"" + attributes[0] + "\" should be a positive number!");
            }

    }

    private void validateLocation(String[] attributes) throws LocationException
    {
        String[] sectors = {"A", "B", "C", "D"};
        String[] shelves = {"1","2","3","4","5","6","7","8","9","10"};
        String[] numbers = {"1","2","3","4","5","6","7","8","9","10"};

        boolean sectorExists = Arrays.asList(sectors).contains(attributes[6]);
        boolean shelfExists = Arrays.asList(shelves).contains(attributes[7]);
        boolean numberExists = Arrays.asList(numbers).contains(attributes[8]);

        if (!sectorExists) {throw new LocationException("You have entered invalid sector!");}

        else if(!shelfExists) {throw new LocationException("You have entered invalid shelf!");}

        else if(!numberExists) {throw new LocationException("You have entered invalid number!");}
    }

}