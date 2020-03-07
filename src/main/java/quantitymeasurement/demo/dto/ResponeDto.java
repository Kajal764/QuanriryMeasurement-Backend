package quantitymeasurement.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponeDto {

    private double convertedValue;
    private String message;

    public ResponeDto(String message, double convertedValue) {
        this.message=message;
        this.convertedValue=convertedValue;

    }

    @Override
    public String toString() {
        return "ResponeDto{" +
                "convertedValue=" + convertedValue +
                ", message='" + message + '\'' +
                '}';
    }
}
