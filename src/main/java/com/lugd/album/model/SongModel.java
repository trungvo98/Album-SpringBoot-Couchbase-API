package com.lugd.album.model;

import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import com.couchbase.client.java.repository.annotation.Field;

public class SongModel {
    
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String sId;
    
	private String sTitle;
    
	private String playSong;
    
	private ArtistModel artist;
	
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
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
