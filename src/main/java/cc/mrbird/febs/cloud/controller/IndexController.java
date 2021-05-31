package cc.mrbird.febs.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/", produces = "text/plain;charset=UTF-8")
    public String index() {
        return "欢迎访问云平台";
    }
}
