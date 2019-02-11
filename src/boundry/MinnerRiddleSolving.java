package boundry;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Utils.RiddleStatus;
import control.RiddleLogic;
import control.Sys;
import control.blockLogic;
import entity.Riddle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MinnerRiddleSolving implements Initializable {

	@FXML
	private TableView<Riddle> riddleTabe;

	@FXML
	private TableColumn<Riddle, Integer> idCoulmn;

	@FXML
	private TableColumn<Riddle, Date> publishDateCoulmn;

	@FXML
	private TableColumn<Riddle, String> descriptionCoulmn;

	@FXML
	private TableColumn<Riddle, Date> solutionFinishTimeCoulmn;

	@FXML
	private TableColumn<Riddle, RiddleStatus> statusCoulmn;

	@FXML
	private TableColumn<Riddle, String> levelCoulmn;

	private static String answer = null;

	@FXML
	void selectRiddle(MouseEvent event) {
		Riddle riddle = riddleTabe.getSelectionModel().getSelectedItem();
	
		
		if (riddle != null && !riddle.getStatus().equals(RiddleStatus.solved)) {

			popUpAnswer(riddle.getRiddleId().toString(), riddle.getDescription());
			if (answer != null) {
				if (RiddleLogic.checkAnswer(riddle, answer)) {
					answer = null;
					int blockSize = riddle.getLevelRIddleObject().getBlockSize();
					popUp("Solved", "Correct \n\r you will get a new Block with size of : " + blockSize);
					riddle.setStatus(RiddleStatus.solved);
					
					blockLogic.addNewBlockToMiner(Sys.user, blockSize);
					RiddleLogic.addFirstToSolve(Sys.user, riddle);
					
					
					riddleTabe.refresh();

				} else {
					answer = null;
					popUp("Wrong", "wrong answer");

				}
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//RiddleLogic.irreleventCheck();
		buildTable();
	}
	
	
	@FXML
	public void goBack(ActionEvent event) {
		  Stage stage = (Stage) riddleTabe.getScene().getWindow();
		  stage.close();
		  ViewLogic.mainMenu();
		  
	    }

	private void buildTable() {
		idCoulmn.setCellValueFactory(new PropertyValueFactory<>("riddleId"));
		publishDateCoulmn.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
		descriptionCoulmn.setCellValueFactory(new PropertyValueFactory<>("description"));
		solutionFinishTimeCoulmn.setCellValueFactory(new PropertyValueFactory<>("solutionFinishTime"));
		statusCoulmn.setCellValueFactory(new PropertyValueFactory<>("status"));
		levelCoulmn.setCellValueFactory(new PropertyValueFactory<>("level"));

		ObservableList<Riddle> riddles = FXCollections.observableArrayList();

		riddles.addAll(RiddleLogic.getSolvableRiddles());

		riddleTabe.getItems().addAll(riddles);

	}

	private void popUp(String title, String message) {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();
		label.setText(message);

		Button confrim = new Button("OK");

		confrim.setOnAction(e -> {
				window.close();
		});


		VBox layout = new VBox(10);

		// Add buttons
		layout.getChildren().addAll(label,confrim);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);

		window.showAndWait();


	}

	private String popUpAnswer(String title, String message) {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();
		TextField text = new TextField();
		label.setText(message);
		Label label2 = new Label("Please enter your answer");

		// Create two buttons
		Button confrim = new Button("Confirm");

		// Clicking will set answer and close window

		VBox layout = new VBox(10);

		// Add buttons
		layout.getChildren().addAll(label, label2, text, confrim);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);

		confrim.setOnAction(e -> {
			answer = text.getText();
			
			window.close();
		});

		window.showAndWait();

		// Make sure to return answer
		return answer;

	}
}
