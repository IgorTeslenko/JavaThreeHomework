package ru.geekbrains.java3.network.client.controllers;

import ru.geekbrains.java3.network.client.NetworkChatClient;

import java.util.List;

public interface Controller {

    void appendMessage(String s);

    void updateUserList(List<String> list);

    NetworkChatClient getClientApp();
}
