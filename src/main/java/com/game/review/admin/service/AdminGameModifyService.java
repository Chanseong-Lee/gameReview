package com.game.review.admin.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.game.review.game.command.GameFileModifyCommand;
import com.game.review.game.command.GameModifyCommand;
import com.game.review.game.command.GenreModifyCommand;
import com.game.review.game.command.SpecModifyCommand;
import com.game.review.game.dao.GameDAO;
import com.game.review.game.dto.GameFilesDTO;
import com.game.review.game.dto.GamesDTO;
import com.game.review.game.dto.GenreDTO;
import com.game.review.game.dto.SpecDTO;
import com.game.review.game.exception.AlreadyExistGameCodeException;
import com.game.review.game.exception.AlreadyExistGameNameException;
import com.game.review.game.exception.NoCheckFileException;
import com.game.review.game.exception.NoCheckGenreException;
import com.game.review.game.exception.NoFileException;
import com.game.review.game.exception.NoImageException;
import com.game.review.member.validate.FileTypeByTika;

@Service
public class AdminGameModifyService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminGameModifyService.class);

	@Autowired
	private GameDAO gameDAO;

	public void updateGame(GameModifyCommand mc) throws AlreadyExistGameCodeException, AlreadyExistGameNameException{
		
		try {
			
			GamesDTO updateGame = new GamesDTO();
			updateGame.setgNum(mc.getgNum());
			updateGame.setgName(mc.getgName());
			updateGame.setgCode(mc.getgCode());
			updateGame.setgDev(mc.getgDev());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(mc.getgDate());
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			updateGame.setgDate(ts);
			updateGame.setgPrice(mc.getgPrice());
			updateGame.setgContent(mc.getgContent());
			
			//????????????
			int resForCode = gameDAO.countGameCodeForDupExItSelf(updateGame);
			if(resForCode != 0) {
				logger.info("????????????" + resForCode);
				throw new AlreadyExistGameCodeException();
			}
			int resForName = gameDAO.countGameNameForDupExItSelf(updateGame);
			if(resForName != 0) {
				logger.info("????????????" + resForName);
				throw new AlreadyExistGameNameException();
			}
			
			gameDAO.updateGame(updateGame);

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public void updateSpec(SpecModifyCommand sc) {
		
		SpecDTO oldSpec = gameDAO.selectSpecBySeq(sc.getgNum());
		SpecDTO updateSpec = new SpecDTO();
		updateSpec.setgNum(sc.getgNum());
		updateSpec.setSpecMinCpu(sc.getSpecMinCpu());
		updateSpec.setSpecMinRam(sc.getSpecMinRam());
		updateSpec.setSpecMinGpu(sc.getSpecMinGpu());
		updateSpec.setSpecMinDx(sc.getSpecMinDx());
		updateSpec.setSpecProCpu(sc.getSpecProCpu());
		updateSpec.setSpecProRam(sc.getSpecProRam());
		updateSpec.setSpecProGpu(sc.getSpecProGpu());
		updateSpec.setSpecProDx(sc.getSpecProDx());
		
		if(oldSpec != null) {//?????????????????? ???????????? ????????????
			gameDAO.updateSpec(updateSpec);
		
		}else {//??????????????? ???????????? ??????????????? ?????????
			gameDAO.upinsertSpec(updateSpec);
		}
	}

	public void updateGenre(GenreModifyCommand gc) {
		
		for(Long genNum : gc.getGenNum()) {
			GenreDTO updateGenre = new GenreDTO();
			updateGenre.setGenNum(genNum);
			updateGenre.setgNum(gc.getgNum());
		
			gameDAO.updateGenre(updateGenre);
		}
	}

	public void deleteGenre(GenreModifyCommand gc) {
		
		if(gc.getGenNum() != null) {
			gameDAO.deleteGenre(gc.getgNum());
		}else {
			throw new NoCheckGenreException();
		}
	}

	public List<GenreDTO> selectGenresBySeq(Long gNum) {

		return gameDAO.selectGenresBySeq(gNum);
	}

	public List<GameFilesDTO> selectGameFilesBySeq(Long gNum) {

		return gameDAO.selectGameFilesBySeq(gNum);
	}

	public GamesDTO selectGameBySeq(Long gNum) {
		GamesDTO view = gameDAO.selectGameBySeq(gNum);
		return view;
	}

	public SpecDTO selectSpecBySeq(Long gNum) {
		SpecDTO spec = gameDAO.selectSpecBySeq(gNum);
		return spec;
	}

	public List<GenreDTO> selectAllGenres() {
		return gameDAO.selectGenres();
	}
	
	
	//??????????????????
	//??????????????? ??????
	@Transactional
	public void updateMainFile(GameFileModifyCommand gameFileModifyCommand) {
		
		//?????? ????????? ??????
		if (gameFileModifyCommand.getImgFile().isEmpty() || gameFileModifyCommand.getImgFile()==null) {
			throw new NoFileException();
		}
		
		MultipartFile imgFile = gameFileModifyCommand.getImgFile();
		String orifile = imgFile.getOriginalFilename();
		String savedfile = UUID.randomUUID().toString().replaceAll("-", "") + orifile;
		String path = "C:\\stsproject\\upload\\images\\games\\" + gameFileModifyCommand.getgCode() + "\\" + savedfile;
		File newFile = null;

		try {
			InputStream inputStream = imgFile.getInputStream();
			boolean isImage = FileTypeByTika.validImgFile(inputStream);
			if(!isImage) {
				inputStream.close();
				throw new NoImageException();
			}else {
				newFile = new File(path);
				if (!newFile.exists()) {
					newFile.mkdirs();
				}
				inputStream.close();
				imgFile.transferTo(newFile);
				//??????????????? ??????
			}
			
			//????????? ????????????
			GameFilesDTO updateMainFile = new GameFilesDTO();
			updateMainFile.setGfFilename(orifile);
			updateMainFile.setGfSavedfilename(savedfile);
			updateMainFile.setgName(gameFileModifyCommand.getgName());
			updateMainFile.setGfLocation("1");
			updateMainFile.setgNum(gameFileModifyCommand.getgNum());
			
			//????????? ????????? ???????????????????????? ????????? ???????????? ???????????? //???????????? gfLocation, gNum
			GameFilesDTO previousFile = gameDAO.selectMainFile(updateMainFile);
			//????????? ?????????????????? ???????????????
			if (previousFile != null) {
				//???????????? ????????? ??????
				//??????????????? ??????????????????
				deleteMainFile(previousFile, gameFileModifyCommand);
			}
			
			//???????????? ????????? ??? ??????????????? ????????? ??????
			gameDAO.updateMainFile(updateMainFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//??????????????? ??????
	public int deleteMainFile(GameFilesDTO previousFile, GameFileModifyCommand gameFileModifyCommand) {
		
		String directoryPath = "C:\\stsproject\\upload\\images\\games\\" + gameFileModifyCommand.getgCode() + "\\" + previousFile.getGfSavedfilename();
		logger.info("????????? ?????? : " + directoryPath);
		//???????????? ??????
		File oldFile = new File(directoryPath);
		int res = -1;
		if (oldFile.exists()) {
			//????????? ?????????????????? ??????
			if(oldFile.delete()) {//?????? ?????????
				res = gameDAO.deleteMainFile(previousFile);//????????????
			}else {
				logger.error("???????????? ?????? ??????");
			}
		}
		return res;
	}
	
	
	//?????????????????? ??????
	public void updateSlideFile(GameFileModifyCommand gmf) {

		for (MultipartFile imgFile : gmf.getSlideImgFile()) {
			
			//?????? ????????? ??????
			if (gmf.getSlideImgFile().isEmpty() || gmf.getSlideImgFile() == null) {
				throw new NoFileException();
			}
			
			try {
				File newSliderFile = null;
				String orifile = imgFile.getOriginalFilename();
				String savedfile = UUID.randomUUID().toString().replaceAll("-", "") + orifile;
				String path = "C:\\stsproject\\upload\\images\\games\\" + gmf.getgCode() + "\\" + savedfile;
				logger.info(path);
				
				if (orifile.isEmpty()) {
					throw new NoFileException();
				}
				InputStream inputStream = imgFile.getInputStream();
				boolean isImage = FileTypeByTika.validImgFile(inputStream);
				if(!isImage) {
					inputStream.close();
					throw new NoImageException();
				}else {
					newSliderFile = new File(path);
					if (!newSliderFile.exists()) {
						newSliderFile.mkdirs();
					}
					inputStream.close();
					imgFile.transferTo(newSliderFile);
				}

				GameFilesDTO updateSlideFile = new GameFilesDTO();

				updateSlideFile.setGfFilename(orifile);
				updateSlideFile.setGfSavedfilename(savedfile);
				updateSlideFile.setgName(gmf.getgName());
				updateSlideFile.setGfLocation("2");
				updateSlideFile.setgNum(gmf.getgNum());

				gameDAO.updateSlideFile(updateSlideFile);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}//for end
	}
	
	//???????????? ??????
	public void deleteSlideFile(GameFileModifyCommand gmf) {

		if (gmf.getGfNum().isEmpty() ||gmf.getGfNum() == null) {
			throw new NoCheckFileException();
			//???????????? ???????????? ??????????????? 
		}
		
		for (Long gfNum : gmf.getGfNum()) {
			GameFilesDTO ordDTO = gameDAO.selectSliderFile(gfNum); //????????? ???????????? ?????? ?????????
			String directoryPath = "C:\\stsproject\\upload\\images\\games\\" + gmf.getgCode() + "\\" + ordDTO.getGfSavedfilename();
			System.out.println(directoryPath);
			
			File file = new File(directoryPath);
			if (file.exists()) {
				if (file.delete()) {
					logger.info("?????? ??????");
					gameDAO.deleteSlideFile(ordDTO);
				} else {
					logger.error("????????????!");
				}
			} else {
				logger.error("?????? ??????!");
			}
		}
	}//deleteSlideFile() end
	
}
