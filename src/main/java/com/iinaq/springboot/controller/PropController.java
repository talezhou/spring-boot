package com.iinaq.springboot.controller;

import com.iinaq.springboot.config.MyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropController {

    private static final Logger log = LoggerFactory.getLogger(PropController.class);
    private final MyProperties prop;

    public PropController(MyProperties prop) {
        this.prop = prop;
    }

    @GetMapping("/prop")
    public MyProperties getProp() {
        log.info(prop.toString());
        return prop;
    }
}
