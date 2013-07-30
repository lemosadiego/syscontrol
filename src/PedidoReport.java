
import br.com.syscontrol.dao.PedidoDao;
import br.com.syscontrol.model.Pedido;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class PedidoReport {

    public static void main(String [] args){
        try {
            PedidoDao dao = new PedidoDao();
            Pedido pedido = dao.buscarPorId(1L);
//            List<ItemPedido> servicos = dao.buscarPorId(1L).getItensPedido();
//            
//            JRDataSource dataSource = new JRBeanCollectionDataSource(servicos);
            
            JasperCompileManager.compileReport("reports/PedidoReport.jrxml"); 
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("IDPEDIDO", pedido.getIdPedido());
            parametros.put("DATAPEDIDO", pedido.getDataCadastro());
            parametros.put("NOMECLIENTE", pedido.getCliente().getNome());
            parametros.put("ENDERECO", pedido.getCliente().getEndereco());
            parametros.put("BAIRRO", pedido.getCliente().getBairro());
            parametros.put("CIDADE", pedido.getCliente().getCidade());
            parametros.put("TELEFONERESIDENCIAL", pedido.getCliente().getTelefoneContato());
            parametros.put("TELEFONECELULAR", pedido.getCliente().getTelefoneContato());           
            //parametros.put("IDPEDIDO", pedido.getIdPedido());
            Connection conexao = ConnectionFactory.getConnection(); 
            //JasperPrint print = JasperFillManager.fillReport("reports/PedidoReport.jasper", parametros, dataSource); conexao.close();
            JasperPrint print = JasperFillManager.fillReport("reports/PedidoReport.jasper", parametros, conexao); 
            
            
            JasperFillManager.fillReportToFile("reports/PedidoReport.jasper", parametros, conexao);
            JasperViewer.viewReport("reports/PedidoReport.jrprint", false, false);
            conexao.close();
            
            
//            JRExporter exporter = new JRPdfExporter(); 
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print); 
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("PedidoReport.pdf")); 
//            exporter.exportReport(); 
            conexao.close(); 
        } catch (JRException ex) {
            Logger.getLogger(PedidoReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
    } 
}
    
    

