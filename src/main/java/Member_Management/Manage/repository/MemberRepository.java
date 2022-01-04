package Member_Management.Manage.repository;

import Member_Management.Manage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
@ResponseBody
public interface MemberRepository extends JpaRepository<Member, Long> {
}
