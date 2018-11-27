package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import alb.util.jdbc.Jdbc;
import business.dto.CambioDto;

public class DataLog extends DataManager{
	
	private static final String SQL_INSERT_LOG = "Insert into log (cambio , fecha)" + "values (?, ?)";
	
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

}
