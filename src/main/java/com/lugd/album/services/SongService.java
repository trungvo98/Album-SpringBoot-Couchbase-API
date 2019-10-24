package com.lugd.album.services;

import com.lugd.album.model.Song;
import com.lugd.album.model.SongModel;

public interface SongService {
	

	public void create(Song song);
	
	public 	void deleteById(String albId);
	
	public void updateSong(String idSong);
	
}
