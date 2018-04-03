package dk.lejengnaver

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.*
import spock.lang.*

import java.security.SecureRandom

@Integration
@Rollback
class SudokoSpec extends Specification {

    @Shared
    def gameMap = new HashMap()
    @Shared
    def emptyGameMap = new HashMap()
    @Shared
    def random = new SecureRandom()

    def setup() {
        for (int i = 0; i < 3; i++) {
            Integer key = random.nextInt(80) + 1
            Integer value = random.nextInt(9)
            gameMap[key] = value
        }
    }

    void "test that a Sudoko has a name and a game"() {
        expect:
        new Sudoko(title: title, author: author, description: description, content: content).validate() == shouldBeValid

        where:
        title          | author   | description | content      | shouldBeValid
        "unclassified" | "test"   | "none"      | gameMap      | true
        null           | "test"   | "none"      | gameMap      | false
        "unclassified" | null     | "none"      | gameMap      | false
        "unclassified" | "test"   | null        | gameMap      | false
        "-"            | "test"   | "none"      | gameMap      | false
        "unclassified" | "-"      | "none"      | gameMap      | false
        "unclassified" | "test"   | "-"         | gameMap      | true
        "unclassified" | "test"   | "none"      | emptyGameMap | true
        "unclassified" | "test"   | "none"      | null         | false

    }
}
