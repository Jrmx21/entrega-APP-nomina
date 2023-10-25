package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmpleadoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	public boolean guardar(Empleado empleado) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO productos (id, nombre, cantidad, precio, fecha_crear,fecha_actualizar) VALUES(?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, empleado.getNombre());
			statement.setString(2, String.valueOf(empleado.getSexo()));
			statement.setInt(4, empleado.getAnyos());
			statement.setString(5, empleado.getDni());

			estadoOperacion = statement.executeUpdate() > 0;

			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// editar producto
		 public boolean editar(Empleado empleado) throws SQLException {
		  String sql = null;
		  estadoOperacion = false;
		  connection = obtenerConexion();
		  try {
		   connection.setAutoCommit(false);
		   sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, sexo=?,fecha_actualizar=? WHERE dni=?";
		   statement = connection.prepareStatement(sql);
		 
		   statement.setString(1, empleado.getNombre());
		   statement.setString(2, String.valueOf(empleado.getSexo()));
		   statement.setInt(3, empleado.getCategoria());
		   statement.setInt(4, empleado.getAnyos());
		   statement.setString(5, empleado.getDni());
		 
		   estadoOperacion = statement.executeUpdate() > 0;
		   connection.commit();
		   statement.close();
		   connection.close();
		 
		  } catch (SQLException e) {
		   connection.rollback();
		   e.printStackTrace();
		  }
		 
		  return estadoOperacion;
		 }
//		 
	// obtener lista de productos

	 public List<Double> obtenerNominasPorDNI(String dniEmpleado) {
	        List<Double> nominas = new ArrayList<>();

	        try (Connection connection = ConnectionDB.getConnection()) {
	            String sql = "SELECT n.sueldo FROM nominas n INNER JOIN empleados e ON n.dni_empleado = e.dni WHERE e.dni = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setString(1, dniEmpleado);
	                
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        double sueldo = resultSet.getDouble("sueldo");
	                        nominas.add(sueldo);
	                        System.out.println("obtenerNominasPorDni success");
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Manejo de errores: Considera un manejo más robusto en producción
	        }

	        return nominas;
	    }
	// obtener producto
	// obtener lista de productos
	public List<Empleado> obtenerEmpleados() throws SQLException {
	    ResultSet resultSet = null;
	    List<Empleado> listaEmpleados = new ArrayList<>();

	    String sql = null;
	    estadoOperacion = false;
	    connection = obtenerConexion();

	    try {
	        sql = "SELECT * FROM empleados";
	        statement = connection.prepareStatement(sql);
	        resultSet = statement.executeQuery();  // Corregir aquí
	        while (resultSet.next()) {
	            Empleado e = new Empleado(resultSet.getString(1), resultSet.getString(2),
	                    resultSet.getString(3).toCharArray()[0], resultSet.getInt(4), resultSet.getInt(5));

	            listaEmpleados.add(e);
	        System.out.println("obtenerEmpleados sucess");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Asegúrate de cerrar los recursos en el bloque finally
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	    System.out.println("listar empleado "+listaEmpleados);
	    return listaEmpleados;
	}
	  public List<Empleado> buscarEmpleadosPorCriterio(String criterio, String valor) throws SQLException {
	        List<Empleado> empleados = new ArrayList<>();

	        try (Connection connection = ConnectionDB.getConnection()) {
	            String sql = "SELECT * FROM empleados WHERE " + criterio + " = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setString(1, valor);
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                    	 Empleado empleado = new Empleado(resultSet.getString(1), resultSet.getString(2),
	         	                    resultSet.getString(3).toCharArray()[0], resultSet.getInt(4), resultSet.getInt(5));
	                    	 System.out.println("buscarEmpleadosPorCriterio sucess");
	                        empleados.add(empleado);
	                    }
	                }
	            }
	        }
	        return empleados;
	    }


	// obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return ConnectionDB.getConnection();
	}

}