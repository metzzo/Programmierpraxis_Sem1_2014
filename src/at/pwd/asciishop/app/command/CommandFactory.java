package at.pwd.asciishop.app.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rfischer on 08.12.14.
 */
public class CommandFactory {
    private static CommandFactory factory = null;

    public static CommandFactory instance() {
        if (factory == null) factory = new CommandFactory();
        return factory;
    }

    private Map<String, Class<? extends  Command>> commands;
    private CommandFactory() {
        commands = new HashMap<String, Class<? extends Command>>();

        commands.put("clear", ClearCommand.class);
        commands.put("centroid", CentroidCommand.class);
        commands.put("create", CreateCommand.class);
        commands.put("fill", FillCommand.class);
        commands.put("grow", GrowCommand.class);
        commands.put("straighten", StraightenCommand.class);
        commands.put("line", LineCommand.class);
        commands.put("load", LoadCommand.class);
        commands.put("print", PrintCommand.class);
        commands.put("replace", ReplaceCommand.class);
        commands.put("symmetric-h", SymmetricCommand.class);
        commands.put("transpose", TransposeCommand.class);
        commands.put("undo", UndoCommand.class);
    }

    public Command makeCommand(final String command) {
        try {
            return commands.containsKey(command) ? commands.get(command).newInstance() : null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
