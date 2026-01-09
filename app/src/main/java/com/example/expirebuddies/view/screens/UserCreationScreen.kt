package com.example.expirebuddies.view.screens

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expirebuddies.model.authentication.User
import com.example.expirebuddies.view.colors.AppTheme
import com.example.expirebuddies.view.colors.Typography
import com.example.expirebuddies.viewmodel.UserCreationEvent
import com.example.expirebuddies.viewmodel.UserCreationViewModel
import java.util.*
import kotlin.text.Typography

//@Preview
@Composable
fun TestPreview() {
    Text("Funziona?")
}

@Preview()
@Composable
fun UserCreationScreen(
   viewmodel:UserCreationViewModel = hiltViewModel()
) {
        RegisterScreen(
            onCreateAccount = { username,date,email,password->
                val user= User(
                    name = username,
                    email = email,
                    dateOfBirth = date.toLong(),
                    password = password

                )
                viewmodel.onEvent(UserCreationEvent.CreateUser(user))
                              },
            onGoogleLogin = {},
            onFacebookLogin = {}
        )
}

@Composable
fun RegisterScreen(
    onCreateAccount: (String, String, String, String) -> Unit,
    onGoogleLogin: () -> Unit,
    onFacebookLogin: () -> Unit
){
    val context= LocalContext.current
    var username by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    val calendar = java.util.Calendar.getInstance()


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            // FORM A SINISTRA
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Crea il tuo account",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(24.dp))
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Username") },
                    singleLine = true,
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = date,
                    onValueChange = { date = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Date") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onCreateAccount(username, date, email, password)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Crea account")
                }
            }

            Spacer(modifier = Modifier.width(24.dp))

            // METODI ALTERNATIVI A DESTRA
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Oppure accedi con",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onGoogleLogin,
                    modifier = Modifier.fillMaxWidth(0.7f),

                    ) {
                    Text("Accedi con Google")
                    Modifier.alpha(0f)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onFacebookLogin,
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text("Accedi con Facebook")
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BirthDatePicker(
        onDateSelected: (Long) -> Unit
    ) {
        val datePickerState = rememberDatePickerState()

        DatePicker(
            state = datePickerState,
            showModeToggle = false
        )

        LaunchedEffect(datePickerState.selectedDateMillis) {
            datePickerState.selectedDateMillis?.let { millis ->
                onDateSelected(millis)
            }
        }
    }


}
