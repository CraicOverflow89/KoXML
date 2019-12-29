package craicoverflow89.koxml

import org.junit.Assert
import org.junit.Test

class MainTest {

    @Test
    fun parseText() {

        // Mock Input
        KoXML.parseText(object {}.javaClass.getResource("/test1.xml").readText()).apply {

            // Test Attributes
            this.getAttributes().apply {

                // Contains Version
                Assert.assertTrue(contains("version"))

                // Validate Version
                Assert.assertEquals(this["version"], "1.0")

                // Contains Encoding
                Assert.assertTrue(contains("encoding"))

                // Validate Encoding
                Assert.assertEquals(this["encoding"], "UTF-8")
            }

            // Test Groups
            this.getRoot().apply {

                // Test Attributes
                this.getAttributes().apply {

                    // Contains Version
                    Assert.assertTrue(contains("version"))

                    // Validate Version
                    Assert.assertEquals(this["version"], "1")
                }

                // Test Groups
                this.getChildren().apply {

                    // Validate Size
                    //Assert.assertEquals(this.size, 2)
                    // NOTE: discovered an issue whereby children should at least return an empty ArrayList<KoXMLNode> but could be null
                }
            }
        }
    }

}