/*
Mikko Romo
1606176

Keeps track of top three users with the highest message count.
*/
class TopChatter: ChatHistoryObserver{

    var oldTop = listOf<Pair<String, Int>>()
    var topChanged = false

    override fun newMessage(message: ChatMessage){
        //sorts user list by message count and adds the first three users
        val topChatters = Users.userMap.toList().sortedBy { -it.second }.take(3)

        //checks if the top three list has changed
        if(oldTop == topChatters){
            topChanged = false
        }else {
            oldTop = topChatters
            topChanged = true
        }

        //prints the list of top chatters
        if (topChanged) {
            var list = ""
            for (s in topChatters) {
                list += "${s.first}: ${s.second} messages\n "
            }
            println("TOP CHATTERS:\n $list")
        }

    }
    //add TopChatter to observer list
    fun registerObserver(){
        ChatHistory.registerObserver(this)
    }

}
