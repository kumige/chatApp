/*
Mikko Romo
1606176

Contains a map of connected users and the number of their sent messages.
*/
object Users {

    val userMap = mutableMapOf<String, Int>()

    //Updates the number of sent messages. Called every time a message is sent
    fun update(username: String, msgNum: Int){
        userMap.replace(username, msgNum)
    }

    //Adds a user to the user map
    fun addUser(username: String): Boolean {
        if (!userMap.containsKey(username)) {
            userMap.put(username,0)
            return true
        }else return false
    }

    //Removes a user from the user map
    fun removeUser(username: String){
        if (userMap.containsKey(username)) {
            userMap.remove(username)
        }else println("username does not exist")
    }

    //returns a string of connected users
    override fun toString(): String {
        var list = "/u"
        for (s in userMap){
            list += "$s "
        }
        return list
    }
}




