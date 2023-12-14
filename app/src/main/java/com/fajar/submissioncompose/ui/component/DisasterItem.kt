package com.fajar.submissioncompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fajar.submissioncompose.R
import com.fajar.submissioncompose.ui.theme.SubmissionComposeTheme


@Composable
fun DisasterItem(

    name:String,
    date:String,
    image:Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(240.dp)
                .width(420.dp)
            )
        Text(
            text = name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = date,
            style = MaterialTheme.typography.titleMedium
            )

    }
}

@Composable
@Preview(showBackground = true)
fun DisasterItemPreview(){
    SubmissionComposeTheme {
        DisasterItem(image = R.drawable.dustbowl, name = "Triangle Shirtwaist Factory Fire" , date = "1930s")
    }
}