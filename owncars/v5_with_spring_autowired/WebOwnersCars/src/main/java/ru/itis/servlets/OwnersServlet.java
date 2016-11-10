package ru.itis.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.models.Owner;
import ru.itis.services.OwnerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by AlexLevor on 25.10.2016.
 */
public class OwnersServlet{
    @Autowired
    private OwnerService ownerService;
    private static Logger log = Logger.getLogger(OwnersServlet.class.getName());

    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public ModelAndView ownersRequestGet() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("owners");
        List<Owner> ownerList = ownerService.getAllOwner();
        modelAndView.addObject("myOwners", ownerList);
        log.info("New owner has been added");
        return modelAndView;

    }/*
@RequestMapping(value = "/owners", method = RequestMethod.POST)
    public ModelAndView ownersRequestPost() throw Exception {
        ModelAndView modelAndView = new ModelAndView();
        String city = modelAndView.getView("city");
        int age = Integer.parseInt(httpServletRequest.getParameter("age"));
        String name = httpServletRequest.getParameter("name");
        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        Owner owner = new Owner (city, age, name, login, password);
        ownerService.addOwner(owner);

        if (owner != null) {
            httpServletRequest.getSession().setAttribute("myOwners", owner);
            httpServletResponse.sendRedirect("owners");
        }
        else {
            httpServletRequest.setAttribute("error", "Unknown user, please try again");
            httpServletRequest.getRequestDispatcher("/owners.jsp").forward(httpServletRequest, httpServletResponse);
            log.info("New owner has been added");
            return modelAndView;

        }

    }*/

}