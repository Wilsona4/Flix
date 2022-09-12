package com.example.core_database

import com.example.core_network.model.PopularMovieDTO

object FakeMovies {

    val movie1 = PopularMovieDTO(
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
    val movie2 = PopularMovieDTO(
        adult = true,
        backdrop_path = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
        genre_ids = listOf(1, 2, 4, 5),
        id = 2,
        original_language = "en",
        original_title = "Jason Bourne",
        overview =
        "The most dangerous former operative of the CIA is drawn out of hiding to uncover hidden truths about his past.",
        popularity = 46.7,
        poster_path = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
        release_date = "2016-08-03",
        title = "Jason Bourne",
        video = false,
        vote_average = 7.7,
        vote_count = 4321
    )

    val movie3 = PopularMovieDTO(
        adult = false,
        backdrop_path = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
        genre_ids = listOf(1, 2, 4, 5),
        id = 3,
        original_language = "en",
        original_title = "Now You See Me 2",
        overview =
        "One year after outwitting the FBI and winning the public’s adulation with their mind-bending spectacles, the Four Horsemen resurface only to find themselves face to face with a new enemy who enlists them to pull off their most dangerous heist yet.",
        popularity = 46.7,
        poster_path = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
        release_date = "2016-08-03",
        title = "Now You See Me 2",
        video = true,
        vote_average = 7.7,
        vote_count = 4321
    )


}