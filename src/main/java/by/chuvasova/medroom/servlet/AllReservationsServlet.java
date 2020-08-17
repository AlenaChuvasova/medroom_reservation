package by.chuvasova.medroom.servlet;

import by.chuvasova.medroom.service.ReservationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static by.chuvasova.medroom.service.ReservationService.reservs;

@WebServlet("/allreservs")
public class AllReservationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReservationService reservationService = new ReservationService();
        try {
            reservationService.getListOfReserves();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("reservs", reservs);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/pages/allreservs.jsp");
        requestDispatcher.forward(req, resp);
    }
}
