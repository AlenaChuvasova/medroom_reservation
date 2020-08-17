package by.chuvasova.medroom.servlet;

import by.chuvasova.medroom.service.ReservationService;
import by.chuvasova.medroom.utils.TimeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

@WebServlet("/addreservation")
public class AddReservationServlet extends HttpServlet {

    ReservationService rs = new ReservationService();
    TimeUtils timeUtils = new TimeUtils();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setAttribute("freeRoom", rs.getNumberAvailableListRoom());
            req.setAttribute("fullNames", rs.getEmployeeFullName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/pages/addreservation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String manipulationName = req.getParameter("manipulationName");
        String description = req.getParameter("description");
        String inputStartTime = req.getParameter("startTime");
        String inputEndTime = req.getParameter("endTime");
        String status = req.getParameter("true");
        String roomNumber = req.getParameter("roomNumber");
        String employeeId = req.getParameter("emplId");
        String roomId = req.getParameter("roomId");

        Date startTime = null;
        Date endTime = null;
        try {
            startTime = timeUtils.dateConverter(inputStartTime);
            endTime = timeUtils.dateConverter(inputEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (description.isEmpty()) {
            try {
                rs.addReservationFromUI(manipulationName, description, startTime, endTime,
                        Boolean.parseBoolean(status), Integer.parseInt(employeeId), Integer.parseInt(roomId));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/addreservation");
        } else {
            resp.sendRedirect(req.getContextPath() + "/allreservs");
        }
    }
}