package business;

import business.dto.CambioDto;
import persistence.DataLog;

public class LogController {
	
	public void a�adirCambio(CambioDto cambio) {
		DataLog dc = new DataLog();
		dc.add(cambio);		
	}

}
