package com.example.cuadricula

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.DataSource
import com.example.affirmations.data.Topic
import com.example.cuadricula.ui.theme.CuadriculaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuadriculaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        CuadriculApp()
                }
            }
        }
    }
}

@Composable
fun CuadriculApp(){
    CuadriculaLista(TopicList = DataSource.topics)
}

@Composable
fun CuadriculaLista(TopicList: List<Topic>, modifier: Modifier=Modifier){
    LazyVerticalGrid(
        /*Indica cuantas columnas  */
        columns = GridCells.Fixed(2),
        /*Espaciado entre elementos*/
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp
            )
        ){
        items(TopicList){
            topic -> CuadriculaCard(
            topic = topic,
            modifier=modifier)
        }
    }
}


@Composable
fun CuadriculaCard(topic: Topic, modifier: Modifier=Modifier){
    Card(modifier=modifier) {
        /*Lo queremos en un renglon*/
        Row {
            /*Box sirve para contener algo dentro de el*/
            Box{
                Image(
                    painter = painterResource(topic.imageResourceId),
                    contentDescription = stringResource(topic.stringResourceId),
                    modifier = Modifier
                        .size(width = 68.dp, height = 68.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column {
            Text(
                text = LocalContext.current.getString(topic.stringResourceId),
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 0.dp
                ),
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Icon(
                    painter = painterResource(R.drawable.ic_grain) ,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 8.dp)
                )
                Text(
                    text = topic.number.toString(),
                    modifier = Modifier.padding(
                        start=8.dp,
                        top = 10.dp),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        }
    }
}

@Preview
@Composable
fun CuadriculaCardPreview(){
    CuadriculaCard(Topic(R.string.photography,250,R.drawable.photography))
}