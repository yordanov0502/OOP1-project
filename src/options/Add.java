package options;

import bg.tu_varna.sit.Location;
import bg.tu_varna.sit.Product;
import еxceptions.LocationException;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Add extends Component implements bg.tu_varna.sit.Add{

    @Override
    public void add(Product product,Map<Location,Product> productList) throws LocationException
    {
        boolean hasMatch = false;

        if (productList.isEmpty())
        {
            productList.put(product.getLocation(),product);
        }

        else
        {
            for(Map.Entry<Location, Product> i: productList.entrySet())
            {
                if(product.equals(i.getValue()))
                {
                    if(i.getValue().getQuantity()==1000)
                    {
                        throw new LocationException("Location "+i.getValue().getLocation().fullInfo(i.getValue().getQuantity())+" is full."
                                +"\nPlease choose another location.");
                    }

                    else if(product.getQuantity()+i.getValue().getQuantity()<=1000)
                    {
                        double updateQuantity = product.getQuantity() + i.getValue().getQuantity();
                        i.getValue().setQuantity(updateQuantity);
                        hasMatch = true;
                        JOptionPane.showMessageDialog(this,"Product \""+product.getProductName()+"\" will be added at location \""+i.getValue().getLocation().toString()+"\"","Information",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }

                    else
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
            }

        }
    }

}