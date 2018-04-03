package dk.lejengnaver

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

abstract class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }
}
