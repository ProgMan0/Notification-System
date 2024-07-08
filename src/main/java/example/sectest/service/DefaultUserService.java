package example.sectest.service;

import example.sectest.domains.BaseUser;
import example.sectest.domains.Event;
import example.sectest.dto.UserCreateDto;
import example.sectest.repo.EventRepository;
import example.sectest.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public BaseUser save(UserCreateDto userDto) {
        Event event = eventRepository.findById(userDto.eventId()).orElse(null);

        if (event == null) {
            throw new RuntimeException("event does not exists!");
        }

        if (userRepository.findByUserId(userDto.userId()).isEmpty()) {
            BaseUser user = BaseUser.builder()
                    .userId(userDto.userId())
                    .event(event)
                    .cratedAt(Instant.now())
                    .build();

            return userRepository.save(user);
        }

        throw new RuntimeException("User %s already exists!".formatted(userDto.userId()));
    }

    @Override
    public BaseUser save(BaseUser baseUser) {
        return userRepository.save(baseUser);
    }

    @Override
    public Set<BaseUser> getAll(Specification<BaseUser> specification) {
        return new HashSet<>(userRepository.findAll(specification));
    }

    @Override
    public Optional<BaseUser> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
