package com.granjamelchor.productos;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	//Variable tipo ModeloProductos para obtener datos del modelo
	private ModeloProductos modeloProductos;
	
	//Nombre del recurso tipo pool y variable tipo miPool
	@Resource(name="jdbc/Productos")
    private DataSource miPool;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try{
			modeloProductos=new ModeloProductos(miPool);
		}catch(Exception e){
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//LEER PARAMETRO QUE VIENE DEL FORMULARIO inserta_producto.jsp
		String elComando=request.getParameter("instruccion");
		
		//SI NO SE RECIBE PARAMETRO SOLO LISTAR PRODUCTOS
		//Obtener la lista de productos desde el modelo
		
		if(elComando==null) elComando="listarBBDD";
		
		
		//REDIRIGIR EL FLUJO DE EJECUCION AL METODO PARA INSERTAR
		switch (elComando){
		
		case "listarBBDD":
			obtenerProductos(request, response);
			break;
			
		case "insertarBBDD":
			agregarProductos(request, response);
			break;
			
		case "cargar":
			try {
				cargaProductos(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break;
		case "actualizarBBDD":
			try {
				actualizaProductos(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default:obtenerProductos(request, response);
		}	
		
	}//cierra doGet

	private void actualizaProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//Leer los datos que vienen del formulario actualizar
		
		String CodArticulo=request.getParameter("CArt");
		String Seccion=request.getParameter("seccion");
		String NombreArticulo=request.getParameter("NArt");
		double Precio=Double.parseDouble(request.getParameter("precio"));
		String Importado=request.getParameter("importado");
		String PaisOrigen=request.getParameter("POrig");
		
		
		//Crear un objeto de tipo producto con la info del formulario
		
		Productos ProductoActualizado=new Productos(CodArticulo,Seccion,NombreArticulo,Precio,Importado,PaisOrigen);
		
		//Actualizar la BBDD con la info del obj Producto
		
		modeloProductos.actualizarProducto(ProductoActualizado);
		
		//Volver al listado con la info actualizar
		
		obtenerProductos(request,response);
	}

	private void cargaProductos(HttpServletRequest request, HttpServletResponse response)throws Exception { 
		// TODO Auto-generated method stub
		
		//Leer el Codigo Articulo que viene del listado
		
		String codigoArticulo=request.getParameter("CArticulo");
		
		//Enviar codigo articulo al Modelo
		
		Productos elProducto=modeloProductos.getProducto(codigoArticulo);
		
		//Colocar atributo correspondiente al modelo
		
		request.setAttribute("ProductoActualizar", elProducto);
		
		//Enviar producto al formulario de actualizar (jsp)
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/actualizarProducto.jsp");
		
		dispatcher.forward(request, response);
	}

	private void agregarProductos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		//leer informacion del producto que viene del formulario
		
		String CodArticulo=request.getParameter("CArt");
		String Seccion=request.getParameter("seccion");
		String NombreArticulo=request.getParameter("NArt");
		double Precio=Double.parseDouble(request.getParameter("precio"));
		String Importado=request.getParameter("importado");
		String PaisOrigen=request.getParameter("POrig");
		
		//Crear objeto de tipo producto
		
		Productos NuevoProducto=new Productos(CodArticulo,Seccion,NombreArticulo,Precio,Importado,PaisOrigen);
		//Enviar el objeto al modelo y despues insertar el objeto producto en la BD
		
		modeloProductos.agregarElNuevoProducto(NuevoProducto);
		//Volver al listado de Productos 
		
		obtenerProductos(request,response);
		
	}

	private void obtenerProductos(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Productos> productos;
		
		try{
			productos = modeloProductos.getProductos();
			
		//Agregar esta lista al Request 
			request.setAttribute("LISTAPRODUCTOS", productos);
		// Enviar el Request a la pagina JSP 
			RequestDispatcher miDispatcher=request.getRequestDispatcher("/ListaProductos.jsp");
			
			miDispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

