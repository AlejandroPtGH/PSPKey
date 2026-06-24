package modelo;

import java.net.InetAddress;

public class Cliente {
    public InetAddress address;
    public int port;

    public Cliente(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }
}