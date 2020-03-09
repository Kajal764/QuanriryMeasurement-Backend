package quantitymeasurement.demo.service;

import quantitymeasurement.demo.Exception.MeasurementException;
import quantitymeasurement.demo.dto.QuantityDto;
import quantitymeasurement.demo.model.MainUnit;
import quantitymeasurement.demo.model.UnitMeasurement;

import java.util.List;


public interface ConversionService {
     double getConvertedValues(QuantityDto unitDto) throws MeasurementException;
     List<UnitMeasurement.UnitType> getUnit(String unitType) throws MeasurementException;
     List<MainUnit> getMainUnit();
}
