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
import model.bean.Funcionario;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leand
 */
public class FuncionarioDAO {
    
    //CRIA OS FUNCIONÁRIOS NO BANCO DE DADOS
    public void cadastrar(Funcionario f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO funcionario (cracha, nome, senha) VALUES (?, ?, ?)");
            stmt.setInt(1, f.getCracha());
            stmt.setString(2, f.getNome());
            stmt.setString(3, f.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso.");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: "+ ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //VERIFICAR SE O FUNCIONÁRIO ESTÁ CADASTRADO NO BANCO
    public boolean checkLogin(int cracha, String senha) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE cracha = ? and senha = ?");
            stmt.setInt(1, cracha);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }
}