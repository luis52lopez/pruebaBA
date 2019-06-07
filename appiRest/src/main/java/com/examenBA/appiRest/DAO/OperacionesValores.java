package com.examenBA.appiRest.DAO;

import javax.management.Query;

import org.hibernate.sql.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.examenBA.appiRest.entity.*;
import com.examenBA.appiRest.beans.RequestPostBean;
import com.examenBA.appiRest.beans.RequestPutBean;
import com.examenBA.appiRest.beans.ResponseGetBean;
import com.examenBA.appiRest.beans.ResponsePostBean;
import com.examenBA.appiRest.beans.ResponsePutBean;
import com.jayway.jsonpath.Criteria;
import com.mongodb.client.result.UpdateResult;

@Repository
public class OperacionesValores {
	private static Logger logger = LoggerFactory.getLogger(OperacionesValores.class);
	private static final String COLECCION_REST = "ServiciosRest";

	@Autowired
	@Qualifier("plantillaMongo")
	private MongoTemplate plantilla;

	public ResponsePostBean insertarValores(RequestPostBean request) {
		ResponsePostBean response = new ResponsePostBean(); 
		
		if(request.getValores() != null && request.getValores().length > 0) {
			ValoresColeccion valores = new ValoresColeccion();
			valores.setValores(request.getValores());
			plantilla.insert(valores, COLECCION_REST);
			logger.info("id: {} valores:{} ", valores.getId(), valores.getValores());
			response.setId(valores.getId());
			response.setCodigo("0");
			response.setMensaje("Operacion exitosa");
		} else {
			response.setCodigo("-1");
			response.setMensaje("Lista de valores necesarias.");
		}
		
		return response;
	}

	public ResponsePutBean actualizarValores(RequestPutBean request) {
		ResponsePutBean response = new ResponsePutBean();
		
		if(request.getId() != null && request.getValores() != null && request.getValores().length > 0) { 
			
			if(plantilla.exists(new Query(Criteria.where("_id").is(request.getId())), COLECCION_REST)){
				Update upd = new Update();
				upd.set("valores", request.getValores());
				UpdateResult resultado = plantilla.updateFirst(new Query(Criteria.where("_id").is(request.getId())), upd, COLECCION_REST); 
				if(resultado.getModifiedCount() != 0) {
					response.setId(request.getId());
					response.setCodigo("0");
					response.setMensaje("Operacion exitosa");
				} else {
					response.setCodigo("-3");
					response.setMensaje("No se actualizo el registro");
				}
			} else {
				response.setCodigo("-2");
				response.setMensaje("El registro que intenta actualizar no existe");
			}
		} else {
			response.setCodigo("-1");
			response.setMensaje("Todos los valores son necesarios");
		}
		
		
		return response;
	}

	public ResponseGetBean obtenerValores(String id) {
		ResponseGetBean response = new ResponseGetBean();
		
		if(id != null && !id.isEmpty()) { 
			
			if(plantilla.exists(new Query(Criteria.where("_id").is(id)), COLECCION_REST)){ 
				ValoresColeccion resultado = plantilla.findOne(new Query(Criteria.where("_id").is(id)), ValoresColeccion.class, COLECCION_REST); 
				response.setValores(resultado.getValores());
				response.setCodigo("0");
				response.setMensaje("Operacion exitosa");
			} else { 
				response.setCodigo("-2");
				response.setMensaje("El registro que intenta obtener no existe");
			}
		} else {
			response.setCodigo("-1");
			response.setMensaje("Todos los valores son necesarios");
		}
		
		return response;
	}
}
