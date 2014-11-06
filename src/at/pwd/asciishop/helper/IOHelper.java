package at.pwd.asciishop.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * This class is used to ease input/output operations on the command line
 * Created by Robert on 06.11.2014.
 */
public class IOHelper {
    private Scanner scanner;
    private InputStream input;
    private OutputStream output;

    public IOHelper() {
        this.scanner = new Scanner(this.input = System.in);
        this.output = System.out;
    }

    public IOHelper(InputStream in, OutputStream out) {
        this.input = in;
        this.output = out;
        this.scanner = new Scanner(in);
    }

    /**
     * Read lines from the command prompt until there is no next line or postResult returns false
     * @return stopped due to unexpected input
     */
    public boolean readLines(final IOResultCallback result) {
        boolean stop = false;
        while (scanner.hasNextLine() && !stop) {
            stop = readLine(result);
        }
        return stop;
    }

    /**
     * Reads a single line from input
     * @return unexpected input?
     */
    public boolean readLine(final IOResultCallback result) {
        final String line = scanner.nextLine();
        return result.postResult(line, this);
    }

    /**
     * writes a line to the output
     */
    public void writeLine(final String line) {
        try {
            output.write((line + "\n").getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e); // rethrow
        }
    }

    /**
     * Callback used to notify whether a new data input has arrived
     */
    public interface IOResultCallback {
        public boolean postResult(final String result, final IOHelper helper);
    }
}
