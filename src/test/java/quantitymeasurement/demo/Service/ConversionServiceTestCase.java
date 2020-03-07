package quantitymeasurement.demo.Service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import quantitymeasurement.demo.dto.QuantityDto;
import quantitymeasurement.demo.model.UnitMeasurement;
import quantitymeasurement.demo.service.UnitConversion;

public class ConversionServiceTestCase {

    @Test
    void given2FeetInQuantityDto_whenGetUnitValue_ThenReturn24Inch(){
        UnitConversion unitConversion = new UnitConversion();
        QuantityDto quantityDto=new QuantityDto(2, UnitMeasurement.UnitType.FEET,UnitMeasurement.UnitType.INCH);
        double convertedValues = unitConversion.getConvertedValues(quantityDto);
        Assert.assertEquals(24.0,convertedValues,0);
    }

    
}
