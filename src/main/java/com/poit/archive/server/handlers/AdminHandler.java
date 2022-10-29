package com.poit.archive.server.handlers;

import com.poit.archive.entity.User;
import java.net.Socket;

public class AdminHandler implements Runnable {

    private Socket socket;
    private User user;

    public AdminHandler(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
    }

    public AdminHandler(User user) {
        this.user = user;
    }

    public AdminHandler() {
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public AdminHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
