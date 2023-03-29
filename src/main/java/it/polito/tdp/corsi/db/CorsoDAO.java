package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	public List<Corso> getCorsiByPeriodo(int periodo) {
		

		String sql = "SELECT * "
				+ "FROM corso "
				+ "WHERE pd = ?";
		
		List<Corso> corsi = new ArrayList<>();
		
		try {
	
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet res = st.executeQuery();
				
			while(res.next()) {
				
				String codins = res.getString("codins");
				int crediti = res.getInt("crediti");
				String nome = res.getString("nome");
				int pd = res.getInt("pd");
				Corso c = new Corso(codins, crediti, nome, pd);
				
				corsi.add(c);
			}
			
			st.close();
			res.close();
			conn.close();
			
			return corsi;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
	}
	
	public Map<Corso, Integer> getCorsiIscritti(int periodo){
	
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS n "
				+ "FROM corso c, iscrizione i "
				+ "WHERE pd = ? AND c.codins = i.codins "
				+ "GROUP BY c.codins, c.crediti, c.nome, c.pd";
		
		Map<Corso, Integer> corsi = new HashMap<>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet res = st.executeQuery();
				
			while(res.next()) {
				
				String codins = res.getString("codins");
				int crediti = res.getInt("crediti");
				String nome = res.getString("nome");
				int pd = res.getInt("pd");

				Corso c = new Corso(codins, crediti, nome, pd);
				
				corsi.put(c, res.getInt("n"));
			}
			
			st.close();
			res.close();
			conn.close();
			
			return corsi;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
