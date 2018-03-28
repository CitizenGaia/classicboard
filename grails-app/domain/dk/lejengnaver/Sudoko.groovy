package dk.lejengnaver

import grails.rest.Resource

@Resource(uri='/game')
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

}
