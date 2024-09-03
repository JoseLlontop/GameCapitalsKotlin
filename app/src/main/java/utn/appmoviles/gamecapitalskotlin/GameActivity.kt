package utn.appmoviles.gamecapitalskotlin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import utn.appmoviles.gamecapitalskotlin.model.Routes

@Preview
@Composable
fun MostrarPantallaGame() {

}

@Composable
fun PantallaGame(navigationController: NavHostController) {

    var contador by rememberSaveable {
        mutableStateOf(0)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { contador += 1 }) {
            Text(text = "Pulsar")
        }

        Text(text = "He sido pulsado ${contador} veces")

        Button(onClick = { navigationController.navigate(Routes.PantallaInicio.route) }) {
            Text(text = "Inicio")
        }
    }
}