package bg.tu_varna.sit;

import еxceptions.MissingProductException;

import java.util.Map;

public interface Remove {
    boolean remove(String productName,double quantity, Map<Location,Product> productList) throws MissingProductException;
}