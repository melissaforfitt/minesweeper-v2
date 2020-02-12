import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Minesweeper extends Application {

    GridPane grid = new GridPane();
    private Button selectionButtons[][];
    int[][] minePosition = new int[8][8];

    public static void main(String[] args) {

        Application.launch(args);

    }

    public void buildGrid() {

        for (int l = 0; l < 8; l++) {
            for (int m = 0; m < 8; m++) {
                selectionButtons[l][m] = new Button();
                selectionButtons[l][m].setId("grid-buttons");
                selectionButtons[l][m].setMinWidth(50);
                selectionButtons[l][m].setMinHeight(50);
                selectionButtons[l][m].setId("grid-buttons");
                grid.add(selectionButtons[l][m], l, m);
            }
        }

    }

    public void buildMines() {

        Random r = new Random();
        int random = r.nextInt(20);

        System.out.println(random);

        for (int a = 0; a < random; a++) {

            Random gridRandom = new Random(); // Generate random number for mine grid position
            int xPosition = gridRandom.nextInt(8);
            System.out.println(xPosition);
            int yPosition = gridRandom.nextInt(8);
            System.out.println(yPosition);
            selectionButtons[xPosition][yPosition].setText("!"); // Show mines

        }

    }

    public void buildNumbers() {

        int mineCount = 0; // Number of mines around a point

        for (int m = 0; m < 8; m++) {
            for (int n = 0; n < 8; n++) {
                if (selectionButtons[m][n].getText().trim().isEmpty()) {
                    if (m < 7) {
                        if (selectionButtons[m + 1][n].getText().contentEquals("!")) {
                            selectionButtons[m][n].setText("1");
                        }
                    }
                    if (n < 7) {
                        if (selectionButtons[m][n + 1].getText().contentEquals("!")) {
                            selectionButtons[m][n].setText("1");
                        }
                    }
                    if (m > 1) {
                        if (selectionButtons[m - 1][n].getText().contentEquals("!")) {
                            selectionButtons[m][n].setText("1");
                        }
                    }
                    if (n > 1) {
                        if (selectionButtons[m][n - 1].getText().contentEquals("!")) {
                            selectionButtons[m][n].setText("1");
                        }
                    }
                    if (m < 7 && n < 7) {
                        if (selectionButtons[m + 1][n + 1].getText().contentEquals("!")) {
                            selectionButtons[m][n].setText("1");
                        }
                    }
                    if (m > 1 && n > 1) {
                        if (selectionButtons[m - 1][n - 1].getText().contentEquals("!")) {
                            selectionButtons[m][n].setText("1");
                        }
                    }
                    if (m < 7 && n > 1) {
                        if (selectionButtons[m + 1][n - 1].getText().contentEquals("!")) {
                            selectionButtons[m][n].setText("1");
                        }
                    }
                    if (m < 1 && n > 7) {
                        if (selectionButtons[m - 1][n + 1].getText().contentEquals("!")) {
                            selectionButtons[m][n].setText("1");
                        }
                    }
                    if (selectionButtons[m][n].getText().trim().isEmpty()) {
                        selectionButtons[m][n].setText("0");
                    }
                }
            }
        }

    }

    public void gameLogic() {

        for (int e = 0; e < 8; e++) {
            for (int f = 0; f < 8; f++) {

                int j = e;
                int k = f;

                selectionButtons[e][f].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        if (selectionButtons[j][k].getText().contentEquals("!")) {
                            selectionButtons[j][k].setVisible(true);
                            selectionButtons[j][k].setId("button-select");
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Game Over");
                            alert.setHeaderText("Game Over");
                            alert.setContentText("You have lost the game!");
                            alert.showAndWait();
                        } else {
                            // selectionButtons[j][k].getText().;
                            selectionButtons[j][k].setId("button-select");
                        }

                    }
                });
            }

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        selectionButtons = new Button[8][8]; // Create grid of 8 buttons

        buildGrid();
        buildMines();
        buildNumbers();

        gameLogic();

        /* Create scene */
        Scene scene = new Scene(grid, 500, 500);
        scene.getStylesheets().add(this.getClass().getResource("Design.css").toExternalForm());
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
