package at.pwd.asciishop.helper

/**
 * Created by Robert on 06.11.2014.
 */
class IOHelperTest extends GroovyTestCase {
    void testReadLines() {
        // arrange
        def bytes  = "hello\nhello\n".getBytes()
        def output = new ByteArrayOutputStream()
        def input  = new ByteArrayInputStream(bytes)
        def helper = new IOHelper(input, output)

        // act / assert
        assert !helper.readLines({String line, IOHelper h2 -> assert line == "hello"; false } as IOHelper.IOResultCallback)
    }

    void testReadLine() {
        // arrange
        def bytes  = "hello\n".getBytes()
        def output = new ByteArrayOutputStream()
        def input  = new ByteArrayInputStream(bytes)
        def helper = new IOHelper(input, output)

        // act / assert
        assert !helper.readLine({String line, IOHelper h2 -> assert line == "hello"; false } as IOHelper.IOResultCallback)
    }

    void testWriteLine() {
        // arrange
        def bytes  = "hello".getBytes()
        def output = new ByteArrayOutputStream()
        def input  = new ByteArrayInputStream(bytes)
        def helper = new IOHelper(input, output)

        // act
        helper.writeLine(bytes.toString())

        // assert
        assert (bytes.toString() + "\n").equals(output.toString())
    }
}
