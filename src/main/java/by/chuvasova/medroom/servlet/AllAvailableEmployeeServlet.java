package by.chuvasova.medroom.servlet;

import by.chuvasova.medroom.dao.EmployeeDao;
import by.chuvasova.medroom.model.Employee;
import by.chuvasova.medroom.dao.impl.EmployeeDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/available-empl")
public class AllAvailableEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Set<Employee> employees = null;
        try {
            employees = employeeDao.getAllAvailableEmployee();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("employees", employees);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/pages/available-empl.jsp");
        requestDispatcher.forward(req, resp);
    }
}
