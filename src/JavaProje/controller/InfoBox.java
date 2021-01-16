package JavaProje.controller;

import javafx.scene.control.Alert;
class Info {
    public static void InfoBox(String infoMessage,String titleBar) {
        infoBox(infoMessage,titleBar,null);
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

}