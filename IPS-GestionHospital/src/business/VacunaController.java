package business;

import java.util.ArrayList;
import java.util.List;
import business.dto.EmpleadoDto;
import business.dto.JornadaLaboralDto;
import business.dto.VacunaDto;
import persistence.DataEmpleado;
import persistence.DataJornada;
import persistence.DataVacuna;

public class VacunaController {

	private DataVacuna dj = new DataVacuna();




	public void addVacuna(VacunaDto vacuna) {
		dj.add(vacuna);
	}
	


	public ArrayList<VacunaDto> getVacunasPaciente(int pacienteId) {		
		return dj.list(pacienteId);
	}



}
