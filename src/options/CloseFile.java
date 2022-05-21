package options;

import bg.tu_varna.sit.Warehouse;

public class CloseFile implements bg.tu_varna.sit.CloseFile {

    @Override
    public void closeFile(Warehouse warehouse)
    {
        warehouse.getProductList().clear();
        warehouse.setProductList(warehouse.getProductList());

        warehouse.getStorageHistory().getAddedQuantity().clear();
        warehouse.getStorageHistory().getRemovedQuantity().clear();
        warehouse.getStorageHistory().setAddedQuantity(warehouse.getStorageHistory().getAddedQuantity());
        warehouse.getStorageHistory().setRemovedQuantity(warehouse.getStorageHistory().getRemovedQuantity());
    }
}