
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.*, com.granjamelchor.productos.*"  %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type "text/css">

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
	</tr>
	
	<!-- JSP tags to use them, you gotta import the next libraries o lib of WebContent, they help you
	out for not using JAVA code on a jps file, they replace the JAVA scriplets
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 	
	 -->
	<c:forEach var="tempProd" items="${LISTAPRODUCTOS}">
	
	<tr>
		<td class="filas">${tempProd.cArt}</td>
		<td class="filas">${tempProd.seccion}</td>
		<td class="filas">${tempProd.nArt}</td>
		<td class="filas">${tempProd.precio}</td>
		<td class="filas">${tempProd.importado}</td>
		<td class="filas">${tempProd.pOrig}</td>
	</tr>
	
	</c:forEach> 
	
	</table>
	
	<div id="contenedorBoton">
		<input type="button" value="Insertar" onclick="window.location.href='inserta_producto.jsp'"/>	
	
	</div>
	
</body>
</html>
