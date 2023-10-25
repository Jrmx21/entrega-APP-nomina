package Controlador;

public class Empleado extends Persona {
	private int categoria;
	public int anyos;
	public int numEmp;

	/**
	 * Constructor de la clase Empleado
	 *
	 * @param nombre El nombre del empleado.
	 * @param dni    El número de DNI del empleado.
	 * @param sexo   El sexo del empleado ('M' para masculino, 'F' para femenino).
	 */

	public Empleado(String nombre, String dni, char sexo) {
		super(nombre, dni, sexo);
		this.categoria = 1;
		this.anyos = 0;
	}

	@Override
	public String toString() {
		return "Empleado [categoria=" + categoria + ", anyos=" + anyos + ", numEmp=" + numEmp + ", nombre=" + nombre
				+ ", dni=" + dni + ", sexo=" + sexo + "]";
	}

	public int getNumEmp() {
		return numEmp;
	}

	/**
	 * Constructor de la clase Empleado que inicializa un objeto Empleado con los
	 * datos proporcionados.
	 *
	 * @param nombre    El nombre del empleado.
	 * @param dni       El número de DNI del empleado.
	 * @param sexo      El sexo del empleado ('M' para masculino, 'F' para
	 *                  femenino).
	 * @param categoria La categoría del empleado (debe estar en el rango de 1 a
	 *                  10).
	 * @param anyos     La cantidad de años de experiencia del empleado (debe ser
	 *                  mayor o igual a 1).
	 * @throws DatosNoCorrectosException Si los datos de categoría o años no están
	 *                                   en el rango correcto.
	 */
	public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) {
		super(nombre, dni, sexo);
		numEmp++;
		if (categoria >= 0 && categoria <= 10) {
			this.categoria = categoria;

		}
		if (anyos >= 0) {
			this.anyos = anyos;
		}
	}

	public void setCategoria(int nuevaCategoria) {
		this.categoria = nuevaCategoria;
	}

	public int getCategoria() {
		return categoria;
	}

	/**
	 * Incrementa la cantidad de años trabajados del empleado en uno.
	 */
	public void incrAnyo() {
		anyos++;
	}

	/**
	 * Imprime los detalles del empleado, incluyendo nombre, sexo, DNI, categoría y
	 * años trabajados.
	 */
	public void imprime() {
		System.out.println("Nombre: " + nombre);
		System.out.println("Sexo: " + sexo);
		System.out.println("Dni: " + dni);
		System.out.println("Categoria: " + categoria);
		System.out.println("Años trabajados: " + anyos);
	}

	public String getImprime() {
		return nombre + ", " + sexo + ", " + dni + ", " + categoria + ", " + anyos;
	}

	public int getAnyos() {
		return anyos;
	}

	public void setAnyos(int anyos) {
		this.anyos = anyos;
	}

	public void setNumEmp(int numEmp) {
		this.numEmp = numEmp;
	}

}
