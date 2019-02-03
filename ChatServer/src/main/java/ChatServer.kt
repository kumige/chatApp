/*
Mikko Romo
1606176

Creates a ServerSocket and listens to incoming connections in an infinite loop.
Every user will have their own thread.
*/
import java.net.ServerSocket

class ChatServer {

    fun serve() {
        try {
            while (true) {
                ChatConsole().registerObserver()
                TopChatter().registerObserver()

                val ss = ServerSocket(30001)
                println("We have port " + ss.localPort)
                while (true) {
                    val s = ss.accept()
                    val t = Thread(CommandInterpreter(s.getInputStream(), s.getOutputStream()))
                    t.start()
                }
            }
        } catch (e: Exception) {
            println("Got exception: ${e.message}")
        } finally {
            println("All serving done.")
        }
    }
}