/*
 * Copyright 2022 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.mediapipe.examples.gesturerecognizer

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.mediapipe.examples.gesturerecognizer.databinding.ActivityMainBinding
import com.google.mediapipe.examples.gesturerecognizer.logic.ContextHolder
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        textToSpeech = TextToSpeech(this, this)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        activityMainBinding.navigation.setupWithNavController(navController)
        activityMainBinding.navigation.setOnNavigationItemReselectedListener {
            // ignore the reselection
        }
    }

    override fun onBackPressed() {
        finish()
    }

    fun deletebuttonClick(view: View?) {
        val textLabel = findViewById<TextView>(R.id.textLabel)
        val currentText = textLabel.text.toString()

        if (currentText.isNotEmpty()) {
            val updatedText = currentText.substring(0, currentText.length - 1)
            textLabel.text = updatedText
            ContextHolder.currentWord = updatedText
        }
    }

    fun addSpacebuttonClick(view: View?) {
        val textLabel = findViewById<TextView>(R.id.textLabel)
        textLabel.text = textLabel.text.toString() + " "
        ContextHolder.currentWord = textLabel.text.toString() + " "
    }

    fun readButtonClick(view: View?) {
        val textLabel = findViewById<TextView>(R.id.textLabel)
        val textToRead = textLabel.text.toString()

        if (textToRead.isNotEmpty()) {
            val sentences = textToRead.split(".", "!", "?") // Split text into sentences
            for (sentence in sentences) {
                if (sentence.isNotBlank()) { // Check if sentence is not blank
                    textToSpeech.speak(sentence.trim(), TextToSpeech.QUEUE_ADD, null, null) // Speak each sentence
                }
            }
        } else {
            textToSpeech.speak("Walang teksto na basahin", TextToSpeech.QUEUE_FLUSH, null, null) // Speak "No text to read" in Filipino
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale("fil", "PH"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Initialization failed", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        val textLabel = findViewById<TextView>(R.id.textLabel)
        textLabel.text = "" // Clear the text when the activity is destroyed
        if (textToSpeech != null) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}
