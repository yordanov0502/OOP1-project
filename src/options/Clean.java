package options;

import bg.tu_varna.sit.Location;
import bg.tu_varna.sit.Product;

import java.util.LinkedHashMap;
import java.util.Map;

import bg.tu_varna.sit.StorageHistory;
import org.joda.time.LocalDate;

public class Clean implements bg.tu_varna.sit.Clean {


    @Override
    public void clean(Map<Location,Product> productList, StorageHistory storageHistory)
    {
        if(productList == null || productList.isEmpty()) {System.out.println("There are no products in the warehouse!");}

        else
        {
            LocalDate currDate = LocalDate.now(); //Current date
            Map<Location, Product> cleanedProducts = new LinkedHashMap<>();

            java.time.LocalDate currentNormalDate = java.time.LocalDate.now();

            for (Map.Entry<Location, Product> i : productList.entrySet())
            {
                if (currDate.compareTo(i.getValue().getExpiryDate()) > 0 || currDate.compareTo(i.getValue().getExpiryDate().minusDays(7)) >= 0)
                {
                    cleanedProducts.put(i.getKey(), i.getValue());

                    storageHistory.noteInStorageHistory(currentNormalDate,i.getValue().getQuantity(),storageHistory);

                    /////////

                    ////////
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