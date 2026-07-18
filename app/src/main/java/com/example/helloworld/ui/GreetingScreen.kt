package com.example.helloworld.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.ui.theme.HelloWorldTheme

/**
 * MINGGU 1 — Pemrograman Mobile Android
 *
 * GreetingScreen dimodifikasi menjadi Form Sederhana sesuai Tugas Minggu 1.
 *
 * Konsep yang dipraktikkan:
 * 1. TextField untuk Nama & NIM/NIP
 * 2. Validasi input (NIM numeric, Nama tidak boleh kosong)
 * 3. State management untuk multiple inputs
 * 4. KeyboardOptions untuk input tipe angka
 */
@Composable
fun GreetingScreen() {

    // ==========================================
    // STATE MANAGEMENT
    // ==========================================
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }
    var showErrors by remember { mutableStateOf(false) }

    // Validasi Dasar
    val isNameValid = name.isNotBlank()
    val isNimValid = nim.isNotBlank() && nim.all { it.isDigit() }
    val canSubmit = isNameValid && isNimValid

    // ==========================================
    // UI LAYOUT
    // ==========================================
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // ── Icon Header ──
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Assignment,
            contentDescription = "Form Icon",
            modifier = Modifier.size(72.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ── Judul ──
        Text(
            text = "Tugas Minggu 1",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Formulir Data Mahasiswa",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
        )

        // ── Card Input ──
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Lengkapi Data",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // TextField Nama Lengkap
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        isSubmitted = false // Reset status submit jika data berubah
                        if (it.isNotBlank()) showErrors = false
                    },
                    label = { Text("Nama Lengkap") },
                    placeholder = { Text("Masukkan nama lengkap") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    isError = showErrors && !isNameValid,
                    supportingText = {
                        if (showErrors && !isNameValid) {
                            Text("Nama tidak boleh kosong", color = MaterialTheme.colorScheme.error)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // TextField NIM/NIP
                OutlinedTextField(
                    value = nim,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }) {
                            nim = newValue
                            isSubmitted = false
                            if (newValue.isNotBlank()) showErrors = false
                        }
                    },
                    label = { Text("NIM/NIP") },
                    placeholder = { Text("Masukkan NIM/NIP (Angka)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    isError = showErrors && !isNimValid,
                    supportingText = {
                        if (showErrors && nim.isBlank()) {
                            Text("NIM tidak boleh kosong", color = MaterialTheme.colorScheme.error)
                        } else if (nim.isNotBlank() && !isNimValid) {
                            // Ini sebenarnya sudah terhandle oleh if(newValue.all { it.isDigit() }) di onValueChange,
                            // tapi baik untuk jaga-jaga jika logika validasi berubah.
                            Text("Harus berupa angka", color = MaterialTheme.colorScheme.error)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Button Submit
                Button(
                    onClick = {
                        if (canSubmit) {
                            isSubmitted = true
                            showErrors = false
                        } else {
                            showErrors = true
                            // Tampilkan pemberitahuan jika data belum valid
                            val message = when {
                                name.isBlank() -> "Nama tidak boleh kosong!"
                                nim.isBlank() -> "NIM/NIP tidak boleh kosong!"
                                !nim.all { it.isDigit() } -> "NIM/NIP harus berupa angka!"
                                else -> "Mohon lengkapi data dengan benar!"
                            }
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Submit Data",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ── Result Display ──
        AnimatedVisibility(
            visible = isSubmitted,
            enter = fadeIn() + slideInVertically()
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Data Berhasil Disubmit:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Nama: $name",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = "NIM/NIP: $nim",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Greeting Screen Preview")
@Composable
fun GreetingScreenPreview() {
    HelloWorldTheme {
        GreetingScreen()
    }
}
