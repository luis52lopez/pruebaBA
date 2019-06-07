package com.examenBA.appiRest.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

public class ProductosColection{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;
	
    public String nobreProducto;
	public String costo;
    public String numeroInventario;
    public String descripcion;   
        	
	public ProductosColection(String id, String nobreProducto, String costo, String numeroInventario,String descripcion) {		
		this.id = id;
		this.nobreProducto = nobreProducto;
		this.costo = costo;
		this.numeroInventario = numeroInventario;
		this.descripcion = descripcion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNobreProducto() {
		return nobreProducto;
	}

	public void setNobreProducto(String nobreProducto) {
		this.nobreProducto = nobreProducto;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}

	public String getNumeroInventario() {
		return numeroInventario;
	}

	public void setNumeroInventario(String numeroInventario) {
		this.numeroInventario = numeroInventario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
