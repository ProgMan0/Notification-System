package example.sectest.service;

import example.sectest.domains.BaseUser;
import example.sectest.dto.UserCreateDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    BaseUser save(UserCreateDto userDto);

    BaseUser save(BaseUser baseUser);

    Set<BaseUser> getAll(Specification<BaseUser> specification);

    Optional<BaseUser> findByUserId(String userId);
}
