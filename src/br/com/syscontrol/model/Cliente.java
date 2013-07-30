/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Diego
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoCliente", length = 10, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "Física")
public class Cliente implements Serializable {    
    
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
    
    @Column(name = "tipoCliente", insertable=false, updatable=false)
    protected String tipoCliente;
    
    @Column(name = "nomecontato")
    protected String nomeContato;
    
    @Column(name = "telefonecontato")
    protected String telefoneContato;

    /**
     * @return the id
     */
    public long getId() {
        return idCliente;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
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
    public String getTipoCliente() {
        return tipoCliente;
    }

    /**
     * @param tipoCliente the tipoCliente to set
     */
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.idCliente ^ (this.idCliente >>> 32));
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
        final Cliente other = (Cliente) obj;
        if (this.idCliente != other.idCliente) {
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
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + idCliente + ", nome=" + nome + ", endereco=" + endereco + ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf + ", cep=" + cep + ", tipoCliente=" + tipoCliente + ", nomeContato=" + nomeContato + ", telefoneContato=" + telefoneContato + '}';
    }

    


}
