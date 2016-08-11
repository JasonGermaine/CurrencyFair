package com.currencyfair.trading.web.converter;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

/**
 * @author jasongermaine.
 */
@Component
public class ConverterConfiguration {

    @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        factoryBean.setConverters(Sets.newHashSet(new TradeDtoToTradeConverter()));
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}
