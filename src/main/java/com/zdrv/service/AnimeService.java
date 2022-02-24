package com.zdrv.service;

import java.util.List;

import com.zdrv.domain.Anime;
import com.zdrv.domain.ViewAnime;
import com.zdrv.domain.ViewAnime2;

public interface AnimeService {
	List<Anime> getAll();
	Anime getById(int id);
	Long countAnimes();
	List<Anime> limitAnimes(int offset,int num);
	List<Anime> getAnimes(Integer page);
	List<Anime> getSearchAnimes(String moji);
	int totalPages();
	List<ViewAnime2> getAllViewAnimes(int id);
	void insertView(ViewAnime viewanime);
	ViewAnime2 editViewAnimes(ViewAnime viewanime);
	
}
