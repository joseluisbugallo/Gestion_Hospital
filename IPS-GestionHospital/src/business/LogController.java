package business;

import java.util.ArrayList;

import business.dto.CambioDto;
import persistence.DataLog;

public class LogController {
	
	public void a�adirCambio(CambioDto cambio) {
		DataLog dc = new DataLog();
		dc.add(cambio);		
	}
	
	
	public ArrayList<CambioDto> listarLog() {
		DataLog dc = new DataLog();
		return dc.list();		
	}

}
