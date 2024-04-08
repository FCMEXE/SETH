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
import model.bean.Cliente;

/**
 *
 * @author leand
 */
public class ClienteDAO {
    //INSERE CLIENTES NO BANCO
    public void cadastrar(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome, email, cpf, nasc, sexo) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getCpf());
            stmt.setString(4, c.getNasc());
            stmt.setString(5, c.getSexo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //SELECIONA OS DADOS DO BANCO E MOSTRA NA LISTA
    public List<Cliente> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente c = new Cliente();
         
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setCpf(rs.getString("cpf"));
                c.setNasc(rs.getString("nasc"));
                c.setSexo(rs.getString("sexo"));
                clientes.add(c);
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir cliente: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
    
    //ATUALIZA OS PRODUTOS QUE FOREM SELECIONADOS
    public void update(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome = ?, email = ?, cpf = ?, nasc = ?, sexo = ? WHERE id = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getCpf());
            stmt.setString(4, c.getNasc());
            stmt.setString(5, c.getSexo());
            stmt.setInt(6, c.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //EXCLUI OS PRODUTOS QUE FOREM SELECIONADOS
    public void delete(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE id = ?");
            stmt.setInt(1, c.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}