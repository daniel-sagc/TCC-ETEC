package Telas;
import java.sql.*;
public class DispositivoDAO {
    public Dispositivos dispo;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;

    public DispositivoDAO() {
        bd = new BD();
        dispo = new  Dispositivos();
        
        System.out.println(bd.getConnection());
    }
    
        

    public boolean localizar() {
        sql = "select * from Dispositivos where nPatrimonio = ?";
        try {
            statement = bd.con.prepareStatement(sql);
            statement.setString(1, dispo.getPatrimonio());
            resultSet = statement.executeQuery();
            resultSet.next();
            dispo.setPatrimonio(resultSet.getString(1));
            dispo.setNome(resultSet.getString(2));
            dispo.setModelo(resultSet.getString(3));
            dispo.setLocal(resultSet.getString(4));
            dispo.setEspeci(resultSet.getString(5));
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }
    public String atualizar(int operacao) {
        men = "Operacão realizada com sucesso!";
        try {
            if (operacao == INCLUSAO) {
                sql = "insert into Dispositivos values (?,?,?,?,?)";
                statement = bd.con.prepareStatement(sql);
              	statement.setString(1, dispo.getPatrimonio());
                statement.setString(2, dispo.getNome());
                statement.setString(3, dispo.getModelo());
                statement.setString(4, dispo.getLocal());
                statement.setString(5, dispo.getEspeci());
            } else if (operacao == ALTERACAO) {
                sql = "update Dispositivos set  tipo = ?, modelo = ?, locall = ? where nPatrimonio = ?";
                statement = bd.con.prepareStatement(sql);
                statement.setString(4, dispo.getPatrimonio());
                statement.setString(1, dispo.getNome());
                statement.setString(2, dispo.getModelo());
                statement.setString(3, dispo.getLocal());
            } else if (operacao == EXCLUSAO) {
                sql = "delete from Dispositivos where nPatrimonio = ?";
                statement = bd.con.prepareStatement(sql);
                statement.setString(1, dispo.getPatrimonio());
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