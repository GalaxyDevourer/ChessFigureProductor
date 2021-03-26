package chessfigureproductor.models.utils.window;

import javafx.scene.control.Alert;

public interface WindowsUtils {
    default void dialogWarningMessage(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
