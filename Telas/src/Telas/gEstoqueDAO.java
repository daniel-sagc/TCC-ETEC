package Telas;
import java.sql.*;

public class gEstoqueDAO {
	public gEstoque gEstoque;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;
    
    public gEstoqueDAO(){
    	bd = new BD();
    	gEstoque = new gEstoque();
    	
    	System.out.println(bd.getConnection());
    }
    
    public boolean localizar() {
        sql = "select * from Material where codigo = ?";
        try {
            statement = bd.con.prepareStatement(sql);
            statement.setString(1, gEstoque.getCodigo());
            resultSet = statement.executeQuery();
            resultSet.next();
            gEstoque.setCodigo(resultSet.getString(1));
            gEstoque.setCategoria(resultSet.getString(2));
            gEstoque.setQuantidade(resultSet.getString(3));
            gEstoque.setTipo(resultSet.getString(4));
            gEstoque.setEspecificacao(resultSet.getString(5));
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }
    
    public String atualizar(int operacao) {
        men = "Operacão realizada com sucesso!";
        try {
            if (operacao == INCLUSAO) {
                sql = "insert into Material values (?,?,?,?)";
                statement = bd.con.prepareStatement(sql);
              	statement.setString(1, gEstoque.getCategoria());
                statement.setString(2, gEstoque.getQuantidade());
                statement.setString(3, gEstoque.getTipo());
                statement.setString(4, gEstoque.getEspecificacao());
            } else if (operacao == ALTERACAO) {
                sql = "update Material set  categoriaM = ?, quantidade= ?, tipoM = ?, especificacao = ? where codigo = ?";
                statement = bd.con.prepareStatement(sql);
                
                statement.setString(5, gEstoque.getCodigo());
                statement.setString(1, gEstoque.getCategoria());
                statement.setString(2, gEstoque.getQuantidade());
                statement.setString(3, gEstoque.getTipo());
                statement.setString(4, gEstoque.getEspecificacao());
            } else if (operacao == EXCLUSAO) {
                sql = "delete from Material where codigo = ?";
                statement = bd.con.prepareStatement(sql);
                statement.setString(1, gEstoque.getCodigo());
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
