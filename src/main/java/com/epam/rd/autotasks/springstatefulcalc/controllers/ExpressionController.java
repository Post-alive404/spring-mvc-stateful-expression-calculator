package com.epam.rd.autotasks.springstatefulcalc.controllers;

import com.epam.rd.autotasks.springstatefulcalc.services.CalculatorIsBadFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@RestController
@RequestMapping("/calc/expression")
public class ExpressionController {

    @PutMapping
    public ResponseEntity<String> putExpression(
            @RequestBody String expression,
            HttpSession session
    ){
        ResponseEntity.BodyBuilder responseEntity;
        if(session.getAttribute("expression") != null){
            responseEntity = ResponseEntity.status(200).header("Location", expression);
        } else {
            responseEntity = ResponseEntity.status(201).header("Location", expression);
        }

        if(CalculatorIsBadFormat.isBadFormatExpression(expression)){
            return ResponseEntity
                    .status(400)
                    .header("Location", expression)
                    .body("Incorrect value (badly formatted) for expression "  + expression);
        }

        session.setAttribute("expression", expression);

        return responseEntity.build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteExpression(
            @RequestBody String expression,
            HttpSession session
    ){

        if(session.getAttribute("expression") == null){
            return ResponseEntity
                    .status(409)
                    .header("Location", expression)
                    .body(expression + " is invalid");

        }

        session.setAttribute("expression", null);

        return ResponseEntity
                .status(204)
                .header("Location", expression)
                .build();
    }

}
