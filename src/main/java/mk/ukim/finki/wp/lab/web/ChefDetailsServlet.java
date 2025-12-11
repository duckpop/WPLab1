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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="ChefDetails", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {
    private final ChefService chefService;
    private final DishService dishService;
    private final TemplateEngine templateEngine;

    public ChefDetailsServlet(ChefService chefService, DishService dishService, TemplateEngine templateEngine) {
        this.chefService = chefService;
        this.dishService = dishService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String chefIdParam = req.getParameter("chefId");
        String dishId = req.getParameter("dishId");

        if (chefIdParam == null || chefIdParam.isEmpty() || dishId == null || dishId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing chefId or dishId");
            return;
        }

        Long chefId;
        try {
            chefId = Long.valueOf(chefIdParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid chefId format");
            return;
        }

        // add dish inside a @Transactional service method
        chefService.addDishToChef(chefId, dishId);

        // re-load chef, with dishes initialized inside the service
        Chef chef = chefService.findById(chefId);   // adjust to findByIdWithDishes if you add it
        if (chef == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Chef not found");
            return;
        }

        // force initialization in service or here (if still in tx); then copy
        List<Dish> chefDishes = List.copyOf(chef.getDishes());

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("selectedChef", chef);
        context.setVariable("dishes", chefDishes);

        templateEngine.process("chefDetails.html", context, resp.getWriter());
    }

}
