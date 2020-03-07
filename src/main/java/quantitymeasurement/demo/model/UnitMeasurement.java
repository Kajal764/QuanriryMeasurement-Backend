package quantitymeasurement.demo.model;

public class UnitMeasurement {


    public enum UnitType{
        FEET(12, "LENGTH"),
        INCH(1, "LENGTH"),
        YARD(36, "LENGTH"),
        CENTIMETER(1 / 2.54, "LENGTH"),
        LITRE(1000, "VOLUME"),
        GALLON(3785, "VOLUME"),
        MILILITRE(1, "VOLUME"),
        TONNE(1000000, "Mass"),
        KILOGRAM(1000, "Mass"),
        GRAM(1, "Mass"),
        FARHANHIT(1, "TEMPERATURE"),
        CELCIUS(2.12, "TEMPERATURE");

        public double value;
        public String unitType;

        UnitType(double val, String unittype) {
            this.value = val;
            this.unitType = unittype;
        }

    }

}
