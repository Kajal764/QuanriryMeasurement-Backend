package quantitymeasurement.demo.service;

import org.springframework.stereotype.Service;
import quantitymeasurement.demo.Exception.MeasurementException;
import quantitymeasurement.demo.dto.QuantityMeasurementDto;
import quantitymeasurement.demo.model.MainUnit;
import quantitymeasurement.demo.model.UnitMeasurement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitConversion implements ConversionService {

    @Override
    public double getConvertedValues(QuantityMeasurementDto unitDto) throws MeasurementException {
//        if(unitDto.unit1==null || unitDto.unit2==null)
//            throw new MeasurementException("Data Invalid");
        if(unitDto.unit1.unitType.equals(unitDto.unit2.unitType))
            return unitDto.value * unitDto.unit1.value / unitDto.unit2.value;
        throw new MeasurementException("Enter Valid Data !!!");
    }

    @Override
    public List<UnitMeasurement.UnitType> getSubUnit(String unitType) throws MeasurementException {
        List<UnitMeasurement.UnitType> collect = Arrays.stream(UnitMeasurement.UnitType.values())
                .filter(data -> data.unitType.toString().contains(unitType))
                .collect(Collectors.toList());
        if(collect.size()>1)
            return collect;
        throw new MeasurementException("Unit Doesn't exist !!!");
    }

    @Override
    public List getMainUnit() {
        List collect = Arrays.stream(MainUnit.values())
                .distinct().collect(Collectors.toList());
        return collect;
    }

}


