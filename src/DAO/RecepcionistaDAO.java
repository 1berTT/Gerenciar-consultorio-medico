package DAO;

import Control.ConnectionFactory;
import Model.RecepcionistaRaiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecepcionistaDAO {

    private Connection connection;
    private static RecepcionistaDAO recepcionistaDAO;

    public static RecepcionistaDAO getInstancia() throws SQLException {
        if (recepcionistaDAO == null) {
            recepcionistaDAO = new RecepcionistaDAO();
        }

        recepcionistaDAO.connection = ConnectionFactory.getConnection();
        
        return recepcionistaDAO;
    }

    private RecepcionistaDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public void pesquisar(RecepcionistaRaiz recepcionista) throws SQLException {

        connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM RECEPCIONISTA WHERE id_Recepcionista = ? ";

        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, recepcionista.getIdRecepcionista());
            result = pstmt.executeQuery();

            while (result.next()) {
                recepcionista.setIdRecepcionista(result.getInt("id_Recepcionista"));
                recepcionista.setNomeRecepcionista(result.getString("nome_Recepcionista"));
                recepcionista.setTelefone(result.getString("telefone"));
                recepcionista.setEmail(result.getString("email"));
                recepcionista.setSenha(result.getString("senha"));
            }

            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }

    
    public void pesquisarPeloEmail(RecepcionistaRaiz recepcionista) throws SQLException {

        connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM RECEPCIONISTA WHERE email = ? ";

        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, recepcionista.getEmail());
            result = pstmt.executeQuery();

            while (result.next()) {
                recepcionista.setIdRecepcionista(result.getInt("id_Recepcionista"));
                recepcionista.setNomeRecepcionista(result.getString("nome_Recepcionista"));
                recepcionista.setTelefone(result.getString("telefone"));
                recepcionista.setEmail(result.getString("email"));
                recepcionista.setSenha(result.getString("senha"));
            }

            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }
    
    
    
    public void adicionar(RecepcionistaRaiz recepcionista) throws SQLException {

        String sql = "INSERT INTO RECEPCIONISTA(nome_Recepcionista, telefone, email, senha) VALUES(?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, recepcionista.getNomeRecepcionista());
            pstmt.setString(2, recepcionista.getTelefone());
            pstmt.setString(3, recepcionista.getEmail());
            pstmt.setString(4, recepcionista.getSenha());

            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }

    public void alterar(RecepcionistaRaiz recepcionista) throws SQLException {

        String sql = "UPDATE RECEPCIONISTA SET nome_Recepcionista=?, telefone=?, email=?, senha=? WHERE id_Recepcionista=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, recepcionista.getNomeRecepcionista());
            pstmt.setString(2, recepcionista.getTelefone());
            pstmt.setString(3, recepcionista.getEmail());
            pstmt.setString(4, recepcionista.getSenha());
            pstmt.setInt(5, recepcionista.getIdRecepcionista());
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }

    public void deletar(Integer id) throws SQLException {

        String sql = "DELETE FROM RECEPCIONISTA WHERE id_Recepcionista=? ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }
    }

    public ArrayList<RecepcionistaRaiz> listar() {

        String sql = "SELECT * FROM RECEPCIONISTA";

        try {

            ArrayList<RecepcionistaRaiz> recepcionistas = new ArrayList<RecepcionistaRaiz>();
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            ResultSet rlst = pstmt.executeQuery();
            while (rlst.next()) {
                RecepcionistaRaiz rr = new RecepcionistaRaiz();
                rr.setIdRecepcionista(rlst.getInt("id_Recepcionista"));
                rr.setNomeRecepcionista(rlst.getString("nome_Recepcionista"));
                rr.setTelefone(rlst.getString("telefone"));
                rr.setEmail(rlst.getString("email"));
                rr.setSenha(rlst.getString("senha"));

                recepcionistas.add(rr);

            }
            rlst.close();
            pstmt.close();
            connection.close();

            return recepcionistas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
