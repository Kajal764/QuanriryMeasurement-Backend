package quantitymeasurement.demo.service;

import org.springframework.stereotype.Service;
import quantitymeasurement.demo.dto.QuantityDto;


public interface ConversionService {
     double getConvertedValues(QuantityDto unitDto);

}
