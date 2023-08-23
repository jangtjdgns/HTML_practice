package com.maepo.church.controller;

import com.maepo.church.entity.Announcements;
import com.maepo.church.repositoy.AnnouncementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/church")
public class HomeController {

    @Autowired
    private AnnouncementsRepository announcementsService;

    @GetMapping("/home")
    public String churchHomeForm(Model model){


        // 공지사항 게시글 체크된 제목 가져오기
        model.addAttribute("announcements", announcementsService.findByIsSelectedTrue());


        return "/pages/home";
    }
}
