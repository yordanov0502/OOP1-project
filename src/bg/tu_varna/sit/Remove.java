package bg.tu_varna.sit;

import еxceptions.LocationException;
import еxceptions.MissingProductException;
import еxceptions.QuantityException;

import java.util.Map;

public interface Remove {
    void remove(String productName,double quantity, Map<Location,Product> productList) throws MissingProductException;
}