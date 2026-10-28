package me.sgomez.facebookclon.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class Story(
    val id: String,
    val userName: String,
    val userAvatarUrl: String,
    val storyImageUrl: String,
    val isViewed: Boolean = false
)

@Composable
fun StoriesRow(
    modifier: Modifier = Modifier,
    stories: List<Story> = emptyList(),
    onCreateStoryClick: () -> Unit = {},
    onStoryClick: (Story) -> Unit = {}
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        // First item: Create Story card
        item {
            CreateStoryCard(onClick = onCreateStoryClick)
        }

        // Subsequent items: User Stories
        items(stories, key = { it.id }) { story ->
            StoryCard(
                story = story,
                onClick = { onStoryClick(story) }
            )
        }
    }
}

@Composable
fun CreateStoryCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(104.dp)
            .height(180.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top half: User profile image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.1f)
            ) {
                AsyncImage(
                    model = "https://picsum.photos/200/300",
                    contentDescription = "Tu foto de perfil",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Bottom half: White/surface background with Add button and text
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f)
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.TopCenter
            ) {
                // Floating Plus Button overlapping top and bottom
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .padding(top = 4.dp)
                        .offset(y = (-20).dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Color(0xFF1877F2)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Crear historia",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Text(
                    text = "Crear historia",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(top = 18.dp)
                )
            }
        }
    }
}

@Composable
fun StoryCard(
    modifier: Modifier = Modifier,
    story: Story,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(104.dp)
            .height(180.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background story image
            AsyncImage(
                model = story.storyImageUrl,
                contentDescription = story.userName,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient scrim at bottom for text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                            startY = 100f
                        )
                    )
            )

            // User profile picture with border at top-left
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(if (story.isViewed) Color.Gray else Color(0xFF1877F2))
                    .padding(2.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = story.userAvatarUrl,
                    contentDescription = story.userName,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            // User name at bottom
            Text(
                text = story.userName,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoriesRowPreview() {
    val sampleStories = listOf(
        Story(
            id = "1",
            userName = "Maria Gomez",
            userAvatarUrl = "https://picsum.photos/200",
            storyImageUrl = "https://picsum.photos/300/500",
            isViewed = false
        ),
        Story(
            id = "2",
            userName = "Carlos Perez",
            userAvatarUrl = "https://picsum.photos/201",
            storyImageUrl = "https://picsum.photos/300/501",
            isViewed = true
        ),
        Story(
            id = "3",
            userName = "Ana Lopez",
            userAvatarUrl = "https://picsum.photos/202",
            storyImageUrl = "https://picsum.photos/300/502",
            isViewed = false
        ),
        Story(
            id = "4",
            userName = "Juan Rodriguez",
            userAvatarUrl = "https://picsum.photos/203",
            storyImageUrl = "https://picsum.photos/300/503",
            isViewed = false
        )
    )

    StoriesRow(
        stories = sampleStories,
        onCreateStoryClick = {},
        onStoryClick = {}
    )
}
