package utn.appmoviles.gamecapitalskotlin.model

sealed class Routes(val route: String) {
    object PantallaInicio:Routes("pantallaInicio")
    object PantallaGame:Routes("pantallaGame")
    object PantallaCapitals:Routes("pantallaCapitals")
}