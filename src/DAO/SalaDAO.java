package DAO;

import Control.ConnectionFactory;
import Model.SalaRaiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class SalaDAO {

   

    private Connection connection;

    private static SalaDAO salaDAO;

    public static SalaDAO getInstancia() throws SQLException {
        if (salaDAO == null) {
            salaDAO = new SalaDAO();
        }

         salaDAO.connection = ConnectionFactory.getConnection();
        
        return salaDAO;
    }

     private SalaDAO() throws SQLException {

        connection = ConnectionFactory.getConnection();
    }

    
    
    public void pesquisar(SalaRaiz sala) throws SQLException {

        connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM SALA WHERE id_Sala = ? ";

        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sala.getIdSala());
            result = pstmt.executeQuery();

            while (result.next()) {

                sala.setIdSala(result.getInt("id_Sala"));
                sala.setBloco(result.getInt("bloco"));
                sala.setNumeroSala(result.getInt("numero_Sala"));
               
                
                
            }
            
            
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }
    
    
    
    public void pesquisarPeloNumero(SalaRaiz sala) throws SQLException {

        connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM SALA WHERE numero_Sala = ? ";

        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sala.getNumeroSala());
            result = pstmt.executeQuery();

            while (result.next()) {

                sala.setIdSala(result.getInt("id_Sala"));
                sala.setBloco(result.getInt("bloco"));
                sala.setNumeroSala(result.getInt("numero_Sala"));
                 
            }
          
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }
    
    
    public void adicionar(SalaRaiz sala) throws SQLException {

        String sql = "INSERT INTO SALA(bloco, numero_Sala) VALUES(?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sala.getBloco());
            pstmt.setInt(2, sala.getNumeroSala());
            
            
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }
    
    
     
     public void alterar(SalaRaiz sala) throws SQLException {

        String sql = "UPDATE SALA SET bloco=?, numero_Sala=? WHERE id_Sala=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sala.getBloco());
            pstmt.setInt(2, sala.getNumeroSala());
            pstmt.setInt(3, sala.getIdSala());
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }

     
     
     public void deletar(Integer id) throws SQLException {

        String sql = "DELETE FROM SALA WHERE id_Sala=? ";

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
 
     
     
     
    public ArrayList<SalaRaiz> listar() {

        String sql = "SELECT * FROM SALA";

        try {
            
           
            
            ArrayList<SalaRaiz> salas = new ArrayList<SalaRaiz>();
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            ResultSet rlst = pstmt.executeQuery();
            while (rlst.next()) {
                SalaRaiz sr = new SalaRaiz();
                sr.setIdSala(rlst.getInt("id_Sala"));
                sr.setBloco(rlst.getInt("bloco"));
                sr.setNumeroSala(rlst.getInt("numero_Sala"));
                
                
                
                salas.add(sr);
            
            }
            rlst.close();
            pstmt.close();
            connection.close();
            
           
            
            return salas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
 
    
     
    
}
