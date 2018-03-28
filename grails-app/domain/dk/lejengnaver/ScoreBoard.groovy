package dk.lejengnaver

import grails.rest.Resource

@Resource(uri='/score')
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

}
