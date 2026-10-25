package kr.ac.kumoh.ce.s20220357.s26w06navigation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import kr.ac.kumoh.ce.s20220357.s26w06navigation.repository.PokemonRepository
import kr.ac.kumoh.ce.s20220357.s26w06navigation.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(
    pokemonId: Int,
    repository: PokemonRepository,
    viewModel: PokemonDetailViewModel = viewModel {
        PokemonDetailViewModel(
            repository = repository,
            pokemonId = pokemonId
        )
    }, // 후행 람다
) {
//    val pokemon = viewModel.getPokemonById(pokemonId)

//    pokemon?.let {
//        Text(it.info)
//    }

    val pokemon by viewModel.pokemon.collectAsStateWithLifecycle()

    var flip by retain { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    flip = !flip
                },
                modifier = Modifier.fillMaxHeight()
            ) {
                Text("<")
            }
            if(!flip) {
                AsyncImage(
                    model = pokemon.image,
                    contentDescription = "${pokemon.name} 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                        //.clip(CircleShape)
                        .clip(RoundedCornerShape(percent = 10))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
            } else {
                AsyncImage(
                    model = pokemon.backimage,
                    contentDescription = "${pokemon.name} 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                        //.clip(CircleShape)
                        .clip(RoundedCornerShape(percent = 10))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
            }
            Button(
                onClick = {
                    flip = !flip
                },
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(">")
            }
        }
        Text(
            text = pokemon.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        pokemon.hp?.let { hp ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "HP: $hp",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Text(
            text = pokemon.info,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}