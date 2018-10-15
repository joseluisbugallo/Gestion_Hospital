package business;

import java.util.ArrayList;
import java.util.List;

import business.dto.CitaDto;
import business.dto.EmpleadoDto;
import business.dto.HistorialDto;
import business.dto.JornadaLaboralDto;
import persistence.DataCita;
import persistence.DataHistorial;
import persistence.DataJornada;

public class CitasController {

	public boolean comprobarDisponibilidadEmpleado(EmpleadoDto empleado, CitaDto cita) {
		List<JornadaLaboralDto> jornadas = obtenerJornadasEmpleado(empleado);
		for (JornadaLaboralDto jornada : jornadas) {
			if (jornada.fechainicio.before(cita.fechainicio) && jornada.fechafin.after(cita.fechafin)) {
				if (comprobarRestoCitas(cita))
					return true; // La cita está disponible!!
			}
		}
		return false;
	}

	public List<CitaDto> getListadoCitas(EmpleadoDto empleado) {
		List<CitaDto> listadoCitas = obtenerCitasEmpleado(empleado.id);
		if (listadoCitas == null)
			return new ArrayList<>();
		else
			return listadoCitas;
	}
	
	public String cargarDatosHistorial(int idPaciente) {
		HistorialDto historial = new HistorialDto();
		DataHistorial dh = new DataHistorial();
		historial = dh.getHistorialByPaciente(idPaciente);
		if(historial.id==0) {
			return "ERROR-----> NO SE HA CARGADO EL HISTORIAL CORRECTAMENTE!";
		}
		
		return historial.datos;
	}

	private boolean comprobarRestoCitas(CitaDto cita) {
		List<CitaDto> citas = obtenerCitasEmpleado(cita.idEmpleado);
		for (CitaDto c : citas) {
			if ((cita.fechainicio.after(c.fechainicio) && cita.fechainicio.before(c.fechafin)) 
					|| (cita.fechafin.before(c.fechafin) && cita.fechafin.after(c.fechainicio)))
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
