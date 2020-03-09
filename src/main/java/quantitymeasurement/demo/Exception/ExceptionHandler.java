package quantitymeasurement.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MeasurementException.class)
    public ResponseEntity measurementExceptionHandler(MeasurementException message)
    {
        return new ResponseEntity(message.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
