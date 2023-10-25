package Controlador;

public class Nomina {

	private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
			230000 };

	/**
	 * Calcula el sueldo de un empleado basado en su categoría y años de
	 * experiencia.
	 *
	 * @param empleado El objeto Empleado para el cual se calculará el sueldo.
	 * @return El sueldo calculado para el empleado.
	 */

	public int sueldo(Empleado empleado) {
		int sueldo = SUELDO_BASE[empleado.getCategoria()] + 5000 * empleado.anyos;
		return sueldo;
	}
	
}
