package com.lugd.album.model;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class Song {

	@Id
	private String id;

	@Field
	private String title;
	@Field
	private String artist;
	@Field
	private String albId;

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

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbId() {
		return albId;
	}

	public void setAlbId(String albId) {
		this.albId = albId;
	}

}
