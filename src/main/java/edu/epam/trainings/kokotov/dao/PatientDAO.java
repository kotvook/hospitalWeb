package edu.epam.trainings.kokotov.dao;

import edu.epam.trainings.kokotov.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * the class implements the DAO interface
 * and provides CRUD operations for the entity Patient
 *
 * @author    Artem Kokotov
 */

public class PatientDAO implements DAO <Patient, Integer>{

    /**
     * Connection of database.
     */
    private final Connection connection;

    /**
     * Init database connection.
     *
     * @param connection of database.
     */
    public PatientDAO(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Create Patient in database.
     *
     * @param patient for create.
     */
    @Override
    public void create(Patient patient) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.INSERT.QUERY)) {
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getSurname());
            statement.setString(3,patient.getBirthday());
            statement.setString(4,patient.getPhone());
            statement.setString(5,patient.getEmail());
            statement.setString(6,patient.getStartDiagnosis());
            statement.setString(7,patient.getEndDiagnosis());
            statement.setString(8,patient.getCome());
            statement.setString(9,patient.getOut());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select Patient by id.
     *
     * @param id for select.
     * @return return valid entity if it exist.
     * If entity does not exist return empty Patient with id = -1.
     */
    @Override
    public Patient read(Integer id) {
        final Patient result = new Patient();

        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));
                result.setSurname(rs.getString("surname"));
                result.setBirthday(rs.getString("birthday"));
                result.setPhone(rs.getString("phone"));
                result.setEmail(rs.getString("email"));
                result.setStartDiagnosis(rs.getString("startdiagnosis"));
                result.setEndDiagnosis(rs.getString("enddiagnosis"));
                result.setCome(rs.getString("come"));
                result.setOut(rs.getString("out"));
            } else {
                result.setName("entity not exist in patients");
                result.setId(-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Select all patients.
     *
     * @return return all patients.
     */
    @Override
    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.GETALL.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));
                patient.setBirthday(rs.getString("birthday"));
                patient.setPhone(rs.getString("phone"));
                patient.setEmail(rs.getString("email"));
                patient.setStartDiagnosis(rs.getString("startdiagnosis"));
                patient.setEndDiagnosis(rs.getString("enddiagnosis"));
                patient.setCome(rs.getString("come"));
                patient.setOut(rs.getString("out"));
                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    /**
     * Update Patient' data by id.
     *
     * @param patient with selected id.
     * @return updated entity.
     */
    @Override
    public void update(Patient patient) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.UPDATE.QUERY)) {
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getSurname());
            statement.setString(3,patient.getBirthday());
            statement.setString(4,patient.getPhone());
            statement.setString(5,patient.getEmail());
            statement.setString(6,patient.getStartDiagnosis());
            statement.setString(7,patient.getEndDiagnosis());
            statement.setString(8,patient.getCome());
            statement.setString(9,patient.getOut());
            statement.setInt(10, patient.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Delete Patient.
     *
     * @param patient for delete.
     */
    @Override
    public void delete(Patient patient) {
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.DELETE.QUERY)) {
            statement.setInt(1, patient.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


/*    *//**
     * Delete Patient by id.
     *
     * @param id for delete.
     *//*
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.DELETE.QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * SQL queries for patients.
     */
    enum SQLPatient {
        GET("SELECT * FROM patients where id = (?)"),
        GETALL("SELECT * FROM patients"),
        INSERT("INSERT INTO patients (name, surname, birthday, phone, email, startdiagnosis, enddiagnosis, come, `out`)" +
                " VALUES ((?), (?), (?), (?), (?), (?), (?), (?), (?))"),
        DELETE("DELETE FROM patients WHERE id = (?)"),
        UPDATE("UPDATE patients SET name = (?), " +
                "surname = (?)," +
                " birthday = (?), " +
                "phone = (?)," +
                " email = (?), " +
                "startdiagnosis = (?), " +
                "enddiagnosis = (?), " +
                "come = (?), " +
                "`out` = (?) " +
                " WHERE id = (?)");
        String QUERY;

        SQLPatient(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
