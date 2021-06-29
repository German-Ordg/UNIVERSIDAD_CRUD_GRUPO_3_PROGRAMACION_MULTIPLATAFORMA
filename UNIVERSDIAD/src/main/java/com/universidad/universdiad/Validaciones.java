/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universidad.universdiad;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JULIO
 */
public class Validaciones {
     public static boolean esCadenaVacia(String cadena){
        Pattern regex = Pattern.compile("^\\s*$");
        Matcher validador = regex.matcher(cadena);
        
        return validador.find();
    }
    
}
