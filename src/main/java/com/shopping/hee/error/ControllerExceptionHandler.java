package com.shopping.hee.error;

import com.shopping.hee.service.ItemService.NoItemsFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoItemsFoundException.class)
    public String handleNoItemsFound(NoItemsFoundException ex, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String prevUrl = (String) session.getAttribute("prevUrl");

        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("prevUrl", prevUrl);

        return "error";
    }
}
