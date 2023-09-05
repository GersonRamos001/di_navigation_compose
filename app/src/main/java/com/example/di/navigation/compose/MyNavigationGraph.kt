package com.example.di.navigation.compose

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation


interface MyDestination {
    val icon: ImageVector
    val route: String
}

object First : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "FirstScreen"
}

object Second : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "SecondScreen"
}

object Threeth : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "ThreethScreen"
}

object MainGraph:MyDestination{
    override val icon = Icons.Filled.PieChart
    override val route = "MainGraph"

}

val myListOfIdDestination= listOf(First,Second,Threeth)

fun NavGraphBuilder.mainGraph(navController: NavHostController) {


        composable(First.route) {

            FirstScreen(
                onClickNavigateTo ={
                    Log.d("mainGraph","FirstScreen onClickNavigateTo mainGraph")
                    //navController.navigate(Second.route)

                    navController.navigateSingleTopTo(Second.route)
                }
            )
        }

        composable(Second.route) {
            SecondScreen {
                Log.d("mainGraph","SecondScreen onClickNavigateTo mainGraph")
                //navController.navigate(First.route)

                navController.navigateSingleTopTo(Threeth.route)
            }
        }

        composable(Threeth.route) { backStackEntry ->
            // Creates a ViewModel from the current BackStackEntry
            // Available in the androidx.hilt:hilt-navigation-compose artifact
            val viewModel = hiltViewModel<MyViewModel>()

            ThreethScreen(myViewModel = viewModel) {
                Log.d("mainGraph","ThreethScreen onClickNavigateTo mainGraph")
                //navController.navigate(First.route)
    
                navController.navigateSingleTopTo(First.route)
            }
        }

}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
