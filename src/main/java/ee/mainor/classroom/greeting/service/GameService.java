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
=======
package ee.mainor.classroom.greeting.service;

import ee.mainor.classroom.greeting.dto.GameResponse;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GameService {
    private final Map<UUID, GameSession> games = new HashMap<>();

    public GameResponse startGame() {
        UUID gameId = UUID.randomUUID();
        games.put(gameId, new GameSession(gameId));
        return new GameResponse(gameId, "Game started");
    }

    public GameResponse makeGuess(UUID gameId, int number) {
        GameSession game = games.get(gameId);
        if (game == null) {
            return new GameResponse(gameId, "Game not found");
        }
        return new GameResponse(gameId, game.guess(number));
    }
}
