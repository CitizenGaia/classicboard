package dk.lejengnaver

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import spock.lang.Shared
import spock.lang.Specification

import java.security.SecureRandom

@Integration
@Rollback
class ScoreBoardSpec extends Specification {

    @Shared
    def testSudoko
    @Shared
    def testUser

    def setup() {
        createGame().save()
        testSudoko = Sudoko.first()
        createUser().save()
        testUser = User.first()
    }

    void "check that ranking are required"() {
        expect:
        new ScoreBoard(ranking: ranking, game: testSudoko, user: testUser).validate() == shouldBeValid

        where:
        ranking | shouldBeValid
        "1"     | true
        null    | false
    }

    void "check that game object is required"() {
        expect:
        new ScoreBoard(ranking: ranking, game: null, user: testUser).validate() == shouldBeValid

        where:
        ranking | shouldBeValid
        "1"     | false
        null    | false
    }

    void "check that user object is required"() {
        expect:
        new ScoreBoard(ranking: ranking, game: testSudoko, user: null).validate() == shouldBeValid

        where:
        ranking | shouldBeValid
        "1"     | false
        null    | false
    }


    def User createUser() {
        return new User(userName: "a user name")
    }

    def Sudoko createGame() {
        def gameMap = new HashMap()
        def random = new SecureRandom()
        for (int i = 0; i < 3; i++) {
            String key = String.valueOf(random.nextInt(80) + 1)
            String value = String.valueOf(random.nextInt(9))
            gameMap[key] = value
        }
        return new Sudoko(title: "a title", author: "an author", description: "-", content: gameMap)
    }
}
