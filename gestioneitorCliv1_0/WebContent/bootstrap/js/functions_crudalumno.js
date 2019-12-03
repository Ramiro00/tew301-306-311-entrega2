/*!
 * Bootstrap v3.3.5 (http://getbootstrap.com)
 * Copyright 2011-2015 Twitter, Inc.
 * Licensed under the MIT license
 */



//Clase que contiene el Modelo de la aplicación.
function Model() {
	// Lista de alumnos.
	this.tbAlumnos = null;
};

// Carga los datos del servicio sobreescribiendo el dato this.tbAlumnos.
this.load = function() {
	this.tbAlumnos = AlumnosServicesRs.getAlumnos();
}
// Llamamos al servicio de alta de alumno
this.add = function(alumno) {
	// Llamamos al servicio de alta de alumno
	AlumnosServicesRs.saveAlumno({
		$entity : alumno,
		$contentType : "application/json"
	});
	// Recargamos la lista de alumnos.
	this.load();
}
// Actualización de un alumno existente: PENDIENTE DE IMPLEMENTAR
this.edit = function(alumno) {
	// Llamamos al servicio de editar alumno
	AlumnosServicesRs.updateAlumno({
		id : id_alumno
	});
	// Recargamos la lista de alumnos.
	this.load();	
}

// Eliminación un alumno existente
this.remove = function(id_alumno) {
	// Llamamos al servicio de borrado de alumno
	AlumnosServicesRs.deleteAlumno({
		id : id_alumno
	});
	// Recargamos la lista de alumnos.
	this.load();
}
this.find = function(id_alumno) {
	function checkAlumno(obj) {
		return obj.id == id_alumno;
	}
	// Buscamos los datos del alumno cuyo id_alumno sea el mismo que el
	// seleccionado
	var alumno = this.tbAlumnos.find(checkAlumno);
	return alumno;
}