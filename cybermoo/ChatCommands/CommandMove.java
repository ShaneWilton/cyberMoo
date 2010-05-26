package cybermoo.ChatCommands;

import cybermoo.Scene;
import cybermoo.SceneHandler;
import cybermoo.ThreadedClient;

public class CommandMove implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        if (arguments != null) {
            Scene curLocation = SceneHandler.getInstance().getScenes().get(source.getPlayer().getLocation());
            String arg = arguments[0].toLowerCase();
            String direction = "";
            if (arg.equals("up")) {
                direction = curLocation.getUp();
            } else if (arg.equals("down")) {
                direction = curLocation.getDown();
            } else if (arg.equals("north")) {
                direction = curLocation.getNorth();
            } else if (arg.equals("east")) {
                direction = curLocation.getEast();
            } else if (arg.equals("south")) {
                direction = curLocation.getSouth();
            } else if (arg.equals("west")) {
                direction = curLocation.getWest();
            }
            if (!direction.equals("")) {
                source.getPlayer().setLocation(direction);
                source.getPlayer().sendLocationData();
            } else {
                source.sendText("Please input a valid direction.");
            }
        } else {
            source.sendText("I don't understand, try: move <direction>");
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() != null;
    }

    public String getHelp() {
        return "say <Message>\nBroadcasts your message to all nearby players";
    }
}
