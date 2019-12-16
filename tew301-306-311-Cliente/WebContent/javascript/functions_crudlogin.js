//Clase que contiene el Modelo de la aplicacion.
function Model() {

	this.tbAgentes = null;
	
	this.load = function() {
		this.tbAgentes = LoginServicesRs.getAgentes();
	}

};

//Clase que contiene la gestión de la capa Vista
function View() {

	this.loadLoginFromForm = function() {
		// Cogemos el piso nuevo del formulario.
		var login = {
				login : $("#username").val(),
				password : $("#passwd").val()
		};
		return login;
	}

	this.resetLoginForm = function(){
		$("#username").val("");
		$("#passwd").val("");
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
		this.model.load();
		
		// MANEJADORES DE EVENTOS

		$("#login").bind("submit",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function(event) {
			//Cogemos el login del formulario
			var login = that.view.loadLoginFromForm();
			var username = login['login'];
			var password = login['password'];
			
			var users = that.model.tbAgentes;
			for (var i in users){
				if(users[i]['login']==username &&users[i]['password']==password)
					sessionStorage.setItem('ID', users[i]['ID']);
					window.location.href = "http://localhost:2000/tew301-306-311-Cliente/index-agente.html";
			}
				
		});

		$("#login").bind("reset",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function(event) {
			// Si el piso cargado en el formulario tiene ID se invoca al
			// método de edición sino se invoca al método de alta.
			that.view.resetLoginForm();
			// Refrescar listado Pisos

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