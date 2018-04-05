package classicboard

import dk.lejengnaver.Game
import dk.lejengnaver.Player

import java.security.SecureRandom

class BootStrap {

    def init = { servletContext ->
        JsonMarshallers.init()

/*
        Player player = createPlayer().save(flush:true)
        printf("Got ${player}\n")
        Game game = createGame().save(flush:true)
        printf("Got ${game}\n")
*/

    }

    Player createPlayer() {
        return new Player(title: "CitizenGaia", location: "Nirvana")
    }

    Game createGame() {
        return new Game(title: "draft", author: "draft", description: "draft", content: createContent())
    }

    Map<String, String> createContent() {
        Map<String, String> gameMap = new HashMap()
        def random = new SecureRandom()
        for (int i = 0; i < 3; i++) {
            String key = String.valueOf(random.nextInt(80) + 1)
            String value = String.valueOf(random.nextInt(9))
            gameMap[key] = value
        }
        return gameMap
    }
}
