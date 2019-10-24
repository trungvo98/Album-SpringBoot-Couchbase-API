package com.lugd.album.services;


import java.util.List;
import java.util.Optional;

import com.lugd.album.model.AlbumModel;
import com.lugd.album.model.SongModel;


/**
 * 
 * @author DatPV
 *
 */

public interface AlbumService {
	
	public void create (AlbumModel album);
	
	public List<AlbumModel> getAll();
	
	public Optional<AlbumModel> findById (String id);
	
	public void update(AlbumModel album);
	
	public void delete(String id);
	
	/*public void addSong(List<SongModel> songs , String idAlbum);*/
	
	
	
	
}
