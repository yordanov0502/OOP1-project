package bg.tu_varna.sit;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@XmlRootElement(name = "StorageHistory")
@XmlType(propOrder = { "addedQuantity", "removedQuantity"})

public class StorageHistory {

    private Map<String, Double> addedQuantity = new LinkedHashMap<>();
    private Map<String, Double> removedQuantity = new LinkedHashMap<>();

    public StorageHistory(){}

    public Map<String, Double> getAddedQuantity() {
        return addedQuantity;
    }

    public void setAddedQuantity(Map<String, Double> addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    public Map<String, Double> getRemovedQuantity() {
        return removedQuantity;
    }

    public void setRemovedQuantity(Map<String, Double> removedQuantity) {
        this.removedQuantity = removedQuantity;
    }

    //izpolzvam metoda pri komanda Add
    public void noteInAddStorageHistory(org.joda.time.LocalDate entryDate,double quantity, StorageHistory storageHistory)
    {
        if(storageHistory.getAddedQuantity().containsKey(entryDate.toString()))
        {
            double oldQuantity = storageHistory.getAddedQuantity().get(entryDate.toString());
            storageHistory.getAddedQuantity().put(entryDate.toString(),oldQuantity+quantity);
        }

        else
        {
            storageHistory.getAddedQuantity().put(entryDate.toString(),quantity);
        }
    }

    //izpolzvam metoda pri komanda Clean i Remove
    public void noteInRemoveStorageHistory(LocalDate removedDate, double quantity, StorageHistory storageHistory)
    {
        if(storageHistory.getRemovedQuantity().containsKey(removedDate.toString()))
        {
            double oldQuantity = storageHistory.getRemovedQuantity().get(removedDate.toString());
            storageHistory.getRemovedQuantity().put(removedDate.toString(),oldQuantity+quantity);
        }

        else
        {
            storageHistory.getRemovedQuantity().put(removedDate.toString(),quantity);
        }
    }
}