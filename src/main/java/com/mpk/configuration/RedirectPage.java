package com.mpk.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectPage {
    @RequestMapping(value = "/**/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}
