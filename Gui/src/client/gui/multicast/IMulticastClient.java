package client.gui.multicast;

import javafx.scene.control.Label;

import java.util.LinkedList;

public interface IMulticastClient {

    void run();

    LinkedList<Label> getAvailableServers();

    void closeMulicastClient();
}
