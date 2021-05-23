package id.interconnect.moviesandtv.utils

import id.interconnect.moviesandtv.data.dataentity.MovieItem
import id.interconnect.moviesandtv.data.dataentity.SingleMovie
import id.interconnect.moviesandtv.data.dataentity.SingleTV
import id.interconnect.moviesandtv.data.dataentity.TVItem

object DummyData {

    fun generateDummyListMovie(): List<MovieItem> {
        val listMovie = ArrayList<MovieItem>()

        listMovie.add(
            MovieItem(
                605116, "Project Power", 6.8, "/bOKjzWDxiDkgEQznhzP4kdeAHNI.jpg",
                "An ex-soldier, a teen and a cop collide in New Orleans as they hunt for the source behind a dangerous new pill that grants users temporary superpowers."
            )
        )
        listMovie.add(
            MovieItem(
                703771,
                "Deathstroke: Knights & Dragons - The Movie",
                7.0,
                "/vFIHbiy55smzi50RmF8LQjmpGcx.jpg",
                "Ten years ago, Slade Wilson-aka the super-assassin called Deathstroke-made a tragic mistake and his wife and son paid a terrible price. Now, a decade later, Wilson's family is threatened once again by the murderous Jackal and the terrorists of H.IV.E. Can Deathstroke atone for the sins of the past-or will his family pay the ultimate price?"
            )
        )
        listMovie.add(
            MovieItem(
                726664, "Fearless", 7.3, "/5oQJ6HeNGWnEtP9Qyt5IZjuKI7j.jpg",
                "A teen gamer is forced to level up to full-time babysitter when his favorite video game drops three superpowered infants from space into his backyard."
            )
        )
        listMovie.add(
            MovieItem(
                475557, "Joker", 8.2, "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure."
            )
        )
        listMovie.add(
            MovieItem(
                612706, "Work It", 405.0, "/b5XfICAvUe8beWExBz97i0Qw4Qh.jpg",
                "A brilliant but clumsy high school senior vows to get into her late father's alma mater by transforming herself and a misfit squad into dance champions."
            )
        )
        listMovie.add(
            MovieItem(
                521034, "The Secret Garden", 7.4, "/5MSDwUcqnGodFTvtlLiLKK0XKS.jpg",
                "Mary Lennox is born in India to wealthy British parents who never wanted her. When her parents suddenly die, she is sent back to England to live with her uncle. She meets her sickly cousin, and the two children find a wondrous secret garden lost in the grounds of Misselthwaite Manor."
            )
        )
        listMovie.add(
            MovieItem(
                547016, "The Old Guard", 7.3, "/cjr4NWURcVN3gW5FlHeabgBHLrY.jpg",
                "Four undying warriors who've secretly protected humanity for centuries become targeted for their mysterious powers just as they discover a new immortal."
            )
        )
        listMovie.add(
            MovieItem(
                594718, "Sputnik", 6.3, "/eAUzmhP54bE1vPXaY7FbuZREJlR.jpg",
                "At the height of the Cold War, a Soviet spacecraft crash lands after a mission gone awry, leaving the commander as its only survivor. After a renowned Russian psychologist is brought in to evaluate the commander’s mental state, it becomes clear that something dangerous may have come back to Earth with him…"
            )
        )
        listMovie.add(
            MovieItem(
                454433, "Magic Camp", 7.4, "/awcPLFFYjufRXd2oAAP6ZIXF9vM.jpg",
                "Andy, at the urging of his former mentor and Magic Camp owner Roy Preston, returns to the camp of his youth hoping to reignite his career. Instead, he finds inspiration in his ragtag bunch of rookie magicians."
            )
        )
        listMovie.add(
            MovieItem(
                430155, "Кома", 6.1, "/ijJm0RSeKIr67qkHLtSmtigS6ra.jpg",
                "A young and talented architect comes to his senses after a horrific accident only to find himself in the odd dystopian world. A world that is filled with the memories of all current coma patients. Just like a human memory this world is fragmental, chaotic and unstable. This is COMA: icecaps, rivers and cities can all exist in a space of a single room and laws of physics are no longer laws as they can be bent."
            )
        )
        return listMovie
    }

