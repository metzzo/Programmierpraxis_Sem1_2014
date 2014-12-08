package at.pwd.asciishop.app.command;

import at.pwd.asciishop.app.AsciiImageOperation;
import at.pwd.asciishop.app.ShopApp;

import java.util.LinkedList;

/**
 * Created by rfischer on 08.12.14.
 */
public class LoadCommand implements Command {
    private String endMarker;

    @Override
    public boolean execute(final ShopApp app) {
        if (app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public boolean run() {
                if (app.io().readString(app)) return true;
                endMarker = app.params().get(0);
                return false;
            }
        })) return false;


        return app.expectParams(new ShopApp.ParamRunner() {
            @Override
            public boolean run() {
                if (app.io().readHereDoc(endMarker, app)) return true;

                final AsciiImageOperation operation = new AsciiImageOperation(app.image());
                app.setImage(operation.load(app.params()));

                if (app.image() == null) return true;

                return false;
            }
        });
    }
}
