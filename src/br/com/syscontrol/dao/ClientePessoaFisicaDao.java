/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.ClientePessoaFisica;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Diego
 */
public class ClientePessoaFisicaDao implements IClientePessoaFisicaDAO<ClientePessoaFisica> {

    private static final Logger LOG = Logger.getLogger("ClientePessoaFisicaDao"); 
    private final Session session;   
    
    public ClientePessoaFisicaDao() {
        this.session = HibernateFactory.getSession();    
    }

    
    @Override
    public void salvar(ClientePessoaFisica clientePessoaFisica) throws SQLException {
        LOG.info("Salvando o  Cliente Fisica Nome [" + clientePessoaFisica.getNome() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(clientePessoaFisica);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao salvar o Cliente Fisica",ex);
            throw new SQLException();
        }
    }

    
    @Override
    public void remover(ClientePessoaFisica clientePessoaFisica) throws SQLException {
        LOG.info("Removendo o  Cliente  Id [" + clientePessoaFisica.getIdCliente()+ "]");
        try{
            Transaction tx = session.beginTransaction();
            session.delete(clientePessoaFisica);
            tx.commit();
        }catch(Exception ex){
            LOG.error("Falha ao remover o Cliente ",ex);
            throw new SQLException();
        }
    }


    @Override
    public void atualizar(ClientePessoaFisica clientePessoaFisica) throws SQLException {
        LOG.info("Iniciando atualização do Cliente  Id [" + clientePessoaFisica.getIdCliente()+ "]");
        try{
             Transaction tx = session.beginTransaction();
             session.update(clientePessoaFisica);
             tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao remover o Cliente ",ex);
            throw new SQLException();
        }
    }

