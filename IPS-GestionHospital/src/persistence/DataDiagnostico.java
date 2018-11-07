package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import business.dto.CitaDto;
import business.dto.DiagnosticoDto;

public class DataDiagnostico extends DataManager {
	
	private static final String SQL_SELECT_DIAGNOSTICOS = "Select * from cie10";
	private static final String SQL_SELECT_DIAGNOSTICOS_DE_CITA = "Select * from cie10 where id10 in( select idcie10 from diagnosticosdecitas where idcita = ?)";
	private static final String SQL_INSERT_DIAGNOSTICO_DE_CITA = "Insert into diagnosticosdecita (idCita, idcie)" + "values (?, ?)";

	public ArrayList<DiagnosticoDto> list() {
		ArrayList<DiagnosticoDto> diagnosticos = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_DIAGNOSTICOS);
			rs = st.executeQuery();
			diagnosticos = new ArrayList<>();
			while (rs.next()) {
				DiagnosticoDto diagnostico = new DiagnosticoDto();
				diagnostico.id = rs.getString(1);
				diagnostico.diagnostico = rs.getString(2);				
				diagnostico.grupo = rs.getString(3);

				diagnosticos.add(diagnostico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return diagnosticos;
	}


	public ArrayList<DiagnosticoDto> listDiagnosticosCita(int idCita) {
		ArrayList<DiagnosticoDto> diagnosticos = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_DIAGNOSTICOS_DE_CITA);
			st.setInt(1, idCita);
			rs = st.executeQuery();
			diagnosticos = new ArrayList<>();
			while (rs.next()) {
				DiagnosticoDto diagnostico = new DiagnosticoDto();
				diagnostico.id = rs.getString(1);
				diagnostico.diagnostico = rs.getString(2);
				diagnostico.grupo = rs.getString(3);				
				diagnosticos.add(diagnostico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return diagnosticos;
	}


	public void addDiagnosticoCita(int idCita, String iddiagnostico) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_INSERT_DIAGNOSTICO_DE_CITA);
			st.setInt(1, idCita);
			st.setString(2, iddiagnostico);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		
	}

}
