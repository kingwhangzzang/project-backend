package com.king.projectbackend.service;

import com.king.projectbackend.dao.MemberDao;
import com.king.projectbackend.entity.Member;
import com.king.projectbackend.util.AesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDao memberDao;
    private final AesUtil aesUtil;

    @Transactional
    public boolean join(Member member) {
        boolean result = memberDao.insertMember(encodeMember(member));

        return result;
    }
    // 복호화메서드수정 필요 할수도 ~
    public Member decodeMember(Member member) {
        try {
            Member result = new Member();
            String memberId = aesUtil.aesCbcDecode(member.getMemberLoginId());
            String name = aesUtil.aesCbcDecode(member.getMemberName());
            String nickname = aesUtil.aesCbcDecode(member.getMemberNickname());
            String tel = aesUtil.aesCbcDecode(member.getMemberTel());
            result.setMemberLoginId(memberId);
            result.setMemberName(name);
            result.setMemberNickname(nickname);
            result.setMemberTel(tel);
            result.setMemberPassword(member.getMemberPassword());
            result.setMemberIdx(member.getMemberIdx());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    //데이터값 한개
    public String decodeGetOne(String data){
        try {
            String result = aesUtil.aesCbcDecode(data);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String encodeGetOne(String data){
        try {
            String result = aesUtil.aesCbcEncode(data);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //암호화 메서드
    public Member encodeMember(Member member) {
        try {
            String memberId =  aesUtil.aesCbcEncode(member.getMemberLoginId());
            String name = aesUtil.aesCbcEncode(member.getMemberName());
            String nickname = aesUtil.aesCbcEncode(member.getMemberNickname());
            String tel = aesUtil.aesCbcEncode(member.getMemberTel());
            member.setMemberLoginId(memberId);
            member.setMemberName(name);
            member.setMemberNickname(nickname);
            member.setMemberTel(tel);
            return member;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Member login(String loginId) {
        //1.암호화 작업
        String encode = encodeGetOne(loginId);
        System.out.println("memberService:encode"+encode);
        //2.DB에서 내가 암호화한걸 가져옴
        Member result =memberDao.selectByIdMember(encode);
        if(result != null){
            return decodeMember(result);
        }else{
            return null;
        }
    }
}
