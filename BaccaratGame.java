import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.control.Control;




public class BaccaratGame extends Application {

	// Public data members
	ArrayList<Card> playerHand = new ArrayList<Card>();
	ArrayList<Card> bankerHand = new ArrayList<Card>();
	BaccaratDealer theDealer;
	BaccaratGameLogic gameLogic;
	double currentBet;
	double totalWinnings;

	// This method will determine if the user won or lost their bet and return the amount won or
	// lost based on the value in currentBet.
	public double evalutateWinnings() {
		// TODO
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

		// Create the title on the start scene
	}



	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		Label title = new Label("Baccarat");
		title.setTextFill(Color.web("#FF0000"));
		title.setScaleX(6);
		title.setScaleY(6);


		VBox scene1VBox = new VBox(title);
		scene1VBox.setAlignment(javafx.geometry.Pos.CENTER);

		title.setLayoutY(100);
		title.setLayoutY(100);


		Scene scene = new Scene(scene1VBox, 700, 700);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setScene(scene);
		primaryStage.show();

		
				
		
	}

}
