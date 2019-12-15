
//Clase que contiene el Modelo de la aplicación.
function Model(){
	//Lista de pisos.

//	Llamamos al servicio de alta de piso
	this.add = function(piso) {
//		Llamamos al servicio de alta de piso
		PisosServicesRs.savePiso({
			$entity : piso,
			$contentType : "application/json"
		});
//		Recargamos la lista de pisos.
		this.load();
	}
//	Actualización de un piso existente
	this.edit = function(piso) {
//		Llamamos al servicio de edicion de piso
		PisosServicesRs.updatePiso({
			$entity : piso,
			$contentType : "application/json"	
		});
//		Recargamos la lista de pisos.
		this.load();
	} 

//	Eliminación un piso existente
	this.remove = function(id_piso) {
//		Llamamos al servicio de borrado de piso
		PisosServicesRs.deletepiso({
			id : id_piso
		});
//		Recargamos la lista de pisos.
		this.load();
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

function View(){
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
	// Funcion de inicialización para cargar modelo y vista, definición de manejadores
	this.init = function() {
		// Cargamos la lista de pisos del servicio
		this.model.load();
		// Repintamos la lista de pisos.
		this.view.list(this.model.tbPisos);
		// MANEJADORES DE EVENTOS
		$("#formimportar").bind("submit",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function(event) {
			$.ajax({
				url : "http://localhost/Servidor/pisos.json",
				type : "GET",
				dataType : "json",
				// Listado de pisos (función de callback)
				success : function(pisos) {
					tbpisos=localStorage.getItem("tbPisos");
					tbPisos=JSON.parse(tbPisos);
					if (tbPisos == null)
						tbPisos = [];
					alert("Recibida respuesta con exito!");

					//Para acceder a los datos de los pisos se puede recorrer así
					for ( var i in pisos) {

						//Preparamos el registro de un piso
						var piso = JSON.stringify({
							id : pisos[i].ID,
							precio : pisos[i].Precio,
							direccion: pisos[i].Direccion,
							ciudad: pisos[i].Ciudad,
							ano: pisos[i].Anyo,
							estado: pisos[i].Estado,
							foto: pisos[i].Foto
						});
						
						var piso = that.model.find(piso.id);
						if(piso==null){
							that.model.add(piso);
						}else{
							that.model.edit(piso);
						}
					}

					// Actualizamos el modelo y la vista

					// Remitir piso al servidor vía servicios web

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
