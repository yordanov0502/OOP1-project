package options;

import bg.tu_varna.sit.Location;
import bg.tu_varna.sit.Product;
import bg.tu_varna.sit.RemovedProduct;
import еxceptions.MissingProductException;

import java.awt.*;
import java.util.*;

import org.joda.time.LocalDate;

import javax.swing.*;

public class Remove extends Component implements bg.tu_varna.sit.Remove{
    @Override
    public void remove(String productName, double quantity, Map<Location, Product> productList) throws MissingProductException
    {
        Map<LocalDate, Product> unsortedMap = new LinkedHashMap<>();

        double oldQuantityLastCase=0;

        for(Map.Entry<Location, Product> i: productList.entrySet())
        {
            if(i.getValue().getProductName().equals(productName))
            {
                unsortedMap.put(i.getValue().getExpiryDate(), i.getValue());
            }
        }

        if(isNullOrEmptyMap(unsortedMap))
        {
            throw new MissingProductException("Product with name \""+productName+"\" doesn't exist in the warehouse!");
        }



        else
        {
            //https://howtodoinjava.com/java/sort/java-sort-map-by-key/

            //LinkedHashMap preserve the ordering of elements in which they are inserted
            Map<Location, Product> sortedMap = new LinkedHashMap<>();

            unsortedMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(x -> sortedMap.put(x.getValue().getLocation(), x.getValue()));


            Map<Location, Product> removedProducts = new LinkedHashMap<>();

            RemovedProduct removedProduct =  new RemovedProduct();
            RemovedProduct updatedProduct = new RemovedProduct();

            //flagove za razlichnite situacii po premahvane na produkti
            boolean flag1=false;
            boolean flag2=false;

            double totalQuantity=0;

            for(Map.Entry<Location, Product> i: sortedMap.entrySet())
            {
                totalQuantity+=i.getValue().getQuantity();
            }



            for(Map.Entry<Location, Product> i: sortedMap.entrySet())
            {
                if(!flag1)
                {
                    if (quantity == i.getValue().getQuantity())
                    {
                        removedProduct.setProduct(i.getValue());
                        productList.remove(i.getKey());

                        System.out.println("------------------The following product has been removed from the warehouse------------------");
                        System.out.println(removedProduct.removeInfo());
                        System.out.println("---------------------------------------------------------------------------------------------");

                        break;
                    }

                    else if (quantity < i.getValue().getQuantity())
                    {
                        double oldQuantity=i.getValue().getQuantity();
                        double newQuantity = oldQuantity - quantity;
                        i.getValue().setQuantity(newQuantity);

                        //productList.computeIfPresent(i.getKey(), (k, v) -> i.getValue());
                        productList.put(i.getKey(),i.getValue());
                        System.out.println("------------------The quantity of the following product has been reduced("+oldQuantity+"->"+newQuantity+")---------------------");
                        System.out.println(productList.get(i.getKey()).removeInfo());
                        System.out.println("---------------------------------------------------------------------------------------------");

                        break;
                    }

                    else if (quantity == totalQuantity)
                    {
                        productList.keySet().removeAll(sortedMap.keySet());

                        System.out.println("------------------The following products have been removed from the warehouse----------------");
                        for (Map.Entry<Location, Product> j : sortedMap.entrySet())
                        {
                            System.out.println(j.getValue().removeInfo());
                        }
                        System.out.println("---------------------------------------------------------------------------------------------");

                        break;
                    }

                    else if (quantity > totalQuantity)
                    {
                        int response = JOptionPane.showConfirmDialog(this, "The total quantity of the product is " + totalQuantity + "\nHowever, do you want to remove the left quantity?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (response == JOptionPane.YES_OPTION)
                        {
                            productList.keySet().removeAll(sortedMap.keySet());

                            System.out.println("------------------The following products have been removed from the warehouse----------------");
                            for (Map.Entry<Location, Product> j : sortedMap.entrySet())
                            {
                                System.out.println(j.getValue().removeInfo());
                            }
                            System.out.println("---------------------------------------------------------------------------------------------");
                            break;
                        }
                        else {break;}
                    }

                    else {flag1=true;}
                }

                if(flag1)
                {
                    if(quantity>0)
                    {
                        if(quantity>i.getValue().getQuantity())
                        {
                            removedProducts.put(i.getKey(),i.getValue());
                            quantity-=i.getValue().getQuantity();
                        }

                        else if(quantity<i.getValue().getQuantity())
                        {
                            oldQuantityLastCase=i.getValue().getQuantity();
                            i.getValue().setQuantity(i.getValue().getQuantity()-quantity);
                            updatedProduct.setProduct(i.getValue());
                            quantity=0;
                            flag2=true;
                            break;
                        }

                        else if(quantity==i.getValue().getQuantity())
                        {
                            removedProducts.put(i.getKey(),i.getValue());
                            quantity=0;
                            break;
                        }
                    }
                }


            }

            if(flag1)
            {
                productList.keySet().removeAll(removedProducts.keySet());

                System.out.println("------------------The following products have been removed from the warehouse----------------");
                for (Map.Entry<Location, Product> i : removedProducts.entrySet())
                {
                    System.out.println(i.getValue().removeInfo());
                }
                System.out.println("---------------------------------------------------------------------------------------------");

                if(flag2)
                {
                    double newQuantity = updatedProduct.getProduct().getQuantity();
                    productList.put(updatedProduct.getProduct().getLocation(),updatedProduct.getProduct());
                    System.out.println("------------------The quantity of the following product has been reduced("+oldQuantityLastCase+"->"+newQuantity+")---------------------");
                    System.out.println(updatedProduct.getProduct().removeInfo());
                    System.out.println("---------------------------------------------------------------------------------------------");
                }
            }


        }

    }

    public static boolean isNullOrEmptyMap(Map <? , ?> map)
    {
        return (map == null || map.isEmpty());
    }

}
