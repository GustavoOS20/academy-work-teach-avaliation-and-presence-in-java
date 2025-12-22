package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.bancodedados.NotesDbServiceDb;
import br.com.projetoa3.modelo.records.Notes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import br.com.projetoa3.modelo.Notas;
import br.com.projetoa3.modelo.Alunos;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaCadastrarNotasController implements Initializable {
    private Alunos alunoSelecionado;

    @FXML
    private Button confirmarCadastro;

    @FXML
    private ListView<Alunos> ListaNomesNotas;

    @FXML
    private TextField cadastrarNotaA1;

    @FXML
    private TextField cadastrarNotaA2;

    @FXML
    private TextField cadastrarNotaA3;

    @FXML
    private Label labelNomeSelecionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListaNomesNotas.setItems(Alunos.getListaObservable());

        ListaNomesNotas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                alunoSelecionado = newVal;

                exibirNotasDoAluno(newVal);
            }
        });

        confirmarCadastro.setOnAction(event -> {
            if (alunoSelecionado != null) {
                cadastrarNotas();
            } else {
                System.out.println("Selecione um aluno da lista.");
            }
        });
    }

    @FXML
    public void cadastrarNotas() {
        try {
            int notaA1 = Integer.parseInt(cadastrarNotaA1.getText());
            int notaA2 = Integer.parseInt(cadastrarNotaA2.getText());
            int notaA3 = Integer.parseInt(cadastrarNotaA3.getText());
            if (notaA1 < 0 || notaA1 > 30 || notaA2 < 0 || notaA2 > 30 || notaA3 < 0 || notaA3 > 40) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "As notas A1 e A2 devem estar entre 0 e 30, e A3 deve estar entre 0 e 40.");
                alert.setTitle("Erro de Validação");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
                alert.showAndWait();
                return;
            }else if (cadastrarNotaA1.getText().isEmpty() || cadastrarNotaA2.getText().isEmpty() || cadastrarNotaA3.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor, preencha todos os campos de notas.");
                alert.setTitle("Erro de Validação");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
                alert.showAndWait();
                return;
            }
            Notes novaNota = new Notes(notaA1, notaA2, notaA3);
            String raStr = String.valueOf(alunoSelecionado.getRa());
            String chaveTurma = raStr + "-" + alunoSelecionado.getTurma();
                    Notas.adicionarNota(chaveTurma, novaNota);
                        NotesDbServiceDb manager = new NotesDbServiceDb();
            manager.inserirNotas(
                    chaveTurma,
                    novaNota.getNotaA1(),
                    novaNota.getNotaA2(),
                    novaNota.getNotaA3(),
                    novaNota.getSomaNota(),
                    novaNota.getStatus()
            );
                cadastrarNotaA1.clear();
            cadastrarNotaA2.clear();
            cadastrarNotaA3.clear();

            exibirNotasDoAluno(alunoSelecionado);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Notas cadastradas com sucesso para o aluno: " + alunoSelecionado.getNome());
            alert.setTitle("Cadastro de Notas");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
        } catch (NumberFormatException e) {
            System.out.println("Erro: Insira apenas números válidos.");

        }
    }

    @FXML
    private void exibirNotasDoAluno(Alunos aluno) {

        labelNomeSelecionado.setText(aluno.getNome());


        Notas notas = Notas.getNotaPorAluno(aluno.getRa()+"-"+aluno.getTurma());
        if (notas != null) {
            cadastrarNotaA1.setText(String.valueOf(notas.getNotaA1()));
            cadastrarNotaA2.setText(String.valueOf(notas.getNotaA2()));
            cadastrarNotaA3.setText(String.valueOf(notas.getNotaA3()));
        } else {
            cadastrarNotaA1.clear();
            cadastrarNotaA2.clear();
            cadastrarNotaA3.clear();
        }
    }
}