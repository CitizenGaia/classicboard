package dk.lejengnaver

import grails.rest.Resource

@Resource(uri='/user')
class User {

    String userName

    static constraints = {
        userName blank:false
    }

}
