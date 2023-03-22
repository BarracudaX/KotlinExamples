package structural

import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

/**
 * Decorator patterns allows adding additional behavior to existing objects by decorating/wrapping them.
 */

interface Chat{

    fun send(message: String)

}

class SimpleChat(private val person: String) : Chat{
    override fun send(message: String) {
        println("$person: $message")
    }

}

class BadWordsDecorator(private val badWords: Set<String>,private val chat: Chat) : Chat{

    override fun send(message: String) {
        var endMessage = message

        for(badWord in badWords){
            endMessage = endMessage.replace(badWord,"")
        }

        chat.send(endMessage)
    }

}

class TimeDecorator(private val chat: Chat) : Chat{

    private val durationBetweenMessage = TimeUnit.SECONDS.toNanos(5)

    private var lastTimeSent: Long? = null


    override fun send(message: String) {
        val now = System.nanoTime()
        val timePassedBetweenNowAndLastMessage = now - (lastTimeSent ?: (now - durationBetweenMessage))
        if( (timePassedBetweenNowAndLastMessage) < durationBetweenMessage){
            println("You need to wait ${TimeUnit.NANOSECONDS.toSeconds(durationBetweenMessage - timePassedBetweenNowAndLastMessage)} seconds before sending new message.")
        }else{
            lastTimeSent = System.nanoTime()
            chat.send(message)
        }
    }

}

fun main(){
    val chat = TimeDecorator(BadWordsDecorator(setOf("banana","bad","EZ"),SimpleChat("John")))

    chat.send("Hello,banana!EZ.")
    chat.send("How are you doing?")
}