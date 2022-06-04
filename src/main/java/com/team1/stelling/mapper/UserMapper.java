package com.team1.stelling.mapper;

import com.team1.stelling.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    public Long login(Map<String, String> loginMap);
    public int idCheck(String userId);
    public int emailCheck(String userEmail);
    public String getSearchId(String userNick, String phoneNum);
    public String findPw(String userId, String userEmail);
    public UserVO findUserId(String userId);
    public UserVO findByUserId(String userId);
    public UserVO findByUserNumber(Long userNumber);
    public int findUserEmail(String userEmail);
    public Long findUserNumberByEmail(String userEmail);
}
