/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	
    	String periodo = this.txtPeriodo.getText();
    	
    	int p;
    	
    	try {
    		p = Integer.parseInt(periodo);
    	}catch(NumberFormatException e) {
    		this.txtRisultato.setText("Inserire un valore intero!");
    		return;
    	}
    	
    	if(p < 1 || p > 2) {
    		this.txtRisultato.setText("Inserire 1 o 2");
    		return;
    	}
    	
    	List<Corso> result = new ArrayList<>();
    	result = model.getCorsiByPeriodo(p);
    	
    	txtRisultato.clear();
    	txtRisultato.setText("Ho trovato "+ result.size() +" corsi.\n");
    	
    	for(Corso c: result) {
    		txtRisultato.appendText(""+c+"\n");
    	}
    	
    }

    @FXML
    void numeroStudenti(ActionEvent event) {
    	
    	String periodo = this.txtPeriodo.getText();
    	
    	int p;
    	
    	try {
    		p = Integer.parseInt(periodo);
    	}catch(NumberFormatException e) {
    		this.txtRisultato.setText("Inserire un valore intero!");
    		return;
    	}
    	
    	if(p < 1 || p > 2) {
    		this.txtRisultato.setText("Inserire 1 o 2");
    		return;
    	}
    	
    	Map<Corso,Integer> result = new HashMap<>();
    	result = model.getCorsiIscritti(p);
    	
    	txtRisultato.clear();
    	txtRisultato.setText("Ho trovato "+ result.size() +" corsi.\n");
    	
    	for(Map.Entry<Corso, Integer> entry : result.entrySet()){
    		txtRisultato.appendText(entry.getKey()+" Iscritti: "+entry.getValue()+"\n");
    	}
    	
    	
    	
    }

    @FXML
    void stampaDivisione(ActionEvent event) {
    	
    	String corso = this.txtCorso.getText();
    	
    	if(corso.isEmpty()) {
    		txtRisultato.setText("Inserire il codice di un corso.");
    		return;
    	}
    	
    	List<Divisione> result = new ArrayList<>();
    	

    	result = model.getDivisioneStudentiCorso(corso);
    	
    	txtRisultato.clear();
    	txtRisultato.setText("Ho trovato "+ result.size() +" CDS diversi per il corso "+corso+".\n");
    	
    	for(Divisione d: result) {
    		txtRisultato.appendText(""+d+"\n");
    	}
    	

    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	
    	String corso = this.txtCorso.getText();
    	
    	if(corso.isEmpty()) {
    		txtRisultato.setText("Inserire il codice di un corso.");
    		return;
    	}
    	
    	List<Studente> result = new ArrayList<>();
    	
    	result = model.getStudentiByCorso(corso);
    	
    	txtRisultato.clear();
    	txtRisultato.setText("Ho trovato "+ result.size() +" studenti.\n");
    	
    	for(Studente s: result) {
    		txtRisultato.appendText(""+s+"\n");
    	}


    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
}