package com.example.bmi.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BmiViewModel : ViewModel() {

    // Private MutableStateFlows to store height, weight, and bmi
    private val _height = MutableStateFlow(0.0f)
    private val _weight = MutableStateFlow(0.0f)
    private val _bmi = MutableStateFlow(0.0f)

    // Public StateFlows to expose values to the UI
    val height: StateFlow<Float> = _height
    val weight: StateFlow<Float> = _weight
    val bmi: StateFlow<Float> = _bmi

    // Method to update height
    fun updateHeight(newHeight: String) {
        _height.value = try {
            newHeight.toFloat()
        } catch (e: NumberFormatException) {
            0.0f
        }
        calculateBmi()
    }

    // Method to update weight
    fun updateWeight(newWeight: String) {
        _weight.value = try {
            newWeight.toFloat()
        } catch (e: NumberFormatException) {
            0.0f
        }
        calculateBmi()
    }

    // Private method to calculate BMI based on height and weight
    private fun calculateBmi() {
        _bmi.value = if (_height.value > 0.0f && _weight.value > 0.0f) {
            _weight.value / (_height.value * _height.value)
        } else {
            0.0f
        }
    }
}
