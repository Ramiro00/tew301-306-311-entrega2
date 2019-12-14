
//Clase que contiene el Modelo de la aplicación.
function Model(){
	//Lista de alumnos.
	this.tbPisos = null;

//	Carga los datos del servicio sobreescribiendo el dato this.tbAlumnos.
	this.load = function() {
		this.tbPisos = PisosServicesRs.getPisos();
	}
//	Llamamos al servicio de alta de alumno
	this.add = function(piso) {
//		Llamamos al servicio de alta de alumno
		PisosServicesRs.savePiso({
			$entity : piso,
			$contentType : "application/json"
		});
//		Recargamos la lista de alumnos.
		this.load();
	}
//	Actualización de un alumno existente
	this.edit = function(piso) {
//		Llamamos al servicio de edicion de alumno
		PisosServicesRs.updatePiso({
			$entity : piso,
			$contentType : "application/json"	
		});
//		Recargamos la lista de alumnos.
		this.load();
	} 

//	Eliminación un alumno existente
	this.remove = function(id_piso) {
//		Llamamos al servicio de borrado de alumno
		PisosServicesRs.deletePiso({
			id : id_piso
		});
//		Recargamos la lista de alumnos.
		this.load();
	}

	this.find = function(id_piso) {
		function checkPiso(obj) {
			return obj.id == id_piso;
		}
//		Buscamos los datos del alumno cuyo id_alumno sea el mismo que el
//		seleccionado
		var piso = this.tbPisos.find(checkPiso);
		return piso;
	} 
};

function View(){
	this.list = function(lista) {
		$("#tblList").html("");
		$("#tblList").html( "<thead>" + "<tr>" + "<th></th>"
				+ "<th>ID</th>" + "<th>IDAgent</th>" + "<th>Direccion</th>"
				+ "<th>Ciudad</th>" + "<th>Precio</th>" + "<th>Estado</th>" + "</tr>"
				+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var piso = lista[i];
			$("#tblList tbody").append("<tr> <td>"
					+ "<img src='icons/edit.png' class='btnEdit'/>"
					+ "<img src='icons/delete.png' class='btnDelete'/> </td>"
					+ "<td>" + piso.id + "</td>" + "<td>" + piso.idagente + "</td>"
					+ "<td>" + piso.direccion + "</td>" + "<td>" + piso.ciudad + "</td>"
					+ "<td>" + piso.precio + "<td>" + piso.estado + "</td>" + "</td></tr>");
		}
	}

	this.loadPisoFromForm = function() {
		// Cogemos el alumno nuevo del formulario.
		var piso = {
				id : $("#id").val(),
				idagente : $("#idagente").val(),
				direccion : $("#direccion").val(),
				ciudad : $("#ciudad").val(),
				precio : $("#precio").val(),
				estado : $("#estado").val()
		};
		return piso;
	}

	this.loadPisoInForm = function(piso) {
		// Pintamos los datos alumnos sobre el formularios de alta/edición
		$("#id").val(piso.id);
		$("#idagente").val(piso.idagent);
		$("#direccion").val(piso.direccion);
		$("#ciudad").val(piso.ciudad);
		$("#precio").val(piso.precio);
		$("#estado").val(piso.estado);
		$("#id").focus(); // Ponemos el foco en el campo Nombre.
	} 

	This.getIdPiso = function(celda) {
		// Accedemos a la fila que está por encima de esta celda
		// (closest('tr'))y despues obtenemos todas las celdas de esa fila
		// (find('tr')) y
		// nos quedamos con la segunda (get(1)) que es la contiene el "id" del
		// alumno.

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
		// Cargamos la lista de alumnos del servicio
		this.model.load();
		// Repintamos la lista de alumnos.
		this.view.list(this.model.tbPisos);
		// MANEJADORES DE EVENTOS
		// Manejador del botón submit del formulario de Alta y Edición
		$("#frmCRUDPisos").bind("submit",
				// Método que gestiona el evento de clickar el botón submit del
				// formulario
				function(event) {
			// Si el alumno cargado en el formulario tiene ID se invoca al
			// método de 
			// edición
			// sino se invoca al método de alta.
			piso = that.view.loadPisoFromForm();
			if ($("#id").val() == "") {
				that.model.add(piso);
			} else {
				that.model.edit(piso);
			}
			// Volvemos a listar los alumnos restantes para que aparezca el
			// nuevo
			// alumnos o el editado
			that.view.list(that.model.tbPisos);
		}); 
	}

	// Manejador del enlace de edición de un alumno en la tabla
	$("#tblList").on("click", ".btnEdit",
			// Método que gestiona el evento de clickar en el evento
			function(event) {
		// Obtenemos el id del alumno seleccionado mediante el icono de
		// edición
		var id_piso = that.view.getIdPiso($(this));
		// Obtenemos el alumno con el id_alumno
		var piso = that.model.find(id_piso);
		// Cargamos el formulario con los datos del alumno seleccionado para
		// editar
		that.view.loadPisoInForm(piso);
	}); 
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
