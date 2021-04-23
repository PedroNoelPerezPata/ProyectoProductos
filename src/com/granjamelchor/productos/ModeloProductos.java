package com.granjamelchor.productos;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;

public class ModeloProductos {
	
	private DataSource origenDatos;
	
	public ModeloProductos(DataSource origenDatos){
		this.origenDatos =origenDatos;
	}
	
	public List<Productos> getProductos() throws Exception{
		List<Productos> productos=new ArrayList<>();
		
		Connection miConexion=null;
		Statement miStatement=null;
		ResultSet miResultset=null;
		
		//Establecer la conexion
		miConexion= origenDatos.getConnection();
		
		//Crear sentencia SQL AND STATEMENT 
		String instruccionSql="SELECT *FROM PRODUCTOS";
		miStatement= miConexion.createStatement();
		
		//Ejecutar SQL 
		miResultset=miStatement.executeQuery(instruccionSql);
		
		//Recorrer el Result Set Obtenido
		
	 
		
		while(miResultset.next()){
			
			String c_art=miResultset.getString("codigoarticulo");
			String seccion=miResultset.getString("seccion");
			String n_art=miResultset.getString("nombrearticulo");
			double precio= miResultset.getDouble("precio");
			//Date fecha= miResultset.getDate("fecha");
			String importado=miResultset.getString("importado");
			String p_orig=miResultset.getString("paisdeorigen");
			
			Productos tempProd=new Productos(c_art,seccion, n_art,precio,  importado, p_orig );
			
			productos.add(tempProd);
		}//CLOSES WHILE
	return productos; 	
	}

	public void agregarElNuevoProducto(Productos nuevoProducto) {
		// TODO Auto-generated method stub
		
		Connection miConexion=null;
		java.sql.PreparedStatement miStatement=null;
		
		//Obtener conexion
		try{
			miConexion=origenDatos.getConnection();
		
		//Crear conexion SQL que inserte el producto (Crear PreparedStatement)
		String sql="INSERT INTO productos (codigoarticulo,seccion,nombrearticulo,precio, importado, paisdeorigen)"+
		"VALUES(?,?,?,?,?,?)";
		
		miStatement=miConexion.prepareStatement(sql);
		
		//establecer parametros para el producto
		miStatement.setString(1,nuevoProducto.getcArt());
		miStatement.setString(2,nuevoProducto.getSeccion());
		miStatement.setString(3,nuevoProducto.getnArt());
		miStatement.setDouble(4,nuevoProducto.getPrecio());
		miStatement.setString(5,nuevoProducto.getImportado());
		miStatement.setString(6,nuevoProducto.getpOrig());

		//Ejecutar la instruccion SQL
		miStatement.execute();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public Productos getProducto(String codigoArticulo) {
		// TODO Auto-generated method stub
		
		Productos elProducto=null;
		Connection miConexion=null;
		java.sql.PreparedStatement miStatement=null;
		ResultSet miResultset=null;
		String cArticulo=codigoArticulo;
		
		try{
		//Establecer la conexion con la BBDD
		
		miConexion=origenDatos.getConnection();	
			
		//Crear sql que busque el producto
		
		String sql="SELECT * FROM PRODUCTOS WHERE CODIGOARTICULO=?";
		
		//Crear la consulta preparada
		
		miStatement=miConexion.prepareStatement(sql);
		//Establecer los parametro
		
		miStatement.setString(1, cArticulo);

		//Ejecutar la consulta
		
		miResultset=miStatement.executeQuery();
		
		//Obtener los datos de respuesta
		if(miResultset.next()){
			String c_art=miResultset.getString("codigoarticulo");
			String seccion=miResultset.getString("seccion");
			String n_art=miResultset.getString("nombrearticulo");
			double precio= miResultset.getDouble("precio");
			//Date fecha= miResultset.getDate("fecha");
			String importado=miResultset.getString("importado");
			String p_orig=miResultset.getString("paisdeorigen");
			
			elProducto=new Productos(c_art, seccion, n_art,precio,  importado, p_orig );
			 
		}else{
			throw new Exception("No se encontro el producto con codigo="+cArticulo);
		}
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
		return elProducto;
	}



public void actualizarProducto(Productos productoActualizado) throws Exception{
	// TODO Auto-generated method stub
	Connection miConexion=null;
	java.sql.PreparedStatement miStatement=null;
	
	//Establecer la conexion 
	miConexion=origenDatos.getConnection();
	//Crear sentencia SQL 
	String sql="UPDATE productos SET seccion=?, nombrearticulo=?, precio=?,"+
			"importado=?, paisdeorigen=? where codigoarticulo=?"; 
	
	//Crear la consulta preparada
	miStatement=miConexion.prepareStatement(sql);

	//Establecer los parametros
	miStatement.setString(1, productoActualizado.getSeccion());
	miStatement.setString(2, productoActualizado.getnArt());
	miStatement.setDouble(3, productoActualizado.getPrecio());
	miStatement.setString(4, productoActualizado.getImportado());
	miStatement.setString(5, productoActualizado.getpOrig());
	

	//Ejecutar la instruccion SQL
	miStatement.execute();	
	 }	
}


