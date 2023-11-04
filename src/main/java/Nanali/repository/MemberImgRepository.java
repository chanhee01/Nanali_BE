package Nanali.repository;

import Nanali.domain.Member.Member;
import Nanali.domain.Member.MemberImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberImgRepository extends JpaRepository<MemberImg, Long> {

    MemberImg findByMember(Member member);

    Optional<MemberImg> findById(Long id);
}
