package com.lugd.album.model;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import com.couchbase.client.java.repository.annotation.Field;

@Document
public class AlbumModel {
	
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;
	@Field
	private String title;
	@Field
	private String imageUrl;
	@Field
	private String relDate;
	@Field
	private List<SongModel> songs =new ArrayList<SongModel>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getRelDate() {
		return relDate;
	}
	public void setRelDate(String relDate) {
		this.relDate = relDate;
	}
	public List<SongModel> getSongs() {
		return songs;
	}
	public void setSongs(List<SongModel> songs) {
		this.songs = songs;
	}
	
}
