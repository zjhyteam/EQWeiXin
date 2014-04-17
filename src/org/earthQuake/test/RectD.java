package org.earthQuake.test;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

class RectD extends JFrame {
	 private static final long serialVersionUID = 1L;
	 int orgx, orgy, endx, endy;//鼠标按下和释放时x、y轴坐标
	 Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//获得屏幕大小
	 BufferedImage image;//存储整个屏幕
	 BufferedImage tempImage;//缓存
	 BufferedImage saveImage;//保存(截取的区域)
	 Graphics g;
	 @Override
	 public void paint(Graphics g) {
	  RescaleOp ro = new RescaleOp(0.8f, 0, null);//构造一个具有所希望的缩放因子和偏移量的新 RescaleOp
	  tempImage = ro.filter(image, null);//对源 BufferedImage对象image进行重缩放
	  g.drawImage(tempImage, 0, 0, this);
	 }
	 public RectD() {
	  snapshot();
	  setVisible(true);//设置窗口可见
	  setSize(d);//最大化窗口
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认关闭方式
	  this.addMouseListener(new MouseAdapter() {
	   public void mousePressed(MouseEvent e) {
	    orgx = e.getX();
	    orgy = e.getY();
	   }
	  });
	  this.addMouseMotionListener(new MouseMotionAdapter() {
	   public void mouseDragged(MouseEvent e) {
	    endx = e.getX();
	    endy = e.getY();
	    g = getGraphics();
	    g.drawImage(tempImage, 0, 0, RectD.this);
	    int x = Math.min(orgx, endx);
	    int y = Math.min(orgy, endy);
	    int width = Math.abs(endx - orgx)+1;
	    int height = Math.abs(endy - orgy)+1;
	    //加上1，防止width或height为0
	    g.setColor(Color.BLUE);
	    g.drawRect(x-1, y-1, width+1, height+1);
	    //减1，加1都是为了防止图片将矩形框覆盖掉
	    saveImage = image.getSubimage(x, y, width, height);
	    g.drawImage(saveImage, x, y, RectD.this);
	   }
	  });
	  this.addKeyListener(new KeyAdapter() {
	   public void keyReleased(KeyEvent e) {
	    //按Esc键
	    if(e.getKeyCode() == 27) {
	     if(saveImage!=null){
	      saveToFile();//保存图片      
	     }
	     System.exit(0);//退出
	    }
	   }
	  });
	  this.addMouseListener(new MouseListener(){
	   @Override
	   public void mouseClicked(MouseEvent e) {//单击(双击)组件时调用
	    // TODO Auto-generated method stub
	    //双击鼠标左键
	    if(e.getClickCount()==2){
	     if(saveImage!=null){//如果截图了则保存图片到桌面
	      saveToFile();      
	     }
	     System.exit(0);//退出
	    }
	   }
	   @Override
	   public void mouseEntered(MouseEvent e) {//进入组件时调用
	    // TODO Auto-generated method stub
	    
	   }
	   @Override
	   public void mouseExited(MouseEvent e) {//离开组件时调用
	    // TODO Auto-generated method stub
	    
	   }
	   @Override
	   public void mousePressed(MouseEvent e) {//在组件上按下鼠标按键时调用
	    // TODO Auto-generated method stub
	    //点击鼠标右键则退出程序
	    if(e.getModifiers()==MouseEvent.BUTTON3_MASK){
	     System.exit(0);
	    }
	   }
	   @Override
	   public void mouseReleased(MouseEvent e) {//在组件上释放鼠标按钮时调用
	    // TODO Auto-generated method stub
	    
	   }   
	  });
	 }
	 //保存图片到桌面，图片名称格式为yyyymmddHHmmss.jpg
	 public void saveToFile() {
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
	  String name = sdf.format(new Date());//格式转化获得图片名
	  File path = FileSystemView.getFileSystemView().getHomeDirectory();//获得桌面路径
	  String format = "jpg";
	  File f = new File(path + File.separator + name + "." + format);
	  try {
	   ImageIO.write(saveImage, format, f);//生成图片
	  } catch (IOException e) {//抛出异常
	   e.printStackTrace();
	  }
	 }
	 public void snapshot() {
	  try {
	   Robot robot = new Robot();//在基本屏幕坐标系中构造一个 Robot对象
	   Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	   image = robot.createScreenCapture(new Rectangle(0, 0, d.width,d.height));//获得整个屏幕
	  } catch (AWTException e) {
	   e.printStackTrace();
	  }
	 }
	}