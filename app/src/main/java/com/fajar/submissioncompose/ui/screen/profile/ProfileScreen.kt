package com.fajar.submissioncompose.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajar.submissioncompose.R
import com.fajar.submissioncompose.ui.theme.SubmissionComposeTheme

@Composable
fun ProfileScreen(
    modifier : Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable(onClick = {})
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        Image(
            painter = painterResource(R.drawable.img_3744e),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .padding(4.dp)
                .border(2.dp, Color.Cyan, CircleShape)
                .clip(CircleShape)
                .size(120.dp)
        )

        Text(
            text = "Fajar Setyawan",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
        )

        Text(
            text = "crackspace990@gmail.com",
            fontSize = 20.sp,
        )
        
    }
    
}

@Preview(showBackground = true, )
@Composable
fun TryComposeAppPreview(){
    SubmissionComposeTheme {
        ProfileScreen()
    }
}