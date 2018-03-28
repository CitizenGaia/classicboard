package classicboard

import dk.lejengnaver.ScoreBoard
import dk.lejengnaver.Sudoko
import dk.lejengnaver.User

import java.security.SecureRandom

class BootStrap {

    def init = { servletContext ->
        JsonMarshallers.init()

        User user = createUser().save(flush:true)
        printf("Got ${user}\n")
        Sudoko game = createGame().save(flush:true)
        printf("Got ${game}\n")

        def scoreBoard = new ScoreBoard(ranking: 1,
                user: user,
                game: game,
        ).save(flush:true)

        printf("Got ${scoreBoard}\n")
    }

    User createUser() {
        return new User(userName: "CitizenGaia")
    }

    Sudoko createGame() {
        return new Sudoko(title: "draft", author: "draft", description: "draft", content: createContent())
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
