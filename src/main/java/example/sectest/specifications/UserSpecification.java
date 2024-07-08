package example.sectest.specifications;

import example.sectest.domains.BaseUser;
import example.sectest.domains.Event;
import org.springframework.data.jpa.domain.Specification;

public interface UserSpecification {

    static Specification<BaseUser> getAllInEvent(Long eventId) {
        return (root, query, builder) -> {
            if (eventId == null) {
                return null;
            }

            return builder.equal(root.get("event").get("id"), eventId);
        };
    }
}
