package goorm.spoco.domain.code.domain;

import goorm.spoco.domain.algorithm.domain.Algorithm;
import goorm.spoco.domain.join.domain.Join;
import goorm.spoco.domain.member.domain.Member;
import goorm.spoco.domain.review.domain.Review;
import goorm.spoco.domain.study.domain.Study;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Data
@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE_ID")
    private Long codeId;

    private String detail;

    @Enumerated(EnumType.STRING)
    @Column(name = "CODE_STATUS")
    private CodeStatus codeStatus = CodeStatus.NONE;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ALGORITHM_ID")
    private Algorithm algorithm;

    @OneToMany(mappedBy = "code", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public Code() {
    }

    //== 연관관계 메서드 ==//
    public void addMember(Member member) {
        this.member = member;
        member.getCodes().add(this);
    }

    public void addAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
        algorithm.getCodes().add(this);
    }

    //== 생성 메서드 ==//
    public static Code code(Member member, Algorithm algorithm, String detail, CodeStatus codeStatus) {
        Code code = new Code();
        code.addMember(member);
        code.addAlgorithm(algorithm);
        code.detail = detail;
        code.codeStatus = codeStatus;
        return code;
    }
}