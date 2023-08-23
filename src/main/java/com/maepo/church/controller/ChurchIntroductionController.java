package com.maepo.church.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/church/교회")
public class ChurchIntroductionController {

    @GetMapping("/history")
    public String churchHistoryForm(){

        return "/pages/churchIntroduction/church_history";
    }
}
