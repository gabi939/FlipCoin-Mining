package boundry;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import Utils.RiddleStatus;
import control.RiddleLogic;
import entity.Riddle;
import entity.RiddleLevel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WorkerAddRiddle implements Initializable{

    @FXML
    private Label errorLabel;
    @FXML
    private Label idLabel;

    @FXML
    private JFXDatePicker publishPick;

    @FXML
    private JFXTextField decpText;

    @FXML
    private JFXDatePicker finishPick;

    @FXML
    private JFXComboBox<RiddleLevel> lvlCombo;

    @FXML
    void confirm(ActionEvent event) {

    	
    	Riddle rid = WorkerRiddleMang.toUpdate;
    	Date publish =  Date.from(publishPick.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	Date finish =  Date.from(finishPick.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	RiddleLevel lvl = lvlCombo.getSelectionModel().getSelectedItem();
    	String decp = decpText.getText();
    	
    	
    	if(publish.before(finish)) {
    		if(decp != null && !decp.equals("")) {
    		
    			rid.setLevel(lvl.getName());
    			rid.setPublishDate(publish);
    			rid.setSolutionFinishTime(finish);
    			rid.setStatus(RiddleStatus.solvable);
    			WorkerRiddleMang.toUpdate = null;
    			WorkerRiddleMang.workerRiddleMang.table.refresh();
    		Stage stage = (Stage) errorLabel.getScene().getWindow();
    		stage.close();
    		
    		}else
        		errorLabel.setText("please enter");

    		
    	}else
    		errorLabel.setText("cant start before finish");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lvlCombo.getItems().addAll(RiddleLogic.getAllLevels());
		lvlCombo.getSelectionModel().select(0);
		finishPick.setValue(LocalDate.now());
		publishPick.setValue(LocalDate.now());
		decpText.setDisable(true);
		idLabel.setText("Update Riddle "+WorkerRiddleMang.toUpdate.getRiddleId());
		decpText.setText(WorkerRiddleMang.toUpdate.getDescription());
		
	}

}