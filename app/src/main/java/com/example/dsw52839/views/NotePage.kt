package com.example.dsw52839.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dsw52839.components.ConfirmationDialog
import com.example.dsw52839.viewmodel.DetailViewModel

@Composable
fun NotePage(
    navController: NavController,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val title = viewModel.title.collectAsState()
    val description = viewModel.description.collectAsState()
    val showConfirmationDialog = viewModel.showConfirmationDialog.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                )
                .padding(
                    top = 40.dp,
                    bottom = 20.dp,
                )
                .padding(
                    horizontal = 16.dp,
                ),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        viewModel.saveOrUpdate() {
                            navController.popBackStack()
                        }
                    }
                ,
                contentDescription = null,
                tint = Color.White,
            )
            Icon(
                Icons.Default.Delete,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        viewModel.showConfirmationDialog()
                    }
                ,
                contentDescription = null,
                tint = Color.White,
            )
        }

        TextField(
            value = title.value,
            onValueChange = { viewModel.titleOnValueChange(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Enter Title")
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 24.sp
            )
        )

        TextField(
            value = description.value,
            onValueChange = {
                viewModel.descriptionOnValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            ,
            placeholder = {
                Text(text = "Enter Description")
            }
        )

    }

    if (showConfirmationDialog.value) {
        ConfirmationDialog(
            dismissButton = {
                viewModel.hideConfirmationDialog()
            },
            confirmButton =  {
                viewModel.deleteNote {
                    navController.popBackStack()
                }
            },
        )
    }
}