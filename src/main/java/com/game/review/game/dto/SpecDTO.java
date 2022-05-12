package com.game.review.game.dto;

import org.apache.ibatis.type.Alias;

@Alias("specDTO")
public class SpecDTO {
	private Long specNum;
	private String specMinCpu;
	private String specMinRam;
	private String specMinGpu;
	private String specMinDx;
	private String specProCpu;
	private String specProRam;
	private String specProGpu;
	private String specProDx;
	private String gName;
	private Long gNum;

	public Long getgNum() {
		return gNum;
	}

	public void setgNum(Long gNum) {
		this.gNum = gNum;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public Long getSpecNum() {
		return specNum;
	}

	public void setSpecNum(Long specNum) {
		this.specNum = specNum;
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

	@Override
	public String toString() {
		return "SpecDTO [specNum=" + specNum + ", specMinCpu=" + specMinCpu + ", specMinRam=" + specMinRam
				+ ", specMinGpu=" + specMinGpu + ", specMinDx=" + specMinDx + ", specProCpu=" + specProCpu
				+ ", specProRam=" + specProRam + ", specProGpu=" + specProGpu + ", specProDx=" + specProDx + ", gName="
				+ gName + "]";
	}

}
