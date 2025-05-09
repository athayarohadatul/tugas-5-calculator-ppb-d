package com.example.kalkulatorsederhana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var num1 by remember { mutableStateOf("0") }
            var num2 by remember { mutableStateOf("0") }
            var result by remember { mutableStateOf("0") }

            val buttonColor = Color(0xFFB39DDB)      // Ungu muda
            val textFieldColor = Color(0xFFEDE7F6)    // Ungu lebih terang

            fun toIntSafe(value: String): Int = value.toIntOrNull() ?: 0

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Basic Calculator App",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = num1,
                            onValueChange = { num1 = it },
                            modifier = Modifier
                                .weight(1f)
                                .height(60.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = textFieldColor,
                                unfocusedContainerColor = textFieldColor
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )

                        TextField(
                            value = num2,
                            onValueChange = { num2 = it },
                            modifier = Modifier
                                .weight(1f)
                                .height(60.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = textFieldColor,
                                unfocusedContainerColor = textFieldColor
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )

                        Text(
                            text = "=",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(60.dp)
                                .background(Color(0xFFB39DDB), shape = RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = result,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row {
                        Button(
                            onClick = { result = (toIntSafe(num1) + toIntSafe(num2)).toString() },
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                        ) {
                            Text(text = "+", color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = { result = (toIntSafe(num1) - toIntSafe(num2)).toString() },
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                        ) {
                            Text(text = "-", color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = { result = (toIntSafe(num1) * toIntSafe(num2)).toString() },
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                        ) {
                            Text(text = "x", color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                                result = if (toIntSafe(num2) == 0) {
                                    "Err"
                                } else {
                                    (toIntSafe(num1) / toIntSafe(num2)).toString()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                        ) {
                            Text(text = "÷", color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "Athaya Rohadatul (5025221235)",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Menambahkan kalkulator suhu
                    TemperatureConverterUI()
                }
            }
        }
    }
}

@Composable
fun TemperatureConverterUI() {
    val buttonColor = Color(0xFFB39DDB)
    val textFieldColor = Color(0xFFEDE7F6)

    var inputTemp by remember { mutableStateOf("0") }
    var resultTemp by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Konversi Suhu",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = inputTemp,
            onValueChange = { inputTemp = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = textFieldColor,
                unfocusedContainerColor = textFieldColor
            ),
            shape = RoundedCornerShape(8.dp),
            placeholder = { Text("Masukkan suhu Celcius") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val c = inputTemp.toDoubleOrNull()
                    resultTemp = if (c != null) {
                        val f = c * 9 / 5 + 32
                        "%.2f °F".format(f)
                    } else {
                        "Invalid"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text("Fahrenheit", color = Color.White)
            }

            Button(
                onClick = {
                    val c = inputTemp.toDoubleOrNull()
                    resultTemp = if (c != null) {
                        val k = c + 273.15
                        "%.2f K".format(k)
                    } else {
                        "Invalid"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text("Kelvin", color = Color.White)
            }

            Button(
                onClick = {
                    val c = inputTemp.toDoubleOrNull()
                    resultTemp = if (c != null) {
                        val r = c * 4 / 5
                        "%.2f °R".format(r)
                    } else {
                        "Invalid"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text("Reamur", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(buttonColor, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "$inputTemp °C",
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "=",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = resultTemp,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}
