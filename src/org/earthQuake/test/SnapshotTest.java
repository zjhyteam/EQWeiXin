package org.earthQuake.test;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * 功能：java截图
 * 运行后将当前屏幕截取，并最大化显示。
 * 拖拽鼠标，选择自己需要的部分。
 * 按Esc键保存图片到桌面，并退出程序。
 * 双击鼠标左键，保存图片到桌面，并退出程序
 * 点击右上角（没有可见的按钮），退出程序，不保存图片。
 * 点击右键，退出程序，不保存图片。
 */
public class SnapshotTest {
 public static void main(String[] args) {
  //全屏运行
  RectD rd = new RectD();
  GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
    .getDefaultScreenDevice();
  gd.setFullScreenWindow(rd);
 }
}