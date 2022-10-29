package com.poit.archive.server.handlers;

import com.poit.archive.entity.User;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private User user;

    public ClientHandler(User user) {
        this.user = user;
    }

    public ClientHandler() {
    }

    public ClientHandler(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {

    }
}
