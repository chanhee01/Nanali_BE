package Nanali.domain.Member;

import Nanali.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MemberImg extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String imgName;

    private String imgUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    public MemberImg() {

    }

    public MemberImg(String imgName, String imgUrl, Member member) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.member = member;
    }

    public void update_memberImg (String imgName, String imgUrl) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}