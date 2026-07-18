/**
 * MINGGU 1 — build.gradle.kts (Project Level)
 *
 * File ini mengkonfigurasi build system di level project,
 * bukan individual module. Biasanya hanya berisi plugin declarations.
 */
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
