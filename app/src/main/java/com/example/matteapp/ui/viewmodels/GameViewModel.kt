package com.example.matteapp.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.matteapp.R
import kotlin.random.Random

class GameViewModel(application: Application) : AndroidViewModel(application)  {

    // Lokal privat variabel for å opprettholde Single source of thruth
    private val _currentMattestykke = mutableStateOf("")
    val currentMattestykke: State<String> = _currentMattestykke

    private val _currentSvar = mutableStateOf("")
    val currentSvar: State<String> = _currentSvar

    private val _inputAnswer = mutableStateOf("")
    val inputAnswer: State<String> = _inputAnswer

    // Holding all used regnestykker
    private var usedRegnestykker: MutableSet<String> = mutableSetOf()

    // Teller antall riktig besvarte regnestykker


    fun drawNewMattestykke() {
        // String arrays med oppgaver og svar ( Oppgave og medfølgende svar har lik indeks på tvers av arrayene)
        val newOppgave: Array<String> = getApplication<Application>().resources.getStringArray(R.array.oppgaver)
        val newSvar: Array<String> = getApplication<Application>().resources.getStringArray(R.array.svar)


        // Hente random array
        var resourceId = Random.nextInt(1,15) //TODO 15 byttes ut med SharedPrefrence

        while(usedRegnestykker.contains(newOppgave[resourceId])){
            resourceId = Random.nextInt(1,15) //TODO 15 byttes ut med SharedPrefrence
        }
        _currentMattestykke.value = newOppgave[resourceId]
        _currentSvar.value = newSvar[resourceId]
        usedRegnestykker.add(newOppgave[resourceId])

    }
    fun onInputNumberFromPadChange(newValue: String) {
        // Ingen svar over 4 siffer
        if(_inputAnswer.value.length < 4) {
            Log.d("GameViewModel", "Number: $newValue")
            _inputAnswer.value += newValue
        }
    }

    fun onSubmitAnswer() {
        if(_currentSvar.value == _inputAnswer.value) {
            Log.d("GameViewModel", "Riktig svar! ${_currentSvar.value} == ${_inputAnswer.value}")
            _inputAnswer.value = ""
            drawNewMattestykke()
        }
        else {
            Log.d("GameViewModel", "Feil svar ${_currentSvar.value} != ${_inputAnswer.value}")
        }
    }

    fun onEraseAnswer() {
        Log.d("GameViewModel","_inputAnswer.value = ${_inputAnswer.value}")
            if(_inputAnswer.value.length == 1) {
                _inputAnswer.value = ""

        } else if(_inputAnswer.value.length > 1) {
                val charSeq = _inputAnswer.value.toCharArray(0, _inputAnswer.value.length-1)
                _inputAnswer.value = String(charSeq)

                Log.d("GameViewModel","_inputAnswer.value etter slett = ${_inputAnswer.value}")
            }
    }

    fun resetGame() {
        drawNewMattestykke()
    }

    init {
        resetGame()
    }
}