import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dsw52839.utils.Routes
import com.example.dsw52839.utils.StoreDataManager
import com.example.dsw52839.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = HomeViewModel(),
    dataStoreManager: StoreDataManager,
    ) {

    val notes = viewModel.notesList
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primary
                    )
                    .padding(
                        top = 40.dp,
                        bottom = 20.dp,
                    )
                    .padding(
                        horizontal = 16.dp,
                    ),
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            scope.launch {
                                dataStoreManager.saveData(isLogged = false)
                            }
                            navController.popBackStack()
                        }
                    ,
                    contentDescription = null,
                    tint = Color.White,
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = "Notes App",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.changeChosenID(-1)
                    navController.navigate(Routes.notePage)
                },
                containerColor = MaterialTheme.colorScheme.tertiary
            ) {
                Icon(
                    Icons.Default.Add,
                    modifier = Modifier.size(40.dp),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(
                    color = Color.LightGray
                ),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(
                vertical = 18.dp
            )
        ) {

            items(notes.size) { index ->
                val note = notes[index]

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            viewModel.changeChosenID(newID = note.id)
                            navController.navigate(route = Routes.notePage)
                        }
                        .padding(
                            horizontal = 16.dp,
                            vertical = 14.dp,
                        )
                ) {
                    Text(
                        text = note.title,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = note.description,
                    )
                }
            }
        }
    }
}