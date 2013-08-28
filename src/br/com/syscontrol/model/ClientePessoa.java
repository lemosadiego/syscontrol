/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Diego
 */

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipoCliente", length = 10, discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
public class ClientePessoa implements Serializable {    
    
    @Id @GeneratedValue
    protected Long idCliente;
    
    @Column(name = "nome")
    protected String nome;
    
    @Column(name = "endereco")
    protected String endereco;
    
    @Column(name = "bairro")
    protected String bairro;
    
    @Column(name = "cidade")
    protected String cidade;    
    
    @Column(name = "uf")
    protected String uf;
    
    @Column(name = "cep")
    protected String cep;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoCliente")
    protected TipoCliente tipoCliente;
    
    @Column(name = "nomecontato")
    protected String nomeContato;
    
    @Column(name = "telefonecontato")
    protected String telefoneContato;
    
    @Column(name = "email")
    private String email;

   
    @OneToMany(targetEntity=OrdemServicoPadrao.class)
    @JoinColumn(name="idCliente", updatable = false)	
    private List<OrdemServicoPadrao> listaOrdensServicos;

    @OneToMany(targetEntity=Pedido.class)
    @JoinColumn(name="idCliente", updatable = false)	
    private List<Pedido> listaPedidos;
    
    /**
     * @return the id
     */
    public long getIdCliente() {
        return idCliente;
    }

    /**
     * @param id the id to set
     */
    public void setIdCliente(Long id) {
        this.idCliente = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

   

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    
    /**
     * @return the nomeContato
     */
    public String getNomeContato() {
        return nomeContato;
    }

    /**
     * @param nomeContato the nomeContato to set
     */
    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    /**
     * @return the telefoneContato
     */
    public String getTelefoneContato() {
        return telefoneContato;
    }

    /**
     * @param telefoneContato the telefoneContato to set
     */
    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the tipoCliente
     */
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    /**
     * @param tipoCliente the tipoCliente to set
     */
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the listaOrdensServicos
     */
    public List<OrdemServicoPadrao> getListaOrdensServicos() {
        return listaOrdensServicos;
    }

    /**
     * @param listaOrdensServicos the listaOrdensServicos to set
     */
    public void setListaOrdensServicos(List<OrdemServicoPadrao> listaOrdensServicos) {
        this.listaOrdensServicos = listaOrdensServicos;
    }

    /**
     * @return the listaPedidos
     */
    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    /**
     * @param listaPedidos the listaPedidos to set
     */
    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.idCliente != null ? this.idCliente.hashCode() : 0);
        hash = 17 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 17 * hash + (this.endereco != null ? this.endereco.hashCode() : 0);
        hash = 17 * hash + (this.bairro != null ? this.bairro.hashCode() : 0);
        hash = 17 * hash + (this.cidade != null ? this.cidade.hashCode() : 0);
        hash = 17 * hash + (this.uf != null ? this.uf.hashCode() : 0);
        hash = 17 * hash + (this.cep != null ? this.cep.hashCode() : 0);
        hash = 17 * hash + (this.tipoCliente != null ? this.tipoCliente.hashCode() : 0);
        hash = 17 * hash + (this.nomeContato != null ? this.nomeContato.hashCode() : 0);
        hash = 17 * hash + (this.telefoneContato != null ? this.telefoneContato.hashCode() : 0);
        hash = 17 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 17 * hash + (this.listaOrdensServicos != null ? this.listaOrdensServicos.hashCode() : 0);
        hash = 17 * hash + (this.listaPedidos != null ? this.listaPedidos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientePessoa other = (ClientePessoa) obj;
        if (this.idCliente != other.idCliente && (this.idCliente == null || !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.endereco == null) ? (other.endereco != null) : !this.endereco.equals(other.endereco)) {
            return false;
        }
        if ((this.bairro == null) ? (other.bairro != null) : !this.bairro.equals(other.bairro)) {
            return false;
        }
        if ((this.cidade == null) ? (other.cidade != null) : !this.cidade.equals(other.cidade)) {
            return false;
        }
        if ((this.uf == null) ? (other.uf != null) : !this.uf.equals(other.uf)) {
            return false;
        }
        if ((this.cep == null) ? (other.cep != null) : !this.cep.equals(other.cep)) {
            return false;
        }
        if (this.tipoCliente != other.tipoCliente) {
            return false;
        }
        if ((this.nomeContato == null) ? (other.nomeContato != null) : !this.nomeContato.equals(other.nomeContato)) {
            return false;
        }
        if ((this.telefoneContato == null) ? (other.telefoneContato != null) : !this.telefoneContato.equals(other.telefoneContato)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if (this.listaOrdensServicos != other.listaOrdensServicos && (this.listaOrdensServicos == null || !this.listaOrdensServicos.equals(other.listaOrdensServicos))) {
            return false;
        }
        if (this.listaPedidos != other.listaPedidos && (this.listaPedidos == null || !this.listaPedidos.equals(other.listaPedidos))) {
            return false;
        }
        return true;
    }
    
}
