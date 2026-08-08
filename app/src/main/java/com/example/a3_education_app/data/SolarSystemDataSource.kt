package com.example.a3_education_app.data

import com.example.a3_education_app.model.SolarBody

object SolarSystemDataSource {
    val bodies = listOf(
        SolarBody("mercury", "Mercury", "Planet", 0, 2439.7, 3.7, "Closest planet to the Sun."),
        SolarBody("venus", "Venus", "Planet", 0, 6051.8, 8.9, "Hottest planet with a thick atmosphere."),
        SolarBody("earth", "Earth", "Planet", 1, 6371.0, 9.8, "Our home planet."),
        SolarBody("mars", "Mars", "Planet", 2, 3389.5, 3.7, "The Red Planet."),
        SolarBody("jupiter", "Jupiter", "Planet", 95, 69911.0, 24.8, "Largest planet; a gas giant."),
        SolarBody("saturn", "Saturn", "Planet", 146, 58232.0, 10.4, "Famous for its rings."),
        SolarBody("uranus", "Uranus", "Planet", 28, 25362.0, 8.7, "Ice giant that rotates on its side."),
        SolarBody("neptune", "Neptune", "Planet", 16, 24622.0, 11.2, "Farthest giant planet from the Sun."),
        SolarBody("moon", "Moon", "Moon", 0, 1737.4, 1.6, "Earth's natural satellite.")
    )
}

