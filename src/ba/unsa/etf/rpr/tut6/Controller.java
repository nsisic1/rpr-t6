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

    boolean validnoIme, validnoPrezime, validanIndeks, validanJmbg, validanDatum, validanEmail;

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
        prezimeField.textProperty().bindBidirectional(prezime);
        brojIndeksaField.textProperty().bindBidirectional(brojIndeksa);
        jmbgField.textProperty().bindBidirectional(jmbg);
        emailField.textProperty().bindBidirectional(email);

        initIme();
        initPrezime();
        initBrojIndeksa();
        initJMBG(dateField.getValue());
        initDatum();
        initEmail();

    }

    void initIme() {
        imeField.textProperty().bindBidirectional(ime);
        validnoIme = false;
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
    }

    void initJMBG(LocalDate datum) {
        validanJmbg = false;
        jmbgField.getStyleClass().add("poljeNijeIspravno");
        jmbgField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (Podaci.isJmbgValid(n, datum)) {
                    jmbgField.getStyleClass().removeAll("poljeNijeIspravno");
                    jmbgField.getStyleClass().add("poljeIspravno");
                    validanJmbg = true;
                } else {
                    jmbgField.getStyleClass().removeAll("poljeIspravno");
                    jmbgField.getStyleClass().add("poljeNijeIspravno");
                    validanJmbg = false;
                }
            }
        });
    }

    void initDatum() {
        validanDatum = false;
        dateField.getStyleClass().add("poljeNijeIspravno");
        dateField.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (Podaci.isDateValid(dateField.getValue())) {
                    dateField.getStyleClass().removeAll("poljeNijeIspravno");
                    dateField.getStyleClass().add("poljeIspravno");
                    validanDatum = true;
                } else {
                    dateField.getStyleClass().removeAll("poljeIspravno");
                    dateField.getStyleClass().add("poljeNijeIspravno");
                    validanDatum = false;
                }
            }
        });
    }

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
    }


    private boolean formularValidan() {
        //return validnoIme && validnoPrezime && validanIndeks && validanJmbg && validanDatum && validanEmail;
        return true;
    }



}
