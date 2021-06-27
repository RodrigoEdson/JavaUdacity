package com.udacity.pricing.PriceService.web;


import com.udacity.pricing.PriceService.api.PriceController;
import com.udacity.pricing.PriceService.service.PriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PriceController.class)
public class PriceControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PriceService priceService;

    @Test
    public void getPriceByVehicle () throws Exception{
        mockMvc.perform(get("/services/price?vehicleId=1"))
               .andExpect(status().isOk());

        verify(priceService, times(1)).getPrice(1L);
    }

}
