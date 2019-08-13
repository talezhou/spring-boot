package com.iinaq.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class FileUpLoadController {

    private static final Logger log = LoggerFactory.getLogger(FileUpLoadController.class);

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("/upload1")
    @ResponseBody
    public Map<String,String> upload1(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("文件类型 ： {}",file.getContentType());
        log.info("文件名称 : {}",file.getOriginalFilename());
        log.info("文件大小 ： {}",file.getSize());
        file.transferTo(new File("E:\\file\\"+file.getOriginalFilename()));
        Map<String, String> map = new HashMap<>(16);
        map.put("contentType",file.getContentType());
        map.put("fileName",file.getOriginalFilename());
        map.put("fileSize",file.getSize()+"");
        return map;
    }

    @PostMapping("/upload2")
    @ResponseBody
    public List<Map<String,String>> upload2(@RequestParam("file") MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0){
            return null;
        }

        List<Map<String,String>> list = new ArrayList<>();
        for (MultipartFile file:files) {
            file.transferTo(new File("E:\\file\\" + file.getOriginalFilename()));
            Map<String, String> map = new HashMap<>(16);
            map.put("contentType", file.getContentType());
            map.put("fileName", file.getOriginalFilename());
            map.put("fileSize", file.getSize() + "");
            list.add(map);
        }
        return list;
    }

    @PostMapping("/upload3")
    @ResponseBody
    public void upload3(String base64) throws IOException {
        final File file = new File("D:\\file\\img.jpg");
        String[] split = base64.split("base64,");
        final byte[] bytes = Base64Utils.decodeFromString(split.length > 1 ? split[1] : split[0]);
        FileCopyUtils.copy(bytes,file);
    }
}
