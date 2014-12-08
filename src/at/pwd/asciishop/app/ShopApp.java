package at.pwd.asciishop.app;

import at.pwd.asciishop.app.command.Command;
import at.pwd.asciishop.app.command.CommandFactory;
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
    private AsciiStack imageStack;

    public ShopApp() {
        this(new IOHelper());
    }
    public ShopApp(final IOHelper ioHelper) {
        this.io = ioHelper;
        imageStack = new AsciiStack(3);
    }

    public void run() {
        state = ShopStates.DATA_IN;

        if (this.io.readStrings(this)) {
            this.io.writeLine(Strings.INVALID_INPUT);
            this.image = null;
            return;
        }
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
        return this.image;
    }

    public void setImage(final AsciiImage image) {
        this.image = image;
    }

    public boolean expectParams(final ParamRunner run) {
        this.params = new LinkedList<String>();
        final boolean result = run.run();
        this.params = null;
        return result;
    }

    @Override
    public boolean postResult(final String result, final IOHelper helper) {
        final AsciiImage image = this.image;
        final boolean success = executeCommand(result);

        if (this.image != image && this.image != null && !result.equals("create") && !result.equals("undo")) {
            // image has changed
            imageStack.push(this.image);
        }

        return success;
    }

    public boolean executeCommand(String result) {
        final String strCommand = result.toLowerCase();
        final Command command = CommandFactory.instance().makeCommand(strCommand);

        if (this.state == ShopStates.DATA_IN) {
            this.state = ShopStates.DATA_MODIFY;

            if (!strCommand.equals("create")) return true;

            return command.execute(this);
        }

        if (this.image == null) {
            return false;
        }

        if (command != null && !result.equals("create")) {
            return command.execute(this);
        } else if (params != null) {
            params.add(result);

            return false;
        } else {
            this.io.writeLine(Strings.INVALID_COMMAND);
            this.io.skip();
            return false;
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
        public boolean run();
    }
}
