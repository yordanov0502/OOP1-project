package xml;

import bg.tu_varna.sit.Warehouse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class XmlAccess implements bg.tu_varna.sit.XmlAccess {

    //marshal (write to) XML
    public void saveToFile(Warehouse warehouse, String fileName) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(Warehouse.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, /*Boolean.TRUE*/true);
        mar.marshal(warehouse, new File(fileName));
    }

    //unmarshal (read from) XML
    public Warehouse loadFromFile(String fileName) throws JAXBException, IOException
    {
        JAXBContext context = JAXBContext.newInstance(Warehouse.class);
        return (Warehouse) context.createUnmarshaller().unmarshal(new FileReader(fileName));
    }

}