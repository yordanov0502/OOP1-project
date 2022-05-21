package options;

public class Help implements bg.tu_varna.sit.Help {

    private static final String[] options = {
            "open <file>\t\t\t\t\t\topens <file>",
            "close\t\t\t\t\t\t\tcloses currently opened file",
            "save\t\t\t\t\t\t\tsaves the currently open file",
            "saveas <file>\t\t\t\t\tsaves the currently opened file in <file>",
            "help\t\t\t\t\t\t\tprints this information",
            "exit\t\t\t\t\t\t\texits the program",
            "print\t\t\t\t\t\t\tprints information about available products in the warehouse",
            "add \t\t\t\t\t\t\tadds a new product to the warehouse in dialog mode",
            "remove\t\t\t\t\t\t\tremoves product from the warehouse in dialog mode",
            "log <from> <to>\t\t\t\t\tprints information about quantity changes in the warehouse between 2 dates",
            "clean\t\t\t\t\t\t\tcleans all products from the warehouse, which expiry date have passed or is about to pass (in 7 days)"
    };

    @Override
    public String[] allOptions() {
        return options;
    }
}
