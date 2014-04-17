package org.earthQuake.course.common.msg.resp;

/**
 * 音乐消息
 * 
 * @author 徐晓亮
 * @date 2014-01-06
 */
public class RespMusicMessage extends RespBaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}