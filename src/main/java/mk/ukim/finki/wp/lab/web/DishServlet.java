package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final DishService dishService;
    private final ChefService chefService;

    public DishServlet(DishService dishService, SpringTemplateEngine templateEngine, DishService dishService1, ChefService chefService) {
        this.templateEngine = templateEngine;
        this.dishService = dishService1;
        this.chefService = chefService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long ChefId = Long.valueOf(req.getParameter("chefId"));

        Chef theOne = chefService.findById(ChefId);
        List<Dish> dishes = dishService.listDishes();

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("selectedChef", theOne);
        context.setVariable("dishes", dishes);
        context.setVariable("chefId", ChefId);

        templateEngine.process("dishesList.html", context, resp.getWriter());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fallback: redirect home if accessed via GET directly
        resp.sendRedirect("/");
    }
}
