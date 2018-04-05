package dk.lejengnaver

import grails.gorm.transactions.Rollback
import grails.gorm.transactions.Transactional
import org.grails.orm.hibernate.HibernateDatastore
import org.springframework.transaction.PlatformTransactionManager
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class PlayerSpec extends Specification {

    @Shared @AutoCleanup HibernateDatastore hibernateDatastore
    @Shared PlatformTransactionManager transactionManager

    void setupSpec() {
        hibernateDatastore = new HibernateDatastore(Player)
        transactionManager = hibernateDatastore.getTransactionManager()
    }

    @Transactional
    def setup() {
        new Player(title: "myTitle", location: "myLocation").save()
    }

    @Rollback
    void "test that a Player has a name and a location"() {
        expect:
        new Player(title: title, location: location).validate() == shouldBeValid

        where:
        title     | location  | shouldBeValid
        "unknown" | "nowhere" | true
        null      | null      | false
    }
}
