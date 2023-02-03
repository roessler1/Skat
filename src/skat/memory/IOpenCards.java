package skat.memory;

import java.util.ArrayList;

public interface IOpenCards {

    ArrayList<String> getOpenCards();

    void removeCard(String cardUrl);

    void emptyOpenCards();
}
