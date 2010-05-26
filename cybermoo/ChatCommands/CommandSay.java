package cybermoo.ChatCommands;

import cybermoo.Scene;
import cybermoo.SceneHandler;
import cybermoo.Server;
import cybermoo.ThreadedClient;

public class CommandSay implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        if (arguments != null) {
            String temp = "";
            for (int i = 0; i < arguments.length; i++) {
                temp += " " + arguments[i];
            }
            String message = temp.trim();
            Scene location = SceneHandler.getInstance().getScenes().get(source.getPlayer().getLocation());
            for (int i = 0; i < location.getPlayers().size(); i++) {
                if (location.getPlayers().get(i).getClient() != null) {
                    location.getPlayers().get(i).getClient().sendText(source.getPlayer().getName() + " says \"" + message + "\"");
                }
            }
        } else {
            source.sendText("What are you trying to say? say <message>");
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() != null;
    }

    public String getHelp() {
        return "say <Message>\nBroadcasts your message to all nearby players";
    }
}
