package quantitymeasurement.demo.service;

import org.springframework.stereotype.Service;
import quantitymeasurement.demo.dto.QuantityDto;

@Service
public class UnitConversion implements ConversionService {

    @Override
    public double getConvertedValues(QuantityDto unitDto) {
        return unitDto.value * unitDto.unit1.value / unitDto.unit2.value;
    }

}


