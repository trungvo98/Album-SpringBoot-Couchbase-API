package com.lugd.album.request;

import java.util.List;

import com.lugd.album.model.SongModel;

public class AlbumRequest {

	private String title;
	private String imageUrl;
	private List<SongModel> listSong;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<SongModel> getListSong() {
		return listSong;
	}
	public void setListSong(List<SongModel> listSong) {
		this.listSong = listSong;
	}
	
	

    


	

	

}
