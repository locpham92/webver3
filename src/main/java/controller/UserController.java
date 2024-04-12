
package controller;

import model.Product;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userController", value = "/products")
public class UserController extends HttpServlet {
    private ProductService productService = new ProductService();
    public OrderService orderService = new OrderService();
    public OrderDetailService orderDetailService = new OrderDetailService();
    List<Integer> choices = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "home":
                showHomePage(req, resp);
                break;
            case "search"  :
                showSearchPage(req, resp);
                break;
            case "choose":
                addchoose(req, resp);
                break;

        }
    }
    private void addchoose(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
        int choose = Integer.parseInt(req.getParameter("idChoose"));
        choices.add(choose);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/search.jsp");
        dispatcher.forward(req, resp);
    }
    private void showAddPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private void showSearchPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/search.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.viewAll();
        req.setAttribute("productList", productList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/home.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "search":
                search(req, resp);
                break;
            case "total":
                total(req, resp);
                break;
        }
    }

    private void total(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
        List<Product> choicesP= new ArrayList<>();
        for (int i=0; i<choices.size();i++) {
            Product productchoices = productService.findById(choices.get(i));
            choicesP.add(productchoices);
        }
        for (Product choice : choicesP) {
            System.out.println(choice.getName());
        }
        req.setAttribute("listed",choicesP);
        choices=null;

        RequestDispatcher dispatcher = req.getRequestDispatcher("product/order.jsp");
        dispatcher.forward(req, resp);
    }


    private void search(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
        String keyword = req.getParameter("keyword");
        List<Product> products = productService.findByName(keyword);
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/search.jsp");
        dispatcher.forward(req, resp);
    }
}
