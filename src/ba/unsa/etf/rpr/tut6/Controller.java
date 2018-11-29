package ba.unsa.etf.rpr.tut6;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    boolean validnoIme, validnoPrezime, validanIndeks, validanJmbg,validanDatum, validanEmail;

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
    }





}
