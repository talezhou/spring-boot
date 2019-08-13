package com.iinaq.springboot.domain;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Book {

    private Integer id;

    @NotBlank(message = "name不能为空")
    @Length(min = 2,max = 8,message = "name必须在{min}-{max}之间")
    private String name;

    @NotNull(message = "price不能为空")
    @DecimalMin(value = "0.1",message = "price不能低于{value}")
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
