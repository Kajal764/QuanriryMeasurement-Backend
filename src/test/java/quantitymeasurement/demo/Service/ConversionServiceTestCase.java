package quantitymeasurement.demo.Service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import quantitymeasurement.demo.Exception.MeasurementException;
import quantitymeasurement.demo.dto.QuantityDto;
import quantitymeasurement.demo.model.UnitMeasurement;
import quantitymeasurement.demo.service.UnitConversion;

import java.util.List;

public class ConversionServiceTestCase {

    @Test
    void given2FeetInQuantityDto_whenGetUnitValue_ThenReturn24Inch() throws MeasurementException {
        UnitConversion unitConversion = new UnitConversion();
        QuantityDto quantityDto=new QuantityDto(2, UnitMeasurement.UnitType.FEET,UnitMeasurement.UnitType.INCH);
        double convertedValues = unitConversion.getConvertedValues(quantityDto);
        Assert.assertEquals(24.0,convertedValues,0);
    }

    @Test
    void givenDifferentUnitType_whenConversion_thenItShouldThrowException() throws MeasurementException {
        try {
            QuantityDto quantityDto = new QuantityDto(1, UnitMeasurement.UnitType.GALLON, UnitMeasurement.UnitType.INCH);
            UnitConversion unitConversion = new UnitConversion();
            double convertedValues = unitConversion.getConvertedValues(quantityDto);
            System.out.println(convertedValues);
        } catch (MeasurementException e) {
            Assert.assertEquals("Enter Valid Data !!!", e.getMessage());
        }
    }

    @Test
    void givenDummyDataAsMainUnit_WhenGetUnit_ThenItShouldThrowException() throws MeasurementException {
        try {
            UnitConversion unitConversion = new UnitConversion();
            List<UnitMeasurement.UnitType> length = unitConversion.getUnit("HTGNEG");
            System.out.println(length);
        }
        catch (MeasurementException e){
            Assert.assertEquals("Unit Doesn't exist !!!",e.getMessage());
        }
    }

}

    
