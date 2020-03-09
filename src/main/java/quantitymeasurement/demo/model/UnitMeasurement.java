package quantitymeasurement.demo.model;

public class UnitMeasurement {

    public enum UnitType{

        FEET(12, MainUnit.LENGTH),
        INCH(1, MainUnit.LENGTH),
        YARD(36,MainUnit.LENGTH),
        CENTIMETER(1 / 2.5,MainUnit.LENGTH),
        LITRE(1000, MainUnit.VOLUME),
        GALLON(3785, MainUnit.VOLUME),
        MILILITRE(1,MainUnit.VOLUME),
        TONNE(1000000, MainUnit.MASS),
        KILOGRAM(1000, MainUnit.MASS),
        GRAM(1, MainUnit.MASS),
        FARHANHIT(1, MainUnit.TEMPERATURE),
        CELCIUS(2.12, MainUnit.TEMPERATURE);

        public double value;
        public MainUnit unitType;

        UnitType(double val, MainUnit unitType) {
            this.value = val;
            this.unitType = unitType;
        }
    }
}


