package quantitymeasurement.demo.service;

import quantitymeasurement.demo.Exception.MeasurementException;
import quantitymeasurement.demo.dto.QuantityMeasurementDto;
import quantitymeasurement.demo.model.MainUnit;
import quantitymeasurement.demo.model.UnitMeasurement;

import java.util.List;


public interface ConversionService {
     double getConvertedValues(QuantityMeasurementDto unitDto) throws MeasurementException;
     List<UnitMeasurement.UnitType> getSubUnit(String unitType) throws MeasurementException;
     List<MainUnit> getMainUnit();
}
