package com.maepo.church.controller;

import com.maepo.church.entity.CelebrateOrMourn;
import com.maepo.church.service.CommCelebrateOrMournService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/church/소통")
public class CommCelebrateOrMournController {
    @Autowired
    private CommCelebrateOrMournService commCelebrateOrMournService;

    private static final String URL = "/pages/communication/friendshipNews/celebrateOrMourn/";

    // celebrate_or_mourn 약어 com

    // 글 목록
    @GetMapping("/com-list")
    public String comWriteForm(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                               String searchKeyword) {


        List<CelebrateOrMourn> postList = commCelebrateOrMournService.postId();

        for(CelebrateOrMourn celebrateOrMournItem : postList){

            int id = celebrateOrMournItem.getId();

            String getName = commCelebrateOrMournService.getOccasionType(id);

            String getRoleDescription = commCelebrateOrMournService.getRoleDescription(id);

            celebrateOrMournItem.setOccasionType(getName);
            celebrateOrMournItem.setRoleDescription(getRoleDescription);
        }

        Page<CelebrateOrMourn> list = null;

        if(searchKeyword == null) {
            list = commCelebrateOrMournService.postList(pageable);
        } else {
            list = commCelebrateOrMournService.findByNameContaining(searchKeyword, pageable);
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

        commCelebrateOrMournService.write(celebrateOrMourn);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/church/소통/com-list");

        return "/pages/communication/message";
    }

    // 글보기
    @GetMapping("/com-view")
    public String comView(Model model, Integer id) {

        model.addAttribute("com", commCelebrateOrMournService.postView(id));

        return URL + "comView";
    }

    // 글삭제
    @GetMapping("/com-delete")
    public String comDelete(Integer id) {

        commCelebrateOrMournService.postDelete(id);

        return "redirect:/church/%EC%86%8C%ED%86%B5/com-list";
    }

    // 글수정
    @GetMapping("/com-modify/{id}")
    public String comModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("com", commCelebrateOrMournService.postView(id));

        return URL + "comModify";
    }

    // 업데이트
    @PostMapping("/com-update/{id}")
    public String comUpdate(@PathVariable("id") Integer id, CelebrateOrMourn celebrateOrMourn, Model model){

        CelebrateOrMourn celebrateOrMournTemp = commCelebrateOrMournService.postView(id);
//        celebrateOrMournTemp.setTitle(celebrateOrMourn.getTitle());
//        celebrateOrMournTemp.setContent(celebrateOrMourn.getContent());
//        celebrateOrMournTemp.setAuthor(celebrateOrMourn.getAuthor());

        commCelebrateOrMournService.write(celebrateOrMournTemp);

        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/church/소통/com-view?id=" + id);

        return "/pages/communication/message";
    }
}
