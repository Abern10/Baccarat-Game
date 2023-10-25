import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.*;
public class BaccaratGame extends Application {

	// Public member variables
	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private BaccaratDealer theDealer;
	private BaccaratGameLogic gameLogic;
	private double currentBet;
	private double totalWinnings;

	// TODO: FINISH THIS METHOD
	// Public method
	public double evaluateWinnings() {
		double winnings = 0;
		return winnings;
	}

	public static void main(String[] args) {
		launch(args);
	}

	// Start method that sets title in the beginning and then sets the scene by calling setWelcomeScene
	// and passing in the primaryStage
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Baccarat");

		primaryStage.setScene(setWelcomeScene(primaryStage));
		primaryStage.show();
	}

	// This method sets the scene to welcome the player and lets them start playing the game
	private Scene setWelcomeScene(Stage primaryStage) {
		// Create a label for the title
		Label title = new Label("Baccarat");
		title.setTextFill(Color.RED);
		title.setStyle("-fx-font-size: 48px;"); // Increase the font size

		// Create a button
		Button playButton = new Button("Click to play!");
		playButton.setOnAction(e -> {
			// Transition to the bet scene when the button is clicked
			primaryStage.setScene(setBetScene(primaryStage));
		});

		// Create a VBox for the title and set its alignment to the top
		VBox titleBox = new VBox(title);
		titleBox.setAlignment(Pos.TOP_CENTER);

		// Create a VBox for the button and set its alignment to the center
		VBox buttonBox = new VBox(playButton);
		buttonBox.setAlignment(Pos.CENTER);

		// Create a VBox to contain both titleBox and buttonBox
		VBox mainLayout = new VBox(titleBox, buttonBox);
		mainLayout.setAlignment(Pos.CENTER);

		// Create the scene
		Scene scene = new Scene(mainLayout, 700, 700);

		return scene;
	}


	// This method sets the scene for a player to place their bets
	private Scene setBetScene(Stage primaryStage) {
		primaryStage.setTitle("Baccarat");

		// Create a label for the title
		Label title = new Label("Baccarat");
		title.setTextFill(Color.RED);
		title.setStyle("-fx-font-size: 36px;"); // Increase the font size

		// Create buttons for "PLAYER," "BANKER," and "DRAW"
		Button playerButton = new Button("PLAYER");
		Button bankerButton = new Button("BANKER");
		Button drawButton = new Button("DRAW");

		// Create a VBox to stack the buttons vertically
		VBox buttonBox = new VBox(title, playerButton, bankerButton, drawButton);
		buttonBox.setAlignment(Pos.CENTER);

		// Create a label for "Select a bet amount"
		Label selectBetLabel = new Label("Select a bet amount:");

		// Create a Label for "Total Bet"
		Label totalBetLabel = new Label("Total Bet: $0.0");
		totalBetLabel.setTextFill(Color.BLACK); // Set text color to black

		// Create circle buttons for "$1", "$5", "$10", "$25", and "$50"
		Button dollar1Button = createCircleButton(25, Color.GREEN, "$1", 1.0, totalBetLabel);
		Button dollar5Button = createCircleButton(25, Color.BLUE, "$5", 5.0, totalBetLabel);
		Button dollar10Button = createCircleButton(25, Color.YELLOW, "$10", 10.0, totalBetLabel);
		Button dollar25Button = createCircleButton(25, Color.ORANGE, "$25", 25.0, totalBetLabel);
		Button dollar50Button = createCircleButton(25, Color.RED, "$50", 50.0, totalBetLabel);

		// Create an HBox for the circle buttons
		HBox circleButtonBox = new HBox(dollar1Button, dollar5Button, dollar10Button, dollar25Button, dollar50Button);
		circleButtonBox.setAlignment(Pos.CENTER);
		circleButtonBox.setSpacing(10);

		// Create button to clear bets and event handler
		Button clearBetsButton = createRectangularButton("Clear Bets", 80, 30);
		clearBetsButton.setOnAction(e -> {
			clearBets(totalBetLabel);
		});

		// Creat button to draw cards and event handler
		Button drawButton2 = createRectangularButton("Draw", 80, 30);
		drawButton2.setOnAction(e -> {
			primaryStage.setScene(drawCardsScene(primaryStage));
		});

		// Create an HBox for the rectangular buttons
		HBox rectangularButtonBox = new HBox(clearBetsButton, drawButton2);
		rectangularButtonBox.setAlignment(Pos.CENTER);
		rectangularButtonBox.setSpacing(10);

		// Create a label for "Winnings"
		Label winningsLabel = new Label("Winnings: ");

		// Create a VBox to stack the elements
		VBox root = new VBox(
				buttonBox,
				selectBetLabel,
				circleButtonBox,
				totalBetLabel,
				rectangularButtonBox,
				winningsLabel
		);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(20);

		// Create the scene
		Scene scene = new Scene(root, 700, 700);
		return scene;
	}

	// TODO: Complete this method for the next scene to display the cards
	// This method sets the scene to display the cards that were drawn
	private Scene drawCardsScene(Stage primaryStage) {
		primaryStage.setTitle("Baccarat");


		VBox root = new VBox(
		);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(20);

		Scene scene = new Scene(root, 700, 700);
		return scene;
	}


	// This method creates the buttons and setups up an event handler so when they are clicked they increment
	// the totalBet
	private Button createCircleButton(double radius, Color color, String labelText, double betAmount, Label totalBetLabel) {
		Circle circle = new Circle(radius, color);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(2);

		Label label = new Label(labelText);
		label.setTextFill(Color.BLACK); // Set text color to black

		StackPane stackPane = new StackPane(circle, label);
		stackPane.setAlignment(Pos.CENTER);

		Button button = new Button();
		button.setGraphic(stackPane);
		button.setStyle("-fx-background-color: transparent;"); // Make the button background transparent

		// Add an event handler to increment the current bet when clicked
		button.setOnAction(e -> {
			currentBet += betAmount;
			totalBetLabel.setText("Total Bet: $" + currentBet);
		});

		return button;
	}

	// This method creates the two buttons for clearing the bets and drawing cards
	private Button createRectangularButton(String labelText, double width, double height) {
		Rectangle button = new Rectangle(width, height, Color.LIGHTGRAY);
		button.setStroke(Color.BLACK);
		button.setStrokeWidth(2);

		Label label = new Label(labelText);
		label.setTextFill(Color.BLACK); // Set text color to black

		StackPane stackPane = new StackPane(button, label);
		stackPane.setAlignment(Pos.CENTER);

		Button buttonControl = new Button();
		buttonControl.setGraphic(stackPane);
		buttonControl.setStyle("-fx-background-color: transparent;"); // Make the button background transparent

		return buttonControl;
	}

	// This method will clear the bets the user inputted back to 0
	private void clearBets(Label totalBetLabel) {
		currentBet = 0.0;
		totalBetLabel.setText("Total Bet: $0.0");
	}



}
