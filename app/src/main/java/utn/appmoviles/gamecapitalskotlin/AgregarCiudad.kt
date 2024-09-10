package utn.appmoviles.gamecapitalskotlin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import utn.appmoviles.gamecapitalskotlin.model.Routes
import utn.appmoviles.gamecapitalskotlin.ui.theme.colorBoton
import utn.appmoviles.gamecapitalskotlin.ui.theme.colorSubTitulo
import utn.appmoviles.gamecapitalskotlin.ui.theme.colorTitulo


@Preview(showBackground = true)
@Composable
fun MostrarPantallaAgregarCiudad() {
    val navigationController = rememberNavController()
    PantallaCapitals(navigationController= navigationController)
}

@Composable
fun PantallaAgregarCiudad(navigationController: NavHostController) {
    Column(Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Capitales del Mundo",
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp), // esto es para el espacio entre las cosas
            fontWeight = FontWeight.Medium, // Esto para hacerlo negrita
            color = colorTitulo
        )
        Text(
            text = "Selecciona una opcion",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 30.dp),
            fontWeight = FontWeight.Light,
            color = colorSubTitulo,
        )
        Button(onClick = {navigationController.navigate(Routes.PantallaInicio.route) },
            colors = ButtonDefaults.buttonColors(containerColor = colorBoton),
            modifier = Modifier
                .width(235.dp)
                .height(45.dp)
        )
        {
            Text(text = "Agregar Ciudad", fontSize = 18.sp)

        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = {navigationController.navigate(Routes.PantallaInicio.route) },
            colors = ButtonDefaults.buttonColors(containerColor = colorBoton),
            modifier = Modifier
                .width(235.dp)
                .height(45.dp)
        )
        {
            Text(text = "Buscar Ciudad",fontSize = 18.sp) // consultar una ciudad por su nombre
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = {navigationController.navigate(Routes.PantallaInicio.route) },
            colors = ButtonDefaults.buttonColors(containerColor = colorBoton),
            modifier = Modifier
                .width(235.dp)
                .height(45.dp)
        )
        {
            Text(text = "Eliminar ciudad",fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { navigationController.navigate(Routes.PantallaInicio.route) },
            colors = ButtonDefaults.buttonColors(containerColor = colorBoton),
            modifier = Modifier
                .width(100.dp)
                .height(45.dp)
        )
        {
            Text(text = "Salir",fontSize = 18.sp)
        }
    }
}





