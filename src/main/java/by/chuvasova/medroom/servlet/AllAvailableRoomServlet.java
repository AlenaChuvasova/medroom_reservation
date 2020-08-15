package by.chuvasova.medroom.servlet;

import by.chuvasova.medroom.dao.RoomDao;
import by.chuvasova.medroom.model.Room;
import by.chuvasova.medroom.dao.impl.RoomDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/availableroom")
public class AllAvailableRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomDao roomDao = new RoomDaoImpl();
        Set<Room> rooms = null;
        try {
            rooms = roomDao.getAllAvailableRooms();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("rooms", rooms);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/pages/availableroom.jsp");
        requestDispatcher.forward(req, resp);
    }
}
