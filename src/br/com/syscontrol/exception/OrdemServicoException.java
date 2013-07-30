/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syscontrol.exception;

import org.apache.log4j.Logger;

/**
 *
 * @author Diego Lemos
 */
public class OrdemServicoException extends Exception {
    
    private static Logger LOG = Logger.getLogger("OrdemServicoException");
    private String mensagemErro;
    
    public OrdemServicoException(String mensagem, Throwable causa){        
        super(mensagem, causa);
        this.mensagemErro = mensagem;
        LOG.error(mensagem, causa);
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
