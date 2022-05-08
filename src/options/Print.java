package options;

import bg.tu_varna.sit.Location;
import bg.tu_varna.sit.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class Print implements bg.tu_varna.sit.Print {
    @Override
    public void print(Map<Location,Product> productList)
    {
        if(productList == null || productList.isEmpty()) {System.out.println("There are no products in the warehouse!");}

        else
        {
            Map<String,Double> availableProducts = new LinkedHashMap<>();

            for(Map.Entry<Location, Product> i: productList.entrySet())
            {
                Product currProduct = i.getValue();

                if(availableProducts.containsKey(currProduct.getProductName()))
                {
                    Double oldValue = availableProducts.get(currProduct.getProductName());//podavam key,vzemam vzemam value-to mu
                    Double currValue = currProduct.getQuantity();
                    Double updateValue = oldValue + currValue;

                    availableProducts.put(currProduct.getProductName(), updateValue);
                }

                else
                {
                    availableProducts.put(currProduct.getProductName(),currProduct.getQuantity());
                }
            }

            System.out.println("\n-------------------------------------------------------");
            for(Map.Entry<String, Double> i: availableProducts.entrySet())
            {
                System.out.println("product= \""+i.getKey()+"\" / quantity= "+i.getValue());
            }
            System.out.println("-------------------------------------------------------\n");
        }
    }

}