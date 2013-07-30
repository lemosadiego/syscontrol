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
public class UsuarioCadaGrupoTableModel extends AbstractTableModel {

    private List<GrupoUsuario> linhas;

    private String[] colunas = new String[] {
			"Id", "Grupo Usuario"};

    public UsuarioCadaGrupoTableModel(){
        this.linhas = new ArrayList<GrupoUsuario>();
    }

    public UsuarioCadaGrupoTableModel(List<GrupoUsuario> lista){
        this.linhas = lista;
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Pega o sócio da linha especificada.
		GrupoUsuario ucg = getLinhas().get(rowIndex);

		// Retorna o campo referente a coluna especificada.
		// Aqui é feito um switch para verificar qual é a coluna
		// e retornar o campo adequado. As colunas são as mesmas
		// que foram especificadas no array "colunas".
		switch (columnIndex) {
		case 0: // Primeira coluna é o nome.
			return ucg.getIdGrupoUsuario();
		case 1: // Segunda coluna é o telefone.
			return ucg.getDescricao();

		default:
			// Se o índice da coluna não for válido, lança um
			// IndexOutOfBoundsException (Exceção de índice fora dos limites).
			// Não foi necessário verificar se o índice da linha é inválido,
			// pois o próprio ArrayList lança a exceção caso seja inválido.
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
    }

    @Override
    public String getColumnName(int columnIndex) {

		return colunas[columnIndex];
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

    public GrupoUsuario getUsuarioCadaGrupo(int indiceLinha) {
		return getLinhas().get(indiceLinha);
    }

    public void addUsuarioCadaGrupo(GrupoUsuario ucg) {
		// Adiciona o registro.
		getLinhas().add(ucg);

		// Pega a quantidade de registros e subtrai um para achar
		// o último índice. É preciso subtrair um, pois os índices
		// começam pelo zero.
		int ultimoIndice = getRowCount() - 1;

		// Reporta a mudança. O JTable recebe a notificação
		// e se redesenha permitindo que visualizemos a atualização.
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /* Remove a linha especificada. */
    public void removeUsuarioCadaGrupo(int indiceLinha) {
		// Remove o sócio da linha especificada.
		getLinhas().remove(indiceLinha);

		// Reporta a mudança. O JTable recebe a notificação
		// e se redesenha permitindo que visualizemos a atualização.
		fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

     /* Adiciona uma lista de sócios ao final dos registros. */
	public void addListaDeUsuariosCadaGrupo(List<GrupoUsuario> ucg) {
		// Pega o tamanho antigo da tabela.
		int tamanhoAntigo = getRowCount();

		// Adiciona os registros.
		getLinhas().addAll(ucg);


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
    public List<GrupoUsuario> getLinhas() {
        return linhas;
    }

    /**
     * @param linhas the linhas to set
     */
    public void setLinhas(List<GrupoUsuario> linhas) {
        this.linhas = linhas;
    }

}
