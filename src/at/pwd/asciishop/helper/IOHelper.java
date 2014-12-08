package at.pwd.asciishop.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

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
     * Reads lines from the command prompt until there is no next line or postResult returns false
     */
    public boolean readLines(final IOResultCallback result) {
        boolean stop = false;
        while (scanner.hasNextLine() && !stop) {
            stop = readLine(result);
        }
        return stop;
    }

    /**
     * Reads Strings from the command prompt until there is no next line or postResult returns false
     */
    public boolean readStrings(final IOResultCallback result) {
        boolean stop = false;
        while (scanner.hasNext() && !stop) {
            stop = readString(result);
        }
        return stop;
    }

    /**
     * Reads Strings from the command prompt until there is no next line or postResult returns false
     */
    public boolean readNumerics(final IOResultCallback result) {
        boolean stop = false;
        while (scanner.hasNext() && !stop) {
            stop = readNumeric(result);
        }
        return stop;
    }

    /**
     * Reads a double from input
     * @return should the reading process be stopped?
     */
    public boolean readString(final IOResultCallback result) {
        if (scanner.hasNext()) {
            final String value = scanner.next();
            return result.postResult(value, this);
        } else {
            return false;
        }
    }

    /**
     * Reads a single line from input
     * @return should the reading process be stopped?
     */
    public boolean readLine(final IOResultCallback result) {
        if (scanner.hasNextLine()) {
            final String line = scanner.nextLine();
            return result.postResult(line, this);
        } else {
            return false;
        }
    }

    /**
     * Reads a double from input, if input is not a double,
     * @return should the reading process be stopped?
     */
    public boolean readNumeric(final IOResultCallback result) {
        if (scanner.hasNextDouble()) {
            final String stringValue = scanner.next();
            if (stringValue.contains(".") || stringValue.contains(",")) {
                final double value = new Double(stringValue).doubleValue();
                return result.postResult(value, this);
            } else {
                final int value = new Integer(stringValue).intValue();
                return result.postResult(value, this);
            }
        } else {
            return false;
        }
    }

    public boolean readHereDoc(final String end, final IOResultCallback result) {
        List<String> lines = new LinkedList<String>();
        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();
            if (line.equals(end)) {
                return result.postResult(lines);
            } else if (line.length() > 0) {
                lines.add(line);
            }
        }
        return true;
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
     * Skips current line if possible
     */
    public void skip() {
        if (this.scanner.hasNextLine()) {
            this.scanner.nextLine();
        }
    }

    /**
     * Callback used to notify whether a new data input has arrived
     */
    public interface IOResultCallback {
        public boolean postResult(final String result, final IOHelper helper);
        public boolean postResult(final int result, final IOHelper helper);
        public boolean postResult(final double result, final IOHelper helper);
        public boolean postResult(final List<String> lines);
    }
}
