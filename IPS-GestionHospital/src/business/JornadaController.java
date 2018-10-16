package business;

import java.util.ArrayList;
import java.util.List;
import business.dto.EmpleadoDto;
import business.dto.JornadaLaboralDto;
import persistence.DataEmpleado;
import persistence.DataJornada;

public class JornadaController {
	
	private DataJornada dj;
	
	public List<EmpleadoDto> getMedicos(){
		DataEmpleado dE = new DataEmpleado();
		List<EmpleadoDto> em = dE.list();
		List<EmpleadoDto> medicos = new ArrayList<EmpleadoDto>();
		for (EmpleadoDto e : em) {
			if(e.cargo.equals("Medico")) {
				medicos.add(e);
			}
		}
		return medicos;
	}
	
	public List<EmpleadoDto> getEnfermeros(){
		DataEmpleado dE = new DataEmpleado();
		List<EmpleadoDto> em = dE.list();
		List<EmpleadoDto> enfermeros = new ArrayList<EmpleadoDto>();
		for (EmpleadoDto e : em) {
			if(e.cargo.equals("Enfermero")) {
				enfermeros.add(e);
			}
		}
	
		return enfermeros;
	}
	
	public void addJornada(JornadaLaboralDto jornada) {
		dj.add(jornada);
	}
	
}
