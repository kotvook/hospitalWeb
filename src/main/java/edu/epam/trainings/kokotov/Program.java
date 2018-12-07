package edu.epam.trainings.kokotov;

import edu.epam.trainings.kokotov.dao.DatabaseConnection;
import edu.epam.trainings.kokotov.dao.DrugDAO;
import edu.epam.trainings.kokotov.dao.PatientDAO;
import edu.epam.trainings.kokotov.model.Drug;
import edu.epam.trainings.kokotov.model.Patient;
import edu.epam.trainings.kokotov.resource.ResourceManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/***
 *  Just to see how work database connection and crud operations
 */
public class Program {

    public static void main(String[] args) throws SQLException {

        // establish сonnection
        DatabaseConnection.establishСonnection();

        // localization
        ResourceManager manager = ResourceManager.INSTANCE;
        manager.changeResource(new Locale("ru"));

        // to check the connection to the database and the correctness of the data
        DrugDAO drugDAO = new DrugDAO(DatabaseConnection.getConnection());
        List<Drug> drugs;
        drugs = drugDAO.getAll();

        // output to console
        System.out.println(drugs.toString());

        //close сonnection
        DatabaseConnection.closeConnection ();

    }
}
