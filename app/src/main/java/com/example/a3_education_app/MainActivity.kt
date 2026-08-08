package com.example.a3_education_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.a3_education_app.ui.theme.A3EducationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            A3EducationAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SpaceEducationApp()
                }
            }
        }
    }
}
