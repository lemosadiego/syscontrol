package br.com.syscontrol.relatorios;


import br.com.syscontrol.model.ClientePessoaFisica;
import br.com.syscontrol.model.ClientePessoaJuridica;
import br.com.syscontrol.model.Pedido;
import br.com.syscontrol.model.TipoCliente;
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
//
//    public static void main(String [] args){
//        
//    } 
    
    public PedidoReport(){
        
    }
    
    public void gerarRelatorio(Pedido pedido){
        try {
            
            
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
            parametros.put("UF", pedido.getCliente().getUf());
            parametros.put("CEP", pedido.getCliente().getCep());
            parametros.put("TOTALPEDIDO", pedido.getValorPedido().toString());
            
            
            if(pedido.getCliente().getTipoCliente().equals(TipoCliente.FISICA)){
                ClientePessoaFisica clientePessoaFisica = (ClientePessoaFisica) pedido.getCliente();
                parametros.put("DOCUMENTO", clientePessoaFisica.getCpf());
                
                if(clientePessoaFisica.getTelefoneCelular()== null){
                     parametros.put("TELEFONE", clientePessoaFisica.getTelefoneResidencial());
                }else{
                    parametros.put("TELEFONE", clientePessoaFisica.getTelefoneCelular());
                }               
                
            }else{
                ClientePessoaJuridica clientePessoaJuridica = (ClientePessoaJuridica) pedido.getCliente();
                parametros.put("DOCUMENTO", clientePessoaJuridica.getCnpj());
                
                if(clientePessoaJuridica.getTelefoneComercial()== null){
                     parametros.put("TELEFONE", clientePessoaJuridica.getTelefoneResponsavel());
                }else{
                    parametros.put("TELEFONE", clientePessoaJuridica.getTelefoneComercial());
                }               
            }           
            
            parametros.put("EMAIL", pedido.getCliente().getEmail());
            
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
    
    

