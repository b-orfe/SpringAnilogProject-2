package com.zdrv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdrv.domain.Anime;
import com.zdrv.mapper.AnimeMapper;

@Service
@Transactional
public class AnimeServiceImpl implements AnimeService {

	int numPerPage = 6;

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

}
