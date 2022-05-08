package bg.tu_varna.sit;

import org.joda.time.LocalDate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StorageHistory {

    private static double addedQuantity = 0;
    private static double removedQuantity = 0;

    private Map<LocalDate, List<Product>> addedProducts = new LinkedHashMap<>();
    private Map<LocalDate, List<Product>> removedProducts = new LinkedHashMap<>();

    public StorageHistory(){}

    public static double getAddedQuantity() {
        return addedQuantity;
    }

    public static void setAddedQuantity(double addedQuantity) {
        StorageHistory.addedQuantity = addedQuantity;
    }

    public static double getRemovedQuantity() {
        return removedQuantity;
    }

    public static void setRemovedQuantity(double removedQuantity) {
        StorageHistory.removedQuantity = removedQuantity;
    }

    public Map<LocalDate, List<Product>> getAddedProducts() {
        return addedProducts;
    }

    public void setAddedProducts(Map<LocalDate, List<Product>> addedProducts) {
        this.addedProducts = addedProducts;
    }

    public Map<LocalDate, List<Product>> getRemovedProducts() {
        return removedProducts;
    }

    public void setRemovedProducts(Map<LocalDate, List<Product>> removedProducts) {
        this.removedProducts = removedProducts;
    }
}