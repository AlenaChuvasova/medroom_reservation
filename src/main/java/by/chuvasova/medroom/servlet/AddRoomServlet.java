package by.chuvasova.medroom.servlet;

import by.chuvasova.medroom.dao.RoomDao;
import by.chuvasova.medroom.dao.impl.RoomDaoImpl;
import by.chuvasova.medroom.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addroom")
public class AddRoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/addroom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {
        String roomNumber = req.getParameter("roomNumber");
        String roomType = req.getParameter("roomType");
        if (roomNumber.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/addroom");
        } else {
            RoomDao roomDao = new RoomDaoImpl();
            try {
                roomDao.addNewRoom(new Room(Integer.parseInt(roomNumber), roomType));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/availableroom");
        }
    }
}
