package boundry;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Exceptions.RaffleException;
import control.RaffleLogic;
import entity.RiddleLevel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WorkerLevelMang implements Initializable {

	@FXML
	private TableView<RiddleLevel> table;

	@FXML
	private TableColumn<RiddleLevel, String> nameColm;

	@FXML
	private TableColumn<RiddleLevel, Integer> diffColm;

	@FXML
	private TableColumn<RiddleLevel, Integer> blockColm;

	private static RiddleLevel toUpdate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setTable();

	}

	private void setTable() {

		nameColm.setCellValueFactory(new PropertyValueFactory<>("name"));
		diffColm.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
		blockColm.setCellValueFactory(new PropertyValueFactory<>("blockSize"));

		table.getItems().addAll(RaffleLogic.getAllLevels());

	}

	@FXML
	void add(ActionEvent event) {

		addPop();
		RaffleLogic.addLevel(toUpdate);
		table.getItems().add(toUpdate);
		table.refresh();
		toUpdate = null;

	}

	@FXML
	void goBack(ActionEvent event) {
		Stage sage = (Stage) table.getScene().getWindow();
		sage.close();
		ViewLogic.WorkerMenu();
	}

	@FXML
	void remove(ActionEvent event) {
		RiddleLevel rid = table.getSelectionModel().getSelectedItem();
		if (rid != null) {
			RaffleLogic.removeLevel(rid);
			table.getItems().remove(rid);

		}

	}

	@FXML
	void update(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				RiddleLevel rid = table.getSelectionModel().getSelectedItem();

				if (rid != null) {
					toUpdate = rid;
					popUp();
					table.refresh();
					table.getSelectionModel().clearSelection();

				} else {

					table.getSelectionModel().clearSelection();

				}

			}
		}
	}

	private void addPop() {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner((Stage) table.getScene().getWindow());
		VBox dialogVbox = new VBox(20);
		dialogVbox.setAlignment(Pos.CENTER);
		dialogVbox.getChildren().add(new Text("New level"));

		TextField name = new TextField("new name");
		TextField textDiff = new TextField("new diffculty");
		TextField textSize = new TextField("new block size");
		dialogVbox.getChildren().addAll(name, textDiff, textSize);

		JFXButton but = new JFXButton("Confirm");
		but.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String str = name.getText();
				if (!str.equals("")) {
					try {
						int diff = Integer.parseInt(textDiff.getText());

						try {
							int size = Integer.parseInt(textSize.getText());

							toUpdate.setDifficulty(diff);
							toUpdate.setBlockSize(size);
							toUpdate = new RiddleLevel(str, diff, size);
							dialog.close();
						} catch (NumberFormatException e) {
							textSize.setText("Invalid size , must be positive number");
						} catch (RaffleException e) {
							if (e.getMessage().equals("Invalid size , must be positive number"))
								textSize.setText("Invalid size , must be positive number");
							else
								textDiff.setText("Invalid Difficulty , needs to be between 1- 10");
						}

					} catch (NumberFormatException e) {
						textDiff.setText("Invalid Difficulty , needs to be between 1- 10");
					}
				} else
					name.setText("Please insert level name");
			}

		});

		dialogVbox.getChildren().add(but);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.showAndWait();

	}

	private void popUp() {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner((Stage) table.getScene().getWindow());
		VBox dialogVbox = new VBox(20);
		dialogVbox.setAlignment(Pos.CENTER);
		dialogVbox.getChildren().add(new Text("Update Level " + toUpdate.getName()));

		TextField textDiff = new TextField("new diffculty");
		TextField textSize = new TextField("new block size");
		dialogVbox.getChildren().addAll(textDiff, textSize);

		JFXButton but = new JFXButton("Confirm");
		but.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					int diff = Integer.parseInt(textDiff.getText());

					try {
						int size = Integer.parseInt(textSize.getText());

						toUpdate.setDifficulty(diff);
						toUpdate.setBlockSize(size);
						toUpdate = null;
						dialog.close();
					} catch (NumberFormatException e) {
						textSize.setText("Invalid size , must be positive number");
					} catch (RaffleException e) {
						if (e.getMessage().equals("Invalid size , must be positive number"))
							textSize.setText("Invalid size , must be positive number");
						else
							textDiff.setText("Invalid Difficulty , needs to be between 1- 10");
					}

				} catch (NumberFormatException e) {
					textDiff.setText("Invalid Difficulty , needs to be between 1- 10");
				}
			}
		});

		dialogVbox.getChildren().add(but);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.showAndWait();
	}

}
