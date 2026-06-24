package vista;
 
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class ServidorUDP {

	public void iniciarServidor() {

		DatagramSocket socket = null;
		Scanner sc = new Scanner(System.in);

		try {

			System.out.println("(Servidor) Creando socket...");
			socket = new DatagramSocket(49171);
			System.out.println("(Servidor) Esperando mensajes...");

			while (true) {

				byte[] buffer = new byte[256];
				DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);

				socket.receive(paqueteEntrada);

				String mensajeCliente = new String(paqueteEntrada.getData()).trim();
				System.out.println("(Servidor) Cliente dice: " + mensajeCliente);

				// escribirFichero("Cliente: " + mensajeCliente);

				System.out.print("(Servidor) Escribe respuesta: ");
				String respuestaServidor = sc.nextLine();

				byte[] bufferRespuesta = respuestaServidor.getBytes();

				DatagramPacket paqueteSalida = new DatagramPacket(bufferRespuesta, bufferRespuesta.length,
						paqueteEntrada.getAddress(), paqueteEntrada.getPort());

				socket.send(paqueteSalida);

				// escribirFichero("Servidor: " + respuestaServidor);

//                if (respuestaServidor.equalsIgnoreCase("salir")) {
//                    escribirFichero("Conversación finalizada ");
//                    break;
//                }
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
//        } finally {
//
//            if (socket != null && !socket.isClosed()) {
//                socket.close();
//            }
//
//            sc.close();
//            System.out.println("(Servidor) Socket cerrado");
//        }
	}

//    public void escribirFichero(String texto) {
//
//        try {
//            FileWriter fichero = new FileWriter("conversacion.txt", true);
//            PrintWriter pw = new PrintWriter(fichero);
//
//            pw.println(texto);
//            pw.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}