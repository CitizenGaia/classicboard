package classicboard

import dk.lejengnaver.ScoreBoard
import dk.lejengnaver.Sudoko

class BootStrap {

    def init = { servletContext ->
        def score = new ScoreBoard(rank: 1)
                .save(flush:true)
    }

}
