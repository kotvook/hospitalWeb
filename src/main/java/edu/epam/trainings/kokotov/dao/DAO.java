package edu.epam.trainings.kokotov.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<Entity, ID> {
    void create(Entity model) throws SQLException;
    Entity read(ID key);
    void update(Entity model) throws SQLException;
    void delete(Entity model);
    List<Entity> getAll();
}