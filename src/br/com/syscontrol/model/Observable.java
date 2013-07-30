/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

/**
 *
 * @author Diego
 */
public interface Observable {

        public void registerObserver(Observer o);

        public void removeObserver(Observer o);

        public void notifyObservers(Object arg);

}
