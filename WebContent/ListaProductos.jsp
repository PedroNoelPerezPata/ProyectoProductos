<%@ page language="java" contentType="text/html; charset=ISO-8859-1"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">

.cabecera{
	font-size:1.2em;
	font-weight:bold;
	color:#FFFFFF;
	background-color: #08088A;
}

.filas{
	text-aligh:center;
	background-color: #5882FA;
}

table{
	float:left;
}

#contenedorBoton{
	margin-left:700px;
}

</style>
</head>

<body>
<table>
	
	<tr>
		<td class="cabecera">Codigo Articulo</td>
		<td class="cabecera">Seccion</td>
		<td class="cabecera">Nombre Articulo</td>
		<td class="cabecera">Precio</td>
		<td class="cabecera">Importado</td>
		<td class="cabecera">Pais de Origen</td>
		<td class="cabecera">Accion</td>
		
	</tr>

	<c:forEach var="tempProd" items="${LISTAPRODUCTOS}">
	
	<!--  Link para cada producto con su campo clave -->
	
	<c:url var="LinkTemp" value="ControladorProductos">
		<c:param name="instruccion" value="cargar"></c:param>
		<c:param name="CArticulo" value="${tempProd.cArt}"></c:param>
		
	</c:url>
	<tr>
		<td class="filas">${tempProd.cArt}</td>
		<td class="filas">${tempProd.seccion}</td>
		<td class="filas">${tempProd.nArt}</td>
		<td class="filas">${tempProd.precio}</td>
		<td class="filas">${tempProd.importado}</td>
		<td class="filas">${tempProd.pOrig}</td>
		<td class="filas"><a href="${LinkTemp}">Actualizar</a></td>
	</tr>
	</c:forEach> 
</table>
	<div id="contenedorBoton">
		<input type="button" value="Insertar" onclick="window.location.href='inserta_producto.jsp'"/>	
	
	</div>
	
</body>
</html>