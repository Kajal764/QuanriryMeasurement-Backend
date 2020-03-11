package quantitymeasurement.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quantitymeasurement.demo.exception.MeasurementException;
import quantitymeasurement.demo.dto.QuantityMeasurementDto;
import quantitymeasurement.demo.dto.ResponeDto;
import quantitymeasurement.demo.model.UnitMeasurement;
import quantitymeasurement.demo.service.ConversionService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/unit")
public class QuantityMeasurementController {

    @Autowired
    private ConversionService conversionService;

    @PostMapping("/convertedvalue")
    public ResponseEntity<ResponeDto> getUnitValue(@RequestBody QuantityMeasurementDto userDto) throws MeasurementException {
        System.out.println("Hellloooooooo");
        double convertedValue = conversionService.getConvertedValues(userDto);
        ResponeDto value = new ResponeDto("Unit", convertedValue);
        return new ResponseEntity<ResponeDto>(value, HttpStatus.OK);
    }

    @GetMapping("/subunit/{unit}")
    public List<UnitMeasurement.UnitType> getUnit(@PathVariable("unit") String unit) throws MeasurementException {
        List<UnitMeasurement.UnitType> units = conversionService.getSubUnit(unit);
        return units;
    }

    @GetMapping("/units")
    public List getMainUnit(){
        List mainUnit = conversionService.getMainUnit();
        return mainUnit;
    }
}


