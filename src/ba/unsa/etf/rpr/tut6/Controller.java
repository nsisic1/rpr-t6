package ba.unsa.etf.rpr.tut6;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.function.Function;

public class Controller {
    @FXML
    public TextField imeField;
    @FXML
    public TextField prezimeField;
    @FXML
    public TextField brojIndeksaField;
    @FXML
    public TextField jmbgField;
    @FXML
    public DatePicker dateField;
    @FXML
    public ComboBox mjestoBox;
    @FXML
    public TextField adresaField;
    @FXML
    public TextField telefonField;
    @FXML
    public TextField emailField;
    @FXML
    public ChoiceBox odsjekBox;
    @FXML
    public ChoiceBox godinaBox;
    @FXML
    public ChoiceBox ciklusBox;
    @FXML
    public RadioButton redovanBtn;
    @FXML
    public RadioButton redSamofinancirajuciBtn;
    @FXML
    public CheckBox borackeKategorijeCheck;
    @FXML
    public Button potvrdaButton;
    public ToggleGroup statusFinansiranja;

    // BoolWrapper validnoIme, validnoPrezime, validanIndeks, validanJmbg, validanDatum, validanEmail; TODO zabiljezi: ne
    private BoolWrapper validnoIme = new BoolWrapper();
    private BoolWrapper validnoPrezime = new BoolWrapper();
    private BoolWrapper validanIndeks = new BoolWrapper();
    private BoolWrapper validanJmbg = new BoolWrapper();
    private BoolWrapper validanDatum = new BoolWrapper();
    private BoolWrapper validanEmail = new BoolWrapper();
    private BoolWrapper validanTelefon = new BoolWrapper();

    private SimpleStringProperty ime;
    private SimpleStringProperty prezime;
    private SimpleStringProperty brojIndeksa;
    private SimpleStringProperty jmbg;
    private SimpleStringProperty mjesto;
    private SimpleStringProperty adresa;
    private SimpleStringProperty telefon;
    private SimpleStringProperty email;
    private SimpleStringProperty odsjek;
    private SimpleStringProperty godina;
    private SimpleStringProperty ciklus;

    public Controller() {
        ime = new SimpleStringProperty("");
        prezime = new SimpleStringProperty("");
        brojIndeksa = new SimpleStringProperty("");
        jmbg = new SimpleStringProperty("");
        adresa = new SimpleStringProperty("");
        telefon = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        odsjek = new SimpleStringProperty("");
        godina = new SimpleStringProperty("");
        ciklus = new SimpleStringProperty("");
        mjesto = new SimpleStringProperty("");
    }

    private String getIme() {
        return ime.get();
    }

    private String getPrezime() {
        return prezime.get();
    }

    private String getBrojIndeksa() {
        return brojIndeksa.get();
    }

    private String getJmbg() {
        return jmbg.get();
    }

    private String getMjesto() {
        return mjestoBox.getValue().toString();
    }

    private String getAdresa() {
        return adresa.get();
    }

    private String getTelefon() {
        return telefon.get();
    }

    private String getEmail() {
        return email.get();
    }

    private String getOdsjek() {
        return odsjekBox.getValue().toString();
    }

    private String getGodina() {
        return godinaBox.getValue().toString();
    }

    private String getCiklus() {
        return ciklusBox.getValue().toString();
    }


    @FXML
    public void initialize() {
        imeField.textProperty().bindBidirectional(ime);
        prezimeField.textProperty().bindBidirectional(prezime);
        brojIndeksaField.textProperty().bindBidirectional(brojIndeksa);
        jmbgField.textProperty().bindBidirectional(jmbg);
        //mjestoBox.valueProperty().bindBidirectional(mjesto);
        adresaField.textProperty().bindBidirectional(adresa);
        telefonField.textProperty().bindBidirectional(telefon);
        emailField.textProperty().bindBidirectional(email);

        initField(imeField, validnoIme, Podaci::isImeValid); // TODO zabiljezi slanje metode kao argument
        initField(prezimeField, validnoPrezime, Podaci::isPrezimeValid);
        initField(brojIndeksaField, validanIndeks, Podaci::isBrojIndeksaValid);
        /*initIme();
        initPrezime();
        initBrojIndeksa();*/
        initJMBG(dateField);
        initDatum();
        initField(emailField, validanEmail, Podaci::isEmailValid);
        //initEmail();
        initField(telefonField, validanTelefon, Podaci::isTelefonValid);

    }

