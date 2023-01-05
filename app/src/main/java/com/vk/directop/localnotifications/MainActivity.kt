package com.vk.directop.localnotifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vk.directop.localnotifications.ui.theme.LocalNotificationsTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val service = CounterNotificationService(applicationContext)

        var res1 = ""
        runBlocking {
            val result1 = async { networkCall1() }
            val result2 = async { networkCall2() }

            res1 = "${result1.await()}  ${result2.await()}"
        }
        setContent {
            LocalNotificationsTheme {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,

                    ) {
                    Button(onClick = {
                        service.showNotification(Counter.value)
                    }) {
                        Text(text = "Show notification")
                    }
                    Greeting("Android Notification Service $res1")
                }


            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LocalNotificationsTheme {
        Greeting("Android")
    }
}

suspend fun networkCall1(): Int {
    delay(1000L)
    return 0
}

suspend fun networkCall2(): Int {
    delay(1000L)
    return 1
}