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
public class ServicoDisponivelTableModel extends AbstractTableModel {

    private List<Servico> linhas;

    private String[] colunas = new String[] {
			"Codigo", "Serviço","Garantia","Valor"};




    public ServicoDisponivelTableModel(){
        this.linhas = new ArrayList<Servico>();
    }

    public ServicoDisponivelTableModel(List<Servico> listaServicos){
        this.linhas = listaServicos;
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
		case 0: // Primeira coluna é o nome
			return Long.class;
                case 1:
                        return String.class;
                case 2: // Segunda coluna é o valor
			return Integer.class;
                case 3: // Terceira coluna é a garantia
			return Double.class;

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
		// Pega o sócio da linha especificada.
		Servico servico = getLinhas().get(rowIndex);

		// Retorna o campo referente a coluna especificada.
		// Aqui é feito um switch para verificar qual é a coluna
		// e retornar o campo adequado. As colunas são as mesmas
		// que foram especificadas no array "colunas".
		switch (columnIndex) {
		case 0: // Primeira coluna é o nome.
			return servico.getIdServico();
                case 1:
			return servico.getDescricao();
                case 2:
			return servico.getGarantia();
                case 3:
			return servico.getValor();

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

    public Servico getServico(int indiceLinha) {
		return getLinhas().get(indiceLinha);
    }

    public void addServico(Servico servico) {
		// Adiciona o registro.
		getLinhas().add(servico);

		// Pega a quantidade de registros e subtrai um para achar
		// o último índice. É preciso subtrair um, pois os índices
		// começam pelo zero.
		int ultimoIndice = getRowCount() - 1;

		// Reporta a mudança. O JTable recebe a notificação
		// e se redesenha permitindo que visualizemos a atualização.
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /* Remove a linha especificada. */
    public void removeServico(int indiceLinha) {
		// Remove o sócio da linha especificada.
		getLinhas().remove(indiceLinha);

		// Reporta a mudança. O JTable recebe a notificação
		// e se redesenha permitindo que visualizemos a atualização.
		fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    /* Adiciona uma lista de sócios ao final dos registros. */
	public void addListaDeServicos(List<Servico> servicos) {
		// Pega o tamanho antigo da tabela.
		int tamanhoAntigo = getRowCount();

		// Adiciona os registros.
		getLinhas().addAll(servicos);


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
    public List<Servico> getLinhas() {
        return linhas;
    }

    /**
     * @param linhas the linhas to set
     */
    public void setLinhas(List<Servico> linhas) {
        this.linhas = linhas;
    }

    @Override
    public void setValueAt(Object value, int row, int col){


        if(col==1){
            this.getServico(row).setValor((Double)value);
        }
        
        fireTableCellUpdated(row,col);
    }


}
