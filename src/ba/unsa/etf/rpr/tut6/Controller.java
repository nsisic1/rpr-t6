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
    BoolWrapper validnoIme = new BoolWrapper();
    BoolWrapper validnoPrezime = new BoolWrapper();
    BoolWrapper validanIndeks = new BoolWrapper();
    BoolWrapper validanJmbg = new BoolWrapper();
    BoolWrapper validanDatum = new BoolWrapper();
    BoolWrapper validanEmail = new BoolWrapper();

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

    /*void initIme() {
        imeField.textProperty().bindBidirectional(ime);
        // validnoIme.set(false);
        imeField.getStyleClass().add("poljeNijeIspravno");
        imeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (Podaci.isImeValid(n)) {
                    imeField.getStyleClass().removeAll("poljeNijeIspravno");
                    imeField.getStyleClass().add("poljeIspravno");
                    validnoIme = true;
                } else {
                    imeField.getStyleClass().removeAll("poljeIspravno");
                    imeField.getStyleClass().add("poljeNijeIspravno");
                    validnoIme = false;
                }
            }
        });
    }

    void initPrezime() {
        validnoPrezime = false;
        prezimeField.getStyleClass().add("poljeNijeIspravno");
        prezimeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (Podaci.isPrezimeValid(n)) {
                    prezimeField.getStyleClass().removeAll("poljeNijeIspravno");
                    prezimeField.getStyleClass().add("poljeIspravno");
                    validnoPrezime = true;
                } else {
                    prezimeField.getStyleClass().removeAll("poljeIspravno");
                    prezimeField.getStyleClass().add("poljeNijeIspravno");
                    validnoPrezime = false;
                }
            }
        });
    }

    void initBrojIndeksa() {
        validanIndeks = false;
        brojIndeksaField.getStyleClass().add("poljeNijeIspravno");
        brojIndeksaField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (Podaci.isBrojIndeksaValid(n)) {
                    brojIndeksaField.getStyleClass().removeAll("poljeNijeIspravno");
                    brojIndeksaField.getStyleClass().add("poljeIspravno");
                    validanIndeks = true;
                } else {
                    brojIndeksaField.getStyleClass().removeAll("poljeIspravno");
                    brojIndeksaField.getStyleClass().add("poljeNijeIspravno");
                    validanIndeks = false;
                }
            }
        });
    }*/

    void initJMBG(DatePicker d) {
        validanJmbg.set(false);
        jmbgField.getStyleClass().add("poljeNijeIspravno");
        jmbgField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                LocalDate datum = d.getValue();
                System.out.println(datum);
                if (Podaci.isJmbgValid(n, datum)) {
                    jmbgField.getStyleClass().removeAll("poljeNijeIspravno");
                    jmbgField.getStyleClass().add("poljeIspravno");
                    validanJmbg.set(true);
                } else {
                    jmbgField.getStyleClass().removeAll("poljeIspravno");
                    jmbgField.getStyleClass().add("poljeNijeIspravno");
                    validanJmbg.set(false);
                }
            }
        });
    }

    void initDatum() {
        validanDatum.set(false);
        dateField.getStyleClass().add("poljeNijeIspravno");
        dateField.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (Podaci.isDateValid(dateField.getValue())) {
                    dateField.getStyleClass().removeAll("poljeNijeIspravno");
                    dateField.getStyleClass().add("poljeIspravno");
                    validanDatum.set(true);
                } else {
                    dateField.getStyleClass().removeAll("poljeIspravno");
                    dateField.getStyleClass().add("poljeNijeIspravno");
                    validanDatum.set(false);
                }
            }
        });
    }

    /*
    void initEmail() {
        validanEmail = false;
        emailField.getStyleClass().add("poljeNijeIspravno");
        emailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (Podaci.isEmailValid(n)) {
                    emailField.getStyleClass().removeAll("poljeNijeIspravno");
                    emailField.getStyleClass().add("poljeIspravno");
                    validanEmail = true;
                } else {
                    emailField.getStyleClass().removeAll("poljeIspravno");
                    emailField.getStyleClass().add("poljeNijeIspravno");
                    validanEmail = false;
                }
            }
        });
    }*/

    void initField(TextField field, BoolWrapper valid, Function<String, Boolean> isValid) {
        System.out.println(valid);
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


    private boolean formularValidan() {
        //return validnoIme && validnoPrezime && validanIndeks && validanJmbg && validanDatum && validanEmail;
        return true;
    }

    public void potvrdiClick(ActionEvent actionEvent) {
        /*if (Podaci.isEmailValid(getEmail())) {
            prezime.set(getIme());
        }
        //ime.set("Abc");
        brojIndeksa.set("");*/
        System.out.println(formularValidan());
        if (true) {
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
