package com.epam.rd.autotasks.springstatefulcalc.controllers;

import com.epam.rd.autotasks.springstatefulcalc.services.HandlerStatefulExpression;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;


/**
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/calc")
public class ResultController {
    @GetMapping("/result")
    public ResponseEntity<String> getResult(
            HttpSession session
    )
    {
        String result = HandlerStatefulExpression.calculateExpression(session);
        if(result == null) {
            String invalid = Objects.requireNonNull(session.getAttribute("invalidArgument")).toString();
            return ResponseEntity
                    .status(409)
                    .body(invalid + " is invalid");
        }

        return ResponseEntity
                .status(200)
                .body(result);

    }

}
