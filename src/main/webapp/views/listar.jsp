<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar empleados</title>
</head>
<body>
<form action="/AppWebNomina/" method="get">
        <input type="submit" value="Volver">
    </form>
	<h1>Listar empleados</h1>
	<table border="1">
		<tr>
			<td>DNI</td>
			<td>NOMBRE</td>
			<td>SEXO</td>
			<td>CATEGORIA</td>
			<td>AÑOS</td>
		</tr>
		<a
			href="productos?opcion=meditar&id=<c:out value="${ producto.id}"></c:out>">
			<c:out value="${ producto.id}"></c:out>
		</a>
		<c:forEach var="empleado" items="${lista}">
			<tr>
				<td><a
					href="empleados?opcion=meditar&dni=<c:out value="${ empleado.dni}"></c:out>">
						<c:out value="${ empleado.dni}"></c:out>
				</a></td>
				<td><c:out value="${ empleado.nombre}"></c:out></td>
				<td><c:out value="${ empleado.sexo}"></c:out></td>
				<td><c:out value="${ empleado.categoria}"></c:out></td>
				<td><c:out value="${ empleado.anyos}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>