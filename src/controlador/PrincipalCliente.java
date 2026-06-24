package controlador;

import vista.ClienteUDP;

public class PrincipalCliente {

    public static void main(String[] args) {

        ClienteUDP cliente = new ClienteUDP();
        cliente.iniciarCliente();

    }
}