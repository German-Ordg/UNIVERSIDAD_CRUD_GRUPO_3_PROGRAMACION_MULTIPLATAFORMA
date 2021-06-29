/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universidad.universdiad;

import java.util.Arrays;
import com.universidad.universidad.dao.*;;

/**
 *
 * @author JULIO
 */
public class RectoresTableMode extends GenericDomainTableModel<Rectores>{
    
    //Estamos definiendo los titulos del JTable
    public RectoresTableMode(){
        super( Arrays.asList( new Object[]{"ID", "SIGLAS", "NOMBRE_UNIVERSIDAD", "NOMBRE_RECTOR"} ));
    }
    
    @Override
    public Class<?> getColumnClass(int colIndex){
        switch(colIndex){
            case 0:
                return Double.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
        }
        throw new ArrayIndexOutOfBoundsException(colIndex);
    }
    
    
    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Rectores item = getDomainObject(rowIndex);
        switch(colIndex){
            case 0:
                return item.getID();
            case 1:
                return item.getSIGLAS();
            case 2:
                return item.getNOMBREUNIVERSIDAD();
            case 3:
                return item.getNOMBRECAMPUS();
        }
        throw new ArrayIndexOutOfBoundsException(colIndex);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int colIndex){
       // return rowIndex>0 && colIndex > 1;
       return false;
    }
   
    @Override
    public void setValueAt(Object valor, int rowIndex, int colIndex){
        ///Implementación
        
        //Dispanara un Evento de Notificacion
        notifyTableCellUpdated(rowIndex, colIndex);
    } 
}
