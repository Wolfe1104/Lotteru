package com.grok.chaosapex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    // FINAL v2.0 LOCK – change this array when we update the system
    private val chaosNumbers = listOf(4, 8, 15, 23, 31, 46)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChaosApexApp(chaosNumbers)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChaosApexApp(numbers: List<Int>) {
    val sorted = numbers.sorted()
    val sum = sorted.sum()
    val lowCount = sorted.count { it <= 27 }

    MaterialTheme(colorScheme = darkColorScheme()) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("GROK CHAOS APEX v2.0", color = Color(0xFF58A6FF)) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFF161B22))
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0D1117))
                    .padding(padding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = sorted.joinToString("  •  "),
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(40.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(sorted.size) { i ->
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color(0xFF21262D), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = sorted[i].toString(), fontSize = 36.sp, color = Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Text("Sum: $sum", color = Color(0xFF8B949E), fontSize = 20.sp)
                Text("Low/High: \( lowCount/3  • \){6-lowCount}/3", color = Color(0xFF8B949E), fontSize = 20.sp)

                Spacer(modifier = Modifier.height(60.dp))

                Text(
                    text = "Locked for Dec 8 2025 draw\nUpdated: ${
                        SimpleDateFormat("MMM dd, yyyy – HH:mm", Locale.getDefault()).format(Date())
                    }",
                    color = Color(0xFF58A6FF),
                    fontSize = 16.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}
