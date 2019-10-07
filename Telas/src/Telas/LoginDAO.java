package Telas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	
	private BD bd;
	private PreparedStatement statement;
	private ResultSet rs;
	
	
	public boolean validar(String nome, String senha) {
		boolean result = false;
		try {
			PreparedStatement comando = bd.con.prepareStatement("SELECT * FROM Usuario WHERE nome = ? AND senha = ?");
			rs = statement.executeQuery();
			comando.setString(1, nome);
			comando.setString(2, senha);
			result = true;		
		}	

		catch (SQLException e) {   
			e.getMessage();
			System.out.println(result);
			result = false;
		}   
		System.out.println(result);   
		return result;  
	}//ta caindo sempre no true
	//a logica e ...retornou false da erro, retornou true abre a tela
}
