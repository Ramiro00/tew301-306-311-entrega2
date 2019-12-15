//Clase que contiene el Modelo de la aplicación.
function Model() {
	// Lista de alumnos.
	this.tbPisos = null;

	// Carga los datos del servicio sobreescribiendo el dato this.tbPisos.
	this.load = function() {
		this.tbPisos = PisosServicesRs.getPisos();
	}
	// Llamamos al servicio de alta de piso
	this.add = function(piso) {
		// Llamamos al servicio de alta de piso
		PisosServicesRs.savePiso({
			$entity : piso,
			$contentType : "application/json"
		});
		// Recargamos la lista de alumnos.
		this.load();
	}
};

// Clase que contiene la gestión de la capa Vista
function View() {

	this.loadAlumnoFromForm = function() {
		// Cogemos el piso nuevo del formulario.
		var piso = {
			id : $("#id").val(),
			idagente: $("#idagente").val(),
			precio : $("#precio").val(),
			direccion : $("#direccion").val(),
			cuidad : $("#ciudad").val(),
			ano : $("#ano").val(),
			estado : $("#estado").val(),
			foto : $("foto").val()
		};
		return piso;
	}
};

function Controller(varmodel, varview) {
	// Definimos una copia de this para evitar el conflicto con el this (del
	// objeto que recibe el evento)
	// en los manejadores de eventos
	var that = this;
	// referencia al modelo
	this.model = varmodel;
	// refefencia a la vista
	this.view = varview;
	// Funcion de inicialización para cargar modelo y vista, definición de
	// manejadores
	this.init = function() {
		// Cargamos la lista de alumnos del servicio
		that.model.load();
		// Repintamos la lista de alumnos.
		that.view.list(this.model.tbPisos);
		// MANEJADORES DE EVENTOS

		// Manejador del botón submit del formulario de Alta y Edición
		$("#frmCRUDPisos").bind("submit",
		// Método que gestiona el evento de clickar el botón submit del
		// formulario
		function(event) {
			// Si el piso cargado en el formulario tiene ID se invoca al
			// método de edición sino se invoca al método de alta.
			piso = that.view.loadAlumnoFromForm();
			if ($("#id").val() == "") {
				that.model.add(piso);
			} else {
				that.model.edit(piso);
			}
		});
	}
};

$(function() {
	// Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	// Creamos la vista que incluye acceso al modelo.
	var view = new View();
	// Creamos el controlador
	var control = new Controller(model, view);
	// Iniciamos la aplicación
	control.init();
});