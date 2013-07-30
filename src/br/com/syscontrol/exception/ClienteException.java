/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syscontrol.exception;

/**
 *
 * @author Diego Lemos
 */
public class ClienteException extends Exception{
    
    private String mensagemErro;
    
    public ClienteException(String mensagemErro, Exception e){
        super(e);
        this.mensagemErro = mensagemErro;
    }

    /**
     * @return the mensagemErro
     */
    public String getMensagemErro() {
        return mensagemErro;
    }

    /**
     * @param mensagemErro the mensagemErro to set
     */
    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
    
}
