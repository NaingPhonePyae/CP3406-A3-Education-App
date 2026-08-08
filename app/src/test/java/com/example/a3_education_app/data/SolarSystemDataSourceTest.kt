package com.example.a3_education_app.data

import org.junit.Assert
import org.junit.Test

class SolarSystemDataSourceTest {

    @Test
    fun bodies_isNotEmpty() {
        Assert.assertTrue(SolarSystemDataSource.bodies.isNotEmpty())
    }

    @Test
    fun bodies_idsAreUnique() {
        val ids = SolarSystemDataSource.bodies.map { it.id }
        Assert.assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun bodies_containsEarth() {
        Assert.assertTrue(SolarSystemDataSource.bodies.any { it.id == "earth" })
    }
}