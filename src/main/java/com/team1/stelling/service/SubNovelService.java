package com.team1.stelling.service;

import com.team1.stelling.domain.repository.SubNovelRepository;
import com.team1.stelling.domain.vo.SubNovelVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubNovelService {
    private final SubNovelRepository subNovelRepository;

    public SubNovelVO get(Long snNo){return subNovelRepository.findById(snNo).get();}

    public List<SubNovelVO> getList(){return subNovelRepository.findAll();}

    public void register(SubNovelVO vo){subNovelRepository.save(vo);}

    public void modify(SubNovelVO vo){
        vo.updateSubNovelUpdateDate();
        subNovelRepository.save(vo);
    }

    public Page<SubNovelVO> getListByNovelNumber(Long novelNumber,  Pageable pageable){return subNovelRepository.findAllByNovelVO_NovelNumber(novelNumber, pageable);}

    public List<SubNovelVO> getListByNovelNumber(Long novelNumber){return subNovelRepository.findAllByNovelVO_NovelNumberOrderBySubNovelUploadDate(novelNumber);}

    public List<SubNovelVO> getListByNovelNumOrderSubNum(Long novelNumber){return subNovelRepository.findAllByNovelVO_NovelNumberOrderBySubNovelNumberAsc(novelNumber);}

    public void removeSubNovelVO(Long snNo){subNovelRepository.deleteById(snNo);}
}
