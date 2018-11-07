package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import business.dto.JornadaLaboralDto;
import business.dto.VacunaDto;

public class DataVacuna extends DataManager {

	private static final String SQL_SELECT_VACUNA_PACIENTE = "Select * from vacunas where idpaciente = ?";
	private static final String SQL_INSERT_VACUNA = "Insert into vacunas(idpaciente, fechainicio, fechafin,vacuna ) values (?, ?, ?, ?)";




	public ArrayList<VacunaDto> list(int pacienteId) {
		ArrayList<VacunaDto> vacunas = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_VACUNA_PACIENTE);
			st.setInt(1, pacienteId);
			rs = st.executeQuery();
			vacunas = new ArrayList<VacunaDto>();
			while (rs.next()) {
				VacunaDto vacuna = new VacunaDto();
				vacuna.id= rs.getInt(1);
				vacuna.idPaciente = rs.getInt(2);
				vacuna.fechainicio = rs.getTimestamp(3);
				vacuna.fechafin = rs.getTimestamp(4);
				vacuna.vacuna = rs.getString(5);
				
				vacunas.add(vacuna);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return vacunas;
	}

	public void add(VacunaDto vacuna) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_INSERT_VACUNA);
			st.setInt(1, vacuna.idPaciente);
			st.setTimestamp(2, new Timestamp(vacuna.fechainicio.getTime()));
			st.setTimestamp(3, new Timestamp(vacuna.fechafin.getTime()));
			st.setString(4, vacuna.vacuna);
			

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}





}
