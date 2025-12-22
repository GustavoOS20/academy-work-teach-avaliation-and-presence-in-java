package br.com.projetoa3.gui.fxmlloader;

import br.com.projetoa3.gui.controllers.CadastroProfessorControllers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlLoader {

    public void fxmlLoaderPrincipal(Button botaoEntrar) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaPrincipal.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Lista de notas e Presenças");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 1280, 720));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        Stage currentStage = (Stage) botaoEntrar.getScene().getWindow();
        currentStage.close();
    }

    public void fxmlSingUp(Button botaoCadastrar) throws IOException {
        Stage currentStage = (Stage) botaoCadastrar.getScene().getWindow();
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/layout.fxml"));
        Parent root = loader.load();
        CadastroProfessorControllers controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Cadastro de professor");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 460, 510));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        currentStage.show();
    }

    public void fxmlLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 460, 510));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }
}
