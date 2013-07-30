/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.seguranca;

/**
 *
 * @author Diego
 */
public class GerenciadorPermissoes {

    PersonalizarObjeto po;

    public GerenciadorPermissoes(PersonalizarObjeto po){
        this.po = po;
    }

    public void configurarFormulario(){
        this.po.personalizar();
    }

}
