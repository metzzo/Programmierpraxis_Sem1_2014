package at.pwd.asciishop.app;

import at.pwd.asciishop.helper.IOHelper;
import at.pwd.asciishop.helper.Strings;

import java.util.LinkedList;
import java.util.List;

/**
 * This class aims to support all the operations required for "AsciiShop"
 *
 * Created by Robert on 06.11.2014.
 */
public class ShopApp implements IOHelper.IOResultCallback {
    private IOHelper io;
    private AsciiImage image;
    private List<Integer> params;
    private int currentPosition = 0;

    public ShopApp() {
        this.io = new IOHelper();
    }
    public ShopApp(final IOHelper ioHelper) {
        this.io = ioHelper;
    }

    public void run() {
        // read expected lines
        boolean unexpected =  this.io.readString(new IOHelper.IOResultCallback() {
            @Override
            public boolean postResult(String command, IOHelper helper) {
                if (command.toLowerCase().equals("read")) {
                    return helper.readNumeric(this);
                } else {
                    return true;
                }
            }

            @Override
            public boolean postResult(int result, IOHelper helper) {
                ShopApp.this.image = new AsciiImage(result);
                return false;
            }

            @Override
            public boolean postResult(double result, IOHelper helper) { return true; }
        });
        if (unexpected) {
            this.io.writeLine(Strings.INVALID_INPUT);
            this.image = null;
            return;
        }

        // read image
        final int height = this.image.getHeight();
        this.currentPosition = 0;
        for (int i = 0; i < height; i++) {
            unexpected = this.io.readString(new IOHelper.IOResultCallback() {
                @Override
                public boolean postResult(String result, IOHelper helper) {
                    final AsciiImage newImage = image.updateRow(currentPosition, result);
                    if (image != newImage) {
                        image = newImage;
                        ShopApp.this.currentPosition++;
                        return false;
                    } else {
                        return true;
                    }
                }

                @Override
                public boolean postResult(int result, IOHelper helper) { return true; }

                @Override
                public boolean postResult(double result, IOHelper helper) { return true; }
            });
            if (unexpected) {
                this.io.writeLine(Strings.INVALID_INPUT);
                return;
            }
        }

        // read fill commands
        unexpected = this.io.readStrings(this);
        if (unexpected) {
            this.io.writeLine(Strings.INVALID_INPUT);
            this.image = null;
            return;
        }
        if (this.image != null) {
            this.io.writeLine(this.image.toString());
            this.io.writeLine(this.image.getWidth() + " " + this.image.getHeight());
        }
    }

    @Override
    public boolean postResult(String result, IOHelper helper) {
        if (result.toLowerCase().equals("fill")) {
            params = new LinkedList<Integer>();

            if (this.io.readNumeric(this)) return true;
            if (this.io.readNumeric(this)) return true;
            if (this.io.readString(this)) return true;

            params = null;

            return false;
        } else if (result.toLowerCase().equals("uniquechars")) {
            final AsciiImageOperation operation = new AsciiImageOperation(this.image);
            this.io.writeLine("" + operation.getUniqueChars());

            return false;
        } else if (result.toLowerCase().equals("flip-v")) {
            final AsciiImageOperation operation = new AsciiImageOperation(this.image);
            this.image = operation.flipV();
        } else if (result.toLowerCase().equals("transpose")) {
            final AsciiImageOperation operation = new AsciiImageOperation(this.image);
            this.image = operation.transpose();
        } else if (params != null && params.size() == 2) {
            final int x  = params.get(0);
            final int y  = params.get(1);
            final char c = result.charAt(0);

            final AsciiImageOperation renderer = new AsciiImageOperation(this.image);
            final AsciiImage newImage           = renderer.fill(x, y, c);
            if (newImage == this.image) {
                this.io.writeLine(Strings.INVALID_OPERATION);
                this.image = null;
            } else {
                this.image = newImage;
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean postResult(int result, IOHelper helper) {
        if (params != null && params.size() < 2) {
            params.add(result);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean postResult(double result, IOHelper helper) {
        return true;
    }
}
