/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego
 */
public class BuscaEmpresaTableModel extends AbstractTableModel {

         private List<ClientePessoa> linhas;

    private String[] colunas = new String[] {
			"Código", "Nome","Cnpj","Endereço","Bairro","Telefone Comercial"};


    public BuscaEmpresaTableModel(){
        this.linhas = new ArrayList<ClientePessoa>();
    }

    public BuscaEmpresaTableModel(List<ClientePessoa> listaEmpresas){
        this.linhas = listaEmpresas;
    }


    @Override
    public int getRowCount() {
        return this.getLinhas().size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
		// Retorna a classe referente a coluna especificada.
		// Aqui é feito um switch para verificar qual é a coluna
		// e retornar o tipo adequado. As colunas são as mesmas
		// que foram especificadas no array "colunas".
		switch (columnIndex) {
		case 0: // Primeira coluna é o nome, que é uma String.
			return Integer.class;
		case 1: // Segunda coluna é o telefone, que também é uma String..
			return String.class;
                case 2: // Segunda coluna é o telefone, que também é uma String..
			return String.class;
                case 3: // Segunda coluna é o telefone, que também é uma String..
			return String.class;
                case 4: // Segunda coluna é o telefone, que também é uma String..
			return String.class;
                case 5: // Segunda coluna é o telefone, que também é uma String..
			return String.class;
		default:
			// Se o índice da coluna não for válido, lança um
			// IndexOutOfBoundsException (Exceção de índice fora dos limites).
			// Não foi necessário verificar se o índice da linha é inválido,
			// pois o próprio ArrayList lança a exceção caso seja inválido.
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

    @Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Pega o Condominio da linha especificada.
		ClientePessoaJuridica cliente = (ClientePessoaJuridica) getLinhas().get(rowIndex);

		// Retorna o campo referente a coluna especificada.
		// Aqui é feito um switch para verificar qual é a coluna
		// e retornar o campo adequado. As colunas são as mesmas
		// que foram especificadas no array "colunas".
		switch (columnIndex) {
		case 0: // Primeira coluna é o nome.
			return cliente.getIdCliente();
		case 1: // Segunda coluna é o telefone.
			return cliente.getNome();
                case 2: // Terceira coluna é o telefone.
			return cliente.getCnpj();
                case 3: // Terceira coluna é o endereço.
			return cliente.getEndereco();
                case 4: // Terceira coluna é o Bairro.
			return cliente.getBairro();
                case 5: // Terceira coluna é o telefone.
			return cliente.getTelefoneComercial();
		default:
			// Se o índice da coluna não for válido, lança um
			// IndexOutOfBoundsException (Exceção de índice fora dos limites).
			// Não foi necessário verificar se o índice da linha é inválido,
			// pois o próprio ArrayList lança a exceção caso seja inválido.
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

    public ClientePessoa getClienteEmpresa(int indiceLinha) {
		return getLinhas().get(indiceLinha);
    }


    public void addClientEmpresa(ClientePessoaJuridica ce) {
		// Adiciona o registro.
		getLinhas().add(ce);

		// Pega a quantidade de registros e subtrai um para achar
		// o último índice. É preciso subtrair um, pois os índices
		// começam pelo zero.
		int ultimoIndice = getRowCount() - 1;

		// Reporta a mudança. O JTable recebe a notificação
		// e se redesenha permitindo que visualizemos a atualização.
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /* Adiciona uma lista de empresas ao final dos registros. */
	public void addListaDeEmpresas(List<ClientePessoa> empresas) {
		// Pega o tamanho antigo da tabela.
		int tamanhoAntigo = getRowCount();

		// Adiciona os registros.
		getLinhas().addAll(empresas);


		// Reporta a mudança. O JTable recebe a notificação
		// e se redesenha permitindo que visualizemos a atualização.
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

        /* Remove todos os registros. */
	public void limpar() {
		// Remove todos os elementos da lista de sócios.
		getLinhas().clear();

		// Reporta a mudança. O JTable recebe a notificação
		// e se redesenha permitindo que visualizemos a atualização.
		fireTableDataChanged();
	}

        /* Verifica se este table model está vazio. */
	public boolean isEmpty() {
		return getLinhas().isEmpty();
	}

    /**
     * @return the linhas
     */
    public List<ClientePessoa> getLinhas() {
        return linhas;
    }

    /**
     * @param linhas the linhas to set
     */
    public void setLinhas(List<ClientePessoa> linhas) {
        this.linhas = linhas;
    }

    /**
     * @return the colunas
     */
    public String[] getColunas() {
        return colunas;
    }

    /**
     * @param colunas the colunas to set
     */
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }


}
