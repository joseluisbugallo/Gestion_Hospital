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
		List<JornadaLaboralDto> jornadas = new ArrayList<JornadaLaboralDto>();
		jornadas = obtenerJornadasEmpleado(empleado);
		if(jornadas.size()==0)
		{
			return false;
		}
		for (JornadaLaboralDto jornada : jornadas) {
			if (jornada.fechainicio.before(cita.fechainicio) && jornada.fechafin.after(cita.fechafin)) {
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
			return "No existe el historial de ese paciente";
		}
		
		return historial.datos;
	}
	
	public List<CitaDto> getListadoCompletoDecitas() {
		List<CitaDto> citas =null;
		DataCita dC = new DataCita();
		citas =dC.list();
		return citas;
	}
	

	public boolean comprobarRestoCitas(CitaDto cita, int idEmpl) {
		List<CitaDto> citas = obtenerCitasEmpleado(idEmpl);
		for (CitaDto c : citas) {
			if (cita.fechainicio.after(c.fechainicio) && cita.fechainicio.before(cita.fechafin)
					|| cita.fechafin.after(c.fechainicio) && cita.fechafin.before(c.fechafin))
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
	
	public void crearCita(CitaDto cita, List<EmpleadoDto> empleados) {
		DataCita dc = new DataCita();
		for (EmpleadoDto em: empleados) {
			cita.idEmpleado=em.id;
			dc.add(cita);
		}
		
	}
}
