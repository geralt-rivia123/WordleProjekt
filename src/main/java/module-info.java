module fk.wordleprojekt {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens fk.wordleprojekt to javafx.fxml;
    exports fk.wordleprojekt;
    exports fk.wordleprojekt.controllers;
    opens fk.wordleprojekt.controllers to javafx.fxml;
    exports fk.wordleprojekt.data;
    opens fk.wordleprojekt.data to javafx.fxml;
    exports fk.wordleprojekt.data.characters;
    opens fk.wordleprojekt.data.characters to javafx.fxml;
}