package com.game.review.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.game.command.RepliesCommand;
import com.game.review.game.dto.RepliesDTO;
import com.game.review.game.service.RepliesService;

@Controller
public class RepliesController {

	@Autowired
	private RepliesService rservice;
	
	
      //댓글 등록
	@RequestMapping(value = "/rep/{aNum}", method = RequestMethod.GET)
	public String rep(@ModelAttribute("repData") RepliesCommand rcmd, Errors errros,
			@PathVariable("aNum") Long aNum, Model model) {
		RepliesDTO rdto = new RepliesDTO();
		System.out.println("리뷰 넘버는: " + aNum);
		rdto.setaNum(aNum);	
		
		// 댓글들 보여주기 
		List<RepliesDTO> rep = rservice.selectRe(rdto);
		model.addAttribute("rep1", rep);
	
		return "/rep";

	}
	  //댓글 등록
	@RequestMapping(value = "/rep/{aNum}", method = RequestMethod.POST)
	public String rep2(@ModelAttribute("repData") RepliesCommand rcmd, Errors errros, @PathVariable("aNum") int aNum,
			Model model) {
		System.out.println("댓글컨트롤러 포스트");

		
		//댓글 인서트
		rservice.insetRe(rcmd);
		return "redirect:/rep/{aNum}";
	}
	
	//댓글 업데이트
	@RequestMapping(value = "/repupdate/{aNum}/{reNum}", method = RequestMethod.GET)
	public String update(@ModelAttribute("repupdateData") RepliesCommand rcmd, Errors errros,
			@PathVariable("aNum") Long aNum, @PathVariable("reNum") Long reNum ,
			Model model) {
		
		RepliesDTO rdto = new RepliesDTO();
		System.out.println("리뷰 넘버는: " + aNum);
		rdto.setaNum(aNum);

		 //각각의 리뷰 댓글 보여주기 aNum 리뷰넘버만 확인
		List<RepliesDTO> rep = rservice.selectRe(rdto);
		model.addAttribute("rep", rep);
		System.out.println("댓글업데이트 확인" );


		return "/repupdate";

	}
	@RequestMapping(value = "/repupdate/{aNum}/{reNum}", method = RequestMethod.POST)
	public String update2(@ModelAttribute("repupdateData") RepliesCommand rcmd, Errors errros,
			@PathVariable("aNum") Long aNum, @PathVariable("reNum") Long reNum ,
			Model model) {
		RepliesDTO rdto = new RepliesDTO();
		rdto.setmNum((long) 1);
		rdto.setaNum(aNum);
		rdto.setReNum(reNum);
		rdto.setReContent(rcmd.getReContent());
		
		// 내가쓴 댓글 확인 후 댓글 수정
		rservice.updateRe(rdto);


		return "redirect:/rep/{aNum}";

	}
	
	  //댓글 삭제
	@RequestMapping(value = "/repdel/{aNum}/{reNum}", method = RequestMethod.GET)
	public String delete(@PathVariable("aNum") Long aNum, @PathVariable("reNum") Long reNum ,
			Model model) {
		
		RepliesDTO rdto = new RepliesDTO();
		rdto.setaNum(aNum);
		rdto.setmNum((long) 1);
		
		//aNum 리뷰번호와 mNum 맴버 번호를 확인하여 내가 쓴 댓글 보여주기
		List<RepliesDTO> rep = rservice.selectdel(rdto);
		model.addAttribute("rep", rep);

		System.out.println("댓글삭제 확인" );
		return "/repdel";

	}
	//댓글 삭제
	@RequestMapping(value = "/repdel/{aNum}/{reNum}", method = RequestMethod.POST)
	public String delete2(@PathVariable("aNum") Long aNum, @PathVariable("reNum") Long reNum) {
		System.out.println("댓글삭제 POST 확인" );

		RepliesDTO rdto2 = new RepliesDTO() ;
		rdto2.setReNum(reNum); 
		rdto2.setaNum(aNum);  //리뷰넘버
		rdto2.setmNum((long) 1);
		
		//내가 쓴 댓글 삭제하기
		rservice.deleteRe(rdto2);

		return  "redirect:/rep/{aNum}";

	}

	
	
	
}
