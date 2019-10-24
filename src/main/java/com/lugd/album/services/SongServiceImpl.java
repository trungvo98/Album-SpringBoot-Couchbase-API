package com.lugd.album.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lugd.album.model.Song;
import com.lugd.album.model.SongModel;
import com.lugd.album.repository.SongRepository;


@Service
public class SongServiceImpl implements SongService {
	
	
	@Autowired
	SongRepository songRepo ;
	
	@Override
	public void deleteById(String albId) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void updateSong(String idSong) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Song song) {
		songRepo.save(song);	
	}

}
