package quantitymeasurement.demo.controller;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import quantitymeasurement.demo.dto.QuantityDto;
import quantitymeasurement.demo.model.UnitMeasurement;
import quantitymeasurement.demo.service.ConversionService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTestCases {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    ConversionService conversionService;
    Gson gson=new Gson();


    @Test
    void given2FeetAndUnitsInQuantityDtoThenResponseStatusShouldReturnOk() throws Exception {
        QuantityDto quantityDto = new QuantityDto(2, UnitMeasurement.UnitType.FEET,UnitMeasurement.UnitType.INCH);
        when(conversionService.getConvertedValues(any())).thenReturn(24.0);
        String jsonDto = gson.toJson(quantityDto);
        MvcResult mvcResult = this.mockmvc.perform(post("/convert").content(jsonDto).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }

    @Test
    void given2FeetInQuantityDto_whenGetUnitValue_ThenReturn24Inch() throws Exception {
        QuantityDto quantityDto = new QuantityDto(2, UnitMeasurement.UnitType.FEET,UnitMeasurement.UnitType.INCH);
        when(conversionService.getConvertedValues(any())).thenReturn(24.0);
        String jsonDto = gson.toJson(quantityDto);
        MvcResult mvcResult = this.mockmvc.perform(post("/convert").content(jsonDto).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("24.0"));

    }

    @Test
    void givenFeetWithoutConvertItToJsonInQuantityDtoThenResponseStatusShouldReturnBadRequest() throws Exception {
        QuantityDto quantityDto = new QuantityDto(2, UnitMeasurement.UnitType.FEET,UnitMeasurement.UnitType.INCH);
        when(conversionService.getConvertedValues(any())).thenReturn(24.0);
        MvcResult mvcResult = this.mockmvc.perform(post("/convert").content(String.valueOf(quantityDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400,status);

    }
}
