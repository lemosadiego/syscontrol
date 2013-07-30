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


import br.com.syscontrol.controller.PedidoAction;
import br.com.syscontrol.model.Pedido;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego
 */
public class FrmBuscaPedido extends javax.swing.JFrame {

    private List lista = null;
    private PedidoAction pedidoAction = new PedidoAction();
    private Pedido pedido = null;    
    private FrmPrincipal frmPrincipal;

    /** Creates new form FrmBuscaCliente */
    public FrmBuscaPedido() {
        initComponents();
    }

    /** Creates new form FrmBuscaCliente */
    public FrmBuscaPedido(List lista, FrmPrincipal frmPrincipal,Pedido pedido) {
        initComponents();
        this.lista = lista;
        this.frmPrincipal = frmPrincipal;
        this.pedido = pedido;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaBuscaPedidos = new javax.swing.JTable();
        btnSelecionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregaTelaBuscaPedido(evt);
            }
        });

        tabelaBuscaPedidos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tabelaBuscaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaBuscaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecionaLinhaCliente(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaBuscaPedidos);

        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
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
                    .addComponent(btnSelecionar))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void carregaTelaBuscaPedido(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregaTelaBuscaPedido
        // TODO add your handling code here:

        //Monta o grid contendo os dados do usuario
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);


        try {
            //Monta tabela de usuarios no grid
           this.getPedidoAction().getBuscaPedidoTableModel().addListaDePedidos(lista);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao processar busca - Entre em contato com o Administrador do Sistema!. O erro ocorrido foi: " + ex.getMessage(), "Atenção - Aviso", 0);
        }

        tabelaBuscaPedidos.setModel(this.getPedidoAction().getBuscaPedidoTableModel());
        tabelaBuscaPedidos.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tabelaBuscaPedidos.getColumnModel().getColumn(1).setCellRenderer(renderer);
        tabelaBuscaPedidos.getColumnModel().getColumn(2).setCellRenderer(renderer);
        tabelaBuscaPedidos.getColumnModel().getColumn(3).setCellRenderer(renderer);       
        tabelaBuscaPedidos.getColumnModel().getColumn(0).setMaxWidth(50);
        tabelaBuscaPedidos.getColumnModel().getColumn(1).setMaxWidth(350);
        tabelaBuscaPedidos.getColumnModel().getColumn(2).setMaxWidth(170);
        tabelaBuscaPedidos.getColumnModel().getColumn(3).setMaxWidth(120);
        
    }//GEN-LAST:event_carregaTelaBuscaPedido

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
       
            try {
                //Pedido Selecionado
                int linhaSelecionada = tabelaBuscaPedidos.getSelectedRow();
                this.pedido = this.getPedidoAction().getBuscaPedidoTableModel().getLinhas().get(linhaSelecionada);
                frmPrincipal.getPedidoAction().setPedido(pedido);
                frmPrincipal.populaPedidoConsultado(pedido);
                this.dispose();
            } catch (Exception ex) {
                Logger.getLogger(FrmBuscaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
         
    }//GEN-LAST:event_btnSelecionarActionPerformed

    private void selecionaLinhaCliente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionaLinhaCliente

            try {
                if(evt.getClickCount()==2){
                    //Pedido Selecionado
                    int linhaSelecionada = tabelaBuscaPedidos.getSelectedRow();
                    this.pedido = this.getPedidoAction().getBuscaPedidoTableModel().getLinhas().get(linhaSelecionada);

                    frmPrincipal.populaPedidoConsultado(pedido);
                    this.dispose();
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmBuscaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_selecionaLinhaCliente

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new FrmBuscaPedido().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaBuscaPedidos;
    // End of variables declaration//GEN-END:variables

   
    /**
     * @return the pedidoAction
     */
    public PedidoAction getPedidoAction() {
        return pedidoAction;
    }

    /**
     * @param pedidoAction the pedidoAction to set
     */
    public void setPedidoAction(PedidoAction pedidoAction) {
        this.pedidoAction = pedidoAction;
    }
}
