package com.currencyfair.trading.intf;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jasongermaine.
 */
public class TradeDTO {

    protected static final String DATE_TIME_PATTERN = "dd-MMM-yy HH:mm:ss";

    @NotNull(message = "No user ID present")
    @Min(value = 0, message = "'userId' must be greater than 0")
    @ApiModelProperty(required = true, example = "123456")
    private Long userId;

    @NotBlank(message = "Must specify a value for 'currencyFrom'")
    @ApiModelProperty(required = true, example = "EUR")
    private String currencyFrom;

    @NotBlank(message = "Must specify a value for 'currencyTo'")
    @ApiModelProperty(required = true, example = "GBP")
    private String currencyTo;

    @NotNull(message = "Must specify a value for 'amountSell'")
    @Min(value = 0, message = "'amountSell' must be greater than 0")
    @ApiModelProperty(required = true, example = "1000")
    private BigDecimal amountSell;

    @NotNull(message = "Must specify a value for 'amountBuy'")
    @Min(value = 0, message = "'amountBuy' must be greater than 0")
    @ApiModelProperty(required = true, example = "747.10")
    private BigDecimal amountBuy;

    @NotNull(message = "Must specify a value for 'rate'")
    @Min(value = 0, message = "'rate' must be greater than 0")
    @ApiModelProperty(required = true, example = "0.7471")
    private BigDecimal rate;

    @NotEmpty(message = "Must specify a value for 'originatingCountry'")
    @ApiModelProperty(required = true, example = "FR")
    private String originatingCountry;

    @ApiModelProperty(required = true, example = "14-Aug-16 19:59:59")
    @JsonFormat(pattern=DATE_TIME_PATTERN, timezone = "UTC")
    private Date timePlaced;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getAmountSell() {
        return amountSell;
    }

    public void setAmountSell(BigDecimal amountSell) {
        this.amountSell = amountSell;
    }

    public BigDecimal getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(BigDecimal amountBuy) {
        this.amountBuy = amountBuy;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    public Date getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(Date timePlaced) {
        this.timePlaced = timePlaced;
    }
}
