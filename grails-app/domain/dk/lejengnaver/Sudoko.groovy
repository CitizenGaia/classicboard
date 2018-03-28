package dk.lejengnaver

import grails.rest.Resource

@Resource(uri='/api/game')
class Sudoko {

    String title
    String author
    String description
    Map<String, String> content
    Date createDate =  new Date()

    static constraints = {
        title size: 5..15, blank: false
        author size: 3..15, blank: false
        description size: 1..200, blank: false
        content nullable: false
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        builder.append("Sudoko")
        builder.append("[")
        builder.append("title:${this.title}")
        builder.append(",author,${this.author}")
        builder.append(",description,${this.description}")
        builder.append(",cubes,${this.content.size()}")
        builder.append("]")
        return builder.toString()
    }

    static SudokoMarshaller = { Sudoko domain ->
        return [
                id: domain.id,
                version: domain.version,
                title: domain.title,
                auhor: domain.author,
                description: domain.description,
                cubes: domain.content?.size(),
                cretedate: domain.createDate
        ]
    }

}
