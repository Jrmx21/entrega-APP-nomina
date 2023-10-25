<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Resultados de Búsqueda</title>
</head>
<body>
<form action="/AppWebNomina/" method="get">
        <input type="submit" value="Volver">
    </form>
    <h2>Resultados de Búsqueda</h2>

    <!-- Verificar si hay empleados para mostrar -->
    <c:if test="${not empty empleados}">
        <!-- Mostrar los resultados obtenidos -->
        <table border="1">
            <tr>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Sexo</th>
                <th>Categoría</th>
                <th>Años</th>
                <!-- Agrega más columnas según sea necesario -->
            </tr>
            
            <!-- Iterar sobre la lista de empleados con un bucle for -->
            <c:forEach var="i" begin="0" end="${empleados.size() - 1}">
                <tr>
                    <td>${empleados[i].getDni()}</td>
                    <td>${empleados[i].getNombre()}</td>
                    <td>${empleados[i].getSexo()}</td>
                    <td>${empleados[i].getCategoria()}</td>
                    <td>${empleados[i].getAnyos()}</td>
                    <!-- Agrega más campos según sea necesario -->
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <!-- Mostrar un mensaje si no hay resultados -->
    <c:if test="${empty empleados}">
        <p>No se encontraron resultados para la búsqueda.</p>
    </c:if>
</body>
</html>