    private void initField(TextField field, BoolWrapper valid, Function<String, Boolean> isValid) {
        valid.set(false);
        field.getStyleClass().add("poljeNijeIspravno");
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (isValid.apply(n)) {
                    field.getStyleClass().removeAll("poljeNijeIspravno");
                    field.getStyleClass().add("poljeIspravno");
                    valid.set(true);
                } else {
                    field.getStyleClass().removeAll("poljeIspravno");
                    field.getStyleClass().add("poljeNijeIspravno");
                    valid.set(false);
                }
            }
        });
    }

    private void initJMBG(DatePicker d) {
        validanJmbg.set(false);
        jmbgField.getStyleClass().add("poljeNijeIspravno");
        jmbgField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                //LocalDate datum = d.getValue();
                // System.out.println(datum);
                updateJmbgFieldClass();
            }
        });
    }

    private void initDatum() {
        validanDatum.set(false);
        dateField.getStyleClass().add("poljeNijeIspravno");
        dateField.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate o, LocalDate n) {
                //System.out.println(dateField.getValue());
                if (Podaci.isDateValid(dateField.getValue())) {
                    dateField.getStyleClass().removeAll("poljeNijeIspravno");
                    dateField.getStyleClass().add("poljeIspravno");
                    validanDatum.set(true);
                    // Vrsimo provjeru JMBG
                    updateJmbgFieldClass();
                } else {
                    dateField.getStyleClass().removeAll("poljeIspravno");
                    dateField.getStyleClass().add("poljeNijeIspravno");
                    validanDatum.set(false);
                }
            }
        });
        // TODO: check out https://stackoverflow.com/questions/32346893/javafx-datepicker-not-updating-value ?
    }

    private void updateJmbgFieldClass() {
        if (Podaci.isJmbgValid(getJmbg(), dateField.getValue())) {
            jmbgField.getStyleClass().removeAll("poljeNijeIspravno");
            jmbgField.getStyleClass().add("poljeIspravno");
            validanJmbg.set(true);
        } else {
            jmbgField.getStyleClass().removeAll("poljeIspravno");
            jmbgField.getStyleClass().add("poljeNijeIspravno");
            validanJmbg.set(false);
        }
    }


    private boolean formularValidan() {
        return validnoIme.get() && validnoPrezime.get() && validanIndeks.get() && validanJmbg.get() && validanDatum.get() &&
                validanEmail.get() && validanTelefon.get();
    }

    public void potvrdiClick(ActionEvent actionEvent) {
        if (formularValidan()) {
            ispisiPodatke();
        } else {
            // Dijaloski prozor da forma nije validna
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid form");
            alert.setHeaderText("Formular nije validan!");
            alert.setContentText("Ispravite ili unesite polja sa crvenom bojom.");
            alert.show();
        }
    }

    private void ispisiPodatke() {
        System.out.println("**** Podaci o studentu ****");
        // Osnovni podaci
        System.out.println("----------------");
        System.out.println("|Osnovni podaci|");
        System.out.println("----------------");
        System.out.println("Ime i prezime: " + getIme() + " " + getPrezime());
        System.out.println("Broj indeksa: " + getBrojIndeksa());
        // Detaljni licni podaci
        System.out.println("-----------------------");
        System.out.println("|Detaljni lični podaci|");
        System.out.println("-----------------------");
        System.out.println("JMBG: " + getJmbg());
        System.out.println("Datum rođenja: " + dateField.getValue().toString());
        System.out.println("Mjesto rođenja: " + getMjesto());
        // Kontakt podaci
        System.out.println("----------------");
        System.out.println("|Kontakt podaci|");
        System.out.println("----------------");
        System.out.println("Adresa: " + getAdresa());
        System.out.println("Telefon: " + getTelefon());
        System.out.println("Email: " + getEmail());
        // Podaci o studiju
        System.out.println("------------------");
        System.out.println("|Podaci o studiju|");
        System.out.println("------------------");
        System.out.println("Odsjek: " + getOdsjek());
        System.out.println("Godina studija: " + getGodina());
        System.out.println("Ciklus: " + getCiklus());
        System.out.println(((RadioButton) statusFinansiranja.getSelectedToggle()).getText());
        if (borackeKategorijeCheck.isSelected()) {
            System.out.println("Pripada posebnim boračkim kategorijama.");
        } else {
            System.out.println("Ne pripada posebnim boračkim kategorijama.");
        }
        System.out.println("\n");
    }

    // TODO: ideatestovi

}
