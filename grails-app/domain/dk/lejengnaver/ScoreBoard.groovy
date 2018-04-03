package dk.lejengnaver

import grails.rest.Resource

import java.text.SimpleDateFormat

@Resource(uri='/api/scores')
class ScoreBoard {

    BigInteger ranking
    Sudoko game
    User user
    Date createDate =  new Date()

    static constraints = {
        ranking nullable: false
        game nullable: false
        user nullable: false
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        builder.append("ScoreBoard")
        builder.append("[")
        builder.append("Ranking:${this.ranking}")
        builder.append(",${this.game.toString()}")
        builder.append(",${this.user.toString()}")
        builder.append("]")
        return builder.toString()
    }

    static ScoreBoardMarshaller = { ScoreBoard domain ->
        return [
                id: domain.id,
                version: domain.version,
                ranking: domain.ranking,
                game: domain.game,
                user: domain.user,
                cretedate: domain.createDate?.format("dd.MM.yyyy")
        ]
    }
}
