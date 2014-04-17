package org.earthQuake.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class URL_VIEW extends JFrame{

 private URL url;
 
 private JEditorPane webView; //定义面板
 
 public URL_VIEW(String address){
  super("URL View");
  //Locale locale = new Locale("zh","CN");
  //ResourceBundle resourceBundle = ResourceBundle.getBundle("org.com.cn.jxta.examples_browser",locale);
  webView = new JEditorPane();//创建面板
  webView.setEditable(false);//是否能在面板上能编辑
  webView.setContentType("text/html;charset=utf-8");
  webView.setPreferredSize(new Dimension(1024,768));//定义浏览器的大小
  JScrollPane scrollPane = new JScrollPane(webView);//有滚动条的
  getContentPane().add(scrollPane,BorderLayout.CENTER);//添加到窗体里
  pack();//相当于提交
  
  try {
   url= new URL(address);
   try {
    webView.setPage(url);
    setVisible(true);
   } catch (IOException e) {
    e.printStackTrace();
   }
   
  } catch (MalformedURLException e) {
   e.printStackTrace();
  }
 }
 
 @SuppressWarnings("unused")
public static void main(String[] args) {
  URL_VIEW URLVIEW = new URL_VIEW("http://www.baidu.com"); 
 }
}