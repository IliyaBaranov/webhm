package ee.mainor.classroom.greeting.service;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class GameSession {
    @Id
    private UUID gameId;
    private int targetNumber;
    private int attempts;

    public GameSession() {
        // Required for JPA
    }

    public GameSession(UUID gameId) {
        this.gameId = gameId;
        this.targetNumber = (int) (Math.random() * 100) + 1;
        this.attempts = 0;
    }

    public UUID getGameId() {
        return gameId;
    }

    public int getAttempts() {
        return attempts;
    }

    public String guess(int number) {
        attempts++;
        if (number < targetNumber) {
            return "Nr is bigger";
        } else if (number > targetNumber) {
            return "Nr is smaller";
        } else {
            return "Correct, it took you " + attempts + " times";
        }
    }
}
