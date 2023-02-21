package client.gui.multicast;

import javafx.collections.transformation.SortedList;
import javafx.scene.control.Label;

public interface IMulticastClient {

    void run();

    SortedList<Label> getAvailableServers();

    void closeMulicastClient();
}
