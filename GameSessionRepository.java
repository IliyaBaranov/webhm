package ee.mainor.classroom.greeting;

import ee.mainor.classroom.greeting.service.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface GameSessionRepository extends JpaRepository<GameSession, UUID> {
}
