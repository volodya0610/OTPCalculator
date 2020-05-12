package main.controller;

import main.component.CalculatorComponent;
import main.models.PaymentInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.models.InputDataDTO;
import main.models.OutputDataDTO;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
public class CalculatorController {
    @Autowired
    private CalculatorComponent calculatorComponent;

    @PostMapping(path = "/calculator",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputDataDTO> input(@RequestBody InputDataDTO inputData) {
        try {
            OutputDataDTO outputData = calculatorComponent.calculate(inputData);
            return ResponseEntity.status(HttpStatus.OK).body(outputData);
        }
        catch (Exception e) {
            OutputDataDTO outputData = new OutputDataDTO(false, new PaymentInfoDTO[0]);
            outputData.setMessage("Error : " + e.getMessage());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outputData);
        }

    }

}
