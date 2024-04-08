/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Vendas;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leand
 */
public class VendasDAO {
    
    //CRIA AS VENDAS NO BANCO DE DADOS
    public void cadastrar(Vendas v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO vendas (cracha, dataCompra, idProduto, desconto, preco) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, v.getCracha());
            stmt.setString(2, v.getDataCompra());
            stmt.setInt(3, v.getIdProduto());
            stmt.setDouble(4, v.getDesconto());
            stmt.setDouble(5, v.getPreco());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso.");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar venda: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //SELECIONA OS DADOS DO BANCO E MOSTRA NA LISTA
    public List<Vendas> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendas> venda = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vendas");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Vendas v = new Vendas();
         
                v.setId(rs.getInt("id"));
                v.setCracha(rs.getInt("cracha"));
                v.setDataCompra(rs.getString("dataCompra"));
                v.setIdProduto(rs.getInt("idProduto"));
                v.setDesconto(rs.getDouble("desconto"));
                v.setPreco(rs.getDouble("preco"));
                venda.add(v);
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir venda: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return venda;
    }
    
    //ATUALIZA AS VENDAS QUE FOREM SELECIONADOS
    public void update(Vendas v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE vendas SET cracha = ?, dataCompra = ?, idProduto = ?, desconto = ?, preco = ? WHERE id = ?");
            stmt.setInt(1, v.getCracha());
            stmt.setString(2, v.getDataCompra());
            stmt.setInt(3, v.getIdProduto());
            stmt.setDouble(4, v.getDesconto());
            stmt.setDouble(5, v.getPreco());
            stmt.setInt(6, v.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso.");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar venda: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }    
    
    //EXCLUI AS VENDAS QUE FOREM SELECIONADOS
    public void delete(Vendas v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM vendas WHERE id = ?");
            stmt.setInt(1, v.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Venda excluído com sucesso.");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir venda: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}