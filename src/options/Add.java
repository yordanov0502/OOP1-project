package options;

import bg.tu_varna.sit.Location;
import bg.tu_varna.sit.Product;
import еxceptions.LocationException;

import java.util.Map;

public class Add implements bg.tu_varna.sit.Add{

    @Override
    public void add(Product product,Map<Location,Product> productList) throws LocationException
    {
        boolean hasMatch = false;

        if (productList.isEmpty())
        {
            product.getLocation().setCapacity(product.getQuantity());
            productList.put(product.getLocation(),product);
        }

        else
        {
            for(Map.Entry<Location, Product> i: productList.entrySet())
            {
                if(product.equals(i.getValue()))
                {
                    if(i.getValue().getLocation().getCapacity()==1000)
                    {
                        throw new LocationException("Location "+i.getValue().getLocation().shortInfo()+" is full."
                                +"\nPlease choose another location.");
                    }

                    else if(product.getQuantity()+i.getKey().getCapacity()<=1000)
                    {
                        double fillCapacity = product.getQuantity() + i.getKey().getCapacity();
                        i.getValue().setQuantity(fillCapacity);
                        i.getValue().getLocation().setCapacity(fillCapacity);//////////tuk se updeitva i kapaciteta na klucha poneje klucha vshustnost e atribut na produkta, kojto vsuhstnost e value v mapa
                        hasMatch = true;
                        break;
                    }
                    else {throw new LocationException("You can add only "+(1000-i.getValue().getLocation().getCapacity())+i.getValue().getUnit()
                            +" \""+product.getProductName()+"\""
                            +"\nat location"+i.getValue().getLocation().shortInfo()+".");}

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
                product.getLocation().setCapacity(product.getQuantity());
                productList.put(product.getLocation(),product);
            }

        }
    }



}