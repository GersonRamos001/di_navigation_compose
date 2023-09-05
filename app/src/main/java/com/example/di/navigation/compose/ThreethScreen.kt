package com.example.di.navigation.compose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun ThreethScreen(myViewModel: MyViewModel, onClickNavigateTo:()->Unit) {
    val contadorUiState by myViewModel.contador.collectAsState()


    Column {
        Text("Title")
        Button(onClick = { myViewModel.add() }) {
            Text(text = "Contador")
        }
        Text(text = contadorUiState.toString())

        Button(onClick = {
            Log.d("ThreethScreen DI","OnClick")
            onClickNavigateTo()
        }) {
            Text(text = "To First Screen DI")
        }

    }


}

class ThreethScreen {
}