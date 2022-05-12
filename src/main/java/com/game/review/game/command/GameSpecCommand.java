package com.game.review.game.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class GameSpecCommand {

	private List<MultipartFile> slideImgFile;
	private String specMinCpu;
	private String specMinRam;
	private String specMinGpu;
	private String specMinDx;
	private String specProCpu;
	private String specProRam;
	private String specProGpu;
	private String specProDx;
	private String gName;


	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}



	public List<MultipartFile> getSlideImgFile() {
		return slideImgFile;
	}

	public void setSlideImgFile(List<MultipartFile> slideImgFile) {
		this.slideImgFile = slideImgFile;
	}

	public String getSpecMinCpu() {
		return specMinCpu;
	}

	public void setSpecMinCpu(String specMinCpu) {
		this.specMinCpu = specMinCpu;
	}

	public String getSpecMinRam() {
		return specMinRam;
	}

	public void setSpecMinRam(String specMinRam) {
		this.specMinRam = specMinRam;
	}

	public String getSpecMinGpu() {
		return specMinGpu;
	}

	public void setSpecMinGpu(String specMinGpu) {
		this.specMinGpu = specMinGpu;
	}

	public String getSpecMinDx() {
		return specMinDx;
	}

	public void setSpecMinDx(String specMinDx) {
		this.specMinDx = specMinDx;
	}

	public String getSpecProCpu() {
		return specProCpu;
	}

	public void setSpecProCpu(String specProCpu) {
		this.specProCpu = specProCpu;
	}

	public String getSpecProRam() {
		return specProRam;
	}

	public void setSpecProRam(String specProRam) {
		this.specProRam = specProRam;
	}

	public String getSpecProGpu() {
		return specProGpu;
	}

	public void setSpecProGpu(String specProGpu) {
		this.specProGpu = specProGpu;
	}

	public String getSpecProDx() {
		return specProDx;
	}

	public void setSpecProDx(String specProDx) {
		this.specProDx = specProDx;
	}

}
