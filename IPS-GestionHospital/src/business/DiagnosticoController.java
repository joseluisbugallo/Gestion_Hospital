package business;

import java.util.ArrayList;

import business.dto.CitaDto;
import business.dto.DiagnosticoDto;
import persistence.DataDiagnostico;

public class DiagnosticoController {

	public ArrayList<DiagnosticoDto> obtenerDiagnosticos() {
		DataDiagnostico dd = new DataDiagnostico();
		return dd.list();
	}
	
	public ArrayList<DiagnosticoDto> obtenerDiagnosticosDeCita(int idCita) {
		DataDiagnostico dd = new DataDiagnostico();
		return dd.listDiagnosticosCita(idCita);
	}
	
	public void addDiagnosticoACita(CitaDto cita, DiagnosticoDto diagnostico)
	{
		cita.diagnostico.add(diagnostico);
		DataDiagnostico dd = new DataDiagnostico();
		dd.addDiagnosticoCita(cita.id, diagnostico.id);
	}

}
