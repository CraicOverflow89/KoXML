package craicoverflow89.koxml

import org.junit.Assert
import org.junit.Test

class MainTest {

    @Test
    fun test1() {

        // Mock Input
        KoXML.parseText(object {}.javaClass.getResource("/test1.xml").readText()).apply {

            // Contains Version
            Assert.assertEquals(this.getAttributes().contains("version"), true)

            // Validate Version
            Assert.assertEquals(this.getAttributes()["version"], "1.0")

            // Contains Encoding
            Assert.assertEquals(this.getAttributes().contains("encoding"), true)

            // Validate Encoding
            Assert.assertEquals(this.getAttributes()["encoding"], "UTF-8")
        }
    }

}