
//Clase que contiene el Modelo de la aplicación.
function Model(){

//	Reiniciar la base de datos
	this.reiniciar = function() {
//		Llamamos al servicio de reinicio de la base de datos
		DatosServicesRs.resetDatos();
	}

};


function Controller(varmodel) {
	// Definimos una copia de this para evitar el conflicto con el this (del
	// objeto que recibe el evento)
	// en los manejadores de eventos
	var that = this;
	// referencia al modelo
	this.model = varmodel;
	// refefencia a la vista
	// Funcion de inicialización para cargar modelo y vista, definición de manejadores
	this.init = function() {
		// MANEJADORES DE EVENTOS
		// Manejador del botón submit del formulario
		$("#formdatos").bind("submit", function(event) {
				that.model.reiniciar();
		}); 
	}
}; 

$(function() {
	// Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	// Creamos el controlador
	var control = new Controller(model);
	// Iniciamos la aplicación
	control.init();
}); 