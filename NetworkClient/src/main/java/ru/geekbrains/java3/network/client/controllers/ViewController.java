package ru.geekbrains.java3.network.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import ru.geekbrains.java3.network.client.NetworkChatClient;
import ru.geekbrains.java3.network.client.models.Network;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ViewController implements Controller {

    @FXML
    public ListView<String> userList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    private Network network;

    private String selectedRecipient;

    private final Path historyFile = Paths.get("NetworkClient/src/main/resources/history.txt");

    @FXML
    public void initialize() {
        sendButton.setOnAction(event -> sendMessage());
        textField.setOnAction(event -> sendMessage());

        userList.setCellFactory(lv -> {
            MultipleSelectionModel<String> selectionModel = userList.getSelectionModel();
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                userList.requestFocus();
                if (!cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (selectionModel.getSelectedIndices().contains(index)) {
                        selectionModel.clearSelection(index);
                        selectedRecipient = null;
                    } else {
                        selectionModel.select(index);
                        selectedRecipient = cell.getItem();
                    }
                    event.consume();
                }
            });
            return cell;
        });

        loadHistory();
    }

    private void sendMessage() {
        String message = textField.getText();
        appendMessage("You: " + message);
        textField.clear();

        try {
            if (selectedRecipient != null) {
                network.sendPrivateMessage(message, selectedRecipient);
            } else {
                network.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "Failed to send message";
            NetworkChatClient.showNetworkError(e.getMessage(), errorMessage);
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void loadHistory() {
        try {
            if (!Files.exists(historyFile)) {
                Files.createFile(historyFile);
            }
            List<String> lines = Files.readAllLines(historyFile, StandardCharsets.UTF_8);
            String collect = String.join("\n", lines);
            chatHistory.appendText(collect);
            chatHistory.appendText("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void appendMessage(String message) {
        String timestamp = DateFormat.getInstance().format(new Date());
        String formattedMessage = timestamp +
                System.lineSeparator() +
                message +
                System.lineSeparator() +
                System.lineSeparator();
        chatHistory.appendText(formattedMessage);
    }

    public void updateUserList(List<String> users) {
        userList.setItems(FXCollections.observableArrayList(users));
    }

    @Override
    public NetworkChatClient getClientApp() {
        return null;
    }
}