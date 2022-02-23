package com.zdrv.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdrv.domain.Anime;
import com.zdrv.domain.ViewAnime;
import com.zdrv.domain.ViewAnime2;
import com.zdrv.mapper.AnimeMapper;

@Service
@Transactional
public class AnimeServiceImpl implements AnimeService {

	int numPerPage = 6;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	@Autowired
	AnimeMapper animemapper;

	@Override
	public Anime getById(int id) {

		return animemapper.selectById(id);
	}

	@Override
	public List<Anime> getAll() {

		return animemapper.selectAll();
	}

	@Override
	public List<Anime> limitAnimes(int offset, int num) {

		return animemapper.getLimitedAnimes(offset, num);
	}

	@Override
	public Long countAnimes() {

		return animemapper.countAnimes();
	}

	@Override
	public List<Anime> getAnimes(Integer page) {

		int offset = numPerPage * (page - 1);

		List<Anime> animes = limitAnimes(offset, numPerPage);
		return animes;
	}

	@Override
	public int totalPages() {
		Long count = countAnimes();

		int totalPages = (int) Math.ceil((double) count / numPerPage);
		return totalPages;
	}

	@Override
	public List<Anime> getSearchAnimes(String moji) {
		
		return animemapper.searchAnime(moji);
	}

	@Override
	public List<ViewAnime2> getAllViewAnimes(int id) {
		List<ViewAnime> viewanimes = animemapper.allViewAnimes(id);
		List<ViewAnime2> viewanimes2 = new ArrayList<>();
				
		for(ViewAnime viewanime:viewanimes) {
			var viewanime2 = new ViewAnime2();
			
			viewanime2.setId(viewanime.getId());
			viewanime2.setDate(sdf.format(viewanime.getDate()));
			viewanime2.setTitle(viewanime.getTitle());
			viewanime2.setText(viewanime.getText());
			
			viewanimes2.add(viewanime2);
		}
		
		return viewanimes2;
	}

	@Override
	public void insertView(ViewAnime viewanime) {
		animemapper.viewInsert(viewanime);
	}

	@Override
	public ViewAnime2 editViewAnimes(ViewAnime viewanime) {
		
		return animemapper.updateView(viewanime);
	}

}
