package edu.epam.trainings.kokotov.dao;

import edu.epam.trainings.kokotov.model.Patient;
import edu.epam.trainings.kokotov.model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO implements DAO <Staff, Integer> {

    /**
     * Connection of database.
     */
    private final Connection connection;

    /**
     * Init database connection.
     *
     * @param connection of database.
     */
    public StaffDAO(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Create Patient in database.
     *
     * @param staff for create.
     */
    @Override
    public void create(Staff staff) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLStaff.INSERT.QUERY)) {
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getSurname());
            statement.setString(4, staff.getPhone());
            statement.setString(5, staff.getEmail());
            statement.setInt(6, staff.getQual_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select Staff by id.
     *
     * @param id for select.
     * @return return valid entity if it exist.
     * If entity does not exist return empty Staff with id = -1.
     */
    @Override
    public Staff read(Integer id) {
        final Staff result = new Staff();

        try (PreparedStatement statement = connection.prepareStatement(SQLStaff.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));
                result.setSurname(rs.getString("surname"));
                result.setPhone(rs.getString("phone"));
                result.setEmail(rs.getString("email"));
                result.setQual_id(rs.getInt("qual_id"));
            } else {
                result.setName("entity not exist in staff");
                result.setId(-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Select all staff.
     *
     * @return return everybody staff.
     */
    @Override
    public List<Staff> getAll() {
        List<Staff> staffList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLStaff.GETALL.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setName(rs.getString("name"));
                staff.setSurname(rs.getString("surname"));
                staff.setPhone(rs.getString("phone"));
                staff.setEmail(rs.getString("email"));
                staff.setQual_id(rs.getInt("qual_id"));
                staffList.add(staff);
            }
            return staffList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Update Staff' data by id.
     *
     * @param staff with selected id.
     * @return updated entity.
     */
    @Override
    public void update(Staff staff) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLStaff.UPDATE.QUERY)) {
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getSurname());
            statement.setString(4,staff.getPhone());
            statement.setString(5,staff.getEmail());
            statement.setInt(9,staff.getQual_id());
            statement.setInt(10, staff.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete Staff.
     *
     * @param staff for delete.
     */
    @Override
    public void delete(Staff staff) {
        try (PreparedStatement statement = connection.prepareStatement(SQLStaff.DELETE.QUERY)) {
            statement.setInt(1, staff.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * SQL queries for staff.
     */
    enum SQLStaff {
        GET("SELECT * FROM staff where id = (?)"),
        GETALL("SELECT * FROM staff"),
        INSERT("INSERT INTO staff (name, surname, phone, email, qual_id)" +
                " VALUES ((?), (?), (?), (?), (?))"),
        DELETE("DELETE FROM staff WHERE id = (?)"),
        UPDATE("UPDATE patients SET name = (?), " +
                "surname = (?)," +
                "phone = (?)," +
                " email = (?), " +
                "qual_id = (?), " +
                " WHERE id = (?)");
        String QUERY;

        SQLStaff(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
