/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmBuscaCliente.java
 *
 * Created on 24/05/2011, 23:04:35
 */

package br.com.syscontrol.formularios;

import br.com.syscontrol.controller.BuscaClienteCondominioAction;
import br.com.syscontrol.controller.ClienteCondominioAction;
import br.com.syscontrol.controller.ClientePessoaAction;
import br.com.syscontrol.dao.ClientePessoaFisicaDao;
import br.com.syscontrol.dao.ClientePessoaJuridicaDAO;
import br.com.syscontrol.dao.IClientePessoaJuridicaDAO;
import br.com.syscontrol.exception.ClienteException;
import br.com.syscontrol.model.BuscaCondominioTableModel;
import br.com.syscontrol.model.BuscaEmpresaTableModel;
import br.com.syscontrol.model.BuscaPessoaPedidoTableModel;
import br.com.syscontrol.model.BuscaPessoaFisicaTableModel;
import br.com.syscontrol.model.ClientePessoa;
import br.com.syscontrol.model.ClienteCondominio;
import br.com.syscontrol.model.ClientePessoaJuridica;
import br.com.syscontrol.model.ClientePessoaFisica;
import br.com.syscontrol.model.Contrato;
import br.com.syscontrol.model.Pedido;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego
 */
public class FrmBuscaCliente extends javax.swing.JFrame {

    private List<ClientePessoa> lista;
    private BuscaClienteCondominioAction BuscaClienteCondominioAction = new BuscaClienteCondominioAction();
//    private BuscaEmpresaAction buscaEmpresaAction = new BuscaEmpresaAction();
//    private BuscaPessoaAction buscaPessoaAction = new BuscaPessoaAction();

    private Pedido pedido;
    private ClienteCondominioAction clienteCondominioAction = new ClienteCondominioAction();
    private IClientePessoaJuridicaDAO<ClientePessoaJuridica> empresaDao = new ClientePessoaJuridicaDAO();
    private ClientePessoaFisicaDao pessoaDao = new ClientePessoaFisicaDao();    
    
    
    private FrmPrincipal frmPrincipal = null;    
    private String tipoCliente = "";
    
    

    /** Creates new form FrmBuscaCliente */
    public FrmBuscaCliente() {
        initComponents();
    }

    /** Creates new form FrmBuscaCliente */
    public FrmBuscaCliente(List listaClientes,FrmPrincipal frmPrincipal,String tipoCliente) {
        initComponents();
        this.lista = listaClientes;
        this.frmPrincipal = frmPrincipal;
        this.tipoCliente = tipoCliente;        
    }

        /** Creates new form FrmBuscaCliente */
    public FrmBuscaCliente(List listaCliente,FrmPrincipal frmPrincipal,String tipoCliente,Contrato c) {
        initComponents();
        this.lista = listaCliente;
        this.frmPrincipal = frmPrincipal;
        this.tipoCliente = tipoCliente;
        //contrato = c;
    }


