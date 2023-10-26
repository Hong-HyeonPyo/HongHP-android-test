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
            FavouriteScreen()
        }
//        composable(ContentsMapper.Notification.route) {
        composable("notification_screen") {
            rPtksrl()
        }
        composable("cat_screen") {
            CatScreen()
        }
        composable("toDoList") {
            ToDoList()
        }
        composable("theGame") {
            TheGame(navController)
        }
    }
}