package quantitymeasurement.demo.dto;

import quantitymeasurement.demo.model.UnitMeasurement;

public class QuantityMeasurementDto {

    private double value;
    public UnitMeasurement.UnitType unit1;
    public UnitMeasurement.UnitType unit2;

    public QuantityMeasurementDto(double value, UnitMeasurement.UnitType unit1, UnitMeasurement.UnitType unit2) {
        this.value=value;
        this.unit1=unit1;
        this.unit2=unit2;
    }

    public QuantityMeasurementDto(int value, UnitMeasurement.UnitType unit) {
        this.value=value;
        this.unit1=unit;
    }

    public QuantityMeasurementDto() {
    }

    public QuantityMeasurementDto(UnitMeasurement.UnitType unit1, UnitMeasurement.UnitType unit2) {
        this.unit1=unit1;
        this.unit2=unit2;
    }

    public double getValue() {
        return value;
    }


}
