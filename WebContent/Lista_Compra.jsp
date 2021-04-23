<%@ page import="java.util.*" %>
<html>

<head>
<meta charset="utf-8">
<title>Telecomunicaciones</title>
<style>

body{
	background-color:0099FF;
}
td{
	padding:5px 0;
}
</style>
</head>

<body>
<form name="Formulario_Compra" action="Lista_Compra.jsp">
 
  <h1 style="text-align:center">Granja Melchor</h1>	 
  <p>Articulos a comprar:</p>
  <p>
    <label>
      <input type="checkbox" name="articulos" value="Huevos " >
      Huevos </label>
    <br>
    <label>
      <input type="checkbox" name="articulos" value="Pollo" >
      Pollo </label>
    <br>
     <label>
      <input type="checkbox" name="articulos" value="Gallinaza" >
      Gallinaza </label>
    <br>
     <label>
      <input type="checkbox" name="articulos" value="Concentrado" >
      Concentrado </label>
    <br>
     <label>
      <input type="checkbox" name="articulos" value="Alitas" >
      Alitas </label>
      <br>
       <label>
      <input type="checkbox" name="articulos" value="Yemas" >
      Yemas </label>
  </p>
  <p>
    <input type="submit" name="button" id="button" value="Enviar">
    <br>
  </p>
</form>

<h2>Carro de la compra</h2>

<ul>
<% 

//setAttribute to indicate where we are going to save the session information
//first parameter indicates the session name and the second the object received 
//that the session is going to save(the object we want the session to save)
//In this example it is ListaElementos


List <String> ListaElementos=(List<String>)session.getAttribute("misElementos");

if(ListaElementos==null){
	ListaElementos=new ArrayList <String>();
	session.setAttribute("misElementos", ListaElementos);
}

//el metodo getParemeterValues recibe varios valores a la vez, un array
String [] elementos =request.getParameterValues("articulos");
if(elementos!=null){
	for(String elemTemp:elementos){
		//out.println(("<li>"+elemTemp+"</li>"));
		ListaElementos.add(elemTemp);
		
	}
}
for(String elemTemp:ListaElementos){
	out.println(("<li>"+elemTemp+"</li>"));
}

%>
</ul>
</body>

</html>
