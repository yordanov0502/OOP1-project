package bg.tu_varna.sit;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface OpenFile {
    void open(Warehouse schedule ,String fileName) throws IOException, JAXBException;
}
