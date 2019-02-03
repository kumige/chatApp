/*
Mikko Romo
1606176

Keeps a list of all messages and observers.
*/
object ChatHistory: ChatHistoryObservable {

    val messageList = mutableSetOf<ChatMessage>()
    private val observerList = mutableSetOf<ChatHistoryObserver>()

    //Called when a user sends a message. Adds the message to messageList.
    fun insert(message: ChatMessage){
        messageList.add(message)
        notifyObservers(message)
    }

    //Returns a string with every message sent to the server
    override fun toString(): String{
        var list = "/h"
        for (s in messageList){
            list += "$s|"
        }
        return list
    }

    //Adds an observer to the list of observers
    override fun registerObserver(observer:ChatHistoryObserver){
        observerList.add(observer)
        println("observer added")
    }
    //Removes a user from the list of observers
    override fun removeObserver(observer:ChatHistoryObserver){
        observerList.remove(observer)
    }
    //Calls newMessage() in every ChatHistoryObserver class.
    override fun notifyObservers (message:ChatMessage){
        for (users in observerList){
            users.newMessage(message)
        }

    }


}