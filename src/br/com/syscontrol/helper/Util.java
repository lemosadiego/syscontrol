/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.helper;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Diego
 */
public class Util {

    public boolean isDataValida(String data) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        boolean retorno = true;
        try {
            format.parse(data);

            if(data.length()!=10){
                 retorno = false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public String getDataHoje() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");        
        return format.format(new Date(System.currentTimeMillis()));
    }

    public Time getHora() {
        //SimpleDateFormat format = new SimpleDateFormat("h:mm");
        Time time = new Time(System.currentTimeMillis());
        return time;
    }

    public void carregaComboUf(JComboBox combo){

        String [] ufList = new String [27];
        ufList[0] = "AC";
        ufList[1] = "AL";
        ufList[2] = "AM";
        ufList[3] = "AP";
        ufList[4] = "BA";
        ufList[5] = "CE";
        ufList[6] = "DF";
        ufList[7] = "ES";
        ufList[8] = "GO";
        ufList[9] = "MA";
        ufList[10] = "MG";
        ufList[11] = "MS";
        ufList[12] = "MT";
        ufList[13] = "PA";
        ufList[14] = "PB";
        ufList[15] = "PE";
        ufList[16] = "PI";
        ufList[17] = "PR";
        ufList[18] = "RJ";
        ufList[19] = "RN";
        ufList[20] = "RO";
        ufList[21] = "RR";
        ufList[22] = "RS";
        ufList[23] = "SC";
        ufList[24] = "SE";
        ufList[25] = "SP";
        ufList[26] = "TO";

        for(int i = 0;i<ufList.length;i++){
            combo.addItem(ufList[i]);
        }

        combo.setSelectedIndex(18);

    }

public String formataMoeda(double vlr){
    java.text.DecimalFormat df = new java.text.DecimalFormat("R$ ###,###,##0.00");
    return df.format( vlr );
}

public double converteValorTextoDouble(String texto) throws ParseException{
    java.text.DecimalFormat df = new java.text.DecimalFormat("R$ ###,###,##0.00");
    return  df.parse( texto ).doubleValue();
}


/*Metodo responsavel por converter o valor no formato exemplo 300,00 para formato double exemplo 300.00*/
public double getDoubleValue(String valor){

            
            int p = valor.indexOf(".");
            if(p!=-1 && (valor.substring(p+1,valor.length()).length()>2)){
                valor = valor.substring(0,p)+valor.substring(p+1,valor.length());
            }
            
            int i = valor.indexOf(",");
            if(i!=-1){
                valor = valor.substring(0,i)+ "."+ valor.substring(i+1,valor.length());
            }
            
            return Double.valueOf(valor);       

}

public double converterValor(String svalor) {

    int v = svalor.indexOf(",");
    int p = svalor.indexOf(".");

    if (svalor == "") {

      return 0.0;
    }

    if (v == -1) {

      v = svalor.length();
      svalor = svalor.substring(0, v) + ".00";

    }

    if (p != -1) {

      svalor = svalor.substring(0, p) + svalor.substring(p + 1, v) + "." +
          svalor.substring(v + 1, svalor.length());

    }
    else if (p == -1) {

      svalor = svalor.substring(0, v) + "." +
          svalor.substring(v + 1, svalor.length());

    }

    try {

      double dvalor = Double.parseDouble(svalor);
      return dvalor;

    }
    catch (Exception e) {

      javax.swing.JOptionPane.showMessageDialog(null,
                                                "Valor para cáculo inserido de forma incorreta",
                                                "Atenção", 1);
    }

    return 0.0;
  }

  public String formataSqlDate(String data){

      String retorno = null;

      if(data !=null || data != ""){
        retorno = data.substring(6,10)+"-"+data.substring(3,5)+"-"+data.substring(0,2);
        return retorno;
      }
      return retorno;
  }


  public String formataSqlDateToText(String data){

      String retorno = null;

      if(data !=null){
        retorno = data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0,4);
        return retorno;
      }
      return retorno;
  }

}
