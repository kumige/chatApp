/*
Mikko Romo
1606176

Formats sent messages into a string with the username and a timestamp.
*/
class ChatMessage(private val message: String, private val timestamp: String, private val userName: String) {

    override fun toString(): String {
        return "$timestamp: $userName: $message"
    }

}
