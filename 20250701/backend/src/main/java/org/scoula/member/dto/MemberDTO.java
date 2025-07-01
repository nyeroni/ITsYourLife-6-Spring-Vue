package org.scoula.member.dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    private String username;
    private String email;
    private Date regDate;
    private Date updateDate;
    private MultipartFile avatar;

    private List<String> authList;

    public static MemberDTO of(MemberVO vo) {
        return MemberDTO.builder()

                .username(vo.getUsername())
                .email(vo.getEmail())
                .regDate(vo.getRegDate())
                .updateDate(vo.getUpdateDate())
                .authList(vo.getAuthList().stream().map(a -> a.getAuth()).toList())
                .build();
    }

    public MemberVO toVO() {
        return MemberVO.builder()
                .username(username)
                .email(email)
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }

}
