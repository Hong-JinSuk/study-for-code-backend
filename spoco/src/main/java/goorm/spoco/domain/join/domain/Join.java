package goorm.spoco.domain.join.domain;

import goorm.spoco.domain.member.domain.Member;
import goorm.spoco.domain.study.domain.Study;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Join {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOIN_ID")
    private Long joinId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "STUDY_ID")
    private Study study;

    //== 연관관계 메서드 ==//
    public void addMember(Member member) {
        this.member = member;
        member.getJoins().add(this);
    }

    public void addStudy(Study study) {
        this.study = study;
        study.getJoins().add(this);
    }

    //== 생성 메서드 ==//
    public static Join join(Member member, Study study) {
        joinValidateDuplicate(member, study);
        Join join = new Join();
        join.addMember(member);
        join.addStudy(study);
        return join;
    }

    //== 중복 검증 메서드 ==//
    private static void joinValidateDuplicate(Member member, Study study) {
       if (member.getJoins().stream().anyMatch(join -> join.getStudy().equals(study))) {
           // "멤버는 이미 해당 스터디에 가입되어 있습니다."
       }
    }
}