    @Override
    public ClientePessoaFisica buscarPorNome(String nome) throws SQLException {
        LOG.info("Iniciando busca de Cliente  Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.ilike("nome", "%"+ nome+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoaFisica> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ClientePessoaFisica();
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de de Cliente  Nome [" + nome + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaFisica> buscarListaPorNome(String nome) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente s por Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.ilike("nome", "%"+nome+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaFisica> listaClientess = criteria.list();
                LOG.info("Encontrados [" + listaClientess.size() + "] Clientes" ) ;
               return listaClientess;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente por Nome",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaFisica> buscarListaPorBairro(String bairro) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente s por Bairro [" + bairro + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.ilike("bairro", "%"+ bairro+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaFisica> listaClientess = criteria.list();
                LOG.info("Encontrados [" + listaClientess.size() + "] Clientes" ) ;
               return listaClientess;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Clientes por Bairro",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaFisica> buscarListaPorCpf(String cpf) throws SQLException {
       LOG.info("Iniciando busca Lista de todos os Cliente s por CNPJ [" + cpf + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.ilike("cpf", "%"+ cpf+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaFisica> listaClientess = criteria.list();
                LOG.info("Encontrados [" + listaClientess.size() + "] Clientes" ) ;
               return listaClientess;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Clientes por CPF",ex);
            throw new SQLException();
        }
    }
    
     @Override
    public List<ClientePessoaFisica> buscarListaPorRg(String rg) throws SQLException {
       LOG.info("Iniciando busca Lista de todos os Cliente s por RG [" + rg + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.ilike("rg", "%"+ rg+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaFisica> listaClientess = criteria.list();
                LOG.info("Encontrados [" + listaClientess.size() + "] Clientes" ) ;
               return listaClientess;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente s por RG",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaFisica> buscarListaPorEndereco(String endereco) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Clientes por Endereco [" + endereco + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.ilike("endereco", "%"+ endereco +"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaFisica> listaClientess = criteria.list();
                LOG.info("Encontrados [" + listaClientess.size() + "] Clientes" ) ;
               return listaClientess;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Clientes por Endereco",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaFisica> buscarListaPorTelefoneComercial(String telefoneComercial) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Clientes por Telefone Comercial [" + telefoneComercial + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.ilike("telefoneComercial", "%"+ telefoneComercial+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaFisica> listaClientess = criteria.list();
                LOG.info("Encontrados [" + listaClientess.size() + "] Clientes" ) ;
               return listaClientess;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente s por Telefone Comercial",ex);
            throw new SQLException();
        }
    }
    
    @Override
    public List<ClientePessoaFisica> buscarListaPorTelefoneCelular(String telefoneCelular) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Clientes por Telefone Celular [" + telefoneCelular + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.ilike("telefoneCelular", "%"+ telefoneCelular+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaFisica> listaClientess = criteria.list();
                LOG.info("Encontrados [" + listaClientess.size() + "] Clientes" ) ;
               return listaClientess;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente s por Telefone Celular",ex);
            throw new SQLException();
        }
    }

    @Override
    public ClientePessoaFisica buscarPorId(Long id) throws SQLException {
        LOG.info("Iniciando busca de Cliente  por Id [" + id + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.eq("id", id));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoaFisica> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ClientePessoaFisica();
                }else{
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Cliente  por Id",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaFisica> buscarTodos() throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente s ");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaFisica> listaClientess = criteria.list();
                LOG.info("Encontrados [" + listaClientess.size() + "] Clientes s" ) ;
               return listaClientess;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente s ",ex);
            throw new SQLException();
        }
    }

    
    @Override
    public boolean isCpfExistente(ClientePessoaFisica clientePessoaFisica) throws SQLException {
        LOG.info("Iniciando validação de CNPJ Existente para CNPJ [" + clientePessoaFisica.getCpf() + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.eq("cpf", clientePessoaFisica.getCpf()));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoaFisica> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return false;
                }else{
                        return true;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar validação de CNPJ Existente para Cpf [" + clientePessoaFisica.getCpf() + "]",ex);
            throw new SQLException();
        }
    }
    
    @Override
    public boolean isRgExistente(ClientePessoaFisica clientePessoaFisica) throws SQLException {
        LOG.info("Iniciando validação de RG Existente Numero [" + clientePessoaFisica.getRg() + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.eq("cpf", clientePessoaFisica.getCpf()));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoaFisica> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return false;
                }else{
                        return true;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar validação de RG Existente para RG [" + clientePessoaFisica.getRg() + "]",ex);
            throw new SQLException();
        }
    }

    
    @Override
    public boolean isNomeExistente(ClientePessoaFisica clientePessoaFisica) throws SQLException {
         LOG.info("Iniciando validação de Nome Existente para Nome [" + clientePessoaFisica.getNome() + "]");
        Criteria criteria = session.createCriteria(ClientePessoaFisica.class);
        criteria.add(Restrictions.eq("nome", clientePessoaFisica.getNome()));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoaFisica> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return false;
                }else{
                        return true;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar validação de Nome Existente para Nome [" + clientePessoaFisica.getNome() + "]",ex);
            throw new SQLException();
        }
    }   


    


//
//    Conexao conexao = new Conexao();
//    Connection con;
//
//    //Pega o ClientePessoa pelo ID
//    public ClientePessoaFisica getClientePessoaPorCodigo(int codigoCliente) throws SQLException{
//
//        PreparedStatement pstmt;
//        ResultSet rs;
//        ClientePessoaFisica cp = null;
//        String sql = "SELECT * FROM CLIENTES WHERE ID = ?";
//
//            con = conexao.abreConexao();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setLong(1, codigoCliente);
//            rs = pstmt.executeQuery();
//
//            while(rs.next()){
//                cp = new ClientePessoaFisica();
//                cp.setId(codigoCliente);
//                cp.setNome(rs.getString("NOME"));
//                cp.setEndereco(rs.getString("ENDERECO"));
//                cp.setBairro(rs.getString("BAIRRO"));
//                cp.setCidade(rs.getString("CIDADE"));
//                cp.setCep(rs.getString("CEP"));
//                cp.setUf(rs.getString("UF"));
//                cp.setCpf(rs.getString("CPF"));
//                cp.setRg(rs.getString("RG"));
//                cp.setObs(rs.getString("OBSERVACOES"));
//                cp.setTipoCliente(rs.getInt("IDTIPOCLIENTE"));
//                cp.setTelefoneResidencial(rs.getString("TELEFONERESIDENCIAL"));
//                cp.setTelefoneFax(rs.getString("TELEFONEFAX"));
//                cp.setTelefoneCelular(rs.getString("TELEFONECELULAR"));
//                cp.setTelefoneComercial(rs.getString("TELEFONECOMERCIAL"));
//                cp.setContato(rs.getString("NOMERESPONSAVEL"));
//                cp.setTelefoneContato(rs.getString("TELEFONERESPONSAVEL"));
//                cp.setEmail(rs.getString("EMAIL"));
//            }
//            conexao.fechaConexao(con);
//
//        return cp;
//    }
//
//    //Pega o ClientePessoa pelo Nome
//    public ClientePessoaFisica getClientePessoaPorNome(String nome) throws SQLException{
//
//        Statement stmt;
//        ResultSet rs;
//        ClientePessoaFisica cp = null;
//        String sql = "SELECT * FROM CLIENTES WHERE NOME LIKE '%"+nome+"%' AND IDTIPOCLIENTE = 3";
//
//            con = conexao.abreConexao();
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()){
//                cp = new ClientePessoaFisica();
//                cp.setId(rs.getLong("ID"));
//                cp.setNome(rs.getString("NOME"));
//                cp.setEndereco(rs.getString("ENDERECO"));
//                cp.setBairro(rs.getString("BAIRRO"));
//                cp.setCidade(rs.getString("CIDADE"));
//                cp.setCep(rs.getString("CEP"));
//                cp.setUf(rs.getString("UF"));
//                cp.setCpf(rs.getString("CPF"));
//                cp.setRg(rs.getString("RG"));
//                cp.setObs(rs.getString("OBSERVACOES"));
//                cp.setTipoCliente(rs.getInt("IDTIPOCLIENTE"));
//                cp.setTelefoneResidencial(rs.getString("TELEFONERESIDENCIAL"));
//                cp.setTelefoneFax(rs.getString("TELEFONEFAX"));
//                cp.setTelefoneCelular(rs.getString("TELEFONECELULAR"));
//                cp.setTelefoneComercial(rs.getString("TELEFONECOMERCIAL"));
//                cp.setContato(rs.getString("NOMERESPONSAVEL"));
//                cp.setTelefoneContato(rs.getString("TELEFONERESPONSAVEL"));
//                cp.setEmail(rs.getString("EMAIL"));
//            }
//            conexao.fechaConexao(con);
//
//        return cp;
//    }
//
//    //Pega Lista de ClientePessoa pelo cnpj
//    public List getClientePessoaPorCpf(String cpf) throws SQLException{
//
//        Statement stmt;
//        ResultSet rs;
//        ArrayList retorno = new ArrayList();
//        ClientePessoaFisica cp = null;
//        String sql = "SELECT * FROM CLIENTES WHERE CPF LIKE '%"+cpf+"%' AND IDTIPOCLIENTE = 3";
//
//            con = conexao.abreConexao();
//            stmt = con.createStatement();
//
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()){
//                cp = new ClientePessoaFisica();
//                cp.setId(rs.getLong("ID"));
//                cp.setNome(rs.getString("NOME"));
//                cp.setEndereco(rs.getString("ENDERECO"));
//                cp.setBairro(rs.getString("BAIRRO"));
//                cp.setCidade(rs.getString("CIDADE"));
//                cp.setCep(rs.getString("CEP"));
//                cp.setUf(rs.getString("UF"));
//                cp.setCpf(rs.getString("CPF"));
//                cp.setRg(rs.getString("RG"));
//                cp.setObs(rs.getString("OBSERVACOES"));
//                cp.setTipoCliente(rs.getInt("IDTIPOCLIENTE"));
//                cp.setTelefoneResidencial(rs.getString("TELEFONERESIDENCIAL"));
//                cp.setTelefoneFax(rs.getString("TELEFONEFAX"));
//                cp.setTelefoneCelular(rs.getString("TELEFONECELULAR"));
//                cp.setTelefoneComercial(rs.getString("TELEFONECOMERCIAL"));
//                cp.setContato(rs.getString("NOMERESPONSAVEL"));
//                cp.setTelefoneContato(rs.getString("TELEFONERESPONSAVEL"));
//                cp.setEmail(rs.getString("EMAIL"));
//                retorno.add(cp);
//            }
//            conexao.fechaConexao(con);
//
//        return retorno;
//    }
//
//    //Pega Lista de ClientePessoa pelo Telefone Comercial
//    public List getClientePessoaPorTelefoneComercial(String tel) throws SQLException{
//
//        Statement stmt;
//        ResultSet rs;
//        ArrayList retorno = new ArrayList();
//        ClientePessoaFisica cp = null;
//        String sql = "SELECT * FROM CLIENTES WHERE TELEFONECOMERCIAL LIKE '%"+tel+"%' AND IDTIPOCLIENTE = 3";
//
//            con = conexao.abreConexao();
//            stmt = con.createStatement();
//
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()){
//                cp = new ClientePessoaFisica();
//                cp.setId(rs.getLong("ID"));
//                cp.setNome(rs.getString("NOME"));
//                cp.setEndereco(rs.getString("ENDERECO"));
//                cp.setBairro(rs.getString("BAIRRO"));
//                cp.setCidade(rs.getString("CIDADE"));
//                cp.setCep(rs.getString("CEP"));
//                cp.setUf(rs.getString("UF"));
//                cp.setCpf(rs.getString("CPF"));
//                cp.setRg(rs.getString("RG"));
//                cp.setObs(rs.getString("OBSERVACOES"));
//                cp.setTipoCliente(rs.getInt("IDTIPOCLIENTE"));
//                cp.setTelefoneResidencial(rs.getString("TELEFONERESIDENCIAL"));
//                cp.setTelefoneFax(rs.getString("TELEFONEFAX"));
//                cp.setTelefoneCelular(rs.getString("TELEFONECELULAR"));
//                cp.setTelefoneComercial(rs.getString("TELEFONECOMERCIAL"));
//                cp.setContato(rs.getString("NOMERESPONSAVEL"));
//                cp.setTelefoneContato(rs.getString("TELEFONERESPONSAVEL"));
//                cp.setEmail(rs.getString("EMAIL"));
//                retorno.add(cp);
//            }
//            conexao.fechaConexao(con);
//
//        return retorno;
//    }
//
//    //Pega Lista de ClientePessoa pelo Telefone Celularl
//    public List getClientePessoaPorTelefoneCelular(String tel) throws SQLException{
//
//        Statement stmt;
//        ResultSet rs;
//        ArrayList retorno = new ArrayList();
//        ClientePessoaFisica cp = null;
//        String sql = "SELECT * FROM CLIENTES WHERE TELEFONEFAX LIKE '%"+tel+"%' AND IDTIPOCLIENTE = 3";
//
//            con = conexao.abreConexao();
//            stmt = con.createStatement();
//
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()){
//                cp = new ClientePessoaFisica();
//                cp.setId(rs.getLong("ID"));
//                cp.setNome(rs.getString("NOME"));
//                cp.setEndereco(rs.getString("ENDERECO"));
//                cp.setBairro(rs.getString("BAIRRO"));
//                cp.setCidade(rs.getString("CIDADE"));
//                cp.setCep(rs.getString("CEP"));
//                cp.setUf(rs.getString("UF"));
//                cp.setCpf(rs.getString("CPF"));
//                cp.setRg(rs.getString("RG"));
//                cp.setObs(rs.getString("OBSERVACOES"));
//                cp.setTipoCliente(rs.getInt("IDTIPOCLIENTE"));
//                cp.setTelefoneResidencial(rs.getString("TELEFONERESIDENCIAL"));
//                cp.setTelefoneFax(rs.getString("TELEFONEFAX"));
//                cp.setTelefoneCelular(rs.getString("TELEFONECELULAR"));
//                cp.setTelefoneComercial(rs.getString("TELEFONECOMERCIAL"));
//                cp.setContato(rs.getString("NOMERESPONSAVEL"));
//                cp.setTelefoneContato(rs.getString("TELEFONERESPONSAVEL"));
//                cp.setEmail(rs.getString("EMAIL"));
//                retorno.add(cp);
//            }
//            conexao.fechaConexao(con);
//
//        return retorno;
//    }
//
//
//    //Pega lista de Clientes pelo Nome
//    public List getListaClientePessoaPorNome(String nome) throws SQLException{
//
//        Statement stmt;
//        ResultSet rs;
//        ClientePessoaFisica cp = null;
//        ArrayList retorno = new ArrayList();
//        String sql = "SELECT * FROM CLIENTES WHERE NOME LIKE '%"+nome+"%' AND IDTIPOCLIENTE = 3";
//
//            con = conexao.abreConexao();
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()){
//                cp = new ClientePessoaFisica();
//                cp.setId(rs.getLong("ID"));
//                cp.setNome(rs.getString("NOME"));
//                cp.setEndereco(rs.getString("ENDERECO"));
//                cp.setBairro(rs.getString("BAIRRO"));
//                cp.setCidade(rs.getString("CIDADE"));
//                cp.setCep(rs.getString("CEP"));
//                cp.setUf(rs.getString("UF"));
//                cp.setCpf(rs.getString("CPF"));
//                cp.setRg(rs.getString("RG"));
//                cp.setObs(rs.getString("OBSERVACOES"));
//                cp.setTipoCliente(rs.getInt("IDTIPOCLIENTE"));
//                cp.setTelefoneResidencial(rs.getString("TELEFONERESIDENCIAL"));
//                cp.setTelefoneFax(rs.getString("TELEFONEFAX"));
//                cp.setTelefoneCelular(rs.getString("TELEFONECELULAR"));
//                cp.setTelefoneComercial(rs.getString("TELEFONECOMERCIAL"));
//                cp.setContato(rs.getString("NOMERESPONSAVEL"));
//                cp.setTelefoneContato(rs.getString("TELEFONERESPONSAVEL"));
//                cp.setEmail(rs.getString("EMAIL"));
//                retorno.add(cp);
//            }
//            conexao.fechaConexao(con);
//
//        return retorno;
//    }
//
//    //Pega lista de ClientePessoa pelo Endereco
//    public List getClientePessoaPorEndereco(String end) throws SQLException{
//
//        Statement stmt;
//        ResultSet rs;
//        ArrayList retorno = new ArrayList();
//        ClientePessoaFisica cp = null;
//        String sql = "SELECT * FROM CLIENTES WHERE ENDERECO LIKE '%"+end+"%' AND IDTIPOCLIENTE = 3";
//
//
//
//            con = conexao.abreConexao();
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()){
//                cp = new ClientePessoaFisica();
//                cp.setId(rs.getLong("ID"));
//                cp.setNome(rs.getString("NOME"));
//                cp.setEndereco(rs.getString("ENDERECO"));
//                cp.setBairro(rs.getString("BAIRRO"));
//                cp.setCidade(rs.getString("CIDADE"));
//                cp.setCep(rs.getString("CEP"));
//                cp.setUf(rs.getString("UF"));
//                cp.setCpf(rs.getString("CPF"));
//                cp.setRg(rs.getString("RG"));
//                cp.setObs(rs.getString("OBSERVACOES"));
//                cp.setTipoCliente(rs.getInt("IDTIPOCLIENTE"));
//                cp.setTelefoneResidencial(rs.getString("TELEFONERESIDENCIAL"));
//                cp.setTelefoneFax(rs.getString("TELEFONEFAX"));
//                cp.setTelefoneCelular(rs.getString("TELEFONECELULAR"));
//                cp.setTelefoneComercial(rs.getString("TELEFONECOMERCIAL"));
//                cp.setContato(rs.getString("NOMERESPONSAVEL"));
//                cp.setTelefoneContato(rs.getString("TELEFONERESPONSAVEL"));
//                cp.setEmail(rs.getString("EMAIL"));
//                retorno.add(cp);
//            }
//            conexao.fechaConexao(con);
//
//
//        return retorno;
//    }
//
//    //Pega o Lista de ClientePessoa pelo Endereco
//    public List getClientePessoaPorBairro(String bairro) throws SQLException{
//
//        Statement stmt;
//        ResultSet rs;
//        ArrayList retorno = new ArrayList();
//        ClientePessoaFisica cp = null;
//        String sql = "SELECT * FROM CLIENTES WHERE BAIRRO LIKE '%"+bairro+"%' AND IDTIPOCLIENTE = 3";
//
//            con = conexao.abreConexao();
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()){
//                cp = new ClientePessoaFisica();
//                cp.setId(rs.getLong("ID"));
//                cp.setNome(rs.getString("NOME"));
//                cp.setEndereco(rs.getString("ENDERECO"));
//                cp.setBairro(rs.getString("BAIRRO"));
//                cp.setCidade(rs.getString("CIDADE"));
//                cp.setCep(rs.getString("CEP"));
//                cp.setUf(rs.getString("UF"));
//                cp.setCpf(rs.getString("CPF"));
//                cp.setRg(rs.getString("RG"));
//                cp.setObs(rs.getString("OBSERVACOES"));
//                cp.setTipoCliente(rs.getInt("IDTIPOCLIENTE"));
//                cp.setTelefoneResidencial(rs.getString("TELEFONERESIDENCIAL"));
//                cp.setTelefoneFax(rs.getString("TELEFONEFAX"));
//                cp.setTelefoneCelular(rs.getString("TELEFONECELULAR"));
//                cp.setTelefoneComercial(rs.getString("TELEFONECOMERCIAL"));
//                cp.setContato(rs.getString("NOMERESPONSAVEL"));
//                cp.setTelefoneContato(rs.getString("TELEFONERESPONSAVEL"));
//                cp.setEmail(rs.getString("EMAIL"));
//                retorno.add(cp);
//            }
//            conexao.fechaConexao(con);
//
//        return retorno;
//    }
//
//    //Valida se ja nao existe um  com o mesmo NOME
//    public boolean isNomeExistente(ClientePessoaFisica ce) throws SQLException{
//
//        PreparedStatement pstmt;
//        ResultSet rs = null;
//        boolean retorno = false;
//        String sql = "SELECT ID FROM CLIENTES WHERE NOME = ? AND IDTIPOCLIENTE = 3";
//
//        con = conexao.abreConexao();
//        pstmt = con.prepareStatement(sql);
//        pstmt.setString(1, ce.getNome());
//        rs = pstmt.executeQuery();
//
//        if(rs.next()){
//            retorno = true;
//        }
//
//        return retorno;
//    }
//
//    //Valida se ja nao existe um  com o mesmo CNPJ
//    public boolean isCpfExistente(ClientePessoaFisica cp) throws SQLException{
//
//        PreparedStatement pstmt;
//        ResultSet rs = null;
//        boolean retorno = false;
//        String sql = "SELECT ID FROM CLIENTES WHERE CPF = ? AND IDTIPOCLIENTE = 3";
//
//        con = conexao.abreConexao();
//        pstmt = con.prepareStatement(sql);
//        pstmt.setString(1, cp.getCpf());
//        rs = pstmt.executeQuery();
//
//        if(rs.next()){
//            retorno = true;
//        }
//
//        return retorno;
//    }
//
//
//     //Pega o ID do cliente pelo nome para exibir na tela
//    public int getIdClientePessoa(ClientePessoaFisica cp) throws SQLException{
//
//        PreparedStatement pstmt;
//        ResultSet rs;
//        int retorno = 0;
//        String sql = "SELECT ID FROM CLIENTES WHERE NOME =? AND IDTIPOCLIENTE = 3";
//
//            con = conexao.abreConexao();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, cp.getNome());
//            rs = pstmt.executeQuery();
//
//            while(rs.next()){
//                retorno = rs.getInt("ID");
//            }
//            conexao.fechaConexao(con);
//
//        return retorno;
//    }
//
//
//
//
//    //Inclui o ClientePessoa
//    public void incluir(ClientePessoaFisica cp) throws SQLException, ParseException, Exception{
//
//        PreparedStatement pstmt;
//
//        String sql = "INSERT INTO CLIENTES VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";       
//
//            con = conexao.abreConexao();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, cp.getNome());
//            pstmt.setString(2, cp.getEndereco());
//            pstmt.setString(3, cp.getBairro());
//            pstmt.setString(4, cp.getCidade());
//            pstmt.setString(5, cp.getCep());
//            pstmt.setString(6, cp.getUf());
//            pstmt.setString(7, cp.getObs());
//            pstmt.setDate(8, new Date(System.currentTimeMillis()));
//            pstmt.setString(9, cp.getCpf());
//            pstmt.setString(10, cp.getRg());
//            pstmt.setString(11, null);
//            pstmt.setString(12, null);
//            pstmt.setInt(13, 0);
//            pstmt.setInt(14, 0);
//            pstmt.setInt(15, 0);
//            pstmt.setInt(16, 0);
//            pstmt.setString(17, null);
//            pstmt.setString(18, null);
//            pstmt.setString(19, null);
//            pstmt.setString(20, null);
//            pstmt.setString(21,cp.getTelefoneResidencial() );
//            pstmt.setString(22,cp.getTelefoneComercial() );
//            pstmt.setString(23,cp.getTelefoneFax() );
//            pstmt.setString(24, cp.getTelefoneCelular());
//            pstmt.setString(25, cp.getContato());
//            pstmt.setString(26, cp.getTelefoneContato());
//            pstmt.setInt(27, cp.getTipoCliente());
//            pstmt.setString(28, cp.getEmail());
//            pstmt.executeUpdate();
//            conexao.fechaConexao(con);
//
//
//    }
//
//
//
//    public void alterar(ClientePessoaFisica ce) throws SQLException{
//
//        PreparedStatement pstmt;
//        String sql = "UPDATE CLIENTES SET "
//                + "NOME = ?,"
//                + "ENDERECO = ?,"
//                + "BAIRRO = ?,"
//                + "CIDADE = ?,"
//                + "CEP = ?,"
//                + "UF = ?,"
//                + "OBSERVACOES = ?,"
//                + "CPF = ?,"
//                + "RG = ?," 
//                + "IDTIPOCLIENTE = ?,"
//                + "TELEFONECOMERCIAL = ?,"
//                + "TELEFONEFAX = ?,"
//                + "NOMERESPONSAVEL = ?,"
//                + "TELEFONERESPONSAVEL = ?,"
//                + "EMAIL = ?,"
//                + "TELEFONERESIDENCIAL = ?,"
//                + "TELEFONECELULAR = ?"
//                + " WHERE ID = ?";
//
//            con = conexao.abreConexao();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, ce.getNome());
//            pstmt.setString(2, ce.getEndereco());
//            pstmt.setString(3, ce.getBairro());
//            pstmt.setString(4, ce.getCidade());
//            pstmt.setString(5, ce.getCep());
//            pstmt.setString(6, ce.getUf());
//            pstmt.setString(7, ce.getObs());
//            pstmt.setString(8, ce.getCpf());
//            pstmt.setString(9, ce.getRg());
//            pstmt.setInt(10, ce.getTipoCliente());
//            pstmt.setString(11, ce.getTelefoneComercial());
//            pstmt.setString(12, ce.getTelefoneFax());
//            pstmt.setString(13, ce.getContato());
//            pstmt.setString(14, ce.getTelefoneContato());
//            pstmt.setString(15, ce.getEmail());
//            pstmt.setString(16, ce.getTelefoneResidencial());
//            pstmt.setString(17, ce.getTelefoneCelular());
//            pstmt.setLong(18, ce.getId());
//            pstmt.executeUpdate();
//
//            conexao.fechaConexao(con);
//    }
//
//    public void deletar(long codigoCliente) throws SQLException{
//
//        PreparedStatement pstmt;
//        String sql = "DELETE FROM CLIENTES WHERE ID = ?";
//
//            con = conexao.abreConexao();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setLong(1, codigoCliente);
//            pstmt.executeUpdate();
//            conexao.fechaConexao(con);
//    }



}



