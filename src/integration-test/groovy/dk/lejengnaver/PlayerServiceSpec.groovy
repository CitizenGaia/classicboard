package dk.lejengnaver

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.hibernate.SessionFactory
import spock.lang.Specification

@Integration
class PlayerServiceSpec extends Specification {

    PlayerService playerService
    SessionFactory sessionFactory

    private Long setupData() {
        // Populate valid domain instances and return a valid ID
        5.times {
            new Player(title: "aTitle${it}", location: "aLocation${it}").save(flush: true, failOnError: true)
        }
        Player.first().id
    }

    @Rollback
    void "test get"() {
        setupData()

        expect:
        playerService.get(1) != null
    }

    @Rollback
    void "test list"() {
        setupData()

        when:
        List<Player> playerList = playerService.list(max: 2, offset: 2)

        then:
        playerList.size() == 2
    }

    @Rollback
    void "test count"() {
        setupData()

        expect:
        playerService.count() == 5
    }

    @Rollback
    void "test delete"() {
        Long playerId = setupData()

        expect:
        playerService.count() == 5

        when:
        playerService.delete(playerId)
        sessionFactory.currentSession.flush()

        then:
        playerService.count() == 4
    }

    @Rollback
    void "test save"() {
        when:
        Player player = new Player(title: 'CitizenGaia', location: 'Gaia')
        playerService.save(player)

        then:
        player.id != null
    }
}
