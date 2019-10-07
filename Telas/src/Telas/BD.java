package Telas;
import java.sql.*;
public class BD{
	public Connection con = null;
	public ResultSet rs = null;
	public PreparedStatement st = null;
	private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String DATABASENAME = "sqlserver";
	private final String URL   = "jdbc:sqlserver://localhost:1433;" + "database=banco";
	private final String LOGIN = "sa";
	private final String SENHA = "etec";

	public static void main(String[] args) {
		BD bd = new BD();
		bd.getConnection();
		bd.close();
	}
	/** 
	 * método que faz conexão com o banco de dados
	 * retorna true se houve sucesso, ou false em caso negativo
	 */
	public boolean getConnection(){
		try{
			con = DriverManager.getConnection(URL,LOGIN,SENHA);
		return true;		
		}
				
		catch(SQLException erro){
				System.out.println(erro.toString());	
		return false;
		}
	}

	public void close(){
		try{
           if(rs!=null)
              rs.close();
		}
		catch(SQLException erro){}
		try{
           if(st!=null)
			  st.close();
		}
		catch(SQLException erro){} 
		try{
			con.close();
			
		}
		catch(SQLException erro){
			System.out.println(erro.toString());
		} 
	} 
}


