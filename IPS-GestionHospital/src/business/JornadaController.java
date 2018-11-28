package business;

import java.util.ArrayList;
import java.util.List;
import business.dto.EmpleadoDto;
import business.dto.JornadaLaboralDto;
import persistence.DataEmpleado;
import persistence.DataJornada;

public class JornadaController {

	private DataJornada dj = new DataJornada();

	public List<EmpleadoDto> getMedicos() {
		DataEmpleado dE = new DataEmpleado();
		List<EmpleadoDto> em = dE.list();
		List<EmpleadoDto> medicos = new ArrayList<EmpleadoDto>();
		for (EmpleadoDto e : em) {
			if (e.cargo.equals("Medico")) {
				medicos.add(e);
			}
		}
		return medicos;
	}
	
	public void desactivarEmpleado(EmpleadoDto empleado) {
	
		DataEmpleado de = new DataEmpleado();
		de.update(empleado);
		
	}
	
	public List<EmpleadoDto> getEnfermeros() {
		DataEmpleado dE = new DataEmpleado();
		List<EmpleadoDto> em = dE.list();
		List<EmpleadoDto> enfermeros = new ArrayList<EmpleadoDto>();
		for (EmpleadoDto e : em) {
			if (e.cargo.equals("Enfermero")) {
				enfermeros.add(e);
			}
		}

		return enfermeros;
	}

	public void addJornada(JornadaLaboralDto jornada) {
		dj.add(jornada);
	}
	
	private boolean[] obtenerDiasSemana(String dias)
	{
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

	public void asignarJornada(java.util.Date fechaInicio, java.util.Date fechafin, String dias, int idEmpleado) {
		
			
		int horaInicio = fechaInicio.getHours();
		int minutosInicio = fechaInicio.getMinutes();

		int horaFin = fechafin.getHours();
		int minutosFin = fechafin.getMinutes();

		java.util.Date actualInicio = fechaInicio;
		java.util.Date actualFin = new java.util.Date(actualInicio.getTime());
		actualFin.setHours(horaFin);
		actualFin.setMinutes(minutosFin);
		
		
		
		boolean[] days = obtenerDiasSemana(dias);

		while (actualInicio.compareTo(fechafin)<=0) {
			if(days[actualInicio.getDay()])
			{
				JornadaLaboralDto jornada = new JornadaLaboralDto();
				jornada.fechainicio = actualInicio;
				jornada.fechafin = actualFin;
				jornada.idempleado= idEmpleado;
				dj.add(jornada);
			}	
			int a= actualInicio.getDate()+1;
			actualInicio.setDate(a);			
			actualFin.setDate(a);
		}

	}

	public List<JornadaLaboralDto> getJornadasByEmpleado(EmpleadoDto empleado) {
		DataJornada dj = new DataJornada();
		return dj.listJornadasByEmpleado(empleado.id);
	}

	public void eliminarJornadas(List<JornadaLaboralDto> jornadas) {
		DataJornada dj = new DataJornada();
		for (JornadaLaboralDto jornada: jornadas) {
			dj.delete(jornada);
		}
		
	}
	
	public List<EmpleadoDto> getEmpleados(){
		List<EmpleadoDto> empleados = new ArrayList<>();
		empleados.addAll(getMedicos());
		empleados.addAll(getEnfermeros());
		return empleados;
	}

	public void actualizarEmpleado(EmpleadoDto actual) {
		DataEmpleado de= new DataEmpleado();
		de.update(actual);
	}

}
