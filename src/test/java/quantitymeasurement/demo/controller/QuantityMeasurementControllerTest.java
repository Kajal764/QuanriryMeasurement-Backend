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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import quantitymeasurement.demo.dto.QuantityMeasurementDto;
import quantitymeasurement.demo.exception.MeasurementException;
import quantitymeasurement.demo.model.MainUnit;
import quantitymeasurement.demo.service.ConversionService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static quantitymeasurement.demo.model.UnitMeasurement.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    ConversionService conversionService;
    Gson gson=new Gson();

    @Test
    void given2FeetAndUnits_WhenGetConvertedValue_ThenResponseStatusShouldReturnOk() throws Exception {
        QuantityMeasurementDto quantityDto = new QuantityMeasurementDto(2, UnitType.FEET, UnitType.INCH);
        when(conversionService.getConvertedValues(any())).thenReturn(24.0);
        String jsonDto = gson.toJson(quantityDto);
        MvcResult mvcResult = this.mockmvc.perform(post("/unit/convertedvalue").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }

    @Test
    void givenWrongUrl_WhenGetConvertedValue_ThenResponseStatusShouldReturnBadStatus() throws Exception {
        QuantityMeasurementDto quantityDto = new QuantityMeasurementDto(2, UnitType.FEET, UnitType.INCH);
        when(conversionService.getConvertedValues(any())).thenReturn(24.0);
        String jsonDto = gson.toJson(quantityDto);
        MvcResult mvcResult = this.mockmvc.perform(post("/unitconvertedvalue").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404,status);
    }

    @Test
    void givenSpaceInUrl_WhenGetConvertedValue_ThenResponseStatusShouldReturnBadStatus() throws Exception {
        QuantityMeasurementDto quantityDto = new QuantityMeasurementDto(2, UnitType.FEET, UnitType.INCH);
        when(conversionService.getConvertedValues(any())).thenReturn(24.0);
        String jsonDto = gson.toJson(quantityDto);
        MvcResult mvcResult = this.mockmvc.perform(post("/  unit/convertedvalue").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404,status);
    }


    @Test
    void given2Feet_WhenGetConvertedValue_ThenReturn24Inch() throws Exception {
        QuantityMeasurementDto quantityDto = new QuantityMeasurementDto(2, UnitType.FEET, UnitType.INCH);
        when(conversionService.getConvertedValues(any())).thenReturn(24.0);
        String jsonDto = gson.toJson(quantityDto);
        MvcResult mvcResult = this.mockmvc.perform(post("/unit/convertedvalue").content(jsonDto).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("24.0"));
    }

    @Test
    void givenFeetWithoutConvertItToJson_WhenWhenGetConvertedValue_ThenResponseStatusShouldReturnBadRequest() throws Exception {
        QuantityMeasurementDto quantityDto = new QuantityMeasurementDto(2, UnitType.FEET, UnitType.INCH);
        when(conversionService.getConvertedValues(any())).thenReturn(24.0);
        MvcResult mvcResult = this.mockmvc.perform(post("/unit/convertedvalue").content(String.valueOf(quantityDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400,status);
    }

    @Test
    void givenFeetAndInchAsUnit_WhenConvertToXML_ThenItShouldReturnBadStatus() throws Exception {
        QuantityMeasurementDto quantityDto = new QuantityMeasurementDto(2, UnitType.FEET, UnitType.INCH);
        when(conversionService.getConvertedValues(any())).thenReturn(24.0);
        String jsonDto = gson.toJson(quantityDto);
        MvcResult mvcResult = this.mockmvc.perform(post("/unit/convertedvalue").content(jsonDto).contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
                .andReturn();
        Assert.assertEquals(415,mvcResult.getResponse().getStatus());
    }

    @Test
    void givenLengthAsMainUnit_WhenGetUnit_ThenItShouldReturnItsSubUnit() throws Exception {
        MvcResult mvcResult = this.mockmvc.perform(MockMvcRequestBuilders.get("/unit/subunit/LENGTH")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }

    @Test
    void givenSpaceInurl_WhenGetUnit_ThenItStatusShouldReturnBad() throws Exception {
        MvcResult mvcResult = this.mockmvc.perform(MockMvcRequestBuilders.get("/  unit/subunit/LENGTH")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404,status);
    }

    @Test
    void givenDifferentValueAsMainUnit_WhenGetUnit_ThenItShouldReturnBadStatus() throws Exception {
        try {
            when(conversionService.getSubUnit(any())).thenThrow(MeasurementException.class);
            MvcResult mvcResult = this.mockmvc.perform(MockMvcRequestBuilders.get("/unit/subunit/HTGNEL")
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        }
        catch (MeasurementException e)
        {
            Assert.assertEquals("Unit Doesn't exist !!!",e.getMessage());
        }
    }

    @Test
    void givenLengthAsMainUnit_WhenConvertToXML_ThenItShouldReturnBadStatus() throws Exception {
        MvcResult mvcResult = this.mockmvc.perform(MockMvcRequestBuilders.get("/unit/subunit/LENGTH")
                .accept(MediaType.APPLICATION_ATOM_XML_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(406,status);
    }

    @Test
    void givenPostRequestInsteadOfGet_WhenGetUnit_ThenItShouldReturnBadStatus() throws Exception {
        MvcResult mvcResult = this.mockmvc.perform(MockMvcRequestBuilders.post("/unit/subunit/LENGTH")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(405,status);
    }


    @Test
    void givenLengthAsMainUnit_WhenGetUnit_ThenItShouldReturnCorrectList() throws Exception {
        when(conversionService.getSubUnit("LENGTH")).thenReturn(Collections.singletonList(UnitType.FEET));
        MvcResult mvcResult = this.mockmvc.perform(MockMvcRequestBuilders.get("/unit/subunit/LENGTH")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("FEET"));
    }

    @Test
    void givenPostRequestInsteadOfGet_WhenGetSubListUnit_ThenItShoudReturnBadStatus() throws Exception {
        MvcResult mvcResult = this.mockmvc.perform(MockMvcRequestBuilders.post("//unit/subunit/{}")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404,status);
    }


    @Test
    void givenUnit_ItShouldReturnMainUnit() throws Exception {
        String url="/unit/units";
        when(conversionService.getMainUnit()).thenReturn(Collections.singletonList(MainUnit.LENGTH));
        MvcResult mvcResult = this.mockmvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("LENGTH"));
    }

    @Test
    void givenUnit_ItShouldReturnMainUnitAndItsResponse() throws Exception {
        String url="/unit/units";
        when(conversionService.getMainUnit()).thenReturn(Collections.singletonList(MainUnit.LENGTH));
        MvcResult mvcResult = this.mockmvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("LENGTH"));
    }

    @Test
    void givenPostRequestInsteadOfGet_WhenGetListOfUnit_ThenItShouldReturnBadStatus() throws Exception {
        String url="/unit/units";
        MvcResult mvcResult = this.mockmvc.perform(MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(405,status);
    }
}

