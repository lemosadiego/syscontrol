/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package br.com.syscontrol.dao;


/**
 *
 * @author Diego
 */
public class PagamentoPedidoDao {

//    Conexao conexao = new Conexao();
//
//    //Retorna o ultimo pedido cadastrado para auxiliar no metodo incluirItensPedido
//    public Long getUltimoPagamento() throws SQLException{
//
//         String sql = "SELECT MAX(IDPAGAMENTO) FROM PAGAMENTOPEDIDO";
//         ResultSet rs = null;
//         Long retorno = null;
//
//        Connection con = conexao.abreConexao();
//        try {
//            Statement stmt = con.createStatement();
//            rs =stmt.executeQuery(sql);
//
//            if(rs.next()){
//                retorno =  rs.getLong(1);
//            }else{
//                retorno = null;
//            }
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }
//        conexao.fechaConexao(con);
//        return retorno;
//    }
//
//    //Grava informaçoes na tabela de relacionamento muitos para muitos , PEDIDOSSERVICOS
//    public void incluirPagamentoPedido(Pedido pedido) throws SQLException{
//
//        String sql = "INSERT INTO PAGAMENTOPEDIDO VALUES("
//                + "?,"
//                + "?,"
//                + "?,"
//                + "?,"
//                + "?,"
//                + "?)";
//
//        Connection conPedido = conexao.abreConexao();
//        PreparedStatement pstmt;
//
//        ArrayList<Parcela> parcelas;
//
//        if(pedido.getFormaPagamento() == null){
//            Parcela p = new Parcela();            
//            p.setNumeroParcela(1);
//            p.setDataVencimento(new Util().getDataHoje());
//            p.setValor(pedido.getValorPedido());
//            FormaPagamento fp = new FormaPagamento();
//            pedido.setFormaPagamento(fp);
//            pedido.getFormaPagamento().getParcelas().add(p);
//            parcelas = pedido.getFormaPagamento().getParcelas();
//        }else{            
//            parcelas = pedido.getFormaPagamento().getParcelas();
//        }
//
//            try {
//               for(Object o : parcelas){
//                    Parcela p = (Parcela) o;
//                    pstmt = conPedido.prepareStatement(sql);
//                    pstmt.setLong(1, pedido.getIdPedido());
//                    pstmt.setLong(2, p.getNumeroParcela());
//                    if(p.getDataVencimento().indexOf("-")!=-1){
//                        pstmt.setDate(3, java.sql.Date.valueOf(p.getDataVencimento()));
//                    }else{
//                        pstmt.setDate(3, java.sql.Date.valueOf(new Util().formataSqlDate(p.getDataVencimento())));
//                    }
//                    
//                    pstmt.setDouble(4, p.getValor());
//                    pstmt.setDouble(5, pedido.getFormaPagamento().getValorEntrada());
//
//                    if(pedido.getFormaPagamento().isCalculoAutomatico()){
//                        pstmt.setInt(6, 1);
//                    }else{
//                        pstmt.setInt(6, 0);
//                    }
//
//                    pstmt.executeUpdate();
//                 }
//            }catch (SQLException ex) {
//                throw new SQLException(ex);
//            }        
//        conexao.fechaConexao(conPedido);
//    }
//
//   
//   
//    public void removerParcela(int idFormaPagamento, int numeroParcela) throws SQLException {
//
//        String sql = "DELETE FROM PAGAMENTOPEDIDO WHERE IDFORMAPAGAMENTO = ? AND NUMERO PARCELA = ?";
//        Connection conPedido = conexao.abreConexao();
//        PreparedStatement pstmt;
//
//            try {
//                    pstmt = conPedido.prepareStatement(sql);
//                    pstmt.setInt(1, idFormaPagamento);
//                    pstmt.setInt(2, numeroParcela);
//                    pstmt.executeUpdate();
//            }catch (SQLException ex) {
//                throw new SQLException(ex);
//            }
//        conexao.fechaConexao(conPedido);
//    }
//
//    @Override
//    public void alterar(Object o) throws SQLException {
//        Pedido pedido = (Pedido) o;
//        this.remover(o);
//        this.incluirPagamentoPedido(pedido);
//    }
//
//    @Override
//    public Collection consultarPorNome(Object o) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Object consultarPorId(Object o) throws SQLException {
//
//         String sql = "SELECT * FROM PAGAMENTOPEDIDO WHERE IDPEDIDO = ";
//         ResultSet rs = null;
//         Collection parcelas = new ArrayList();
//         Connection con = conexao.abreConexao();
//         FormaPagamento fp = new FormaPagamento();
//         Pedido pedido = (Pedido) o;
//
//        Parcela p;
//        try {
//            Statement stmt = con.createStatement();
//            sql = sql + String.valueOf(pedido.getIdPedido());
//            rs =stmt.executeQuery(sql);
//
//            while(rs.next()){
//                p = new Parcela();
//                p.setNumeroParcela(rs.getInt("NUMEROPARCELA"));
//                p.setDataVencimento(rs.getDate("DATAVENCIMENTO").toString());
//                p.setValor(rs.getDouble("VALORPARCELA"));
//                int calculoAutomatico = rs.getInt("CALCULOAUTOMATICO");
//                if(calculoAutomatico==0){
//                    fp.setCalculoAutomatico(false);
//                }else{
//                    fp.setCalculoAutomatico(true);
//                }
//                fp.setValorEntrada(rs.getDouble("VALORENTRADA"));
//                parcelas.add(p);
//            }
//
//            fp.setValorTotal(pedido.getValorPedido());
//            fp.setParcelas((ArrayList<Parcela>) parcelas);
//            pedido.setFormaPagamento(fp);
//        }catch (SQLException ex) {
//                throw new SQLException(ex);
//         }
//        return pedido;
//    }
//
//    @Override
//    public void inserir(Object o) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//   
//
//    @Override
//    public Collection getLista() throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public boolean isObjetoExistente(Object o) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//    
//
//    
//
//   
//    @Override
//    public void remover(Object o) throws SQLException {
//        Pedido pedido = (Pedido) o;
//        String sql = "DELETE FROM PAGAMENTOPEDIDO WHERE IDPEDIDO = ?";
//        Connection conPedido = conexao.abreConexao();
//        PreparedStatement pstmt;
//
//            try {
//                    pstmt = conPedido.prepareStatement(sql);
//                    pstmt.setLong(1, pedido.getIdPedido());
//                    pstmt.executeUpdate();
//            }catch (SQLException ex) {
//                throw new SQLException(ex);
//            }
//        conexao.fechaConexao(conPedido);
//    }



}

