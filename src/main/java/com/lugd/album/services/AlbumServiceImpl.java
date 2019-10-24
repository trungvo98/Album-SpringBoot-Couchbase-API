package com.lugd.album.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lugd.album.model.AlbumModel;
import com.lugd.album.model.SongModel;
import com.lugd.album.repository.AlbumRepository;


@Service
public class AlbumServiceImpl implements AlbumService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	AlbumRepository albumRepository;

	@Override
	public void create(AlbumModel album) {
		albumRepository.save(album);
		
	}

	@Override
	public List<AlbumModel> getAll() {
		return albumRepository.findAll();
	}
	
	@Override
	public Optional<AlbumModel> findById(String id) {
		
	 return albumRepository.findById(id);
	}

	@Override
	public void delete(String id) {
	
		albumRepository.deleteById(id);
	}

	@Override
	public void update(AlbumModel album) {
		albumRepository.save(album);
	}


	


	
	

	

}
