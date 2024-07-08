package example.sectest.controllers;

import example.sectest.specifications.UserSpecification;
import example.sectest.domains.BaseUser;
import example.sectest.dto.UserCreateDto;
import example.sectest.service.EventService;
import example.sectest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class BaseUserController {

    private final UserService userService;
    private final EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Long>> createUser(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.ok()
                .body(Map.of(userService.save(userCreateDto).getUserId(), userCreateDto.eventId()));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Set<String>> getAllInEvent(@PathVariable(name = "eventId") Long eventId) {
        if (eventService.isExistEvent(eventId)) {
            Specification<BaseUser> specification = UserSpecification.getAllInEvent(eventId);

            return ResponseEntity.ok()
                    .body(new HashSet<>(
                            userService.getAll(specification).stream()
                                    .map(user -> user.getUserId())
                                    .toList())
                    );
        }

        throw new RuntimeException("user with id %s not exists".formatted(eventId));
    }
}
