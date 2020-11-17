package ru.geekbrains.java3.network.server.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.java3.network.clientserver.Command;
import ru.geekbrains.java3.network.server.chat.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private final ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(MyServer.class);


    public MyServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        logger.info("Server has been started.");
        DBConnect.connect();
        try {
            while (true) {
                waitAndProcessNewConnection();
            }
        } catch (IOException e) {
            logger.error("Failed to accept new connection.");
            e.printStackTrace();
        } finally {
            serverSocket.close();
            logger.info("Server has been closed.");
            DBConnect.disconnect();
        }
    }

    private void waitAndProcessNewConnection() throws IOException {
        System.out.println("Awaiting for new connections...");

        Socket clientSocket = serverSocket.accept();
        logger.info("Client has been connected.");

        processClientConnection(clientSocket);
    }

    private void processClientConnection(Socket clientSocket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public void broadcastMessage(ClientHandler sender, Command command) throws IOException {
        for (ClientHandler client : clients) {
            if (client == sender) {
                continue;
            }
            client.sendMessage(command);
            logger.info("Client sent a message.");
        }
    }

    public synchronized void subscribe(ClientHandler handler) throws IOException {
        clients.add(handler);
        List<String> usernames = getAllUsernames();
        broadcastMessage(null, Command.updateUserListCommand(usernames));
    }

    public synchronized void unsubscribe(ClientHandler handler) throws IOException {
        clients.remove(handler);
        List<String> usernames = getAllUsernames();
        broadcastMessage(null, Command.updateUserListCommand(usernames));
        logger.info("Client has been disconnected.");
    }

    private List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        for (ClientHandler client : clients) {
            usernames.add(client.getUsername());
        }
        return usernames;
    }

    public synchronized boolean isUsernameAlreadyTaken(String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void sendPrivateMessage(String recipient, Command command) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(recipient)) {
                client.sendMessage(command);
            }
        }
    }

}
