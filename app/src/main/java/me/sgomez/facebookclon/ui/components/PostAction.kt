package me.sgomez.facebookclon.ui.components

import android.graphics.PostProcessor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PostAction(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LikeButton()
        CommentButton()
        ShareButton()
    }
}
@Preview
@Composable
fun PostActionPreview(){
    PostAction()
}