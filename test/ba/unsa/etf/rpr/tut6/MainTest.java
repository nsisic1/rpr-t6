package ba.unsa.etf.rpr.tut6;

import static org.junit.jupiter.api.Assertions.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public void nameFieldTest(FxRobot robot) {
        TextField nameField = robot.lookup("#imeField").queryAs(TextField.class);
        robot.clickOn("#imeField").write("Nedim");
        assertEquals("Nedim", nameField.getText());
    }

    @Test
    public void surnameFieldTest(FxRobot robot) {
        TextField surnameField = robot.lookup("#prezimeField").queryAs(TextField.class);
        robot.clickOn("#prezimeField").write("Sisic");
        assertEquals("Sisic", surnameField.getText());
    }

    @Test
    public void indexFieldTest(FxRobot robot) {
        TextField indexField = robot.lookup("#brojIndeksaField").queryAs(TextField.class);
        robot.clickOn("#brojIndeksaField").write("17962");
        assertEquals("17962", indexField.getText());
    }
}