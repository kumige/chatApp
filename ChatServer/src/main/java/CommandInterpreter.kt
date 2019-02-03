/*
Mikko Romo
1606176

CommandInterpreter handles the user input. Messages are sent to PrintStream so other users may see them.
Is a ChatHistoryObserver.
*/
import java.io.*
import java.time.LocalDateTime
import java.util.*

class CommandInterpreter(inputStream: InputStream, out: OutputStream) : Runnable, ChatHistoryObserver {

    private val sc = Scanner(inputStream, "UTF-8")
    private val ps = PrintStream(out, true)
    private var msgNum = 0

    override fun run() {
        ps.println("Welcome")
        var running = true
        var username = ""
        var usernameIsSet = false
        ChatHistory.registerObserver(this)

        //This loop listens to user input until the user types ":quit"
        while (running) {
            var message = sc.nextLine()
            if (!(message == null || message.isEmpty())) {
                //Messages will not be sent before the user has set a username.
                //This loop breaks when the user does so.
                while (!usernameIsSet) {
                    if (!(message == null || message.isEmpty())) {
                        when {
                            message.startsWith(":user ") -> {
                                val name = message.split(" ")
                                if (message != ":user") {
                                    username = name[1]
                                    if (Users.addUser(username)) {
                                        ps.println("username set to $username")
                                        println("user added")
                                        usernameIsSet = true
                                    } else {
                                        ps.println("username taken")
                                    }
                                } else ps.println("Please enter a valid username")
                                message = sc.nextLine()

                            }
                            message == ":quit" -> {
                                running = false
                                usernameIsSet = true
                            }
                            else -> {
                                ps.println("Please set a username with :user")
                                message = sc.nextLine()
                            }
                        }
                    } else {
                        ps.println("message was empty or null")
                        message = sc.nextLine()
                    }
                }
                //After the username is set, commands will be checked
                //If the message is not a command, it is a message and will be sent to other users.
                if (!message.isEmpty() && running) {
                    if (message.first() == ':') {
                        if (!message.startsWith(":user")) {
                            when (message) {
                                ":listUsers" -> ps.println(Users.toString())
                                ":history" -> ps.println(ChatHistory.toString())
                                ":quit" -> {
                                    running = false
                                    Users.removeUser(username)
                                }
                                else -> ps.println("invalid command: $message")
                            }
                        } else ps.println("username set already")
                    } else {
                        msgNum++
                        Users.update(username, msgNum)
                        ChatHistory.insert(ChatMessage(message, LocalDateTime.now().toString(), username))
                    }
                }
            } else if (message.isEmpty()) {
                print("")
            } else {
                ps.println("message was null or empty")
                running = false
            }

        }
        ps.println("Goodbye")
        ChatHistory.removeObserver(this)
        sc.close()
    }
    //Prints the message to PrintStream
    override fun newMessage(message: ChatMessage) {
        ps.println(message)
    }


}