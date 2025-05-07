import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CalcModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import view.MainController;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class AppRunnerTests {

    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("main.fxml"));
        BorderPane view = loader.load();

        MainController controller = loader.getController();
        if (controller == null) {
            throw new IllegalStateException("MainController not found in FXML");
        }

        CalcModel model = new CalcModel();
        controller.setModel(model);

        Scene scene = new Scene(view);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Test Multiplication (10 * 20 = 200)")
    public void testMultiplication(FxRobot robot) {
        calculate(robot, 10, 20, "#multiply");
        assertResultEquals(robot, 200);
    }

    @Test
    @DisplayName("Test Addition (10 + 20 = 30)")
    public void testAddition(FxRobot robot) {
        calculate(robot, 10, 20, "#add");
        assertResultEquals(robot, 30);
    }

    @Test
    @DisplayName("Test Subtraction (10 - 20 = -10)")
    public void testSubtraction(FxRobot robot) {
        calculate(robot, 10, 20, "#subtract");
        assertResultEquals(robot, -10);
    }

    @Test
    @DisplayName("Test Division (10 / 20 = 0.5)")
    public void testDivision(FxRobot robot) {
        calculate(robot, 10, 20, "#divide");
        assertResultEquals(robot, 0.5);
    }

    private void calculate(FxRobot robot, int num1, int num2, String btnId) {
        enterValue(robot, "#num1", String.valueOf(num1));
        enterValue(robot, "#num2", String.valueOf(num2));
        robot.clickOn(btnId);
    }

    private void enterValue(FxRobot robot, String fieldId, String text) {
        robot.clickOn(fieldId);
        robot.eraseText(10);  // assumes input field has max 10 characters
        robot.write(text);
    }

    private void assertResultEquals(FxRobot robot, double expected) {
        Label resultLabel = robot.lookup("#result").queryAs(Label.class);
        Assertions.assertThat(Double.parseDouble(resultLabel.getText()))
                  .isCloseTo(expected, Assertions.within(0.01));
    }
}
