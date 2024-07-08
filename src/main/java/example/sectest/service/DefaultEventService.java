package example.sectest.service;

import example.sectest.domains.BaseUser;
import example.sectest.domains.Event;
import example.sectest.dto.CheckCreatorDto;
import example.sectest.repo.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class DefaultEventService implements EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    @Transactional
    @Override
    public Long save(Event event) {
        AtomicReference<Long> atomicLong = new AtomicReference<>();

        userService.findByUserId(event.getCreatorId())
                .ifPresentOrElse(obj -> atomicLong.set(eventRepository.save(event).getId()), () -> {
                    BaseUser current = BaseUser.builder()
                            .userId(event.getCreatorId())
                            .cratedAt(Instant.now())
                            .build();

                    userService.save(current);
                    atomicLong.set(eventRepository.save(event).getId());
                });

        return atomicLong.get();
    }

    @Override
    public boolean isExistEvent(Long eventId) {
        return eventRepository.findById(eventId).isPresent();
    }

    @Override
    public boolean isCreator(CheckCreatorDto dto) {
        AtomicReference<Boolean> atomicBoolean = new AtomicReference<>();

        eventRepository.findById(dto.eventId()).ifPresent(event -> {
            if (event.getCreatorId().equals(dto.creatorId())) {
                atomicBoolean.set(true);
                return;
            }

            atomicBoolean.set(false);
        });

        return atomicBoolean.get();
    }
}
