package bg.tu_varna.sit;

import еxceptions.OptionException;

import java.util.Scanner;

public class StorageSystem {

    private static StorageSystem instance;
    private Warehouse warehouse;

    private StorageSystem(){
        warehouse = new Warehouse();
    }

    public static StorageSystem getInstance()
    {
        if(instance==null) {instance = new StorageSystem();}
        return instance;
    }

    public void launch()
    {
        Menu menu = new GeneralMenu(warehouse);

        Scanner scanner = new Scanner(System.in);

        String input;
        String[] options;

        while(true)
        {
            System.out.print('>');
            input = scanner.nextLine();

            if(input.equals("exit")) break;

            options = input.split(" ", 2);

            try
            {
                menu.start(options);
            }
            catch (OptionException customException)
            {
                System.out.println(customException.getMessage());
            }
        }
    }

}