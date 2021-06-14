package com.moviebag.unofficial.domain.mapper

import com.moviebag.unofficial.data.model.response.CastResponse
import com.moviebag.unofficial.data.model.uimodel.CastViewItem
import com.moviebag.unofficial.domain.decider.CastItemDecider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CastItemMapper @Inject constructor(private val itemDecider: CastItemDecider) :
    Mapper<CastResponse, List<CastViewItem>?> {
    override fun mapFrom(item: CastResponse): List<CastViewItem>? {
        return item.cast?.map { cast ->
            CastViewItem(
                name = cast.name.orEmpty(),
                character = cast.character.orEmpty(),
                profilePath = itemDecider.provideImagePath(cast.profilePath).orEmpty()
            )
        }
    }
}