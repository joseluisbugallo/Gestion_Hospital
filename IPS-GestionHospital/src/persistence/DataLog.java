package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import alb.util.jdbc.Jdbc;
import business.dto.CambioDto;
import business.dto.CitaDto;

public class DataLog extends DataManager{
	
	private static final String SQL_INSERT_LOG = "Insert into log (cambio , fecha)" + "values (?, ?)";
	private static final String SQL_SELECT_LOG = "Select * from log";
	
	public void add(CambioDto cambio) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_INSERT_LOG);
			st.setString(1, cambio.cambio);
			st.setTimestamp(2, new Timestamp(cambio.fecha.getTime()));
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}
	
	
	public ArrayList<CambioDto> list()
	{
		ArrayList<CambioDto> listado = new ArrayList<CambioDto>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_LOG);
			rs = st.executeQuery();
			while (rs.next()) {
				CambioDto cambio = new CambioDto();
				cambio.id = rs.getInt(1);
				cambio.cambio = rs.getString(2);
				cambio.fecha = rs.getTimestamp(3);
				listado.add(cambio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}	
		
		
		return listado;
	}
	

}
