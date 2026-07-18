package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworld.ui.theme.HelloWorldTheme
import com.example.helloworld.ui.GreetingScreen

/**
 * MINGGU 1 — Pemrograman Mobile Android
 * Entry point aplikasi Hello World
 *
 * ComponentActivity adalah base class modern untuk Activity di Android.
 * Mendukung Jetpack Compose melalui setContent { }
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // enableEdgeToEdge() membuat app tampil full-screen (tanpa status bar padding)
        enableEdgeToEdge()

        // setContent { } menggantikan setContentView(R.layout.activity_main)
        // Ini adalah entry point Jetpack Compose
        setContent {
            HelloWorldTheme {
                // Surface adalah container dasar dengan background dari MaterialTheme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil Composable utama kita
                    GreetingScreen()
                }
            }
        }
    }
}

// Preview bisa dilihat langsung di Android Studio tanpa menjalankan emulator
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    HelloWorldTheme {
        GreetingScreen()
    }
}
