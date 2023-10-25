import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.*;

public class BaccaratGame extends Application {

	// Private member variables for UI
	private Stage primaryStage;
	private VBox root;

	// Public required member variables
	public ArrayList<Card> playerHand;
	public ArrayList<Card> bankerHand;
	public BaccaratDealer theDealer;
	public BaccaratGameLogic gameLogic;
	public double currentBet;
	public double totalWinnings;

	// TODO: Required method to implement
	// This method will determine if the user won or lost their bet and return the amount won or
	// lost based on the value in currentBet.
	public double evaluateWinnings() {
		double winnings = 0;

		return winnings;
	}

	// Main method to launch game and all arguments
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// Creates a stage and sets the title
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Baccarat");

		// Sets the stage to the welcome scene using welcomeScene method
		primaryStage.setScene(welcomeScene());
		primaryStage.show();
	}

	// This method sets the scene for the welcome scene
	private Scene welcomeScene() {
		Scene welcomeScene = new Scene(root, 700, 700);

//		// Create a label for the title
//		Label title = createTitleLabel("Baccarat");
//
//		// Create a button for "Click to play!"
//		Button playButton = createPlayButton();
//
//		// Create a GridPane to center the elements
//		GridPane grid = createCenteredGrid(title, playButton);
//
//		// Create the main layout VBox for the initial scene
//		VBox initialRoot = createCenteredLayout(grid);


        return welcomeScene;
    }

	private Scene setBetScene() {
		primaryStage.setTitle("Baccarat");
		Scene betScene = new Scene(root, 700, 700);;

		return betScene;
	}

	// Method to set up scene to place bets (Scene 2)
//	private Scene setBetScene() {
//		Label title = createTitleLabel("Baccarat");
//
//		Button playerButton = new Button("PLAYER");
//		Button bankerButton = new Button("BANKER");
//		Button drawButton = new Button("DRAW");
//
//		VBox buttonBox = createCenteredLayout(title, playerButton, bankerButton, drawButton);
//
//		Label selectBetLabel = new Label("Select a bet amount:");
//
//		HBox circleButtonBox = createCircleButtonBox();
//
//		Label totalBetLabel = new Label("Total Bet: ");
//
//		HBox rectangularButtonBox = createRectangularButtonBox();
//
//		Label winningsLabel = new Label("Winnings: ");
//
//		VBox newRoot = createCenteredLayout(buttonBox, selectBetLabel, circleButtonBox, totalBetLabel, rectangularButtonBox, winningsLabel);
//
//		Scene newScene = createScene(newRoot);
//	}

	private Label createTitleLabel(String text) {
		Label title = new Label(text);
		title.setTextFill(Color.RED);
		title.setStyle("-fx-font-size: 36px");
		return title;
	}

	private Button createPlayButton() {
		Button playButton = new Button("Click to play!");
		playButton.setOnAction(event -> setBetScene());
		playButton.setAlignment(Pos.CENTER);
		VBox.setMargin(playButton, new Insets(20, 0, 0, 0));
		return playButton;
	}

	private GridPane createCenteredGrid(Label title, Button playButton) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.add(title, 0, 0);
		grid.add(playButton, 0, 1);
		return grid;
	}

	private VBox createCenteredLayout(GridPane content) {
		VBox root = new VBox(content);
		root.setAlignment(Pos.CENTER);
		return root;
	}

	// This method creates a new scene that is just a template
//	private Scene createScene() {
//		Scene scene = new Scene(root, 700, 700);
//		primaryStage.setTitle("Baccarat");
//		return scene;
//	}

	private HBox createCircleButtonBox() {
		Circle dollar1Button = createCircleButton("$1", Color.GREEN);
		Circle dollar5Button = createCircleButton("$5", Color.BLUE);
		Circle dollar10Button = createCircleButton("$10", Color.YELLOW);
		Circle dollar25Button = createCircleButton("$25", Color.ORANGE);
		Circle dollar50Button = createCircleButton("$50", Color.RED);

		HBox circleButtonBox = new HBox(dollar1Button, dollar5Button, dollar10Button, dollar25Button, dollar50Button);
		circleButtonBox.setAlignment(Pos.CENTER);
		circleButtonBox.setSpacing(10);

		return circleButtonBox;
	}

	private Circle createCircleButton(String text, Color color) {
		Circle button = new Circle(25, color);
		button.setStroke(Color.BLACK);
		button.setStrokeWidth(2);
		return button;
	}

	private HBox createRectangularButtonBox() {
		Rectangle clearBetsButton = createRectangularButton("Clear Bets");
		Rectangle drawButton = createRectangularButton("Draw");

		HBox rectangularButtonBox = new HBox(clearBetsButton, drawButton);
		rectangularButtonBox.setAlignment(Pos.CENTER);
		rectangularButtonBox.setSpacing(10);

		return rectangularButtonBox;
	}

	private Rectangle createRectangularButton(String text) {
		Rectangle button = new Rectangle(80, 30, Color.LIGHTGRAY);
		button.setStroke(Color.BLACK);
		button.setStrokeWidth(2);
		return button;
	}
}
