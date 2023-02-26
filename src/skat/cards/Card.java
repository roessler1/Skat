package skat.cards;

import java.io.Serializable;

public enum Card implements Serializable {
    C_ACE((byte) 0, (byte) 12, (byte) 11, "/assets/texture/c_ace.png"),
    C_KING((byte) 1, (byte) 12, (byte) 4, "/assets/texture/c_king.png"),
    C_QUEEN((byte) 2, (byte) 12, (byte) 3, "/assets/texture/c_queen.png"),
    C_JACK((byte) 3, (byte) 12, (byte) 2, "/assets/texture/c_jack.png"),
    C_TEN((byte) 4, (byte) 12, (byte) 10, "/assets/texture/c_ten.png"),
    C_NINE((byte) 5, (byte) 12, (byte) 0, "/assets/texture/c_nine.png"),
    C_EIGHT((byte) 6, (byte) 12, (byte) 0, "/assets/texture/c_eight.png"),
    C_SEVEN((byte) 7, (byte) 12, (byte) 0, "/assets/texture/c_seven.png"),
    S_ACE((byte) 8, (byte) 11, (byte) 11, "/assets/texture/s_ace.png"),
    S_KING((byte) 9, (byte) 11, (byte) 4, "/assets/texture/s_king.png"),
    S_QUEEN((byte) 10, (byte) 11, (byte) 3, "/assets/texture/s_queen.png"),
    S_JACK((byte) 11, (byte) 11, (byte) 2, "/assets/texture/s_jack.png"),
    S_TEN((byte) 12, (byte) 11, (byte) 10, "/assets/texture/s_ten.png"),
    S_NINE((byte) 13, (byte) 11, (byte) 0, "/assets/texture/s_nine.png"),
    S_EIGHT((byte) 14, (byte) 11, (byte) 0, "/assets/texture/s_eight.png"),
    S_SEVEN((byte) 15, (byte) 11, (byte) 0, "/assets/texture/s_seven.png"),
    H_ACE((byte) 16, (byte) 10, (byte) 11, "/assets/texture/h_ace.png"),
    H_KING((byte) 17, (byte) 10, (byte) 4, "/assets/texture/h_king.png"),
    H_QUEEN((byte) 18, (byte) 10, (byte) 3, "/assets/texture/h_queen.png"),
    H_JACK((byte) 19, (byte) 10, (byte) 2, "/assets/texture/h_jack.png"),
    H_TEN((byte) 20, (byte) 10, (byte) 10, "/assets/texture/h_ten.png"),
    H_NINE((byte) 21, (byte) 10, (byte) 0, "/assets/texture/h_nine.png"),
    H_EIGHT((byte) 22, (byte) 10, (byte) 0, "/assets/texture/h_eight.png"),
    H_SEVEN((byte) 23, (byte) 10, (byte) 0, "/assets/texture/h_seven.png"),
    D_ACE((byte) 24, (byte) 9, (byte) 11, "/assets/texture/d_ace.png"),
    D_KING((byte) 25, (byte) 9, (byte) 4, "/assets/texture/d_king.png"),
    D_QUEEN((byte) 26, (byte) 9, (byte) 3, "/assets/texture/d_queen.png"),
    D_JACK((byte) 27, (byte) 9, (byte) 2, "/assets/texture/d_jack.png"),
    D_TEN((byte) 28, (byte) 9, (byte) 10, "/assets/texture/d_ten.png"),
    D_NINE((byte) 29, (byte) 9, (byte) 0, "/assets/texture/d_nine.png"),
    D_EIGHT((byte) 30, (byte) 9, (byte) 0, "/assets/texture/d_eight.png"),
    D_SEVEN((byte) 31, (byte) 9, (byte) 0, "/assets/texture/d_seven.png");

    private final byte id;
    private final byte color;
    private final byte value;
    private final String url;

    Card(byte id, byte color, byte value, String url) {
        this.id = id;
        this.color = color;
        this.value = value;
        this.url = url;
    }

    public byte getId() {
        return id;
    }

    public byte getColor() {
        return color;
    }

    public byte getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }
}
