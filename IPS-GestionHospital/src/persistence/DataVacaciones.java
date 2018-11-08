package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import business.dto.EmpleadoDto;
import business.dto.VacacionesDto;

public class DataVacaciones extends DataManager {

	private static final String SQL_SELECT_VACACIONES = "Select * from vacaciones";
	private static final String SQL_INSERT_VACACIONES = "Insert into vacaciones(idempleado, fechainicio, fechafin ) values (?, ?, ?)";
	private static final String SQL_DELETE_VACACIONES = "Delete from vacaciones where idvacaciones=?";
	private static final String SQL_UPDATE_VACACIONES = "Update vacaciones set idempleado=? fechainicio=?, fechafin=?,"
			+ "  where idvacaciones=?";
	
	private static final String SQL_SELECT_VACACIONES_BY_MEDICO = "Select * from vacaciones where idempleado=?";

	public List<VacacionesDto> list() {
		List<VacacionesDto> listVacaciones = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_VACACIONES);
			rs = st.executeQuery();
			listVacaciones = new ArrayList<>();
			while (rs.next()) {
				VacacionesDto vacaciones = new VacacionesDto();
				vacaciones.id = rs.getInt(1);
				vacaciones.idempleado = rs.getInt(2);
				vacaciones.fechainicio = rs.getTimestamp(4);
				vacaciones.fechafin = rs.getTimestamp(3);
				
				listVacaciones.add(vacaciones);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return listVacaciones;
	}

	public void add(VacacionesDto vacaciones) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_INSERT_VACACIONES);
			st.setInt(1, vacaciones.idempleado);
			st.setTimestamp(3, new Timestamp(vacaciones.fechainicio.getTime()));
			st.setTimestamp(2, new Timestamp(vacaciones.fechafin.getTime()));
			

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	public void delete(VacacionesDto vacaciones) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_DELETE_VACACIONES);
			st.setInt(1,  vacaciones.id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	public void update(VacacionesDto vacaciones) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_UPDATE_VACACIONES);
			st.setInt(1, vacaciones.idempleado);
			st.setTimestamp(3, new Timestamp(vacaciones.fechainicio.getTime()));
			st.setTimestamp(2, new Timestamp(vacaciones.fechafin.getTime()));
			
			st.setInt(4, vacaciones.id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}
	
	public List<VacacionesDto> listVacacionesByEmpleado(int id){
		PreparedStatement st= null;
		ResultSet rs = null;
		List<VacacionesDto> listVacaciones = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_VACACIONES_BY_MEDICO);
			st.setLong(1, id);
			
			rs = st.executeQuery();
			listVacaciones = new ArrayList<>();
			while(rs.next()) {
				VacacionesDto vacaciones = new VacacionesDto();
				vacaciones.id = rs.getInt(1);
				
				vacaciones.idempleado = rs.getInt(2);
				
				vacaciones.fechainicio = rs.getTimestamp(4);
				vacaciones.fechafin = rs.getTimestamp(3);
				
				listVacaciones.add(vacaciones);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs,st);
		}
		return listVacaciones;
	}

	public boolean empleadoTieneVacaciones(EmpleadoDto empleado) {
		List<VacacionesDto> vacaciones = listVacacionesByEmpleado(empleado.id);
		return vacaciones.size()>0;
	}

}