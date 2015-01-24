package at.pwd.asciishop.app;

import at.pwd.asciishop.app.operation.Operation;
import at.pwd.asciishop.app.operation.OperationException;
import at.pwd.asciishop.app.operation.OperationFactory;
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
    public enum ShopStates {
        DATA_IN, DATA_MODIFY
    }

    private IOHelper io;
    private AsciiImage image, defaultImage;
    private List<String> params;
    private ShopStates state;
    private AsciiStack imageStack;

    public ShopApp() {
        this(new IOHelper());
    }
    public ShopApp(final IOHelper ioHelper) {
        this.io = ioHelper;
        imageStack = new AsciiStack();
    }

    public void run() {
        state = ShopStates.DATA_IN;

        this.io.readStrings(this);
    }

    public List<String> params() {
        return this.params;
    }

    public IOHelper io() {
        return this.io;
    }

    public AsciiStack imageStack() {
        return imageStack;
    }

    public AsciiImage image() {
        return this.image != null ? this.image : defaultImage;
    }

    public void setImage(final AsciiImage image) {
        if (defaultImage == null) {
            defaultImage = image;
        }
        this.image = image;
    }

    public void setState(final ShopStates state) { this.state = state; }

    public void expectParams(final ParamRunner run) throws OperationException {
        this.params = new LinkedList<String>();
        try {
            run.run();
        } finally {
            this.params = null;
        }
    }

    @Override
    public boolean postResult(final String result, final IOHelper helper) {
        if (params != null) {
            params.add(result);
        } else {
            try {
                executeCommand(result);
            } catch (final OperationException e) {
                this.io.skip();
                this.io.writeLine(e.getMessage());
                return true;
            }
        }

        return false;
    }

    public void executeCommand(String result) throws OperationException {
        final AsciiImage image = this.image;
        final String strCommand = result.toLowerCase();
        final Operation command = OperationFactory.instance().makeCommand(this.state, strCommand);

        if (command != null) {
            try {
                command.execute(this);
            } catch (final Exception ex) {
                if (ex instanceof OperationException) {
                    throw (OperationException)ex; // rethrow
                } else {
                    throw new OperationException.InvalidOperationException();
                }
            }
        }

        if (this.image != image && this.image != null && command.shouldSaveOnStack()) {
            // image has changed
            imageStack.push(this.image);
        }
    }

    @Override
    public boolean postResult(final int result, final IOHelper helper) {
        if (params != null) {
            params.add("" +result);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean postResult(final double result, final IOHelper helper) {
        return true;
    }

    @Override
    public boolean postResult(final List<String> lines) {
        this.params = lines;
        return false;
    }

    public interface ParamRunner {
        public void run() throws OperationException;
    }
}
