package ru.geekbrains.java3.network.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import ru.geekbrains.java3.network.client.NetworkChatClient;
import ru.geekbrains.java3.network.client.models.Network;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
        StringBuilder sb = new StringBuilder();
        File historyFile = new File("NetworkClient/src/main/resources/history.txt");
        try {
            historyFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(historyFile));
                InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)
        ) {
            int x;
            while (true) {
                x = reader.read();
                if (x == -1) {
                    break;
                } else {
                    sb.append((char) x);
                }
            }
        } catch (IOException e) {
            System.err.println("No messages in history");
            ;
        }
        chatHistory.appendText(sb.toString());
    }

    @Override
    public void appendMessage(String message) {
        String timestamp = DateFormat.getInstance().format(new Date());
        String sb = timestamp +
                System.lineSeparator() +
                message +
                System.lineSeparator() +
                System.lineSeparator();
        chatHistory.appendText(sb);
    }

    public void updateUserList(List<String> users) {
        userList.setItems(FXCollections.observableArrayList(users));
    }

    @Override
    public NetworkChatClient getClientApp() {
        return null;
    }
}