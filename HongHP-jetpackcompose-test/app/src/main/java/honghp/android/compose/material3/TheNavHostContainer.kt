package honghp.android.compose.material3

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostContainer(navController: NavHostController) {
    NavHost(navController, startDestination = ContentsMapper.HomeScreen.route) {
        composable(ContentsMapper.HomeScreen.route) {
            HomeScreen()
        }
        composable(ContentsMapper.Weather.route) {
            WeatherScreen()
        }
//        composable(ContentsMapper.Notification.route) {
        composable("notification_screen") {
            CalculaterScreen()
        }
        composable("cat_screen") {
            CatScreen()
        }
        composable("toDoList") {
            ToDoList()
        }
        composable("theGame") {
            GameScreen()
        }
        composable("TstScrn2") {
            TstScrn2()
        }
        composable("TstScrn") {
            TstScrean()
        }

    }
}