package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.ShopApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rfischer on 08.12.14.
 */
public class OperationFactory {
    private static OperationFactory factory = null;

    public static OperationFactory instance() {
        if (factory == null) factory = new OperationFactory();
        return factory;
    }

    private Map<String, Class<? extends Operation>> commands;
    private OperationFactory() {
        commands = new HashMap<String, Class<? extends Operation>>();
        commands.put("clear", ClearOperation.class);
        commands.put("centroid", CentroidOperation.class);
        commands.put("fill", FillOperation.class);
        commands.put("grow", GrowOperation.class);
        commands.put("straighten", StraightenOperation.class);
        commands.put("line", LineOperation.class);
        commands.put("load", LoadOperation.class);
        commands.put("print", PrintOperation.class);
        commands.put("replace", ReplaceOperation.class);
        commands.put("symmetric-h", SymmetricOperation.class);
        commands.put("transpose", TransposeOperation.class);
        commands.put("undo", UndoOperation.class);
        commands.put("filter", FilterOperation.class);
        commands.put("binary", BinaryOperation.class);
        commands.put("create", CreateOperation.class);
        commands.put("save", SaveOperation.class);
        commands.put("search", SearchOperation.class);
        commands.put("printsaved", PrintSavedOperation.class);
    }

    public Operation makeCommand(final String command) {
        try {
            return commands.get(command).newInstance();
        } catch (final Exception ex) {
            return new InvalidOperation();
        }
    }
}
