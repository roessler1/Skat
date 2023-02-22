package server.logic;

public enum Bid {

    EIGHTTEEN((short) 18),
    TWENTY((short) 20),
    TWENTYTWO((short) 22),
    TWENTYTHREE((short) 23),
    TWENTYFOUR((short) 24),
    TWENTYSEVEN((short) 27),
    THIRTY((short) 30),
    THIRTYTHREE((short) 33),
    THIRTYFIVE((short) 35),
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
    ONEHUNDREDSEVENTEEN((short) 117),
    ONEHUNDREDTWENTY((short) 120),
    ONEHUNDREDTWENTYONE((short) 121),
    ONHEHUNDREDTWENTYSIX((short) 126),
    ONEHUNDREDTHIRTY((short) 130),
    ONEHUNDREDTHIRTYTWO((short) 132),
    ONEHUNDREDTHRITYFIVE((short) 135),
    ONEHUNDREDFORTY((short) 140),
    ONEHUNDREDFORTYTHREE((short) 143),
    ONEHUNDREDFORTYFOUR((short) 144),
    ONEHUNDREDFIFTY((short) 150),
    ONEHUNDREDFIFTYTHREE((short) 153),
    ONEHUNDREDFIFTYFOUR((short) 154),
    ONEHUNDREDFIFTYSIX((short) 156),
    ONEHUNDREDSIXTY((short) 160),
    ONEHUNDREDSIXTYTWO((short) 162),
    ONEHUNDREDSIXTYFIVE((short) 165),
    ONEHUNDREDSIXTYEIGHT((short) 168),
    ONEHUNDREDSEVENTY((short) 170),
    ONEHUNDREDSEVENTYSIX((short) 176),
    ONEHUNDREDEIGHTY((short) 180),
    ONEHUNDREDEIGHTYSEVEN((short) 187),
    ONEHUNDREDNINETYTWO((short) 192),
    ONEHUNDREDNINETYEIGHT((short) 198),
    TWOHUNDREDFOUR((short) 204),
    TWOHUNDREDSIXTEEN((short) 216),
    TWOHUNDREDFORTY((short) 240),
    TWOHUNDREDSIXTYFOUR((short) 264);

    final short bid;

    Bid(short bid) {
        this.bid = bid;
    }

    public short getBid() {
        return bid;
    }
}
