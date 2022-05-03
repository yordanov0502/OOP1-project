package bg.tu_varna.sit;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface XmlAccess {
    void saveToFile(Warehouse warehouse, String fileName)throws JAXBException, IOException;
    Warehouse loadFromFile(String fileName)throws JAXBException, IOException;
}
