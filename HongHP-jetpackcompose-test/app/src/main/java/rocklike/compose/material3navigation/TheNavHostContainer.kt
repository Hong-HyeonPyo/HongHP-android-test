package rocklike.android.material3navigation

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
        composable(ContentsMapper.Favourite.route) {
            FavouriteScreen()
        }
//        composable(ContentsMapper.Notification.route) {
        composable("notification_screen") {
            NotificationScreen()
        }
        composable("cat_screen") {
            CatScreen()
        }
    }
}