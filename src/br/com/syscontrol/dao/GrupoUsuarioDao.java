/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import java.sql.Connection;

/**
 *
 * @author Diego
 */
public class GrupoUsuarioDao {

      Conexao conexao = new Conexao();
    Connection con;

//    public GrupoUsuario getGrupoUsuario(int id){
//
//        PreparedStatement pstmt;
//        ResultSet rs;
//        GrupoUsuario retorno = null;
//        String sql = "SELECT * FROM GRUPOUSUARIOS WHERE ID = ?";
//
//        try {
//            con = conexao.abreConexao();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, id);
//            rs = pstmt.executeQuery();
//
//            if(rs.next()){
//                retorno.setId(id);
//                retorno.setDescricao(rs.getString("DESCRICAO"));
//            }
//            conexao.fechaConexao(con);
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return retorno;
//
//    }
//
//    public void incluir(GrupoUsuario gu){
//
//        PreparedStatement pstmt;
//        String sql = "INSERT INTO GRUPOUSUARIOS VALUES(null,?)";
//
//        try {
//            con = conexao.abreConexao();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, gu.getDescricao());
//            pstmt.executeUpdate();
//            conexao.fechaConexao(con);
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void alterar(GrupoUsuario gu){
//
//        PreparedStatement pstmt;
//        String sql = "UPDATE GRUPOUSUARIOS SET DESCRICAO = ? WHERE ID = ?";
//
//        try {
//            con = conexao.abreConexao();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, gu.getDescricao());
//            pstmt.setInt(2, gu.getId());
//            pstmt.executeUpdate();
//            conexao.fechaConexao(con);
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void deletar(GrupoUsuario gu){
//
//        PreparedStatement pstmt;
//        String sql = "DELETE FROM GRUPOUSUARIOS WHERE ID = ?";
//
//        try {
//            con = conexao.abreConexao();
//            pstmt = con.prepareStatement(sql);          
//            pstmt.setInt(1, gu.getId());
//            pstmt.executeUpdate();
//            conexao.fechaConexao(con);
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//
//    public List getListaGrupos(){
//
//        Statement stmt;
//        ResultSet rs;
//        List lista = new ArrayList();
//        GrupoUsuario gu = null;
//        String sql = "SELECT * FROM GRUPOUSUARIOS";
//
//        try {
//            con = conexao.abreConexao();
//            stmt = con.prepareStatement(sql);
//            rs = stmt.executeQuery(sql);
//            while(rs.next()){
//                gu = new GrupoUsuario();
//                gu.setId(rs.getInt("ID"));
//                gu.setDescricao(rs.getString("DESCRICAO"));
//                lista.add(gu);
//            }
//
//            conexao.fechaConexao(con);
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    public boolean isGrupoUsuarioCadastrado(GrupoUsuario gu){
//        boolean retorno = false;
//
//        PreparedStatement pstmt;
//        ResultSet rs;
//        String sql = "SELECT * FROM GRUPOUSUARIOS WHERE DESCRICAO = ?";
//
//        try {
//            con = conexao.abreConexao();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, gu.getDescricao());
//            rs = pstmt.executeQuery();
//            while(rs.next()){
//              retorno = true;
//            }
//            conexao.fechaConexao(con);
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return retorno;
//
//    }

}
