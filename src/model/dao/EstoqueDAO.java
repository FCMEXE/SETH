/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Estoque;

/**
 *
 * @author leand
 */
public class EstoqueDAO {
    
    //INSERE DADOS NO BANCO
    public void cadastrar(Estoque e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO estoque (nomePeca, qtd, tamanhos, cores, preco) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, e.getNomePeca());
            stmt.setInt(2, e.getQtd());
            stmt.setString(3, e.getTamanhos());
            stmt.setString(4, e.getCores());
            stmt.setDouble(5, e.getPreco());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //SELECIONA OS DADOS DO BANCO E MOSTRA NA LISTA
    public List<Estoque> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Estoque> estoques = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM estoque");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Estoque e = new Estoque();
         
                e.setId(rs.getInt("id"));
                e.setNomePeca(rs.getString("nomePeca"));
                e.setQtd(rs.getInt("qtd"));
                e.setTamanhos(rs.getString("tamanhos"));
                e.setCores(rs.getString("cores"));
                e.setPreco(rs.getDouble("preco"));
                estoques.add(e);
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir produto: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return estoques;
    }
    
    //ATUALIZA OS PRODUTOS QUE FOREM SELECIONADOS
    public void update(Estoque e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE estoque SET nomePeca = ?, qtd = ?, tamanhos = ?, cores = ?, preco = ? WHERE id = ?");
            stmt.setString(1, e.getNomePeca());
            stmt.setInt(2, e.getQtd());
            stmt.setString(3, e.getTamanhos());
            stmt.setString(4, e.getCores());
            stmt.setDouble(5, e.getPreco());
            stmt.setInt(6, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso.");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //EXCLUI OS PRODUTOS QUE FOREM SELECIONADOS
    public void delete(Estoque e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM estoque WHERE id = ?");
            stmt.setInt(1, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso.");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}