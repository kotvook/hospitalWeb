package edu.epam.trainings.kokotov.servlet.grug;


import edu.epam.trainings.kokotov.dao.DatabaseConnection;
import edu.epam.trainings.kokotov.dao.DrugDAO;
import edu.epam.trainings.kokotov.model.Drug;
import edu.epam.trainings.kokotov.resource.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/ViewServlet")
public class DrugViewServlet extends HttpServlet {

    private List<Drug> drugs;

    @Override
    public void init() throws ServletException {

        // establish сonnection
        try {
            DatabaseConnection.establishСonnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DrugDAO drugDAO = new DrugDAO(DatabaseConnection.getConnection());
        drugs = drugDAO.getAll();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<a href='view/index.html'>Add New Drugs</a>");
        out.println("<h1>Drugs List</h1>");



        if (drugs.size()<1){
            out.println("<h1>Have not drugs</h1>");
        }
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Drug</th><th>Doctor</th><th>Patient</th><th>Edit</th><th>Delete</th></tr>");
        for(Drug drug:drugs){
            out.print("<tr>"
                    + "<td>"+drug.getId()+"</td>"
                    + "<td>" + drug.getDrug() + "</td>"
                    + "<td>" + drug.getDoctor()+ "</td>"
                    + "<td>" + drug.getPatient() + "</td>"
                    + "<td><a href='EditServlet?id="+drug.getId()+"'>edit</a></td>"
                    + "<td><a href='DeleteServlet?id="+drug.getId()+"'>delete</a></td>"
                    + "</tr>");
        }
        out.print("</table>");

        out.close();
    }
}

