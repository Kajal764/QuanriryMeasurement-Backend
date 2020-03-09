package quantitymeasurement.demo.Service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import quantitymeasurement.demo.Exception.MeasurementException;
import quantitymeasurement.demo.dto.QuantityMeasurementDto;
import quantitymeasurement.demo.model.UnitMeasurement;
import quantitymeasurement.demo.service.UnitConversion;
import java.util.List;

@SpringBootTest
public class QuantityMeasurementServiceTest {

    @Autowired
    UnitConversion unitConversion;

    @Test
    void given2FeetInQuantityDto_whenGetUnitValue_ThenReturn24Inch() throws MeasurementException {
        UnitConversion unitConversion = new UnitConversion();
        QuantityMeasurementDto quantityDto=new QuantityMeasurementDto(2, UnitMeasurement.UnitType.FEET,UnitMeasurement.UnitType.INCH);
        double convertedValues = unitConversion.getConvertedValues(quantityDto);
        Assert.assertEquals(24.0,convertedValues,0);
    }

    @Test
    public void whenGivenZeroInch_ShouldReturnTrue() throws MeasurementException {
        QuantityMeasurementDto quantityDto=new QuantityMeasurementDto(0, UnitMeasurement.UnitType.INCH,UnitMeasurement.UnitType.INCH);
        double convertedValues = unitConversion.getConvertedValues(quantityDto);
        Assert.assertEquals(0,convertedValues,0);
    }

    @Test
    public void whenGiven0FeetAndInch_ItshouldReturnCorrectValue() throws MeasurementException {
        QuantityMeasurementDto quantityDto=new QuantityMeasurementDto(1, UnitMeasurement.UnitType.FEET,UnitMeasurement.UnitType.INCH);
        double convertedValues = unitConversion.getConvertedValues(quantityDto);
        System.out.println(convertedValues);
        Assert.assertEquals(12.0,convertedValues,0);

    }

    @Test
    public void whenGivenThreeFeet_WhenConvertToYard_ItShouldReturnCorrectValue() throws MeasurementException {
        QuantityMeasurementDto quantityMeasurementDto = new QuantityMeasurementDto(3.0, UnitMeasurement.UnitType.FEET, UnitMeasurement.UnitType.YARD);
        double convertedValues = unitConversion.getConvertedValues(quantityMeasurementDto);
        Assert.assertEquals(1.0,convertedValues,0);
    }

    @Test
    void given1Yard_WhenConvertToFeet_ThenItShouldReturnCorrectValue() throws MeasurementException {
        QuantityMeasurementDto quantityMeasurementDto = new QuantityMeasurementDto(1.0, UnitMeasurement.UnitType.YARD, UnitMeasurement.UnitType.FEET);
        double convertedValues = unitConversion.getConvertedValues(quantityMeasurementDto);
        System.out.println(convertedValues);
        Assert.assertEquals(3.0,convertedValues,0);
    }

    @Test
    public void whenGivenTwoInch_WhenConvertToCentimeter_ItShouldReturnCorrectValue() throws MeasurementException{
        QuantityMeasurementDto quantityMeasurementDto=new QuantityMeasurementDto(2, UnitMeasurement.UnitType.INCH, UnitMeasurement.UnitType.CENTIMETER);
        double convertedValues = unitConversion.getConvertedValues(quantityMeasurementDto);
        Assert.assertEquals(5,convertedValues,0.0);
    }

    @Test
    public void givenOneGallon_WhenConvertToLitre_ItShouldReturnCorrectValue() throws MeasurementException{
        QuantityMeasurementDto quantityMeasurementDto=new QuantityMeasurementDto(1, UnitMeasurement.UnitType.GALLON, UnitMeasurement.UnitType.LITRE);
        double convertedValues = unitConversion.getConvertedValues(quantityMeasurementDto);
        Assert.assertEquals(3.785,convertedValues,0.0);
    }

    @Test
    public void givenOneLitre_WhenConvertToMiliLitre_ItShouldReturnCorrectValue() throws MeasurementException{
        QuantityMeasurementDto quantityMeasurementDto=new QuantityMeasurementDto(1, UnitMeasurement.UnitType.LITRE,UnitMeasurement.UnitType.MILILITRE);
        double convertedValues = unitConversion.getConvertedValues(quantityMeasurementDto);
        Assert.assertEquals(1000.0,convertedValues,0.0);
    }

    @Test
    void givenOneKilogram_WhenConvertToGram_ItShouldReturnCorrectValue() throws MeasurementException {

        QuantityMeasurementDto quantityMeasurementDto = new QuantityMeasurementDto(1, UnitMeasurement.UnitType.KILOGRAM, UnitMeasurement.UnitType.GRAM);
        double convertedValues = unitConversion.getConvertedValues(quantityMeasurementDto);
        Assert.assertEquals(1000.0,convertedValues,0.0);
    }


    @Test
    void givenOneTonne_WhenConvertToKiloGram_ItShouldReturnCorrectValue() throws MeasurementException {
        QuantityMeasurementDto quantityMeasurementDto = new QuantityMeasurementDto(1, UnitMeasurement.UnitType.TONNE, UnitMeasurement.UnitType.KILOGRAM);
        double convertedValues = unitConversion.getConvertedValues(quantityMeasurementDto);
        Assert.assertEquals(1000.0,convertedValues,0.0);
    }

    @Test
    void givenDifferentUnitType_whenConversion_thenItShouldThrowException() throws MeasurementException {
        try {
            QuantityMeasurementDto quantityDto = new QuantityMeasurementDto(1, UnitMeasurement.UnitType.GALLON, UnitMeasurement.UnitType.INCH);
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
            List<UnitMeasurement.UnitType> length = unitConversion.getSubUnit("HTGNEG");
            System.out.println(length);
        }
        catch (MeasurementException e){
            Assert.assertEquals("Unit Doesn't exist !!!",e.getMessage());
        }
    }

}

    
