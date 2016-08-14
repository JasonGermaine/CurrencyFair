package com.currencyfair.trading.web.controller;

import com.currencyfair.trading.TradingApplication;
import com.currencyfair.trading.web.converter.ConverterConfiguration;
import com.currencyfair.trading.web.jms.JmsConfiguration;
import com.currencyfair.trading.web.service.TradeService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jasongermaine.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TradingApplication.class, ConverterConfiguration.class, JmsConfiguration.class})
@WebAppConfiguration
@TestPropertySource(locations="classpath:test.properties")
public class TradeIT {

    private static final String TRADE_PATH = "/api/trade";

    @Autowired
    private ConversionService conversionService;
    private TradeService service;
    private TradeController controller;
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        service = mock(TradeService.class);
        controller = new TradeController(service, conversionService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testProcessTradeRequest() throws Exception {
        final long tradeId = 1L;
        when(service.processTrade(any())).thenReturn(tradeId);

        final MvcResult result = mockMvc.perform(
                post(TRADE_PATH)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"userId\": \"134256\", " +
                                "\"currencyFrom\": \"EUR\", " +
                                "\"currencyTo\": \"GBP\", " +
                                "\"amountSell\": 1000, " +
                                "\"amountBuy\": 747.10, " +
                                "\"rate\": 0.7471, " +
                                "\"timePlaced\" : \"24-JAN-15 10:27:44\", " +
                                "\"originatingCountry\" : \"FR\"}"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(new Long(result.getResponse().getContentAsString())).isEqualByComparingTo(tradeId);
    }
}