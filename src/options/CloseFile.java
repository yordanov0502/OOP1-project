package options;

import bg.tu_varna.sit.Warehouse;

public class CloseFile implements bg.tu_varna.sit.CloseFile {

    @Override
    public void closeFile(Warehouse warehouse)
    {
        warehouse.getProductList().clear();
        warehouse.setProductList(warehouse.getProductList());
    }
}