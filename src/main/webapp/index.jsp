<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menú de Opciones</title>
</head>
<body>
	<h1>Menu de Opciones Productos</h1>
	<table border="1">
		<tr>
			<td><a href="empleados?opcion=listar"> Listar empleados</a></td>
		</tr>
		<tr>
			<td><a href="empleados?opcion=actualizar"> Actualizar
					salarios</a></td>
		</tr>
	</table>
	<h2>Buscar Empleado por DNI</h2>
	<form action="empleados?opcion=mostrarSalario" method="post">
		DNI: <input type="text" name="dni" required> <input
			type="submit" value="Buscar">
	</form>

	<h2>Búsqueda de Empleados</h2>

	<!-- Formulario para realizar la búsqueda -->
	<form action="empleados" method="get">
		<!-- Agrega un parámetro a la URL para indicar la opción -->
		<input type="hidden" name="opcion" value="resultadosBusqueda">
		<label>Buscar por:</label> <select name="criterio">
			<option value="dni">DNI</option>
			<option value="nombre">Nombre</option>
			<option value="sexo">Sexo</option>
			<option value="categoria">Categoria</option>
			<option value="anyos">Años</option>
		</select> <input type="text" name="valor" required> <input
			type="submit" value="Buscar">
	</form>
</body>
</html>