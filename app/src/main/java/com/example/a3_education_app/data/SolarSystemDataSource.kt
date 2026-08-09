package com.example.a3_education_app.data

import com.example.a3_education_app.R
import com.example.a3_education_app.model.SolarBody

object SolarSystemDataSource {
    val bodies = listOf(
        SolarBody(
            id = "mercury",
            name = "Mercury",
            type = "Planet",
            moons = 0,
            meanRadiusKm = 2439.7,
            gravity = 3.7,
            summary = "Mercury is the smallest planet and the closest planet to the Sun.",
            facts = listOf(
                "Mercury is the smallest planet in the solar system.",
                "Mercury is the closest planet to the Sun.",
                "A day on Mercury lasts about 59 Earth days.",
                "Mercury has no moons or rings."
            ),
            imageRes = R.drawable.mercury,
            sourceUrl = "https://science.nasa.gov/mercury/facts/"
        ),

        SolarBody(
            id = "venus",
            name = "Venus",
            type = "Planet",
            moons = 0,
            meanRadiusKm = 6051.8,
            gravity = 8.87,
            summary = "Venus is a hot, rocky world with a thick atmosphere and crushing surface pressure.",
            facts = listOf(
                "Venus is the second planet from the Sun.",
                "Venus is the hottest planet in the solar system.",
                "Venus spins in the opposite direction from most planets.",
                "Venus is similar in size and structure to Earth."
            ),
            imageRes = R.drawable.venus,
            sourceUrl = "https://science.nasa.gov/venus/venus-facts/"
        ),

        SolarBody(
            id = "earth",
            name = "Earth",
            type = "Planet",
            moons = 1,
            meanRadiusKm = 6371.0,
            gravity = 9.8,
            summary = "Earth is a rocky planet with liquid surface water and the only known life in the solar system.",
            facts = listOf(
                "Earth is the third planet from the Sun.",
                "Earth is the only planet known to support life.",
                "About 71 percent of Earth's surface is covered by water.",
                "Earth has one natural satellite, the Moon."
            ),
            imageRes = R.drawable.earth,
            sourceUrl = "https://science.nasa.gov/earth/facts/"
        ),

        SolarBody(
            id = "mars",
            name = "Mars",
            type = "Planet",
            moons = 2,
            meanRadiusKm = 3389.5,
            gravity = 3.7,
            summary = "Mars is a dusty, cold, desert world with a thin atmosphere.",
            facts = listOf(
                "Mars is about half the diameter of Earth.",
                "A day on Mars is about 24.6 hours.",
                "Mars has two small moons: Phobos and Deimos.",
                "Olympus Mons on Mars is the tallest volcano in the solar system."
            ),
            imageRes = R.drawable.mars,
            sourceUrl = "https://science.nasa.gov/mars/facts/"
        ),

        SolarBody(
            id = "jupiter",
            name = "Jupiter",
            type = "Planet",
            moons = 95,
            meanRadiusKm = 69911.0,
            gravity = 24.8,
            summary = "Jupiter is the largest planet in the solar system and a massive gas giant.",
            facts = listOf(
                "Jupiter is the largest planet in the solar system.",
                "Jupiter is a gas giant made mostly of hydrogen and helium.",
                "Jupiter has a giant storm called the Great Red Spot.",
                "Jupiter has a faint ring system and many moons."
            ),
            imageRes = R.drawable.jupiter,
            sourceUrl = "https://science.nasa.gov/jupiter/jupiter-facts/"
        ),

        SolarBody(
            id = "saturn",
            name = "Saturn",
            type = "Planet",
            moons = 146,
            meanRadiusKm = 58232.0,
            gravity = 10.4,
            summary = "Saturn is a gas giant famous for its spectacular system of icy rings.",
            facts = listOf(
                "Saturn is the sixth planet from the Sun.",
                "Saturn is the second-largest planet in the solar system.",
                "Saturn has a spectacular system of rings made mostly of ice and rock.",
                "Saturn is the least dense planet in the solar system."
            ),
            imageRes = R.drawable.saturn,
            sourceUrl = "https://science.nasa.gov/saturn/facts/"
        ),

        SolarBody(
            id = "uranus",
            name = "Uranus",
            type = "Planet",
            moons = 28,
            meanRadiusKm = 25362.0,
            gravity = 8.7,
            summary = "Uranus is a cold, blue-green ice giant that rotates almost on its side.",
            facts = listOf(
                "Uranus is the seventh planet from the Sun.",
                "Uranus rotates at an angle of about 98 degrees, making it appear to spin sideways.",
                "Uranus has 13 faint rings and 28 known moons.",
                "Methane in its atmosphere gives Uranus its blue-green color."
            ),
            imageRes = R.drawable.uranus,
            sourceUrl = "https://science.nasa.gov/uranus/facts/"
        ),

        SolarBody(
            id = "neptune",
            name = "Neptune",
            type = "Planet",
            moons = 16,
            meanRadiusKm = 24622.0,
            gravity = 11.2,
            summary = "Neptune is a dark, cold ice giant with powerful supersonic winds.",
            facts = listOf(
                "Neptune is the eighth and most distant planet from the Sun.",
                "Neptune has the fastest winds in the solar system.",
                "Neptune is more than 30 times farther from the Sun than Earth.",
                "Neptune was the first planet discovered using mathematical predictions."
            ),
            imageRes = R.drawable.neptune,
            sourceUrl = "https://science.nasa.gov/neptune/neptune-facts/"
        )
    )
}

