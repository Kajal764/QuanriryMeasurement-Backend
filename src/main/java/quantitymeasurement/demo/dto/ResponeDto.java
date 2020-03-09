package quantitymeasurement.demo.dto;

import lombok.Getter;
import lombok.Setter;
import quantitymeasurement.demo.model.UnitMeasurement;

import java.util.List;

@Getter
@Setter
public class ResponeDto {

    private List<UnitMeasurement.UnitType> unit;
    private double convertedValue;
    private String message;

    public ResponeDto(String message, Double convertedValue) {
        this.message=message;
        this.convertedValue=convertedValue;
    }

}
