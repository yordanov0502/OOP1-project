package options;


import bg.tu_varna.sit.StorageHistory;
import еxceptions.OptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class Log implements bg.tu_varna.sit.Log {

    private String from;
    private String to;

    public Log(String from, String to)
    {
        this.from=from;
        this.to=to;
    }

    public Log(){}

    public void validateFromDate() throws OptionException
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try
        {
            java.time.LocalDate.parse(from, formatter);
        }
        catch (DateTimeParseException e)
        {
            throw new OptionException("You have entered invalid <from> date!");
        }
    }

    public void validateToDate() throws OptionException
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try
        {
            java.time.LocalDate.parse(to,formatter);
        }
        catch (DateTimeParseException e)
        {
            throw new OptionException("You have entered invalid <to> date!");
        }
    }

    @Override
    public void log(LocalDate from, LocalDate to, StorageHistory storageHistory)
    {
        double addedQuantityForPeriod=0;
        double removedQuantityForPeriod=0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");



        for(Map.Entry<String, Double> i: storageHistory.getAddedQuantity().entrySet())
        {
            if(java.time.LocalDate.parse(i.getKey(),formatter).compareTo(from) >= 0 && java.time.LocalDate.parse(i.getKey(),formatter).compareTo(to) <= 0)
            {
                addedQuantityForPeriod+=i.getValue();
            }
        }

        for(Map.Entry<String, Double> i: storageHistory.getRemovedQuantity().entrySet())
        {
            if(java.time.LocalDate.parse(i.getKey(),formatter).compareTo(from) >= 0 && java.time.LocalDate.parse(i.getKey(),formatter).compareTo(to) <= 0)
            {
                removedQuantityForPeriod+=i.getValue();
            }
        }


        System.out.println("\n***********(from "+from.toString()+" to "+to.toString()+")***********");
        System.out.println("-----------------------------------------");
        System.out.println("Added quantity = "+addedQuantityForPeriod);
        System.out.println("Removed quantity = "+removedQuantityForPeriod);
        System.out.println("-----------------------------------------");
    }

}