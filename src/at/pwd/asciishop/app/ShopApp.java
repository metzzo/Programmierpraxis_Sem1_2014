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
    private enum ShopStates {
        DATA_IN, DATA_MODIFY
    }

    private IOHelper io;
    private AsciiImage image;
    private List<String> params;
    private ShopStates state;

    public ShopApp() {
        this.io = new IOHelper();
    }
    public ShopApp(final IOHelper ioHelper) {
        this.io = ioHelper;
    }

    public void run() {
        state = ShopStates.DATA_IN;

        if (this.io.readStrings(this)) {
            this.io.writeLine(Strings.INVALID_INPUT);
            this.image = null;
            return;
        }
    }

    @Override
    public boolean postResult(String result, IOHelper helper) {
        if (this.state == ShopStates.DATA_IN) {
            this.state = ShopStates.DATA_MODIFY;

            if (!result.toLowerCase().equals("create")) return true;

            params = new LinkedList<String>();

            if (this.io.readNumeric(this)) return true;
            if (this.io.readNumeric(this)) return true;

            if (params.size() != 2) return true;

            final int width = Integer.valueOf(params.get(0));
            final int height = Integer.valueOf(params.get(1));

            if (width <= 0 || height <= 0) return true;

            this.image = new AsciiImage(width, height);
            final AsciiImageOperation operation = new AsciiImageOperation(this.image);
            this.image = operation.clear();

            params = null;
            return false;
        }

        if (this.image == null) {
            return false;
        }

        if (result.toLowerCase().equals("fill")) {
            params = new LinkedList<String>();

            if (this.io.readNumeric(this)) return true;
            if (this.io.readNumeric(this)) return true;
            if (this.io.readString(this)) return true;

            if (params.size() != 3) return true;

            final int x  = Integer.valueOf(params.get(0));
            final int y  = Integer.valueOf(params.get(1));
            final char c = params.get(2).charAt(0);

            final AsciiImageOperation renderer = new AsciiImageOperation(this.image);
            final AsciiImage newImage          = renderer.fill(x, y, c);
            if (newImage == this.image) {
                this.io.writeLine(Strings.INVALID_OPERATION);
                this.image = null;
            } else {
                this.image = newImage;
            }

            params = null;

            return false;
        } else if (result.toLowerCase().equals("transpose")) {
            final AsciiImageOperation operation = new AsciiImageOperation(this.image);
            this.image = operation.transpose();

            return false;
        } else if (result.toLowerCase().equals("symmetric-h")) {
            final AsciiImageOperation operation = new AsciiImageOperation(this.image);
            this.io.writeLine("" + operation.isSymmetricH());

            return false;
        } else if (result.toLowerCase().equals("clear")) {
            final AsciiImageOperation operation = new AsciiImageOperation(this.image);
            this.image = operation.clear();

            return false;
        } else if (result.toLowerCase().equals("line")) {
            params = new LinkedList<String>();

            if (this.io.readNumeric(this)) return true;
            if (this.io.readNumeric(this)) return true;
            if (this.io.readNumeric(this)) return true;
            if (this.io.readNumeric(this)) return true;
            if (this.io.readString(this)) return true;

            if (params.size() != 5) return true;

            final AsciiImageOperation operation = new AsciiImageOperation(this.image);
            this.image = operation.line(new AsciiImageOperation.Vec(Integer.valueOf(params.get(0)), Integer.valueOf(params.get(1))), new AsciiImageOperation.Vec(Integer.valueOf(params.get(2)), Integer.valueOf(params.get(3))), params.get(4).charAt(0));

            params = null;
            return false;
        } else if (result.toLowerCase().equals("load")) {
            params = new LinkedList<String>();

            if (this.io.readString(this)) return true;
            final String endMarker = params.get(0);
            params.clear();
            if (this.io.readHereDoc(endMarker, this)) return true;

            final AsciiImageOperation operation = new AsciiImageOperation(this.image);
            this.image = operation.load(params);

            params = null;

            if (this.image == null) return true;

            return false;
        } else if (result.toLowerCase().equals("print")) {
            this.io.writeLine(this.image.toString() + '\n');

            return false;
        } else if (result.toLowerCase().equals("replace")) {
            params = new LinkedList<String>();

            if (this.io.readString(this)) return true;
            if (this.io.readString(this)) return true;

            if (params.size() != 2) return true;

            final AsciiImageOperation operation = new AsciiImageOperation(this.image);
            this.image = operation.replace(params.get(0).charAt(0), params.get(1).charAt(0));

            params = null;
            return false;
        } else if (params != null) {
            params.add(result);

            return false;
        } else {
            this.io.writeLine(Strings.INVALID_COMMAND);
            return false;
        }
    }

    @Override
    public boolean postResult(int result, IOHelper helper) {
        if (params != null) {
            params.add("" +result);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean postResult(double result, IOHelper helper) {
        return true;
    }

    @Override
    public boolean postResult(List<String> lines) {
        this.params = lines;
        return false;
    }
}
