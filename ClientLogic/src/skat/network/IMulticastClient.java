package skat.network;

import java.util.Queue;

public interface IMulticastClient {

    void run();

    Queue<String> getAvailableServers();

    void closeMulicastClient();
}
