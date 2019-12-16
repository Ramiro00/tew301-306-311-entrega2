
//Clase que contiene el Modelo de la aplicación.
function Model(){

	this.tbPisos = null;

//	Carga los datos del servicio sobreescribiendo el dato this.tbPisos.
	this.load = function() {
		this.tbPisos = PisosServicesRs.getPisos();
	}

//	Llamamos al servicio de alta de piso
	this.add = function(piso) {
//		Llamamos al servicio de alta de piso
		PisosServicesRs.savePiso({
			$entity : piso,
			$contentType : "application/json"
		});
	}

//	Actualización de un piso existente
	this.edit = function(piso) {
//		Llamamos al servicio de edicion de piso
		PisosServicesRs.updatePiso({
			$entity : piso,
			$contentType : "application/json"	
		});
	}

	this.find = function(id_piso) {
		function checkpiso(obj) {
			return obj.id == id_piso;
		}
//		Buscamos los datos del piso cuyo id_piso sea el mismo que el
//		seleccionado
		var piso = this.tbPisos.find(checkpiso);
		return piso;
	} 
};

function View() {
	this.getPath = function() {
		return $("#URL").val();
	}
}

function Controller(varmodel, varview) {
	// Definimos una copia de this para evitar el conflicto con el this (del
	// objeto que recibe el evento)
	// en los manejadores de eventos
	var that = this;
	// referencia al modelo
	this.model = varmodel;

	this.view = varview;
	// Funcion de inicialización para cargar modelo y vista, definición de manejadores
	this.init = function() {
		// MANEJADORES DE EVENTOS
		this.model.load();

		$("#formimportarservidor").bind("submit", function(event) {
			// Método que gestiona el evento de clickar el botón submit del
			// formulario
			var direccion = that.view.getPath();
			$.ajax({
				url : direccion,
				type : "GET",
				dataType : "json",
				// Listado de pisos (función de callback)
				success : function(pisos) {
					alert("Recibida respuesta con exito!");
					//Para acceder a los datos de los pisos se puede recorrer así
					for ( var i in pisos) {//Preparamos el registro de un piso
						var piso = ({
							id : pisos[i].ID,
							idagente: 1,
							precio : pisos[i].Precio,
							direccion: pisos[i].Direccion,
							ciudad: pisos[i].Ciudad,
							ano: pisos[i].Anyo,
							estado: pisos[i].Estado,
							foto: pisos[i].Foto
						});

//						Buscamos los datos del piso cuyo id_piso sea el mismo que el
//						seleccionado
						if(that.model.find(piso.ID)!= null){
							that.model.edit(piso);
						}
						else that.model.add(piso);
					}
				} //Cierre de la función de callback
			}); //Cierre del parámetro de .ajax
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