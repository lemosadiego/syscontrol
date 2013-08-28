/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmPrincipal.java
 *
 * Created on 22/04/2011, 15:16:22
 */

package br.com.syscontrol.formularios;



import br.com.syscontrol.controller.BuscaClienteCondominioAction;
import br.com.syscontrol.controller.ClienteCondominioAction;
import br.com.syscontrol.controller.ClienteEmpresaAction;
import br.com.syscontrol.controller.ClienteListener;
import br.com.syscontrol.controller.ClientePessoaAction;
import br.com.syscontrol.controller.ClientePessoaFisicaAction;
import br.com.syscontrol.controller.ContratoAction;
import br.com.syscontrol.controller.GenericAction;
import br.com.syscontrol.controller.GrupoUsuarioAction;
import br.com.syscontrol.controller.OrdemServicoAction;
import br.com.syscontrol.controller.PedidoAction;
import br.com.syscontrol.controller.ServicoAction;
import br.com.syscontrol.controller.UsuarioAction;
import br.com.syscontrol.controller.UsuarioCadaGrupoAction;
import br.com.syscontrol.exception.ClienteException;
import br.com.syscontrol.exception.OrdemServicoException;
import br.com.syscontrol.exception.PedidoException;
import br.com.syscontrol.exception.ServicoException;
import br.com.syscontrol.exception.UsuarioException;
import br.com.syscontrol.helper.Util;
import br.com.syscontrol.model.ClientePessoa;
import br.com.syscontrol.model.ClienteCondominio;
import br.com.syscontrol.model.ClientePessoaJuridica;
import br.com.syscontrol.model.ClientePessoaFisica;
import br.com.syscontrol.model.Colaborador;
import br.com.syscontrol.model.Contrato;
import br.com.syscontrol.model.GrupoUsuario;
import br.com.syscontrol.model.ItemPedido;
import br.com.syscontrol.model.Observer;
import br.com.syscontrol.model.OrdemServicoPadrao;
import br.com.syscontrol.model.OrdemServicoTableModel;
import br.com.syscontrol.model.Pedido;
import br.com.syscontrol.model.Servico;
import br.com.syscontrol.model.ServicoContrato;
import br.com.syscontrol.model.ServicoContratoTableModel;
import br.com.syscontrol.model.ServicoDisponivelTableModel;
import br.com.syscontrol.model.ServicoPedidoTableModel;
import br.com.syscontrol.model.TipoCliente;
import br.com.syscontrol.model.Usuario;
import br.com.syscontrol.relatorios.PedidoReport;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;


/**
 *
 * @author Diego
 */
public class FrmPrincipal extends javax.swing.JFrame implements Observer{

    private UsuarioAction usuarioAction;
    private GrupoUsuarioAction grupoUsuarioAction;
    private static Usuario usuarioTela;
    private GrupoUsuario grupoUsuarioTela;   

    /*Declaração de Actions*/
    private UsuarioCadaGrupoAction usuarioCadaGrupoAction;
    private ClienteCondominioAction clienteCondominioAction;
    private ClienteEmpresaAction clienteEmpresaAction;
    private ClientePessoaAction clientePessoaAction = new ClientePessoaAction();
    private ClientePessoaFisicaAction clientePessoaFisicaAction;
    
    private ServicoAction servicoAction;
    private PedidoAction pedidoAction;
    private ContratoAction contratoAction;
    private OrdemServicoAction ordemServicoAction;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    //Interface responsavel por declarar o metodos fundamentais de uma action
    private GenericAction action;

    private ClienteCondominio clienteCondominio;
    private ClientePessoaJuridica clientePessoaJuridica;
    private ClientePessoaFisica clientePessoa;

    private ClienteListener clienteListener = new ClienteListener();
    
    ImageIcon iconeNovo = new ImageIcon("/iconNovo.png");
    ImageIcon iconeIncluir = new ImageIcon("/iconIncluir.png");
    ImageIcon iconeAlterar = new ImageIcon("/iconAlterar.png");
    ImageIcon iconeExcluir = new ImageIcon("/iconExcluir.png");
    ImageIcon iconePagamento = new ImageIcon("/cifrao.png");

    

    /** Creates new form FrmPrincipal */
    public FrmPrincipal() {
        
        initComponents();
        txtDataAgendamento = CalendarFactory.createDateField();        
        txtDataAgendamento.setFont(new java.awt.Font("Times New Roman", 0, 14));
        jPanel21.add(txtDataAgendamento).setBounds(60, 55, 120, 22);
        txtDataAgendamento.setEnabled(false);
        cmdPeriodoContrato.addChangeListener(listener2);

    }

