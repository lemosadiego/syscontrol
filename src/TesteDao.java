
import br.com.syscontrol.dao.HibernateFactory;
import br.com.syscontrol.dao.IPedidoDAO;
import br.com.syscontrol.dao.PedidoDao;
import br.com.syscontrol.model.FormaPagamento;
import br.com.syscontrol.model.Parcela;
import br.com.syscontrol.model.Pedido;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class TesteDao {
    
    public static void main(String [] args){
        try {
            Session session = HibernateFactory.getSession();

            FormaPagamento formaPagamento = new FormaPagamento();
            formaPagamento.setValorEntrada(500.00);
            formaPagamento.setValorTotal(500.00);
            
            Parcela parcela1 = new Parcela();
            //parcela1.setFormaPagamento(formaPagamento);
            parcela1.setNumeroParcela(1);
            parcela1.setValor(500.00);
            
            
            formaPagamento.getParcelas().add(parcela1);
            
            Transaction tx = session.beginTransaction();
            session.save(formaPagamento);
            tx.commit();
            
    //        Parcela parcela1 = new Parcela();
    //        //parcela1.setFormaPagamento(formaPagamento);
    //        parcela1.setNumeroParcela(1);
    //        parcela1.setValor(500.00);
    //        parcela1.setFormaPagamento(formaPagamento);
    //        
    //        
    //        Transaction tx2 = session.beginTransaction();
    //            session.save(parcela1);
    //        tx2.commit();
            
            
            
    //        ClienteCondominio cliente = new ClienteCondominio();
    //        cliente.setNome("DIEGO LEMOS4");
    //        cliente.setBairro("FONSECA");
    //        cliente.setCidade("NITEROI");
    //        cliente.setEndereco("ALAMEDA SAO BOAVENTURA 1029 BLOCO 01 APTO 202");
    //        
    //        Transaction tx = session.beginTransaction();
    //        session.save(cliente);
    //        tx.commit();
            
            
            IPedidoDAO<Pedido> dao = new PedidoDao();
            
            List<Pedido> lista = dao.buscarPedidosPorNomeCliente("diego");
            
            
            for(Pedido p : lista){
                System.out.println(p.getIdPedido());
            }
            
            
    //        IColaboradorDAO dao = new ColaboradorDao();
    //       try {
    //           Colaborador c =  (Colaborador) dao.buscarPorNome("DIEGO LEMOS USUARIO");
    //           
    //           Usuario usuario = new Usuario();
    //           usuario.setColaborador(c);
    //           usuario.setUsuario("diego");
    //           usuario.setSenha("itaqua");
    //           
    //            IUsuarioDAO usuarioDAO = new UsuarioDao();
    //            usuarioDAO.salvar(usuario);
               
    //           
    //           
    //           List<Colaborador> lista = dao.buscarTodos();
    //           
    //           for(Colaborador clienteObj : lista){
    //               System.out.println(clienteObj.toString());
    //           }
               
               //System.out.println("Nome do usuario é " + c.getNome());
    //        } catch (SQLException ex) {
    //            Logger.getLogger(TesteDao.class.getName()).log(Level.SEVERE, null, ex);
    //        }
        } catch (SQLException ex) {
            Logger.getLogger(TesteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
