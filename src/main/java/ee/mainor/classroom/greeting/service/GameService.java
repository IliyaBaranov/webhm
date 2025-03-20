package ee.mainor.classroom.greeting.service;

import ee.mainor.classroom.greeting.dto.GameResponse;
import ee.mainor.classroom.greeting.GameSessionRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {
    private final GameSessionRepository gameSessionRepository;

    public GameService(GameSessionRepository gameSessionRepository) {
        this.gameSessionRepository = gameSessionRepository;
    }

    public GameResponse startGame() {
        UUID gameId = UUID.randomUUID();
        GameSession gameSession = new GameSession(gameId);
        gameSessionRepository.save(gameSession);
        return new GameResponse(gameId, "Game started");
    }

    public GameResponse makeGuess(UUID gameId, int number) {
        Optional<GameSession> gameSessionOpt = gameSessionRepository.findById(gameId);
        if (gameSessionOpt.isEmpty()) {
            return new GameResponse(gameId, "Game not found");
        }
        GameSession gameSession = gameSessionOpt.get();
        String result = gameSession.guess(number);
        gameSessionRepository.save(gameSession);
        return new GameResponse(gameId, result);
    }
}
