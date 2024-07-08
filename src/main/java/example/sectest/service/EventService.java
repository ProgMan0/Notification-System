package example.sectest.service;

import example.sectest.domains.Event;
import example.sectest.dto.CheckCreatorDto;

public interface EventService {
    Long save(Event event);

    boolean isExistEvent(Long eventId);

    boolean isCreator(CheckCreatorDto dto);
}
