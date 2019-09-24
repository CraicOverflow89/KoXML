package craicoverflow89.koxml

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class MainTest {

    @Test
    fun test1() {

        // Mock Input
        KoXML.parseText(object {}.javaClass.getResource("/test1.xml").readText()).apply {

            // Contains Version
            Assert.assertTrue(this.getAttributes().contains("version"))

            // Validate Version
            Assert.assertEquals(this.getAttributes()["version"], "1.0")

            // Contains Encoding
            Assert.assertTrue(this.getAttributes().contains("encoding"))

            // Validate Encoding
            Assert.assertEquals(this.getAttributes()["encoding"], "UTF-8")
        }
    }

}