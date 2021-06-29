/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universidad.universdiad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author JULIO
 */
public abstract class GenericDomainTableModel<T> implements TableModel {
    private EventListenerList _listaEventos;
    private List _listaIdentificadorColumnas;
    private final List<T> _datos;
    
    public GenericDomainTableModel(){
        _datos = new ArrayList<>();
        _listaIdentificadorColumnas = new ArrayList();
        _listaEventos = new EventListenerList();
    }
    public GenericDomainTableModel(List identificadorColumnas){
        this();
        if (identificadorColumnas == null) {
            throw new IllegalArgumentException("El identificadorColumnas no puede ser nulo");
        } else {
            this._listaIdentificadorColumnas.addAll(identificadorColumnas);
        }
    }
    
    @Override
    public void addTableModelListener(TableModelListener listener) {
        _listaEventos.add(TableModelListener.class, listener);
    }
    
    @Override
    public void removeTableModelListener(TableModelListener listener) {
        _listaEventos.remove(TableModelListener.class, listener);
    }
    
    public TableModelListener[] getTableModelListeners() {
        return _listaEventos.getListeners(TableModelListener.class);
    }
    
    protected void notifyTableChanged(TableModelEvent e) {
        TableModelListener[] listeners = getTableModelListeners();
        for (int i = listeners.length - 1; i >= 0; i--) {
            listeners[i].tableChanged(e);
        }
    }
    
    protected void notifyTableHeaderChanged() {
        TableModelEvent e = new TableModelEvent(this, TableModelEvent.HEADER_ROW);
        notifyTableChanged(e);
    }
    
    protected void notifyTableRowsInserted(int firstRow, int lastRow) {
        TableModelEvent e = new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        notifyTableChanged(e);
    }
    
    protected void notifyTableRowsUpdated(int firstRow, int lastRow) {
        TableModelEvent e = new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);
        notifyTableChanged(e);
    }
    
    protected void notifyTableRowsDeleted(int firstRow, int lastRow) {
        TableModelEvent e = new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        notifyTableChanged(e);
    }
    
    protected void notifyTableCellUpdated(int row, int column) {
        TableModelEvent e = new TableModelEvent(this, row, row, column);
    }
    
    
    @Override
    public int getRowCount() {
        return _datos.size();
    }

    @Override
    public int getColumnCount() {
        return _listaIdentificadorColumnas.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= getColumnCount()) {
            throw new ArrayIndexOutOfBoundsException(columnIndex);
        } else {
            return _listaIdentificadorColumnas.get(columnIndex).toString();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    public void addRow(T domainObject) {
        int rowIndex = _datos.size();
        _datos.add(domainObject);
        notifyTableRowsInserted(rowIndex, rowIndex);
    }
    
    public void addRows(List<T> domainObjects) {
        if (!domainObjects.isEmpty()) {
            int firstRow = _datos.size();
            _datos.addAll(domainObjects);
            int lastRow = _datos.size() - 1;
            notifyTableRowsInserted(firstRow, lastRow);
        }
    }
    
    public void insertRow(T domainObject, int rowIndex) {
        _datos.add(rowIndex, domainObject);
        notifyTableRowsInserted(rowIndex, rowIndex);
    }

    public void notifyDomainObjectUpdated(T domainObject) {
        T[] elements = (T[])_datos.toArray();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == domainObject) {
                notifyTableRowsUpdated(i, i);
            }
        }
    }
    
    public void deleteRow(T domainObject) {
        int rowIndex = -1;
        while ((rowIndex = _datos.indexOf(domainObject)) > -1) {
            _datos.remove(domainObject);
            notifyTableRowsDeleted(rowIndex, rowIndex);
        }
    }
    
    public void deleteRow(int rowIndex) {
        if (_datos.remove(_datos.get(rowIndex))) {
            notifyTableRowsDeleted(rowIndex, rowIndex);
        }
    }
    
    public void deleteRows(int firstRow, int lastRow) {
        if (firstRow < 0 || lastRow < 0 || firstRow > lastRow) {
            throw new IllegalArgumentException("Los parámetros firstRow y lastRow deben ser positivos y firstRow >= lastRow.");
        } else {
            for (int i = firstRow; i <= lastRow; i++) {
                _datos.remove(i);
            }
            notifyTableRowsDeleted(firstRow, lastRow);
        }
    }
    
    public void clearTableModelData() {
        if (!_datos.isEmpty()) {
            int lastRow = _datos.size() - 1;
            _datos.clear();
            notifyTableRowsDeleted(0, lastRow);
        }
    }
    
    public T getDomainObject(int rowIndex) {
        return _datos.get(rowIndex);
    }
     
    public List<T> getDomainObjects(int firstRow, int lastRow) {
        return Collections.unmodifiableList(_datos.subList(firstRow, lastRow + 1));
    }
    
    public List<T> getDomainObjects() {
        return Collections.unmodifiableList(_datos);
    }
    
    public void setColumnIdentifiers(List columnIdentifiers) {
        this._listaIdentificadorColumnas.clear();
        this._listaIdentificadorColumnas.addAll(columnIdentifiers);
        notifyTableHeaderChanged();
    }
}

