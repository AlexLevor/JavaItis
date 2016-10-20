package ru.itis.servlets;

import ru.itis.SuportFactories.ServiceSupportFactory;
import ru.itis.services.OwnersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by AlexLevor on 21.10.2016.
 */
public class FirstServlet extends HttpServlet {
    private OwnersService ownersService;

    @Override
    public void init() throws ServletException{
        super.init();
        ownersService = ServiceSupportFactory.getInstance().getServiceOwners();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){

    }
}
