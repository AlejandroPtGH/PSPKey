package vista;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Consola {

	private static final String URL = "jdbc:mysql://localhost/psp_exam_bd";
	private static final String USER = "root";
	private static final String PASS = "";

	static Scanner teclado = new Scanner(System.in);

	public int pideInt(String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				return Integer.parseInt(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Debes introducir un número entero.");
			}
		}
	}

	public double pideDouble(String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				String entrada = teclado.nextLine().replace(",", ".");
				return Double.parseDouble(entrada);
			} catch (NumberFormatException e) {
				System.out.println("Número inválido.");
			}
		}
	}

	public float pideFloat(String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				String entrada = teclado.nextLine().replace(",", ".");
				return Float.parseFloat(entrada);
			} catch (NumberFormatException e) {
				System.out.println("Número inválido");
			}
		}
	}

	public String pideTexto(String mensaje) {
		System.out.print(mensaje);
		return teclado.nextLine();
	}

	public boolean inicio() throws IOException {

		int contador = 0;

		do {
			String nombre = pideTexto("Escribe el usuario: ");
			String password = pideTexto("Escribe la Contraseña: ");

			String nombreHash = hashear(nombre);
			String passwordHasheada = hashear(password);
						
			//insertaDatos(nombreHash, passwordHasheada);

			if (existeUsuario(nombreHash)) {
				System.out.println("si existe user");
				if (existePass(passwordHasheada)) {
					System.out.println("si existe pass");
					return true;
				}
			}

			contador++;

			System.out.println("Error numero " + contador);
			if (contador == 3) {
				System.out.println("Adios buen intento!");
			}

		} while (contador < 3);

		return false;
	}

	public String hashear(String frase) {

		try {
			byte[] bytes = frase.getBytes();
			MessageDigest algoritmo;
			algoritmo = MessageDigest.getInstance("SHA-256");
			algoritmo.reset();
			algoritmo.update(frase.getBytes());
			byte[] resumen = algoritmo.digest();
			// System.out.println(Arrays.toString(resumen));

			return Arrays.toString(resumen);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}

//	public boolean insertaDatos(String a, String b) {
//		try {
//			Connection conexion = DriverManager.getConnection(URL, USER, PASS);
//			PreparedStatement consulta = conexion.prepareStatement("INSERT INTO usuarios (user, pass) VALUES (?, ?)");
//			consulta.setString(1, a);
//			consulta.setString(2, b);
//
//			consulta.executeUpdate();
//			conexion.close();
//			return true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}

	public boolean existeUsuario(String usuarioHash) {

		try {
			Connection conexion = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM usuarios where user = ?");
			consulta.setString(1, usuarioHash);
			ResultSet resultado = consulta.executeQuery();

			if (resultado.next()) {
				return true;
			}
			conexion.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean existePass(String contraHash) {

		try {
			Connection conexion = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM usuarios where pass = ?");
			consulta.setString(1, contraHash);
			ResultSet resultado = consulta.executeQuery();

			if (resultado.next()) {
				return true;
			}
			conexion.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

//	public boolean actualizaPassword(String usuario, String nuevaPassword) {
//	    String userHash = hashear(usuario);
//	    String passHash = hashear(nuevaPassword);
//
//	    // Primero comprobamos con el metodo que creamos arriba
//	    if (!existeUsuario(userHash)) {
//	        System.out.println("El usuario no existe, no se puede actualizar.");
//	        return false;
//	    }
//
//	    try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
//	        String sql = "UPDATE usuarios SET pass = ? WHERE user = ?";
//	        PreparedStatement consulta = conexion.prepareStatement(sql);
//	        consulta.setString(1, passHash);
//	        consulta.setString(2, userHash);
//
//	        int filasAfectadas = consulta.executeUpdate();
//	        if (filasAfectadas > 0) {
//	            System.out.println("Contraseña actualizada con éxito.");
//	            return true;
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return false;
//	}
//	
//	public boolean eliminaUsuario(String usuario) {
//	    String userHash = hashear(usuario);
//
//	    try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
//	        String sql = "DELETE FROM usuarios WHERE user = ?";
//	        PreparedStatement consulta = conexion.prepareStatement(sql);
//	        consulta.setString(1, userHash);
//
//	        int filasAfectadas = consulta.executeUpdate();
//	        
//	        if (filasAfectadas > 0) {
//	            System.out.println("Usuario " + usuario + " eliminado.");
//	            return true;
//	        } else {
//	            System.out.println("No se pudo eliminar: el usuario no existe en la base de datos.");
//	            return false;
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//	}
}