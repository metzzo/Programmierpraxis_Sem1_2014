package at.pwd.asciishop.app.operation;

import at.pwd.asciishop.app.ShopApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rfischer on 08.12.14.
 */
public class OperationFactory {
    private class Operations {
        private Map<String, Class<? extends Operation>> commands = new HashMap<String, Class<? extends Operation>>();
    }
    private static OperationFactory factory = null;

    public static OperationFactory instance() {
        if (factory == null) factory = new OperationFactory();
        return factory;
    }

    private Map<ShopApp.ShopStates, Operations> commands;
    private OperationFactory() {
        commands = new HashMap<ShopApp.ShopStates, Operations>();

        final Operations DataModify = new Operations();
        DataModify.commands.put("clear", ClearOperation.class);
        DataModify.commands.put("centroid", CentroidOperation.class);
        DataModify.commands.put("fill", FillOperation.class);
        DataModify.commands.put("grow", GrowOperation.class);
        DataModify.commands.put("straighten", StraightenOperation.class);
        DataModify.commands.put("line", LineOperation.class);
        DataModify.commands.put("load", LoadOperation.class);
        DataModify.commands.put("print", PrintOperation.class);
        DataModify.commands.put("replace", ReplaceOperation.class);
        DataModify.commands.put("symmetric-h", SymmetricOperation.class);
        DataModify.commands.put("transpose", TransposeOperation.class);
        DataModify.commands.put("undo", UndoOperation.class);
        DataModify.commands.put("filter", FilterOperation.class);
        DataModify.commands.put("binary", BinaryOperation.class);
        DataModify.commands.put("histogram", HistogramOperation.class);

        final Operations DataIn = new Operations();
        DataIn.commands.put("create", CreateOperation.class);

        commands.put(ShopApp.ShopStates.DATA_IN, DataIn);
        commands.put(ShopApp.ShopStates.DATA_MODIFY, DataModify);
    }

    public Operation makeCommand(final ShopApp.ShopStates state, final String command) {
        try {
            return commands.get(state).commands.get(command).newInstance();
        } catch (final Exception ex) {
            return new InvalidOperation();
        }
    }
}
