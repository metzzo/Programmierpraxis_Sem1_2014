package at.pwd.asciishop.app;

import at.pwd.asciishop.helper.IOHelper;
import at.pwd.asciishop.helper.Strings;

/**
 * This class aims to support all the operations required for "AsciiShop"
 *
 * Created by Robert on 06.11.2014.
 */
public class ShopApp implements IOHelper.IOResultCallback {
    enum PARSE_TYPES {
        INT, DOUBLE, NONE
    }

    private PARSE_TYPES type;
    private double percentage;
    private int bars;
    private IOHelper io;
    private StringRenderer renderer = new StringRenderer();

    public ShopApp() {
        this.io = new IOHelper();
    }
    public ShopApp(final IOHelper ioHelper) {
        this.io = ioHelper;
    }

    public void run() {
        // read image
        boolean unexpectedStop = this.io.readStrings(this);
        if (unexpectedStop) {
            io.writeLine(Strings.INVALID_INPUT);
        }
    }
    /**
     * IOResultCallback implementation
     */
    @Override
    public boolean postResult(final String label, final IOHelper helper) {
        this.type = PARSE_TYPES.NONE;
        boolean result = false;
        if (!helper.readNumeric(this)) {
            switch (this.type) {
                case NONE:
                    result = true;
                    break;
                case INT:
                    helper.writeLine(renderer.drawBar(label, this.bars));
                    break;
                case DOUBLE:
                    helper.writeLine(renderer.drawBar(label, this.percentage));
                    break;
            }
        } else {
            result = true;
        }
        return result;
    }
    @Override
    public boolean postResult(final int value, final IOHelper helper) {
        this.bars = value;
        if (this.bars >= 0 && this.bars <= 30) {
            this.type = PARSE_TYPES.INT;
        }
        return false;
    }
    @Override
    public boolean postResult(final double value, final IOHelper helper) {
        this.percentage = value;
        if (this.percentage >= 0 && this.percentage <= 1) {
            this.type = PARSE_TYPES.DOUBLE;
        }
        return false;
    }
}
