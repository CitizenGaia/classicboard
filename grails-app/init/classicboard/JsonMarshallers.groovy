package classicboard

import dk.lejengnaver.ScoreBoard
import dk.lejengnaver.Sudoko
import dk.lejengnaver.User
import grails.converters.JSON

class JsonMarshallers {

    static init() {
        JSON.registerObjectMarshaller(Date) {
            return it?.format("dd-MM-yyyy")
        }
        JSON.registerObjectMarshaller(User, User.UserMarshaller)
        JSON.registerObjectMarshaller(Sudoko, Sudoko.SudokoMarshaller)
        JSON.registerObjectMarshaller(ScoreBoard, ScoreBoard.ScoreBoardMarshaller)
    }

}
