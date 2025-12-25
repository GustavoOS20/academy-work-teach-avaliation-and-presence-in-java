package br.com.projetoa3.gui.fxmlloader;

import br.com.projetoa3.gui.controllers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlLoader {

    @FXML
    public void fxmlLoaderPrincipal(Button botaoEntrar) throws IOException {
        Stage currentStage = (Stage) botaoEntrar.getScene().getWindow();
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaPrincipal.fxml"));
        Parent root = loader.load();
        TelaPrincipalController telaPrincipalController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Lista de notas e Presenças");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 1280, 720));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        currentStage.show();
}
    @FXML
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

    @FXML
    public void fxmlLogin(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(root,460 , 510);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Projeto A3 - Lista de Notas e Presenças");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        primaryStage.show();
    }

    @FXML
    public void abrirTelaCadastro(Stage mainStage, ListView<String> listaDePresenca) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaCadastro.fxml"));
        Parent root = loader.load();
        TelaCadastroController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Aluno");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        listaDePresenca.refresh();
        stage.showAndWait();
    }

    @FXML
    public void abrirTelaNotas(ListView<String> listaNotasId, ListView<String> listaAlunosId, Stage mainStage) throws IOException {
        listaNotasId.getItems().clear();
        listaAlunosId.getSelectionModel().clearSelection();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaCadastrarNotaSeparado.fxml"));
        Parent root = loader.load();
        TelaCadastrarNotasController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Notas");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        listaNotasId.refresh();
        stage.showAndWait();
    }

    @FXML
    public void abrirTelaRemoverAluno(Stage mainStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaRemoverAluno.fxml"));
        Parent root = loader.load();
        RemoverAlunoControllers controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Remover Aluno");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 600, 400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    public void adicionarTurma(Stage mainStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdicionarTurma.fxml"));
        Parent root = loader.load();
        TelaAdicionarTurmaController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Turma");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 600, 380));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    public void sairDaConta(Label RAL, ListView<String> listaAlunosId) throws IOException {
        try {
            Stage stage = (Stage) RAL.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            loginStage.setScene(new Scene(root, 460, 510));
            loginStage.setResizable(false);
            loginStage.show();
            listaAlunosId.getItems().clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
