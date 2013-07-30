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
public class ContratoException extends Exception {
    
    private static Logger LOG = Logger.getLogger("OrdemServicoException");
    
    public ContratoException(String mensagem, Throwable causa){        
        super(mensagem, causa);
        LOG.error(mensagem, causa);
    }
    
}
