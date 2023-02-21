module Skat {
    requires java.logging;
    exports skat.log to Server, Gui;
    exports skat.cards to Server, Gui;
}