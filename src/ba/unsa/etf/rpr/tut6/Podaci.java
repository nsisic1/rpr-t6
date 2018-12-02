package ba.unsa.etf.rpr.tut6;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalDate.now;

public class Podaci {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    static boolean isImeValid(String ime) {
        return (ime.length() > 0 && ime.length() <= 20 && (ime.matches(".*[a-z].*") || ime.matches(".*[A-Z].*")));
        // Po uslovu zadatok u zadatku
    }

    static boolean isPrezimeValid(String prezime) {
        return isImeValid(prezime);
    }

    static boolean isBrojIndeksaValid(String indeks) {
        return indeks.length() == 5 && indeks.matches("[0-9]*");
    }

    static boolean isJmbgValid(String jmbg, LocalDate datum) {
        /*System.out.println("Pocetak jmbg provjere");
        System.out.println(jmbg);
        System.out.println(jmbg.matches("[0-9]*"));
        System.out.println(datum == null);*/
        if (jmbg.length() != 13 || !jmbg.matches("[0-9]*") || datum == null) {
            //System.out.println("Hmm");
            return  false;
        }
        int[] brojevi = new int[13];
        for (int i = 0; i < 13; i++) {
            brojevi[i] = jmbg.charAt(i) - '0';
        }
        //System.out.println("Do datuma");
        // Provjera datuma
        int dan = brojevi[0] * 10 + brojevi[1];
        int mjesec = brojevi[2] * 10 + brojevi[3];
        int godina = 1000 + 100 * brojevi[4] + 10 * brojevi[5] + brojevi[6];
        //System.out.println(dan + " " + mjesec + " " + godina);
        if (dan != datum.getDayOfMonth() || mjesec != datum.getMonthValue() || godina != datum.getYear()) {
            return false;
        }
        //System.out.println("Do regije");
        // Provjera regije
        int politickaRegija = 10 * brojevi[7] + brojevi[8];
        if (politickaRegija < 0 || politickaRegija > 96) {
            return false;
        }
        // if (zenica, sarajevo ...
        //System.out.println("Do kontrolne");
        // Kontrolna cifra
        int kontrolnaCifra = 11 - ((7*(brojevi[0] + brojevi[6]) + 6*(brojevi[1] + brojevi[7]) + 5*(brojevi[2] + brojevi[8])
                + 4*(brojevi[3] + brojevi[9]) + 3*(brojevi[4] + brojevi[10]) + 2*(brojevi[5] + brojevi[11])) % 11);
        if (kontrolnaCifra > 9) {
            kontrolnaCifra = 0;
        }
        return kontrolnaCifra == brojevi[12];
    }

    static boolean isDateValid(LocalDate datum) {
        if (datum == null) {
            return false;
        }
        return datum.compareTo(now()) < 1;
    }

    static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.find();
    }

    static boolean isMjestoValid(String mjesto) {
        return !mjesto.isEmpty();
    }

    static boolean isTelefonValid(String broj) {
        return broj.matches("\\d{3}-\\d{3} \\d{3}");
    }

}
