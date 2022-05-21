package options;

import bg.tu_varna.sit.Location;
import bg.tu_varna.sit.Product;
import bg.tu_varna.sit.StorageHistory;
import еxceptions.LocationException;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Add extends Component implements bg.tu_varna.sit.Add{

    @Override
    public void add(Product product, Map<Location,Product> productList, StorageHistory storageHistory) throws LocationException
    {
        boolean hasMatch = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (productList.isEmpty())
        {
            productList.put(product.getLocation(),product);

            storageHistory.noteInAddStorageHistory(product.getEntryDate(),product.getQuantity(),storageHistory);
        }

        else
        {
            for(Map.Entry<Location, Product> i: productList.entrySet())
            {
                if(product.equals(i.getValue()))
                {
                    if(!(product.getLocation().equals(i.getValue().getLocation())) && product.getQuantity()+i.getValue().getQuantity()<=1000)
                   {
                    JOptionPane.showMessageDialog(this,"Product \""+product.getProductName()+"\" will be added at location \""+i.getValue().getLocation().toString()+"\"","Information",JOptionPane.INFORMATION_MESSAGE);
                    double updateQuantity = product.getQuantity() + i.getValue().getQuantity();
                    i.getValue().setQuantity(updateQuantity);
                    hasMatch = true;

                    storageHistory.noteInAddStorageHistory(i.getValue().getEntryDate(),product.getQuantity(),storageHistory);

                    break;
                   }

                    else if(product.getLocation().equals(i.getValue().getLocation()) && product.getQuantity()+i.getValue().getQuantity()<=1000)
                    {
                        double updateQuantity = product.getQuantity() + i.getValue().getQuantity();
                        i.getValue().setQuantity(updateQuantity);
                        hasMatch = true;

                        storageHistory.noteInAddStorageHistory(i.getValue().getEntryDate(),product.getQuantity(),storageHistory);

                        break;
                    }

                    else if(product.getLocation().equals(i.getValue().getLocation()) && i.getValue().getQuantity()==1000)
                    {
                        throw new LocationException("Location "+i.getValue().getLocation().fullInfo(i.getValue().getQuantity())+" is full."
                                +"\nPlease choose another location.");
                    }


                    else if(product.getLocation().equals(i.getValue().getLocation()) && product.getQuantity()+i.getValue().getQuantity()>1000)
                   {
                            throw new LocationException("You can add only "+(1000-i.getValue().getQuantity())+i.getValue().getUnit()
                            +" \""+product.getProductName()+"\""
                            +"\nat location"+i.getValue().getLocation().fullInfo(i.getValue().getQuantity())+".");
                   }

                }
            }

            if(!hasMatch)
            {
                for(Map.Entry<Location,Product> i: productList.entrySet())
                {
                    if(product.getLocation().equals(i.getKey()))
                    {
                        throw new LocationException("The location you want to place product \""+product.getProductName()+"\" is currently occupied!"
                                +"\nPlease choose another location.");
                    }
                }

                productList.put(product.getLocation(),product);

                storageHistory.noteInAddStorageHistory(product.getEntryDate(),product.getQuantity(),storageHistory);
            }

        }
    }


}