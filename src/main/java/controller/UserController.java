
package controller;

import service.OrderDetailService;
import service.OrderService;
import service.RoleService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "userController", value = "/products")
public class UserController extends HttpServlet {
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();
    public OrderService orderService = new OrderService();
    public OrderDetailService orderDetailService = new OrderDetailService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "login":
                showLoginForm(req, resp);
                break;
        }
    }
    private void showLoginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("Users/Login/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "login":
                login(req, resp);
                break;

        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(userService.checkUser(username, password)){
            int id = userService.getIdUser(username, password);
            HttpSession session = req.getSession();
            session.setAttribute("idUser", id);
            if(roleService.findById(id).getName().equals("admin")){
                resp.sendRedirect("");
            } else {
                resp.sendRedirect("/product?action=home");
            }

        } else {
            resp.sendRedirect("http://localhost:8080/user?action=login");
        }
    }
}
