package com.game.review.admin.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.game.review.game.command.GameRegCommand;
import com.game.review.game.command.GameSpecCommand;
import com.game.review.game.dao.GameDAO;
import com.game.review.game.dto.GameFilesDTO;
import com.game.review.game.dto.GamesDTO;
import com.game.review.game.dto.GenreDTO;
import com.game.review.game.dto.SpecDTO;
import com.game.review.game.exception.AlreadyExistGameCodeException;
import com.game.review.game.exception.AlreadyExistGameNameException;
import com.game.review.game.exception.NoImageException;
import com.game.review.member.validate.FileTypeByTika;

@Service
public class AdminGameService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminGameService.class);
	
	@Autowired
	private GameDAO gameDAO;
	
	// for game list
	public List<GenreDTO> selectGenreList() {
		return gameDAO.selectGenreListOfGame();
	}

	public List<GameFilesDTO> selectGameFilesList() {
		return gameDAO.selectGameFilesList();
	}

	public List<GamesDTO> selectGameList() {
		return gameDAO.selectGameList();
	}
	
	//장르표시
	public List<GenreDTO> selectGenre() {
		return gameDAO.selectGenres();
	}
	
	
	public void gameDupCheck(GameRegCommand gameRegCommand) throws AlreadyExistGameNameException, AlreadyExistGameCodeException {
		
		//게임코드 중복 확인
		int resForCode = gameDAO.countGameCodeForDup(gameRegCommand.getgCode());
		if(resForCode != 0) {
			logger.info("코드중복" + resForCode);
			throw new AlreadyExistGameCodeException();
		}
		//게임이름 중복 확인
		int resForName = gameDAO.countGameNameForDup(gameRegCommand.getgName());
		if(resForName != 0) {
			logger.info("이름중복" + resForName);
			throw new AlreadyExistGameNameException();
		}
	}
	
	// for game insert
	@Transactional
	public void insertGame(GameRegCommand grc) {
		
		MultipartFile img = grc.getImgFile();
		String orifile = img.getOriginalFilename();
		String savedfile = UUID.randomUUID().toString().replaceAll("-", "") + orifile;
		String path = "C:\\stsproject\\upload\\images\\games\\" + grc.getgCode() + "\\" + savedfile;
		File gameImgFile = null;
		
		try {
			
			//업로드된 파일이 존재 한다면
			if(img != null && !img.isEmpty()) {
				gameImgFile = new File(path);
				if (!gameImgFile.exists()) {
					gameImgFile.mkdirs();
					img.transferTo(gameImgFile);
				}
			}
			
			GamesDTO newGame = new GamesDTO();
			GameFilesDTO newGamefile = new GameFilesDTO();
			GenreDTO newGameGr = new GenreDTO();
			
			//Game Data
			//view에서 String으로 넘어온 날짜 데이터 -> Date -> Timestamp
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = date.parse(grc.getgDate());
			long time = date1.getTime();
			Timestamp ts = new Timestamp(time);
			newGame.setgDate(ts);
			newGame.setgName(grc.getgName());
			newGame.setgDev(grc.getgDev());
			newGame.setgPrice(grc.getgPrice());
			newGame.setgContent(grc.getgContent());
			newGame.setgCode(grc.getgCode());
			
			//File Data
			newGamefile.setGfFilename(orifile);
			newGamefile.setGfSavedfilename(savedfile);
			newGamefile.setgName(grc.getgName());
			newGamefile.setGfLocation("1");
			gameDAO.insertGame(newGame);
			gameDAO.insertMainFile(newGamefile);
			
			//Genre Data
			List<String> genreNums = grc.getGenNum();
			for (int i = 0; i < genreNums.size(); i++) {
				newGameGr.setGenNum(Long.parseLong(genreNums.get(i)));
				newGameGr.setgName(grc.getgName());
				gameDAO.insertGr(newGameGr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}//insertGame() end
	
	//insert Spec Data and images for slider
	@Transactional
	public void specRegist(GameSpecCommand gsc, String gCode) throws NoImageException{

		try {
			
			// Slider Files
			for (int i = 0; i < gsc.getSlideImgFile().size(); i++) {
				MultipartFile imgFile = gsc.getSlideImgFile().get(i);
				String orifile = imgFile.getOriginalFilename();
				String savedfile =UUID.randomUUID().toString().replaceAll("-", "") +  orifile;
				String path = "C:\\stsproject\\upload\\images\\games\\" + gCode + "\\" + savedfile;
		
				File fileForSlider = null;
				InputStream inputStream = imgFile.getInputStream();
				
				boolean isImage = FileTypeByTika.validImgFile(inputStream);
				if(!isImage) {
					throw new NoImageException();
				}else {
					fileForSlider = new File(path);
					if (!fileForSlider.exists()) {
						fileForSlider.mkdirs();
					}
					inputStream.close();
					imgFile.transferTo(fileForSlider);
				}
				
				GameFilesDTO newSliderFiles = new GameFilesDTO();

				newSliderFiles.setGfFilename(orifile);
				newSliderFiles.setGfSavedfilename(savedfile);
				newSliderFiles.setgName(gsc.getgName());
				newSliderFiles.setGfLocation("2");
				
				gameDAO.insertSlideFile(newSliderFiles);
			}//for end
			
			//Spec
			SpecDTO newSpec = new SpecDTO();
			newSpec.setSpecMinCpu(gsc.getSpecMinCpu());
			newSpec.setSpecMinRam(gsc.getSpecMinRam());
			newSpec.setSpecMinGpu(gsc.getSpecMinGpu());
			newSpec.setSpecMinDx(gsc.getSpecMinDx());
			newSpec.setSpecProCpu(gsc.getSpecProCpu());
			newSpec.setSpecProRam(gsc.getSpecProRam());
			newSpec.setSpecProGpu(gsc.getSpecProGpu());
			newSpec.setSpecProDx(gsc.getSpecProDx());
			newSpec.setgName(gsc.getgName());
			gameDAO.insertSpec(newSpec);
			
		} catch (IOException e) {
				e.printStackTrace();
		}
	}//specRegist end
	
	//show detail
	public List<GenreDTO> detailGenres(Long gNum) {
		return gameDAO.selectGenresBySeq(gNum);
	}

	public List<GameFilesDTO> detailGameFiles(Long gNum) {
		return gameDAO.selectGameFilesBySeq(gNum);
	}

	public GamesDTO detailGame(Long gNum) {
		return (GamesDTO) gameDAO.selectGameBySeq(gNum);
	}

	public Object detailSpec(Long gNum) {
		return (SpecDTO) gameDAO.selectSpecBySeq(gNum);
	}
}
