package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;

	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}


	public List<Corso> getCorsiByPeriodo(int p){
		return this.corsoDAO.getCorsiByPeriodo(p);		
	}
	
	public Map<Corso,Integer> getCorsiIscritti(int p){
		return this.corsoDAO.getCorsiIscritti(p);
	}
	
	public List<Studente> getStudentiByCorso(String c){
		return this.studenteDAO.getStudentiByCorso(c);
	}
	
	public List<Divisione> getDivisioneStudentiCorso(String c){	
		return this.studenteDAO.getDivisioneStudentiCorso(c);
	}
}
