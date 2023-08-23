package com.maepo.church.controller;

import com.maepo.church.dto.AnnouncementsDTO;
import com.maepo.church.entity.Announcements;
import com.maepo.church.service.AnnouncementsService;
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
public class CommunicationController {

    @Autowired
    private AnnouncementsService announcementsService;

    // 공지사항 글쓰기
    @GetMapping("/write")
    public String announcementsWriteForm() {

        return "/pages/communication/churchNews/announcementsWrite";
    }

    // 공지사항 글 작성 처리
    @PostMapping("/writePro")
    public String announcements(AnnouncementsDTO announcementsDTO, Model model) {
        Announcements announcements = announcementsDTO.toEntity();

        announcementsService.write(announcements);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/church/소통/list");

        return "/pages/communication/churchNews/announcementsMessage";
    }

    // 공지사항 목록
    @GetMapping("/list")
    public String announcementsList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Announcements> list = announcementsService.announcementsList(pageable);

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

        return "/pages/communication/churchNews/announcementsList";
    }

    // 공지사항 설정 -팝업창
    @GetMapping("/setting")
    public String announcementsSetting(Model model) {

        model.addAttribute("articleSet", announcementsService.announcementsList());


        return "/pages/communication/churchNews/announcementsSetting";
    }

    @PostMapping("/listSetting")
    public String listSettingForm(@RequestParam List<Integer> selectedBox, Model model) {

        // false로 is_selected 전부 초기화
        announcementsService.resetSelected();

        for (Integer id : selectedBox) {
            announcementsService.updateIsSelectedById(id);
        }

        model.addAttribute("message", "저장되었습니다.");
        model.addAttribute("searchUrl", "/church/소통/setting");

        return "/pages/communication/churchNews/announcementsMessage";
    }


    // 게시글 보기
    @GetMapping("/view")
    public String announcementsView(Model model, Integer id) {

        model.addAttribute("announcements", announcementsService.announcementsView(id));

        announcementsService.incrementHit(id);

        return "/pages/communication/churchNews/announcementsView";
    }


    // 게시글 수정
    @GetMapping("/modify/{id}")
    public String announcementsModify(@PathVariable("id") Integer id, Model model){

        model.addAttribute("announcements", announcementsService.announcementsView(id));

        return "/pages/communication/churchNews/announcementsModify";
    }

    @PostMapping("/update/{id}")
    public String announcementsUpdate(@PathVariable("id") Integer id, Announcements announcements, Model model) {

        Announcements announcementsTemp = announcementsService.announcementsView(id);
        announcementsTemp.setTitle(announcements.getTitle());
        announcementsTemp.setContent(announcements.getContent());
        announcementsTemp.setAuthor(announcements.getAuthor());

        announcementsService.write(announcementsTemp);

        model.addAttribute("message", "수정되었습니다.");
        model.addAttribute("searchUrl", "/church/소통/view?id=" + id);

        return "/pages/communication/churchNews/announcementsMessage";
    }

    // 삭제
    @GetMapping("/delete")
    public String announcementsDelete(Integer id){

        announcementsService.announcementsDelete(id);

        // redirect 사용시 한글 url인 경우 인코딩 해야 리턴됨
        return "redirect:/church/%EC%86%8C%ED%86%B5/list";
    }
}
