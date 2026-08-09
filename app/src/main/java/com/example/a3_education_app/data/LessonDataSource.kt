package com.example.a3_education_app.data

import com.example.a3_education_app.R
import com.example.a3_education_app.model.Lesson

object LessonDataSource {
    val lessons: List<Lesson> = listOf(
        Lesson(
            id = "solar_system_tour",
            title = "Our Solar System",
            content = "The solar system is our home in space. It has the Sun, eight planets, many moons, asteroids, and comets.",
            facts = listOf(
                "The Sun is at the center of the solar system.",
                "Planets travel around the Sun in paths called orbits.",
                "The eight planets are Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, and Neptune.",
                "Dwarf planets like Pluto are also part of the solar system."
            ),
            imageRes = R.drawable.earth,
            sourceUrl = "https://science.nasa.gov/solar-system/",
            quizPlanetId = "earth"
        ),
        Lesson(
            id = "the_sun",
            title = "The Sun",
            content = "The Sun is a star. It gives Earth light and heat and holds the planets in orbit with its gravity.",
            facts = listOf(
                "The Sun is a giant ball of hot gas.",
                "Light from the Sun takes about 8 minutes to reach Earth.",
                "The Sun is much larger than Earth.",
                "Without the Sun, Earth would be too cold for life as we know it."
            ),
            imageRes = R.drawable.mercury,
            sourceUrl = "https://science.nasa.gov/sun/",
            quizPlanetId = "mercury"
        ),
        Lesson(
            id = "rocky_vs_gas",
            title = "Rocky Planets and Gas Giants",
            content = "Inner planets are mostly rock. Outer planets are huge and made mostly of gas.",
            facts = listOf(
                "Mercury, Venus, Earth, and Mars are rocky (terrestrial) planets.",
                "Jupiter, Saturn, Uranus, and Neptune are gas giants / giant planets.",
                "Gas giants are much larger than Earth.",
                "Saturn is famous for its bright rings."
            ),
            imageRes = R.drawable.jupiter,
            sourceUrl = "https://science.nasa.gov/solar-system/",
            quizPlanetId = "jupiter"
        ),
        Lesson(
            id = "moons",
            title = "Moons",
            content = "A moon is a natural object that orbits a planet. Earth has one Moon; some planets have many.",
            facts = listOf(
                "Earth's Moon helps make ocean tides.",
                "Mars has two small moons: Phobos and Deimos.",
                "Jupiter has dozens of moons, including Europa and Ganymede.",
                "Some moons may hide oceans under icy crusts."
            ),
            imageRes = R.drawable.earth,
            sourceUrl = "https://science.nasa.gov/moon/",
            quizPlanetId = "earth"
        ),
        Lesson(
            id = "gravity_space",
            title = "Gravity in Space",
            content = "Gravity is the pull between objects. The Sun's gravity keeps planets in orbit. A planet's gravity holds moons and people down.",
            facts = listOf(
                "Bigger objects have stronger gravity.",
                "On the Moon, you would weigh less than on Earth.",
                "Gravity keeps the International Space Station in orbit around Earth.",
                "Without gravity, planets would not stay in neat paths around the Sun."
            ),
            imageRes = R.drawable.saturn,
            sourceUrl = "https://science.nasa.gov/",
            quizPlanetId = "saturn"
        ),
        Lesson(
            id = "day_night_seasons",
            title = "Day, Night, and Seasons",
            content = "Earth spins to make day and night. Earth's tilt and path around the Sun help make seasons.",
            facts = listOf(
                "One full spin of Earth is about 24 hours — one day.",
                "One full trip around the Sun is about 365 days — one year.",
                "Earth is tilted, so sunlight hits different places more strongly during the year.",
                "When it is summer in one hemisphere, it is winter in the other."
            ),
            imageRes = R.drawable.earth,
            sourceUrl = "https://science.nasa.gov/earth/",
            quizPlanetId = "earth"
        ),
        Lesson(
            id = "what_is_exoplanet",
            title = "What Is an Exoplanet?",
            content = "An exoplanet is a planet that orbits a star other than our Sun. Thousands have been found.",
            facts = listOf(
                "Exoplanets are outside our solar system.",
                "Some exoplanets are rocky; others are gas giants.",
                "Scientists look for exoplanets that might be the right temperature for liquid water.",
                "You can explore real exoplanet data in this app's Exoplanets section."
            ),
            imageRes = R.drawable.neptune,
            sourceUrl = "https://science.nasa.gov/exoplanets/",
            quizPlanetId = null
        ),
        Lesson(
            id = "finding_exoplanets",
            title = "How We Find Exoplanets",
            content = "Most exoplanets are far away and hard to see. Scientists often find them by watching stars carefully.",
            facts = listOf(
                "The transit method watches for a tiny dip in a star's light when a planet passes in front.",
                "The wobble method looks for a star tugged by an orbiting planet.",
                "Space telescopes help find many exoplanets.",
                "Try Exoplanet Blitz to practice facts from real discoveries."
            ),
            imageRes = R.drawable.uranus,
            sourceUrl = "https://exoplanets.nasa.gov/",
            quizPlanetId = null
        )
    )

    fun lessonFor(id: String): Lesson =
        lessons.first { it.id == id }
}