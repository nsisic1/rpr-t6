package ba.unsa.etf.rpr.tut6;

import static org.junit.jupiter.api.Assertions.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class MainTest {

    @Start
    public void start(Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("/fxml/formular.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    void nameFieldTest(FxRobot robot) {
        TextField imeField = robot.lookup("#imeField").queryAs(TextField.class);
        robot.clickOn("#imeField").write("Nedim");
        assertEquals("Nedim", imeField.getText());
    }

    @Test
    void surnameFieldTest(FxRobot robot) {
        TextField prezimeField = robot.lookup("#prezimeField").queryAs(TextField.class);
        robot.clickOn("#prezimeField").write("Sisic");
        assertEquals("Sisic", prezimeField.getText());
    }

    @Test
    void indexFieldTest(FxRobot robot) {
        TextField brojIndeksaField = robot.lookup("#brojIndeksaField").queryAs(TextField.class);
        robot.clickOn("#brojIndeksaField").write("17962");
        assertEquals("17962", brojIndeksaField.getText());
    }

    @Test
    void jmbgValidityTest(FxRobot robot) {
        TextField jmbgField = robot.lookup("#jmbgField").queryAs(TextField.class);
        robot.clickOn("#jmbgField").write("1811997190027");
        robot.clickOn("#dateField").write("18/11/1997");
        robot.clickOn("#telefonField");
        assertEquals("text-input text-field poljeIspravno", jmbgField.getStyleClass().toString());
    }

    @Test
    void telefonValidityFalseTest(FxRobot robot) {
        TextField telefonField = robot.lookup("#telefonField").queryAs(TextField.class);
        robot.clickOn("#telefonField").write("033-225 88311");
        assertEquals("text-input text-field poljeNijeIspravno", telefonField.getStyleClass().toString());
    }

    @Test
    public void boxTest(FxRobot robot) {
        ComboBox mjesto = robot.lookup("#mjestoBox").queryAs(ComboBox.class);

        ChoiceBox odsjek = robot.lookup("#odsjekBox").queryAs(ChoiceBox.class);
        robot.clickOn("#odsjekBox").clickOn("RI");

        ChoiceBox godina = robot.lookup("#godinaBox").queryAs(ChoiceBox.class);
        robot.clickOn("#godinaBox").clickOn("Druga");

        ChoiceBox ciklus = robot.lookup("#ciklusBox").queryAs(ChoiceBox.class);
        robot.clickOn("#ciklusBox").clickOn("Bachelor");

        assertAll("podaci o studiju", () -> {
            assertEquals("Sarajevo", mjesto.getValue().toString());
            assertEquals("RI", odsjek.getValue().toString());
            assertEquals("Druga", godina.getValue().toString());
            assertEquals("Bachelor", ciklus.getValue().toString());
        });
    }
}