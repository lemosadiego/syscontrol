/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class Conexao {

    private Connection con;

    public Connection abreConexao(){
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            //Class.forName("com.Mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/syscontroldb", "root", "itaqua");
        } catch (Exception ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void fechaConexao(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fechaConexao(Connection con, Statement... stmt){
        try {
            for(int i=0;i<stmt.length; i++){
                stmt[i].close();
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void fechaConexao(Connection con, ResultSet rs,Statement... stmt){
        try {
            for(int i=0;i<stmt.length; i++){
                stmt[i].close();
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
