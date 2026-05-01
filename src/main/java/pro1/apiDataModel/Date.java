package pro1.apiDataModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    public String value;

    public boolean isValid() {
        return value != null && !value.isEmpty();
    }

    public LocalDate toLocalDate() {
        var formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        return LocalDate.parse(value.split(" ")[0], formatter);
    }
}