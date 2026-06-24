package vista;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {

    public void iniciarCliente() {

        try {

            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            int puertoServidor = 49171;

            Scanner sc = new Scanner(System.in);

            //System.out.println("(Cliente) Escribe 'salir' para terminar");

            while (true) {

                System.out.print("(Cliente) Mensaje: ");
                String mensaje = sc.nextLine();

                byte[] bufferEnvio = mensaje.getBytes();

                DatagramPacket paqueteEnvio = new DatagramPacket(
                        bufferEnvio,
                        bufferEnvio.length,
                        direccionServidor,
                        puertoServidor
                );

                socket.send(paqueteEnvio);

//                if (mensaje.equalsIgnoreCase("salir")) {
//                    break;
//                }

                byte[] bufferRespuesta = new byte[256];
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);

                socket.receive(paqueteRespuesta);

                String respuesta = new String(paqueteRespuesta.getData()).trim();
                System.out.println("(Cliente) Servidor responde: " + respuesta);

//                if (respuesta.equalsIgnoreCase("salir")) {
//                    break;
//                }
            }

            //socket.close();
           // sc.close();
//            System.out.println("(Cliente) Socket cerrado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}