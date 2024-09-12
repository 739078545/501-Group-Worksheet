package com.example.celsiustofahrenheitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatureConverter()
        }
    }
}

@Composable
fun TemperatureConverter() {
    var celsius by remember { mutableStateOf(0f) }
    var fahrenheit by remember { mutableStateOf(32f) }
    var message by remember { mutableStateOf("I wish it were warmer.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Celsius: ${celsius.roundToInt()}ºC")
        Slider(
            value = celsius,
            onValueChange = {
                celsius = it
                fahrenheit = celsiusToFahrenheit(celsius)
                message = updateMessage(celsius)
            },
            valueRange = 0f..100f,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(text = "Fahrenheit: ${fahrenheit.roundToInt()}ºF")
        Slider(
            value = fahrenheit,
            onValueChange = {
                fahrenheit = if (it < 32f) 32f else it
                celsius = fahrenheitToCelsius(fahrenheit)
                message = updateMessage(celsius)
            },
            valueRange = 0f..212f,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(text = message)
    }
}

fun celsiusToFahrenheit(celsius: Float): Float {
    return (celsius * 9 / 5) + 32
}

fun fahrenheitToCelsius(fahrenheit: Float): Float {
    return (fahrenheit - 32) * 5 / 9
}

fun updateMessage(celsius: Float): String {
    return if (celsius <= 20.99) {
        "I wish it were warmer."
    } else {
        "I wish it were colder."
    }
}


