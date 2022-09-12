package com.example.core_database.model

import com.example.core_network.model.PopularMovieDTO
import org.junit.Assert
import org.junit.Test


class EntityModelMappingTest {

    @Test
    fun popular_movie_dto_can_be_mapped_to_popular_movie_entity() {
        val networkModel = PopularMovieDTO(
            adult = true,
            backdrop_path = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
            genre_ids = listOf(1, 2, 4, 5),
            id = 1,
            original_language = "en",
            original_title = "Suicide Squad",
            overview =
            "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
            popularity = 46.7,
            poster_path = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
            release_date = "2016-08-03",
            title = "Suicide Squad",
            video = false,
            vote_average = 5.8,
            vote_count = 5193
        )
        val entity = networkModel.asEntity()

        Assert.assertEquals(1, entity.id)
        Assert.assertEquals(true, entity.adult)
        Assert.assertEquals("Suicide Squad", entity.title)
        Assert.assertEquals(listOf(1, 2, 4, 5), entity.genre_ids)
    }

}