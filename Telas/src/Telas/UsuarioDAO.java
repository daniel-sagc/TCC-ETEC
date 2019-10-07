package Telas;
import java.sql.*;

public class UsuarioDAO {

	    public Usuario usuario;
	    public BD bd;
	    private PreparedStatement statement;
	    private ResultSet resultSet;
	    private String men, sql;
	    public static final byte INCLUSAO = 1;
	    public static final byte ALTERACAO = 2;
	    public static final byte EXCLUSAO = 3;

	    public UsuarioDAO() {
	        bd = new BD();
	        usuario = new Usuario();
	        
	        
	        System.out.println(bd.getConnection());

	    }
	     
	            
	        
	        
	     
	    public boolean localizar() {
	        sql = "select * from Usuario where nome = ?";
	        try {
	            statement = bd.con.prepareStatement(sql);
	            statement.setString(1, usuario.getNome());
	            resultSet = statement.executeQuery();
	            resultSet.next();
	            usuario.setDataNasci(resultSet.getString(2));
	            usuario.setEmail(resultSet.getString(3));
	            usuario.setSenha(resultSet.getString(4));
	            usuario.setTipo(resultSet.getString(5));

	            return true;
	        } catch (SQLException erro) {
	            return false;
	        }
	    }
	    public String atualizar(int operacao) {
	        men = "Operacão realizada com sucesso!";
	        try {
	        	
	            if (operacao == INCLUSAO) {
	                statement = bd.con.prepareStatement("insert into Usuario values (?,?,?,?,?,?)");
	                statement.setString(1, usuario.getNome());
	                statement.setString(2, usuario.getEmail());
	                statement.setString(3, usuario.getDataNasci());
	                statement.setString(4, usuario.getTipo());
	                statement.setString(5, usuario.getLogin());
	                statement.setString(6, usuario.getSenha());


	            } else if (operacao == ALTERACAO) {
	                sql = "update Usuario set email = ?, dataNascimento = ?, tipo = ?,useru = ?,senha = ? where nome = ?";
	                statement = bd.con.prepareStatement(sql);
	                statement.setString(6, usuario.getNome());    
	                statement.setString(1, usuario.getEmail());
	                statement.setString(2, usuario.getDataNasci());
	                statement.setString(3, usuario.getTipo());
	                statement.setString(4, usuario.getLogin());
	                statement.setString(5, usuario.getSenha());

	              

	            } else if (operacao == EXCLUSAO) {
	                sql = "delete from Usuario where Nome = ?";
	                statement = bd.con.prepareStatement(sql);
	                statement.setString(1, usuario.getNome());
	            }
	            if (statement.executeUpdate() == 0) {
	                men = "Falha na operacao!";
	            }
	        } catch (SQLException erro) {
	            men = "Falha na operacao " + erro.toString();
	        }
	        return men;
	    }
	}
	

