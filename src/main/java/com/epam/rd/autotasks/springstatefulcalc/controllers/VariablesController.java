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
@RequestMapping("/calc/{variable_name}")
public class VariablesController {

    @PutMapping
    public ResponseEntity<String> putVariables(
            @PathVariable String variable_name,
            @RequestBody String value,
            HttpSession session)
    {
        ResponseEntity.BodyBuilder responseEntity;

        if(CalculatorIsBadFormat.isBadFormatVariable(variable_name)){
            return ResponseEntity
                    .status(400)
                    .header("Location", variable_name)
                    .body("Incorrect value (badly formatted) for variable "  + variable_name);
        }
        if(CalculatorIsBadFormat.isBadFormatValue(value)){
            return ResponseEntity
                    .status(403)
                    .header("Location", variable_name)
                    .body("Incorrect value (badly formatted) for variable "  + variable_name);
        }

        if(session.getAttribute(variable_name) != null){
            responseEntity = ResponseEntity.status(200).header("Location", variable_name);
        } else {
            responseEntity = ResponseEntity.status(201).header("Location", variable_name);
        }

        session.setAttribute(variable_name, value);

        return responseEntity.build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteVariables(
            @PathVariable String variable_name,
            HttpSession session
    ){

        if(session.getAttribute("expression") == null){
            return ResponseEntity
                    .status(409)
                    .header("Location", variable_name)
                    .body(variable_name + " is invalid");

        }

        session.setAttribute(variable_name, null);

        return ResponseEntity
                .status(204)
                .header("Location", variable_name)
                .build();
    }

}
