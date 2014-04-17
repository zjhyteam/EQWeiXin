package org.earthQuake.test;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImgTool {
	private int startX;
	private int startY;
	private int width;
	private int height;
	static ImgTool imgT = new ImgTool();
	/**
	 * 源路径
	 */
	private String srcPath;
	/**
	 * 目标路径
	 */
	private String tarPath;
	
	public static void main(String args[]) throws MalformedURLException, IOException, URISyntaxException, AWTException{
//		URL url;
//		String address = "http://qq370273662.oicp.net/EQWeiXin/map/map.html";
//		try {
//			url = new URL(address);
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			InputStream Fi=con.getInputStream();
//			File file=new File("E:/image/22.jpg");
//			InputStreamReader in = new InputStreamReader(Fi,"GB2312");
//			file.createNewFile();
//			FileOutputStream Fo=new FileOutputStream(file);
//			OutputStreamWriter Osw=new OutputStreamWriter(Fo,"GB2312");
//			char[] by=new char[12];
//			while(in.ready()){
//				in.read(by);
//				Osw.write(by);
//			}
//			Fi.close();
//			Osw.flush();
//			Osw.close();
//		} catch (MalformedURLException e1) {
//			e1.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
		
	//1、最直接的方式——使用Robot
	//此方法仅适用于JdK1.6及以上版本
	Desktop.getDesktop().browse(
			new URL("http://qq370273662.oicp.net/EQWeiXin/map/map.jsp?lat=22.73488&lon=112.29717").toURI());
	Robot robot = new Robot();
//	robot.delay(10000);
	Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
	int width = (int) d.getWidth();
	int height = (int) d.getHeight();
	//最大化浏览器
	robot.keyRelease(KeyEvent.VK_F11);
	robot.delay(5000);
	Image image = robot.createScreenCapture(new Rectangle(80, 130, width-160,
			height-130));
	BufferedImage bi = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	Graphics g = bi.createGraphics();
	g.drawImage(image, 0, 0, width, height, null);
	//保存图片
	final String fileName = System.currentTimeMillis() + ".jpg";
	ImageIO.write(bi, "jpg", new File("E:/image/" + fileName + ".jpg"));
	
	//2、最常规的方式——利用JNI，调用第三方C/C++组件
	
		
//	imgT.setStartX(0);
//	imgT.setStartY(0);
//	imgT.setHeight(height-130);
//	imgT.setWidth(width-160);
//	imgT.setSrcPath("E:/image/333.jpg");
//	imgT.setTarPath("E:/image/11.jpg");
//	boolean flag = imgT.cut();
//	if(flag){
		Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");  
        Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");  
        Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");  
        Runtime.getRuntime().exec("taskkill /F /IM safari.exe");  
        Runtime.getRuntime().exec("taskkill /F /IM opera.exe");  
//	}
	}
	/**
	 * 根据开始坐标，宽度和高度切图。
	 * 把图片读入内存缓冲区，然后再
	 * 根据具体坐标切取子图最后把子
	 * 图俺规定的格式存入指定文件
	 */
	public boolean cut(){
		try {
			BufferedImage bufImg = ImageIO.read(new File(imgT.getSrcPath()));
			bufImg = bufImg.getSubimage(imgT.getStartX(), imgT.getStartY(), imgT.getWidth(), imgT.getHeight());
			ImageIO.write(bufImg,"jpg",new File(imgT.getTarPath()));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getStartX() {
		return startX;
	}
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public int getStartY() {
		return startY;
	}
	public void setStartY(int startY) {
		this.startY = startY;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getTarPath() {
		return tarPath;
	}
	public void setTarPath(String tarPath) {
		this.tarPath = tarPath;
	}
}