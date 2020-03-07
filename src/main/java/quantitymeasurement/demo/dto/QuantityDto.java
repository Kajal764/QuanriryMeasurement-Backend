package quantitymeasurement.demo.dto;

import quantitymeasurement.demo.model.UnitMeasurement;

public class QuantityDto {

    public UnitMeasurement.UnitType unit1;
    public UnitMeasurement.UnitType unit2;
    public int value;

    public QuantityDto(int value, UnitMeasurement.UnitType unit1, UnitMeasurement.UnitType unit2) {
        this.value=value;
        this.unit1=unit1;
        this.unit2=unit2;
    }

    @Override
    public String toString() {
        return "QuantityDto{" +
                "unit1=" + unit1 +
                ", unit2=" + unit2 +
                ", value=" + value +
                '}';
    }
}
