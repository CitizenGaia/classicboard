package dk.lejengnaver

import grails.compiler.GrailsCompileStatic
import grails.gorm.annotation.Entity

@GrailsCompileStatic
@Entity
class Game implements Serializable {

    private static final long serialVersionUID = 1

    String title
    String author
    String description
    Map<String, String> content
    Date createDate = new Date()

    static constraints = {
        title size: 5..15, blank: false
        author size: 3..15, blank: false
        description size: 1..200, blank: false
        content nullable: false
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        builder.append("Game")
        builder.append("[")
        builder.append("title:${this.title}")
        builder.append(",author,${this.author}")
        builder.append(",description,${this.description}")
        builder.append(",cubes,${this.content?.size()}")
        builder.append("]")
        return builder.toString()
    }

    static GameMarshaller = { Game domain ->
        return [
                id: domain.id,
                version: domain.version,
                title: domain.title,
                auhor: domain.author,
                description: domain.description,
                cubes: (domain.content!=null ? domain.content.size() : "0"),
                createdate: domain.createDate
        ]
    }

}
