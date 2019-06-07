package com.examen.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenBA.appiRest.DAO.OperacionesValores;
import com.examenBA.appiRest.beans.RequestGetBean;
import com.examenBA.appiRest.beans.RequestPostBean;
import com.examenBA.appiRest.beans.RequestPutBean;
import com.examenBA.appiRest.beans.ResponseGetBean;
import com.examenBA.appiRest.beans.ResponsePostBean;
import com.examenBA.appiRest.beans.ResponsePutBean;

@RestController
@RequestMapping("/examen/rest/")
public class ProductosController {
	private static Logger logger = LoggerFactory.getLogger(ProductosController.class);
	
	@Autowired private OperacionesValores operaciones;
	
	@PostMapping(path = "inserta/valores", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponsePostBean insertarValores(@RequestBody RequestPostBean request) {
		logger.info("inserta/valores: {}", request.getValores().toString());
		
		return operaciones.insertarValores(request);
	}
	
	@GetMapping(path = "obtener/valores", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseGetBean obtenerValores(@RequestBody RequestGetBean request) {		
		return operaciones.obtenerValores(request.getId());
	}
	
	
	@PutMapping(path = "actualiza/valores", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponsePutBean actualizaValores(@RequestBody RequestPutBean request) {
		return operaciones.actualizarValores(request);
		
	}
}

