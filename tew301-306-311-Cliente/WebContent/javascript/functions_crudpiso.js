
//Clase que contiene el Modelo de la aplicación.
function Model(){
	//Lista de pisos.
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
	this.list = function(lista) {
		$("#tblList").html("");
		$("#tblList").html( "<thead>" + "<tr>" + "<th></th>"
				+ "<th>ID</th>" + "<th>IDAgente</th>" + "<th>Precio</th>"
				+ "<th>Direccion</th>" + "<th>Cuidad</th>" + "<th>ano</th>" +
				"<th>ano</th>" + "</tr>" +
				+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var piso = lista[i];
			$("#tblList tbody").append("<tr> <td>"
					+ "<img src='icons/edit.png' class='btnEdit'/>"
					+ "<img src='icons/delete.png' class='btnDelete'/> </td>"
					+ "<td>" + piso.id + "</td>" + "<td>" + piso.idagente + "</td>"
					+ "<td>" + piso.precio + "</td>" + "<td>" + piso.direccion + "</td>"
					+ "<td>" + piso.ano + "<td>" + piso.estado +"</td></tr>");
		}
	}

	this.loadpisoFromForm = function() {
		// Cogemos el piso nuevo del formulario.
		var piso = {
				id 		: $("#id").val(),
				idagente: $("#idagente").val(),
				precio 	: $("#precio").val(),
				direccion : $("#direccion").val(),
				direccion 	: $("#ciudad").val(),
				ano 	: $("#ano").val(),
				estado 	: $("#estado").val(),
				foto: $("foto").val()
		};
		return piso;
	}

	this.loadpisoInForm = function(piso) {
		// Pintamos los datos pisos sobre el formularios de alta/edición
		$("#id").val(piso.id);
		$("#idagente").val(piso.idagente);
		$("#precio").val(piso.precio);
		$("#direccion").val(piso.direccion);
		$("#ciudad").val(piso.ciudad);
		$("#ano").val(piso.ano);
		$("#estado").val(piso.estado);
		foto: $("foto").val();
		$("#id").focus(); // Ponemos el foco en el campo Nombre.
	} 

	This.getIdpiso = function(celda) {
		// Accedemos a la fila que está por encima de esta celda
		// (closest('tr'))y despues obtenemos todas las celdas de esa fila
		// (find('tr')) y
		// nos quedamos con la segunda (get(1)) que es la contiene el "id" del
		// piso.

		var id_piso = parseInt(celda.closest('tr').find('td').get(1).innerHTML);
		return id_piso;
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
	// Funcion de inicialización para cargar modelo y vista, definición de manejadores
	this.init = function() {
		// Cargamos la lista de pisos del servicio
		this.model.load();
		// Repintamos la lista de pisos.
		this.view.list(this.model.tbPisos);
		// MANEJADORES DE EVENTOS
		// Manejador del botón submit del formulario de Alta y Edición
		$("#frmCRUDpisos").bind("submit",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function(event) {
			// Si el piso cargado en el formulario tiene ID se invoca al
			// método de 
			// edición
			// sino se invoca al método de alta.
			piso = that.view.loadpisoFromForm();
			if ($("#id").val() == "") {
				that.model.add(piso);
			} else {
				that.model.edit(piso);
			}
			// Volvemos a listar los pisos restantes para que aparezca el
			// nuevo
			// pisos o el editado
			that.view.list(that.model.tbPisos);
		}); 

		// Manejador del enlace de edición de un piso en la tabla
		$("#tblList").on("click", ".btnEdit",
				// Método que gestiona el evento de clickar en el evento
				function(event) {
			// Obtenemos el id del piso seleccionado mediante el icono de
			// edición
			var id_piso = that.view.getIdpiso($(this));
			// Obtenemos el piso con el id_piso
			var piso = that.model.find(id_piso);
			// Cargamos el formulario con los datos del piso seleccionado para
			// editar
			that.view.loadpisoInForm(piso);
		}); 

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
