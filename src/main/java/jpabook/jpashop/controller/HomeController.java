package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j //logger 사용
public class HomeController {

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home"; //home.html(resources>templates)을 찾아서 반환
    }
}
