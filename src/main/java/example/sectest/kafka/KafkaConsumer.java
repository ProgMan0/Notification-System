package example.sectest.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.sectest.domains.BaseUser;
import example.sectest.domains.Event;
import example.sectest.dto.EventCreateDto;
import example.sectest.dto.UserCreateDto;
import example.sectest.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaConsumer {
    private final ObjectMapper objectMapper;
    private final EventService eventService;

    @KafkaListener(topics = "event_topic", groupId = "1")
    public void listen(String message) {
        try {
            EventCreateDto createDto = objectMapper.readValue(message, EventCreateDto.class);

            Event event = Event.builder()
                    .message(createDto.messageTemplate())
                    .creatorId(createDto.creatorId())
                    .build();

            eventService.save(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
