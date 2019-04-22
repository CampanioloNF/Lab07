package it.polito.tdp.poweroutages;
import java.net.URL;
import java.util.ResourceBundle;


import it.polito.tdp.poweroutages.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class PowerOutagesController {

	
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ChoiceBox<Nerc> btnNerc;

	    @FXML
	    private TextField txtMaxYears;

	    @FXML
	    private TextField txtMaxHours;

	    @FXML
	    private Button btnWorstCase;

	    @FXML
	    private TextArea txtResult;

	    @FXML
	    private Label txtMessage;

		private Model model;
		
		private ObservableList<Nerc> comboItems;

	    @FXML
	    void doWorstCase(ActionEvent event) {

	    
	    	this.txtResult.clear();
	    	this.txtMessage.setText("");
	    	
	    	try {
	    		
	    		Nerc nerc = this.btnNerc.getValue();
	    		
	    		int anni = Integer.parseInt(this.txtMaxYears.getText());
	    		int ore = Integer.parseInt(this.txtMaxHours.getText());
	    		
	    		String result = this.model.worstCase(nerc,anni, ore);
	    		
	    		this.txtResult.setText(result);
	    		
	    		
	    	}catch(Exception e) {
	    		this.txtMessage.setText("ERRORE: inserire un NERC, e due NUMERI (INTERI) nella casella Max years e Max hours");
	    	}
	    	
	    	
	    }

	    @FXML
	    void initialize() {
	        assert btnNerc != null : "fx:id=\"btnNerc\" was not injected: check your FXML file 'PowerOutages.fxml'.";
	        assert txtMaxYears != null : "fx:id=\"txtMaxYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
	        assert txtMaxHours != null : "fx:id=\"txtMaxHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
	        assert btnWorstCase != null : "fx:id=\"btnWorstCase\" was not injected: check your FXML file 'PowerOutages.fxml'.";
	        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";
	        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'PowerOutages.fxml'.";

	    }

		public void setModel(Model model) {
			this.model=model;
			
		}

		public void caricaBox() {
			comboItems = FXCollections.observableList(model.getNercList());
			this.btnNerc.setItems(comboItems);
			
		}
	}

