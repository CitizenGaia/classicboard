package classicboard

import dk.lejengnaver.Game
import dk.lejengnaver.Player
import grails.converters.JSON

class JsonMarshallers {

    static init() {
        JSON.registerObjectMarshaller(Date) {
            return it?.format("dd-MM-yyyy")
        }
        JSON.registerObjectMarshaller(Player, Player.PlayerMarshaller)
        JSON.registerObjectMarshaller(Game, Game.GameMarshaller)
    }

}
