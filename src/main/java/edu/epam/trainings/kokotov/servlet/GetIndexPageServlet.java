package edu.epam.trainings.kokotov.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.epam.trainings.kokotov.dao.DatabaseConnection;
import edu.epam.trainings.kokotov.dao.DrugDAO;
import edu.epam.trainings.kokotov.dao.StaffDAO;
import edu.epam.trainings.kokotov.model.Drug;
import edu.epam.trainings.kokotov.model.Staff;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GetIndexPageServlet extends HttpServlet {

    private List<Drug> drugs;

    @Override
    public void init() throws ServletException {
        try {
            DatabaseConnection.establishСonnection();
            System.out.println("Установлено соединение с БД");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DrugDAO drugDAO = new DrugDAO(DatabaseConnection.getConnection());
        drugs = drugDAO.getAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("drugs", drugs);
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
        PrintWriter out=resp.getWriter();
        out.println("<h1>Update Employee</h1>");
    }

}
