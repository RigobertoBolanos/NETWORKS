import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
	
	/**
	 * Puerto por donde el servidor atiende a los clientes
	 */
	public static final int PORT = 8000;
	/**
	 * El servidor dispone de un serversocket, para permitir la conexion a los clientes
	 */
	private static  ServerSocket serverSocket;
	/**
	 * El servidor dispone de un socket para atender a cada cliente por individual
	 */
	private static Socket socket;

	public static void main(String[] args) 
	{
		DataInputStream in;
		DataOutputStream out;
		
		try 
		{
			serverSocket = new ServerSocket();
			System.out.println("Esperando solicitudes de cliente\n");
			
			while(true) 
			{
				socket = serverSocket.accept();
				System.out.println("El cliente se ha conectado!\n");
				
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				
				String solicitud = in.readUTF();
				System.out.println("El mensaje enviado por el cliente fue : " + solicitud);
				
				String respuestaServer = encriptar(solicitud);
				out.writeUTF(respuestaServer);
				socket.close();
				System.out.println("::El cliente fue desconectado del server::");
			
			}
		} catch (IOException e) { e.printStackTrace(); }	
	}
	
	private static String encriptar(String solicitud) {
		
		char[] cadena = solicitud.trim().toCharArray();
		String  mensaje = "";
		for (char c : cadena) 
		{
			mensaje += new Character((char) (c + 2)) + "";
		}
		
		return mensaje;
	}

}
