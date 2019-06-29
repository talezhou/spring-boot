package com.iinaq.springboot;

import com.iinaq.springboot.controller.PropController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PropControllerTest extends ChapterApplicationTests{

    @Autowired
    private PropController propController;
    @Test
    public void getProp(){
        propController.getProp();
    }
}