    public FrmPrincipal(Usuario usuario) {
        initComponents();
        txtDataAgendamento = CalendarFactory.createDateField();
        txtDataAgendamento.setFont(new java.awt.Font("Times New Roman", 0, 14));
        txtDataAgendamento.repaint();
        txtDataExecucao = CalendarFactory.createDateField();
        txtDataExecucao.setFont(new java.awt.Font("Times New Roman", 0, 14));
        txtDataExecucao.repaint();
        txtDataExecucaoOsPadrao = CalendarFactory.createDateField();
        txtDataExecucaoOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jPanel31.add(txtDataExecucaoOsPadrao).setBounds(226, 43, 120, 27);
        txtDataExecucaoOsPadrao.setEnabled(false);
        jPanel21.add(txtDataExecucao).setBounds(6,45, 110, 22);
        jPanel34.add(txtDataAgendamento).setBounds(18, 45, 110, 22);
        cmdPeriodoContrato.addChangeListener(listener2);
        usuarioTela = usuario;
        //jTabbedPane1.getComponentAt(3).setVisible(false);

        ordemServicoAction = new OrdemServicoAction(this);

    }


    
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtNomeUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbColaborador = new javax.swing.JComboBox();
        chkBloqueado = new javax.swing.JCheckBox();
        btnUsuarioNovo = new javax.swing.JButton();
        btnIncluirUsuario = new javax.swing.JButton();
        btnAlterarUsuario = new javax.swing.JButton();
        btnExcluirUsuario = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaGrupoUsuarios = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtGrupoUsuario = new javax.swing.JTextField();
        btnNovoGrupo = new javax.swing.JButton();
        btnIncluirGrupo = new javax.swing.JButton();
        btnAlterarGrupo = new javax.swing.JButton();
        btnExcluirGrupo = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cmbUsuarioColaborador = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaUsuariosCadaGrupo = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tabelaServicos = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        txtNomeServico = new javax.swing.JTextField();
        txtTempoGarantia = new javax.swing.JTextField();
        txtValorServico = new javax.swing.JTextField();
        btnServicoNovo = new javax.swing.JButton();
        btnIncluirServico = new javax.swing.JButton();
        btnAlterarServico = new javax.swing.JButton();
        btnExcluirServico = new javax.swing.JButton();
        txtIdServico = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIe = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cmbUf = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        txtBlocos = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtAndares = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtApAndar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtQuartos = new javax.swing.JTextField();
        btnCondominioNovo = new javax.swing.JButton();
        btnIncluirCondominio = new javax.swing.JButton();
        btnAlterarClienteCondominio = new javax.swing.JButton();
        btnExcluirCondominio = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtNomeSindico = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTelSindico = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtNomeZelador = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtTelZelador = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtEmailCondominio = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        txtTelComercial = new javax.swing.JTextField();
        txtTelFax = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        cmbBuscaCondominio = new javax.swing.JComboBox();
        txtBuscaCondominio = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        txtCodigoPessoa = new javax.swing.JTextField();
        Nome = new javax.swing.JLabel();
        txtNomePessoa = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtCpfPessoa = new javax.swing.JFormattedTextField();
        jLabel47 = new javax.swing.JLabel();
        txtRgPessoa = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtEnderecoPessoa = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtBairroPessoa = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtCidadePessoa = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        cmbUfPessoa = new javax.swing.JComboBox();
        jLabel52 = new javax.swing.JLabel();
        txtCepPessoa = new javax.swing.JFormattedTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtObsPessoa = new javax.swing.JTextArea();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel17 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        txtContatoPessoa = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txtTelContatoPessoa = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtEmailPessoa = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel61 = new javax.swing.JLabel();
        txtTelResidencialPessoa = new javax.swing.JTextField();
        txtTelCelularPessoa = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        btnPessoaNova = new javax.swing.JButton();
        btnIncluirPessoa = new javax.swing.JButton();
        btnExcluirPessoa = new javax.swing.JButton();
        btnAlterarPessoa = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        cmbBuscaPessoa = new javax.swing.JComboBox();
        txtBuscaPessoa = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtCodigoEmpresa = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtNomeEmpresa = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtCnpjEmpresa = new javax.swing.JFormattedTextField();
        jLabel28 = new javax.swing.JLabel();
        txtIeEmpresa = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtEnderecoEmpresa = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtBairroEmpresa = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtCidadeEmpresa = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        cmbUfEmpresa = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        txtCepEmpresa = new javax.swing.JFormattedTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtObsEmpresa = new javax.swing.JTextArea();
        btnNovaEmpresa = new javax.swing.JButton();
        btnIncluirEmpresa = new javax.swing.JButton();
        btnAlterarEmpresa = new javax.swing.JButton();
        btnExcluirEmpresa = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        txtNomeResponsavelEmpresa = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtTelResponsavelEmpresa = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtEmailEmpresa = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtTelComercialEmpresa = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        cmbBuscaEmpresa = new javax.swing.JComboBox();
        txtBuscaEmpresa = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        txtTelFaxEmpresa = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableServicosDisponiveisPedido = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableServicosPedido = new javax.swing.JTable();
        btnAddItemServico = new javax.swing.JButton();
        btnRemoveItemServico = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtObsPedido = new javax.swing.JTextPane();
        btnFormaPagamento = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        lbDataExecucao = new javax.swing.JLabel();
        lbHoraExecucao = new javax.swing.JLabel();
        cmbHoraExecucaoPedido = new javax.swing.JComboBox();
        btnNovoPedido = new javax.swing.JButton();
        btnIncluirPedido = new javax.swing.JButton();
        btnAlterarPedido = new javax.swing.JButton();
        btnExcluirPedido = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        txtCodigoBuscaPedido = new javax.swing.JTextField();
        btnConsultaPedido = new javax.swing.JButton();
        cmbBuscaPedido = new javax.swing.JComboBox();
        jPanel32 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtNumeroPedido = new javax.swing.JTextField();
        txtDataCadastroPedido = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        cmbStatusPedido = new javax.swing.JComboBox();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txtTotalPedido = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        cmbHoraAgendamentoPedido = new javax.swing.JComboBox();
        jLabel37 = new javax.swing.JLabel();
        txtNomeClientePedido = new javax.swing.JTextField();
        btnVisualizaDadosCliente = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        txtDataEmissaoOsPadrao = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        txtHoraEmissaoOsPadrao = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        txtUsuarioOsPadrao = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        txtNomeClienteOsPadrao = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        txtTelefoneContatoOsPadrao = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        txtNomeContatoOsPadrao = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        txtEnderecoOsPadrao = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        txtBairroOsPadrao = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        txtCidadeOsPadrao = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtObsOsPadrao = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        tabelaServicosOsPadrao = new javax.swing.JTable();
        btnGerenciaServicoOs = new javax.swing.JButton();
        jLabel96 = new javax.swing.JLabel();
        txtCodigoClienteOsPadrao = new javax.swing.JTextField();
        cmbUfOrdemServico = new javax.swing.JComboBox();
        jLabel56 = new javax.swing.JLabel();
        btnRemoveServicoOs = new javax.swing.JButton();
        btnRefreshServicoOs = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        txtCodigoBuscaOsPadrao = new javax.swing.JTextField();
        btnConsultaOrdemServicoPadrao = new javax.swing.JButton();
        cmbBuscaOsPadrao = new javax.swing.JComboBox();
        btnExcluirOsPadrao = new javax.swing.JButton();
        btnAlterarOsPadrao = new javax.swing.JButton();
        btnIncluirOsPadrao = new javax.swing.JButton();
        btnNovoOsPadrao = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        txtCodigoOsPadrao = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        txtNumeroPedidoOsPadrao = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        txtHoraExecucaoOsPadrao = new javax.swing.JFormattedTextField();
        cmbStatusOsPadrao = new javax.swing.JComboBox();
        jLabel92 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        txtValorOsPadrao = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        txtCodigoContrato = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        txtDataInicioContrato = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel24 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        txtNomeCC = new javax.swing.JTextField();
        txtEnderecoCC = new javax.swing.JTextField();
        txtBairroCC = new javax.swing.JTextField();
        txtCidadeCC = new javax.swing.JTextField();
        cmbUfCC = new javax.swing.JComboBox();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txtContatoCC = new javax.swing.JTextField();
        txtTelefoneContatoCC = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtObsCC = new javax.swing.JTextArea();
        jLabel76 = new javax.swing.JLabel();
        cmdPeriodoContrato = new javax.swing.JSpinner();
        jLabel77 = new javax.swing.JLabel();
        txtDataExpiracao = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        txtValorTotalContrato = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tableServicosContrato = new javax.swing.JTable();
        btnGerenciaServicosContrato = new javax.swing.JButton();
        btnNovoContrato = new javax.swing.JButton();
        btnIncluirContrato = new javax.swing.JButton();
        btnAlterarContrato = new javax.swing.JButton();
        btnExcluirContrato = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        txtCodigoBuscaContrato = new javax.swing.JTextField();
        btnConsultaContrato = new javax.swing.JButton();
        cmbBuscaContrato = new javax.swing.JComboBox();
        jButton7 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("FrmPrincipal"); // NOI18N
        setResizable(false);

        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(997, 728));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(997, 728));

        jTabbedPane2.setFont(new java.awt.Font("Garamond", 0, 14)); // NOI18N

        jPanel5.setFont(new java.awt.Font("Garamond", 0, 14)); // NOI18N
        jPanel5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregaUsuarioTab(evt);
            }
        });

        tabelaUsuarios.setAutoCreateRowSorter(true);
        tabelaUsuarios.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tabelaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecionaLinha(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaUsuarios);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Nome do Usuário");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Senha");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Colaborador");

        cmbColaborador.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbColaborador.setEnabled(false);
        cmbColaborador.setFocusable(false);

        chkBloqueado.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        chkBloqueado.setText("Bloqueado");

        btnUsuarioNovo.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnUsuarioNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconNovo.png"))); // NOI18N
        btnUsuarioNovo.setToolTipText("Novo Usuário");
        btnUsuarioNovo.setPreferredSize(new java.awt.Dimension(65, 41));
        btnUsuarioNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioNovoActionPerformed(evt);
            }
        });

        btnIncluirUsuario.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnIncluirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconIncluir.png"))); // NOI18N
        btnIncluirUsuario.setToolTipText("Gravar Inclusão");
        btnIncluirUsuario.setMaximumSize(new java.awt.Dimension(65, 25));
        btnIncluirUsuario.setMinimumSize(new java.awt.Dimension(65, 25));
        btnIncluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirUsuarioActionPerformed(evt);
            }
        });

        btnAlterarUsuario.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnAlterarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconAlterar.png"))); // NOI18N
        btnAlterarUsuario.setToolTipText("Gravar Alteraçao");
        btnAlterarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarUsuarioActionPerformed(evt);
            }
        });

        btnExcluirUsuario.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnExcluirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconExcluir.png"))); // NOI18N
        btnExcluirUsuario.setToolTipText("Excluir Usuario");
        btnExcluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnUsuarioNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIncluirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirUsuario))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cmbColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(chkBloqueado)))))))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAlterarUsuario, btnExcluirUsuario, btnIncluirUsuario, btnUsuarioNovo});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addComponent(chkBloqueado, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuarioNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnIncluirUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnAlterarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnExcluirUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAlterarUsuario, btnExcluirUsuario, btnIncluirUsuario, btnUsuarioNovo});

        jLabel1.getAccessibleContext().setAccessibleName("");

        jTabbedPane2.addTab("Usuários", jPanel5);

        jPanel6.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregaGrupoUsuarioTab(evt);
            }
        });

        tabelaGrupoUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaGrupoUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaGrupoUsuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaGrupoUsuarios);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Grupo");

        txtGrupoUsuario.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnNovoGrupo.setText("Novo");
        btnNovoGrupo.setMaximumSize(new java.awt.Dimension(65, 41));
        btnNovoGrupo.setMinimumSize(new java.awt.Dimension(65, 41));
        btnNovoGrupo.setPreferredSize(new java.awt.Dimension(65, 41));
        btnNovoGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoGrupoActionPerformed(evt);
            }
        });

        btnIncluirGrupo.setText("Incluir");
        btnIncluirGrupo.setPreferredSize(new java.awt.Dimension(65, 39));
        btnIncluirGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirGrupoActionPerformed(evt);
            }
        });

        btnAlterarGrupo.setText("Alterar");
        btnAlterarGrupo.setPreferredSize(new java.awt.Dimension(65, 39));
        btnAlterarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarGrupoActionPerformed(evt);
            }
        });

        btnExcluirGrupo.setText("Excluir");
        btnExcluirGrupo.setPreferredSize(new java.awt.Dimension(65, 39));
        btnExcluirGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirGrupoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnNovoGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIncluirGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(txtGrupoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGrupoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnIncluirGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnAlterarGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnExcluirGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Grupos", jPanel6);

        jPanel7.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregaUsuarioCadaGrupoTab(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Usuários Colaboradores");

        cmbUsuarioColaborador.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tabelaUsuariosCadaGrupo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tabelaUsuariosCadaGrupo);

        jButton1.setLabel("+");

        jButton2.setLabel("-");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(cmbUsuarioColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(656, 656, 656))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbUsuarioColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(156, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Usuários Cada Grupo", jPanel7);

        jPanel35.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregarServicoTab(evt);
            }
        });

        tabelaServicos.setAutoCreateRowSorter(true);
        tabelaServicos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tabelaServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaServicosselecionaLinha(evt);
            }
        });
        jScrollPane14.setViewportView(tabelaServicos);

        jLabel60.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel60.setText("Nome do Serviço");

        jLabel95.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel95.setText("Tempo de Garantia (Meses)");

        jLabel98.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel98.setText("Valor");

        txtNomeServico.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtTempoGarantia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtValorServico.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtValorServico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                valorServicoFocusLost(evt);
            }
        });

        btnServicoNovo.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnServicoNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconNovo.png"))); // NOI18N
        btnServicoNovo.setToolTipText("Novo Serviço");
        btnServicoNovo.setPreferredSize(new java.awt.Dimension(65, 41));
        btnServicoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServicoNovoActionPerformed(evt);
            }
        });

        btnIncluirServico.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnIncluirServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconIncluir.png"))); // NOI18N
        btnIncluirServico.setToolTipText("Gravar Inclusão");
        btnIncluirServico.setMaximumSize(new java.awt.Dimension(65, 25));
        btnIncluirServico.setMinimumSize(new java.awt.Dimension(65, 25));
        btnIncluirServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirServicoActionPerformed(evt);
            }
        });

        btnAlterarServico.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnAlterarServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconAlterar.png"))); // NOI18N
        btnAlterarServico.setToolTipText("Gravar Alteração");
        btnAlterarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarServicoActionPerformed(evt);
            }
        });

        btnExcluirServico.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnExcluirServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconExcluir.png"))); // NOI18N
        btnExcluirServico.setToolTipText("Excluir Serviço");
        btnExcluirServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirServicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(txtNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTempoGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel98)
                                    .addGroup(jPanel35Layout.createSequentialGroup()
                                        .addComponent(txtValorServico, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(txtIdServico, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addComponent(btnServicoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIncluirServico, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterarServico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluirServico)))
                        .addGap(0, 216, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel95)
                    .addComponent(jLabel98))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTempoGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnServicoNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnIncluirServico, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnAlterarServico, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(btnExcluirServico, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        jTabbedPane2.addTab("Serviços", jPanel35);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2))
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("Cadastrosgerais");

        jTabbedPane1.addTab("Cadastros SysControl", jPanel1);

        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTabbedPane3.setMaximumSize(new java.awt.Dimension(1288, 691));
        jTabbedPane3.setMinimumSize(new java.awt.Dimension(1288, 691));

        jPanel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel8.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregaClienteCondominioTab(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Código");

        txtCodigoCliente.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtCodigoCliente.setForeground(new java.awt.Color(255, 0, 0));
        txtCodigoCliente.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtCodigoCliente.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Condominio");

        txtNome.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Cnpj");

        txtCnpj.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("IE");

        txtIe.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Endereço");

        txtEndereco.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Bairro");

        txtBairro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Cidade");

        txtCidade.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("UF");

        cmbUf.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbUf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RJ", "SP" }));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Cep");

        txtCep.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtObs.setColumns(20);
        txtObs.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtObs.setRows(5);
        txtObs.setPreferredSize(new java.awt.Dimension(926, 50));
        txtObs.setRequestFocusEnabled(false);
        jScrollPane4.setViewportView(txtObs);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Blocos");

        txtBlocos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Andares");

        txtAndares.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Ap. por Andar");

        txtApAndar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Quartos");

        txtQuartos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnCondominioNovo.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnCondominioNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconNovo.png"))); // NOI18N
        btnCondominioNovo.setToolTipText("Novo Usuário");
        btnCondominioNovo.setPreferredSize(new java.awt.Dimension(65, 41));
        btnCondominioNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCondominioNovoActionPerformed(evt);
            }
        });

        btnIncluirCondominio.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnIncluirCondominio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconIncluir.png"))); // NOI18N
        btnIncluirCondominio.setToolTipText("Gravar Inclusão");
        btnIncluirCondominio.setMaximumSize(new java.awt.Dimension(65, 25));
        btnIncluirCondominio.setMinimumSize(new java.awt.Dimension(65, 25));
        btnIncluirCondominio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirCondominioActionPerformed(evt);
            }
        });

        btnAlterarClienteCondominio.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnAlterarClienteCondominio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconAlterar.png"))); // NOI18N
        btnAlterarClienteCondominio.setToolTipText("Gravar Alteraçao");
        btnAlterarClienteCondominio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarClienteCondominioActionPerformed(evt);
            }
        });

        btnExcluirCondominio.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnExcluirCondominio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconExcluir.png"))); // NOI18N
        btnExcluirCondominio.setToolTipText("Excluir Usuario");
        btnExcluirCondominio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCondominioActionPerformed(evt);
            }
        });

        jSeparator1.setMaximumSize(new java.awt.Dimension(965, 10));
        jSeparator1.setPreferredSize(new java.awt.Dimension(965, 10));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Contatos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jPanel11.setForeground(new java.awt.Color(51, 51, 51));
        jPanel11.setMaximumSize(new java.awt.Dimension(946, 146));
        jPanel11.setMinimumSize(new java.awt.Dimension(946, 146));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Síndico");

        txtNomeSindico.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setText("Telefone Síndico");

        txtTelSindico.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Zelador");

        txtNomeZelador.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setText("Telefone Zelador");

        txtTelZelador.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel34.setText("E-Mail");

        txtEmailCondominio.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEmailCondominio, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeSindico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(txtTelSindico, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNomeZelador, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(txtTelZelador, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeSindico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelSindico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEmailCondominio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeZelador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelZelador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jSeparator2.setMaximumSize(new java.awt.Dimension(965, 10));
        jSeparator2.setPreferredSize(new java.awt.Dimension(965, 10));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel23.setText("Telefone Comercial");

        txtTelComercial.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtTelFax.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel24.setText("Telefone Fax");

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca Condomínio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        cmbBuscaCondominio.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbBuscaCondominio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Nome", "Cnpj", "Endereço", "Bairro", "Telefone Comercial" }));

        txtBuscaCondominio.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtBuscaCondominio.setMinimumSize(new java.awt.Dimension(15, 23));
        txtBuscaCondominio.setPreferredSize(new java.awt.Dimension(10, 23));
        txtBuscaCondominio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarCondominioKeyPressed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarCondominios(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbBuscaCondominio, 0, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaCondominio, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBuscaCondominio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscaCondominio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel13))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbUf, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIe, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelFax, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBlocos, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(txtAndares, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApAndar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(txtQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(btnCondominioNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIncluirCondominio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterarClienteCondominio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluirCondominio)
                                .addGap(108, 108, 108)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(jLabel24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBlocos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAndares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApAndar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cmbUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnCondominioNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIncluirCondominio, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAlterarClienteCondominio)
                        .addComponent(btnExcluirCondominio))
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Condomínios", jPanel8);

        jPanel10.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregarClientePessoaTab(evt);
            }
        });

        jPanel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel16.setMaximumSize(new java.awt.Dimension(947, 609));
        jPanel16.setMinimumSize(new java.awt.Dimension(947, 609));
        jPanel16.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel16carregaClienteCondominioTab(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel44.setText("Código");

        txtCodigoPessoa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtCodigoPessoa.setForeground(new java.awt.Color(255, 0, 0));
        txtCodigoPessoa.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtCodigoPessoa.setEnabled(false);

        Nome.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Nome.setText("Nome");

        txtNomePessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNomePessoa.setMinimumSize(new java.awt.Dimension(10, 23));
        txtNomePessoa.setPreferredSize(new java.awt.Dimension(10, 23));

        jLabel46.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel46.setText("CPF");

        txtCpfPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel47.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel47.setText("RG");

        txtRgPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel48.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel48.setText("Endereço");

        txtEnderecoPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEnderecoPessoa.setPreferredSize(new java.awt.Dimension(10, 23));
        txtEnderecoPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoPessoaActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel49.setText("Bairro");

        txtBairroPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBairroPessoa.setMaximumSize(new java.awt.Dimension(40, 2147483647));
        txtBairroPessoa.setMinimumSize(new java.awt.Dimension(40, 23));
        txtBairroPessoa.setPreferredSize(new java.awt.Dimension(10, 23));

        jLabel50.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel50.setText("Cidade");

        txtCidadePessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel51.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel51.setText("UF");

        cmbUfPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbUfPessoa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RJ", "SP" }));

        jLabel52.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel52.setText("Cep");

        txtCepPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtObsPessoa.setColumns(20);
        txtObsPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtObsPessoa.setRows(5);
        jScrollPane6.setViewportView(txtObsPessoa);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Contato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabel57.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel57.setText("Contato");

        txtContatoPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel58.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel58.setText("Telefone Contato");

        txtTelContatoPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel59.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel59.setText("E-Mail");

        txtEmailPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContatoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(txtTelContatoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59)
                    .addComponent(txtEmailPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEmailPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel58)
                            .addComponent(jLabel57))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtContatoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelContatoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel61.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel61.setText("Telefone Residencial");

        txtTelResidencialPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtTelCelularPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel62.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel62.setText("Celular");

        btnPessoaNova.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnPessoaNova.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconNovo.png"))); // NOI18N
        btnPessoaNova.setToolTipText("Novo Usuário");
        btnPessoaNova.setPreferredSize(new java.awt.Dimension(65, 41));
        btnPessoaNova.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPessoaNovaActionPerformed(evt);
            }
        });

        btnIncluirPessoa.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnIncluirPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconIncluir.png"))); // NOI18N
        btnIncluirPessoa.setToolTipText("Gravar Inclusão");
        btnIncluirPessoa.setMaximumSize(new java.awt.Dimension(65, 25));
        btnIncluirPessoa.setMinimumSize(new java.awt.Dimension(65, 25));
        btnIncluirPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirPessoaActionPerformed(evt);
            }
        });

        btnExcluirPessoa.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnExcluirPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconExcluir.png"))); // NOI18N
        btnExcluirPessoa.setToolTipText("Excluir Usuario");
        btnExcluirPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirPessoaActionPerformed(evt);
            }
        });

        btnAlterarPessoa.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnAlterarPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconAlterar.png"))); // NOI18N
        btnAlterarPessoa.setToolTipText("Gravar Alteraçao");
        btnAlterarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarPessoaActionPerformed(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(51, 51, 51));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca Pessoa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        cmbBuscaPessoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbBuscaPessoa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Nome", "CPF", "Endereço", "Bairro", "Telefone Residencial", "Celular" }));

        txtBuscaPessoa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtBuscaPessoa.setMinimumSize(new java.awt.Dimension(15, 23));
        txtBuscaPessoa.setPreferredSize(new java.awt.Dimension(10, 23));
        txtBuscaPessoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarClientePessoaFisicaKeyPressed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClientePessoaFisica(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarClientePessoaFisicaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbBuscaPessoa, 0, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscaPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBuscaPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscaPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(txtEnderecoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(txtBairroPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(txtCidadePessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addGap(101, 101, 101)
                                .addComponent(jLabel51))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txtCepPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbUfPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel44))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nome)
                                    .addComponent(txtNomePessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel46)
                                    .addComponent(txtCpfPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRgPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel47))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel61)
                                    .addComponent(txtTelResidencialPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel62)
                                    .addComponent(txtTelCelularPessoa)))
                            .addComponent(jScrollPane6)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(btnPessoaNova, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIncluirPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterarPessoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluirPessoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Nome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomePessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel47))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCpfPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRgPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTelResidencialPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelCelularPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel62)
                                .addGap(29, 29, 29)))))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel52)
                        .addComponent(jLabel51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(jLabel49))
                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(1, 1, 1)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmbUfPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCepPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBairroPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCidadePessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEnderecoPessoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnPessoaNova, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIncluirPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAlterarPessoa)
                        .addComponent(btnExcluirPessoa))
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Pessoa Física", jPanel10);

        jPanel9.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregaClienteEmpresaTab(evt);
            }
        });

        jPanel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel13.setMaximumSize(new java.awt.Dimension(1300, 620));
        jPanel13.setMinimumSize(new java.awt.Dimension(1300, 620));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel25.setText("Código");

        txtCodigoEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtCodigoEmpresa.setForeground(new java.awt.Color(255, 0, 0));
        txtCodigoEmpresa.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtCodigoEmpresa.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel26.setText("Empresa");

        txtNomeEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel27.setText("Cnpj");

        txtCnpjEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel28.setText("IE");

        txtIeEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel29.setText("Endereço");

        txtEnderecoEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEnderecoEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoEmpresaActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel30.setText("Bairro");

        txtBairroEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel31.setText("Cidade");

        txtCidadeEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCidadeEmpresa.setAlignmentX(0.0F);
        txtCidadeEmpresa.setAlignmentY(0.0F);
        txtCidadeEmpresa.setMaximumSize(new java.awt.Dimension(500, 50));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel32.setText("UF");

        cmbUfEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbUfEmpresa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RJ", "SP" }));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel33.setText("Cep");

        txtCepEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtObsEmpresa.setColumns(20);
        txtObsEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtObsEmpresa.setRows(5);
        jScrollPane5.setViewportView(txtObsEmpresa);

        btnNovaEmpresa.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnNovaEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconNovo.png"))); // NOI18N
        btnNovaEmpresa.setToolTipText("Novo Usuário");
        btnNovaEmpresa.setPreferredSize(new java.awt.Dimension(65, 41));
        btnNovaEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaEmpresaActionPerformed(evt);
            }
        });

        btnIncluirEmpresa.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnIncluirEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconIncluir.png"))); // NOI18N
        btnIncluirEmpresa.setToolTipText("Gravar Inclusão");
        btnIncluirEmpresa.setMaximumSize(new java.awt.Dimension(65, 25));
        btnIncluirEmpresa.setMinimumSize(new java.awt.Dimension(65, 25));
        btnIncluirEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirEmpresaActionPerformed(evt);
            }
        });

        btnAlterarEmpresa.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnAlterarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconAlterar.png"))); // NOI18N
        btnAlterarEmpresa.setToolTipText("Gravar Alteraçao");
        btnAlterarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarEmpresaActionPerformed(evt);
            }
        });

        btnExcluirEmpresa.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnExcluirEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconExcluir.png"))); // NOI18N
        btnExcluirEmpresa.setToolTipText("Excluir Usuario");
        btnExcluirEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirEmpresaActionPerformed(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Contatos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel38.setText("Nome Responsável");

        txtNomeResponsavelEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel39.setText("Telefone Responsável");

        txtTelResponsavelEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel35.setText("E-Mail");

        txtEmailEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeResponsavelEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtTelResponsavelEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmailEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39)
                        .addComponent(jLabel35))
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeResponsavelEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelResponsavelEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel42.setText("Telefone Comercial");

        txtTelComercialEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel43.setText("Telefone Fax");

        jPanel15.setBackground(new java.awt.Color(51, 51, 51));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca Empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        cmbBuscaEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbBuscaEmpresa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Nome", "Cnpj", "Endereço", "Bairro", "Telefone Comercial" }));
        cmbBuscaEmpresa.setSelectedItem("Nome");
        cmbBuscaEmpresa.setBorder(null);
        cmbBuscaEmpresa.setOpaque(false);

        txtBuscaEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtBuscaEmpresa.setMinimumSize(new java.awt.Dimension(15, 23));
        txtBuscaEmpresa.setPreferredSize(new java.awt.Dimension(10, 23));
        txtBuscaEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarClientePessoaJuridicaKeyPressed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClientePessoaJuridica(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarClientePessoaJuridicaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbBuscaEmpresa, 0, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBuscaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtTelFaxEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btnNovaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIncluirEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirEmpresa)
                        .addGap(112, 112, 112)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(txtEnderecoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel30)
                                    .addComponent(txtBairroEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCidadeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCepEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(cmbUfEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(txtCodigoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNomeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(txtCnpjEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(txtIeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(txtTelComercialEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(txtTelFaxEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel29)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(jLabel26))
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGap(49, 49, 49)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNomeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCnpjEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                            .addComponent(jLabel28)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtIeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addGap(29, 29, 29)))))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelFaxEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelComercialEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEnderecoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel31))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBairroEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCidadeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel32))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCepEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbUfEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(24, 24, 24)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnNovaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIncluirEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAlterarEmpresa)
                        .addComponent(btnExcluirEmpresa))
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 955, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Empresas", jPanel9);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clientes", jPanel2);

        jPanel19.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregaPedidoTab(evt);
            }
        });

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jPanel20.setMaximumSize(new java.awt.Dimension(972, 484));
        jPanel20.setMinimumSize(new java.awt.Dimension(972, 484));

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados do Serviço"));
        jPanel22.setMaximumSize(new java.awt.Dimension(942, 162));
        jPanel22.setMinimumSize(new java.awt.Dimension(942, 162));

        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Serviços Disponíveis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jScrollPane7.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        tableServicosDisponiveisPedido.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tableServicosDisponiveisPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tableServicosDisponiveisPedido);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Itens do Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jScrollPane8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        tableServicosPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableServicosPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                informaNovoValorServicoPedido(evt);
            }
        });
        jScrollPane8.setViewportView(tableServicosPedido);

        btnAddItemServico.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAddItemServico.setText("Adicionar");
        btnAddItemServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemServicoActionPerformed(evt);
            }
        });

        btnRemoveItemServico.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnRemoveItemServico.setText("Remover");
        btnRemoveItemServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveItemServicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnRemoveItemServico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddItemServico))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnAddItemServico)
                .addGap(18, 18, 18)
                .addComponent(btnRemoveItemServico)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jLabel66.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel66.setText("Observações");

        txtObsPedido.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtObsPedido.setMaximumSize(new java.awt.Dimension(6, 25));
        jScrollPane9.setViewportView(txtObsPedido);

        btnFormaPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cifrao.png"))); // NOI18N
        btnFormaPagamento.setText("Pagamento");
        btnFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormaPagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFormaPagamento, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFormaPagamento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(51, 51, 51));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Execução", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel21.setMaximumSize(new java.awt.Dimension(223, 99));

        lbDataExecucao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbDataExecucao.setForeground(new java.awt.Color(255, 255, 255));
        lbDataExecucao.setText("Data");

        lbHoraExecucao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbHoraExecucao.setForeground(new java.awt.Color(255, 255, 255));
        lbHoraExecucao.setText("Hora");

        cmbHoraExecucaoPedido.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cmbHoraExecucaoPedido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07:00:00", "07:30:00", "08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00", "19:30:00", "20:00:00", "20:30:00", "21:00:00", "21:30:00", "22:00:00", "22:30:00", "23:00:00" }));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(lbDataExecucao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbHoraExecucaoPedido, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbHoraExecucao, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoraExecucao)
                    .addComponent(lbDataExecucao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbHoraExecucaoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNovoPedido.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnNovoPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconNovo.png"))); // NOI18N
        btnNovoPedido.setToolTipText("Novo Usuário");
        btnNovoPedido.setPreferredSize(new java.awt.Dimension(65, 41));
        btnNovoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoPedidoActionPerformed(evt);
            }
        });

        btnIncluirPedido.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnIncluirPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconIncluir.png"))); // NOI18N
        btnIncluirPedido.setToolTipText("Gravar Inclusão");
        btnIncluirPedido.setMaximumSize(new java.awt.Dimension(65, 25));
        btnIncluirPedido.setMinimumSize(new java.awt.Dimension(65, 25));
        btnIncluirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirPedidoActionPerformed(evt);
            }
        });

        btnAlterarPedido.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnAlterarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconAlterar.png"))); // NOI18N
        btnAlterarPedido.setToolTipText("Gravar Alteraçao");
        btnAlterarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarPedidoActionPerformed(evt);
            }
        });

        btnExcluirPedido.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnExcluirPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconExcluir.png"))); // NOI18N
        btnExcluirPedido.setToolTipText("Excluir Usuario");
        btnExcluirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirPedidoActionPerformed(evt);
            }
        });

        jPanel23.setBackground(new java.awt.Color(51, 51, 51));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtCodigoBuscaPedido.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        btnConsultaPedido.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnConsultaPedido.setText("Buscar");
        btnConsultaPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaPedidoActionPerformed(evt);
            }
        });

        cmbBuscaPedido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Cliente" }));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbBuscaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCodigoBuscaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConsultaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultaPedido)
                    .addComponent(txtCodigoBuscaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBuscaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel32.setBackground(new java.awt.Color(51, 51, 51));
        jPanel32.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel36.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Numero Pedido");

        txtNumeroPedido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNumeroPedido.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNumeroPedido.setEnabled(false);

        txtDataCadastroPedido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDataCadastroPedido.setForeground(new java.awt.Color(255, 255, 255));
        txtDataCadastroPedido.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDataCadastroPedido.setEnabled(false);

        jLabel55.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Data Cadastro");

        cmbStatusPedido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cmbStatusPedido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aberto", "Fechado", "Indisp. Cliente", "Cancelado", " " }));
        cmbStatusPedido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                aplicarRegrasStatusPedido(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Status");

        jLabel65.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Total do Pedido");

        txtTotalPedido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTotalPedido.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotalPedido.setEnabled(false);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumeroPedido, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDataCadastroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63)
                    .addComponent(cmbStatusPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(txtTotalPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel55)
                    .addComponent(jLabel63)
                    .addComponent(jLabel65))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroPedido)
                    .addComponent(txtDataCadastroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatusPedido)
                    .addComponent(txtTotalPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agendamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jPanel34.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel99.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel99.setText("Data");

        jLabel100.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel100.setText("Hora");

        cmbHoraAgendamentoPedido.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cmbHoraAgendamentoPedido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07:00:00", "07:30:00", "08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00", "19:30:00", "20:00:00", "20:30:00", "21:00:00", "21:30:00", "22:00:00", "22:30:00", "23:00:00" }));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbHoraAgendamentoPedido, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel100, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(jLabel99))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbHoraAgendamentoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Nome do Cliente");

        txtNomeClientePedido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNomeClientePedido.setAlignmentX(0.0F);
        txtNomeClientePedido.setEnabled(false);

        btnVisualizaDadosCliente.setText("Visualizar");
        btnVisualizaDadosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizaDadosClienteActionPerformed(evt);
            }
        });

        jButton8.setText("Imprimir Pedido");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btnNovoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIncluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(txtNomeClientePedido, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizaDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnVisualizaDadosCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNomeClientePedido, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnExcluirPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNovoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAlterarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIncluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel20.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("Pedidos", jPanel19);

        jPanel4.setEnabled(false);

        jPanel28.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregaOrdemServicoTab(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel80.setText("Data Emissão");

        txtDataEmissaoOsPadrao.setEditable(false);
        txtDataEmissaoOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtDataEmissaoOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDataEmissaoOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel81.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel81.setText("Hora Emissão");

        txtHoraEmissaoOsPadrao.setEditable(false);
        txtHoraEmissaoOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtHoraEmissaoOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtHoraEmissaoOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel82.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel82.setText("Usuário");

        txtUsuarioOsPadrao.setEditable(false);
        txtUsuarioOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtUsuarioOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtUsuarioOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jPanel29.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel29.setMaximumSize(new java.awt.Dimension(947, 357));

        jLabel85.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel85.setText("Nome Cliente");

        txtNomeClienteOsPadrao.setEditable(false);
        txtNomeClienteOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtNomeClienteOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNomeClienteOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel86.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel86.setText("Telefone Contato");

        txtTelefoneContatoOsPadrao.setEditable(false);
        txtTelefoneContatoOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefoneContatoOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTelefoneContatoOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel87.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel87.setText("Nome Contato");

        txtNomeContatoOsPadrao.setEditable(false);
        txtNomeContatoOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtNomeContatoOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNomeContatoOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel88.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel88.setText("Endereço");

        txtEnderecoOsPadrao.setEditable(false);
        txtEnderecoOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtEnderecoOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEnderecoOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel89.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel89.setText("Bairro");

        txtBairroOsPadrao.setEditable(false);
        txtBairroOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtBairroOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBairroOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel90.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel90.setText("Cidade");

        txtCidadeOsPadrao.setEditable(false);
        txtCidadeOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtCidadeOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCidadeOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel91.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel91.setText("Observação");

        txtObsOsPadrao.setColumns(20);
        txtObsOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtObsOsPadrao.setRows(5);
        txtObsOsPadrao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtObsOsPadrao.setMaximumSize(new java.awt.Dimension(224, 89));
        jScrollPane12.setViewportView(txtObsOsPadrao);

        jScrollPane13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Serviços Solicitados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jScrollPane13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tabelaServicosOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tabelaServicosOsPadrao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(tabelaServicosOsPadrao);

        btnGerenciaServicoOs.setText("Gerenciar");
        btnGerenciaServicoOs.setEnabled(false);
        btnGerenciaServicoOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciaServicoOsActionPerformed(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel96.setText("Código Cliente");

        txtCodigoClienteOsPadrao.setEditable(false);
        txtCodigoClienteOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtCodigoClienteOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cmbUfOrdemServico.setBackground(new java.awt.Color(204, 204, 204));
        cmbUfOrdemServico.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbUfOrdemServico.setEnabled(false);

        jLabel56.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel56.setText("UF");

        btnRemoveServicoOs.setText("jButton10");
        btnRemoveServicoOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerServicoOsActionPerformed(evt);
            }
        });

        btnRefreshServicoOs.setText("jButton11");
        btnRefreshServicoOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshServicoOsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnRefreshServicoOs, 0, 0, Short.MAX_VALUE)
                                    .addComponent(btnRemoveServicoOs, javax.swing.GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addComponent(btnGerenciaServicoOs, javax.swing.GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE))
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel88)
                                    .addComponent(txtEnderecoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel89)
                                    .addComponent(txtBairroOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel90)
                                    .addComponent(txtCidadeOsPadrao, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefoneContatoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel86))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel87)
                                    .addComponent(txtNomeContatoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23))
                    .addComponent(jLabel91)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel96)
                            .addComponent(txtCodigoClienteOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeClienteOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel85))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(cmbUfOrdemServico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel96)
                            .addComponent(jLabel85))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoClienteOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeClienteOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUfOrdemServico)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel90)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidadeOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnderecoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel89)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairroOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel86)
                            .addComponent(jLabel87))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefoneContatoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeContatoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGerenciaServicoOs, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(btnRemoveServicoOs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRefreshServicoOs)))))
                .addContainerGap())
        );

        jPanel30.setBackground(new java.awt.Color(51, 51, 51));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca Ordem de Serviço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel30.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtCodigoBuscaOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        btnConsultaOrdemServicoPadrao.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnConsultaOrdemServicoPadrao.setText("Buscar");
        btnConsultaOrdemServicoPadrao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaOrdemServicoPadraoActionPerformed(evt);
            }
        });

        cmbBuscaOsPadrao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código OS", "Número Pedido", "Nome Cliente" }));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbBuscaOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCodigoBuscaOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConsultaOrdemServicoPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultaOrdemServicoPadrao)
                    .addComponent(txtCodigoBuscaOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBuscaOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnExcluirOsPadrao.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnExcluirOsPadrao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconExcluir.png"))); // NOI18N
        btnExcluirOsPadrao.setToolTipText("Excluir Usuario");
        btnExcluirOsPadrao.setEnabled(false);
        btnExcluirOsPadrao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirOsPadraoActionPerformed(evt);
            }
        });

        btnAlterarOsPadrao.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnAlterarOsPadrao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconAlterar.png"))); // NOI18N
        btnAlterarOsPadrao.setToolTipText("Gravar Alteraçao");
        btnAlterarOsPadrao.setEnabled(false);
        btnAlterarOsPadrao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarOsPadraoActionPerformed(evt);
            }
        });

        btnIncluirOsPadrao.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnIncluirOsPadrao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconIncluir.png"))); // NOI18N
        btnIncluirOsPadrao.setToolTipText("Gravar Inclusão");
        btnIncluirOsPadrao.setEnabled(false);
        btnIncluirOsPadrao.setMaximumSize(new java.awt.Dimension(65, 25));
        btnIncluirOsPadrao.setMinimumSize(new java.awt.Dimension(65, 25));
        btnIncluirOsPadrao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirOsPadraoActionPerformed(evt);
            }
        });

        btnNovoOsPadrao.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnNovoOsPadrao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconNovo.png"))); // NOI18N
        btnNovoOsPadrao.setToolTipText("Novo Usuário");
        btnNovoOsPadrao.setPreferredSize(new java.awt.Dimension(65, 41));
        btnNovoOsPadrao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoOsPadraoActionPerformed(evt);
            }
        });

        jPanel31.setBackground(new java.awt.Color(51, 51, 51));
        jPanel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), null, null));
        jPanel31.setMaximumSize(new java.awt.Dimension(900, 84));
        jPanel31.setPreferredSize(new java.awt.Dimension(900, 84));

        jLabel79.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("OS");

        txtCodigoOsPadrao.setEditable(false);
        txtCodigoOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtCodigoOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCodigoOsPadrao.setDisabledTextColor(new java.awt.Color(0, 255, 0));

        jLabel93.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Pedido/Contrato");

        txtNumeroPedidoOsPadrao.setEditable(false);
        txtNumeroPedidoOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtNumeroPedidoOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNumeroPedidoOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel84.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("Data Execução");

        jLabel94.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("Hora Execução");

        txtHoraExecucaoOsPadrao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        txtHoraExecucaoOsPadrao.setEnabled(false);
        txtHoraExecucaoOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtHoraExecucaoOsPadrao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validarHoraExecucao(evt);
            }
        });

        cmbStatusOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cmbStatusOsPadrao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aberta", "Fechada", "Indisp. Cliente", "Cancelada" }));
        cmbStatusOsPadrao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                statusFocusLost(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("Status OS");

        jLabel83.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Total");

        txtValorOsPadrao.setEditable(false);
        txtValorOsPadrao.setBackground(new java.awt.Color(204, 204, 204));
        txtValorOsPadrao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtValorOsPadrao.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorOsPadrao.setEnabled(false);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79)
                    .addComponent(txtCodigoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumeroPedidoOsPadrao)
                    .addComponent(jLabel93))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel84)
                .addGap(32, 32, 32)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoraExecucaoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel94))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel92)
                    .addComponent(cmbStatusOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel83)
                    .addComponent(txtValorOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel31Layout.createSequentialGroup()
                            .addComponent(jLabel92)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbStatusOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel79)
                                    .addComponent(jLabel94))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCodigoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoraExecucaoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel93)
                                    .addComponent(jLabel84))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroPedidoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel28Layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtHoraEmissaoOsPadrao)
                                            .addComponent(jLabel81)))
                                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtDataEmissaoOsPadrao)
                                        .addComponent(jLabel80)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel82)
                                    .addComponent(txtUsuarioOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnNovoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIncluirOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarOsPadrao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirOsPadrao)
                        .addGap(112, 112, 112)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataEmissaoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel81)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoraEmissaoOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuarioOsPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnExcluirOsPadrao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNovoOsPadrao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIncluirOsPadrao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAlterarOsPadrao))
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ordens de Serviço", jPanel4);

        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                carregarContratoTab(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel64.setText("Código Contrato");

        txtCodigoContrato.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCodigoContrato.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCodigoContrato.setEnabled(false);

        jLabel67.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel67.setText("Data Inicio Contrato");

        txtDataInicioContrato.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados do Cliente"));
        jPanel24.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jPanel24.setMaximumSize(new java.awt.Dimension(924, 228));
        jPanel24.setPreferredSize(new java.awt.Dimension(924, 228));

        jLabel68.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel68.setText("Cliente");

        jLabel69.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel69.setText("Endereço");

        jLabel70.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel70.setText("UF");

        jLabel71.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel71.setText("Bairro");

        txtNomeCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtEnderecoCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtBairroCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtCidadeCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cmbUfCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel72.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel72.setText("Cidade");

        jLabel73.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel73.setText("Nome Contato");

        jLabel74.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel74.setText("Telefone Contato");

        txtContatoCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtTelefoneContatoCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel75.setText("Observações");

        txtObsCC.setColumns(20);
        txtObsCC.setRows(5);
        jScrollPane10.setViewportView(txtObsCC);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel75)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel24Layout.createSequentialGroup()
                                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel68)
                                        .addComponent(txtNomeCC, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel69)
                                        .addComponent(txtEnderecoCC, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel70)
                                        .addComponent(cmbUfCC, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel24Layout.createSequentialGroup()
                                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel71)
                                        .addComponent(txtBairroCC, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel72)
                                        .addComponent(txtCidadeCC, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel73)
                                        .addComponent(txtContatoCC, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTelefoneContatoCC, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel74))))
                            .addGap(280, 280, 280))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnderecoCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addGap(25, 25, 25))
                    .addComponent(cmbUfCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidadeCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairroCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContatoCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefoneContatoCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel76.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel76.setText("Prazo");

        cmdPeriodoContrato.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel77.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel77.setText("Data Expiração");

        txtDataExpiracao.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDataExpiracao.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDataExpiracao.setEnabled(false);

        jLabel78.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel78.setText("Valor Total Contrato");

        txtValorTotalContrato.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtValorTotalContrato.setEnabled(false);

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Serviços Contratados"));
        jPanel25.setMaximumSize(new java.awt.Dimension(924, 300));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );

        tableServicosContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(tableServicosContrato);

        btnGerenciaServicosContrato.setText("Gerenciar");
        btnGerenciaServicosContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciaServicosContratoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap(152, Short.MAX_VALUE)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGerenciaServicosContrato)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGerenciaServicosContrato)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(269, 269, 269)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNovoContrato.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnNovoContrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconNovo.png"))); // NOI18N
        btnNovoContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoContratoActionPerformed(evt);
            }
        });

        btnIncluirContrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconIncluir.png"))); // NOI18N
        btnIncluirContrato.setEnabled(false);
        btnIncluirContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirContratoActionPerformed(evt);
            }
        });

        btnAlterarContrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ìconAlterar.png"))); // NOI18N
        btnAlterarContrato.setEnabled(false);
        btnAlterarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarContratoActionPerformed(evt);
            }
        });

        btnExcluirContrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconExcluir.png"))); // NOI18N
        btnExcluirContrato.setEnabled(false);
        btnExcluirContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirContratoActionPerformed(evt);
            }
        });

        jPanel27.setBackground(new java.awt.Color(51, 51, 51));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca Contrato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel27.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtCodigoBuscaContrato.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        btnConsultaContrato.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnConsultaContrato.setText("Buscar");
        btnConsultaContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaContratoActionPerformed(evt);
            }
        });

        cmbBuscaContrato.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Cliente" }));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbBuscaContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCodigoBuscaContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConsultaContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultaContrato)
                    .addComponent(txtCodigoBuscaContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBuscaContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton7.setText("Emitir Ordem de Serviço");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnNovoContrato)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIncluirContrato)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterarContrato)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluirContrato)))
                        .addGap(78, 78, 78)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(txtCodigoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDataInicioContrato)
                            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdPeriodoContrato)
                            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77)
                            .addComponent(txtDataExpiracao, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(206, 206, 206)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtValorTotalContrato)
                            .addComponent(jLabel78))))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64)
                            .addComponent(jLabel67)
                            .addComponent(jLabel76)
                            .addComponent(jLabel77))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataInicioContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdPeriodoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataExpiracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorTotalContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnExcluirContrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAlterarContrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIncluirContrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNovoContrato, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)))
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Controle de Contratos", jPanel3);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1002, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Duplicatas", jPanel33);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Tab");

        pack();
    }// </editor-fold>//GEN-END:initComponents



