import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.util.Duration;


public class BaccaratGame extends Application {

	// Public member variables
	public ArrayList<Card> playerHand;
	public ArrayList<Card> bankerHand;
	public BaccaratDealer theDealer;
	public BaccaratGameLogic gameLogic = new BaccaratGameLogic();
	public double currentBet;
	public double totalWinnings;

	// Public member variables that we added
	public boolean setBetPlayer;
	public boolean setBetBanker;
	public boolean setBetDraw;

	// TODO: FINISH THIS METHOD
	// This method will determine if the user won or lost their bet and return the amount won or
	// lost based on the value in currentBet.
	public double evaluateWinnings() {
		totalWinnings = 0;
		return totalWinnings;
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

		return new Scene(mainLayout, 700, 700);
	}


	// This method sets the scene for a player to place their bets
	private Scene setBetScene(Stage primaryStage) {
		primaryStage.setTitle("Baccarat");

		// Create a label for the title
		Label title = new Label("Baccarat");
		title.setTextFill(Color.RED);
		title.setStyle("-fx-font-size: 36px;"); // Increase the font size

		// Create buttons for "PLAYER," "BANKER," and "DRAW" and all event handlers
		Button playerButton = new Button("PLAYER");
		playerButton.setOnAction(e -> setBetPlayer());

		Button bankerButton = new Button("BANKER");
		bankerButton.setOnAction(e -> setBetBanker());


		Button drawButton = new Button("DRAW");
		drawButton.setOnAction(e -> setBetDraw());

		// Create a VBox to stack the buttons vertically
		VBox buttonBox = new VBox(title, playerButton, bankerButton, drawButton);
		buttonBox.setAlignment(Pos.CENTER);

		// Create a label for "Select a bet amount"
		Label selectBetLabel = new Label("Select a bet amount:");

		// Create a Label for "Total Bet"
		Label totalBetLabel = new Label("Total Bet: $0.0");
		totalBetLabel.setTextFill(Color.BLACK); // Set text color to black

		// Create circle buttons for "$1", "$5", "$10", "$25", and "$50"
		Button dollar1Button = createCircleButton(Color.GREEN, "$1", 1.0, totalBetLabel);
		Button dollar5Button = createCircleButton(Color.BLUE, "$5", 5.0, totalBetLabel);
		Button dollar10Button = createCircleButton(Color.YELLOW, "$10", 10.0, totalBetLabel);
		Button dollar25Button = createCircleButton(Color.ORANGE, "$25", 25.0, totalBetLabel);
		Button dollar50Button = createCircleButton(Color.RED, "$50", 50.0, totalBetLabel);

		// Create an HBox for the circle buttons
		HBox circleButtonBox = new HBox(dollar1Button, dollar5Button, dollar10Button, dollar25Button, dollar50Button);
		circleButtonBox.setAlignment(Pos.CENTER);
		circleButtonBox.setSpacing(10);

		// Create button to clear bets and event handler
		Button clearBetsButton = createRectangularButton("Clear Bets");
		clearBetsButton.setOnAction(e -> clearBets(totalBetLabel));

		// Creat button to draw cards and event handler
		Button drawButton2 = createRectangularButton("Draw Cards");

		drawButton2.setOnAction(e -> {
			if (currentBet != 0 && (setBetBanker ^ setBetDraw ^ setBetPlayer))
				primaryStage.setScene(drawCardsScene(primaryStage));
		});

		// Create an HBox for the rectangular buttons
		HBox rectangularButtonBox = new HBox(clearBetsButton, drawButton2);
		rectangularButtonBox.setAlignment(Pos.CENTER);
		rectangularButtonBox.setSpacing(10);

		// TODO: COMPLETE WINNINGS LABELS
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

		return new Scene(root, 700, 700);
	}

	// TODO: Complete this method for the next scene to display the cards
	// This method sets the scene to display the cards that were drawn
	private Scene drawCardsScene(Stage primaryStage) {
		primaryStage.setTitle("Baccarat");

		theDealer = new BaccaratDealer();
		theDealer.generateDeck();
//
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();


		Label playerL = new Label();
		Label bankerL = new Label();
		VBox banker = new VBox(bankerL);
		VBox player = new VBox(playerL);
		HBox root = new HBox(player, banker);

		Timeline timeline = new Timeline();
		displayHandsTimeLine(playerHand, bankerHand, playerL, bankerL, timeline, player, banker, root);
		timeline.play();




//		if (gameLogic.evaluatePlayerDraw(playerHand)) {
//			playerHand.add(theDealer.drawOne());
//			Button drawOneMore = createRectangularButton("DRAW CARD");
//		}
		// First show the players card
		// Then show the bankers card
		// Then show player's second card
		// Then show bankers's second card

		// If there is a natural win, show message that whoever won and end the round

		// Else, check if the player or banker can draw
		// if they can draw, show a button that would say "Draw" card on it

		// Add the third card(s) to whoever they are for then show the third car

		// Create a VBox to stack the Labels
		// Creates the label for the bankers hand
//		VBox banker = new VBox(bankerL);
//		banker.setAlignment(Pos.CENTER_RIGHT);
//
//		// Creates the label for the players hand
//		VBox player = new VBox(playerL);
//		player.setAlignment(Pos.CENTER_LEFT);
//
//		HBox root = new HBox(player, banker);
//		root.setSpacing(250);
//		root.setAlignment(Pos.CENTER);
		return new Scene(timeline.play(), 700, 700);
	}


	// This method creates the buttons and setups up an event handler so when they are clicked they increment
	// the totalBet
	private Button createCircleButton(Color color, String labelText, double betAmount, Label totalBetLabel) {
		Circle circle = new Circle(25, color);
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
	private Button createRectangularButton(String labelText) {
		Rectangle button = new Rectangle(80, 30, Color.LIGHTGRAY);
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


	// The three methods below will set the appropriate boolean variable to true
	// and the others to false
	private void setBetPlayer() {
		setBetPlayer = true;
		setBetBanker = false;
		setBetDraw = false;
	}

	private void setBetBanker() {
		setBetPlayer = false;
		setBetBanker = true;
		setBetDraw = false;
	}

	private void setBetDraw() {
		setBetPlayer = false;
		setBetBanker = false;
		setBetDraw = true;
	}

	// This method returns the value of each card as a string
	private String getString(int value) {
		if (value == 1) {
			return "Ace";
		} else if (value == 11) {
			return "Jack";
		} else if (value == 12) {
			return "Queen";
		} else if (value == 13) {
			return "King";
		} else {
			return Integer.toString(value);
		}
	}



//	Label label = new Label();
//		label.setText((s +'\n' + hand.get(0).suite + " " + getString(hand.get(0).value) + "  |  " + hand.get(1).suite + " " + getString(hand.get(1).value) + "\n\nTotal: " + Integer.toString(gameLogic.handTotal(hand))));
//		return label;
//
//}
