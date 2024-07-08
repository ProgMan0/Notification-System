package example.sectest.repo;

import example.sectest.domains.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BaseUser, Long>,
        JpaSpecificationExecutor<BaseUser> {
    Optional<BaseUser> findByUserId(String userId);
}
