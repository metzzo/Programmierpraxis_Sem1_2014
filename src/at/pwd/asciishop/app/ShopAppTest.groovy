package at.pwd.asciishop.app

import at.pwd.asciishop.helper.IOHelper

/**
 * Created by Robert on 06.11.2014.
 */
class ShopAppTest extends GroovyTestCase {
    def app, input, output
    def data = [
        [
                input: "WS2009 15",
                output: "WS2009  |###############               |\n"
        ],
        [
                input: "SS2010 3",
                output: "SS2010  |###                           |\n"
        ],
        [
                input: "WS2010 21",
                output: "WS2010  |#####################         |\n"
        ],
        [
                input: "SS2011 14",
                output: "SS2011  |##############                |\n"
        ],
        [
                input: "ColdMonths 0.8",
                output: "ColdMont|########################      |\n"
        ],
        [
                input: "WarmMonths 0.15",
                output: "WarmMont|#####                         |\n"
        ],
        [
                input: "HotMonths 0.05",
                output: "HotMonth|##                            |\n"
        ],
        [
                input: "WS2009 15 SS2010 3 WS2010 0.7 SS2011 0.47",
                output: "WS2009  |###############               |\nSS2010  |###                           |\nWS2010  |#####################         |\nSS2011  |##############                |\n"
        ],
        [
                input: "SS2011 14",
                output: "SS2011  |##############                |\n"
        ],
        [
                input: "5 5\n55 55\n23 23",
                output: "5       |#####                         |\nINPUT ERROR\n"
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
            assert Arrays.equals(output.toByteArray(), expectedOutput.getBytes()): "Output != Expected "+output.toString()+"_"+expectedOutput.toString()
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
