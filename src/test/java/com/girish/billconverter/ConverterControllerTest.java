package com.girish.billconverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;


@RunWith(SpringRunner.class)
@WebMvcTest(ConverterController.class)
public class ConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoinConverterService service;

    @Test
    public void convertShouldReturnValueFourFromService() throws Exception {
        when(service.convertToMinimumNumberOfCoins(100)).thenReturn(4);
        this.mockMvc.perform(get("/convert/100")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("4")));
    }

    @Test
    public void convertShouldThrowExceptionForIllegalArguments() throws Exception {
        when(service.convertToMinimumNumberOfCoins(100)).thenReturn(4);
        this.mockMvc.perform(get("/convert/abc")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void convertShouldThrowExceptionForNotEnoughAmount() throws Exception {
        when(service.convertToMinimumNumberOfCoins(100)).thenThrow(new CoinConverterException("test"));
        this.mockMvc.perform(get("/convert/100")).andDo(print()).andExpect(status().isInternalServerError());
    }
}