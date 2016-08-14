package com.currencyfair.trading.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jasongermaine.
 */
@Controller
@Slf4j
public class SwaggerController {

    @RequestMapping({"/swagger"})
    public void redirectToSwaggerUi(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }
}
