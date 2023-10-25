<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear Producto</title>
</head>
<body>
	<h1>Crear Producto</h1>
	<form action="empleados" method="post">
		<input type="hidden" name="opcion" value="guardar">
		<table border="1">
			<tr>
				<td>Dni:</td>
				<td><input type="text" name="dni" size="50"></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="50"></td>
			</tr>
			<tr>
				<td>Sexo:</td>
				<td><input type="text" name="sexo" size="50"></td>
			</tr>
			<tr>
				<td>Categoria:</td>
				<td><input type="text" name="categoria" size="50"></td>
			</tr>
			<tr>
				<td>Años:</td>
				<td><input type="text" name="anyos" size="50"></td>
			</tr>
		</table>
		<input type="submit" value="Guardar">
	</form>
</body>
</html>