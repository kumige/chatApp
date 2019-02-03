/*
Mikko Romo
1606176

Prints messages to the console. Is a ChatHistoryObserver.
*/
class ChatConsole: ChatHistoryObserver{

    //print the message to console
    override fun newMessage(message: ChatMessage) {
        println(message)
    }

    //add ChatConsole to observer list
    fun registerObserver(){
        ChatHistory.registerObserver(this)
    }
}