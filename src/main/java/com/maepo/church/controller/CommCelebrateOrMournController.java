package com.maepo.church.controller;

import com.maepo.church.entity.CelebrateOrMourn;
import com.maepo.church.service.CommFamilyEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/church/소통")
public class CommCelebrateOrMournController {
    @Autowired
    private CommFamilyEventService commFamilyEventService;

    private static final String URL = "/pages/communication/friendshipNews/celebrateOrMourn/";

    // celebrate_or_mourn 약어 com

    // 글 목록
    @GetMapping("/com-list")
    public String comWriteForm(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                               String searchKeyword) {

        Page<CelebrateOrMourn> list = null;

        if(searchKeyword == null) {
            list = commFamilyEventService.postList(pageable);
        } else {
            list = commFamilyEventService.findByTitleContaining(searchKeyword, pageable);
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

        return URL + "comList";
    }

    // 글쓰기
    @GetMapping("/com-write")
    public String comWriteForm() {

        return URL + "comWrite";
    }

    @PostMapping("/com-writePro")
    public String comPro(CelebrateOrMourn celebrateOrMourn, Model model){

        commFamilyEventService.write(celebrateOrMourn);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/church/소통/com-list");

        return "/pages/communication/message";
    }
    // 글삭제

    // 글수정

    // 글보기

    // 제목검색
}