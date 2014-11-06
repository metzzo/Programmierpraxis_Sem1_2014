package at.pwd.asciishop.app

import at.pwd.asciishop.helper.IOHelper

/**
 * Created by Robert on 06.11.2014.
 */
class ShopAppTest extends GroovyTestCase {
    def app, input, output
    def data = [
        [
                input: "aa\naa\n",
                output: "2 2\n"
        ],
        [
                input: "asdfgassdsdads\nasdfgassdsdads\nasdfgassdsdads\nasdfgassdsdads\n",
                output: "14 4\n"
        ],
        [
                input: "asdf\naa\n",
                output: "INPUT MISMATCH\n"
        ]
    ]

    void testData() {
        for (def entry in data) {
            // arrange
            def input           = entry['input']
            def expectedOutput  = entry['output']

            // act
            setup(input)
            app.run()

            // assert
            assert Arrays.equals(output.toByteArray(), expectedOutput.getBytes()): "Output != Expected "+output.toString()+"|"+expectedOutput.toString()
        }
    }

    private void setup(String inp) {
        def  bytes            = inp.getBytes()
        output                = new ByteArrayOutputStream()
        input                 = new ByteArrayInputStream(bytes)

        final IOHelper helper = new IOHelper(input, output)

        app = new ShopApp(helper)
    }
}
