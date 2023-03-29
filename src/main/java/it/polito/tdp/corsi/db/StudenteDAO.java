package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getStudentiByCorso(String corso) {
		
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE i.codins = ? AND i.matricola = s.matricola";
		
		List<Studente> studenti = new ArrayList<>();
		
		try {
	
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso);
			ResultSet res = st.executeQuery();
				
			while(res.next()) {
				
				int matricola = res.getInt("matricola");
				String cognome = res.getString("cognome");
				String nome = res.getString("nome");
				String CDS = res.getString("CDS");
				Studente s = new Studente(matricola, cognome, nome, CDS);
				
				studenti.add(s);
			}
			
			st.close();
			res.close();
			conn.close();
			
			return studenti;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	
	
	public List<Divisione> getDivisioneStudentiCorso(String corso){	
		
		String sql = "SELECT s.CDS, COUNT(*) AS n "
				+ "FROM studente s, iscrizione i "
				+ "WHERE i.codins = ? AND i.matricola = s.matricola "
				+ "GROUP BY s.CDS";
		
		List<Divisione> div = new ArrayList<>();
			
		try {
	
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso);
			ResultSet res = st.executeQuery();
				
			while(res.next()) {
				
				String CDS = res.getString("CDS");
				int n = res.getInt("n");
				Divisione d = new Divisione(CDS,n);

				div.add(d);
			}
			
			st.close();
			res.close();
			conn.close();
			
			return div;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
