package quantitymeasurement.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import quantitymeasurement.demo.dto.QuantityDto;
import quantitymeasurement.demo.dto.ResponeDto;
import quantitymeasurement.demo.service.ConversionService;


@CrossOrigin(origins = "*")
@RestController
public class QuantityController {

    @Autowired
    private ConversionService conversionService;

    @PostMapping("/convert")
    public ResponseEntity<ResponeDto> getUnitValue(@RequestBody QuantityDto userDto) {
        double convertedValue = conversionService.getConvertedValues(userDto);
        ResponeDto value = new ResponeDto("Unit", convertedValue);
        return new ResponseEntity<ResponeDto>(value, HttpStatus.OK);
    }
}


