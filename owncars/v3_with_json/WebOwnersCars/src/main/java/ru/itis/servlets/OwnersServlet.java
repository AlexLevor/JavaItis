package ru.itis.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.models.Owner;
import ru.itis.services.OwnerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AlexLevor on 25.10.2016.
 */
public class OwnersServlet extends HttpServlet {

    private OwnerService ownerService;
    private ObjectMapper objectMapper;
    private static Logger log = Logger.getLogger(OwnersServlet.class.getName());

    @Override
    public void init() {
        try {
            super.init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        ownerService = (OwnerService) applicationContext.getBean("ownerService");

    }
    private class RestRequest {
        private Pattern regExAllPattern = Pattern.compile("/owners");
        private Pattern regExIdPattern = Pattern.compile("/owners/([0-9]*)");

        private Integer id;
        private List<Owner> ownerList;

        public RestRequest(String pathInfo) throws ServletException {
            Matcher matcher;

            // Check for ID case first, since the All pattern would also match
            matcher = regExIdPattern.matcher(pathInfo);
            if (matcher.find()) {
                ownerList=ownerService.getAllOwner();
                return;
            }

            matcher = regExAllPattern.matcher(pathInfo);
            if (matcher.find()) return;

            throw new ServletException("Invalid URI");
        }

        public List<Owner> getOwner() {
            return ownerList;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("GET request handling");
        out.println(request.getPathInfo());
        out.println(request.getParameterMap());
        try {
            RestRequest resourceValues = new RestRequest(request.getPathInfo());
            out.println(resourceValues.getId());
        } catch (ServletException e) {
            response.setStatus(400);
            response.resetBuffer();
            e.printStackTrace();
            out.println(e.toString());
        }
        out.close();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Owner> ownerList = ownerService.getAllOwner();
            objectMapper = new ObjectMapper();
            String stringResponse = objectMapper.writeValueAsString(ownerList);
            response.setContentType("application/json");
            response.getWriter().write(stringResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
/*
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");


        String city = request.getParameter("city");
        int age = Integer.parseInt(request.getParameter("age"));
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Owner owner = new Owner (city, age, name, login, password);
        ownerService.addOwner(owner);

        if (owner != null) {
            request.getSession().setAttribute("myOwners", owner);
            response.sendRedirect("owners");
        }
        else {
            request.setAttribute("error", "Unknown user, please try again");
            request.getRequestDispatcher("/owners.jsp").forward(request, response);
        }
    }
*/
}
