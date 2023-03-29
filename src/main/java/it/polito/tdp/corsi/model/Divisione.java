package it.polito.tdp.corsi.model;

public class Divisione {
	
	private String cds;
	private int nStudenti;
	
	
	public Divisione(String cds, int nStudenti) {
		super();
		this.cds = cds;
		this.nStudenti = nStudenti;
	}


	public String getCds() {
		return cds;
	}


	public int getnStudenti() {
		return nStudenti;
	}


	@Override
	public String toString() {
		return "Divisione [cds=" + cds + ", nStudenti=" + nStudenti + "]";
	}
	
	
	
	

}
