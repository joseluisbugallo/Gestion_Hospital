package business.dto;

public class DiagnosticoDto {
	
	public String id;
	public String diagnostico;
	public String grupo;
	
	
	public String mostrarDiagnostico()
	{
		String a= "El diagnostico "+ id + " del grupo "+grupo+" es: \n"+ diagnostico;
		return a;
	}
	
	@Override
	public String toString() {
		return  id + ": "+ diagnostico;
	}	

}
