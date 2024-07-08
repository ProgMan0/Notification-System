package example.sectest.controllers;

import example.sectest.domains.Event;
import example.sectest.dto.CheckCreatorDto;
import example.sectest.dto.EventCreateDto;
import example.sectest.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/events")
@RestController
public class EventController {

    private final EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<Long> createEvent(@RequestBody EventCreateDto createDto) {
        Event event = Event.builder()
                .message(createDto.messageTemplate())
                .creatorId(createDto.creatorId())
                .build();

        return ResponseEntity.ok().body(eventService.save(event));
    }

    @PostMapping("/info")
    public ResponseEntity<?> isCreator(@RequestBody CheckCreatorDto dto) {
        return ResponseEntity.ok()
                .body(eventService.isCreator(dto));
    }

}
