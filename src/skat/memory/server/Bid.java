package skat.memory.server;

public enum Bid {

    EIGHTTEEN((short) 18),
    TWENTY((short) 20),
    TWENTYTWO((short) 22),
    TWENTYTHREE((short) 23),
    TWENTYFOUR((short) 24),
    TWENTYSEVEN((short) 27),
    THIRTY((short) 30),
    THIRTYTHREE((short) 33),
    THRITYFIVE((short) 35),
    THIRTYSIX((short) 36),
    FORTY((short) 40),
    FORTYFOUR((short) 44),
    FORTYFIVE((short) 45),
    FORTYSIX((short) 46),
    FORTYEIGHT((short) 48),
    FIFTY((short) 50),
    FIFTYFOUR((short) 54),
    FIFTYFIVE((short) 55),
    FIFTYNINE((short) 59),
    SIXTY((short) 60),
    SIXTYTHREE((short) 63),
    SIXTYSIX((short) 66),
    SEVENTY((short) 70),
    SEVENTYTWO((short) 72),
    SEVENTYSEVEN((short) 77),
    EIGHTY((short) 80),
    EIGHTYONE((short) 81),
    EIGHTYFOUR((short) 84),
    EIGHTYEIGHT((short) 88),
    NINETY((short) 90),
    NINETYSIX((short) 96),
    NINETYNINE((short) 99),
    ONEHUNDRED((short) 100),
    ONEHUNDREDEIGHT((short) 108),
    ONEHUNDREDTEN((short) 110),
    ONEHUNDREDTWENTY((short) 120),
    ONEHUNDREDTWENTEYONE((short) 121),
    ONEHUNDREDTHIRTYTWO((short) 132),
    ONEHUNDREDFORTYFOUR((short) 144),
    ONEHUNDREDSIXTYEIGHT((short) 168),
    ONEHUNDREDNINETYTWO((short) 192),
    TWOHUNDREDSIXTEEN((short) 216),
    TWOHUNDREDFORTY((short) 240),
    TWOHUNDREDSIXTYFOUR((short) 264);

    private final short value;

    Bid(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
