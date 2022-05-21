package bg.tu_varna.sit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashMap;
import java.util.Map;

@XmlRootElement(name="Warehouse")
@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse {

    private Map<Location,Product> productList = new LinkedHashMap<>();

    private StorageHistory storageHistory = new StorageHistory();

    public Map<Location,Product> getProductList() {
        return productList;
    }

    public void setProductList(Map<Location, Product> productList) {this.productList = productList;}

    public StorageHistory getStorageHistory() {return storageHistory;}

    public void setStorageHistory(StorageHistory storageHistory) {this.storageHistory = storageHistory;}

    @Override
    public String toString() {
        return "productList->\n" + productList ;
    }
}