public void setBtnCadastroContrato(boolean b){
        btnIncluirContrato.setEnabled(b);
}

public void setBtnAlteraContrato(boolean b){
        btnAlterarContrato.setEnabled(b);
}

public void setBtnExcluirContrato(boolean b){
        btnExcluirContrato.setEnabled(b);
}





    private void carregaUsuarioTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregaUsuarioTab
        // TODO add your handling code here:

        //Monta o grid contendo os dados do usuario
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        setUsuarioAction(new UsuarioAction());
        try {
            //Monta tabela de usuarios no grid
            getUsuarioAction().populaTabelaUsuarios();
        } catch (UsuarioException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
        }
        tabelaUsuarios.setModel(getUsuarioAction().getUsuarioTableModel());
        tabelaUsuarios.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tabelaUsuarios.getColumnModel().getColumn(1).setCellRenderer(renderer);
        tabelaUsuarios.getColumnModel().getColumn(2).setCellRenderer(renderer);
        tabelaUsuarios.getColumnModel().getColumn(3).setCellRenderer(renderer);
        tabelaUsuarios.getColumnModel().getColumn(0).setMaxWidth(40);
        tabelaUsuarios.getColumnModel().getColumn(1).setMaxWidth(200);
        tabelaUsuarios.getColumnModel().getColumn(2).setMaxWidth(130);
        tabelaUsuarios.getColumnModel().getColumn(3).setMaxWidth(100);
        

        /*Limpa os campos da tela*/
        this.limparCamposTelaUsuario();

        /* Carrega Combo de Usuarios */
        //this.carregarComboColaboradores();

        this.carregarComboColaboradores();
        
        /*Desabilita o combo de colaboradores*/
        this.cmbColaborador.setEnabled(false);
        
    }//GEN-LAST:event_carregaUsuarioTab

    private void selecionaLinha(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionaLinha
        // TODO add your handling code here:

        /*Desabilita o combo de colaboradores*/
        this.cmbColaborador.setEnabled(false);

        //this.setUsuarioTela(this.getUsuarioAction().getUsuarioTableModel().getLinhas().get(tabelaUsuarios.getSelectedRow()));
        Usuario usuarioSelecionado = this.getUsuarioAction().getUsuarioTableModel().getLinhas().get(tabelaUsuarios.getSelectedRow());
        
        txtNomeUsuario.setText(usuarioSelecionado.getUsuario());
        txtSenha.setText(usuarioSelecionado.getSenha());
        try {
            usuarioSelecionado = this.usuarioAction.getUsuario(usuarioSelecionado);
        } catch (UsuarioException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
        }

        if(usuarioSelecionado.getBloqueado()== 0){
            chkBloqueado.setSelected(false);
        }else{
            chkBloqueado.setSelected(true);
        }

        Colaborador colaborador = usuarioSelecionado.getColaborador();
        cmbColaborador.setSelectedItem(colaborador.getNome());
        
    }//GEN-LAST:event_selecionaLinha

    private void btnUsuarioNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioNovoActionPerformed
        // TODO add your handling code here:
        this.limparCamposTelaUsuario();
        cmbColaborador.setEnabled(true);
    }//GEN-LAST:event_btnUsuarioNovoActionPerformed

    private void btnIncluirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirUsuarioActionPerformed
        try {
            // TODO add your handling code here:

            Usuario usuario = new Usuario();
            Colaborador colaborador = new Colaborador();
            int bloqueado = 0;

            usuario.setUsuario(txtNomeUsuario.getText());
            usuario.setSenha(txtSenha.getText());

            if(chkBloqueado.isSelected()){
                bloqueado = 1;
            }
            usuario.setBloqueado(bloqueado);
            colaborador.setId(this.getUsuarioAction().getIdColaborador((String)cmbColaborador.getSelectedItem()));
            colaborador.setNome((String)cmbColaborador.getSelectedItem());
            usuario.setColaborador(colaborador);

            /*Valida se o colaborador ja nao possui usuario cadastrado*/
            if(this.getUsuarioAction().isUsuarioCadastrado(usuario)){
                javax.swing.JOptionPane.showMessageDialog(this, "Já existe um usuário cadastrado para o Colaborador selecionado!","Atenção - Aviso",0);
            }else if(this.validaCamposUsuario()){
                this.getUsuarioAction().incluir(usuario);
                javax.swing.JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!","Aviso",1);
                this.limparCamposTelaUsuario();
                getUsuarioAction().populaTabelaUsuarios();
                tabelaUsuarios.setModel(getUsuarioAction().getUsuarioTableModel());
                cmbColaborador.setEnabled(false);
            }
        } catch (UsuarioException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
        }
    }//GEN-LAST:event_btnIncluirUsuarioActionPerformed

    private void btnAlterarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarUsuarioActionPerformed
        try {
            // TODO add your handling code here:
            Usuario usuario = this.getUsuarioTela();
            Colaborador colaborador = new Colaborador();
            int bloqueado = 0;

            usuario.setUsuario(txtNomeUsuario.getText());
            usuario.setSenha(txtSenha.getText());
            
            if(chkBloqueado.isSelected()){
                bloqueado = 1;
            }
            
            usuario.setBloqueado(bloqueado);
            colaborador.setId(this.getUsuarioAction().getIdColaborador((String)cmbColaborador.getSelectedItem()));
            colaborador.setNome((String)cmbColaborador.getSelectedItem());
            usuario.setColaborador(colaborador);

            /*Valida se os campos da tela foram preenchidos e grava as informaçoes*/
            if(this.validaCamposUsuario() && this.getUsuarioAction().isLoginExistente(usuario)){
                this.getUsuarioAction().alterar(usuario);
                javax.swing.JOptionPane.showMessageDialog(this, "Usuário alterado com sucesso!","Aviso",1);
                this.limparCamposTelaUsuario();
                getUsuarioAction().populaTabelaUsuarios();
                tabelaUsuarios.setModel(getUsuarioAction().getUsuarioTableModel());
            }
        } catch (UsuarioException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
        }

    }//GEN-LAST:event_btnAlterarUsuarioActionPerformed

    private void btnExcluirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirUsuarioActionPerformed
                   // TODO add your handling code here:    
        
        Usuario usuarioSelecionado = this.getUsuarioAction().getUsuarioTableModel().getLinhas().get(tabelaUsuarios.getSelectedRow());
        
        try {
            if(usuarioSelecionado != null){
                usuarioSelecionado = this.usuarioAction.getUsuario(usuarioSelecionado);  
                this.getUsuarioAction().deletar(usuarioSelecionado);
                javax.swing.JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!","Aviso",1);
                this.limparCamposTelaUsuario();
                getUsuarioAction().populaTabelaUsuarios();            
                tabelaUsuarios.setModel(getUsuarioAction().getUsuarioTableModel());
            }else{
                javax.swing.JOptionPane.showMessageDialog(this, "Você deve selecionar o usuário que deseja remover!","Atenção - Aviso",0);
                this.cmbColaborador.setEnabled(false);
            }                 
        } catch (UsuarioException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
        }         
    }//GEN-LAST:event_btnExcluirUsuarioActionPerformed



    private void carregaGrupoUsuarioTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregaGrupoUsuarioTab
        // TODO add your handling code here:

          //Monta o grid contendo os dados do usuario
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        setGrupoUsuarioAction(new GrupoUsuarioAction());

        //Monta tabela de usuarios no grid
//        getGrupoUsuarioAction().populaTabelaGrupoUsuarios();
//        tabelaGrupoUsuarios.setModel(getGrupoUsuarioAction().getGrupoUsuarioTableModel());
//        tabelaGrupoUsuarios.getColumnModel().getColumn(0).setCellRenderer(renderer);
//        tabelaGrupoUsuarios.getColumnModel().getColumn(1).setCellRenderer(renderer);
//        tabelaGrupoUsuarios.getColumnModel().getColumn(0).setMaxWidth(40);
//        tabelaGrupoUsuarios.getColumnModel().getColumn(1).setMaxWidth(300);


    }//GEN-LAST:event_carregaGrupoUsuarioTab

    //Usado para carregar os dados apos a busca do cliente Condominio
    //FrmBuscaCliente invoca esse metodo
    public void popularClienteCondominio(ClienteCondominio clienteCondominio){
        this.clienteCondominio = clienteCondominio;
        txtCodigoCliente.setText(String.valueOf(clienteCondominio.getIdCliente()));
        txtNome.setText(clienteCondominio.getNome());
        txtCnpj.setText(clienteCondominio.getCnpj());
        txtIe.setText(clienteCondominio.getIe());
        txtTelComercial.setText(clienteCondominio.getTelefoneComercial());
        txtTelFax.setText(clienteCondominio.getTelefoneFax());
        txtEndereco.setText(clienteCondominio.getEndereco());
        txtBairro.setText(clienteCondominio.getBairro());
        txtCidade.setText(clienteCondominio.getCidade());
        txtCep.setText(clienteCondominio.getCep());
        cmbUf.setSelectedItem(clienteCondominio.getUf());
        txtBlocos.setText(String.valueOf(clienteCondominio.getNumeroBlocos()));
        txtAndares.setText(String.valueOf(clienteCondominio.getNumeroAndares()));
        txtApAndar.setText(String.valueOf(clienteCondominio.getNumeroApAndar()));
        txtQuartos.setText(String.valueOf(clienteCondominio.getNumeroQuartos()));
        txtNomeSindico.setText(clienteCondominio.getSindico());
        txtTelSindico.setText(clienteCondominio.getTelefoneSindico());
        txtNomeZelador.setText(clienteCondominio.getZelador());
        txtTelZelador.setText(clienteCondominio.getTelefoneZelador());
        txtObs.setText(clienteCondominio.getObs());
        txtEndereco.setText(clienteCondominio.getEndereco());
        txtBuscaCondominio.setText("");
        txtEmailCondominio.setText(clienteCondominio.getEmail());
    }

    //Usado para carregar os dados apos a busca do cliente Empresa
    //FrmBuscaCliente invoca esse metodo
    public void popularClientePessoaJuridica(ClientePessoaJuridica clientePessoaJuridica){
        this.clientePessoaJuridica = clientePessoaJuridica;
        txtCodigoEmpresa.setText(String.valueOf(clientePessoaJuridica.getIdCliente()));
        txtNomeEmpresa.setText(clientePessoaJuridica.getNome());
        txtCnpjEmpresa.setText(clientePessoaJuridica.getCnpj());
        txtIeEmpresa.setText(clientePessoaJuridica.getIe());
        txtTelComercialEmpresa.setText(clientePessoaJuridica.getTelefoneComercial());
        txtTelFaxEmpresa.setText(clientePessoaJuridica.getTelefoneFax());
        txtEnderecoEmpresa.setText(clientePessoaJuridica.getEndereco());
        txtBairroEmpresa.setText(clientePessoaJuridica.getBairro());
        txtCidadeEmpresa.setText(clientePessoaJuridica.getCidade());
        txtCepEmpresa.setText(clientePessoaJuridica.getCep());
        cmbUfEmpresa.setSelectedItem(clientePessoaJuridica.getUf());
        txtObsEmpresa.setText(clientePessoaJuridica.getObs());
        txtEnderecoEmpresa.setText(clientePessoaJuridica.getEndereco());
        txtBuscaEmpresa.setText("");
        txtNomeResponsavelEmpresa.setText(clientePessoaJuridica.getNomeResponsavel());
        txtTelResponsavelEmpresa.setText(clientePessoaJuridica.getTelefoneResponsavel());
        txtEmailEmpresa.setText(clientePessoaJuridica.getEmail());

    }

    //Usado para carregar os dados apos a busca do cliente Pessoa
    //FrmBuscaCliente invoca esse metodo
    public void popularClientePessoa(ClientePessoaFisica cp){
        this.setClientePessoa(cp);
        txtCodigoPessoa.setText(String.valueOf(cp.getIdCliente()));
        txtNomePessoa.setText(cp.getNome());
        txtCpfPessoa.setText(cp.getCpf());
        txtRgPessoa.setText(cp.getRg());
        txtTelResidencialPessoa.setText(cp.getTelefoneResidencial());
        txtTelCelularPessoa.setText(cp.getTelefoneCelular());
        txtEnderecoPessoa.setText(cp.getEndereco());
        txtBairroPessoa.setText(cp.getBairro());
        txtCidadePessoa.setText(cp.getCidade());
        txtCepPessoa.setText(cp.getCep());
        cmbUfPessoa.setSelectedItem(cp.getUf());
        txtObsPessoa.setText(cp.getObs());
        txtEnderecoPessoa.setText(cp.getEndereco());
        txtBuscaPessoa.setText("");
        txtContatoPessoa.setText(cp.getContato());
        txtTelContatoPessoa.setText(cp.getTelefoneContato());
        txtEmailPessoa.setText(cp.getEmail());
    }
    
       

public void popularClientePedido(ClientePessoa cliente){

        //Enriquece os dados de endereço do pedido, pois pode ser um
        //endereço diferente do endereço de cadastro do cliente
        //this.getPedidoAction().enriquecerEnderecoPedido(cliente);
        txtNomeClientePedido.setText(cliente.getNome());
//        txtEnderecoPedido.setText(cliente.getEndereco());
//        txtBairroPedido.setText(cliente.getBairro());
//        txtCidadePedido.setText(cliente.getCidade());
//        txtContatoPedido.setText(cliente.getNomeContato());
//        txtTelefoneContatoPedido.setText(cliente.getTelefoneContato());

//        if(cliente instanceof ClientePessoaFisica){
//            ClientePessoaFisica clientePessoaFisica = (ClientePessoaFisica) cliente;
//            
////            if(clientePessoaFisica.getTelefoneResidencial()!= null){
////                txtTelefoneClientePedido.setText(clientePessoaFisica.getTelefoneResidencial());
////            }else if(clientePessoaFisica.getTelefoneCelular()!= null){
////                txtTelefoneClientePedido.setText(clientePessoaFisica.getTelefoneCelular());
////            }
//        }else if(cliente instanceof ClientePessoaJuridica){
//            ClientePessoaJuridica clientePJ = (ClientePessoaJuridica) cliente;
//            
//            if(clientePJ.getTelefoneComercial() != null){
//                txtTelefoneClientePedido.setText(clientePJ.getTelefoneComercial());
//            }else if(clientePJ.getTelefoneResponsavel() != null){
//                txtTelefoneClientePedido.setText(clientePJ.getTelefoneResponsavel());
//            }else if(clientePJ.getTelefoneFax() != null){
//                txtTelefoneClientePedido.setText(clientePJ.getTelefoneFax());
//            }
//        }
//            
//        if(cliente.getTelefoneContato()!= null){
//            txtTelefoneContatoPedido.setText(cliente.getTelefoneContato());
//        }
//        
//        cmbUfPedido.setSelectedItem(cliente.getUf());
       
}
    

        /*Configura numero de parcelas*/

ChangeListener listener2 = new ChangeListener() {

        @Override
          public void stateChanged(ChangeEvent e) {
            gerenciaPrazoContrato();
          }
  };

  public void gerenciaPrazoContrato(){
            //Instancia variáveis
            Calendar calendar = Calendar.getInstance();
            //Atribui a data do dia
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
            if((Integer)cmdPeriodoContrato.getValue()>0){
                Integer i = (Integer) cmdPeriodoContrato.getValue();
                calendar.add(Calendar.MONTH,i);
                txtDataExpiracao.setText(format.format(calendar.getTime()));
            }else{
                javax.swing.JOptionPane.showMessageDialog(this, "O valor do prazo não pode ser menor do que 0.","Atenção - Aviso",0);
            }

  }


     public void popularClienteContrato(ClientePessoaFisica c){

        this.getContratoAction().getContrato().setIdContrato("C"+c.getIdCliente());
        
        txtCodigoContrato.setText("C"+c.getIdCliente());
        txtDataInicioContrato.setText(new Util().getDataHoje());
        txtNomeCC.setText(c.getNome());
        txtEnderecoCC.setText(c.getEndereco());
        txtBairroCC.setText(c.getBairro());
        txtCidadeCC.setText(c.getCidade());
        txtContatoCC.setText(c.getContato());
        txtTelefoneContatoCC.setText(c.getTelefoneContato());
        cmbUfCC.setSelectedItem(c.getUf());
    }

    private void btnNovoGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoGrupoActionPerformed
            // TODO add your handling code here:
        txtGrupoUsuario.setText("");

    }//GEN-LAST:event_btnNovoGrupoActionPerformed

    private void tabelaGrupoUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaGrupoUsuariosMouseClicked
        // TODO add your handling code here:
        //this.setGrupoUsuarioTela(this.getGrupoUsuarioAction().getGrupoUsuarioTableModel().getLinhas().get(tabelaGrupoUsuarios.getSelectedRow()));
        txtGrupoUsuario.setText(this.getGrupoUsuarioTela().getDescricao());

    }//GEN-LAST:event_tabelaGrupoUsuariosMouseClicked

    private void btnIncluirGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirGrupoActionPerformed
        // TODO add your handling code here:

        GrupoUsuario gu = new GrupoUsuario();
        gu.setDescricao(txtGrupoUsuario.getText());       

        /*Valida se ja existe um Grupo de usuario com o mesmo nome*/
//        if(this.getGrupoUsuarioAction().isGrupoUsuarioCadastrado(gu)){
//            javax.swing.JOptionPane.showMessageDialog(this, "Já existe um Grupo cadastrado com o mesmo nome!","Atenção - Aviso",0);
//        }else if(this.validaCamposGrupoUsuario()){
//            this.getGrupoUsuarioAction().salvar(gu);
//            javax.swing.JOptionPane.showMessageDialog(this, "Grupo de Usuários cadastrado com sucesso!","Aviso",1);
//            this.limparCamposTelaGrupoUsuario();
//            getGrupoUsuarioAction().populaTabelaGrupoUsuarios();
//            tabelaGrupoUsuarios.setModel(getGrupoUsuarioAction().getGrupoUsuarioTableModel());            
//        }
    }//GEN-LAST:event_btnIncluirGrupoActionPerformed

    private void btnAlterarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarGrupoActionPerformed
        // TODO add your handling code here:
//        GrupoUsuario gu = new GrupoUsuario();
//        if(this.grupoUsuarioTela != null){
//            gu.setDescricao(this.txtGrupoUsuario.getText());
//            gu.setId(this.grupoUsuarioTela.getId());
//        }
//
//        /*Valida se os campos da tela foram preenchidos e grava as informaçoes*/
//        if(this.validaCamposGrupoUsuario() && !this.getGrupoUsuarioAction().isGrupoUsuarioCadastrado(gu)){
//            this.getGrupoUsuarioAction().atualizar(gu);
//            javax.swing.JOptionPane.showMessageDialog(this, "Grupo alterado com sucesso!","Aviso",1);
//            this.limparCamposTelaGrupoUsuario();
//            getGrupoUsuarioAction().populaTabelaGrupoUsuarios();
//            tabelaGrupoUsuarios.setModel(getGrupoUsuarioAction().getGrupoUsuarioTableModel());
//        }else if(this.getGrupoUsuarioAction().isGrupoUsuarioCadastrado(gu)){
//                javax.swing.JOptionPane.showMessageDialog(this, "Já existe um Grupo cadastrado com o mesmo nome!","Atenção - Aviso",0);
//
//        }

    }//GEN-LAST:event_btnAlterarGrupoActionPerformed

    private void btnExcluirGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirGrupoActionPerformed
        // TODO add your handling code here:

//         if(txtGrupoUsuario.getText().equals("")){
//            javax.swing.JOptionPane.showMessageDialog(this, "Você deve selecionar o grupo que deseja remover!","Atenção - Aviso",0);
//        }else{
//            this.getGrupoUsuarioAction().remover(grupoUsuarioTela);
//            javax.swing.JOptionPane.showMessageDialog(this, "Grupo removido com sucesso!","Aviso",1);
//            this.limparCamposTelaGrupoUsuario();
//            this.grupoUsuarioAction.populaTabelaGrupoUsuarios();
//            tabelaGrupoUsuarios.setModel(getGrupoUsuarioAction().getGrupoUsuarioTableModel());           
//        }
    }//GEN-LAST:event_btnExcluirGrupoActionPerformed

    private void carregaUsuarioCadaGrupoTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregaUsuarioCadaGrupoTab

