package com.team1.stelling.service;

import com.team1.stelling.domain.dao.ReplyDAO;
import com.team1.stelling.domain.repository.ReplyRepository;
import com.team1.stelling.domain.vo.ReplyVO;
import com.team1.stelling.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService{
    private final ReplyRepository replyRepository;

    public ReplyVO get(Long replyNo){return  replyRepository.findById(replyNo).get();}

    public List<ReplyVO> getList(Long subNovelNumber){return replyRepository.findAllBySubNovelVO_SubNovelNumberOrderByReplyNumber(subNovelNumber); }

    public List<UserVO> getUserList(Long subNovelNumber){return replyRepository.findAllBySubNovelVO_SubNovelNumberOrderByReplyNumber(subNovelNumber).stream().map(v->v.getUserVO()).collect(Collectors.toList());}

    public void register(ReplyVO replyVo){replyRepository.save(replyVo);}

    public void modify(ReplyVO replyVO){
        replyVO.updateReplyUpdateDate();
        replyRepository.save(replyVO);
    }

    public Page<ReplyVO> getReplyListByNovelNumber(Long novelNumber,Pageable pageable){return replyRepository.findAllByNovelVO_NovelNumber(novelNumber,pageable); }

    public Page<ReplyVO> getReplyListBySubNovelNumber(Long subNovelNumber, Pageable pageable){return replyRepository.findAllBySubNovelVO_SubNovelNumber(subNovelNumber,pageable);}

    public void removeReply(Long replyNumber){ replyRepository.deleteById(replyNumber);}
}
