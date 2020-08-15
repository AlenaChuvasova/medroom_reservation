package by.chuvasova.medroom.servlet;

import by.chuvasova.medroom.dao.EmployeeDao;
import by.chuvasova.medroom.model.Employee;
import by.chuvasova.medroom.dao.impl.EmployeeDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addemployee")
public class AddEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/addemployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String position = req.getParameter("position");
        if (name.isEmpty() || surname.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/addemployee");
        } else {
            EmployeeDao employeeDao = new EmployeeDaoImpl();
            try {
                employeeDao.addNewEmployee(new Employee(name, surname, position));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/available-empl");
        }
    }
}
