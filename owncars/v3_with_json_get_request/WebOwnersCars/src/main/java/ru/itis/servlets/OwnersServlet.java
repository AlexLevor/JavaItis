package ru.itis.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.models.Car;
import ru.itis.models.Owner;
import ru.itis.services.CarService;
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
    private CarService carService;
    private ObjectMapper objectMapper;

    private Pattern regExOwnerLoginPattern = Pattern.compile("/owners");
    private Pattern regExAllCarsPattern = Pattern.compile("/owners/([0-9]+)/cars");
    private Matcher matcher;
    private String stringResponse;

    private static Logger log = Logger.getLogger(OwnersServlet.class.getName());

    @Override
    public void init() {
        try {
            super.init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        this.ownerService = (OwnerService) applicationContext.getBean("ownerService");
        this.carService = (CarService) applicationContext.getBean("carService");
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {


        try{

            String requestPath = request.getServletPath();
        matcher=regExOwnerLoginPattern.matcher(requestPath);
        if (matcher.find()) {
            if (request.getParameter("age") == null) {
                stringResponse = objectMapper.writeValueAsString(ownerService.getAllOwner());
            }else{
                int age = Integer.parseInt(request.getParameter("age"));
                stringResponse = objectMapper.writeValueAsString(ownerService.getAllOwnersOfAge(age));
            }
        }
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
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }
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
