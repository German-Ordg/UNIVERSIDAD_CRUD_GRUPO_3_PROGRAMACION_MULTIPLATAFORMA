/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universidad.universidad.dao;

/**
 *
 * @author JULIO
 */
public class Rectores {

    /**
     * @return the _ID
     */
    public double getID() {
        return _ID;
    }

    /**
     * @param _ID the _ID to set
     */
    public void setID(double _ID) {
        this._ID = _ID;
    }

    /**
     * @return the _SIGLAS
     */
    public String getSIGLAS() {
        return _SIGLAS;
    }

    /**
     * @param _SIGLAS the _SIGLAS to set
     */
    public void setSIGLAS(String _SIGLAS) {
        this._SIGLAS = _SIGLAS;
    }

    /**
     * @return the _NOMBREUNIVERSIDAD
     */
    public String getNOMBREUNIVERSIDAD() {
        return _NOMBREUNIVERSIDAD;
    }

    /**
     * @param _NOMBREUNIVERSIDAD the _NOMBREUNIVERSIDAD to set
     */
    public void setNOMBREUNIVERSIDAD(String _NOMBREUNIVERSIDAD) {
        this._NOMBREUNIVERSIDAD = _NOMBREUNIVERSIDAD;
    }

    /**
     * @return the _NOMBRECAMPUS
     */
    public String getNOMBRECAMPUS() {
        return _NOMBRECAMPUS;
    }

    /**
     * @param _NOMBRECAMPUS the _NOMBRECAMPUS to set
     */
    public void setNOMBRECAMPUS(String _NOMBRECAMPUS) {
        this._NOMBRECAMPUS = _NOMBRECAMPUS;
    }
    private String _SIGLAS;
    private String _NOMBREUNIVERSIDAD;
    private String _NOMBRECAMPUS;
    private double _ID;
}
