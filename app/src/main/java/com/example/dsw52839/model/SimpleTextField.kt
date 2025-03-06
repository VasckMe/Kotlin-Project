package com.example.dsw52839.model

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.dsw52839.R
import com.example.dsw52839.ui.theme.PurpleBorder
import com.example.dsw52839.ui.theme.PurpleTextAndIcon

@Composable
fun SimpleTextField(
    placeholder: String,
    leadingIcon: ImageVector,
    showTrailingIcon: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean
) {
    val passwordIsHidden = remember { mutableStateOf(showTrailingIcon) }

    TextField(
        value = value,
        visualTransformation = if (passwordIsHidden.value) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = if (showTrailingIcon) KeyboardType.Password else KeyboardType.Text),
        onValueChange = onValueChange,
        label = { Text(placeholder) },
        leadingIcon = { Icon(leadingIcon, contentDescription = null, tint = PurpleTextAndIcon) },
        trailingIcon = {
            if (showTrailingIcon) {
                IconButton(onClick = { passwordIsHidden.value = !passwordIsHidden.value }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ph_eye),
                        contentDescription = "Ellipse Decoration",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black,
                    )
                }
            }
        },
        singleLine = true,
        isError = !isValid,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .border(2.dp, PurpleBorder, RoundedCornerShape(15.dp))
    )
}