import java.awt.*;
import javax.swing.*;


class inframe extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JProgressBar jpb=new JProgressBar();//进度条
	JLabel value=new JLabel();//显示进度条数值标签
	JLabel news=new JLabel();//显示提示信息标签
	public inframe()
	{
		Toolkit kit=Toolkit.getDefaultToolkit();//获取屏幕大小
		Dimension screenSize=kit.getScreenSize();
		int Height=screenSize.height;
		int Width=screenSize.width;
		
		setLocation(Width/2-250,Height/2-80);//框架定位
		setSize(500,160);
		setTitle("正在接收");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);//不允许改变框架大小
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.LEFT,20,30));
		
		jpb.setPreferredSize(new Dimension(360,40));
		jpb.setMinimum(0);				//进度条最小值
		jpb.setMaximum(100);			//进度条最大值
		add(jpb);
		add(value);
		add(news);
	}
}

class outframe extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JProgressBar jpb=new JProgressBar();//进度条
	JLabel value=new JLabel();//显示进度条数值标签
	JLabel news=new JLabel();//显示提示信息标签
	public outframe()
	{
		Toolkit kit=Toolkit.getDefaultToolkit();//获取屏幕大小
		Dimension screenSize=kit.getScreenSize();
		int Height=screenSize.height;
		int Width=screenSize.width;
		
		setLocation(Width/2-250,Height/2-80);//框架定位
		setSize(500,160);
		setTitle("正在发送");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);//不允许改变框架大小
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.LEFT,20,30));
		
		jpb.setPreferredSize(new Dimension(360,40));
		jpb.setMinimum(0);				//进度条最小值
		jpb.setMaximum(100);			//进度条最大值
		add(jpb);
		add(value);
		add(news);
	}
}
