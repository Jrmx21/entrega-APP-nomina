package Controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Administra peticiones para la tabla", urlPatterns = "/empleados")
public class EmpleadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final char E = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("empleados?" + buscarEmpleadosPorCriterio("dni", "20099558L"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String opcion = request.getParameter("opcion");

		if (opcion.equals("resultadosBusqueda")) {

			try {
				List<Empleado> empleados = realizarBusqueda(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (opcion.equals("listar")) {
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			List<Empleado> lista = new ArrayList<>();
			try {
				lista = empleadoDAO.obtenerEmpleados();
				for (Empleado empleado : lista) {
					System.out.println(empleado);
				}
				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error al obtener empleados: " + e.getMessage());
			}

			System.out.println("Usted a presionado la opcion listar");
		}

		else if (opcion.equals("meditar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id: " + id);
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			Empleado e = new Empleado("", "", E);
			try {
				e = (Empleado) empleadoDAO.obtenerEmpleados();
				System.out.println(e);
				request.setAttribute("employees", e);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException error) {
				// TODO Auto-generated catch block
				error.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	private double obtenerSalarioDesdeBD(String dni) {
//		double salario = 0.0;
//
//		// Configura tu conexión a la base de datos aquí usando ConnectionDB
//		try (Connection connection = ConnectionDB.getConnection()) {
//			// Consulta SQL para obtener el sueldo asociado al DNI de la tabla empleados
//			String sql = "SELECT n.sueldo FROM nominas n INNER JOIN empleados e ON n.dni_empleado = e.dni WHERE e.dni = ?";
//			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//				preparedStatement.setString(1, dni);
//
//				try (ResultSet resultSet = preparedStatement.executeQuery()) {
//					if (resultSet.next()) {
//						salario = resultSet.getDouble("sueldo");
//					}
//					System.out.println(" obtenerSalarioDesdeBD success");
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace(); // Manejo de errores: ¡Considera un manejo más robusto en un entorno de
//									// producción!
//		}
//
//		return salario;
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recuperar el DNI del formulario
		String dni = request.getParameter("dni");

		// Llamar a un método para obtener las nominas desde la base de datos
		List<Double> nominas = obtenerNominasDesdeBD(dni);

		// Establecer las nominas como un atributo para la página JSP
		request.setAttribute("nominas", nominas);

		// Redirigir a la página JSP de visualización
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mostrarSalario.jsp");
		dispatcher.forward(request, response);
	}

	private List<Double> obtenerNominasDesdeBD(String dni) {
		// Llamar al método de EmpleadoDAO para obtener las nominas
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		System.out.println(" obtenerNominasDesdeBD success");
		return empleadoDAO.obtenerNominasPorDNI(dni);

	}

	public static void actualizarSalario() {

		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		List<Empleado> empleados = null;
		try {
			empleados = empleadoDAO.obtenerEmpleados();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Nomina n = new Nomina();
		for (Empleado empleado : empleados) {
			int nuevoSueldo = n.sueldo(empleado);
			try (Connection connection = ConnectionDB.getConnection()) {
				String sql = "UPDATE nominas SET sueldo = ? WHERE dni = (SELECT dni FROM empleados WHERE dni_empleado = ?)";
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.setInt(2, nuevoSueldo);
					preparedStatement.setString(1, empleado.getDni());
					preparedStatement.executeUpdate();
					System.out.println(" actualizarSalario success");
				}
			} catch (SQLException e) {
				e.printStackTrace(); // Manejo de errores: Considera un manejo más robusto en producción
			}
		}
	}

	public List<Empleado> realizarBusqueda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// Recuperar los parámetros del formulario de búsqueda
		String criterio = request.getParameter("criterio");
		String valor = request.getParameter("valor");
		List<Empleado> empleados = buscarEmpleadosPorCriterio(criterio, valor);
		// Llamar al método para buscar empleados en la base de datos
		;
		// Imprimir información de depuración
		for (Empleado empleado : empleados) {
			System.out.println("empleados: " + empleado.toString());
			System.out.println("lista empleados: " + empleados);
		}
		// Establecer los resultados como atributo para la página JSP
		request.setAttribute("empleados", empleados);

		// Redirigir a la página JSP de resultados
		request.getRequestDispatcher("/views/resultadosBusqueda.jsp").forward(request, response);
		return empleados;

	}

	private List<Empleado> buscarEmpleadosPorCriterio(String criterio, String valor) throws SQLException {
		// Lógica para buscar empleados según el criterio y valor proporcionados
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		System.out.println(" buscarEmpleadoPorCriterio success");
		return empleadoDAO.buscarEmpleadosPorCriterio(criterio, valor);

	}
}