    fun generateDetailMovie(): List<SingleMovie> {
        val listMovie = ArrayList<SingleMovie>()
        listMovie.apply {
            add(
                SingleMovie(
                    id = 605116,
                    original_title = "Project Power",
                    vote_average = 6.8,
                    poster_path = "/bOKjzWDxiDkgEQznhzP4kdeAHNI.jpg",
                    genres = arrayListOf("Action", "Crime", "Science Fiction"),
                    overview = "An ex-soldier, a teen and a cop collide in New Orleans as they hunt for the source behind a dangerous new pill that grants users temporary superpowers.",
                    production_companies = arrayListOf(
                        "Screen Arcade",
                        "Supermarché"
                    ),
                    adult = false,
                    original_language = "en",
                    popularity = 271.572
                )
            )
            add(
                SingleMovie(
                    id = 703771,
                    original_title = "Deathstroke: Knights & Dragons - The Movie",
                    vote_average = 7.0,
                    poster_path = "/vFIHbiy55smzi50RmF8LQjmpGcx.jpg",
                    genres = arrayListOf("Animation", "Action"),
                    overview = "Ten years ago, Slade Wilson-aka the super-assassin called Deathstroke-made a tragic mistake and his wife and son paid a terrible price. Now, a decade later, Wilson's family is threatened once again by the murderous Jackal and the terrorists of H.IV.E. Can Deathstroke atone for the sins of the past-or will his family pay the ultimate price?",
                    production_companies = arrayListOf(
                        "Warner Bros. Animation",
                        "DC Comics",
                        "Blue Ribbon Content",
                        "Berlanti Productions",
                        "DC Entertainment"
                    ),
                    adult = false,
                    original_language = "en",
                    popularity = 109.739
                )
            )
            add(
                SingleMovie(
                    id = 726664,
                    original_title = "Fearless",
                    vote_average = 7.3,
                    poster_path = "/5oQJ6HeNGWnEtP9Qyt5IZjuKI7j.jpg",
                    genres = arrayListOf("Animation", "Comedy"),
                    overview = "A teen gamer is forced to level up to full-time babysitter when his favorite video game drops three superpowered infants from space into his backyard.",
                    production_companies = arrayListOf("Vanguard Animation"),
                    adult = false,
                    original_language = "en",
                    popularity = 101.68
                )
            )
            add(
                SingleMovie(
                    id = 475557,
                    original_title = "Joker",
                    vote_average = 8.2,
                    poster_path = "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                    genres = arrayListOf("Crime", "Thriller", "Drama"),
                    overview = "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                    production_companies = arrayListOf(
                        "DC Entertainment",
                        "Warner Bros. Pictures",
                        "DC Comics",
                        "Joint Effort",
                        "Village Roadshow Pictures",
                        "Bron Studios",
                        "Creative Wealth Media Finance",
                        "DC Films",
                        "22 & Indiana Pictures"
                    ),
                    adult = false,
                    original_language = "en",
                    popularity = 91.27
                )
            )
            add(
                SingleMovie(
                    id = 612706,
                    original_title = "Work It",
                    vote_average = 405.0,
                    poster_path = "/b5XfICAvUe8beWExBz97i0Qw4Qh.jpg",
                    genres = arrayListOf("Comedy", "Music"),
                    overview = "A brilliant but clumsy high school senior vows to get into her late father's alma mater by transforming herself and a misfit squad into dance champions.",
                    production_companies = arrayListOf(
                        "STX Entertainment",
                        "Alloy Entertainment",
                        "AK Worldwide"
                    ),
                    adult = false,
                    original_language = "en",
                    popularity = 66.991
                )
            )
            add(
                SingleMovie(
                    id = 521034,
                    original_title = "The Secret Garden",
                    vote_average = 7.4,
                    poster_path = "/5MSDwUcqnGodFTvtlLiLKK0XKS.jpg",
                    genres = arrayListOf("Drama", "Fantasy", "Family"),
                    overview = "Mary Lennox is born in India to wealthy British parents who never wanted her. When her parents suddenly die, she is sent back to England to live with her uncle. She meets her sickly cousin, and the two children find a wondrous secret garden lost in the grounds of Misselthwaite Manor.",
                    production_companies = arrayListOf("Heyday Films", "StudioCanal"),
                    adult = false,
                    original_language = "en",
                    popularity = 76.96
                )
            )
            add(
                SingleMovie(
                    id = 547016,
                    original_title = "The Old Guard",
                    vote_average = 7.3,
                    genres = arrayListOf(),
                    poster_path = "/cjr4NWURcVN3gW5FlHeabgBHLrY.jpg",
                    overview = "Four undying warriors who've secretly protected humanity for centuries become targeted for their mysterious powers just as they discover a new immortal.",
                    production_companies = arrayListOf(
                        "Skydance Media",
                        "Denver and Delilah Productions",
                        "Dune Films"
                    ),
                    adult = false,
                    original_language = "en",
                    popularity = 58.696
                )
            )
            add(
                SingleMovie(
                    id = 594718,
                    original_title = "Спутник",
                    vote_average = 6.3,
                    genres = arrayListOf("Science Fiction", "Drama", "Horror"),
                    poster_path = "/eAUzmhP54bE1vPXaY7FbuZREJlR.jpg",
                    overview = "At the height of the Cold War, a Soviet spacecraft crash lands after a mission gone awry, leaving the commander as its only survivor. After a renowned Russian psychologist is brought in to evaluate the commander’s mental state, it becomes clear that something dangerous may have come back to Earth with him…",
                    production_companies = arrayListOf(
                        "Vodorod Film Company", "Art Pictures Studio", "Hype Film",
                        "National Media Group", "STS Media", "Cinema Fund"
                    ),
                    adult = false,
                    original_language = "ru",
                    popularity = 143.819
                )
            )
            add(
                SingleMovie(
                    id = 454433,
                    original_title = "Magic Camp",
                    vote_average = 7.4,
                    genres = arrayListOf("Comedy", "Family", "Fantasy"),
                    poster_path = "/awcPLFFYjufRXd2oAAP6ZIXF9vM.jpg",
                    overview = "Andy, at the urging of his former mentor and Magic Camp owner Roy Preston, returns to the camp of his youth hoping to reignite his career. Instead, he finds inspiration in his ragtag bunch of rookie magicians.",
                    production_companies = arrayListOf("Walt Disney Pictures", "Disney+"),
                    adult = false,
                    original_language = "en",
                    popularity = 70.258
                )
            )
            add(
                SingleMovie(
                    id = 430155,
                    original_title = "Кома",
                    vote_average = 6.1,
                    genres = arrayListOf("Fantasy", "Action", "Science Fiction"),
                    poster_path = "/ijJm0RSeKIr67qkHLtSmtigS6ra.jpg",
                    overview = "A young and talented architect comes to his senses after a horrific accident only to find himself in the odd dystopian world. A world that is filled with the memories of all current coma patients. Just like a human memory this world is fragmental, chaotic and unstable. This is COMA: icecaps, rivers and cities can all exist in a space of a single room and laws of physics are no longer laws as they can be bent.",
                    production_companies = arrayListOf(),
                    adult = false,
                    original_language = "ru",
                    popularity = 51.885
                )
            )
        }
        return listMovie
    }

    fun generateDummyListTV(): List<TVItem> {
        val listTV = ArrayList<TVItem>()

        listTV.apply {
            add(
                TVItem(
                    2734,
                    "Law & Order: Special Victims Unit",
                    7.3,
                    "/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                    "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories."
                )
            )
            add(
                TVItem(
                    63174,
                    "Lucifer",
                    8.4,
                    "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                    "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."
                )
            )
            add(
                TVItem(
                    1416,
                    "Grey's Anatomy",
                    7.9,
                    "/jnsvc7gCKocXnrTXF6p03cICTWb.jpg",
                    "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
                )
            )
            add(
                TVItem(
                    456,
                    "The Simpsons",
                    7.6,
                    "/qcr9bBY6MVeLzriKCmJOv1562uY.jpg",
                    "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general."
                )
            )
            add(
                TVItem(
                    1622,
                    "Supernatural",
                    8.0,
                    "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                    "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. "
                )
            )
            add(
                TVItem(
                    1434,
                    "Family Guy",
                    6.8,
                    "/q3E71oY6qgAEiw6YZIHDlHSLwer.jpg",
                    "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues."
                )
            )
            add(
                TVItem(
                    60735,
                    "The Flash",
                    7.5,
                    "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."
                )
            )
            add(
                TVItem(
                    4614,
                    "NCIS",
                    7.1,
                    "/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg",
                    "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position."
                )
            )
            add(
                TVItem(
                    60572,
                    "Pokémon",
                    6.8,
                    "/rOuGm07PxBhEsK9TaGPRQVJQm1X.jpg",
                    "Join Satoshi, accompanied by his partner Pikachu, as he travels through many regions, meets new friends and faces new challenges on his quest to become a Pokémon Master."
                )
            )
            add(
                TVItem(
                    48866,
                    "The 100",
                    7.6,
                    "/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
                    "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable."
                )
            )

        }
        return listTV
    }

    fun generateDetailTV() : List<SingleTV> {
        val listTV = ArrayList<SingleTV>()

        listTV.apply {
            add(
                SingleTV(
                    id = 2734,
                    original_name = "Law & Order: Special Victims Unit",
                    vote_average = 7.3,
                    poster_path = "/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                    overview = "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                    genres = arrayListOf("Crime", "Drama"),
                    created_by = arrayListOf("Dick Wolf"),
                    original_language = "en",
                    popularity = 174.84,
                    number_of_episodes = 478,
                    production_companies = arrayListOf(
                        "Wolf Films",
                        "Universal Television",
                        "USA Network"
                    )
                )
            )
            add(
                SingleTV(
                    id = 63174,
                    original_name = "Lucifer",
                    vote_average = 8.4,
                    poster_path = "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                    overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                    genres = arrayListOf("Crime", "Sci-Fi & Fantasy"),
                    created_by = arrayListOf("Tom Kapinos"),
                    original_language = "en",
                    popularity = 179.102,
                    number_of_episodes = 75,
                    production_companies = arrayListOf(
                        "Fox Productions",
                        "Warner Bros. Television",
                        "Aggressive Mediocrity",
                        "DC Entertainment",
                        "Jerry Bruckheimer Television"
                    )
                )
            )
            add(
                SingleTV(
                    id = 1416,
                    original_name = "Grey's Anatomy",
                    vote_average = 7.9,
                    poster_path = "/jnsvc7gCKocXnrTXF6p03cICTWb.jpg",
                    overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                    genres = arrayListOf("Drama"),
                    created_by = arrayListOf("Shonda Rhimes"),
                    original_language = "en",
                    popularity = 162.928,
                    number_of_episodes = 362,
                    production_companies = arrayListOf(
                        "The Mark Gordon Company",
                        "ShondaLand",
                        "ABC Studios",
                        "Touchstone Television"
                    )
                )
            )
            add(
                SingleTV(
                    id = 456,
                    original_name = "The Simpsons",
                    vote_average = 7.6,
                    poster_path = "/qcr9bBY6MVeLzriKCmJOv1562uY.jpg",
                    overview = "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                    genres = arrayListOf("Animation", "Comedy"),
                    created_by = arrayListOf("Matt Groening"),
                    original_language = "en",
                    popularity = 149.171,
                    number_of_episodes = 685,
                    production_companies = arrayListOf(
                        "The Curiosity Company",
                        "Gracie Films",
                        "20th Century Fox Television"
                    )
                )
            )
            add(
                SingleTV(
                    id = 1622,
                    original_name = "Supernatural",
                    vote_average = 8.0,
                    poster_path = "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                    overview = "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. ",
                    genres = arrayListOf("Drama", "Mystery", "Sci-Fi & Fantasy"),
                    created_by = arrayListOf("Eric Kripke"),
                    original_language = "en",
                    popularity = 147.196,
                    number_of_episodes = 327,
                    production_companies = arrayListOf(
                        "Kripke Enterprises",
                        "Supernatural Films",
                        "Wonderland Sound and Vision",
                        "Warner Bros. Television"
                    )
                )
            )
            add(
                SingleTV(
                    id = 1434,
                    original_name = "Family Guy",
                    vote_average = 6.8,
                    poster_path = "/q3E71oY6qgAEiw6YZIHDlHSLwer.jpg",
                    overview = "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                    genres = arrayListOf("Animation", "Comedy"),
                    created_by = arrayListOf("Seth MacFarlane"),
                    original_language = "en",
                    popularity = 145.224,
                    number_of_episodes = 348,
                    production_companies = arrayListOf(
                        "Fox Television Animation",
                        "20th Century Fox Television",
                        "Fuzzy Door Productions"
                    )
                )
            )
            add(
                SingleTV(
                    id = 60735,
                    original_name = "The Flash",
                    vote_average = 7.5,
                    poster_path = "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                    overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                    genres = arrayListOf("Drama", "Sci-Fi & Fantasy"),
                    created_by = arrayListOf("Greg Berlanti", "Geoff Johns", "Andrew Kreisberg"),
                    original_language = "en",
                    popularity = 143.901,
                    number_of_episodes = 133,
                    production_companies = arrayListOf(
                        "Warner Bros. Television",
                        "Berlanti Productions",
                        "DC Entertainment",
                        "Mad Ghost Productions"
                    )
                )
            )
            add(
                SingleTV(
                    id = 4614,
                    original_name = "NCIS",
                    vote_average = 7.1,
                    poster_path = "/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg",
                    overview = "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                    genres = arrayListOf("Action & Adventure", "Crime", "Drama"),
                    created_by = arrayListOf("Donald P. Bellisario", "Don McGill"),
                    original_language = "en",
                    popularity = 142.607,
                    number_of_episodes = 398,
                    production_companies = arrayListOf(
                        "thinkfilm",
                        "Paramount Television",
                        "Belisarius Productions"
                    )
                )
            )
            add(
                SingleTV(
                    id = 60572,
                    original_name = "Pokémon",
                    vote_average = 6.8,
                    poster_path = "/rOuGm07PxBhEsK9TaGPRQVJQm1X.jpg",
                    overview = "Join Satoshi, accompanied by his partner Pikachu, as he travels through many regions, meets new friends and faces new challenges on his quest to become a Pokémon Master.",
                    genres = arrayListOf("Animation", "Action & Adventure", "Sci-Fi & Fantasy"),
                    created_by = arrayListOf("Satoshi Tajiri", "Junichi Masuda", "Ken Sugimori"),
                    original_language = "ja",
                    popularity = 140.196,
                    number_of_episodes = 1123,
                    production_companies = arrayListOf(
                        "The Summit Media Group, Inc.",
                        "Shogakukan Production",
                        "The Pokémon Company",
                        "Nintendo",
                        "TV Tokyo",
                        "4Kids Entertainment"
                    )
                )
            )
            add(
                SingleTV(
                    id = 48866,
                    original_name = "The 100",
                    vote_average = 7.6,
                    poster_path = "/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
                    overview = "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.",
                    genres = arrayListOf("Sci-Fi & Fantasy", "Drama", "Action & Adventure"),
                    created_by = arrayListOf("Jason Rothenberg"),
                    original_language = "en",
                    popularity = 137.039,
                    number_of_episodes = 100,
                    production_companies = arrayListOf(
                        "Warner Bros. Television",
                        "Alloy Entertainment",
                        "Bonanza Productions",
                        "CBS Television Studios"
                    )
                )
            )
        }
        return listTV
    }

    fun DummyListMovie() : List<id.interconnect.moviesandtv.data.MovieItem>{
        val listMovie = ArrayList<id.interconnect.moviesandtv.data.MovieItem>()
        listMovie.apply {
            add(
                id.interconnect.moviesandtv.data.MovieItem(
                    id = 605116,
                    title = "Project Power",
                    vote_average = 869.0,
                    poster_path = "/TnOeov4w0sTtV2gqICqIxVi74V.jpg" ,
                    overview = "An ex-soldier, a teen and a cop collide in New Orleans as they hunt for the source behind a dangerous new pill that grants users temporary superpowers."
                )
            )
            add(
                id.interconnect.moviesandtv.data.MovieItem(
                    id = 718444,
                    title = "Rogue",
                    vote_average = 82.0,
                    poster_path = "/uOw5JD8IlD546feZ6oxbIjvN66P.jpg",
                    overview = "Battle-hardened O’Hara leads a lively mercenary team of soldiers on a daring mission: rescue hostages from their captors in remote Africa. But as the mission goes awry and the team is stranded, O’Hara’s squad must face a bloody, brutal encounter with a gang of rebels."
                )
            )
        }
        return listMovie
    }

}