package options;

import bg.tu_varna.sit.Location;
import bg.tu_varna.sit.Product;

import java.util.HashMap;
import java.util.Map;

public class Print implements bg.tu_varna.sit.Print {
    @Override
    public void print(Map<Location,Product> productList)
    {
        Map<String,Double> availableProducts = new HashMap<>();

        if(isNullOrEmptyMap(productList))
        {
            System.out.println("There are no products in the warehouse!");
        }

        else
        {

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

            for(Map.Entry<String, Double> i: availableProducts.entrySet())
            {
                System.out.println("product= "+i.getKey()+"/ quantity= "+i.getValue());
            }

        }
    }

    public static boolean isNullOrEmptyMap(Map <? , ?> map)
    {
        return (map == null || map.isEmpty());
    }
}