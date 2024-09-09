package utn.appmoviles.gamecapitalskotlin

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import utn.appmoviles.gamecapitalskotlin.model.Routes
import utn.appmoviles.gamecapitalskotlin.ui.theme.colorBoton
import utn.appmoviles.gamecapitalskotlin.ui.theme.colorTitulo
import kotlin.random.Random


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MostrarPantallaGame() {
    PantallaGame(null)
}

// Crear un DataStore
val Context.dataStore by preferencesDataStore(name = "game_prefs")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaGame(navigationController: NavHostController?) {

    val context = LocalContext.current
    val mejorPuntajeKey = intPreferencesKey("mejorPuntaje")

    // Variables necesarias para la logica
    var numeroSecreto by rememberSaveable { mutableStateOf(Random.nextInt(1, 6)) }
    var intentosFallidos by rememberSaveable { mutableStateOf(0) }
    var puntaje by rememberSaveable { mutableStateOf(0) }
    var mejorPuntaje by rememberSaveable { mutableStateOf(0) }
    var input by remember { mutableStateOf(TextFieldValue("")) }

    // Corutina para manejar la lectura y escritura de DataStore
    val scope = rememberCoroutineScope()

    // Leer el mejor puntaje de DataStore
    LaunchedEffect(Unit) {
        context.dataStore.data.collect { preferences ->
            mejorPuntaje = preferences[mejorPuntajeKey] ?: 0
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PuntajeTitulo(puntaje, mejorPuntaje)

        Spacer(modifier = Modifier.height(38.dp))

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            maxLines = 1,
            label = {
                Text(
                    text = "Adivina el número entre 1 y 5",
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }, colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF8338ec),
                unfocusedBorderColor = Color(0xFF3a86ff),
          )
        )

        Spacer(modifier = Modifier.height(64.dp))


        Row(
            horizontalArrangement = Arrangement.spacedBy(38.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = { navigationController?.navigate(Routes.PantallaInicio.route) },
                colors = ButtonDefaults.buttonColors(containerColor = colorBoton),
                modifier = Modifier
                    .width(130.dp)
                    .height(45.dp)
            ) {
                Text(text = "Salir", fontSize = 18.sp)
            }

            Button(
                onClick = {
                    val numeroIngresado = input.text.toIntOrNull()

                    if (numeroIngresado != null) {
                        if (numeroIngresado == numeroSecreto) {
                            puntaje += 10
                            numeroSecreto = Random.nextInt(1, 6) // Nuevo número aleatorio
                            intentosFallidos = 0
                            Toast.makeText(context, "¡Adivinaste!", Toast.LENGTH_SHORT).show()
                        } else {
                            intentosFallidos += 1
                            Toast.makeText(context, "Fallaste, intenta de nuevo.", Toast.LENGTH_SHORT).show()

                            if (intentosFallidos >= 5) {
                                puntaje = 0
                                intentosFallidos = 0
                                Toast.makeText(context, "Perdiste. El puntaje se ha reiniciado.", Toast.LENGTH_SHORT).show()
                            }
                        }

                        // Si el puntaje actual es mayor al mejor puntaje, guardarlo en DataStore
                        if (puntaje > mejorPuntaje) {
                            mejorPuntaje = puntaje
                            scope.launch {
                                context.dataStore.edit { preferences ->
                                    preferences[mejorPuntajeKey] = mejorPuntaje
                                }
                            }
                        }
                    } else {
                        Toast.makeText(context, "Por favor, ingresa un número válido.", Toast.LENGTH_SHORT).show()
                    }

                }, colors = ButtonDefaults.buttonColors(containerColor = colorBoton),
                modifier = Modifier
                    .width(130.dp)
                    .height(45.dp)
            ) {
                Text(text = "Intentar", fontSize = 18.sp)
            }

        }

    }
}

@Composable
fun PuntajeTitulo(puntaje: Int, mejorPuntaje: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Puntaje actual: $puntaje",
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp),
            fontWeight = FontWeight.Bold,
            color = colorTitulo
        )

        Text(
            text = "Mejor puntaje: $mejorPuntaje",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 30.dp),
            fontWeight = FontWeight.Bold,
            color = colorTitulo
        )

    }
}