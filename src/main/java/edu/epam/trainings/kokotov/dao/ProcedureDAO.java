package edu.epam.trainings.kokotov.dao;

import edu.epam.trainings.kokotov.model.Drug;
import edu.epam.trainings.kokotov.model.Patient;
import edu.epam.trainings.kokotov.model.Procedure;
import edu.epam.trainings.kokotov.model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedureDAO implements DAO <Procedure, Integer> {
    /**
     * Connection of database.
     */
    private final Connection connection;

    /**
     * Init database connection.
     *
     * @param connection of database.
     */
    public ProcedureDAO(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Create Procedure in database.
     *
     * @param procedure for create.
     */
    @Override
    public void create(Procedure procedure) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLProcedure.INSERT.QUERY)) {
            if (procedure.getDoctor().getId() == 1){
                statement.setString(1, procedure.getProcedure());
                statement.setInt(2, procedure.getDoctor().getId());
                statement.setInt(3, procedure.getPatient().getId());
                statement.executeUpdate();
            } else {
                throw new Exception("Unauthorized attempt to schedule a procedure !!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Select Procedure by id.
     *
     * @param id for select.
     * @return return valid entity if it exist.
     * If entity does not exist return empty Procedure with id = -1.
     */
    @Override
    public Procedure read(Integer id) {
        final Procedure result = new Procedure();

        try (PreparedStatement statement = connection.prepareStatement(SQLProcedure.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {

                result.setId(rs.getInt("id"));

                result.setProcedure(rs.getString("drug"));

                PatientDAO patientDAO = new PatientDAO(connection);
                Patient patient = patientDAO.read(rs.getInt("patient_id"));
                result.setPatient(patient);

                StaffDAO staffDAO = new StaffDAO(connection);
                Staff doctor = staffDAO.read(rs.getInt("doctor_id"));
                result.setDoctor(doctor);

            } else {
                result.setProcedure("entity not exist in procedures");
                result.setId(-1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Update Operation' data by id.
     *
     * @param procedure with selected id.
     * @return updated entity.
     */
    @Override
    public void update(Procedure procedure) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLProcedure.UPDATE.QUERY)) {
            statement.setString(1, procedure.getProcedure());
            statement.setInt(2, procedure.getPatient().getId());
            statement.setInt(3, procedure.getDoctor().getId());
            statement.setInt(4, procedure.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete Procedure.
     *
     * @param procedure for delete.
     */
    @Override
    public void delete(Procedure procedure) {
        try (PreparedStatement statement = connection.prepareStatement(SQLProcedure.DELETE.QUERY)) {
            statement.setInt(1, procedure.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select all procedures.
     *
     * @return return all procedures.
     */
    @Override
    public List<Procedure> getAll() {
        List<Procedure> procedures = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLProcedure.GETALL.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Procedure procedure = new Procedure();
                procedure.setId(rs.getInt("id"));
                procedure.setProcedure(rs.getString("procedure"));

                PatientDAO patientDAO = new PatientDAO(connection);
                Patient patient = patientDAO.read(rs.getInt("patient_id"));
                procedure.setPatient(patient);

                StaffDAO staffDAO = new StaffDAO(connection);
                Staff doctor = staffDAO.read(rs.getInt("doctor_id"));
                procedure.setDoctor(doctor);

                procedures.add(procedure);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return procedures;
    }

    /**
     * SQL queries for procedures.
     */
    enum SQLProcedure {
        GET("SELECT * FROM procedures where id = (?)"),
        GETALL("SELECT * FROM procedures"),
        INSERT("INSERT INTO procedures (procedure, patient_id, doctor_id)" +
                " VALUES ((?), (?), (?))"),
        DELETE("DELETE FROM procedures WHERE id = (?)"),
        UPDATE("UPDATE procedures SET procedure = (?), " +
                "patient_id = (?)," +
                " doctor_id = (?), " +
                " WHERE id = (?)");
        String QUERY;

        SQLProcedure(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