    /** Creates new form FrmBuscaCliente */
    public FrmBuscaCliente(List<ClientePessoa> listaCliente,FrmPrincipal frmPrincipal,String tipoCliente,Pedido pedido) {
        initComponents();
        this.lista = listaCliente;
        this.frmPrincipal = frmPrincipal;
        this.tipoCliente = tipoCliente;
        this.pedido = pedido;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaBuscaClientes = new javax.swing.JTable();
        btnSelecionar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregaBuscaCliente(evt);
            }
        });

        tabelaBuscaClientes.setFont(new java.awt.Font("Times New Roman", 0, 14));
        tabelaBuscaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaBuscaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecionaLinhaCliente(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaBuscaClientes);

        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSelecionar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void carregaBuscaCliente(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregaBuscaCliente
        // TODO add your handling code here:

        //Monta o grid contendo os dados do usuario
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);   
        
        if(tipoCliente.equals("Condominio")){
            
            BuscaCondominioTableModel buscaCondominioTableModel = new BuscaCondominioTableModel(lista);
            
            //Monta tabela de usuarios no grid
            //this.populaTabelaCliente( buscaCondominioTableModel, lista);            

            tabelaBuscaClientes.setModel(buscaCondominioTableModel);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setMaxWidth(350);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setMaxWidth(170);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setMaxWidth(400);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setMaxWidth(150);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setMaxWidth(150);

        }else if(tipoCliente.equals("Empresa")){
            
                //Monta tabela de usuarios no grid
            BuscaEmpresaTableModel buscaEmpresaTableModel = new BuscaEmpresaTableModel(lista);
                //this.populaTabelaCliente(new BuscaEmpresaTableModel(), lista);
            

            tabelaBuscaClientes.setModel(buscaEmpresaTableModel);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setMaxWidth(350);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setMaxWidth(170);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setMaxWidth(400);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setMaxWidth(150);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setMaxWidth(150);

        }else if(tipoCliente.equals("Pessoa")){
           //Monta tabela de usuarios no grid
            BuscaPessoaFisicaTableModel buscaPessoaTableModel = new BuscaPessoaFisicaTableModel(lista);
           //     this.populaTabelaCliente(new BuscaPessoaFisicaTableModel(), lista);
           

            tabelaBuscaClientes.setModel(buscaPessoaTableModel);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(6).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setMaxWidth(350);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setMaxWidth(170);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setMaxWidth(400);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setMaxWidth(150);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setMaxWidth(150);
            tabelaBuscaClientes.getColumnModel().getColumn(6).setMaxWidth(150);
        }else if(tipoCliente.equals("Generico")){
           
            BuscaPessoaFisicaTableModel buscaPessoaTableModel = new BuscaPessoaFisicaTableModel(lista);

            tabelaBuscaClientes.setModel(buscaPessoaTableModel);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(6).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setMaxWidth(350);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setMaxWidth(170);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setMaxWidth(400);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setMaxWidth(150);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setMaxWidth(150);
            tabelaBuscaClientes.getColumnModel().getColumn(6).setMaxWidth(150);

        }else if(tipoCliente.equals("ClientePedido")){
            
                //Monta tabela de usuarios no grid
            BuscaPessoaPedidoTableModel buscaPessoaTableModel = new BuscaPessoaPedidoTableModel(lista);
            //    this.populaTabelaCliente(new BuscaPessoaFisicaTableModel(), lista);
            tabelaBuscaClientes.setModel(buscaPessoaTableModel);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setCellRenderer(renderer);            
            tabelaBuscaClientes.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setMaxWidth(450);
            
        }else if(tipoCliente.equals("Contrato")){
            
                //Monta tabela de usuarios no grid
            //this.populaTabelaCliente(new BuscaPessoaFisicaTableModel(), lista);
            
            BuscaPessoaFisicaTableModel buscaPessoaTableModel = new BuscaPessoaFisicaTableModel(lista);
            

            tabelaBuscaClientes.setModel(buscaPessoaTableModel);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(6).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setMaxWidth(350);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setMaxWidth(170);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setMaxWidth(400);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setMaxWidth(150);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setMaxWidth(150);
            tabelaBuscaClientes.getColumnModel().getColumn(6).setMaxWidth(150);

        }else if(tipoCliente.equals("Telefone")){
           
            //this.populaTabelaCliente(new BuscaPessoaFisicaTableModel(),lista);
            
            BuscaPessoaFisicaTableModel buscaPessoaTableModel = new BuscaPessoaFisicaTableModel(lista);
           
            tabelaBuscaClientes.setModel(buscaPessoaTableModel);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(6).setCellRenderer(renderer);
            tabelaBuscaClientes.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaBuscaClientes.getColumnModel().getColumn(1).setMaxWidth(350);
            tabelaBuscaClientes.getColumnModel().getColumn(2).setMaxWidth(170);
            tabelaBuscaClientes.getColumnModel().getColumn(3).setMaxWidth(400);
            tabelaBuscaClientes.getColumnModel().getColumn(4).setMaxWidth(150);
            tabelaBuscaClientes.getColumnModel().getColumn(5).setMaxWidth(150);
            tabelaBuscaClientes.getColumnModel().getColumn(6).setMaxWidth(150);
        
        }
        
        
        
    }//GEN-LAST:event_carregaBuscaCliente

    private void populaTabelaCliente(AbstractTableModel tableModel, List listaClientes){
        
        if(tableModel instanceof BuscaEmpresaTableModel){            
            BuscaEmpresaTableModel buscaEmpresaTableModel = ((BuscaEmpresaTableModel) tableModel);
            buscaEmpresaTableModel.limpar();
            buscaEmpresaTableModel.addListaDeEmpresas(listaClientes);
        }else if(tableModel instanceof BuscaCondominioTableModel){
            BuscaCondominioTableModel buscaCondominioTableModel = ((BuscaCondominioTableModel) tableModel);
            buscaCondominioTableModel.limpar();
            buscaCondominioTableModel.addListaDeCondominios(listaClientes);               
        }
        
    }
    
    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed

        ClientePessoa cliente;

        if(this.tipoCliente.equals("Condominio")){
             try {
                // TODO add your handling code here:
                Long codigoCliente = (Long)tabelaBuscaClientes.getValueAt(tabelaBuscaClientes.getSelectedRow(), 0);
                ClienteCondominio cc = clienteCondominioAction.buscarPorId(codigoCliente);
                 if(pedido != null){
                           pedido.setCliente(cc);
                 }
                frmPrincipal.popularClienteCondominio(cc);
                this.dispose();
            } catch (ClienteException ex) {
                Logger.getLogger(FrmBuscaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(this.tipoCliente.equals("Empresa")){
             try {
                // TODO add your handling code here:
                Long codigoCliente = (Long)tabelaBuscaClientes.getValueAt(tabelaBuscaClientes.getSelectedRow(), 0);
                ClientePessoaJuridica ce = empresaDao.buscarPorId(codigoCliente);
                 if(pedido != null){
                           pedido.setCliente(ce);
                 }
                frmPrincipal.popularClientePessoaJuridica(ce);
                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(FrmBuscaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(this.tipoCliente.equals("Pessoa")){
             try {
                // TODO add your handling code here:
                Long codigoCliente = (Long)tabelaBuscaClientes.getValueAt(tabelaBuscaClientes.getSelectedRow(), 0);
                ClientePessoaFisica cp = pessoaDao.buscarPorId(codigoCliente);
                 if(pedido != null){
                           pedido.setCliente(cp);
                        }
                frmPrincipal.popularClientePessoa(cp);
                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(FrmBuscaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(this.tipoCliente.equals("ClientePedido")){
                     try {
                        // TODO add your handling code here:
                        Long codigoCliente = (Long)tabelaBuscaClientes.getValueAt(tabelaBuscaClientes.getSelectedRow(), 0);
                        cliente = ClientePessoaAction.getInstance().buscarPorId(codigoCliente);
                         if(pedido != null){
                           pedido.setCliente(cliente);
                        }
                        frmPrincipal.popularClientePedido(cliente);
                        this.dispose();                    
                    } catch (ClienteException ex) {
                         javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha ao consulta lista de clientes. O seguinte erro ocorreu: " + ex.getMessage(),"Atenção - Erro",0);
                    }
        }else if(this.tipoCliente.equals("Contrato")){
             try {
                // TODO add your handling code here:
                Long codigoCliente = (Long)tabelaBuscaClientes.getValueAt(tabelaBuscaClientes.getSelectedRow(), 0);
                cliente = ClientePessoaAction.getInstance().buscarPorId(codigoCliente);
                //this.contrato.setCliente(c);
                frmPrincipal.getContratoAction().getContrato().setCliente(cliente);
                frmPrincipal.popularClienteContrato((ClientePessoaFisica)cliente);
                this.dispose();
             } catch (Exception ex) {
                Logger.getLogger(FrmBuscaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(this.tipoCliente.equals("Telefone")){
             try {
                // TODO add your handling code here:
                Long codigoCliente = (Long)tabelaBuscaClientes.getValueAt(tabelaBuscaClientes.getSelectedRow(), 0);
                cliente = ClientePessoaAction.getInstance().buscarPorId(codigoCliente);
                 if(pedido != null){
                           pedido.setCliente(cliente);
                 }
                
                frmPrincipal.popularClientePedido((ClientePessoaFisica) cliente);
                this.dispose();


            } catch (Exception ex) {
                Logger.getLogger(FrmBuscaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSelecionarActionPerformed

    private void selecionaLinhaCliente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionaLinhaCliente

        if(evt.getClickCount()==2){

                 if(this.tipoCliente.equals("Condominio")){
                     try {
                        // TODO add your handling code here:
                        Long codigoCliente = (Long)tabelaBuscaClientes.getValueAt(tabelaBuscaClientes.getSelectedRow(), 0);
                        ClienteCondominio cc = clienteCondominioAction.buscarPorId(codigoCliente);
                        if(pedido != null){
                           pedido.setCliente(cc);
                        }
                        
                        frmPrincipal.popularClienteCondominio(cc);
                        this.dispose();


                    } catch (ClienteException ex) {

                        Logger.getLogger(FrmBuscaCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(this.tipoCliente.equals("Empresa")){
                     try {
                        // TODO add your handling code here:
                        Long codigoCliente = (Long)tabelaBuscaClientes.getValueAt(tabelaBuscaClientes.getSelectedRow(), 0);
                        ClientePessoaJuridica ce = empresaDao.buscarPorId(codigoCliente);
                         if(pedido != null){
                           pedido.setCliente(ce);
                        }
                        frmPrincipal.popularClientePessoaJuridica(ce);
                        this.dispose();


                    } catch (SQLException ex) {
                        Logger.getLogger(FrmBuscaCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(this.tipoCliente.equals("Pessoa")){
                     try {
                         // TODO add your handling code here:
                            Long codigoCliente = (Long)tabelaBuscaClientes.getValueAt(tabelaBuscaClientes.getSelectedRow(), 0);
                            ClientePessoaFisica cp = pessoaDao.buscarPorId(codigoCliente);
                          if(pedido != null){
                            pedido.setCliente(cp);
                         }
                         frmPrincipal.popularClientePessoa(cp);
                         this.dispose();
                     } catch (SQLException ex) {
                         Logger.getLogger(FrmBuscaCliente.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }else if(this.tipoCliente.equals("ClientePedido")){
                     try {
                        // TODO add your handling code here:
                        Long codigoCliente = (Long)tabelaBuscaClientes.getValueAt(tabelaBuscaClientes.getSelectedRow(), 0);
                        ClientePessoa cliente = ClientePessoaAction.getInstance().buscarPorId(codigoCliente);
                         if(pedido != null){
                           pedido.setCliente(cliente);
                        }
                        frmPrincipal.popularClientePedido(cliente);
                        this.dispose();                    
                    } catch (ClienteException ex) {
                         javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha ao consulta lista de clientes. O seguinte erro ocorreu: " + ex.getMessage(),"Atenção - Erro",0);
                    }
                }
        }
    }//GEN-LAST:event_selecionaLinhaCliente

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(frmPrincipal.getTxtNomeClienteContrato().equalsIgnoreCase("")){
            frmPrincipal.setBtnCadastroContrato(false);
            frmPrincipal.setBtnAlteraContrato(false);
            frmPrincipal.setBtnExcluirContrato(false);
        }else{
            frmPrincipal.setBtnCadastroContrato(true);
            frmPrincipal.setBtnAlteraContrato(true);
            frmPrincipal.setBtnExcluirContrato(true);
        }
        this.dispose();
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if(frmPrincipal.getTxtNomeClienteContrato().equalsIgnoreCase("")){
            frmPrincipal.setBtnCadastroContrato(false);
            frmPrincipal.setBtnAlteraContrato(false);
            frmPrincipal.setBtnExcluirContrato(false);
        }else{
            frmPrincipal.setBtnCadastroContrato(true);
            frmPrincipal.setBtnAlteraContrato(true);
            frmPrincipal.setBtnExcluirContrato(true);
        }
        
        
        
        this.dispose();
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBuscaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaBuscaClientes;
    // End of variables declaration//GEN-END:variables

   

    /**
     * @return the buscaCondominioAction
     */
    public BuscaClienteCondominioAction getBuscaClienteCondominioAction() {
        return BuscaClienteCondominioAction;
    }

    /**
     * @param buscaCondominioAction the buscaCondominioAction to set
     */
    public void setBuscaClienteCondominioAction(BuscaClienteCondominioAction BuscaClienteCondominioAction) {
        this.BuscaClienteCondominioAction = BuscaClienteCondominioAction;
    }

//    /**
//     * @return the buscaEmpresaAction
//     */
//    public BuscaEmpresaAction getBuscaEmpresaAction() {
//        return buscaEmpresaAction;
//    }
//
//    /**
//     * @param buscaEmpresaAction the buscaEmpresaAction to set
//     */
//    public void setBuscaEmpresaAction(BuscaEmpresaAction buscaEmpresaAction) {
//        this.buscaEmpresaAction = buscaEmpresaAction;
//    }
//
//    /**
//     * @return the buscaPessoaAction
//     */
//    public BuscaPessoaAction getBuscaPessoaAction() {
//        return buscaPessoaAction;
//    }

//    /**
//     * @param buscaPessoaAction the buscaPessoaAction to set
//     */
//    public void setBuscaPessoaAction(BuscaPessoaAction buscaPessoaAction) {
//        this.buscaPessoaAction = buscaPessoaAction;
//    }

    /**
     * @return the pessoaDao
     */
    public ClientePessoaFisicaDao getPessoaDao() {
        return pessoaDao;
    }

    /**
     * @param pessoaDao the pessoaDao to set
     */
    public void setPessoaDao(ClientePessoaFisicaDao pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

}
