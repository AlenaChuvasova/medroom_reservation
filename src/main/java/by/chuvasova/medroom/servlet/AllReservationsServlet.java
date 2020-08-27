package by.chuvasova.medroom.servlet;

import by.chuvasova.medroom.dto.ReservationDto;
import by.chuvasova.medroom.service.ReservationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

@WebServlet("/allreservs")
public class AllReservationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReservationService reservationService = new ReservationService();
        Set<ReservationDto> reservations = null;
        try {
            reservations = reservationService.getListOfReserves();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("reservations", reservations);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/pages/allreservs.jsp");
        requestDispatcher.forward(req, resp);
        reservations.clear();
    }
}
