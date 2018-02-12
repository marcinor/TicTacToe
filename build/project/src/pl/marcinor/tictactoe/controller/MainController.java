package pl.marcinor.tictactoe.controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pl.marcinor.tictactoe.app.Status;

public class MainController implements Initializable {

	@FXML
	private ImageView imageView00;

	@FXML
	private ImageView imageView10;

	@FXML
	private ImageView imageView20;

	@FXML
	private ImageView imageView01;

	@FXML
	private ImageView imageView11;

	@FXML
	private ImageView imageView21;

	@FXML
	private ImageView imageView02;

	@FXML
	private ImageView imageView12;

	@FXML
	private ImageView imageView22;

	@FXML
	private Button newGameButton;

	@FXML
	private ImageView turnImageView;
	
    @FXML
    private Label turnLabel;

	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	private Image miniCircle = new Image("/pl/marcinor/tictactoe/res/miniCircle.png");
	private Image miniCross = new Image("/pl/marcinor/tictactoe/res/miniCross.png");
	private Image miniEmpty = new Image("/pl/marcinor/tictactoe/res/miniEmpty.png");
	private Image empty = new Image("/pl/marcinor/tictactoe/res/Empty.png");
	private Image circle = new Image("/pl/marcinor/tictactoe/res/Circle.png");
	private Image cross = new Image("/pl/marcinor/tictactoe/res/Cross.png");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> newGame());
		
		imageView00.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> draw(imageView00));
		imageView01.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> draw(imageView01));
		imageView02.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> draw(imageView02));
		imageView10.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> draw(imageView10));
		imageView11.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> draw(imageView11));
		imageView12.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> draw(imageView12));
		imageView20.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> draw(imageView20));
		imageView21.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> draw(imageView21));
		imageView22.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> draw(imageView22));
		
	}
	
	private void newGame() {
		turnLabel.setText("It's your turn now!");
		
		imageView00.setImage(empty);
		imageView00.setDisable(false);

		imageView01.setImage(empty);
		imageView01.setDisable(false);

		imageView02.setImage(empty);
		imageView02.setDisable(false);

		imageView10.setImage(empty);
		imageView10.setDisable(false);

		imageView11.setImage(empty);
		imageView11.setDisable(false);

		imageView12.setImage(empty);
		imageView12.setDisable(false);

		imageView20.setImage(empty);
		imageView20.setDisable(false);

		imageView21.setImage(empty);
		imageView21.setDisable(false);

		imageView22.setImage(empty);
		imageView22.setDisable(false);

		turnImageView.setImage(miniEmpty);

		Random random = new Random();
		int player = random.nextInt(2);
		// 1 - circle starts
		// 0 - cross starts
		if (player == 1) {
			setStatus(Status.CROSS);
			nextTurn();
		} else {
			setStatus(Status.CIRCLE);
			nextTurn();
		}

	}

	private void draw(ImageView iv) {
		if (status == Status.CIRCLE) {
			iv.setImage(circle);
			iv.setDisable(true);
		} else if (status == Status.CROSS) {
			iv.setImage(cross);
			iv.setDisable(true);
		}
		nextTurn();
	}

	private void nextTurn() {
		if (getStatus() == Status.CIRCLE) {
			turnImageView.setImage(miniCross);
			isWon();
			setStatus(Status.CROSS);
		} else if (getStatus() == Status.CROSS) {
			turnImageView.setImage(miniCircle);
			isWon();
			setStatus(Status.CIRCLE);
		}
	}

	private void isWon() {
		Boolean won = false;
		
		// checking rows
		if(imageView00.getImage() == imageView01.getImage() && imageView00.getImage() == imageView02.getImage() && imageView00.getImage() != empty)
			won = true;
		else if(imageView10.getImage() == imageView11.getImage() && imageView10.getImage() == imageView12.getImage() && imageView10.getImage() != empty)
			won = true;
		else if(imageView20.getImage() == imageView21.getImage() && imageView20.getImage() == imageView22.getImage() && imageView20.getImage() != empty)
			won = true;
		// checking columns
		else if(imageView00.getImage() == imageView10.getImage() && imageView00.getImage() == imageView20.getImage() && imageView00.getImage() != empty)
			won = true;
		else if(imageView01.getImage() == imageView11.getImage() && imageView01.getImage() == imageView21.getImage() && imageView01.getImage() != empty)
			won = true;
		else if(imageView02.getImage() == imageView12.getImage() && imageView02.getImage() == imageView22.getImage() && imageView02.getImage() != empty)
			won = true;
		// checking diagonally
		else if(imageView00.getImage() == imageView11.getImage() && imageView00.getImage() == imageView22.getImage() && imageView00.getImage() != empty)
			won = true;
		else if(imageView02.getImage() == imageView11.getImage() && imageView02.getImage() == imageView20.getImage() && imageView02.getImage() != empty)
			won = true;
		
		// draw
		else if(imageView00.getImage() != empty && imageView01.getImage() != empty && imageView02.getImage() != empty
				&& imageView10.getImage() != empty && imageView11.getImage() != empty && imageView12.getImage() != empty
				&& imageView20.getImage() != empty && imageView21.getImage() != empty && imageView22.getImage() != empty) {
			turnImageView.setImage(miniEmpty);
		    turnLabel.setText("It's a DRAW!");
		}
			
		
		if(won == true) {
			if(getStatus() == Status.CIRCLE) {
				// set image of o
				turnImageView.setImage(miniCircle);
			    turnLabel.setText("CIRCLE won!");
			}
			else if(getStatus() == Status.CROSS) {
				// set image x
				turnImageView.setImage(miniCross);
				turnLabel.setText("CROSS won!");
			}
			imageView00.setDisable(true);
			imageView01.setDisable(true);
			imageView02.setDisable(true);
			imageView10.setDisable(true);
			imageView11.setDisable(true);
			imageView12.setDisable(true);
			imageView20.setDisable(true);
			imageView21.setDisable(true);
			imageView22.setDisable(true);
		}
	}
}