package bg.tu_varna.sit;

import еxceptions.OptionException;

import java.time.LocalDate;

public interface Log {
    void validateFromDate() throws OptionException;
    void validateToDate() throws OptionException;
    void validateDatesInterval() throws OptionException;
    void log(LocalDate from, LocalDate to, StorageHistory storageHistory);
}