//        this.usuarioCadaGrupoAction = new UsuarioCadaGrupoAction();
//        
//         /* Carrega Combo de Usuarios */
//        this.carregarComboUsuarioCadaGrupo();       
//
//        //Monta o grid contendo os dados do usuario
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//        renderer.setHorizontalAlignment(SwingConstants.LEFT);
//        
//        Usuario usuario = this.getUsuarioCadaGrupoAction().getUsuario((String)cmbUsuarioColaborador.getItemAt(0));
//
//        //Monta tabela de grupos do usuario selecionado no grid
//        this.getUsuarioCadaGrupoAction().populaTabelaUsuarioCadaGrupo(usuario);
//        this.tabelaUsuariosCadaGrupo.setModel(this.getUsuarioCadaGrupoAction().getUcgTableModel());
//        this.tabelaUsuariosCadaGrupo.getColumnModel().getColumn(0).setCellRenderer(renderer);
//        this.tabelaUsuariosCadaGrupo.getColumnModel().getColumn(1).setCellRenderer(renderer);
//        this.tabelaUsuariosCadaGrupo.getColumnModel().getColumn(0).setMaxWidth(40);
//        this.tabelaUsuariosCadaGrupo.getColumnModel().getColumn(1).setMaxWidth(200);
           
    }//GEN-LAST:event_carregaUsuarioCadaGrupoTab

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void btnCondominioNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCondominioNovoActionPerformed
        new ClienteForm().limparCamposTelaClienteCondominio();
    }//GEN-LAST:event_btnCondominioNovoActionPerformed

    private void limparCamposTelaClienteEmpresa(){
        txtCodigoEmpresa.setText("");
        txtNomeEmpresa.setText("");
        txtCnpjEmpresa.setText("");
        txtIeEmpresa.setText("");
        txtTelComercial.setText("");
        txtTelFaxEmpresa.setText("");
        txtEnderecoEmpresa.setText("");
        txtBairroEmpresa.setText("");
        txtCidadeEmpresa.setText("");
        txtCepEmpresa.setText("");
        txtNomeResponsavelEmpresa.setText("");
        txtTelResponsavelEmpresa.setText("");
        txtObsEmpresa.setText("");
        txtEmailEmpresa.setText("");
    }

    private void limparCamposTelaClientePessoa(){
        txtCodigoPessoa.setText("");
        txtNomePessoa.setText("");
        txtCpfPessoa.setText("");
        txtRgPessoa.setText("");
        txtTelResidencialPessoa.setText("");
        txtTelCelularPessoa.setText("");
        txtEnderecoPessoa.setText("");
        txtBairroPessoa.setText("");
        txtCidadePessoa.setText("");
        txtCepPessoa.setText("");
        txtContatoPessoa.setText("");
        txtTelContatoPessoa.setText("");
        txtObsPessoa.setText("");
        txtEmailPessoa.setText("");
    }



    private void btnIncluirCondominioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirCondominioActionPerformed

        new ClienteForm().salvarClienteCondominio(this);       
        
    }//GEN-LAST:event_btnIncluirCondominioActionPerformed

    

    private boolean isCamposClienteEmpresaValidos(){

        boolean retorno = true;

        if(txtNomeEmpresa.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão do cliente. O Campo NOME deve ser preenchido","Atenção - Aviso",0);
            retorno = false;
        }else if(txtEnderecoEmpresa.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão do cliente. O Campo ENDEREÇO deve ser preenchido","Atenção - Aviso",0);
            retorno = false;
        }else if(txtBairroEmpresa.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão do cliente. O Campo BAIRRO deve ser preenchido","Atenção - Aviso",0);
            retorno = false;
        }else if(txtTelComercialEmpresa.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão do cliente. O Campo TELEFONE COMERCIAL deve ser preenchido","Atenção - Aviso",0);
            retorno = false;
        }else if(txtTelFaxEmpresa.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão do cliente. O Campo TELEFONE FAX deve ser preenchido","Atenção - Aviso",0);
            retorno = false;
        }

        return retorno;
    }

    private boolean isCamposClientePessoaValidos(){

        boolean retorno = true;

        if(txtNomePessoa.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão do cliente. O Campo NOME deve ser preenchido","Atenção - Aviso",0);
            retorno = false;
        }else if(txtEnderecoPessoa.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão do cliente. O Campo ENDEREÇO deve ser preenchido","Atenção - Aviso",0);
            retorno = false;
        }else if(txtBairroPessoa.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão do cliente. O Campo BAIRRO deve ser preenchido","Atenção - Aviso",0);
            retorno = false;
        }else if(txtTelResidencialPessoa.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão do cliente. O Campo TELEFONE RESIDENCIAL deve ser preenchido","Atenção - Aviso",0);
            retorno = false;
        }else if(txtTelCelularPessoa.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão do cliente. O Campo TELEFONE CELULAR deve ser preenchido","Atenção - Aviso",0);
            retorno = false;
        }

        return retorno;
    }

    private void btnAlterarClienteCondominioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarClienteCondominioActionPerformed

       new ClienteForm().atualizarClienteCondominio(this);
       
    }//GEN-LAST:event_btnAlterarClienteCondominioActionPerformed

    private void btnExcluirCondominioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCondominioActionPerformed
        // TODO add your handling code here:
        new ClienteForm().removerClienteCondominio(this);
    }//GEN-LAST:event_btnExcluirCondominioActionPerformed

    private void carregaClienteCondominioTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregaClienteCondominioTab
        this.clienteCondominioAction = new ClienteCondominioAction();
        this.clienteCondominio = new ClienteCondominio();
        new ClienteForm().limparCamposTelaClienteCondominio();
    }//GEN-LAST:event_carregaClienteCondominioTab

    /*
     * Author: Diego Lemos
     * Descriçao: Busca os clientes do tipo condominio atraves do botao "consultar"
     */

    private void buscarCondominios(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarCondominios
        new ClienteForm().buscarClienteCondominio(this);       
    }//GEN-LAST:event_buscarCondominios


    /*
     * Author: Diego Lemos
     * Descriçao: Busca os clientes do tipo condominio atraves da tecla "Enter"
     */
    private void buscarCondominioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarCondominioKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            new ClienteForm().buscarClienteCondominio(this); 
        }

    }//GEN-LAST:event_buscarCondominioKeyPressed

    private void txtEnderecoPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoPessoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoPessoaActionPerformed

    private void btnPessoaNovaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPessoaNovaActionPerformed

        
        txtCodigoPessoa.setText("");
        txtNomePessoa.setText("");
        txtCpfPessoa.setText("");
        txtRgPessoa.setText("");
        txtTelCelularPessoa.setText("");
        txtTelResidencialPessoa.setText("");
        txtEnderecoPessoa.setText("");
        txtBairroPessoa.setText("");
        txtCidadePessoa.setText("");
        txtCepPessoa.setText("");
        txtContatoPessoa.setText("");
        txtTelContatoPessoa.setText("");
        txtObsPessoa.setText("");
        txtEmailPessoa.setText("");


        // TODO add your handling code here:
    }//GEN-LAST:event_btnPessoaNovaActionPerformed

    private void btnIncluirPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirPessoaActionPerformed
        
        new ClienteForm().salvarClientePessoaFisica(this);  
    }//GEN-LAST:event_btnIncluirPessoaActionPerformed

    private void btnAlterarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarPessoaActionPerformed

         new ClienteForm().atualizarClientePessoaFisica(this);
    }//GEN-LAST:event_btnAlterarPessoaActionPerformed

    private void btnExcluirPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirPessoaActionPerformed
         
        new ClienteForm().removerClientePessoaFisica(this);
        
    }//GEN-LAST:event_btnExcluirPessoaActionPerformed

    private void buscarClientePessoaFisicaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarClientePessoaFisicaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            new ClienteForm().buscarClientePessoaFisica(this);
        }
    }//GEN-LAST:event_buscarClientePessoaFisicaKeyPressed
 


    private void btnBuscarClientePessoaFisica(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClientePessoaFisica
        new ClienteForm().buscarClientePessoaFisica(this);
    }//GEN-LAST:event_btnBuscarClientePessoaFisica

    private void jPanel16carregaClienteCondominioTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel16carregaClienteCondominioTab
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel16carregaClienteCondominioTab

    private void carregaClienteEmpresaTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregaClienteEmpresaTab
        this.clienteEmpresaAction = new ClienteEmpresaAction();
        this.clientePessoaJuridica = new ClientePessoaJuridica();
        this.limparCamposTelaClienteEmpresa();        
    }//GEN-LAST:event_carregaClienteEmpresaTab

    private void carregarClientePessoaTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregarClientePessoaTab

        this.clientePessoaFisicaAction = new ClientePessoaFisicaAction();
        this.setClientePessoa(new ClientePessoaFisica());
        this.limparCamposTelaClientePessoa();
        // TODO add your handling code here:
    }//GEN-LAST:event_carregarClientePessoaTab

    private void btnNovoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoPedidoActionPerformed

        try {            
            //Limpa e Habilita os campos
            new PedidoForm().preparaCamposNovoPedido();
            //Instancia Pedido
            
            //this.getPedidoAction().getPedido().setIdPedido(numeroPedido);

            //long numeroPedido = this.getPedidoAction().getNumeroPedido();
            //txtNumeroPedido.setText(Long.toString(numeroPedido));
            this.getPedidoAction().setPedido(new Pedido());
            //this.getPedidoAction().getPedido().setIdPedido(numeroPedido);
            new ClienteForm().buscarClientePedido(this);           
                        
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this,"Falha na criação do novo Pedido. O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNovoPedidoActionPerformed

    private void btnIncluirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirPedidoActionPerformed


        new PedidoForm().incluirPedido();

//        try {
//            Pedido p = this.getPedidoAction().getPedido();
//            double totalPedido = new Util().converteValorTextoDouble(txtTotalPedido.getText());
//            p.setStatus((String) cmbStatusPedido.getSelectedItem());
//
//            if(new PedidoForm().isFormularioPedidoValido(p)){
//
//                    p.setDataAgendamento(String.valueOf(format.format(txtDataAgendamento.getValue())));
//                    p.setObservacao(txtObsPedido.getText());
//
//                    if(this.getPedidoAction().getPedido().getFormaPagamento() != null){
//                        this.getPedidoAction().getPedido().getFormaPagamento().setValorTotal(totalPedido);
//                    }
//
//                    if(this.getPedidoAction().getFormaPagamento()== null){
//                             int r = javax.swing.JOptionPane.showConfirmDialog(this,"A forma de pagamento não foi definida. O pedido será gravado como pagamento à vista. Confirma a operação ?","Atenção", 0);
//                                if(r == 0){
//                                    // Grava os dados do pedido na base
//                                    this.getPedidoAction().salvar(this.getPedidoAction().getPedido());
//                                    javax.swing.JOptionPane.showMessageDialog(this,"Pedido incluído com sucesso!","Aviso",1);
//                                    new PedidoForm().aplicarEstadoCommandos("salvar");
//                                }
//                    }else if(!this.getPedidoAction().getFormaPagamento().getParcelas().isEmpty()){
//                            this.getPedidoAction().salvar(this.getPedidoAction().getPedido());
//                            javax.swing.JOptionPane.showMessageDialog(this,"Pedido incluído com sucesso!","Aviso",1);
//                            new PedidoForm().aplicarEstadoCommandos("salvar");
//                    }
//              }
//        } catch (Exception ex) {
//            if(ex.getMessage().indexOf("Duplicate")!=-1){
//                javax.swing.JOptionPane.showMessageDialog(this,"Falha na Operação. Pedido já cadastrado na base de dados","Atenção",2);
//            }else{
//                javax.swing.JOptionPane.showMessageDialog(this,"Falha na Operação. O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
//            }
//            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnIncluirPedidoActionPerformed

    private void btnAlterarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarPedidoActionPerformed

        Pedido p = this.getPedidoAction().getPedido();

        new PedidoForm().alterarPedido(p);

        txtCodigoBuscaPedido.setText(String.valueOf(p.getIdPedido()));
        btnConsultaPedidoActionPerformed(null);
    }//GEN-LAST:event_btnAlterarPedidoActionPerformed



    private void btnExcluirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirPedidoActionPerformed
        try {            
            int r = JOptionPane.showConfirmDialog(this,"Esse procedimento irá remover todas as informacões do  pedido. Confirma a remoção do pedido ?","Atenção", 0);
            if(r == 0){
                // Remove os dados do pedido na base
                this.getPedidoAction().remover(this.getPedidoAction().getPedido());
                JOptionPane.showMessageDialog(this,"Pedido removido com sucesso!","Aviso",1);
                new PedidoForm().preparaCamposNovoPedido();
            }
        } catch (PedidoException ex) {
            JOptionPane.showMessageDialog(this,ex.getMensagemErro(),"Atenção",2);
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnExcluirPedidoActionPerformed

    /*Metodo Responsavel por carregar aba Pedido*/
    private void carregaPedidoTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregaPedidoTab

        if(this.getPedidoAction()==null || this.getPedidoAction().getPedido()==null){
            this.setServicoAction(new ServicoAction());
            this.setPedidoAction(new PedidoAction());
            //Carrega Combo de UF
            //new Util().carregaComboUf(cmbUfPedido);

            try{
                getPedidoAction().popularListaServicos(tableServicosDisponiveisPedido);
                getPedidoAction().popularServicosPedido(tableServicosPedido);
                txtTotalPedido.setText(new Util().formataMoeda(0.0));
            }catch (PedidoException ex) {
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
            } catch (Exception ex) {
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                javax.swing.JOptionPane.showMessageDialog(this, "Atenção - Falha ao listar serviços disponiveis. O seguinte erro ocorreu : "+ ex.getMessage(),"Atenção - Erro",0);
            }
        }
    }//GEN-LAST:event_carregaPedidoTab

  
    private void btnAddItemServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemServicoActionPerformed

        Double v,valorConvertido;
        String valorFormatado;

        //Instancia variáveis
            
        ItemPedido itemPedido = new ItemPedido();

        if(tableServicosDisponiveisPedido.getSelectedRowCount()!=0){
            //Extrai o objeto Serviço selecionado
            Servico s = (((ServicoDisponivelTableModel)tableServicosDisponiveisPedido.getModel()).getServico(tableServicosDisponiveisPedido.getSelectedRow()));
            //Instacia um novo Objeto Servico que seráq vinculado ao Pedido
            Servico sPedido = new Servico();
            sPedido.setDescricao(s.getDescricao());
            sPedido.setIdServico(s.getIdServico());
            sPedido.setValor(s.getValor());            
            sPedido.setGarantia(s.getGarantia());
            itemPedido.setServico(sPedido);
            //itemPedido.setDataVencimentoGarantia(format.format(calendar.getTime()));
            itemPedido.setValor(sPedido.getValor());
           try {
               /*Inclui o serviço selecionado na tabela de Serviços do Pedido e inclui
               o mesmo na lista de serviços do objeto Pedido*/
               this.getPedidoAction().addItemPedido(tableServicosDisponiveisPedido, itemPedido);

               //Incrementa valor no total do pedido - INICIO
                valorConvertido = new Util().converteValorTextoDouble(txtTotalPedido.getText());
                v = getPedidoAction().adicionaValor(valorConvertido, sPedido.getValor());
                this.getPedidoAction().getPedido().setValorPedido(v);
                valorFormatado = new Util().formataMoeda(v);                
                txtTotalPedido.setText(valorFormatado);
                //Incrementa valor no total do pedido - FIM
            } catch (Exception ex) {
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }//GEN-LAST:event_btnAddItemServicoActionPerformed

    
    private void btnRemoveItemServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveItemServicoActionPerformed
        /**Remove os itens da lista de servidços do pedido*/
        ArrayList listaItensPedido = (ArrayList) ((ServicoPedidoTableModel)tableServicosPedido.getModel()).getLinhas();
        int[] linhasSelecionadas;
        double valorServicoSelecionado;
        Double v,valorConvertido;
        String valorFormatado;
        
        if(!listaItensPedido.isEmpty()){
            linhasSelecionadas = tableServicosPedido.getSelectedRows();
            if(linhasSelecionadas.length > 1){
                    javax.swing.JOptionPane.showMessageDialog(this, "Existe mais de uma linha selecionada. Só é possível remover 1 item por vez.","Atenção",0);
            }else if(linhasSelecionadas.length == 0){
                    javax.swing.JOptionPane.showMessageDialog(this, "Selecione um item para ser removido.","Atenção",0);
            }else{
            
                valorServicoSelecionado = (Double)tableServicosPedido.getValueAt(tableServicosPedido.getSelectedRow(), 1);               
                
                try {
                    this.getPedidoAction().removeItemPedido((ItemPedido) listaItensPedido.get(linhasSelecionadas[0]),linhasSelecionadas[0]);
                    valorConvertido = new Util().converteValorTextoDouble(txtTotalPedido.getText());
                    v =  this.getPedidoAction().removeValor(valorConvertido, valorServicoSelecionado);
                    valorFormatado = new Util().formataMoeda(v);
                    txtTotalPedido.setText(valorFormatado);
                } catch (Exception ex) {
                    Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnRemoveItemServicoActionPerformed

    private void informaNovoValorServicoPedido(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_informaNovoValorServicoPedido

        /**Metodo responsavel por lancar um novo valor para o serviço adicionado ao pedido em caso de
         desconto ou acréscimo no valor*/

        if(tableServicosPedido.getRowCount()!= 0){
            if(evt.getClickCount()==2){
                try{
                    Double valorServicoOriginal = (Double)tableServicosPedido.getValueAt(tableServicosPedido.getSelectedRow(), 1);
                    String v = javax.swing.JOptionPane.showInputDialog(this,"Informe o novo valor do servico para este pedido:","Aviso - Comunicado",2);

                    if(v!=null || !v.equals("")){
                        Double valor = Double.parseDouble(v);
                        tableServicosPedido.setValueAt(valor, tableServicosPedido.getSelectedRow(), 1);
                        Double totalPedido = new Util().converteValorTextoDouble(txtTotalPedido.getText());
                        totalPedido = totalPedido - valorServicoOriginal;
                        totalPedido = totalPedido + valor;
                        txtTotalPedido.setText(new Util().formataMoeda(totalPedido));
                    }
                }catch(Exception e){
                    javax.swing.JOptionPane.showMessageDialog(this, "O valor informado é inválido","Atenção",2);
                }
            }
        }
    }//GEN-LAST:event_informaNovoValorServicoPedido

    private void btnFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormaPagamentoActionPerformed
        try {
            this.getPedidoAction().getPedido().setValorPedido(new Util().converteValorTextoDouble(txtTotalPedido.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        FrmPagamentoPedido frmPagamento = new FrmPagamentoPedido(this.getPedidoAction().getPedido());
        frmPagamento.setAlwaysOnTop(true);
        frmPagamento.setDefaultCloseOperation(FrmPagamentoPedido.DISPOSE_ON_CLOSE);
        frmPagamento.setVisible(true);
    }//GEN-LAST:event_btnFormaPagamentoActionPerformed

    private void btnConsultaPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaPedidoActionPerformed

        new PedidoForm().consultarPedido(this);

    }//GEN-LAST:event_btnConsultaPedidoActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
//          BuscaPessoaAction buscaPessoaAction = new BuscaPessoaAction();
//
//        FrmBuscaCliente frmBC = null;
//        try{
//                //Busca por nome
//                ArrayList lista = (ArrayList) buscaPessoaAction.getListaClienteGenericoPorNome(txtNomeCC.getText());
//                frmBC = new FrmBuscaCliente(lista,this,"Contrato");
//                frmBC.setResizable(false);
//                frmBC.setAlwaysOnTop(true);
//                frmBC.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//                frmBC.setLocationRelativeTo(this);
//                frmBC.setVisible(true);
//        }catch(Exception e){
//            javax.swing.JOptionPane.showMessageDialog(this, "Falha na Consulta - Verifique se o campo de busca foi preenchido corretamente! ","Atenção - Aviso",0);
//        }
    }//GEN-LAST:event_jButton6ActionPerformed


    public String getTxtNomeClienteContrato(){
        return txtNomeCC.getText();
    }

    private void carregarContratoTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregarContratoTab

        

        //Metodo acionado ao entrar na aba de Controle de Contratos
        cmdPeriodoContrato.addChangeListener(listener2);
        
        try {
            new Util().carregaComboUf(cmbUfCC);
            this.contratoAction = new ContratoAction();
            this.getContratoAction().popularServicosContrato(tableServicosContrato);
            this.limparCamposTelaContrato();

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Falha no carregamento - O seguinte erro ocorreu: " + ex.getMessage(),"Atenção - Aviso",0);
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_carregarContratoTab

    private void btnConsultaContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaContratoActionPerformed
       
        this.carregarContratoTab(null);
        // TODO add your handling code here:
        

        FrmBuscaContrato frmBC = null;

        if(cmbBuscaContrato.getSelectedItem().equals("Código")){
            //Obtem o codigo do contrato para busca
            String codigoContrato = txtCodigoBuscaContrato.getText();
            try {
                this.getContratoAction().setValorCampoBusca(codigoContrato);
                this.getContratoAction().setContrato((Contrato) this.getContratoAction().getContratoPorId(codigoContrato));

                if(this.getContratoAction().getContrato() == null){
                    javax.swing.JOptionPane.showMessageDialog(this, "Código do contrato não encontrado!","Atenção",1);
                }else{
                    this.populaContratoConsultado(this.getContratoAction().getContrato());
                    btnIncluirContrato.setEnabled(false);
                    btnAlterarContrato.setEnabled(true);
                    btnExcluirContrato.setEnabled(true);
                }
            } catch (Exception ex) {
                  javax.swing.JOptionPane.showMessageDialog(this, "Falha na consulta.O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else if(cmbBuscaContrato.getSelectedItem().equals("Cliente")){


            //Obtem o pedido pelo nome do cliente
            String nomeCliente = txtCodigoBuscaContrato.getText();
            try {
                this.getContratoAction().setValorCampoBusca(nomeCliente);
                ArrayList lista = (ArrayList) this.getContratoAction().getContratoPorNomeCliente(nomeCliente);
                frmBC = new FrmBuscaContrato(lista,this,getContratoAction().getContrato());
                frmBC.setResizable(false);
                frmBC.setAlwaysOnTop(true);
                frmBC.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frmBC.setLocationRelativeTo(this);
                frmBC.setVisible(true);
            } catch (Exception ex) {
                  javax.swing.JOptionPane.showMessageDialog(this, "Falha na consulta.O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnConsultaContratoActionPerformed

  

    private void btnGerenciaServicosContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciaServicosContratoActionPerformed
                FrmGerenciaServicosContrato frmGerenciaServicos = new FrmGerenciaServicosContrato(this.getContratoAction(),this.tableServicosContrato,txtValorTotalContrato);
                frmGerenciaServicos.setResizable(false);
                frmGerenciaServicos.setAlwaysOnTop(true);
                frmGerenciaServicos.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frmGerenciaServicos.setLocationRelativeTo(this);
                frmGerenciaServicos.setVisible(true);
    }//GEN-LAST:event_btnGerenciaServicosContratoActionPerformed

    private void btnNovoContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoContratoActionPerformed

        this.limparCamposTelaContrato();        

//        BuscaPessoaAction buscaPessoaAction = new BuscaPessoaAction();        
//        FrmBuscaCliente frmBC = null;
//        try{
//                //Busca por nome
//                ArrayList lista = (ArrayList) buscaPessoaAction.getListaClienteGenericoPorNome(txtNomeCC.getText());
//                frmBC = new FrmBuscaCliente(lista,this,"Contrato",this.getContratoAction().getContrato());
//                frmBC.setResizable(false);
//                frmBC.setAlwaysOnTop(true);
//                frmBC.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//                frmBC.setLocationRelativeTo(this);
//                frmBC.setVisible(true);
//        }catch(Exception e){
//            javax.swing.JOptionPane.showMessageDialog(this, "Falha na Consulta - Verifique se o campo de busca foi preenchido corretamente! ","Atenção - Aviso",0);
//        }

        btnIncluirContrato.setEnabled(true);
        btnAlterarContrato.setEnabled(true);
        btnExcluirContrato.setEnabled(true);
    }//GEN-LAST:event_btnNovoContratoActionPerformed

    /*Metodo responsavel por validar o objeto contrato*/
    public Boolean validaContrato(){
        Contrato contrato = this.contratoAction.getContrato();
        contrato.setPeriodo((Integer)cmdPeriodoContrato.getValue());

        if(contrato.getCliente() == null){
            javax.swing.JOptionPane.showMessageDialog(this, "O cliente deve ser informado.","Atenção - Aviso",0);
            return false;
        }else if(contrato.getDataExpiracao().equals("")|| contrato.getDataExpiracao() == null){
            javax.swing.JOptionPane.showMessageDialog(this, "O prazo do contrato deve ser informado.","Atenção - Aviso",0);
            return false;
        }else if(contrato.getItensContratados().isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "O contrato deve possuir pelo menos 1 serviço.","Atenção - Aviso",0);
            return false;
        }else if(contrato.getPeriodo()<=0){
            javax.swing.JOptionPane.showMessageDialog(this, "O prazo informado está incorreto.","Atenção - Aviso",0);
            return false;
        }
        return true;
    }



    private void btnIncluirContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirContratoActionPerformed
        
        this.contratoAction.getContrato().setDataExpiracao(txtDataExpiracao.getText());
        this.contratoAction.getContrato().setDataInicioContrato(new Util().getDataHoje());
        this.contratoAction.getContrato().setObservacao(this.txtObsCC.getText());

        if(this.validaContrato()){
            try {
                this.contratoAction.salvar(this.contratoAction.getContrato());
                javax.swing.JOptionPane.showMessageDialog(this,"Contrato incluído com sucesso!","Aviso",1);
                 btnIncluirContrato.setEnabled(false);
                 btnAlterarContrato.setEnabled(true);
                 btnExcluirContrato.setEnabled(true);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na inclusão.O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnIncluirContratoActionPerformed

    private void btnAlterarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarContratoActionPerformed

        this.contratoAction.getContrato().setDataExpiracao(txtDataExpiracao.getText());
        this.contratoAction.getContrato().setObservacao(this.txtObsCC.getText());
        this.contratoAction.getContrato().setPeriodo((Integer)cmdPeriodoContrato.getValue());

        if(this.validaContrato()){
            try {
                this.contratoAction.atualizar(this.contratoAction.getContrato());
                javax.swing.JOptionPane.showMessageDialog(this,"Contrato atualizado com sucesso!","Aviso",1);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na atualização. O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAlterarContratoActionPerformed

    private void btnExcluirContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirContratoActionPerformed

        if(this.validaContrato()){
            try {
                int r = javax.swing.JOptionPane.showConfirmDialog(this,"Esse procedimento irá remover as informacoes da base de dados. Tais informações nao poderão ser consultadas posteriormente. Deseja Prosseguir assim mesmo ?","Atenção", 0);
                if(r == 0){
                    this.contratoAction.remover(this.contratoAction.getContrato());
                    javax.swing.JOptionPane.showMessageDialog(this,"Contrato removido com sucesso!","Aviso",1);
                    this.limparCamposTelaContrato();
//                    btnIncluirPedido.setEnabled(false);
//                    btnAlterarPedido.setEnabled(false);
//                    btnExcluirPedido.setEnabled(false);
                }

            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na exclusão.O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnExcluirContratoActionPerformed

  
    private void btnConsultaOrdemServicoPadraoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaOrdemServicoPadraoActionPerformed

        new OsForm().limparCamposFormulario();

        OrdemServicoPadrao os;
        Pedido pedido;
        FrmBuscaOrdemServico frmBuscaOS = null;


       
             if(this.cmbBuscaOsPadrao.getSelectedItem().equals("Código OS")){
                  try
                  {
                    long codigoOS = Long.parseLong(txtCodigoBuscaOsPadrao.getText());
                    this.ordemServicoAction.consultarOsPorCodigo(codigoOS);
                        // TODO add your handling code here:
                    btnIncluirOsPadrao.setEnabled(false);
                    btnAlterarOsPadrao.setEnabled(true);
                    btnExcluirOsPadrao.setEnabled(true);
                  }catch(Exception pe)
                  {
                    javax.swing.JOptionPane.showMessageDialog(this, "Código da OS não é válido","Atenção",1);
                  }
            }else if(cmbBuscaOsPadrao.getSelectedItem().equals("Nome Cliente")){
                
                //Obtem a ordem de serviço pelo nome do cliente

                try {
                    String nomeCliente = txtCodigoBuscaOsPadrao.getText();
                    

                    if(nomeCliente != null)
                    {
                        ArrayList lista = (ArrayList) this.getOrdemServicoAction().getOsPorCliente(clientePessoaAction.buscarPorNome(nomeCliente));
                        frmBuscaOS = new FrmBuscaOrdemServico(lista,this, (OrdemServicoPadrao) this.getOrdemServicoAction().getOrdemServicoPadrao());
                        frmBuscaOS.setResizable(false);
                        frmBuscaOS.setAlwaysOnTop(true);
                        frmBuscaOS.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        frmBuscaOS.setLocationRelativeTo(this);
                        frmBuscaOS.setVisible(true);
                    }else
                    {
                         javax.swing.JOptionPane.showMessageDialog(this, "Nome do Cliente não é válido","Atenção",1);
                    }
                } catch (OrdemServicoException ex) {                   
                    javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",1);
                } catch (ClienteException ex) {
                    javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",1);
                }
            }else if(cmbBuscaOsPadrao.getSelectedItem().equals("Número Pedido"))
            {
                try
                {
                    if(txtCodigoBuscaOsPadrao.getText().equals("")){
                        javax.swing.JOptionPane.showMessageDialog(this, "Número do pedido não é válido","Atenção",1);
                    }else{
                         try {

                            Long numeroPedido = Long.parseLong(txtCodigoBuscaOsPadrao.getText());              
                            

                            if(numeroPedido != 0)
                            {
                                ArrayList lista = (ArrayList) this.getOrdemServicoAction().consultarOsPorPedido(pedidoAction.buscarPorId(numeroPedido));
                                frmBuscaOS = new FrmBuscaOrdemServico(lista,this, (OrdemServicoPadrao) this.getOrdemServicoAction().getOrdemServicoPadrao());
                                frmBuscaOS.setResizable(false);
                                frmBuscaOS.setAlwaysOnTop(true);
                                frmBuscaOS.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                                frmBuscaOS.setLocationRelativeTo(this);
                                frmBuscaOS.setVisible(true);
                            }else
                            {
                                 javax.swing.JOptionPane.showMessageDialog(this, "Numero do Pedido não é válido","Atenção",1);
                            }
                        } catch (OrdemServicoException ex) {
                          javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro() ,"Atenção - Erro",1);
                        }                       
                    }
                }catch(Exception e)
                {
                    Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        
        
       



/*
        if(cmbBuscaOsPadrao.getSelectedItem().equals("Código OS")){
            //Obtem o codigo do pedido para busca
            long codigoOS = Long.parseLong(txtCodigoBuscaOsPadrao.getText());
            try {
                this.getOrdemServicoAction().setOrdemServicoPadrao((OrdemServicoPadrao) this.getOrdemServicoAction().getOsPorId(codigoOS));

                if(this.getOrdemServicoAction().getOrdemServicoPadrao() == null){
                    javax.swing.JOptionPane.showMessageDialog(this, "Número da ordem de serviço não encontrado!","Atenção",1);
                }else{
                    this.populaCamposTelaOrdemServicoPadrao((OrdemServicoPadrao) this.getOrdemServicoAction().getOrdemServicoPadrao());
                }
            } catch (Exception ex) {
                  javax.swing.JOptionPane.showMessageDialog(this, "Falha na consulta.O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(cmbBuscaOsPadrao.getSelectedItem().equals("Nome ClientePessoa")){

            //Obtem o pedido pelo nome do cliente
            String nomeCliente = txtCodigoBuscaOsPadrao.getText();
            try {
                ArrayList lista = (ArrayList) this.getOrdemServicoAction().getOsPorCliente(nomeCliente);
                frmBuscaOS = new FrmBuscaOrdemServico(lista,this, (OrdemServicoPadrao) this.getOrdemServicoAction().getOrdemServicoPadrao());
                frmBuscaOS.setResizable(false);
                frmBuscaOS.setAlwaysOnTop(true);
                frmBuscaOS.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frmBuscaOS.setLocationRelativeTo(this);
                frmBuscaOS.setVisible(true);
            } catch (Exception ex) {
                  javax.swing.JOptionPane.showMessageDialog(this, "Falha na consulta.O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else if(cmbBuscaOsPadrao.getSelectedItem().equals("Número Pedido")){

            try{
                long numeroPedido = Long.parseLong(txtCodigoBuscaOsPadrao.getText());
                    os = (OrdemServicoPadrao) this.getOrdemServicoAction().getOsPorPedido(numeroPedido);

                    if(os != null){
                        
                            this.getOrdemServicoAction().setOrdemServicoPadrao(os);
                            this.populaCamposTelaOrdemServicoPadrao((OrdemServicoPadrao) this.getOrdemServicoAction().getOrdemServicoPadrao());
                        
                    }else{
                            javax.swing.JOptionPane.showMessageDialog(this, "O número de pedido informado não foi localizado!","Atenção",2);
                    }                    
            }catch(Exception e){
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, e);
            }
                    
        }
*/
    }//GEN-LAST:event_btnConsultaOrdemServicoPadraoActionPerformed

    private void btnExcluirOsPadraoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirOsPadraoActionPerformed
        
        try {
            int r = javax.swing.JOptionPane.showConfirmDialog(this,"Esse procedimento irá remover as informações"
                    + " da OS e do Pedido da base de dados.\nTais informações não poderão ser consultadas posteriormente. \n"
                    + "Deseja prosseguir assim mesmo ?","Atenção", 0);
                if(r == 0){
                    this.getOrdemServicoAction().remover((OrdemServicoPadrao) this.getOrdemServicoAction().getOrdemServicoPadrao());
                    javax.swing.JOptionPane.showMessageDialog(this, "Operação realizada com sucesso!!","Aviso",1);
                    new OsForm().limparCamposFormulario();
                }
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Falha na consulta.O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",0);
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }//GEN-LAST:event_btnExcluirOsPadraoActionPerformed

    /*Metodo responsavel por enriquecer o objeto
     OrdemServicoPadrao com base nos objetos da tela*/
    private void enriqueceObjetoOSPadrao(OrdemServicoPadrao osPadrao){
        
        try {
                osPadrao.setIdOrdemServico(Long.parseLong(txtCodigoOsPadrao.getText()));
                osPadrao.setPedido((Pedido) this.getPedidoAction().buscarPorId(Long.parseLong(txtNumeroPedidoOsPadrao.getText())));
                osPadrao.setDataExecucao((String) txtDataExecucaoOsPadrao.getValue());
                osPadrao.setHoraExecucao(Time.valueOf(txtHoraExecucaoOsPadrao.getText()));
                osPadrao.setStatus((String) cmbStatusOsPadrao.getSelectedItem());
                osPadrao.setDataEmissao(txtDataEmissaoOsPadrao.getText());
                osPadrao.setHoraEmissao(Time.valueOf(txtHoraEmissaoOsPadrao.getText()));
                osPadrao.setValor(new Util().converteValorTextoDouble(txtValorOsPadrao.getText()));
                osPadrao.setUsuarioLog(txtUsuarioOsPadrao.getText());
                osPadrao.setObservacao(txtObsOsPadrao.getText());
                osPadrao.setContrato((Contrato) this.getContratoAction().getContratoPorId(txtNumeroPedidoOsPadrao.getText()));
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Falha durante o enriquecimento da ordem de serviço. O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }


    }



    private void btnAlterarOsPadraoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarOsPadraoActionPerformed
        
        
        
        this.ordemServicoAction.getOrdemServicoPadrao().setObservacao(txtObsOsPadrao.getText());
        this.ordemServicoAction.getOrdemServicoPadrao().setUsuarioLog(usuarioTela.getUsuario());
        this.ordemServicoAction.getOrdemServicoPadrao().setStatus((String) cmbStatusOsPadrao.getSelectedItem());

        if(txtDataExecucaoOsPadrao.getValue() != null){
            this.ordemServicoAction.getOrdemServicoPadrao().setDataExecucao(format.format(txtDataExecucaoOsPadrao.getValue()));
        }

        if(!txtHoraExecucaoOsPadrao.getText().equals("")){
            this.ordemServicoAction.getOrdemServicoPadrao().setHoraExecucao(Time.valueOf(txtHoraExecucaoOsPadrao.getText()));
        }
        
        try {
            this.ordemServicoAction.alterar(this.ordemServicoAction.getOrdemServicoPadrao());
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Falha ao atualizar OS.","Atenção",2);
        }

        /*
         OrdemServicoPadrao os = (OrdemServicoPadrao) this.ordemServicoAction.getOrdemServicoPadrao();
         Pedido pedido = os.getPedido();
        

        try {
            if(txtCodigoOsPadrao.getText()== null){
                javax.swing.JOptionPane.showMessageDialog(this, "A Ordem de Serviço ainda não foi cadastrada. Não é possível realizar a operação!","Atenção",0);
            }else{
                if(txtDataExecucaoOsPadrao.getValue()!= null){
                    os.setDataExecucao(String.valueOf(format.format(txtDataExecucaoOsPadrao.getValue())));
                }

                if(!txtHoraExecucaoOsPadrao.getText().equals("")){
                    os.setHoraExecucao(Time.valueOf(txtHoraExecucaoOsPadrao.getText()));
                }
                
                os.setObservacao(txtObsOsPadrao.getText());
                os.setUsuarioLog(this.usuarioTela.getUsuario());
                os.setStatus((String) cmbStatusOsPadrao.getSelectedItem());

                if(pedido != null){
                    if(os.getStatus().equalsIgnoreCase("Fechada")){
                        pedido.setStatus("Fechado");
                    }else if(os.getStatus().equalsIgnoreCase("Cancelada")){
                        pedido.setStatus("Cancelado");
                    }else if(os.getStatus().equalsIgnoreCase("Indisp. ClientePessoa")){
                        pedido.setStatus("Indisp. ClientePessoa");
                    }
                }                

                /*
                if(os.getStatus().equalsIgnoreCase("Fechada")){
                    if(os.getDataExecucao().equals("") || os.getHoraExecucao() == null){
                        javax.swing.JOptionPane.showMessageDialog(this, "Falha na Operação. Os Campos Data Execução e Hora Execução precisam ser informados.","Atenção",0);
                    }else{
                        this.getOrdemServicoAction().atualizar(os);
                        javax.swing.JOptionPane.showMessageDialog(this, "A alteração foi realizada com sucesso!","Aviso",3);
                    }*/
                //}else{
        /*
                if(this.getOrdemServicoAction().isStatusOSValido()){
                        this.getOrdemServicoAction().atualizar(os);
                }
                //}
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btnAlterarOsPadraoActionPerformed


    public void atualizarTotalOS(Double valor){
        this.txtValorOsPadrao.setText(new Util().formataMoeda(valor));
    }


    private void btnIncluirOsPadraoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirOsPadraoActionPerformed

        this.ordemServicoAction.getOrdemServicoPadrao().setObservacao(txtObsOsPadrao.getText());
        this.ordemServicoAction.getOrdemServicoPadrao().setUsuarioLog(usuarioTela.getUsuario());
        this.ordemServicoAction.getOrdemServicoPadrao().setStatus((String) cmbStatusOsPadrao.getSelectedItem());
        try {
            this.ordemServicoAction.inserir(this.ordemServicoAction.getOrdemServicoPadrao());
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Falha ao inserir OS.","Atenção",2);
        }
    }//GEN-LAST:event_btnIncluirOsPadraoActionPerformed

    private void btnNovoOsPadraoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoOsPadraoActionPerformed

        String codigo = javax.swing.JOptionPane.showInputDialog("Informe o número do contrato ou número do pedido:");

        this.getOrdemServicoAction().prepararNovaOs(codigo);
        
    }//GEN-LAST:event_btnNovoOsPadraoActionPerformed

    private void btnGerenciaServicoOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciaServicoOsActionPerformed
/*
        FrmGerenciaServicosOS frmGerenciaServicosOS = new FrmGerenciaServicosOS(this,this.getOrdemServicoAction(),this.tabelaServicosOsPadrao,txtValorOsPadrao);
                frmGerenciaServicosOS.setResizable(false);
                frmGerenciaServicosOS.setAlwaysOnTop(true);
                frmGerenciaServicosOS.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frmGerenciaServicosOS.setLocationRelativeTo(this);
                frmGerenciaServicosOS.setVisible(true);*/
    }//GEN-LAST:event_btnGerenciaServicoOsActionPerformed

    private void validarHoraExecucao(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_validarHoraExecucao
        try{
            String hora = txtHoraExecucaoOsPadrao.getText();
            if(hora.length()==4){
                hora = hora.substring(0, 2)+ ":"+hora.substring(2, 4)+ ":00";
                txtHoraExecucaoOsPadrao.setText(hora);
            }else if(hora.length()==5){
                hora = hora.substring(0, 5)+ ":00";
                txtHoraExecucaoOsPadrao.setText(hora);
            }
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(this, "A hora informada não é válida!","Atenção",2);
        }
    }//GEN-LAST:event_validarHoraExecucao

    private void aplicarRegrasStatusPedido(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_aplicarRegrasStatusPedido

        if(String.valueOf(cmbStatusPedido.getSelectedItem()).equals("Fechado")){
                    lbDataExecucao.setVisible(true);
                    lbHoraExecucao.setVisible(true);
                    txtDataExecucao.setVisible(true);
                    cmbHoraExecucaoPedido.setVisible(true);
        }else{
            lbDataExecucao.setVisible(false);
            lbHoraExecucao.setVisible(false);
            txtDataExecucao.setVisible(false);
            cmbHoraExecucaoPedido.setVisible(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_aplicarRegrasStatusPedido

    private void btnBuscarClientePessoaFisicaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarClientePessoaFisicaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            new ClienteForm().buscarClientePessoaFisica(this);
        }
    }//GEN-LAST:event_btnBuscarClientePessoaFisicaKeyPressed

    private void carregaOrdemServicoTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregaOrdemServicoTab

            

            

       
    }//GEN-LAST:event_carregaOrdemServicoTab

    private void removerServicoOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerServicoOsActionPerformed
       
        try {
            int row = tabelaServicosOsPadrao.getSelectedRow();

            if(row == -1){
                javax.swing.JOptionPane.showMessageDialog(rootPane,"Selecione um item na lista de serviços..","Atenção - Aviso",1);
            }else{
                this.getOrdemServicoAction().removerServicoOs(row);
            }
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(rootPane,"Falha ao remover item. O seguinte erro ocorreu: " + ex.getMessage(),"Atenção - Erro",1);
        }
    }//GEN-LAST:event_removerServicoOsActionPerformed

    private void btnRefreshServicoOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshServicoOsActionPerformed

                int retorno = javax.swing.JOptionPane.showConfirmDialog(rootPane, "Esse procedimento deixará a OS em seu estado original e todas as informaçoes ALTERADAS serão perdidas.Deseja prosseguir ?", "Atenção - Aviso", 0);
                
                if(retorno == 0){
                    try {
                        if(this.getOrdemServicoAction().getOrdemServicoPadrao().getContrato() != null){
                            this.getOrdemServicoAction().prepararNovaOs(this.getOrdemServicoAction().getOrdemServicoPadrao().getContrato().getIdContrato());
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }
    }//GEN-LAST:event_btnRefreshServicoOsActionPerformed

    private void statusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_statusFocusLost

        this.ordemServicoAction.aplicarStatusLost();

    }//GEN-LAST:event_statusFocusLost

    private void btnBuscarClientePessoaJuridicaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarClientePessoaJuridicaKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER)
        new ClienteForm().buscarClientePessoaJuridica(this);
    }//GEN-LAST:event_btnBuscarClientePessoaJuridicaKeyPressed

    private void btnBuscarClientePessoaJuridica(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClientePessoaJuridica

        new ClienteForm().buscarClientePessoaJuridica(this);
    }//GEN-LAST:event_btnBuscarClientePessoaJuridica

    private void buscarClientePessoaJuridicaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarClientePessoaJuridicaKeyPressed

        if(evt.getKeyCode()== KeyEvent.VK_ENTER)
        new ClienteForm().buscarClientePessoaJuridica(this);
    }//GEN-LAST:event_buscarClientePessoaJuridicaKeyPressed

    private void btnExcluirEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirEmpresaActionPerformed

        new ClienteForm().removerClientePessoaJuridica(this);

    }//GEN-LAST:event_btnExcluirEmpresaActionPerformed

    private void btnAlterarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarEmpresaActionPerformed

        new ClienteForm().alterarClientePessoaJuridica(this);
    }//GEN-LAST:event_btnAlterarEmpresaActionPerformed

    private void btnIncluirEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirEmpresaActionPerformed

        new ClienteForm().salvarClientePessoaJuridica(this);
    }//GEN-LAST:event_btnIncluirEmpresaActionPerformed

    private void btnNovaEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaEmpresaActionPerformed
        // TODO add your handling code here:

        txtCodigoEmpresa.setText("");
        txtNomeEmpresa.setText("");
        txtCnpjEmpresa.setText("");
        txtIeEmpresa.setText("");
        txtTelComercial.setText("");
        txtTelFaxEmpresa.setText("");
        txtEnderecoEmpresa.setText("");
        txtBairroEmpresa.setText("");
        txtCidadeEmpresa.setText("");
        txtCepEmpresa.setText("");
        txtNomeResponsavelEmpresa.setText("");
        txtTelResponsavelEmpresa.setText("");
        txtObsEmpresa.setText("");
        txtEmailEmpresa.setText("");
    }//GEN-LAST:event_btnNovaEmpresaActionPerformed

    private void txtEnderecoEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoEmpresaActionPerformed

    private void tabelaServicosselecionaLinha(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaServicosselecionaLinha
        try {
            Long idServico = (Long)tabelaServicos.getValueAt(tabelaServicos.getSelectedRow(), 0);
            Servico servico = new Servico();
            servico.setIdServico(idServico);
            servico = this.getServicoAction().buscarPorId(idServico);
            
            txtIdServico.setText(String.valueOf(idServico));
            txtNomeServico.setText(servico.getDescricao());
            txtTempoGarantia.setText(String.valueOf(servico.getGarantia()));
            txtValorServico.setText(new Util().formataMoeda(servico.getValor()));
        } catch (ServicoException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(rootPane,ex.getMensagemErro(),"Atenção - Erro",1);
        }
    }//GEN-LAST:event_tabelaServicosselecionaLinha

    private void btnServicoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServicoNovoActionPerformed
       this.limparCamposTelaServico();
    }//GEN-LAST:event_btnServicoNovoActionPerformed

    private void btnIncluirServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirServicoActionPerformed
        
        this.setServicoAction(new ServicoAction());
        
        Servico servico = new Servico();
        servico.setDescricao(txtNomeServico.getText());
        servico.setGarantia(Integer.parseInt(txtTempoGarantia.getText()));
        Double valorServico;
        try {
            valorServico = new Util().converteValorTextoDouble(txtValorServico.getText());
            servico.setValor(valorServico);
        } catch (ParseException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, "O valor do serviço informado não é válido!","Atenção - Erro",0);
        }
        
        if(servico.isValido()){
            try {
                if(servicoAction.buscarPorNome(servico.getDescricao()).isEmpty()){
                    this.getServicoAction().salvar(servico);
                    javax.swing.JOptionPane.showMessageDialog(this, "Novo Serviço cadastrado com sucesso!","Atenção - Aviso",1);
                    this.limparCamposTelaServico();
                }else{
                    javax.swing.JOptionPane.showMessageDialog(this, "Já existe um serviço com esse nome na base de dados.","Atenção - Erro",1);
                }
            } catch (ServicoException ex) {
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
            }
        }else{
            javax.swing.JOptionPane.showMessageDialog(this, "Verifique se os campos foram preenchidos corretamente","Atenção - Erro",1);
        }
        
        this.carregarServicoTab(null);
        
    }//GEN-LAST:event_btnIncluirServicoActionPerformed

    private void btnAlterarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarServicoActionPerformed
        
            Servico servico;       
        try {
            servico = this.getServicoAction().buscarPorId(Long.parseLong(txtIdServico.getText()));
            
            if(servico == null){
              javax.swing.JOptionPane.showMessageDialog(this, "É necessário selecionar um serviço previamente.","Atenção - Erro",0);  
            }else{
                
                 try {
                    servico.setDescricao(txtNomeServico.getText());                 
                    servico.setGarantia(Integer.parseInt(txtTempoGarantia.getText()));
                    servico.setValor(new Util().converteValorTextoDouble(txtValorServico.getText()));

                    if(servicoAction.buscarPorNome(servico.getDescricao()).isEmpty()){
                        this.getServicoAction().atualizar(servico);
                        javax.swing.JOptionPane.showMessageDialog(this, "O Serviço " + servico.getDescricao() + " foi atualizado com sucesso!","Atenção - Aviso",1);            
                         this.carregarServicoTab(null);
                         this.limparCamposTelaServico();
                    }else{
                        javax.swing.JOptionPane.showMessageDialog(this, "Já existe um serviço com esse nome na base de dados.","Atenção - Erro",1);
                    }
                } catch (ServicoException ex) {
                    Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    javax.swing.JOptionPane.showMessageDialog(rootPane,ex.getMensagemErro(),"Atenção - Erro",1);
                } catch (ParseException ex) {
                    Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    javax.swing.JOptionPane.showMessageDialog(this, "O valor do serviço informado não é válido!","Atenção - Erro",0);
                }
            }
        } catch (ServicoException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
        }
    }//GEN-LAST:event_btnAlterarServicoActionPerformed

    private void btnExcluirServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirServicoActionPerformed
                    
        try {
            Servico servico = this.getServicoAction().buscarPorId(Long.parseLong(txtIdServico.getText()));
            
            if(servico == null){
              javax.swing.JOptionPane.showMessageDialog(this, "É necessário selecionar um serviço previamente.","Atenção - Erro",0);  
            }else{
                servicoAction.remover(servico);
                javax.swing.JOptionPane.showMessageDialog(this, "O Serviço " + servico.getDescricao() + " foi removido com sucesso!","Atenção - Aviso",1);            
                 this.carregarServicoTab(null);
                 this.limparCamposTelaServico();
            }
        } catch (ServicoException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
        }
    }//GEN-LAST:event_btnExcluirServicoActionPerformed

    private void carregarServicoTab(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_carregarServicoTab
        try {
            txtIdServico.setVisible(false);
            this.setServicoAction(new ServicoAction());
            getServicoAction().popularListaServicos(tabelaServicos);
            txtValorServico.setText(new Util().formataMoeda(0.0));
        } catch (PedidoException ex) {
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, "Atenção - Falha ao listar serviços disponiveis. O seguinte erro ocorreu : "+ ex.getMessage(),"Atenção - Erro",0);
        }
    }//GEN-LAST:event_carregarServicoTab

    private void valorServicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_valorServicoFocusLost
        txtValorServico.setText(new Util().formataMoeda(new Util().converterValor(txtValorServico.getText())));
    }//GEN-LAST:event_valorServicoFocusLost

    private void btnVisualizaDadosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizaDadosClienteActionPerformed
        try {
            
            String tipoPessoa = pedidoAction.getPedido().getCliente().getTipoCliente().toString();
            
            
            if(tipoPessoa.equalsIgnoreCase(TipoCliente.CONDOMINIO.toString())){
                this.clienteCondominio = clienteCondominioAction.buscarPorId(pedidoAction.getPedido().getCliente().getIdCliente());            
                this.carregaClienteCondominioTab(null);
                this.popularClienteCondominio(this.clienteCondominio);               
            }else if(tipoPessoa.equalsIgnoreCase(TipoCliente.FISICA.toString())){
                this.clientePessoa = clientePessoaFisicaAction.buscarPorId(pedidoAction.getPedido().getCliente().getIdCliente());            
                this.carregarClientePessoaTab(null);
                this.popularClientePessoa(this.clientePessoa);               
            }else if(tipoPessoa.equalsIgnoreCase(TipoCliente.JURIDICA.toString())){
                this.clientePessoaJuridica = clienteEmpresaAction.buscarPorId(pedidoAction.getPedido().getCliente().getIdCliente());            
                this.carregarClientePessoaTab(null);
                this.popularClientePessoaJuridica(this.clientePessoaJuridica);           
            }
            
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnVisualizaDadosClienteActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        new PedidoReport().gerarRelatorio(pedidoAction.getPedido());
        
    }//GEN-LAST:event_jButton8ActionPerformed




    public void setTxtValorTotalContrato(String valor ){
        txtValorTotalContrato.setText(valor);
    }

    public void populaPedidoConsultado(Pedido pedido){
        //new PedidoForm().preparaCamposNovoPedido();
        this.populaCamposTelaPedido(pedido);        
    }

   public void populaCamposTelaOrdemServicoPadrao(OrdemServicoPadrao os){

       
    }

    public void populaCamposTelaPedido(Pedido pedido){

        try{
              //Lista os itens do pedido consultado
                ((ServicoPedidoTableModel)tableServicosPedido.getModel()).addListaDeItens((List<ItemPedido>) pedido.getItensPedido());

            txtNumeroPedido.setText(String.valueOf(pedido.getIdPedido()));
            txtDataCadastroPedido.setText(pedido.getDataCadastro());
            txtNomeClientePedido.setText(pedido.getCliente().getNome());
//            txtEnderecoPedido.setText(pedido.getCliente().getEndereco());
//            cmbUfPedido.setSelectedItem(pedido.getCliente().getUf());
//            txtBairroPedido.setText(pedido.getCliente().getBairro());
//            txtCidadePedido.setText(pedido.getCliente().getCidade());
//            txtContatoPedido.setText(pedido.getCliente().getNomeContato());
//            txtTelefoneContatoPedido.setText(pedido.getCliente().getTelefoneContato());
            txtObsPedido.setText(pedido.getObservacao());
            cmbStatusPedido.setSelectedItem(pedido.getStatus());
            txtDataAgendamento.setValue(pedido.getDataAgendamento());
            cmbHoraAgendamentoPedido.setSelectedItem(pedido.getHoraAgendamento().toString());
            txtTotalPedido.setText(new Util().formataMoeda(pedido.getValorPedido()));
//            txtTelefoneClientePedido.setText(((ClientePessoaFisica)(pedido.getCliente())).getTelefoneResidencial());
            
            
//            if(pedido.getFormaPagamento()!=null){
//                btnFormaPagamento.setEnabled(true);
//            }else{
//                btnFormaPagamento.setEnabled(false);
//            }

            if(pedido.getStatus().equals("Fechado")){
                lbDataExecucao.setVisible(true);
                lbHoraExecucao.setVisible(true);
                txtDataExecucao.setVisible(true);
                cmbHoraExecucaoPedido.setVisible(true);
            }else{
                lbDataExecucao.setVisible(false);
                lbHoraExecucao.setVisible(false);
                txtDataExecucao.setVisible(false);
                cmbHoraExecucaoPedido.setVisible(false);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Metodo responsavel por habilitar os campos na tela de pedido*/
    
  
    

    public void populaContratoConsultado(Contrato contrato){

        this.populaCamposTelaContrato(contrato);

     //   Habilita Campos Tela Contrato
        
     //   txtNomeClientePedido.setEnabled(true);
     //   btnBuscaClientePedido.setEnabled(true);
     //   txtEnderecoPedido.setEnabled(true);
     //   cmbUfPedido.setEnabled(true);
     //   txtBairroPedido.setEnabled(true);
     //   txtCidadePedido.setEnabled(true);
     //   txtContatoPedido.setEnabled(true);
     //   txtTelefoneContatoPedido.setEnabled(true);
     //   txtObsPedido.setEnabled(true);
     //   btnAddItemServico.setEnabled(true);
     //   btnRemoveItemServico.setEnabled(true);
     //   btnFormaPagamento.setEnabled(true);
     //   btnIncluirPedido.setEnabled(false);
     //   btnAlterarPedido.setEnabled(true);
     //   btnExcluirPedido.setEnabled(true);
     //   cmbStatusPedido.setEnabled(true);
     //   txtHoraAgendamento.setEnabled(true);
     //   txtDataAgendamento.setEnabled(true);
    }


    public void populaCamposTelaContrato(Contrato contrato){
        try {
                //Lista os itens do contrato consultado
                ((ServicoContratoTableModel)tableServicosContrato.getModel()).addListaDeItemContratados(contrato.getItensContratados());

                //Preenche campos da tela
                 //Habilita Campos Tela Controle de Contratos
            txtCodigoContrato.setText(contrato.getIdContrato());
            txtDataInicioContrato.setText(contrato.getDataInicioContrato());
            txtDataExpiracao.setText(contrato.getDataExpiracao());
            txtNomeCC.setText(contrato.getCliente().getNome());
            txtEnderecoCC.setText(contrato.getCliente().getEndereco());
            cmbUfCC.setSelectedItem(contrato.getCliente().getUf());
            txtBairroCC.setText(contrato.getCliente().getBairro());
            txtCidadeCC.setText(contrato.getCliente().getCidade());
            txtContatoCC.setText(contrato.getCliente().getNomeContato());
            txtTelefoneContatoCC.setText(contrato.getCliente().getTelefoneContato());
            txtObsCC.setText(contrato.getObservacao());
            txtValorTotalContrato.setText(new Util().formataMoeda(contrato.getTotal()));
            cmdPeriodoContrato.setValue(contrato.getPeriodo());
            btnIncluirContrato.setEnabled(false);
            btnAlterarContrato.setEnabled(true);
            btnExcluirContrato.setEnabled(true);

        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


     //Metodo responsavel por editar o valor do pedido direto na tabela de Itens do Pedido
    /*
    public void carregarComboColaboradores(){

         /* Limpa Combo de Usuarios */
    /*
        this.limparComboUsuarioColaborador();

        ArrayList lista = (ArrayList) this.getUsuarioAction().getListaColaboradores();
        Iterator i = lista.iterator();
        
        while(i.hasNext()){
               cmbColaborador.addItem(((Colaborador)i.next()).getNome());
        }
    }*/

    public void carregarComboUsuarioCadaGrupo(){

         /* Limpa Combo de Colaboradores que possuem usuarios cadastrados */
        this.limparComboUsuarioColaborador();

//        ArrayList lista = (ArrayList) this.getUsuarioCadaGrupoAction().getListaColaboradores();
//        Iterator i = lista.iterator();
//
//        while(i.hasNext()){
//               cmbUsuarioColaborador.addItem(((Colaborador)i.next()).getNome());
//        }
    }

   
    public boolean validaCamposUsuario(){
        boolean retorno = false;

        if(txtNomeUsuario.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "O campo Nome do Usuário deve ser preenchido!","Atenção - Aviso",0);
            retorno = false;
        }else if(txtSenha.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "O campo Senha deve ser preenchido!","Atenção - Aviso",0);
            retorno = false;
        }else{
            retorno = true;
        }

        return retorno;
    }

    public boolean validaCamposGrupoUsuario(){
        boolean retorno = false;

        if(txtGrupoUsuario.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "O campo Grupo deve ser preenchido!","Atenção - Aviso",0);
            retorno = false;
        }
        else{
            retorno = true;
        }

        return retorno;
    }

    //Limpa o combo de colabores da tela de Usuario
     public void limparComboColaboradores(){
        cmbColaborador.removeAllItems();
    }

     //Limpa o combo de colabores da tela de Usuario de Cada Grupo
     public void limparComboUsuarioColaborador(){
        cmbUsuarioColaborador.removeAllItems();
    }


      public void limparCamposTelaContrato(){
        txtCodigoContrato.setText("");
        txtDataInicioContrato.setText("");
        cmdPeriodoContrato.setValue((Integer)0);
        txtDataExpiracao.setText("");
        txtValorTotalContrato.setText("");
        txtNomeCC.setText("");
        txtEnderecoCC.setText("");
        txtBairroCC.setText("");
        txtCidadeCC.setText("");
        cmbUfCC.setSelectedItem(null);
        txtContatoCC.setText("");
        txtTelefoneContatoCC.setText("");
        txtObsCC.setText("");
        ((ServicoContratoTableModel)tableServicosContrato.getModel()).limpar();
        this.contratoAction = new ContratoAction();
     }


     public void limparCamposTelaUsuario(){
         txtNomeUsuario.setText("");
         txtSenha.setText("");
         chkBloqueado.setSelected(false);        
     }

     public void limparCamposTelaGrupoUsuario(){
         this.txtGrupoUsuario.setText(""); 
     }

     public void limparCamposTelaServico(){
         this.txtNomeServico.setText(""); 
         this.txtTempoGarantia.setText(""); 
         this.txtValorServico.setText(""); 
     }


    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nome;
    protected javax.swing.JButton btnAddItemServico;
    private javax.swing.JButton btnAlterarClienteCondominio;
    private javax.swing.JButton btnAlterarContrato;
    private javax.swing.JButton btnAlterarEmpresa;
    private javax.swing.JButton btnAlterarGrupo;
    public javax.swing.JButton btnAlterarOsPadrao;
    protected javax.swing.JButton btnAlterarPedido;
    private javax.swing.JButton btnAlterarPessoa;
    private javax.swing.JButton btnAlterarServico;
    private javax.swing.JButton btnAlterarUsuario;
    private javax.swing.JButton btnCondominioNovo;
    private javax.swing.JButton btnConsultaContrato;
    public javax.swing.JButton btnConsultaOrdemServicoPadrao;
    protected javax.swing.JButton btnConsultaPedido;
    private javax.swing.JButton btnExcluirCondominio;
    private javax.swing.JButton btnExcluirContrato;
    private javax.swing.JButton btnExcluirEmpresa;
    private javax.swing.JButton btnExcluirGrupo;
    public javax.swing.JButton btnExcluirOsPadrao;
    protected javax.swing.JButton btnExcluirPedido;
    private javax.swing.JButton btnExcluirPessoa;
    private javax.swing.JButton btnExcluirServico;
    private javax.swing.JButton btnExcluirUsuario;
    protected javax.swing.JButton btnFormaPagamento;
    private javax.swing.JButton btnGerenciaServicoOs;
    private javax.swing.JButton btnGerenciaServicosContrato;
    private javax.swing.JButton btnIncluirCondominio;
    private javax.swing.JButton btnIncluirContrato;
    private javax.swing.JButton btnIncluirEmpresa;
    private javax.swing.JButton btnIncluirGrupo;
    public javax.swing.JButton btnIncluirOsPadrao;
    protected javax.swing.JButton btnIncluirPedido;
    private javax.swing.JButton btnIncluirPessoa;
    private javax.swing.JButton btnIncluirServico;
    private javax.swing.JButton btnIncluirUsuario;
    private javax.swing.JButton btnNovaEmpresa;
    private javax.swing.JButton btnNovoContrato;
    private javax.swing.JButton btnNovoGrupo;
    public javax.swing.JButton btnNovoOsPadrao;
    protected javax.swing.JButton btnNovoPedido;
    private javax.swing.JButton btnPessoaNova;
    private javax.swing.JButton btnRefreshServicoOs;
    protected javax.swing.JButton btnRemoveItemServico;
    private javax.swing.JButton btnRemoveServicoOs;
    private javax.swing.JButton btnServicoNovo;
    private javax.swing.JButton btnUsuarioNovo;
    protected javax.swing.JButton btnVisualizaDadosCliente;
    private javax.swing.JCheckBox chkBloqueado;
    private javax.swing.JComboBox cmbBuscaCondominio;
    private javax.swing.JComboBox cmbBuscaContrato;
    private javax.swing.JComboBox cmbBuscaEmpresa;
    public javax.swing.JComboBox cmbBuscaOsPadrao;
    protected javax.swing.JComboBox cmbBuscaPedido;
    private javax.swing.JComboBox cmbBuscaPessoa;
    private javax.swing.JComboBox cmbColaborador;
    private javax.swing.JComboBox cmbHoraAgendamentoPedido;
    private javax.swing.JComboBox cmbHoraExecucaoPedido;
    public javax.swing.JComboBox cmbStatusOsPadrao;
    protected javax.swing.JComboBox cmbStatusPedido;
    private javax.swing.JComboBox cmbUf;
    private javax.swing.JComboBox cmbUfCC;
    private javax.swing.JComboBox cmbUfEmpresa;
    private javax.swing.JComboBox cmbUfOrdemServico;
    private javax.swing.JComboBox cmbUfPessoa;
    private javax.swing.JComboBox cmbUsuarioColaborador;
    private javax.swing.JSpinner cmdPeriodoContrato;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;

    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    public javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;

    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbDataExecucao;
    private javax.swing.JLabel lbHoraExecucao;
    private javax.swing.JTable tabelaGrupoUsuarios;
    private javax.swing.JTable tabelaServicos;
    private javax.swing.JTable tabelaServicosOsPadrao;
    private javax.swing.JTable tabelaUsuarios;
    private javax.swing.JTable tabelaUsuariosCadaGrupo;
    private javax.swing.JTable tableServicosContrato;
    protected javax.swing.JTable tableServicosDisponiveisPedido;
    protected javax.swing.JTable tableServicosPedido;
    private javax.swing.JTextField txtAndares;
    private javax.swing.JTextField txtApAndar;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtBairroCC;
    private javax.swing.JTextField txtBairroEmpresa;
    private javax.swing.JTextField txtBairroOsPadrao;
    private javax.swing.JTextField txtBairroPessoa;
    private javax.swing.JTextField txtBlocos;
    private javax.swing.JTextField txtBuscaCondominio;
    private javax.swing.JTextField txtBuscaEmpresa;
    private javax.swing.JTextField txtBuscaPessoa;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JFormattedTextField txtCepEmpresa;
    private javax.swing.JFormattedTextField txtCepPessoa;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCidadeCC;
    private javax.swing.JTextField txtCidadeEmpresa;
    private javax.swing.JTextField txtCidadeOsPadrao;
    private javax.swing.JTextField txtCidadePessoa;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JFormattedTextField txtCnpjEmpresa;
    private javax.swing.JTextField txtCodigoBuscaContrato;
    private javax.swing.JTextField txtCodigoBuscaOsPadrao;
    protected javax.swing.JTextField txtCodigoBuscaPedido;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCodigoClienteOsPadrao;
    private javax.swing.JTextField txtCodigoContrato;
    private javax.swing.JTextField txtCodigoEmpresa;
    private javax.swing.JTextField txtCodigoOsPadrao;
    private javax.swing.JTextField txtCodigoPessoa;
    private javax.swing.JTextField txtContatoCC;
    private javax.swing.JTextField txtContatoPessoa;
    private javax.swing.JFormattedTextField txtCpfPessoa;
    protected javax.swing.JTextField txtDataCadastroPedido;
    private javax.swing.JTextField txtDataEmissaoOsPadrao;
    private javax.swing.JTextField txtDataExpiracao;
    private javax.swing.JTextField txtDataInicioContrato;
    private javax.swing.JTextField txtEmailCondominio;
    private javax.swing.JTextField txtEmailEmpresa;
    private javax.swing.JTextField txtEmailPessoa;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtEnderecoCC;
    private javax.swing.JTextField txtEnderecoEmpresa;
    private javax.swing.JTextField txtEnderecoOsPadrao;
    private javax.swing.JTextField txtEnderecoPessoa;
    private javax.swing.JTextField txtGrupoUsuario;
    private javax.swing.JTextField txtHoraEmissaoOsPadrao;
    public javax.swing.JFormattedTextField txtHoraExecucaoOsPadrao;
    private javax.swing.JTextField txtIdServico;
    private javax.swing.JTextField txtIe;
    private javax.swing.JTextField txtIeEmpresa;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeCC;
    private javax.swing.JTextField txtNomeClienteOsPadrao;
    private javax.swing.JTextField txtNomeClientePedido;
    private javax.swing.JTextField txtNomeContatoOsPadrao;
    private javax.swing.JTextField txtNomeEmpresa;
    private javax.swing.JTextField txtNomePessoa;
    private javax.swing.JTextField txtNomeResponsavelEmpresa;
    private javax.swing.JTextField txtNomeServico;
    private javax.swing.JTextField txtNomeSindico;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JTextField txtNomeZelador;
    protected javax.swing.JTextField txtNumeroPedido;
    private javax.swing.JTextField txtNumeroPedidoOsPadrao;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextArea txtObsCC;
    private javax.swing.JTextArea txtObsEmpresa;
    private javax.swing.JTextArea txtObsOsPadrao;
    protected javax.swing.JTextPane txtObsPedido;
    private javax.swing.JTextArea txtObsPessoa;
    private javax.swing.JTextField txtQuartos;
    private javax.swing.JTextField txtRgPessoa;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtTelCelularPessoa;
    private javax.swing.JTextField txtTelComercial;
    private javax.swing.JTextField txtTelComercialEmpresa;
    private javax.swing.JTextField txtTelContatoPessoa;
    private javax.swing.JTextField txtTelFax;
    private javax.swing.JTextField txtTelFaxEmpresa;
    private javax.swing.JTextField txtTelResidencialPessoa;
    private javax.swing.JTextField txtTelResponsavelEmpresa;
    private javax.swing.JTextField txtTelSindico;
    private javax.swing.JTextField txtTelZelador;
    private javax.swing.JTextField txtTelefoneContatoCC;
    private javax.swing.JTextField txtTelefoneContatoOsPadrao;
    private javax.swing.JTextField txtTempoGarantia;
    protected javax.swing.JTextField txtTotalPedido;
    private javax.swing.JTextField txtUsuarioOsPadrao;
    private javax.swing.JTextField txtValorOsPadrao;
    private javax.swing.JTextField txtValorServico;
    private javax.swing.JTextField txtValorTotalContrato;
    // End of variables declaration//GEN-END:variables

    protected DateField txtDataAgendamento = new DateField();
    protected DateField txtDataExecucao = new DateField();
    public DateField txtDataExecucaoOsPadrao = new DateField();
    

    /**
     * @return the usuarioAction
     */
    public UsuarioAction getUsuarioAction() {
        return usuarioAction;
    }

    /**
     * @param usuarioAction the usuarioAction to set
     */
    public void setUsuarioAction(UsuarioAction usuarioAction) {
        this.usuarioAction = usuarioAction;
    }

    /**
     * @return the usuarioTela
     */
    public Usuario getUsuarioTela() {
        return usuarioTela;
    }

    /**
     * @param usuarioTela the usuarioTela to set
     */
    public void setUsuarioTela(Usuario usuarioTela) {
        usuarioTela = usuarioTela;
    }

    /**
     * @return the grupoUsuarioAction
     */
    public GrupoUsuarioAction getGrupoUsuarioAction() {
        return grupoUsuarioAction;
    }

    /**
     * @param grupoUsuarioAction the grupoUsuarioAction to set
     */
    public void setGrupoUsuarioAction(GrupoUsuarioAction grupoUsuarioAction) {
        this.grupoUsuarioAction = grupoUsuarioAction;
    }

    /**
     * @return the grupoUsuarioTela
     */
    public GrupoUsuario getGrupoUsuarioTela() {
        return grupoUsuarioTela;
    }

    /**
     * @param grupoUsuarioTela the grupoUsuarioTela to set
     */
    public void setGrupoUsuarioTela(GrupoUsuario grupoUsuarioTela) {
        this.grupoUsuarioTela = grupoUsuarioTela;
    }

    /**
     * @return the usuarioCadaGrupoAction
     */
    public UsuarioCadaGrupoAction getUsuarioCadaGrupoAction() {
        return usuarioCadaGrupoAction;
    }

    /**
     * @param usuarioCadaGrupoAction the usuarioCadaGrupoAction to set
     */
    public void setUsuarioCadaGrupoAction(UsuarioCadaGrupoAction usuarioCadaGrupoAction) {
        this.usuarioCadaGrupoAction = usuarioCadaGrupoAction;
    }

    /**
     * @return the clienteCondominio
     */
    public ClienteCondominio getClienteCondominio() {
        return clienteCondominio;
    }

    /**
     * @param clienteCondominio the clienteCondominio to set
     */
    public void setClienteCondominio(ClienteCondominio clienteCondominio) {
        this.clienteCondominio = clienteCondominio;
    }

    /**
     * @return the clientePessoa
     */
    public ClientePessoaFisica getClientePessoa() {
        return clientePessoa;
    }

    /**
     * @param clientePessoa the clientePessoa to set
     */
    public void setClientePessoa(ClientePessoaFisica clientePessoa) {
        this.clientePessoa = clientePessoa;
    }


    /**
     * @return the action
     */
    public GenericAction getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(GenericAction action) {
        this.action = action;
    }

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

    /**
     * @return the servicoAction
     */
    public ServicoAction getServicoAction() {
        return servicoAction;
    }

    /**
     * @param servicoAction the servicoAction to set
     */
    public void setServicoAction(ServicoAction servicoAction) {
        this.servicoAction = servicoAction;
    }

    /**
     * @return the contratoAction
     */
    public ContratoAction getContratoAction() {
        if(contratoAction ==null){
            return new ContratoAction();
        }else{
            return contratoAction;
        }
    }

    /**
     * @param contratoAction the contratoAction to set
     */
    public void setContratoAction(ContratoAction contratoAction) {
        this.contratoAction = contratoAction;
    }

    /**
     * @return the ordemServicoAction
     */
    public OrdemServicoAction getOrdemServicoAction() {
        return ordemServicoAction;
    }

    /**
     * @param ordemServicoAction the ordemServicoAction to set
     */
    public void setOrdemServicoAction(OrdemServicoAction ordemServicoAction) {
        this.ordemServicoAction = ordemServicoAction;
    }

    /*
    @Override
    public void update(Observable o, Object arg) {

        if(arg instanceof Pedido){
            this.populaCamposTelaPedido((Pedido) arg);
        }else if(arg instanceof OrdemServicoController){
            new OsForm().limparCamposFormulario();
            new OsForm().prepararNovaOs((OrdemServicoInterface) arg);
        }
    }

*/

    public void enriqueceObjetoPedido(Pedido p){

    }

    @Override
   public void update(Object arg) {
         if(arg instanceof Pedido){
            this.populaCamposTelaPedido((Pedido) arg);
        }else if(arg instanceof OrdemServicoPadrao){
            new OsForm().limparCamposFormulario();
            new OsForm().popularOs((OrdemServicoPadrao) arg);
        }
    }

    private void carregarComboColaboradores() {
         /* Limpa Combo de Colaboradores que possuem usuarios cadastrados */
        this.limparComboColaboradores();
        
        try {
            ArrayList lista = (ArrayList) this.getUsuarioAction().getListaColaboradores();
            
            Iterator i = lista.iterator();

            while(i.hasNext()){
                   cmbColaborador.addItem(((Colaborador)i.next()).getNome());
            }
        } catch (UsuarioException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMensagemErro(),"Atenção - Erro",0);
        }        
    }

    /**
     * @return the clientePessoaFisicaAction
     */
    public ClientePessoaFisicaAction getClientePessoaFisicaAction() {
        return clientePessoaFisicaAction;
    }

    /**
     * @param clientePessoaFisicaAction the clientePessoaFisicaAction to set
     */
    public void setClientePessoaFisicaAction(ClientePessoaFisicaAction clientePessoaFisicaAction) {
        this.clientePessoaFisicaAction = clientePessoaFisicaAction;
    }



    /************************ ClienteForm *************************/
    class ClienteForm {

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            private void buscarClientePessoaJuridica(FrmPrincipal frmPrincipal){
                 //BuscaEmpresaAction buscaEmpresaAction = new BuscaEmpresaAction();

                FrmBuscaCliente frmBC = null;


                if(cmbBuscaEmpresa.getSelectedIndex()==0){
                    try {
                        ClientePessoaJuridica ce = clienteEmpresaAction.buscarPorId(Long.parseLong(txtBuscaEmpresa.getText()));
                        if(ce!=null)
                        popularClientePessoaJuridica(ce);
                    } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }

                }else if(cmbBuscaEmpresa.getSelectedIndex()==1){
                    try {
                        //Busca por nome
                        List lista = clienteEmpresaAction.buscarListaPorNome(txtBuscaEmpresa.getText());
                        frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Empresa");                        
                     } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }


                }else if(cmbBuscaEmpresa.getSelectedIndex()==2){
                    try {
                        //Busca por cnpj
                        List lista = clienteEmpresaAction.buscarListaPorCnpj(txtBuscaEmpresa.getText());
                        frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Empresa");                        
                     } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }
                }else if(cmbBuscaEmpresa.getSelectedIndex()==3){
                     //Busca por endereço
                    try {
                        List lista = clienteEmpresaAction.buscarListaPorEndereco(txtBuscaEmpresa.getText());
                        frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Empresa");                        
                     } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }
                }else if (cmbBuscaEmpresa.getSelectedIndex() == 4) {
                     //Busca por bairro
                    try {
                        List lista = clienteEmpresaAction.buscarListaPorBairro(txtBuscaEmpresa.getText());
                        frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Empresa");                        
                     } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }
                }else if (cmbBuscaEmpresa.getSelectedIndex() == 5) {
                     //Busca por telefone comercial
                    try {
                        List lista = clienteEmpresaAction.buscarListaPorTelefoneComercial(txtBuscaEmpresa.getText());
                        frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Empresa");                        
                     } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }
                }

                if(frmBC != null){
                    frmBC.setResizable(false);
                    frmBC.setAlwaysOnTop(true);
                    frmBC.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    frmBC.setLocationRelativeTo(frmPrincipal);
                    frmBC.setVisible(true);
                }
            }

            private void buscarClientePessoaFisica(FrmPrincipal frmPrincipal){

                FrmBuscaCliente frmBC = null;

                if(cmbBuscaPessoa.getSelectedIndex()==0){
                    try {
                        ClientePessoaFisica cp = clientePessoaFisicaAction.buscarPorId(Long.parseLong(txtBuscaPessoa.getText()));
                        if(cp!=null)
                        popularClientePessoa(cp);
                    } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha na Consulta - Verifique se o campo de busca foi preenchido corretamente! ","Atenção - Aviso",0);
                    }

                }else if(cmbBuscaPessoa.getSelectedIndex()==1){
                    try {
                        //Busca por nome
                        List lista = clientePessoaFisicaAction.buscarListaPorNome(txtBuscaPessoa.getText());
                        frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Pessoa");                        
                     } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }


                }else if(cmbBuscaPessoa.getSelectedIndex()==2){
                    try {
                        //Busca por cnpj
                        List lista = clientePessoaFisicaAction.buscarListaPorCpf(txtBuscaPessoa.getText());
                        frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Pessoa");
                    } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }
                }else if(cmbBuscaPessoa.getSelectedIndex()==3){
                     //Busca por endereço
                    try {
                        List lista = clientePessoaFisicaAction.buscarListaPorEndereco(txtBuscaPessoa.getText());
                        frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Pessoa");
                    } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }
                }else if (cmbBuscaPessoa.getSelectedIndex() == 4) {
                     //Busca por bairro
                    try {
                        List lista = clientePessoaFisicaAction.buscarListaPorBairro(txtBuscaPessoa.getText());
                        frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Pessoa");
                    } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }
                }else if (cmbBuscaPessoa.getSelectedIndex() == 5) {
                     //Busca por telefone comercial
                    try {
                        List lista = clientePessoaFisicaAction.buscarListaPorTelefoneComercial(txtBuscaPessoa.getText());
                        frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Pessoa");
                    } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }
                }else if (jTabbedPane3.getSelectedIndex()== 2) {

                    if(cmbBuscaPessoa.getSelectedIndex() == 6){
                        //Busca por telefone celular
                        try {
                            List lista = clientePessoaFisicaAction.buscarListaPorTelefoneCelular(txtBuscaPessoa.getText());
                            frmBC = new FrmBuscaCliente(lista,frmPrincipal,"Pessoa");
                        } catch (ClienteException ex) {
                            javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                        }
                    }

                }

                if(frmBC != null){
                    frmBC.setResizable(false);
                    frmBC.setAlwaysOnTop(true);
                    frmBC.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    frmBC.setLocationRelativeTo(frmPrincipal);
                    frmBC.setVisible(true);
                }
        }          
            

        private void buscarClienteCondominio(FrmPrincipal formPrincipal){
             BuscaClienteCondominioAction buscaCondominioAction = new BuscaClienteCondominioAction();

                FrmBuscaCliente frmBC = null;

                if(cmbBuscaCondominio.getSelectedIndex()==0){
                    if(!txtBuscaCondominio.getText().equals("")){
                        try {
                            ClienteCondominio clienteCondominio = buscaCondominioAction.getCondominioPorCodigo(Long.parseLong(txtBuscaCondominio.getText()));
                            if(clienteCondominio!=null){
                                popularClienteCondominio(clienteCondominio);
                            }else{
                                javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Não existe nenhum cadastro com o código informado","Atenção - Aviso",1);
                            }
                        } catch (Exception ex) {
                            javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Consulta - Verifique se o campo de busca foi preenchido corretamente! ","Atenção - Aviso",0);
                        }
                    }
                }else if(cmbBuscaCondominio.getSelectedIndex()==1){
                        if(!txtBuscaCondominio.getText().equals("")){
                                try {
                                //Busca por nome
                                List lista = buscaCondominioAction.getListaCondominioPorNome(txtBuscaCondominio.getText());
                                frmBC = new FrmBuscaCliente(lista,formPrincipal,"Condominio");
                            } catch (Exception ex) {
                                javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Consulta - Verifique se o campo de busca foi preenchido corretamente! ","Atenção - Aviso",0);
                            }
                        }
                }else if(cmbBuscaCondominio.getSelectedIndex()==2){
                    if(!txtBuscaCondominio.getText().equals("")){
                        try {
                            //Busca por cnpj
                            ArrayList lista = (ArrayList) buscaCondominioAction.getListaCondominioPorCnpj(txtBuscaCondominio.getText());
                            frmBC = new FrmBuscaCliente(lista,formPrincipal,"Condominio");                            
                        } catch (Exception ex) {
                            javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Consulta - Verifique se o campo de busca foi preenchido corretamente! ","Atenção - Aviso",0);
                        }
                    }
                }else if(cmbBuscaCondominio.getSelectedIndex()==3){
                     if(!txtBuscaCondominio.getText().equals("")){
                                     //Busca por endereço
                            try {
                                ArrayList lista = (ArrayList) buscaCondominioAction.getListaCondominioPorEndereco(txtBuscaCondominio.getText());
                                frmBC = new FrmBuscaCliente(lista,formPrincipal,"Condominio");
                            } catch (Exception ex) {
                                javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Consulta - Verifique se o campo de busca foi preenchido corretamente! ","Atenção - Aviso",0);
                            }
                    }
                }else if (cmbBuscaCondominio.getSelectedIndex() == 4) {
                    if(!txtBuscaCondominio.getText().equals("")){
                         //Busca por bairro
                        try {
                            ArrayList lista = (ArrayList) buscaCondominioAction.getListaCondominioPorBairro(txtBuscaCondominio.getText());
                            frmBC = new FrmBuscaCliente(lista,formPrincipal,"Condominio");
                        } catch (Exception ex) {
                            javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Consulta - Verifique se o campo de busca foi preenchido corretamente! ","Atenção - Aviso",0);
                        }
                    }
                }else if (cmbBuscaCondominio.getSelectedIndex() == 5) {
                    if(!txtBuscaCondominio.getText().equals("")){
                                         //Busca por telefone comercial
                            try {
                                ArrayList lista = (ArrayList) buscaCondominioAction.getListaCondominioPorTelefoneComercial(txtBuscaCondominio.getText());
                                frmBC = new FrmBuscaCliente(lista,formPrincipal,"Condominio");
                            } catch (Exception ex) {
                                javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Consulta - Verifique se o campo de busca foi preenchido corretamente! ","Atenção - Aviso",0);
                            }
                        }
                }

                if(frmBC!= null){
                    frmBC.setResizable(false);
                    frmBC.setAlwaysOnTop(true);
                    frmBC.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    frmBC.setLocationRelativeTo(formPrincipal);
                    frmBC.setVisible(true);
                }
        }
        
        /*Busca o ClientePessoa para que seja vinculado a um pedido*/
        private void buscarClientePedido(FrmPrincipal frmPrincipal){

            FrmBuscaCliente frmBC = null;
            
            try {                
                //Busca por nome
                List<ClientePessoa> lista = ClientePessoaAction.getInstance().buscarTodos();
                
                pedidoAction.setPedido(new Pedido());
                frmBC = new FrmBuscaCliente(lista,frmPrincipal,"ClientePedido", pedidoAction.getPedido());                        
            } catch (ClienteException ex) {
               javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
            }

            if(frmBC != null){
                frmBC.setResizable(false);
                frmBC.setAlwaysOnTop(true);
                frmBC.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frmBC.setLocationRelativeTo(frmPrincipal);
                frmBC.setVisible(true);
            }
        }
                
       private void atualizarClienteCondominio(FrmPrincipal formPrincipal){
                    Object[] o = {"SIM", "NÃO"};

                if(!txtCodigoCliente.getText().equals("")){

                  int resultado = JOptionPane.showOptionDialog(formPrincipal.getContentPane(),
                  "Tem certeza que deseja alterar os dados do cliente ?", "Atenção", 2, 2, null, o, null);

                  if(resultado == 0){
                      try {
                          ClienteCondominio cc = clienteCondominioAction.buscarPorId(Long.parseLong(txtCodigoCliente.getText()));                    
                          cc.setNome(txtNome.getText());
                          cc.setEndereco(txtEndereco.getText());
                          cc.setBairro(txtBairro.getText());
                          cc.setCidade(txtCidade.getText());
                          cc.setCep(txtCep.getText());
                          cc.setUf((String) cmbUf.getSelectedItem());
                          cc.setCnpj(txtCnpj.getText());
                          cc.setIe(txtIe.getText());
                          cc.setZelador(txtNomeZelador.getText());
                          cc.setTelefoneZelador(txtTelZelador.getText());
                          cc.setSindico(txtNomeSindico.getText());
                          cc.setTelefoneSindico(txtTelSindico.getText());
                          cc.setTelefoneComercial(txtTelComercial.getText());
                          cc.setTelefoneFax(txtTelFax.getText());
                          cc.setEmail(txtEmailCondominio.getText());

                          cc.setObs(txtObs.getText());

                          if(!txtBlocos.getText().equals("")){
                              try{
                                  cc.setNumeroBlocos(Integer.parseInt(txtBlocos.getText()));
                              } catch (Exception e) {
                                  javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Alteração - O Campo Numero de Blocos deve ser preenchido corretamente! ","Atenção - Aviso",0);
                              }
                          }

                          if(!txtAndares.getText().equals("")){
                              try{
                                  cc.setNumeroAndares(Integer.parseInt(txtAndares.getText()));
                              } catch (Exception e) {
                                  javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Alteração - O Campo Numero de Andares deve ser preenchido corretamente! ","Atenção - Aviso",0);
                              }
                          }

                          if(!txtAndares.getText().equals("")){
                              try{
                                  cc.setNumeroApAndar(Integer.parseInt(txtApAndar.getText()));
                              } catch (Exception e) {
                                  javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Alteração - O Campo Ap por Andar deve ser preenchido corretamente! ","Atenção - Aviso",0);
                              }
                          }

                          if(!txtQuartos.getText().equals("")){
                              try{
                                  cc.setNumeroQuartos(Integer.parseInt(txtQuartos.getText()));
                              } catch (Exception e) {
                                  javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Alteração - O Campo Quartos deve ser preenchido corretamente! ","Atenção - Aviso",0);
                              }
                          }

                              

                          /*Valida os campos e altera o cliente*/
                          if(isCamposClienteCondominioValidos(formPrincipal)){
                                  try {

                                               /*Altera o cliente e caso o objeto cliente tenha o mesmo NOME, CNPJ OU IE
                                               já cadastrado no banco, entrará no cacth já que esses campos sao
                                               UNIQUE na base de dados*/
                                              clienteCondominioAction.atualizar(cc);
                                              javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Cliente alterado com sucesso!","Aviso",1);

                                  } catch (ClienteException ex) {
                                      javax.swing.JOptionPane.showMessageDialog(formPrincipal, ex.getMensagemErro() ,"Atenção - Erro",0);
                                  }
                             }
                      } catch (ClienteException ex) {
                          Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                          javax.swing.JOptionPane.showMessageDialog(formPrincipal, ex.getMensagemErro() ,"Atenção - Erro",0);
                      }
                  }
                }else{
                    javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Alteração - O Campo Codigo não foi informado. Você deve realizar uma busca primeiramente e selecionar o Cliente que deseja atualizar!","Atenção - Aviso",0);
                }

           
       }      
        
        
        private void salvarClienteCondominio(FrmPrincipal formPrincipal){
            
                ClienteCondominio cc = new ClienteCondominio();
                cc.setNome(txtNome.getText());
                cc.setEndereco(txtEndereco.getText());
                cc.setBairro(txtBairro.getText());
                cc.setCidade(txtCidade.getText());
                cc.setCep(txtCep.getText());
                cc.setUf((String) cmbUf.getSelectedItem());
                cc.setCnpj(txtCnpj.getText());
                cc.setIe(txtIe.getText());
                cc.setZelador(txtNomeZelador.getText());
                cc.setTelefoneZelador(txtTelZelador.getText());
                cc.setSindico(txtNomeSindico.getText());
                cc.setTelefoneSindico(txtTelSindico.getText());
                cc.setTelefoneComercial(txtTelComercial.getText());
                cc.setTelefoneFax(txtTelFax.getText());
                cc.setObs(txtObs.getText());
                cc.setEmail(txtEmailCondominio.getText());
                cc.setTipoCliente(TipoCliente.CONDOMINIO);

                if(!txtBlocos.getText().equals("")){
                    try{
                        cc.setNumeroBlocos(Integer.parseInt(txtBlocos.getText()));
                    } catch (Exception e) {
                        javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Inclusão - O Campo Numero de Blocos deve ser preenchido corretamente! ","Atenção - Aviso",0);
                    }
                }

                if(!txtAndares.getText().equals("")){
                    try{
                        cc.setNumeroAndares(Integer.parseInt(txtAndares.getText()));
                    } catch (Exception e) {
                        javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Inclusão - O Campo Numero de Andares deve ser preenchido corretamente! ","Atenção - Aviso",0);
                    }
                }

                if(!txtAndares.getText().equals("")){
                    try{
                        cc.setNumeroApAndar(Integer.parseInt(txtAndares.getText()));
                    } catch (Exception e) {
                        javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Inclusão - O Campo Ap por Andar deve ser preenchido corretamente! ","Atenção - Aviso",0);
                    }
                }

                if(!txtQuartos.getText().equals("")){
                    try{
                        cc.setNumeroQuartos(Integer.parseInt(txtQuartos.getText()));
                    } catch (Exception e) {
                        javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Inclusão - O Campo Quartos deve ser preenchido corretamente! ","Atenção - Aviso",0);
                    }
                }


                /*Valida os campos e grava o cliente*/
                if(isCamposClienteCondominioValidos(formPrincipal)){
                        try {
                                if(clienteCondominioAction.isNomeExistente(cc)){
                                    javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Inclusão - Condominio já cadastrado com o mesmo Nome! ","Atenção - Aviso",0);
                                }else{
                                     /*Cadastra o cliente e caso o objeto cliente tenha o mesmo NOME, CNPJ OU IE
                                     já cadastrado no banco, entrará no cacth já que esses campos sao
                                     UNIQUE na base de dados*/
                                    clienteCondominioAction.salvar(cc);
                                    javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Cliente cadastrado com sucesso!","Aviso",1);
                                    txtCodigoCliente.setText(String.valueOf(clienteCondominioAction.buscarPorId(cc.getIdCliente())));
                                }

                        } catch (Exception ex) {
                            javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Inclusão - Verifique se os campos foram preenchidos corretamente! ","Atenção - Aviso",0);
                            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   }
            
        }
        
        
        private void removerClienteCondominio(FrmPrincipal formPrincipal){
            
                    Object[] o = {"SIM", "NÃO"};

                if(!txtCodigoCliente.getText().equals("")){
                    int resultado = JOptionPane.showOptionDialog(formPrincipal.getContentPane(),
                  "Tem certeza que deseja excluir o cliente ?", "Atenção", 2, 2, null, o, null);

                    if(resultado == 0){
                        try {
                            formPrincipal.clienteCondominioAction.deletar(clienteCondominio);
                            javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Cliente excluído com sucesso!","Aviso",1);
                            limparCamposTelaClienteCondominio();                   
                        } catch (ClienteException ex) {
                            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            javax.swing.JOptionPane.showMessageDialog(formPrincipal, ex.getMensagemErro() ,"Atenção - Erro",0);
                        }
                    }
                }else{
                    javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na Exclusão - O Campo Codigo não foi informado. Você deve realizar uma busca e selecionar o Cliente que deseja excluir!","Atenção - Erro",0);
                }            
        }
        
        private boolean isCamposClienteCondominioValidos(FrmPrincipal formPrincipal){

            boolean retorno = true;

            if(txtNome.getText().equals("")){
                javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na inclusão do cliente. O Campo NOME deve ser preenchido","Atenção - Aviso",0);
                retorno = false;
            }else if(txtEndereco.getText().equals("")){
                javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na inclusão do cliente. O Campo ENDEREÇO deve ser preenchido","Atenção - Aviso",0);
                retorno = false;
            }else if(txtBairro.getText().equals("")){
                javax.swing.JOptionPane.showMessageDialog(formPrincipal, "Falha na inclusão do cliente. O Campo BAIRRO deve ser preenchido","Atenção - Aviso",0);
                retorno = false;
            }

            return retorno;
        }
        
            private void limparCamposTelaClienteCondominio(){
                txtCodigoCliente.setText("");
                txtNome.setText("");
                txtCnpj.setText("");
                txtIe.setText("");
                txtTelComercial.setText("");
                txtTelFax.setText("");
                txtEndereco.setText("");
                txtBairro.setText("");
                txtCidade.setText("");
                txtCep.setText("");
                txtBlocos.setText("");
                txtAndares.setText("");
                txtApAndar.setText("");
                txtQuartos.setText("");
                cmbUf.setSelectedItem("RJ");
                txtNomeSindico.setText("");
                txtTelSindico.setText("");
                txtNomeZelador.setText("");
                txtTelZelador.setText("");
                txtObs.setText("");
                txtBuscaCondominio.setText("");
                cmbBuscaCondominio.setSelectedIndex(0);
                txtEmailCondominio.setText("");
            }

        private void salvarClientePessoaFisica(FrmPrincipal frmPrincipal) {
            
            ClientePessoaFisica clientePessoaFisica = new ClientePessoaFisica();
            clientePessoaFisica.setNome(txtNomePessoa.getText());
            clientePessoaFisica.setEndereco(txtEnderecoPessoa.getText());
            clientePessoaFisica.setBairro(txtBairroPessoa.getText());
            clientePessoaFisica.setCidade(txtCidadePessoa.getText());
            clientePessoaFisica.setCep(txtCepPessoa.getText());
            clientePessoaFisica.setUf((String) cmbUfPessoa.getSelectedItem());
            clientePessoaFisica.setCpf(txtCpfPessoa.getText());
            clientePessoaFisica.setRg(txtRgPessoa.getText());
            clientePessoaFisica.setTelefoneResidencial(txtTelResidencialPessoa.getText());
            clientePessoaFisica.setTelefoneCelular(txtTelCelularPessoa.getText());
            clientePessoaFisica.setObs(txtObsPessoa.getText());
            clientePessoaFisica.setContato(txtContatoPessoa.getText());
            clientePessoaFisica.setTelefoneContato(txtTelContatoPessoa.getText());
            clientePessoaFisica.setEmail(txtEmailPessoa.getText());
            clientePessoaFisica.setTipoCliente(TipoCliente.FISICA);


            /*Valida os campos e grava o cliente*/
            if(frmPrincipal.isCamposClientePessoaValidos()){
                    try {
                            if(clientePessoaFisicaAction.isNomeExistente(clientePessoaFisica)){
                                javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha na Inclusão - Cliente já cadastrado com o mesmo Nome! ","Atenção - Aviso",0);
                            }else{
                                 /*Cadastra o cliente e caso o objeto cliente tenha o mesmo NOME, CNPJ OU IE
                                 já cadastrado no banco, entrará no cacth já que esses campos sao
                                 UNIQUE na base de dados*/
                                clientePessoaFisicaAction.salvar(clientePessoaFisica);
                                javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Cliente cadastrado com sucesso!","Aviso",1);
                                txtCodigoPessoa.setText(String.valueOf(clientePessoaFisica.getIdCliente()));
                            }

                    } catch (Exception ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha na Inclusão - Verifique se os campos foram preenchidos corretamente! ","Atenção - Aviso",0);
                        Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
        }

        private void atualizarClientePessoaFisica(FrmPrincipal frmPrincipal) {
                    
                Object[] o = {"SIM", "NÃO"};

                if(!txtCodigoPessoa.getText().equals("")){

                  int resultado = JOptionPane.showOptionDialog(frmPrincipal.getContentPane(),
                  "Tem certeza que deseja alterar os dados do cliente ?", "Atenção", 2, 2, null, o, null);

                  if(resultado == 0){
                          ClientePessoaFisica cp = frmPrincipal.clientePessoa;                           
                          cp.setNome(txtNomePessoa.getText());
                          cp.setEndereco(txtEnderecoPessoa.getText());
                          cp.setBairro(txtBairroPessoa.getText());
                          cp.setCidade(txtCidadePessoa.getText());
                          cp.setCep(txtCepPessoa.getText());
                          cp.setUf((String) cmbUfPessoa.getSelectedItem());
                          cp.setCpf(txtCpfPessoa.getText());
                          cp.setRg(txtRgPessoa.getText());
                          cp.setTelefoneResidencial(txtTelResidencialPessoa.getText());
                          cp.setTelefoneCelular(txtTelCelularPessoa.getText());
                          cp.setObs(txtObsPessoa.getText());
                          cp.setContato(txtContatoPessoa.getText());
                          cp.setTelefoneContato(txtTelContatoPessoa.getText());
                          cp.setEmail(txtEmailPessoa.getText());

                          if(frmPrincipal.isCamposClientePessoaValidos()){
                             try {
                                 /*Altera o cliente e caso o objeto cliente tenha o mesmo NOME, CPF OU RG
                                               já cadastrado no banco, entrará no cacth já que esses campos sao
                                               UNIQUE nare base de dados*/
                                              clientePessoaAction.alterar(cp);
                                              javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Cliente alterado com sucesso!","Aviso",1);
                                              frmPrincipal.limparCamposTelaClientePessoa();

                                  } catch (ClienteException ex) {
                                      javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro() ,"Atenção - Aviso",0);
                                  }
                          }                      
                      }
                }else{
                    javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha na Alteração - O Campo Codigo não foi informado. Você deve realizar uma busca primeiramente!","Atenção - Aviso",0);
                }
        }

        private void removerClientePessoaFisica(FrmPrincipal frmPrincipal) {
                    Object[] o = {"SIM", "NÃO"};

                if(!txtCodigoPessoa.getText().equals("")){
                    int resultado = JOptionPane.showOptionDialog(frmPrincipal.getContentPane(),
                  "Tem certeza que deseja excluir o cliente ?", "Atenção", 2, 2, null, o, null);

                    if(resultado == 0){
                        try {
                            frmPrincipal.clientePessoaAction.remover(clientePessoa);
                            javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Cliente excluído com sucesso!","Aviso",1);
                            frmPrincipal.limparCamposTelaClientePessoa();
                        } catch (ClienteException ex) {
                            javax.swing.JOptionPane.showMessageDialog(frmPrincipal,  ex.getMensagemErro(),"Atenção - Aviso",0);
                        }
                    }
                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha na Exclusão - O Campo Codigo não foi informado. Você deve realizar uma busca primeiramente!","Atenção - Aviso",0);
                }
        }

        private void salvarClientePessoaJuridica(FrmPrincipal frmPrincipal) {
            
                    ClientePessoaJuridica clientePessoaJuridica = new ClientePessoaJuridica();
                    clientePessoaJuridica.setNome(txtNomeEmpresa.getText());
                    clientePessoaJuridica.setEndereco(txtEnderecoEmpresa.getText());
                    clientePessoaJuridica.setBairro(txtBairroEmpresa.getText());
                    clientePessoaJuridica.setCidade(txtCidadeEmpresa.getText());
                    clientePessoaJuridica.setCep(txtCepEmpresa.getText());
                    clientePessoaJuridica.setUf((String) cmbUfEmpresa.getSelectedItem());
                    clientePessoaJuridica.setCnpj(txtCnpjEmpresa.getText());
                    clientePessoaJuridica.setIe(txtIeEmpresa.getText());
                    clientePessoaJuridica.setTelefoneComercial(txtTelComercialEmpresa.getText());
                    clientePessoaJuridica.setTelefoneFax(txtTelFaxEmpresa.getText());
                    clientePessoaJuridica.setObs(txtObsEmpresa.getText());
                    clientePessoaJuridica.setNomeResponsavel(txtNomeResponsavelEmpresa.getText());
                    clientePessoaJuridica.setTelefoneResponsavel(txtTelResponsavelEmpresa.getText());
                    clientePessoaJuridica.setEmail(txtEmailEmpresa.getText());
                    clientePessoaJuridica.setTipoCliente(TipoCliente.JURIDICA);

                    /*Valida os campos e grava o cliente*/
                    if(frmPrincipal.isCamposClienteEmpresaValidos()){
                            try {
                                    if(clienteEmpresaAction.isNomeExistente(clientePessoaJuridica)){
                                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha na Inclusão - Cliente já cadastrado com o mesmo Nome! ","Atenção - Aviso",0);
                                    }else{
                                         /*Cadastra o cliente e caso o objeto cliente tenha o mesmo NOME, CNPJ OU IE
                                         já cadastrado no banco, entrará no cacth já que esses campos sao
                                         UNIQUE na base de dados*/
                                        clienteEmpresaAction.salvar(clientePessoaJuridica);
                                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Cliente cadastrado com sucesso!","Aviso",1);
                                        txtCodigoEmpresa.setText(String.valueOf(clientePessoaJuridica.getIdCliente()));
                                    }

                            } catch (Exception ex) {
                                javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha na Inclusão - Verifique se os campos foram preenchidos corretamente! ","Atenção - Aviso",0);
                                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                       }
        }

        private void alterarClientePessoaJuridica(FrmPrincipal frmPrincipal) {
                    
                Object[] o = {"SIM", "NÃO"};

                if(!txtCodigoEmpresa.getText().equals("")){

                  int resultado = JOptionPane.showOptionDialog(frmPrincipal.getContentPane(),
                  "Tem certeza que deseja alterar os dados do cliente ?", "Atenção", 2, 2, null, o, null);

                  if(resultado == 0){
                    ClientePessoaJuridica clienteEmpresa = frmPrincipal.clientePessoaJuridica;
                    clienteEmpresa.setIdCliente(Long.parseLong(txtCodigoEmpresa.getText()));
                    clienteEmpresa.setNome(txtNomeEmpresa.getText());
                    clienteEmpresa.setEndereco(txtEnderecoEmpresa.getText());
                    clienteEmpresa.setBairro(txtBairroEmpresa.getText());
                    clienteEmpresa.setCidade(txtCidadeEmpresa.getText());
                    clienteEmpresa.setCep(txtCepEmpresa.getText());
                    clienteEmpresa.setUf((String) cmbUfEmpresa.getSelectedItem());
                    clienteEmpresa.setCnpj(txtCnpjEmpresa.getText());
                    clienteEmpresa.setIe(txtIeEmpresa.getText());
                    clienteEmpresa.setTelefoneComercial(txtTelComercialEmpresa.getText());
                    clienteEmpresa.setTelefoneFax(txtTelFaxEmpresa.getText());
                    clienteEmpresa.setObs(txtObsEmpresa.getText());
                    clienteEmpresa.setNomeResponsavel(txtNomeResponsavelEmpresa.getText());
                    clienteEmpresa.setTelefoneResponsavel(txtTelResponsavelEmpresa.getText());
                    clienteEmpresa.setEmail(txtEmailEmpresa.getText());

                    if(frmPrincipal.isCamposClienteEmpresaValidos()){
                       try {
                           /*Altera o cliente e caso o objeto cliente tenha o mesmo NOME, CNPJ OU IE
                                         já cadastrado no banco, entrará no cacth já que esses campos sao
                                         UNIQUE na base de dados*/
                                        clienteEmpresaAction.alterar(clienteEmpresa);
                                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Cliente alterado com sucesso!","Aviso",1);

                            } catch (Exception ex) {
                                javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha na Alteração - Verifique se os campos foram preenchidos corretamente! ","Atenção - Aviso",0);
                            }
                    }
                  }
                }else{
                    javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha na Alteração - O Campo Codigo não foi informado. Você deve realizar uma busca primeiramente!","Atenção - Aviso",0);
                }            
        }        

        private void removerClientePessoaJuridica(FrmPrincipal frmPrincipal) {
                Object[] o = {
            "SIM", "NÃO"};

            if(!txtCodigoEmpresa.getText().equals("")){
                int resultado = JOptionPane.showOptionDialog(frmPrincipal.getContentPane(),
              "Tem certeza que deseja excluir o cliente ?", "Atenção", 2, 2, null, o, null);

                if(resultado == 0){
                    try {
                        frmPrincipal.clienteEmpresaAction.remover(clientePessoaJuridica);
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Cliente excluído com sucesso!","Aviso",1);
                        frmPrincipal.limparCamposTelaClienteEmpresa();
                    } catch (ClienteException ex) {
                        javax.swing.JOptionPane.showMessageDialog(frmPrincipal, ex.getMensagemErro(),"Atenção - Aviso",0);
                    }
                }
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(frmPrincipal, "Falha na Exclusão - O Campo Codigo não foi informado. Você deve realizar uma busca primeiramente!","Atenção - Aviso",0);
            }
        }
    }


    /************************ PedidoForm *************************/

    class PedidoForm {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        
        private void preparaCamposNovoPedido(){
                //Habilita Campos Tela Pedido
            //txtNomeClientePedido.setEnabled(true);
//            btnBuscaClientePedido.setEnabled(true);
//            txtEnderecoPedido.setEnabled(true);
//            cmbUfPedido.setEnabled(true);
//            txtBairroPedido.setEnabled(true);
//            txtCidadePedido.setEnabled(true);
//            txtContatoPedido.setEnabled(true);
//            txtTelefoneContatoPedido.setEnabled(true);
//            txtObsPedido.setEnabled(true);
//            btnAddItemServico.setEnabled(true);
//            btnRemoveItemServico.setEnabled(true);
//            btnFormaPagamento.setEnabled(true);
//            btnIncluirPedido.setEnabled(false);
//            btnAlterarPedido.setEnabled(false);
//            btnExcluirPedido.setEnabled(false);
//            btnFormaPagamento.setEnabled(false);
//            btnAddItemServico.setEnabled(false);
//            btnRemoveItemServico.setEnabled(false);
//            btnVisualizaDadosCliente.setEnabled(false);
            
            cmbStatusPedido.setSelectedIndex(0);
//            txtDataAgendamento.setEnabled(true);
            //txtTelefoneClientePedido.setEnabled(true);            
            lbDataExecucao.setVisible(false);
            lbHoraExecucao.setVisible(false);
            txtDataExecucao.setVisible(false);
            cmbHoraExecucaoPedido.setVisible(false);
            //btnBuscaClientePorTelefoneRes.setEnabled(true);            
            //Limpa Campos
            txtNumeroPedido.setText("");
            txtCodigoBuscaPedido.setText("");
            txtNomeClientePedido.setText("");
//            txtEnderecoPedido.setText("");
//            txtBairroPedido.setText("");
//            txtCidadePedido.setText("");
//            txtContatoPedido.setText("");
//            txtTelefoneContatoPedido.setText("");
//            txtTelefoneClientePedido.setText("");            
            txtObsPedido.setText("");
            ((ServicoPedidoTableModel) tableServicosPedido.getModel()).limpar();
            //Formata valor do Total do Pedido
            txtTotalPedido.setText(new Util().formataMoeda(0.0));
            //Aplica Data do Dia para o novo Pedido
            txtDataCadastroPedido.setText(new Util().getDataHoje());
            txtDataAgendamento.setValue(new Date(System.currentTimeMillis()));
        }


        //Aplica permissoes de visible true ou false aos botoes da tela de Pedidos
        private void aplicarEstadoCommandos(String comando){

//            if(comando.equals("incluir")){
//                btnNovoPedido.setEnabled(true);
//                btnIncluirPedido.setEnabled(false);
//                btnAlterarPedido.setEnabled(true);
//                btnExcluirPedido.setEnabled(true);
//            }else if(comando.equals("novo")){
//                btnNovoPedido.setEnabled(true);
//                btnIncluirPedido.setEnabled(true);
//                btnAlterarPedido.setEnabled(false);
//                btnExcluirPedido.setEnabled(false);
//            }else if(comando.equals("alterar")){
//                btnNovoPedido.setEnabled(true);
//                btnIncluirPedido.setEnabled(false);
//                btnAlterarPedido.setEnabled(true);
//                btnExcluirPedido.setEnabled(true);
//            }else if(comando.equals("excluir")){
//                btnNovoPedido.setEnabled(true);
//                btnIncluirPedido.setEnabled(false);
//                btnAlterarPedido.setEnabled(false);
//                btnExcluirPedido.setEnabled(false);
//            }else if(comando.equals("consultar")){
//                btnNovoPedido.setEnabled(true);
//                btnIncluirPedido.setEnabled(false);
//                btnAlterarPedido.setEnabled(true);
//                btnExcluirPedido.setEnabled(true);
//            }
       }


        private boolean isFormularioPedidoValido(Pedido pedido) throws ParseException{

            boolean retorno = false;
            
            Calendar dataAgendamento = Calendar.getInstance();
            dataAgendamento.setTime(format.parse(pedido.getDataAgendamento()));
            Calendar dataExecucao = Calendar.getInstance();
            if(pedido.getDataExecucao()==null){
                dataExecucao.setTime(new Date());
            }else{
                dataExecucao.setTime(format.parse(pedido.getDataExecucao()));
            }
            

             if(pedido.getItensPedido().isEmpty()){
                    javax.swing.JOptionPane.showMessageDialog(null,"Falha na Operação. Não existem serviços associados a este pedido.","Atenção",2);
            }else if(pedido.getCliente()== null && txtNomeClientePedido.getText().equals("")){
                    javax.swing.JOptionPane.showMessageDialog(null,"Falha na Operação. Você deve associar este pedido a um cliente.","Atenção",2);
            }else if(txtDataAgendamento.getValue()== null){
                javax.swing.JOptionPane.showMessageDialog(null,"Falha na Operação. A data do agendamento deve ser informada.","Atenção",2);
            }else if(getPedidoAction().getPedido().getCliente()==null){
                javax.swing.JOptionPane.showMessageDialog(null,"Falha na Operação. O Cliente informado não possui cadastro no sistema.","Atenção",2);
            }else if(pedido.getStatus().equalsIgnoreCase("Fechado") && dataExecucao.before(dataAgendamento)){
                dataExecucao.getTime();
                dataAgendamento.getTime();
                javax.swing.JOptionPane.showMessageDialog(null,"Falha na Operação. A data da execução do pedido nao pode ser anterior a data do seu agendamento.","Atenção",2);
                    retorno = false;
                  
            }else if((pedido.getStatus().equalsIgnoreCase("Fechado")) && (dataExecucao.equals(dataAgendamento) && (pedido.getHoraAgendamento().getTime()== pedido.getHoraExecucao().getTime()))){
                    javax.swing.JOptionPane.showMessageDialog(null,"Falha na Operação. A data e hora da execução do pedido não pode ser igual data e hora do agendamento.","Atenção",2);
                    retorno = false;
            }else if((!pedido.getStatus().equalsIgnoreCase("Fechado")) && (dataAgendamento.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || dataAgendamento.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)){
                int r = javax.swing.JOptionPane.showConfirmDialog(rootPane, "A data de agendamento selecionada refere-se a um dia de final de semana. Deseja prosseguir?","Aviso",1);
                if(r==0){
                    retorno = true;
                }else{
                    retorno = false;
                }
            }else if(txtObsPedido.getText()== null || txtObsPedido.getText().equals("") ){
                txtObsPedido.setText("");
                retorno = true;
            }else{
                retorno = true;
            }
            return retorno;
        }


        private void incluirPedido(){

            try {
                Pedido p = getPedidoAction().getPedido();                
                p.setStatus((String) cmbStatusPedido.getSelectedItem());
                p.setDataAgendamento(String.valueOf(format.format(txtDataAgendamento.getValue())));
                p.setHoraAgendamento(Time.valueOf(cmbHoraAgendamentoPedido.getSelectedItem().toString()));
                p.setObservacao(txtObsPedido.getText());               

                
                if(getPedidoAction().getPedido().getIdPedido() != null){
                    javax.swing.JOptionPane.showMessageDialog(null,"Pedido já existe na base de dados. Não é possível incluí-lo novamente.","Atenção",2);
                }else  if(isFormularioPedidoValido(p)){
                       
                        double totalPedido = new Util().converteValorTextoDouble(txtTotalPedido.getText());

                        if(getPedidoAction().getPedido().getFormaPagamento() != null){
                            getPedidoAction().getPedido().getFormaPagamento().setValorTotal(totalPedido);
                        }

                        if(getPedidoAction().getFormaPagamento()== null){
                                 int r = javax.swing.JOptionPane.showConfirmDialog(null,"A forma de pagamento não foi definida. O pedido será gravado como pagamento à vista. Confirma a operação ?","Atenção", 0);
                                    if(r == 0){
                                        // Grava os dados do pedido na base
                                        getPedidoAction().salvar(getPedidoAction().getPedido());
                                        txtNumeroPedido.setText(String.valueOf(getPedidoAction().getPedido().getIdPedido()));
                                        javax.swing.JOptionPane.showMessageDialog(null,"Pedido incluído com sucesso!","Aviso",1);
                                        aplicarEstadoCommandos("incluir");
                                    }
                        }else if(!getPedidoAction().getFormaPagamento().getParcelas().isEmpty()){
                                getPedidoAction().salvar(getPedidoAction().getPedido());
                                txtNumeroPedido.setText(String.valueOf(getPedidoAction().getPedido().getIdPedido()));
                                javax.swing.JOptionPane.showMessageDialog(null,"Pedido incluído com sucesso!","Aviso",1);
                                new PedidoForm().aplicarEstadoCommandos("incluir");
                        }
                  }
            } catch (Exception ex) {
                if(ex.getMessage().indexOf("Duplicate")!=-1){
                    javax.swing.JOptionPane.showMessageDialog(null,"Falha na Operação. Pedido já cadastrado na base de dados","Atenção",2);
                }else{
                    javax.swing.JOptionPane.showMessageDialog(null,"Falha na Operação. O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                }
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void consultarPedido(JFrame frame){
            
            FrmBuscaPedido frmBP;
            Long codigoPedido = null;
                if(cmbBuscaPedido.getSelectedItem().equals("Código")){
                    //Obtem o codigo do pedido para busca

                        try{
                          codigoPedido = Long.parseLong(txtCodigoBuscaPedido.getText());
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(rootPane, "Falha na consulta. O Numero de Pedido informado nao é válido.","Atenção",2);
                        }
                    
                    try {
                        Pedido pedido = getPedidoAction().buscarPorId(codigoPedido);
                        
                        getPedidoAction().setPedido(pedido);

                        if(getPedidoAction().getPedido() == null){
                            JOptionPane.showMessageDialog(rootPane, "Pedido não encontrado!","Atenção",1);
                        }else{
                            new PedidoForm().preparaCamposNovoPedido();
                            populaPedidoConsultado(getPedidoAction().getPedido());
                        }

                    } catch (NumberFormatException nex) {
                        JOptionPane.showMessageDialog(rootPane, "Falha na consulta. Verifique o código informado.","Atenção",2);
                        Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, nex);
                    } catch (PedidoException ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMensagemErro(),"Atenção - Erro",2);
                        Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(cmbBuscaPedido.getSelectedItem().equals("Cliente")){
                    //Obtem o pedido pelo nome do cliente
                    String nomeCliente = txtCodigoBuscaPedido.getText();
                    
                    try {
                        List lista = pedidoAction.buscarPedidoPorNomeCliente(nomeCliente);
                        frmBP = new FrmBuscaPedido(lista, (FrmPrincipal) frame,getPedidoAction().getPedido());
                        frmBP.setResizable(false);
                        frmBP.setAlwaysOnTop(true);
                        frmBP.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        frmBP.setLocationRelativeTo(rootPane);
                        frmBP.setVisible(true);

                         //this.carregaPedidoTab(null);
//                        btnIncluirPedido.setEnabled(false);
//                        btnAlterarPedido.setEnabled(true);
//                        btnExcluirPedido.setEnabled(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Falha na consulta.O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                        Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }

        private void alterarPedido(Pedido p){
            try {                
                p.setStatus((String) cmbStatusPedido.getSelectedItem());
                p.setDataAgendamento(String.valueOf(format.format(txtDataAgendamento.getValue())));
                p.setHoraAgendamento(Time.valueOf(cmbHoraAgendamentoPedido.getSelectedItem().toString()));
                p.setObservacao(txtObsPedido.getText());                

                if(p.getStatus().equalsIgnoreCase("Fechado")){
                    p.setDataExecucao(String.valueOf(format.format(txtDataExecucao.getValue())));
                    p.setHoraExecucao(Time.valueOf(String.valueOf(cmbHoraExecucaoPedido.getSelectedItem())));
                }else{
                    p.setDataExecucao(null);
                    p.setHoraExecucao(null);
                }


                if(isFormularioPedidoValido(p)){

                      
                        if(getPedidoAction().getPedido().getFormaPagamento() != null){
                            double totalPedido = new Util().converteValorTextoDouble(txtTotalPedido.getText());
                            getPedidoAction().getPedido().getFormaPagamento().setValorTotal(totalPedido);
                        }

                        p.setValorPedido(new Util().converteValorTextoDouble(txtTotalPedido.getText()));

                        if(getPedidoAction().getFormaPagamento()== null){
                                 int r = javax.swing.JOptionPane.showConfirmDialog(rootPane,"A forma de pagamento não foi definida. O pedido será gravado como pagamento à vista. Confirma a operação ?","Atenção", 0);
                                    if(r == 0){
                                        // Grava os dados do pedido na base
                                        getPedidoAction().atualizar(getPedidoAction().getPedido());
                                        javax.swing.JOptionPane.showMessageDialog(rootPane,"Pedido alterado com sucesso!","Aviso",1);
                                    }
                            }else if(!getPedidoAction().getFormaPagamento().getParcelas().isEmpty()){
                                int r = javax.swing.JOptionPane.showConfirmDialog(rootPane,"Confirma a alteração do pedido ?","Atenção", 0);
                                    if(r == 0){
                                        // Grava os dados do pedido na base
                                        getPedidoAction().atualizar(getPedidoAction().getPedido());
                                        javax.swing.JOptionPane.showMessageDialog(rootPane,"Pedido alterado com sucesso!","Aviso",1);
                                    }
                            }else{
                                int r = javax.swing.JOptionPane.showConfirmDialog(rootPane,"A forma de pagamento não foi definida. O pedido será gravado como pagamento à vista. Confirma a operação ?","Atenção", 0);
                                    if(r == 0){
                                        // Grava os dados do pedido na base
                                        getPedidoAction().atualizar(getPedidoAction().getPedido());
                                        javax.swing.JOptionPane.showMessageDialog(rootPane,"Pedido alterado com sucesso!","Aviso",1);
                                    }
                            }
                  }
            } catch (Exception ex) {
                if(ex.getMessage().indexOf("Duplicate")!=-1){
                    javax.swing.JOptionPane.showMessageDialog(rootPane,"Falha na Operação. Pedido já cadastrado na base de dados","Atenção",2);
                }else{
                    javax.swing.JOptionPane.showMessageDialog(rootPane,"Falha na Operação. O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                }
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class OsForm {

        private void limparCamposFormulario(){

            new Util().carregaComboUf(cmbUfOrdemServico);

            txtCodigoOsPadrao.setText("");
            txtDataEmissaoOsPadrao.setText("");
            txtHoraEmissaoOsPadrao.setText("");
            cmbStatusOsPadrao.setSelectedIndex(0);
            txtUsuarioOsPadrao.setText("");
            txtValorOsPadrao.setText("");
            txtNumeroPedidoOsPadrao.setText("");
            txtCodigoClienteOsPadrao.setText("");
            txtNomeClienteOsPadrao.setText("");
            txtTelefoneContatoOsPadrao.setText("");
            txtNomeContatoOsPadrao.setText("");
            txtEnderecoOsPadrao.setText("");
            txtBairroOsPadrao.setText("");
            txtCidadeOsPadrao.setText("");
            txtObsOsPadrao.setText("");

            txtDataExecucaoOsPadrao.setValue(null);
            txtHoraExecucaoOsPadrao.setText("");

            if(!(tabelaServicosOsPadrao.getModel() instanceof OrdemServicoTableModel)){
                try {
                    getOrdemServicoAction().aplicarModelOrdemServico(tabelaServicosOsPadrao);
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(rootPane,"Falha ao carregar modelo de tabela ordem serviço ","Atenção - Erro",1);
                    Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            ((OrdemServicoTableModel)(tabelaServicosOsPadrao.getModel())).limpar();

        }

        public void popularOs(OrdemServicoPadrao os){

                       if(os.getPedido()!=null){
                           try{
                                //Lista os itens do pedido consultado

                                ((OrdemServicoTableModel)tabelaServicosOsPadrao.getModel()).addListaDeItens((List<ItemPedido>) os.getPedido().getItensPedido());
                                txtNumeroPedidoOsPadrao.setText(String.valueOf(os.getPedido().getIdPedido()));


                                if(os.getIdOrdemServico()!= null && os.getIdOrdemServico()!= 0)
                                     txtCodigoOsPadrao.setText(os.getIdOrdemServico().toString());

                                if(os.getDataEmissao()!= null)
                                    txtDataEmissaoOsPadrao.setText(os.getDataEmissao());

                                if(os.getHoraEmissao()!=null)
                                    txtHoraEmissaoOsPadrao.setText(os.getHoraEmissao().toString());

                                if(os.getStatus()!=null){
                                    if(os.getStatus().equalsIgnoreCase("Fechada") ||
                                            os.getStatus().equalsIgnoreCase("Cancelada")){
                                        cmbStatusOsPadrao.setSelectedItem(os.getStatus());
                                        cmbStatusOsPadrao.setEnabled(false);

                                    }
                                    cmbStatusOsPadrao.setSelectedItem(os.getStatus());
                                    cmbStatusOsPadrao.setEnabled(true);
                                }else{
                                    cmbStatusOsPadrao.setSelectedItem(0);
                                    os.setStatus("Aberta");
                                }

                                if(os.getUsuarioLog()!=null){
                                    txtUsuarioOsPadrao.setText(os.getUsuarioLog());
                                }else{
                                    txtUsuarioOsPadrao.setText(getUsuarioTela().getUsuario());
                                }

                                if(os.getDataExecucao() == null||os.getDataExecucao().equals("")){
                                    txtDataExecucaoOsPadrao.setValue(null);
                                }else{
                                    txtDataExecucaoOsPadrao.setValue(os.getDataExecucao());
                                }

                                if(os.getHoraExecucao()== null){
                                    txtHoraExecucaoOsPadrao.setText("");
                                }else{
                                    txtHoraExecucaoOsPadrao.setText(os.getHoraExecucao().toString());
                                }


                                txtCodigoClienteOsPadrao.setText(String.valueOf(os.getPedido().getCliente().getIdCliente()));
                                txtValorOsPadrao.setText(new Util().formataMoeda(os.getValor()));
                                txtNomeClienteOsPadrao.setText(os.getPedido().getCliente().getNome());
                                txtTelefoneContatoOsPadrao.setText(os.getPedido().getCliente().getTelefoneContato());
                                txtNomeContatoOsPadrao.setText(os.getPedido().getCliente().getNomeContato());
                                txtEnderecoOsPadrao.setText(os.getPedido().getCliente().getEndereco());
                                txtBairroOsPadrao.setText(os.getPedido().getCliente().getBairro());
                                txtCidadeOsPadrao.setText(os.getPedido().getCliente().getCidade());
                                txtObsOsPadrao.setText(os.getObservacao());
                                cmbUfOrdemServico.setSelectedItem(os.getPedido().getCliente().getUf());

                            } catch (Exception ex) {
                                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                           btnIncluirOsPadrao.setEnabled(true);
                           btnAlterarOsPadrao.setEnabled(true);
                           btnExcluirOsPadrao.setEnabled(true);

                       }else if(os.getContrato()!=null){
                                   try{
                                       List itens = null;
                                        //Lista os itens do pedido consultado
                                            if(!(os.getContrato().getItensContratados().isEmpty()) && (os.getContrato().getItensContratados().get(0) instanceof ServicoContrato)){
                                                itens = getOrdemServicoAction().converterItensContratadosToItensOs(os.getContrato().getItensContratados());
                                                ((OrdemServicoTableModel)tabelaServicosOsPadrao.getModel()).addListaDeItens(itens);
                                            }else{
                                                ((OrdemServicoTableModel)tabelaServicosOsPadrao.getModel()).addListaDeItens(os.getContrato().getItensContratados());
                                            }
                                            
                                             
                                             txtNumeroPedidoOsPadrao.setText(os.getContrato().getIdContrato());


                                        if(os.getIdOrdemServico()!= null && os.getIdOrdemServico()!= 0)
                                             txtCodigoOsPadrao.setText(os.getIdOrdemServico().toString());

                                        if(os.getDataEmissao()!= null)
                                            txtDataEmissaoOsPadrao.setText(os.getDataEmissao());

                                        if(os.getHoraEmissao()!=null)
                                            txtHoraEmissaoOsPadrao.setText(os.getHoraEmissao().toString());

                                        if(os.getStatus()!=null){
                                            if(os.getStatus().equalsIgnoreCase("Fechada") ||
                                                    os.getStatus().equalsIgnoreCase("Cancelada")){
                                                cmbStatusOsPadrao.setSelectedItem(os.getStatus());
                                                cmbStatusOsPadrao.setEnabled(false);
                                            }
                                            cmbStatusOsPadrao.setSelectedItem(os.getStatus());
                                            cmbStatusOsPadrao.setEnabled(true);
                                        }else{
                                            cmbStatusOsPadrao.setSelectedItem(0);
                                            os.setStatus("Aberta");
                                        }

                                        if(os.getUsuarioLog()!=null){
                                            txtUsuarioOsPadrao.setText(os.getUsuarioLog());
                                        }else{
                                            txtUsuarioOsPadrao.setText(getUsuarioTela().getUsuario());
                                        }

                                        if(os.getDataExecucao() == null||os.getDataExecucao().equals("")){
                                            txtDataExecucaoOsPadrao.setValue(null);
                                        }else{
                                            txtDataExecucaoOsPadrao.setValue(os.getDataExecucao());
                                        }

                                        if(os.getHoraExecucao()== null){
                                            txtHoraExecucaoOsPadrao.setText("");
                                        }else{
                                            txtHoraExecucaoOsPadrao.setText(os.getHoraExecucao().toString());
                                        }

                                        txtCodigoClienteOsPadrao.setText(String.valueOf(os.getContrato().getCliente().getIdCliente()));
                                        txtValorOsPadrao.setText(new Util().formataMoeda(os.getValor()));
                                        txtNomeClienteOsPadrao.setText(os.getContrato().getCliente().getNome());
                                        txtTelefoneContatoOsPadrao.setText(os.getContrato().getCliente().getTelefoneContato());
                                        txtNomeContatoOsPadrao.setText(os.getContrato().getCliente().getNomeContato());
                                        txtEnderecoOsPadrao.setText(os.getContrato().getCliente().getEndereco());
                                        txtBairroOsPadrao.setText(os.getContrato().getCliente().getBairro());
                                        txtCidadeOsPadrao.setText(os.getContrato().getCliente().getCidade());
                                        txtObsOsPadrao.setText(os.getObservacao());
                                        cmbUfOrdemServico.setSelectedItem(os.getContrato().getCliente().getUf());

                                    } catch (Exception ex) {
                                        Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                   
                                   btnIncluirOsPadrao.setEnabled(true);
                                   btnAlterarOsPadrao.setEnabled(true);
                                   btnExcluirOsPadrao.setEnabled(true);
                               }
                        }
        }
}





