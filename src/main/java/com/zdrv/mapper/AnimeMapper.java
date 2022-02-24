package com.zdrv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zdrv.domain.Anime;
import com.zdrv.domain.ViewAnime;

@Mapper
public interface AnimeMapper {
	List<Anime> selectAll();
	Anime selectById(int id);
	Long countAnimes();
	List<Anime> getLimitedAnimes(@Param("offset") int offset,@Param("num") int num);
	List<Anime> searchAnime(String moji);
	List<ViewAnime> allViewAnimes(int id);
	ViewAnime selectViewAnime(@Param("userId") int userId,@Param("ViewId") int ViewId);
	void viewInsert(ViewAnime viewanime);
	void updateView(ViewAnime viewanime);
	void deleteView(int ViewId);
	
}
