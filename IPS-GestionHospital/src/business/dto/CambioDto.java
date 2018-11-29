package business.dto;

public class CambioDto {	
	
	@Override
	public String toString() {
		return "LOG [id=" + id + ", cambio=" + cambio + ", fecha=" + fecha.toString() + "]";
	}
	public int id;
	public String cambio;
	public java.util.Date fecha;
	//Comentario

}
