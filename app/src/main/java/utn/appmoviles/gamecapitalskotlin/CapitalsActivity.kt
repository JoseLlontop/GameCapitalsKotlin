package utn.appmoviles.gamecapitalskotlin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import utn.appmoviles.gamecapitalskotlin.model.Routes

@Preview
@Composable
fun MostrarPantallaCapitals() {

}

@Composable
fun PantallaCapitals(navigationController: NavHostController) {
    Column(Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navigationController.navigate(Routes.PantallaInicio.route) }) {
            Text(text = "Inicio")
        }
    }
}