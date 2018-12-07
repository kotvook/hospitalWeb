package edu.epam.trainings.kokotov.dao;

import edu.epam.trainings.kokotov.model.Drug;
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
 * and provides CRUD operations for the entity Drug
 *
 * @author    Artem Kokotov
 */

public class DrugDAO implements DAO<Drug, Integer>{
    /**
     * Connection of database.
     */
    private final Connection connection;

    /**
     * Init database connection.
     *
     * @param connection of database.
     */
    public DrugDAO(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Create Patient in database.
     *
     * @param drug for create.
     */
    @Override
    public void create(Drug drug) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLDrug.INSERT.QUERY)) {
            if (drug.getDoctor().getId() == 1){
                statement.setString(1, drug.getDrug());
                statement.setInt(2, drug.getDoctor().getId());
                statement.setInt(3, drug.getPatient().getId());
                statement.executeUpdate();
            } else {
                throw new Exception("Unauthorized attempt to prescribe medication !!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Select Drug by id.
     *
     * @param id for select.
     * @return return valid entity if it exist.
     * If entity does not exist return empty Drug with id = -1.
     */
    @Override
    public Drug read(Integer id) {
        final Drug result = new Drug();

        try (PreparedStatement statement = connection.prepareStatement(SQLDrug.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {

                result.setId(rs.getInt("id"));

                result.setDrug(rs.getString("drug"));

                PatientDAO patientDAO = new PatientDAO(connection);
                Patient patient = patientDAO.read(rs.getInt("patient_id"));
                result.setPatient(patient);

                StaffDAO staffDAO = new StaffDAO(connection);
                Staff doctor = staffDAO.read(rs.getInt("doctor_id"));
                result.setDoctor(doctor);
/*                if (doctor.getQual_id()==1){
                    result.setDoctor(doctor);
                } else {
                    throw new Exception("Unauthorized attempt to prescribe medication !!!");
                }*/
            } else {
                result.setDrug("entity not exist in drugs");
                result.setId(-1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Select all drugs.
     *
     * @return return all drugs.
     */
    @Override
    public List<Drug> getAll() {
        List<Drug> drugs = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLDrug.GETALL.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Drug drug = new Drug();
                drug.setId(rs.getInt("id"));
                drug.setDrug(rs.getString("drug"));

                PatientDAO patientDAO = new PatientDAO(connection);
                Patient patient = patientDAO.read(rs.getInt("patient_id"));
                drug.setPatient(patient);

                StaffDAO staffDAO = new StaffDAO(connection);
                Staff doctor = staffDAO.read(rs.getInt("doctor_id"));
                drug.setDoctor(doctor);

                drugs.add(drug);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugs;
    }

    /**
     * Update Drug' data by id.
     *
     * @param drug with selected id.
     * @return updated entity.
     */
    @Override
    public void update(Drug drug) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLDrug.UPDATE.QUERY)) {
            statement.setString(1, drug.getDrug());
            statement.setInt(2, drug.getPatient().getId());
            statement.setInt(3, drug.getDoctor().getId());
            statement.setInt(4, drug.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete Patient.
     *
     * @param drug for delete.
     */
    @Override
    public void delete(Drug drug) {
        try (PreparedStatement statement = connection.prepareStatement(SQLDrug.DELETE.QUERY)) {
            statement.setInt(1, drug.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * SQL queries for drugs.
     */
    enum SQLDrug {
        GET("SELECT * FROM drugs where id = (?)"),
        GETALL("SELECT * FROM drugs"),
        INSERT("INSERT INTO drugs (drug, patient_id, doctor_id)" +
                " VALUES ((?), (?), (?))"),
        DELETE("DELETE FROM drugs WHERE id = (?)"),
        UPDATE("UPDATE drugs SET drug = (?), " +
                "patient_id = (?)," +
                " doctor_id = (?), " +
                " WHERE id = (?)");
        String QUERY;

        SQLDrug(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
