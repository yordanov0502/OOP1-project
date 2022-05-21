package options;

import bg.tu_varna.sit.Location;
import bg.tu_varna.sit.Product;
import bg.tu_varna.sit.RemovedProduct;
import bg.tu_varna.sit.StorageHistory;
import еxceptions.MissingProductException;

import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class Remove extends Component implements bg.tu_varna.sit.Remove{
    @Override
    public boolean remove(String productName, double quantity, Map<Location, Product> productList, StorageHistory storageHistory) throws MissingProductException
    {
        List<Product> localList = new ArrayList<>();
        LocalDate currDate = LocalDate.now(); //Current date

        double oldQuantityLastCase=0;

        for(Map.Entry<Location, Product> i: productList.entrySet())
        {
            if(i.getValue().getProductName().equals(productName))
            {
                localList.add(i.getValue());
            }
        }

        if(localList.isEmpty())
        {
            throw new MissingProductException("Product with name \""+productName+"\" doesn't exist in the warehouse!");
        }


        else
        {
            localList.sort(Comparator.comparing(Product::getExpiryDate));


            Map<Location, Product> removedProducts = new LinkedHashMap<>();

            RemovedProduct removedProduct =  new RemovedProduct();
            RemovedProduct updatedProduct = new RemovedProduct();

            //flagove za razlichnite situacii po premahvane na produkti
            boolean flag1=false;
            boolean flag2=false;

            double totalQuantity=0;


            for (Product product : localList)
            {
                totalQuantity += product.getQuantity();
            }


            for(Product currProduct : localList)
            {
                if(!flag1)
                {
                    if (quantity == currProduct.getQuantity())
                    {
                        storageHistory.noteInRemoveStorageHistory(currDate,currProduct.getQuantity(),storageHistory);

                        removedProduct.setProduct(currProduct);
                        productList.remove(currProduct.getLocation());

                        System.out.println("------------------The following product has been removed from the warehouse------------------");
                        System.out.println(removedProduct.removeInfo());
                        System.out.println("---------------------------------------------------------------------------------------------");

                        break;
                    }

                    else if (quantity < currProduct.getQuantity())
                    {
                        storageHistory.noteInRemoveStorageHistory(currDate,quantity,storageHistory);

                        double oldQuantity = currProduct.getQuantity();
                        double newQuantity = oldQuantity - quantity;
                        currProduct.setQuantity(newQuantity);

                        //productList.computeIfPresent(i.getKey(), (k, v) -> i.getValue());
                        productList.put(currProduct.getLocation(),currProduct);
                        System.out.println("------------------The quantity of the following product has been reduced("+oldQuantity+"->"+newQuantity+")---------------------");
                        System.out.println(productList.get(currProduct.getLocation()).removeInfo());
                        System.out.println("---------------------------------------------------------------------------------------------");

                        break;
                    }

                    else if (quantity == totalQuantity)
                    {
                        productList.keySet().removeAll(transferProducts(localList).keySet());
                        storageHistory.noteInRemoveStorageHistory(currDate,totalQuantity,storageHistory);

                        System.out.println("------------------The following products have been removed from the warehouse----------------");
                        for (Product curProduct : localList)
                        {
                            System.out.println(curProduct.removeInfo());
                        }
                        System.out.println("---------------------------------------------------------------------------------------------");

                        break;
                    }

                    else if (quantity > totalQuantity)
                    {
                        int response = JOptionPane.showConfirmDialog(this, "The total quantity of the product \""+currProduct.getProductName()+"\" is " + totalQuantity + "\nHowever, do you want to remove the left quantity?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (response == JOptionPane.YES_OPTION)
                        {
                            productList.keySet().removeAll(transferProducts(localList).keySet());
                            storageHistory.noteInRemoveStorageHistory(currDate,totalQuantity,storageHistory);

                            System.out.println("------------------The following products have been removed from the warehouse----------------");
                            for (Product curProduct : localList)
                            {
                                System.out.println(curProduct.removeInfo());
                            }
                            System.out.println("---------------------------------------------------------------------------------------------");
                            break;
                        }
                        else {return true;}
                    }

                    else {flag1=true;}
                }

                if(flag1)
                {

                    if(quantity>0)
                    {
                        if(quantity>currProduct.getQuantity())
                        {
                            storageHistory.noteInRemoveStorageHistory(currDate,currProduct.getQuantity(),storageHistory);

                            removedProducts.put(currProduct.getLocation(), currProduct);
                            quantity-=currProduct.getQuantity();
                        }

                        else if(quantity<currProduct.getQuantity())
                        {
                            storageHistory.noteInRemoveStorageHistory(currDate,quantity,storageHistory);

                            oldQuantityLastCase=currProduct.getQuantity();
                            currProduct.setQuantity(oldQuantityLastCase-quantity);
                            updatedProduct.setProduct(currProduct);
                            quantity=0;
                            flag2=true;
                            break;
                        }

                        else if(quantity==currProduct.getQuantity())
                        {
                            storageHistory.noteInRemoveStorageHistory(currDate,currProduct.getQuantity(),storageHistory);

                            removedProducts.put(currProduct.getLocation(), currProduct);
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
                    System.out.println("------------The quantity of the following product has been reduced("+oldQuantityLastCase+"->"+newQuantity+")-------------");
                    System.out.println(updatedProduct.getProduct().removeInfo());
                    System.out.println("---------------------------------------------------------------------------------------------");
                }
            }


        }
     return false;
    }



    private Map<Location,Product> transferProducts(List<Product> localList)
    {
        Map<Location, Product> tempMap = new LinkedHashMap<>();

        for(Product currProduct : localList)
        {
            tempMap.put(currProduct.getLocation(),currProduct);
        }
        return tempMap;
    }


}