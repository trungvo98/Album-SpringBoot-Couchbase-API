package com.lugd.album.request;

import com.lugd.album.model.ArtistModel;

public class SongRequest {
    private String sTitle;
    
	private String playSong;
    
	private ArtistModel artist;

	public String getsTitle() {
		return sTitle;
	}

	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}

	public String getPlaySong() {
		return playSong;
	}

	public void setPlaySong(String playSong) {
		this.playSong = playSong;
	}

	public ArtistModel getArtist() {
		return artist;
	}

	public void setArtist(ArtistModel artist) {
		this.artist = artist;
	}
	
	
	
	
}
