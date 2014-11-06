package at.pwd.asciishop.app;

import at.pwd.asciishop.helper.IOHelper;
import at.pwd.asciishop.helper.Strings;

/**
 * This class aims to support all the operations required for "AsciiShop"
 *
 * Created by Robert on 06.11.2014.
 */
public class ShopApp implements IOHelper.IOResultCallback {
    private IOHelper io;

    private int lines = 0;
    private String lastLine = null;

    public ShopApp() {
        this.io = new IOHelper();
    }
    public ShopApp(final IOHelper ioHelper) {
        this.io = ioHelper;
    }

    public void run() {
        // read image
        boolean unexpectedStop = this.io.readLines(this);

        // output dimensions
        if (!unexpectedStop) {
            this.io.writeLine(width() + " " + height());
        }
    }

    public int width() {
        return this.lastLine != null ? this.lastLine.length() : 0;
    }

    public int height() {
        return this.lines;
    }

    /**
     * IOResultCallback implementation
     */
    @Override
    public boolean postResult(String result, IOHelper helper) {
       if (this.lastLine != null && this.lastLine.length() != result.length()) {
           helper.writeLine(Strings.INVALID_INPUT);

           return true;
       } else {
           this.lines++;
           this.lastLine = result;

           return false;
       }
    }
}
