package by.chuvasova.medroom.servlet;

import by.chuvasova.medroom.dao.ReservationDao;
import by.chuvasova.medroom.dao.impl.ReservationDaoImpl;
import by.chuvasova.medroom.service.ReservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addreservation")
public class AddReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/addreservation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String manipulationName = req.getParameter("manipulationName");
        String description = req.getParameter("description");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        String roomId = req.getParameter("roomId");
        String employeeId = req.getParameter("employeeId");

        if (manipulationName.isEmpty() || description.isEmpty()) {
            ReservationService reservationService = new ReservationService();
            //create object from UI
//                reservationService.addReservationFromUI(manipulationName, description,
//                        startTime, endTime, roomId, employeeId);
            resp.sendRedirect(req.getContextPath() + "/addreservation");
        } else {
            resp.sendRedirect(req.getContextPath() + "/allreservation");
        }
    }
}