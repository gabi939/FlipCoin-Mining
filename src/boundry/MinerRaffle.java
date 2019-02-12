package boundry;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import control.RaffleLogic;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

public class MinerRaffle implements Initializable {

    @FXML
    private JFXTreeTableView<Raffle> RaffleTable;

    @FXML
    private JFXListView<?> BenList;

    @FXML
    private JFXSnackbar snackBar;

    
    
    class Raffle extends RecursiveTreeObject<Raffle>{
    	IntegerProperty RaffleId;
    	StringProperty  raffleDate;
    	IntegerProperty maxMiners;
    	IntegerProperty numWinners;
    	IntegerProperty numBenefits;
    	public Raffle(int RaffleId,Date raffleDate,int maxMiners,int numWinners ,int numBenefits){
    		
    		this.RaffleId = new SimpleIntegerProperty(RaffleId);
    		this.raffleDate = new SimpleStringProperty(raffleDate.toString());
    		this.maxMiners = new SimpleIntegerProperty(maxMiners);
    		this.numWinners = new SimpleIntegerProperty(numWinners);
    		this.numBenefits = new SimpleIntegerProperty(numBenefits);
    		
    	}
    	
        
    }

    
    
    
    
    
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	initTable();
	}
    
    
    @SuppressWarnings("unchecked")
	private void initTable() {
    	JFXTreeTableColumn<Raffle, Integer> deptColumn = new JFXTreeTableColumn<>("ID");
    	deptColumn.setPrefWidth(150);
    	deptColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Raffle, Integer> param) ->{
    	    if(deptColumn.validateValue(param)) return param.getValue().getValue().RaffleId.asObject();
    	    else return deptColumn.getComputedValue(param);
    	});
    	 
    	JFXTreeTableColumn<Raffle, String> empColumn = new JFXTreeTableColumn<>("Date");
    	empColumn.setPrefWidth(150);
    	empColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Raffle, String> param) ->{
    	    if(empColumn.validateValue(param)) return param.getValue().getValue().raffleDate;
    	    else return empColumn.getComputedValue(param);
    	});
    	 
    	JFXTreeTableColumn<Raffle, Integer> ageColumn = new JFXTreeTableColumn<>("maximum Miners");
    	ageColumn.setPrefWidth(150);
    	ageColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Raffle, Integer> param) ->{
    	    if(ageColumn.validateValue(param)) return param.getValue().getValue().maxMiners.asObject();
    	    else return ageColumn.getComputedValue(param);
    	});
    	 
    	JFXTreeTableColumn<Raffle, Integer> ageColumn1 = new JFXTreeTableColumn<>("number of possible Winners");
    	ageColumn.setPrefWidth(150);
    	ageColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Raffle, Integer> param) ->{
    	    if(ageColumn.validateValue(param)) return param.getValue().getValue().numWinners.asObject();
    	    else return ageColumn.getComputedValue(param);
    	});
    	  
    	JFXTreeTableColumn<Raffle, Integer> ageColumn2 = new JFXTreeTableColumn<>("number of benefits");
    	ageColumn.setPrefWidth(150);
    	ageColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Raffle, Integer> param) ->{
    	    if(ageColumn.validateValue(param)) return param.getValue().getValue().numBenefits.asObject();
    	    else return ageColumn.getComputedValue(param);
    	});
    	 
    	ObservableList<Raffle> raffles = FXCollections.observableArrayList();
    	for(entity.Raffle temp : RaffleLogic.getAllRaffles())
    		raffles.add(new Raffle(temp.getRaffleId(), temp.getRaffleDate(), temp.getMaxMiners(), temp.getNumWinners(), temp.getNumBenefits()));
    	final TreeItem<Raffle> root = new RecursiveTreeItem<Raffle>(raffles, RecursiveTreeObject::getChildren);
    	
    	RaffleTable.getColumns().setAll(deptColumn, empColumn, ageColumn, ageColumn1, ageColumn2);
    	RaffleTable.setRoot(root);
    	RaffleTable.setShowRoot(false);
    	RaffleTable.setEditable(false);
    	
	}


	@FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void joinRaffle(ActionEvent event) {

    }

    @FXML
    void leaveRaffle(ActionEvent event) {

    }

	

}