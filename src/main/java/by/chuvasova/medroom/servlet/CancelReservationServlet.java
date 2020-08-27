package by.chuvasova.medroom.servlet;

import by.chuvasova.medroom.service.ReservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cancel")
public class CancelReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/allreservs.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {
        String reservId = req.getParameter("id");
        if (reservId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cancel");
        } else {
            ReservationService reservationService = new ReservationService();
            try {
                reservationService.stopReservation(Integer.parseInt(reservId));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/allreservs");
        }
    }
}
