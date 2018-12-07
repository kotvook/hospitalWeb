package edu.epam.trainings.kokotov.dao;

import edu.epam.trainings.kokotov.model.Operation;
import edu.epam.trainings.kokotov.model.Patient;
import edu.epam.trainings.kokotov.model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * the class implements the DAO interface
 * and provides CRUD operations for the entity Operatio
 *
 * @author    Artem Kokotov
 */

public class OperationDAO implements DAO <Operation, Integer>{

    /**
     * Connection of database.
     */
    private final Connection connection;

    /**
     * Init database connection.
     *
     * @param connection of database.
     */
    public OperationDAO(final Connection connection) {
        this.connection = connection;
    }


    /**
     * Create Operation in database.
     *
     * @param operation for create.
     */
    @Override
    public void create(Operation operation) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLOperation.INSERT.QUERY)) {
            if (operation.getDoctor().getId() == 1){
                statement.setString(1, operation.getOperation());
                statement.setInt(2, operation.getDoctor().getId());
                statement.setInt(3, operation.getPatient().getId());
                statement.executeUpdate();
            } else {
                throw new Exception("Unauthorized attempt to schedule operation !!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Select Operation by id.
     *
     * @param id for select.
     * @return return valid entity if it exist.
     * If entity does not exist return empty Operation with id = -1.
     */
    @Override
    public Operation read(Integer id) {
        final Operation result = new Operation();

        try (PreparedStatement statement = connection.prepareStatement(SQLOperation.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {

                result.setId(rs.getInt("id"));

                result.setOperation(rs.getString("operation"));

                PatientDAO patientDAO = new PatientDAO(connection);
                Patient patient = patientDAO.read(rs.getInt("patient_id"));
                result.setPatient(patient);

                StaffDAO staffDAO = new StaffDAO(connection);
                Staff doctor = staffDAO.read(rs.getInt("doctor_id"));
                result.setDoctor(doctor);

            } else {
                result.setOperation("entity not exist in operations");
                result.setId(-1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Update Operation' data by id.
     *
     * @param operation with selected id.
     * @return updated entity.
     */
    @Override
    public void update(Operation operation) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLOperation.UPDATE.QUERY)) {
            statement.setString(1, operation.getOperation());
            statement.setInt(2, operation.getPatient().getId());
            statement.setInt(3, operation.getDoctor().getId());
            statement.setInt(4, operation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete Operation.
     *
     * @param operation for delete.
     */
    @Override
    public void delete(Operation operation) {
        try (PreparedStatement statement = connection.prepareStatement(SQLOperation.DELETE.QUERY)) {
            statement.setInt(1, operation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select all operations.
     *
     * @return return all operations.
     */
    @Override
    public List<Operation> getAll() {
        List<Operation> operations = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLOperation.GETALL.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Operation operation = new Operation();
                operation.setId(rs.getInt("id"));
                operation.setOperation(rs.getString("operation"));

                PatientDAO patientDAO = new PatientDAO(connection);
                Patient patient = patientDAO.read(rs.getInt("id"));
                operation.setPatient(patient);

                StaffDAO staffDAO = new StaffDAO(connection);
                Staff doctor = staffDAO.read(rs.getInt("id"));
                operation.setDoctor(doctor);

                operations.add(operation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operations;
    }

    /**
     * SQL queries for operations.
     */
    enum SQLOperation {
        GET("SELECT * FROM operations where id = (?)"),
        GETALL("SELECT * FROM operations"),
        INSERT("INSERT INTO operations (operation, patient_id, doctor_id)" +
                " VALUES ((?), (?), (?))"),
        DELETE("DELETE FROM operations WHERE id = (?)"),
        UPDATE("UPDATE operations SET operation = (?), " +
                "patient_id = (?)," +
                " doctor_id = (?), " +
                " WHERE id = (?)");
        String QUERY;

        SQLOperation(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
