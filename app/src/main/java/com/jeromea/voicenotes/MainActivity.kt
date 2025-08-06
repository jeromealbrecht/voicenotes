package com.jeromea.voicenotes

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.jeromea.voicenotes.ui.theme.VoiceNotesTheme
import androidx.activity.result.contract.ActivityResultContracts
import android.app.AlertDialog
import androidx.activity.compose.rememberLauncherForActivityResult
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import android.util.Log


class MainActivity : ComponentActivity() {

    private lateinit var audioRecorder: AudioRecorder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        audioRecorder = AudioRecorder(this)

        setContent {
            VoiceNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VoiceNoteUI(audioRecorder)
                }
            }
        }
    }
}

@Composable
fun VoiceNoteUI(audioRecorder: AudioRecorder) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    var isRecording by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val micGranted = permissions[Manifest.permission.RECORD_AUDIO] == true
        if (!micGranted) {
            Toast.makeText(context, "Permission micro refusée", Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE // facultatif en Android 10+
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isRecording) "Enregistrement en cours..." else "Appuyez pour enregistrer",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val micGranted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED

                if (!micGranted) {
                    Toast.makeText(context, "Permission micro non accordée", Toast.LENGTH_SHORT).show()
                    permissionLauncher.launch(
                        arrayOf(Manifest.permission.RECORD_AUDIO)
                    )
                    return@Button
                }

                try {
                    if (!isRecording) {
                        audioRecorder.startRecording()
                    } else {
                        audioRecorder.stopRecording()
                    }
                    isRecording = !isRecording
                } catch (e: Exception) {
                    Log.e("VoiceNote", "Erreur enregistrement : ", e)
                }
            }
        ) {
            Text(if (isRecording) "Stop" else "Enregistrer")
        }
    }
}


