package options;

import bg.tu_varna.sit.Location;
import bg.tu_varna.sit.Product;

import java.util.HashMap;
import java.util.Map;

public class Clean implements bg.tu_varna.sit.Clean {


    @Override
    public void clean(Map<Location,Product> productList) {

        if (productList.isEmpty()) {System.out.println("There are no products in the warehouse!");}

        else
        {
            org.joda.time.LocalDate currDate = org.joda.time.LocalDate.now(); //Current date
            Map<Location, Product> cleanedProducts = new HashMap<>();

            for (Map.Entry<Location, Product> i : productList.entrySet())
            {
                if (currDate.compareTo(i.getValue().getExpiryDate()) > 0 || currDate.compareTo(i.getValue().getExpiryDate().minusDays(7)) >= 0)
                {
                    cleanedProducts.put(i.getKey(), i.getValue());
                }
            }


            if (cleanedProducts.isEmpty()) {System.out.println("All products in the warehouse are up to date!");}

            else
            {
                productList.keySet().removeAll(cleanedProducts.keySet());

                System.out.println("------------------The following products have been cleaned from the warehouse----------------");
                for (Map.Entry<Location, Product> i : cleanedProducts.entrySet())
                {
                    System.out.println(i.getValue().removeInfo());
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
        }
    }
}