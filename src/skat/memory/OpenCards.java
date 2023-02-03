package skat.memory;

import java.util.ArrayList;
import java.util.Arrays;

public class OpenCards implements IOpenCards {

    private ArrayList<String> openCards;

    public OpenCards(String[] openCards) {
        this.openCards = new ArrayList<>(Arrays.asList(openCards));
    }

    @Override
    public ArrayList<String> getOpenCards() {
        return openCards;
    }

    @Override
    public void removeCard(String cardUrl) {
        openCards.remove(cardUrl);
    }

    @Override
    public void emptyOpenCards() {
        openCards.clear();
    }
}
