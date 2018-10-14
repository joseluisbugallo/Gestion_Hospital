package business;

import java.util.List;

import business.dto.CitaDto;
import business.dto.EmpleadoDto;
import business.dto.JornadaLaboralDto;
import persistence.DataCita;
import persistence.DataJornada;

public class CitasController {	
	
	public boolean comprobarDisponibilidadEmpleado(EmpleadoDto empleado, CitaDto cita) {
		List<JornadaLaboralDto> jornadas = obtenerJornadasEmpleado(empleado);
		for(JornadaLaboralDto jornada: jornadas) {
			if(jornada.fechainicio.before(cita.fechainicio) && jornada.fechafin.after(cita.fechafin)) {
				if(comprobarRestoCitas(cita))
					return true; //La cita está disponible!!
			}
		}
		return false;
	}

	private boolean comprobarRestoCitas(CitaDto cita) {
		List<CitaDto> citas = obtenerCitasEmpleado(cita.idEmpleado);
		for(CitaDto c:citas) {
			if(cita.fechainicio.after(c.fechainicio) || cita.fechafin.before(c.fechafin))
				return false;
		}
		return true;
	}

	private List<CitaDto> obtenerCitasEmpleado(int idEmpleado) {
		DataCita dC = new DataCita();
		List<CitaDto> cs = dC.listCitasByidEmpleado(idEmpleado);
		return cs;
	}

	private List<JornadaLaboralDto> obtenerJornadasEmpleado(EmpleadoDto medico) {
		DataJornada dJ = new DataJornada();
		List<JornadaLaboralDto> js = dJ.listJornadasByEmpleado(medico.id);
		return js;
	}

}
