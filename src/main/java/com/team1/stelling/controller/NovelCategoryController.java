package com.team1.stelling.controller;

import com.team1.stelling.aspect.annotation.LogStatus;
import com.team1.stelling.domain.dto.NovelCategoryDTO;
import com.team1.stelling.domain.dto.PageableDTO;
import com.team1.stelling.service.NovelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;

@Controller
@Slf4j
@RequestMapping("/novel/category/*")
@RequiredArgsConstructor
public class NovelCategoryController {
    private  final NovelService novelService;


    // 기본정렬
    @LogStatus
    @GetMapping("novelCategory")
    public String novelCategory(@RequestParam(defaultValue="0") int categoryStatus, Model model, @PageableDefault(page = 0, size = 10, sort = "novelNumber" ,direction = Sort.Direction.DESC)Pageable pageable){
        Page<NovelCategoryDTO> list = null;
       if(categoryStatus == 1){
           Calendar cal = Calendar.getInstance();
           Date now = new Date();
           cal.setTime(now);
           cal.add(Calendar.DATE, -7);
           Date targetPast = cal.getTime();
           list = novelService.getNewNovelList(targetPast,now,pageable);
       }else if (categoryStatus == 2){
           list =   novelService.getEndNovelList(pageable);
       }else{ list = novelService.getList(pageable);}

        PageableDTO pageableDTO = new PageableDTO((int) list.getTotalElements(), pageable);
        pageableDTO.setCategoryStatus(categoryStatus);


        model.addAttribute( "list",list);
        model.addAttribute( "novelTotal", list.getTotalElements());
        model.addAttribute("pageableDTO", pageableDTO);
        return "novel/category/novelCategory";
    }

    // 좋아요 정렬
    @LogStatus
    @GetMapping("novelCategory/viewCount")
    public String novelCategoryViewCount(@RequestParam(defaultValue="0") int categoryStatus, Model model, @PageableDefault(page = 0, size = 10, sort = "novelViewCountTotal" ,direction = Sort.Direction.DESC)Pageable pageable){
        Page<NovelCategoryDTO> list = null;
        if(categoryStatus == 1){
            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            cal.add(Calendar.DATE, -7);
            Date targetPast = cal.getTime();
            list = novelService.getNewNovelList(targetPast,now,pageable);
        }else if (categoryStatus == 2){
            list =   novelService.getEndNovelList(pageable);
        }else{ list = novelService.getList(pageable);}
        PageableDTO pageableDTO = new PageableDTO( (int)list.getTotalElements(),pageable);
        pageableDTO.setCategoryStatus(categoryStatus);
        model.addAttribute( "list",list);
        model.addAttribute( "novelTotal", list.getTotalElements());
        model.addAttribute("pageableDTO", pageableDTO);
        return "novel/category/novelCategoryViewCount";
    }

    // 좋아요 정렬
    @LogStatus
    @GetMapping("novelCategory/likeCount")
    public String novelCategoryLikeCount(@RequestParam(defaultValue="0") int categoryStatus, Model model, @PageableDefault(page = 0, size = 10, sort = "novelLikeCountTotal" ,direction = Sort.Direction.DESC)Pageable pageable){
        Page<NovelCategoryDTO> list = null;
        if(categoryStatus == 1){
            // 신작
            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            cal.add(Calendar.DATE, -7);
            Date targetPast = cal.getTime();
            list = novelService.getNewNovelList(targetPast,now,pageable);
        }else if (categoryStatus == 2){
            list =   novelService.getEndNovelList(pageable);
        }else{ list = novelService.getList(pageable);}
        PageableDTO pageableDTO = new PageableDTO( (int)list.getTotalElements(),pageable);
        pageableDTO.setCategoryStatus(categoryStatus);
        model.addAttribute( "list",list);
        model.addAttribute( "novelTotal", list.getTotalElements());
        model.addAttribute("pageableDTO", pageableDTO);
        return "novel/category/novelCategoryLikeCount";
    }

