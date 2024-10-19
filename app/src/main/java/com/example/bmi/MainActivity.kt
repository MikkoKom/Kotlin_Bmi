package com.example.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.bmi.ui.theme.BmiTheme
import com.example.bmi.viewmodel.BmiViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = ViewModelProvider(this).get(BmiViewModel::class.java)
            BmiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BmiScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun BmiScreen(viewModel: BmiViewModel) {
    // Collect the state from the ViewModel using collectAsState()
    val heightState = viewModel.height.collectAsState()
    val weightState = viewModel.weight.collectAsState()
    val bmiState = viewModel.bmi.collectAsState()

    // Access the values explicitly
    val height = heightState.value
    val weight = weightState.value
    val bmi = bmiState.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Display the height input
        OutlinedTextField(
            value = if (height == 0.0f) "" else height.toString(),
            onValueChange = { viewModel.updateHeight(it) },
            label = { Text(text = "Height (m)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Display the weight input
        OutlinedTextField(
            value = if (weight == 0.0f) "" else weight.toString(),
            onValueChange = { viewModel.updateWeight(it) },
            label = { Text(text = "Weight (kg)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Display the calculated BMI
        Text(
            text = "Body mass index is ${String.format("%.2f", bmi)}", // Removed Locale.US
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BmiTheme {
        // Using mock data for preview purposes
        BmiScreenPreview()
    }
}

// This function provides mock state for the Preview composable
@Composable
fun BmiScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = "1.75", // Mock height
            onValueChange = {},
            label = { Text(text = "Height (m)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = "70", // Mock weight
            onValueChange = {},
            label = { Text(text = "Weight (kg)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Text(
            text = "Body mass index is 22.86", // Mock BMI
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )
    }
}
