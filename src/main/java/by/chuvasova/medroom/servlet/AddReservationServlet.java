package by.chuvasova.medroom.servlet;

import by.chuvasova.medroom.dao.ReservationDao;
import by.chuvasova.medroom.dao.impl.ReservationDaoImpl;
import by.chuvasova.medroom.service.ReservationService;
import by.chuvasova.medroom.utils.DbDataUtils;
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
    ReservationDao reservationDao = new ReservationDaoImpl();
    TimeUtils timeUtils = new TimeUtils();
    DbDataUtils utils = new DbDataUtils();

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
        String fullName = req.getParameter("fullNames");
        String manipulationName = req.getParameter("manipulation");
        String description = req.getParameter("description");
        String inputStartTime = req.getParameter("startTime");
        String inputEndTime = req.getParameter("endTime");
        String status = req.getParameter("status");
        String roomNumber = req.getParameter("freeRoom");
        Integer roomId = null;
        try {
            roomId = reservationDao.getRoomId(utils.getRoomIdConverter(roomNumber));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = timeUtils.dateConverter(inputStartTime);
            endTime = timeUtils.dateConverter(inputEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String surname = utils.splitFullNames(fullName);
        Integer emplId = null;
        try {
            emplId = reservationDao.getEmployeeId(surname);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (!description.isEmpty()) {
            try {
                rs.addReservationFromUI(manipulationName, description, startTime, endTime,
                        Boolean.parseBoolean(status), emplId, roomId);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/allreservs");
        } else {
            resp.sendRedirect(req.getContextPath() + "/addreservation");
        }
    }
}