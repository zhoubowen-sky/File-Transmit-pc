import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;


/**UDP接受线程类*/
public class UdpReceive extends Thread
{
	private DatagramSocket udpSocket;
    private DatagramPacket udpPacket;
    private Socket socket;
    
	public static final int DEFAULT_PORT = 43708;
    
    public void run()
    {
        byte[] data = new byte[256];
        try
        {
            udpSocket = new DatagramSocket(DEFAULT_PORT);
            udpPacket = new DatagramPacket(data, data.length);
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        try
        {
            udpSocket.receive(udpPacket);
            if(udpPacket.getAddress() != null)
            {
            	String ip = udpPacket.getAddress().toString().substring(1);
            	mainFrame.Msg.setText(ip);
            	mainFrame.Send1.setVisible(true);
            	
            	socket = new Socket(ip, 8080);//发起tcp连接
            	socket.close();
            	udpSocket.close();
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
}