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

    // BoolWrapper validnoIme, validnoPrezime, validanIndeks, validanJmbg, validanDatum, validanEmail; TODO zabiljezi: ne
    private BoolWrapper validnoIme = new BoolWrapper();
    private BoolWrapper validnoPrezime = new BoolWrapper();
    private BoolWrapper validanIndeks = new BoolWrapper();
    private BoolWrapper validanJmbg = new BoolWrapper();
    private BoolWrapper validanDatum = new BoolWrapper();
    private BoolWrapper validanEmail = new BoolWrapper();

    private SimpleStringProperty ime;
    private SimpleStringProperty prezime;
    private SimpleStringProperty brojIndeksa;
    private SimpleStringProperty jmbg;
    private SimpleStringProperty email;

    public Controller() {
        ime = new SimpleStringProperty("");
        prezime = new SimpleStringProperty("");
        brojIndeksa = new SimpleStringProperty("");
        jmbg = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
    }

    public String getIme() {
        return ime.get();
    }

    public String getPrezime() {
        return prezime.get();
    }

    public String getBrojIndeksa() {
        return brojIndeksa.get();
    }

    public String getJmbg() {
        return jmbg.get();
    }

    public String getEmail() {
        return email.get();
    }

    @FXML
    public void initialize() {
        imeField.textProperty().bindBidirectional(ime);
        prezimeField.textProperty().bindBidirectional(prezime);
        brojIndeksaField.textProperty().bindBidirectional(brojIndeksa);
        jmbgField.textProperty().bindBidirectional(jmbg);
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
                System.out.println(dateField.getValue());
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
                validanEmail.get();
    }

    public void potvrdiClick(ActionEvent actionEvent) {
        /*if (Podaci.isEmailValid(getEmail())) {
            prezime.set(getIme());
        }
        //ime.set("Abc");
        brojIndeksa.set("");*/
        System.out.println(formularValidan());
        if (formularValidan()) {
            System.out.println("**** Podaci o studentu ****");
            // Osnovni podaci
            System.out.println("Osnovni podaci");
            System.out.println("--------------");
            System.out.println("Ime i prezime: " + getIme() + " " + getPrezime());
            System.out.println("Broj indeksa: " + getBrojIndeksa());
            // Detaljni licni podaci
            System.out.println("Detaljni lični podaci");
            System.out.println("---------------------");
            System.out.println("JMBG: " + getJmbg());
            System.out.println("Datum rođenja: " + dateField.getValue().toString());
            System.out.println("Mjesto rođenja: " + mjestoBox.getValue());
        }
    }



}
