package bg.tu_varna.sit;

import еxceptions.LocationException;

import java.util.Map;

public interface Add
{
    void add(Product product, Map<Location,Product> productList,StorageHistory storageHistory) throws LocationException;
}
