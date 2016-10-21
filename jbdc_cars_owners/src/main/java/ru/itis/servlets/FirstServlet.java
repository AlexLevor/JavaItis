package ru.itis.servlets;

import ru.itis.SuportFactories.ServiceSupportFactory;
import ru.itis.models.Owner;
import ru.itis.services.OwnersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by AlexLevor on 21.10.2016.
 */
public class FirstServlet extends HttpServlet {
    private OwnersService ownersService;

    @Override
    public void init() {
        try {
            super.init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        ownersService = ServiceSupportFactory.getInstance().getServiceOwners();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html; charset=UTF-8");
        List<Owner> ownerList = ownersService.getAllOwner();
        request.setAttribute("myOwners", ownerList);
        try {
            getServletContext().getRequestDispatcher("/owners.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String city = request.getParameter("city");
        int age = Integer.parseInt(request.getParameter("age"));
        String name = request.getParameter("name");
        Owner owner = new Owner (city, age, name);
        ownersService.addOwner(owner);

        if (owner != null) {
            request.getSession().setAttribute("myOwners", owner);
            try {
                response.sendRedirect("users");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            request.setAttribute("error", "Unknown user, please try again");
            try {
                request.getRequestDispatcher("/owners.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