    @LogStatus
    @GetMapping("novelSearch")
    public String novelSearch (@RequestParam(defaultValue="0") int categoryStatus, String keyword, Model model, @PageableDefault(page = 0, size = 10, sort = "novelNumber" ,direction = Sort.Direction.DESC) Pageable pageable){
        Page<NovelCategoryDTO> searchList = null;
        if(categoryStatus == 1){
            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            cal.add(Calendar.DATE, -7);
            Date targetPast = cal.getTime();
            searchList = novelService.getNewNovelListSearch(targetPast,now,keyword,pageable);
        }else if(categoryStatus == 2){
            searchList = novelService.getEndNovelListSearch(keyword,pageable);
        }else{
            searchList = novelService.search(keyword, pageable);
        }
//        Page<NovelCategoryDTO> searchList = novelSearchService.search(keyword, pageable);
        PageableDTO pageableDTO = new PageableDTO( (int)searchList.getTotalElements(),pageable);
        pageableDTO.setCategoryStatus(categoryStatus);
        searchList.getSize();
        pageableDTO.setKeyword(keyword);
        model.addAttribute("list", searchList);
        model.addAttribute( "novelTotal", searchList.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageableDTO", pageableDTO);
        return "novel/category/novelCategoryAllSearch";
    }

    @LogStatus
    @GetMapping("novelSearch/viewCount")
    public String novelSearchViewCount (@RequestParam(defaultValue="0") int categoryStatus, String keyword, Model model, @PageableDefault(page = 0, size = 10, sort = "novelViewCountTotal" ,direction = Sort.Direction.DESC) Pageable pageable){
        Page<NovelCategoryDTO> searchList = null;
        if(categoryStatus == 1){
            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            cal.add(Calendar.DATE, -7);
            Date targetPast = cal.getTime();
            searchList = novelService.getNewNovelListSearch(targetPast,now,keyword,pageable);
        }else if(categoryStatus == 2){
            searchList = novelService.getEndNovelListSearch(keyword,pageable);
        }else{
            searchList = novelService.search(keyword, pageable);
        }
//        Page<NovelCategoryDTO> searchList = novelSearchService.search(keyword, pageable);
        PageableDTO pageableDTO = new PageableDTO( (int)searchList.getTotalElements(),pageable);
        pageableDTO.setCategoryStatus(categoryStatus);
        searchList.getSize();
        pageableDTO.setKeyword(keyword);
        model.addAttribute("list", searchList);
        model.addAttribute( "novelTotal", searchList.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageableDTO", pageableDTO);
        return "novel/category/novelCategoryAllSearchViewCount";
    }

    @LogStatus
    @GetMapping("novelSearch/likeCount")
    public String novelSearchViewCountLikeCount (@RequestParam(defaultValue="0") int categoryStatus, String keyword, Model model, @PageableDefault(page = 0, size = 10, sort = "novelLikeCountTotal" ,direction = Sort.Direction.DESC) Pageable pageable){
        Page<NovelCategoryDTO> searchList = null;
        if(categoryStatus == 1){
            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            cal.add(Calendar.DATE, -7);
            Date targetPast = cal.getTime();
            searchList = novelService.getNewNovelListSearch(targetPast,now,keyword,pageable);
        }else if(categoryStatus == 2){
            searchList = novelService.getEndNovelListSearch(keyword,pageable);
        }else{
            searchList = novelService.search(keyword, pageable);
        }
//        Page<NovelCategoryDTO> searchList = novelSearchService.search(keyword, pageable);
        PageableDTO pageableDTO = new PageableDTO( (int)searchList.getTotalElements(),pageable);
        pageableDTO.setCategoryStatus(categoryStatus);
        searchList.getSize();
        pageableDTO.setKeyword(keyword);
        model.addAttribute("list", searchList);
        model.addAttribute( "novelTotal", searchList.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageableDTO", pageableDTO);
        return "novel/category/novelCategoryAllSearchLikeCount";
    }

    @LogStatus
    @GetMapping("novelFindToTag")
    public String novelFindToTag (@RequestParam(defaultValue="0") int categoryStatus, String keyword, Model model, @PageableDefault(page = 0, size = 10, sort = "novelNumber" ,direction = Sort.Direction.DESC)Pageable pageable){
        Page<NovelCategoryDTO> searchList = null;
        if(categoryStatus == 1){
            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            cal.add(Calendar.DATE, -7);
            Date targetPast = cal.getTime();
            searchList = novelService.getNewNovelListSearch(targetPast,now,keyword,pageable);
        }else if(categoryStatus == 2){
            searchList = novelService.getEndNovelListSearch(keyword,pageable);
        }else{
            searchList = novelService.search(keyword, pageable);
        }
        PageableDTO pageableDTO = new PageableDTO( (int)searchList.getTotalElements(),pageable);
        pageableDTO.setCategoryStatus(categoryStatus);
        searchList.getSize();
        pageableDTO.setKeyword(keyword);
        model.addAttribute("list", searchList);
        model.addAttribute( "novelTotal", searchList.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageableDTO", pageableDTO);
        return "novel/category/novelCategoryFindToTag";
    }

    @LogStatus
    @GetMapping("novelFindToTag/viewCount")
    public String novelFindToTagViewCount (@RequestParam(defaultValue="0") int categoryStatus, String keyword, Model model, @PageableDefault(page = 0, size = 10, sort = "novelViewCountTotal" ,direction = Sort.Direction.DESC)Pageable pageable){
        Page<NovelCategoryDTO> searchList = null;
        if(categoryStatus == 1){
            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            cal.add(Calendar.DATE, -7);
            Date targetPast = cal.getTime();
            searchList = novelService.getNewNovelListSearch(targetPast,now,keyword,pageable);
        }else if(categoryStatus == 2){
            searchList = novelService.getEndNovelListSearch(keyword,pageable);
        }else{
            searchList = novelService.search(keyword, pageable);
        }
        PageableDTO pageableDTO = new PageableDTO( (int)searchList.getTotalElements(),pageable);
        pageableDTO.setCategoryStatus(categoryStatus);
        searchList.getSize();
        pageableDTO.setKeyword(keyword);
        model.addAttribute("list", searchList);
        model.addAttribute( "novelTotal", searchList.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageableDTO", pageableDTO);
        return "novel/category/novelCategoryFindToTagViewCount";
    }

    @LogStatus
    @GetMapping("novelFindToTag/likeCount")
    public String novelFindToTagLikeCount (@RequestParam(defaultValue="0") int categoryStatus,String keyword, Model model, @PageableDefault(page = 0, size = 10, sort = "novelLikeCountTotal" ,direction = Sort.Direction.DESC)Pageable pageable){
        Page<NovelCategoryDTO> searchList = null;
        if(categoryStatus == 1){
            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            cal.add(Calendar.DATE, -7);
            Date targetPast = cal.getTime();
            searchList = novelService.getNewNovelListSearch(targetPast,now,keyword,pageable);
        }else if(categoryStatus == 2){
            searchList = novelService.getEndNovelListSearch(keyword,pageable);
        }else{
            searchList = novelService.search(keyword, pageable);
        }
        PageableDTO pageableDTO = new PageableDTO( (int)searchList.getTotalElements(),pageable);
        pageableDTO.setCategoryStatus(categoryStatus);
        searchList.getSize();
        pageableDTO.setKeyword(keyword);
        model.addAttribute("list", searchList);
        model.addAttribute( "novelTotal", searchList.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageableDTO", pageableDTO);
        return "novel/category/novelCategoryFindToTagLikeCount";
    }

    @GetMapping("endNovel")
    public String novelTest (@RequestParam(defaultValue="0") int categoryStatus,String keyword, Model model, @PageableDefault(page = 0, size = 10, sort = "novelNumber" ,direction = Sort.Direction.DESC)Pageable pageable){
        Page<NovelCategoryDTO> list =   novelService.getEndNovelList(pageable);
        PageableDTO pageableDTO = new PageableDTO( (int)list.getTotalElements(),pageable);
        pageableDTO.setCategoryStatus(categoryStatus);
        model.addAttribute( "list",list);
        model.addAttribute( "novelTotal", list.getTotalElements());
        model.addAttribute("pageableDTO", pageableDTO);
        return "novel/category/novelCategory";
    }

    @GetMapping("headerSearch")
    public String headerSearch(@RequestParam(defaultValue="0") int categoryStatus,@RequestParam String keyword, @RequestParam String type, Model model,@PageableDefault(page = 0, size = 10, sort = "novelNumber" ,direction = Sort.Direction.DESC)Pageable pageable){
        log.info("headerSearch k:"+keyword+"&&&  t :" + type);
        String searchTagType = "tag";
        String searchAllType = "all";
        Page<NovelCategoryDTO> list = null;
        if(type.equals(searchAllType)){
            // 작성자, 제목, 태그 전체 검색
            list =  novelService.getListSearchAll(keyword,pageable);
        }else if(type.equals(searchTagType)){
            // 태그로 검색
            log.info("들어옴@@@@");
            list =   novelService.search(keyword,pageable);
        }


        PageableDTO pageableDTO = new PageableDTO( (int)list.getTotalElements(),pageable);
        pageableDTO.setCategoryStatus(categoryStatus);
        model.addAttribute( "novelTotal", list.getTotalElements());
        model.addAttribute( "list",list);
        model.addAttribute("pageableDTO", pageableDTO);
            return  "novel/category/novelCategory";
    }
}
