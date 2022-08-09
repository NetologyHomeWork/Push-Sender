package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .setDatabaseUrl(DB_URL)
        .build()

    FirebaseApp.initializeApp(options)

    // val message = likeNotification(Actions.POST.action)
    val message = postNotification(Actions.POST.action)

    FirebaseMessaging.getInstance().send(message)
}

private fun likeNotification(action: String): Message {
    return Message.builder()
        .putData("action", action)
        .putData(
            "content",
            """{
            "userId": 1,
            "userName": "Артем Мостяев",
            "postId": 2,
            "postAuthor": "Netology"
            }""".trimIndent()
        )
        .setToken(TOKEN)
        .build()
}

private fun postNotification(action: String): Message {
    return Message.builder()
        .putData("action", action)
        .putData(
            "content",
            """{
            "postId": 3,
            "postAuthor": "Артем Мостяев",
            "postContent": "Таймбоксинг — отличный способ навести порядок в своём календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип — на каждое дело заранее выделяется определённый отрезок времени. В это время вы работаете только над одной задачей, не переключаясь на другие. Собрали советы, которые помогут внедрить таймбоксинг \uD83D\uDC47\uD83C\uDFFB"
            }""".trimIndent()
        )
        .setToken(TOKEN)
        .build()
}

enum class Actions(val action: String) {
    LIKE(action = "LIKE"), POST(action = "POST")
}
