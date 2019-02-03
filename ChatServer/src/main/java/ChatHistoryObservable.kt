/*
Mikko Romo
1606176

Contains functions for classes that implement ChatHistoryObservable.
*/
interface ChatHistoryObservable{

    fun registerObserver(observer:ChatHistoryObserver){

    }
    fun removeObserver(observer:ChatHistoryObserver){

    }
    fun notifyObservers (message:ChatMessage){

    }
}