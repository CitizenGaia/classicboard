package dk.lejengnaver

import grails.rest.Resource

@Resource(uri='/api/users')
class User {

    String userName
    Date createDate = new Date()

    static constraints = {
        userName blank:false
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        builder.append("User")
        builder.append("[")
        builder.append("username:${this.userName}")
        builder.append(",created,${this.createDate}")
        builder.append("]")
        return builder.toString()
    }

    static UserMarshaller = { User domain ->
        return [
                id: domain.id,
                version: domain.version,
                domainName: domain.userName,
                createDate: domain.createDate
        ]
    }

}
