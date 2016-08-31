import java.io.DataInputStream;  
import java.io.DataOutputStream;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;  
import java.net.InetSocketAddress;
import java.net.ServerSocket;  
import java.net.Socket;
  
  
/** 
 * 接收文件服务 
 * @author admin_Hzw 
 * 
 */  
class Server extends Thread
{
    static public String pathdir;
    public void setPathdir(String s)//设置文件路径方法
    {
    	pathdir=s;
    }
    public void run()//run方法
    {
		ServerSocket server = null;
		try
		{
			server = new ServerSocket(9999);
	        while (!Thread.currentThread().isInterrupted())//如果线程没有中断
	        {
	            Socket socket = server.accept();//等待连接，一直阻塞
	            byte[] inputByte = null;//定义输入字节数组
	            int length = 0;//定义长度
	            double sumL=0;//进度条参数
	            long l;//文件长度
	            DataInputStream dis = null;  
	            FileOutputStream fos = null;  
	            String filePath;
	            try
	            {  
	                dis = new DataInputStream(socket.getInputStream());
	                File f = new File(pathdir);//新建文件f
	                if(!f.exists())
	                {  
	                    f.mkdir();
	                }  
	                filePath=pathdir+"/"+dis.readUTF();//得到文件路径
	                l=dis.readLong();//获取文件大小
	                fos = new FileOutputStream(new File(filePath));//文件输出流
	                inputByte = new byte[1024];
	                inframe in = new inframe();
	                in.news.setText("正在接收文件...   "+filePath);//进度条显示框
	                while ((length = dis.read(inputByte, 0, inputByte.length)) > 0)
	                { 
	                	sumL += length;
	                	in.value.setText("已传输："+(int)((sumL/(l*1024))*100)+"%");
	                	in.jpb.setValue((int)((sumL/(l*1024))*100)+1);
	                    fos.write(inputByte, 0, length);  
	                    fos.flush();
	                }  
	                in.dispose();//关闭进度条框
	            }
	            finally//关闭流及socket
	            {  
	                if (fos != null)  
	                    fos.close();  
	                if (dis != null)  
	                    dis.close();  
	                if (socket != null)  
	                    socket.close();   
	            }
	        }
		}
        catch (Exception e)
        {  
        	Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}  


/** 
 * 文件发送客户端主程序 
 * @author admin_Hzw 
 * 
 */  
class Client implements Runnable
{
	private String filePath,ipArr;//定义文件路径和ip
	private int port;
	public void setFilepath(String s)
	{
		this.filePath=s;
	}
	public void setIpArr(String s)
	{
		this.ipArr=s;
	}
	public void setPort(int p)
	{
		this.port = p;
	}
	public void run()
	{
		try
		{
			int length = 0;  
			double sumL = 0 ;  
	        byte[] sendBytes = null;  
	        Socket socket = null;  
	        DataOutputStream dos = null;  
	        FileInputStream fis = null;  
	        outframe out=new outframe();//进度条显示
	        try
	        {  
	            File file = new File(filePath); //要传输的文件路径  
	            long l = file.length();   
	            socket = new Socket();    
	            socket.connect(new InetSocketAddress(ipArr,port));//192.168.191.2
	            dos = new DataOutputStream(socket.getOutputStream());  
	            fis = new FileInputStream(file);        
	            sendBytes = new byte[1024];
	            dos.writeUTF(file.getName());//传递文件名
	            dos.flush();
	            dos.writeLong((long)file.length()/1024+1);
	            dos.flush();
	            
	            out.news.setText("正在发送文件...   "+filePath); 
	            while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0)
	            {  
	                sumL += length;    
	                out.value.setText("已传输："+(int)((sumL/l)*100)+"%");
	                out.jpb.setValue((int)((sumL/l)*100));
	                dos.write(sendBytes, 0, length);
	                dos.flush();  
	            } 
	            out.dispose();
	        }
	        catch (Exception e)
	        {  
	        	out.news.setText("客户端文件传输异常");  
	            e.printStackTrace();
	        }
	        finally
	        {    
	            if (dos != null)
	            	dos.close();
	            if (fis != null)
					fis.close(); 
	            if (socket != null)
						socket.close();
	        }
        }
        catch (Exception e)
        {
        	Thread.currentThread().interrupt();
            e.printStackTrace();
        }
	}
}