package dk.lejengnaver

import grails.compiler.GrailsCompileStatic
import grails.gorm.annotation.Entity

@GrailsCompileStatic
@Entity
class Player implements Serializable {

    private static final long serialVersionUID = 1

    String title
    String location
    Date createDate = new Date()

    static constraints = {
        title size: 6..24, blank: false
        location size: 0..24, blank: false
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        builder.append("Player")
        builder.append("[")
        builder.append("title:${this.title}")
        builder.append(",location:${this.location}")
        builder.append("]")
        return builder.toString()
    }

    static PlayerMarshaller = { Player domain ->
        return [
                id: domain.id,
                version: domain.version,
                title: domain.title,
                location: domain.location,
                createDate: domain.createDate
        ]
    }

}
