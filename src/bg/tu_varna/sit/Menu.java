package bg.tu_varna.sit;

import еxceptions.OptionException;

public interface Menu {
    void start(String[] options)throws OptionException;
}