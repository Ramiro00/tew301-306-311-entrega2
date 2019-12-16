//Clase que contiene el Modelo de la aplicacion.
function Model() {
	// Lista de pisos.
	this.tbPisos = null;
	this.tbPisosordenados = null;

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
		// Recargamos la lista de Pisos.
		this.load();
	}
	// Actualización de un piso existente
	this.edit = function(piso) {
		// Llamamos al servicio de edit de piso
		PisosServicesRs.updatePiso({
			$entity : piso,
			$contentType : "application/json"
		});
	}
	// Eliminación un piso existente
	this.remove = function(id) {
		// Llamamos al servicio de borrado de piso
		PisosServicesRs.deletePiso({
			id : id
		});
		// Recargamos la lista de Pisos.
		this.load();
	}
	this.find = function(id) {
		function checkPiso(obj) {
			return obj.id == id;
		}
		var piso = this.tbPisos.find(checkPiso);
		return piso;
	}
/*    String nombre = file.getOriginalFilename();
    String tipo   = file.getContentType();
    Long tamano   = file.getSize();
    byte[] pixel  = file.getBytes();*/
};

// Clase que contiene la gestión de la capa Vista
function View() {
	this.list = function(lista) {
		$("#tblList").html("");
		$("#tblList").html(
				"<thead>" + "<tr>" + "<th></th>" + "<th>ID</th>"
						+ "<th>ID.AGENTE</th>" + "<th>PRECIO</th>"
						+ "<th>DIRECCION</th>" + "<th>CIUDAD</th>" 
						+ "<th>AÑO</th>" + "<th>ESTADO</th>"
						+ "<th>FOTO</th>" + "</tr>"
						+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var piso = lista[i];
			$("#tblList tbody")
					.append(
							"<tr> <td>"
							+ "<img src='icons/edit.png' class='btnEdit'/>"
							+ "<img src='icons/delete.png' class='btnDelete'/> </td>"
							+ "<td>" + piso.id + "</td>" + "<td>"
							+ piso.idagente + "</td>" + "<td>"
							+ piso.precio + "</td>" + "<td>"
							+ piso.direccion + "</td>" + "<td>"
							+ piso.ciudad + "</td>" + "<td>"
							+ piso.ano + "</td>" + "<td>"
							+ piso.estado + "</td>" + "<td>"
							+ piso.foto + "</td></tr>");			

		}
	}

	this.loadPisoFromForm = function() {
		// Cogemos el piso nuevo del formulario.
		var piso = {
			id : $("#id").val(),
			//Hasta no tener login no se cambia y de asigna todo al agente 1
			//idagente : $("#idagente").val(),
			idagente : 1,
			precio : $("#precio").val(),
			direccion : $("#direccion").val(),
			ciudad : $("#ciudad").val(),
			ano : $("#ano").val(),
			estado : $('#estado').val(),
			foto : $("#foto").val()
		};
		return piso;
	}

	this.loadPisoInForm = function(piso) {
		// Pintamos los datos Pisos sobre el formularios de alta/edición
		$("#id").val(piso.id);
		$("#idagente").val(piso.idagente);
		$("#precio").val(piso.precio);
		$("#direccion").val(piso.direccion);
		$("#ciudad").val(piso.ciudad);
		$("#estado").val(piso.estado);
		$("#ano").val(piso.ano);
		$("#foto").val(piso.foto);
		$("#idagente").focus(); // Ponemos el foco en el campo Nombre.
	}

	this.getIdPiso = function(celda) {
		// Accedemos a la fila que está por encima de esta celda
		// (closest('tr'))y despues obtenemos todas las celdas de esa fila
		// (find('tr')) y
		// nos quedamos con la segunda (get(1)) que es la contiene el "id" del
		// piso.
		var id = parseInt(celda.closest('tr').find('td').get(1).innerHTML);
		return id;
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
		// Cargamos la lista de Pisos del servicio
		that.model.load();
		// Repintamos la lista de Pisos.
		that.view.list(this.model.tbPisos);
		// MANEJADORES DE EVENTOS

		// Manejador del botón submit del formulario de Alta y Edición
		$("#frmCRUDPisos").bind("submit",
		// Método que gestiona el evento de clickar el botón submit del
		// formulario
		function(event) {
			// Si el piso cargado en el formulario tiene ID se invoca al
			// método de edición sino se invoca al método de alta.
			var piso = that.view.loadPisoFromForm();
			if ($("#id").val() == "") {
				that.model.add(piso);
			} else {
				that.model.edit(piso);
			}
			// Refrescar listado Pisos
			that.view.list(that.model.tbPisos);
			
		});
		$("#btnSave").click(function(){
			var piso = that.view.loadPisoFromForm();
			
			if ($("#id").val() == "") {
				that.model.add(piso);
			} else {
				that.model.edit(piso);
			}
			// Refrescar listado Pisos
			that.view.list(that.model.tbPisos);

		})
		
		//Ordenar de menor a mayor
		$("#btnOrdenar").click(function(){
			var p = that.model.tbPisos;
			  var i, j, aux;
		        for (i = 0; i < p.length - 1; i++) {
		            for (j = 0; j < p.length - i - 1; j++) {
		                if (p[j + 1].precio < p[j].precio) {
		                    aux = p[j + 1]
		                    p[j + 1] = p[j];
		                    p[j] = aux;
		                }
		            }
		        }
			
			// Refrescar listado Pisos
			that.view.list(that.model.tbPisos);

		})
		
		//Ordenar de mayor a menor
		$("#btnOrdenar2").click(function(){
			var p = that.model.tbPisos;
			  var i, j, aux;
		        for (i = 0; i < p.length - 1; i++) {
		            for (j = 0; j < p.length - i - 1; j++) {
		                if (p[j + 1].precio > p[j].precio) {
		                    aux = p[j + 1]
		                    p[j + 1] = p[j];
		                    p[j] = aux;
		                }
		            }
		        }
			
			// Refrescar listado Pisos
			that.view.list(that.model.tbPisos);

		})
		
		//Filtrar por ciudad
		$("#btnFiltrar").click(function(){
			var p = that.model.tbPisos;
			  var i;
			  var ciudad = $("#ciudad");
			  if(ciudad.val()==""){
				  that.model.load();
			  }
		        for (i = 0; i < p.length - 1; i++) {
		            if (p[i].ciudad.includes(ciudad.val()) ){
		            	
		            }else{
		            	p.splice(i);
		            }
		        }
			
			// Refrescar listado Pisos
			that.view.list(that.model.tbPisos);

		})
		
		
		// Borrado de un piso en la tabla
		$("#tblList").on("click", ".btnDelete",
		// Método que gestiona el evento de clickar en el evento
		function(event) {
			// Obtenemos el id del piso seleccionado mediante el icono de
			// edición
			var id = that.view.getIdPiso($(this));
			// Llamamos al modelo para borrar
			that.model.remove(id);
			that.view.list(that.model.tbPisos);
		});
		
		// Edición de un piso en la tabla
		$("#tblList").on("click", ".btnEdit",
		// Método que gestiona el evento de clickar en el evento
		function(event) {
			// Obtenemos el id del piso seleccionado mediante el icono de
			// edición
			var id = that.view.getIdPiso($(this));
			// Obtenemos el piso con el id_Piso
			var piso = that.model.find(id);
			// Cargamos el formulario con los datos del piso seleccionado para
			// editar
			//console.log("paso");
			that.view.loadPisoInForm(piso);
			that.view.list(that.model.tbPisos);
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