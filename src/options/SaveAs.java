package options;

import bg.tu_varna.sit.Warehouse;
import bg.tu_varna.sit.XmlAccess;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class SaveAs implements bg.tu_varna.sit.SaveAs {

    @Override
    public void saveAs(Warehouse warehouse, String fileName)
    {
        XmlAccess xmlAccess = new xml.XmlAccess();

        try
        {
            xmlAccess.saveToFile(warehouse, fileName);
        }
        catch (JAXBException | IOException ex)
        {
            System.out.println("An error has occurred while saving!");
        }
    }
}