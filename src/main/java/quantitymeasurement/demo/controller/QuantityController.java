package quantitymeasurement.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quantitymeasurement.demo.Exception.MeasurementException;
import quantitymeasurement.demo.dto.QuantityDto;
import quantitymeasurement.demo.dto.ResponeDto;
import quantitymeasurement.demo.model.UnitMeasurement;
import quantitymeasurement.demo.service.ConversionService;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController

public class QuantityController {

    @Autowired
    private ConversionService conversionService;

    @PostMapping("/convert")
    public ResponseEntity<ResponeDto> getUnitValue(@RequestBody QuantityDto userDto) throws MeasurementException {
        double convertedValue = conversionService.getConvertedValues(userDto);
        ResponeDto value = new ResponeDto("Unit", convertedValue);
        return new ResponseEntity<ResponeDto>(value, HttpStatus.OK);
    }

    @GetMapping("{subUnit}")
    public List<UnitMeasurement.UnitType> getUnit(@PathVariable String subUnit) throws MeasurementException {
        List<UnitMeasurement.UnitType> unit = conversionService.getUnit(subUnit);
        return unit;
    }

    @GetMapping("/mainunit")
    public List getMainUnit(){
        List mainUnit = conversionService.getMainUnit();
        return mainUnit;
    }


}


