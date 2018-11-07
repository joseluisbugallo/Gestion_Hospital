package business;

import java.util.List;

import business.dto.EmpleadoDto;
import business.dto.VacacionesDto;
import persistence.DataVacaciones;

public class VacacionesController {

	private DataVacaciones dv = new DataVacaciones();

	public void addVacaciones(VacacionesDto vacaciones) {
		dv.add(vacaciones);
	}

	private boolean[] obtenerDiasSemana(String dias) {
		String[] diasSemanaString;
		diasSemanaString = dias.split(" ");
		boolean[] days = new boolean[7];
		for (String s : diasSemanaString) {
			if (s.toLowerCase().equals("domingo")) {
				days[0] = true;
			} else if (s.toLowerCase().equals("lunes")) {
				days[1] = true;

			} else if (s.toLowerCase().equals("martes")) {
				days[2] = true;

			} else if (s.toLowerCase().equals("miércoles")) {
				days[3] = true;

			} else if (s.toLowerCase().equals("jueves")) {
				days[4] = true;

			} else if (s.toLowerCase().equals("viernes")) {
				days[5] = true;

			} else if (s.toLowerCase().equals("sábado")) {
				days[6] = true;
			}
		}
		return days;
	}

	public void asignarVacaciones(java.util.Date fechaInicio, java.util.Date fechafin, int idEmpleado) {

		int horaInicio = fechaInicio.getHours();
		int minutosInicio = fechaInicio.getMinutes();

		int horaFin = fechafin.getHours();
		int minutosFin = fechafin.getMinutes();

		java.util.Date actualInicio = fechaInicio;
		java.util.Date actualFin = new java.util.Date(actualInicio.getTime());
		actualFin.setHours(horaFin);
		actualFin.setMinutes(minutosFin);

		// boolean[] days = obtenerDiasSemana(dias);

		while (actualInicio.compareTo(fechafin) <= 0) {
			// if(days[actualInicio.getDay()])
			// {
			VacacionesDto vacaciones = new VacacionesDto();
			vacaciones.fechainicio = actualInicio;
			vacaciones.fechafin = actualFin;
			vacaciones.idempleado = idEmpleado;
			dv.add(vacaciones);
			// }
			int a = actualInicio.getDate() + 1;
			actualInicio.setDate(a);
			actualFin.setDate(a);
		}

	}

	public boolean empleadoTieneVacaciones(EmpleadoDto empleado) {
		DataVacaciones dv = new DataVacaciones();
		return dv.empleadoTieneVacaciones(empleado);

	}

	public List<VacacionesDto> getVacacionesPorEmpleado(EmpleadoDto empleado) {
		DataVacaciones dv = new DataVacaciones();
		return dv.listVacacionesByEmpleado(empleado.id);

	}
}
