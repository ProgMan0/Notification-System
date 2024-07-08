package example.sectest.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Getter
@Setter
@Table(name = "test_users")
public class BaseUser extends User {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
