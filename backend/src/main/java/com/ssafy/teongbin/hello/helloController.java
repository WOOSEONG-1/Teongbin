package com.ssafy.teongbin.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hello")
public class helloController {

    @GetMapping("/test")
    public String getHello(){
        return "helloTest";
    }

    @GetMapping("/{word}")
    @ResponseBody
    public String getVarHello(@PathVariable("word") String word){
        return word;
    }

}
