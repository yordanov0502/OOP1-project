package bg.tu_varna.sit;

import dialogs.DialogAdd;
import еxceptions.OptionException;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class GeneralMenu implements Menu {

    private Warehouse warehouse;
    private String file;
    private boolean isFileOpened=false;


    public GeneralMenu(Warehouse warehouse) {
        this.warehouse=warehouse;
        file = null;
    }

    @Override
    public void start(String[] options)throws OptionException
    {
        switch (options[0])
        {
            case "open": open(options);break;

            case "close": close();break;

            case "save": save();break;

            case "saveas": saveAs(options);break;

            case "help": help();break;

            case "print": print();break;

            case "add": addProduct();break;

            //case "remove"

            //case "log"

            case "clean": clean();break;

            default: throw new OptionException("Invalid Option!");
        }
    }

    private void open(String[] parameters)throws OptionException
    {
        if(parameters.length!=2) {throw new OptionException("Incorrect parameters!");}

        OpenFile openFile = new options.OpenFile();
        String fileName = parameters[1].replace("\"", "");
        file = fileName;

        boolean flag=true;
        try
        {
            openFile.open(warehouse, fileName);
        }
        catch (IOException | JAXBException e)
        {
            flag=false;
            System.out.println("File has not been found!");
        }

        if(flag) {System.out.println("File has opened successfully.");isFileOpened=true;}
    }

    private void close()
    {
        if(isFileOpened)
        {
            CloseFile closeFile = new options.CloseFile();
            closeFile.closeFile(warehouse);
            System.out.println("File has been closed successfully.");
            isFileOpened = false;
        }
        else {System.out.println("Cannot close a file, which has not been opened!");}
    }

    private void save()
    {
        if(isFileOpened)
        {
            SaveAs saveAs = new options.SaveAs();

            if (file != null)
            {
                saveAs.saveAs(warehouse, file);
                System.out.println("File has been saved successfully.");
                isFileOpened = true;
            }
            else
            {
                System.out.println("File has not been found!");
            }
        }
        else {System.out.println("Cannot save a file, which has not been opened!");}
    }

    private void saveAs(String[] parameters)throws OptionException
    {
        if(parameters.length!=2) {throw new OptionException("Incorrect parameters!");}

        SaveAs saveAs = new options.SaveAs();
        String fileName = parameters[1].replace("\"", "");
        file = fileName;
        saveAs.saveAs(warehouse, fileName);
        System.out.println("File has been saved successfully.");
        isFileOpened=true;
    }

    public void help()
    {
        System.out.println("The following commands are supported:");
        Help help = new options.Help();
        for (String i: help.allOptions())
        {
            System.out.println(i);
        }
    }

    private void print()
    {
        Print print = new options.Print();
        print.print(warehouse.getProductList());

        System.out.println(warehouse.getProductList());
    }

    private void addProduct()
    {
        DialogAdd dialog = new DialogAdd(warehouse);
        dialog.setContentPane(dialog.getPanelAdd());
        dialog.setTitle("Please add product");
        dialog.setBounds(700, 200, 500, 570);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    ///////////////////

    ///////////////////

    private void clean()
    {
        Clean clean = new options.Clean();
        clean.clean(warehouse.getProductList());
    }

}