package com.maepo.church.controller;

import com.maepo.church.entity.Announcements;
import com.maepo.church.service.CommAnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/church/소통")
public class CommAnnouncementsController {

    @Autowired
    private CommAnnouncementsService commAnnouncementsService;

    private static final String URL = "/pages/communication/churchNews/";

    // 공지사항 글쓰기
    @GetMapping("/notice-write")
    public String announcementsWriteForm() {

        return URL + "announcementsWrite";
    }

    // 공지사항 글 작성 처리
    @PostMapping("/notice-writePro")
    public String announcements(Announcements announcements, Model model) {

        commAnnouncementsService.write(announcements);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/church/소통/notice-list");

        return "/pages/communication/message";
    }

    // 공지사항 목록
    @GetMapping("/notice-list")
    public String announcementsList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                    String searchKeyword) {

        Page<Announcements> list = null;

        if(searchKeyword == null) {
            list = commAnnouncementsService.postList(pageable);
        } else {
            list = commAnnouncementsService.findByTitleContaining(searchKeyword, pageable);
        }


        int maxVisiblePages = 7;                    // 보이는 페이지 수
        int maxVisiblePage = maxVisiblePages - 1;

        int totalPages = list.getTotalPages();

        // 현재페이지가 7 미만일 때
        if (totalPages < maxVisiblePages){
            maxVisiblePages = totalPages;
            maxVisiblePage = maxVisiblePages - 1;
        }

        int currentPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(currentPage - maxVisiblePages / 2, 1);
        int endPage = Math.min(startPage + maxVisiblePages - 1, totalPages);

        if (startPage >= endPage - maxVisiblePage) {
            startPage = endPage - maxVisiblePage;
        }

        model.addAttribute("list", list);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages", totalPages);

        return URL + "announcementsList";
    }

    // 공지사항 설정 -팝업창
    @GetMapping("/notice-setting")
    public String announcementsSetting(Model model) {

        model.addAttribute("articleSet", commAnnouncementsService.announcementsList());


        return URL + "announcementsSetting";
    }

    @PostMapping("/notice-listSetting")
    public String listSettingForm(@RequestParam List<Integer> selectedBox, Model model) {

        // false로 is_selected 전부 초기화
        commAnnouncementsService.resetSelected();

        for (Integer id : selectedBox) {
            commAnnouncementsService.updateIsSelectedById(id);
        }

        model.addAttribute("message", "저장되었습니다.");
        model.addAttribute("searchUrl", "/church/소통/notice-setting");

        return "/pages/communication/message";
    }


    // 게시글 보기
    @GetMapping("/notice-view")
    public String announcementsView(Model model, Integer id) {

        model.addAttribute("announcements", commAnnouncementsService.postView(id));

        commAnnouncementsService.incrementHit(id);

        return URL + "announcementsView";
    }


    // 게시글 수정
    @GetMapping("/notice-modify/{id}")
    public String announcementsModify(@PathVariable("id") Integer id, Model model){

        model.addAttribute("announcements", commAnnouncementsService.postView(id));

        return URL + "announcementsModify";
    }

    @PostMapping("/notice-update/{id}")
    public String announcementsUpdate(@PathVariable("id") Integer id, Announcements announcements, Model model) {

        Announcements announcementsTemp = commAnnouncementsService.postView(id);
        announcementsTemp.setTitle(announcements.getTitle());
        announcementsTemp.setContent(announcements.getContent());
        announcementsTemp.setAuthor(announcements.getAuthor());

        commAnnouncementsService.write(announcementsTemp);

        model.addAttribute("message", "수정되었습니다.");
        model.addAttribute("searchUrl", "/church/소통/notice-view?id=" + id);

        return "/pages/communication/message";
    }

    // 삭제
    @GetMapping("/notice-delete")
    public String announcementsDelete(Integer id){

        commAnnouncementsService.postDelete(id);

        // redirect 사용시 한글 url인 경우 인코딩 해야 리턴됨
        return "redirect:/church/%EC%86%8C%ED%86%B5/notice-list";
    }
}
