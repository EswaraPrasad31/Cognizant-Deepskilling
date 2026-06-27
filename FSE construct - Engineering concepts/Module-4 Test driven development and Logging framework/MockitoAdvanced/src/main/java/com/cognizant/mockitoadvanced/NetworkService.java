package com.cognizant.mockitoadvanced;

public class NetworkService {

    private NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public String connectToServer() {

        String connection = networkClient.connect();

        return "Connected to " + connection;
    }

}