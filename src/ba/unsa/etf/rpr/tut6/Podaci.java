package ba.unsa.etf.rpr.tut6;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalDate.now;

public class Podaci {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    static boolean isImeValid(String ime) {
        return (ime.length() > 0 && ime.length() <= 20 && (ime.matches(".*[a-z].*") || ime.matches(".*[A-Z].*")));
    }

    static boolean isPrezimeValid(String prezime) {
        return isImeValid(prezime);
    }

    static boolean isBrojIndeksaValid(String indeks) {
        return indeks.length() == 5 && indeks.matches("[0-9]*");
    }


    static boolean isDateValid(LocalDate datum) {
        return datum.compareTo(now()) < 1;
        // jmbg?
    }

    static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.find();
    }

}
