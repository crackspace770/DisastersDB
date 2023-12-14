package com.fajar.submissioncompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fajar.submissioncompose.R
import com.fajar.submissioncompose.ui.theme.SubmissionComposeTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisasterDetail(
    name:String,
    date:String,
    location:String,
    description: String,
    death:Int,
    image:Int,
    bookmarkStatus: Boolean,
    updateBookmarkStatus: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var rememberedBookmarkStatus by remember { mutableStateOf(bookmarkStatus) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable { onBackClick() }
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Add some space between the back button and the title
                        Text(
                            text = name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Update the remembered state and trigger recomposition
                        rememberedBookmarkStatus = !rememberedBookmarkStatus
                        // Call the provided function to handle the bookmark update
                        updateBookmarkStatus()
                    }) {
                        Icon(
                            imageVector = if (rememberedBookmarkStatus) {
                                Icons.Default.Favorite
                            } else {
                                Icons.Default.FavoriteBorder
                            },
                            contentDescription = stringResource(R.string.save_bookmark),
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->

        Column (modifier = modifier
            .padding(innerPadding)
        ) {
            Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
            ) {

                Column(
                  //  horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {

                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(350.dp)
                            .width(550.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = name,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Row {

                        Text(
                            text = "Date : ",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = date,
                            style = MaterialTheme.typography.bodyMedium
                        )

                    }

                    Row {

                        Text(
                            text = "Location : ",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = location,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }


                    Row (

                    ) {

                        Text(
                            text = "Death Toll : ",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = death.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 8.dp)

                    )

                }

            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun DisasterDetailReview() {
    SubmissionComposeTheme {
        DisasterDetail(
            name = "The 2004 Indian Ocean Tsunami",
            date = "December 26, 2004",
            location = "Indian Ocean",
            description = "The 2004 Indian Ocean Tsunami was triggered by a massive undersea earthquake with a magnitude of 9.1–9.3. The earthquake, one of the largest ever recorded, unleashed a series of devastating tsunamis that struck the coastal areas of several countries bordering the Indian Ocean. The waves reached as far as South Africa, with the death toll reaching approximately 230,000 people. Entire communities were swept away, and the aftermath led to extensive humanitarian efforts and improvements in tsunami warning systems.",
            death = 230000,
            image = R.drawable.bhopal_gas,
            onBackClick = {},
            bookmarkStatus = false,
            updateBookmarkStatus = {},
        )
    }
}