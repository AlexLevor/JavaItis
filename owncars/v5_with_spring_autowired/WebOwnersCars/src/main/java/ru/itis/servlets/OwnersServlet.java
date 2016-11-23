package ru.itis.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ru.itis.models.Owner;
import ru.itis.services.OwnerService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by AlexLevor on 25.10.2016.
 */
@Controller
public class OwnersServlet{
    @Autowired
    private OwnerService ownerService;
    private static Logger log = Logger.getLogger(OwnersServlet.class.getName());

    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public ModelAndView ownersRequestGet() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Owner> ownerList = ownerService.getAllOwner();
        modelAndView.addObject("myOwners", ownerList);
        log.info("New owner has been added");
        modelAndView.setViewName("owners");
        return modelAndView;

    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView ownersRequestPost(@RequestParam("login") String login,
                                          @RequestParam("password") String password, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        ownerService.setToken(login, password);
        response.addCookie(new Cookie("token", ownerService.getToken(login)));
        modelAndView.setViewName("redirect:/owners");
        return modelAndView;
    }

    @RequestMapping(value = "/owners", method = RequestMethod.POST)
    public ModelAndView ownersRequestPost(@RequestParam("city") String city, @RequestParam("age") int age,
                                          @RequestParam("name") String name,@RequestParam("login") String login,
                                          @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        Owner owner = new Owner (city, age, name, login, password);
        ownerService.addOwner(owner);
        modelAndView.setViewName("redirect:/owners");
        return modelAndView;
    }
}