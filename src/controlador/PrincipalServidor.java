package controlador;

import java.io.IOException;

import vista.Consola;
import vista.ServidorUDP;

public class PrincipalServidor {

	public static void main(String[] args) throws IOException {

		Consola c = new Consola();
		if (c.inicio()) {
			ServidorUDP servidor = new ServidorUDP();
			servidor.iniciarServidor();
		}

	}
}