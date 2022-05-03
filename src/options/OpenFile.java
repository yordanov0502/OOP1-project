package options;

import bg.tu_varna.sit.Warehouse;
import bg.tu_varna.sit.XmlAccess;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class OpenFile implements bg.tu_varna.sit.OpenFile {

    @Override
    public void open(Warehouse warehouse ,String fileName) throws IOException, JAXBException
    {
        XmlAccess xmlAccess = new xml.XmlAccess();
        Warehouse loadedWarehouse = xmlAccess.loadFromFile(fileName);
        warehouse.setProductList(loadedWarehouse.getProductList());
    }
}