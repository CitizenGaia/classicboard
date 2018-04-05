package dk.lejengnaver

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.hibernate.SessionFactory
import spock.lang.Specification

@Integration
class GameServiceSpec extends Specification {

    GameService gameService
    SessionFactory sessionFactory

    private Long setupData() {
        // Populate valid domain instances and return a valid ID
        5.times {
            def gameMap = new HashMap()
            new Game(title: "aTitle${it}", author: "anAuthor${it}", description: "aDescription${it}", content: gameMap).save(flush: true, failOnError: true)
        }
        Game.first().id
    }

    @Rollback
    void "test get"() {
        setupData()

        expect:
        gameService.get(1) != null
    }

    @Rollback
    void "test list"() {
        setupData()

        when:
        List<Game> gameList = gameService.list(max: 2, offset: 2)

        then:
        gameList.size() == 2
    }

    @Rollback
    void "test count"() {
        setupData()

        expect:
        gameService.count() == 5
    }

    @Rollback
    void "test delete"() {
        Long gameId = setupData()

        expect:
        gameService.count() == 5

        when:
        gameService.delete(gameId)
        sessionFactory.currentSession.flush()

        then:
        gameService.count() == 4
    }

    @Rollback
    void "test save"() {
        when:
        Game game = new Game(title: "aTitle", author: "anAuthor", description: "aDescription", content: new HashMap())
        gameService.save(game)

        then:
        game.id != null
    }
}
