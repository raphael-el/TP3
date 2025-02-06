package com.example.pourboire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
// import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoeSizeConverterApp()
        }
    }
}

@Composable
fun ShoeSizeConverterApp() {
    var euSize by remember { mutableStateOf("") }
    var usSize by remember { mutableStateOf("") }
    var cmSize by remember { mutableStateOf("") }

    fun convertSizes(input: String, type: String) {
        val size = input.toFloatOrNull() ?: return
        when (type) {
            "EU" -> {
                usSize = (size - 33).toString()
                cmSize = (size / 1.5).toString()
            }
            "US" -> {
                euSize = (size + 33).toString()
                cmSize = ((size + 33) / 1.5).toString()
            }
            "CM" -> {
                euSize = (size * 1.5).toString()
                usSize = (size * 1.5 - 33).toString()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = euSize,
            onValueChange = {
                euSize = it
                convertSizes(it, "EU")
            },
            label = { Text("EU Size") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = usSize,
            onValueChange = {
                usSize = it
                convertSizes(it, "US")
            },
            label = { Text("US Size") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = cmSize,
            onValueChange = {
                cmSize = it
                convertSizes(it, "CM")
            },
            label = { Text("CM Size") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShoeSizeConverterApp()
}