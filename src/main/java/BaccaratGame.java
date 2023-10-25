import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.image.Image;
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

		Image backgroundImage = new Image("poker-background.png");

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

		// Add padding to titleBox to push the title to the top
		VBox.setMargin(titleBox, new Insets(20, 0, 0, 0));

		// Create a VBox for the button and set its alignment to the center
		VBox buttonBox = new VBox(playButton);
		buttonBox.setAlignment(Pos.CENTER);

		// Create a VBox to contain both titleBox and buttonBox
		VBox mainLayout = new VBox(titleBox, buttonBox);
		mainLayout.setAlignment(Pos.CENTER);

		// Create a BackgroundImage with the loaded image
		BackgroundImage background = new BackgroundImage(
				backgroundImage,
				BackgroundRepeat.NO_REPEAT, // You can adjust this if you want the image to repeat
				BackgroundRepeat.NO_REPEAT, // You can adjust this if you want the image to repeat
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT
		);
		// Set the background for the mainLayout
		mainLayout.setBackground(new Background(background));

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

		// Create circle buttons for "$1", "$5", "$10", "$25", and "$50"
		Circle dollar1Button = new Circle(25, Color.GREEN);
		dollar1Button.setStroke(Color.BLACK);
		dollar1Button.setStrokeWidth(2);
		Circle dollar5Button = new Circle(25, Color.BLUE);
		dollar5Button.setStroke(Color.BLACK);
		dollar5Button.setStrokeWidth(2);
		Circle dollar10Button = new Circle(25, Color.YELLOW);
		dollar10Button.setStroke(Color.BLACK);
		dollar10Button.setStrokeWidth(2);
		Circle dollar25Button = new Circle(25, Color.ORANGE);
		dollar25Button.setStroke(Color.BLACK);
		dollar25Button.setStrokeWidth(2);
		Circle dollar50Button = new Circle(25, Color.RED);
		dollar50Button.setStroke(Color.BLACK);
		dollar50Button.setStrokeWidth(2);

		// Create an HBox for the circle buttons
		HBox circleButtonBox = new HBox(dollar1Button, dollar5Button, dollar10Button, dollar25Button, dollar50Button);
		circleButtonBox.setAlignment(Pos.CENTER);
		circleButtonBox.setSpacing(10);

		// Create a label for "Total Bet"
		Label totalBetLabel = new Label("Total Bet: ");

		// Create rectangular buttons for "Clear Bets" and "Draw"
		Rectangle clearBetsButton = new Rectangle(80, 30, Color.LIGHTGRAY);
		clearBetsButton.setStroke(Color.BLACK);
		clearBetsButton.setStrokeWidth(2);
		Rectangle drawButton2 = new Rectangle(80, 30, Color.LIGHTGRAY);
		drawButton2.setStroke(Color.BLACK);
		drawButton2.setStrokeWidth(2);

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

}
