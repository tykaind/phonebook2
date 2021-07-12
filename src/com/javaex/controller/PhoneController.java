package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 파라미터 action 값을 읽어온다.
		String action = request.getParameter("action");
		System.out.println(action);
		
		
		/******************if문시작********************/
		
		/***************리스트 불러오기****************/
		if ("list".equals(action)) {
			//리스트업무
			System.out.println("[리스트]");
			
			// 리스트
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();

			System.out.println(personList);

			// 데이터를 넣어서 list.jsp로 넘김 --> request 어트리뷰트로
			request.setAttribute("pList", personList);


			// html작업은 jsp에게 시킨다 --> froward
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
			
			
		/***************** 등록폼 *******************/
		}else if("wform".equals(action)) {
			System.out.println("[글쓰기]");
			//writeForm 포워드 --> 데이터X
			RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
		
			
		/****************** 등록 ********************/
		}else if("insert".equals(action)) {
			System.out.println("[저장]");
			//파라미터 값을 꺼낸다.
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//Vo로 묶어준다.
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);
			
			//dao personInsert(vo)
			PhoneDao phoneDao = new PhoneDao();
			int count = phoneDao.personInsert(personVo);
			
			//리다이렉트 시킨다.
			response.sendRedirect("/phonebook2/pbc?action=list");
		
		/****************** 삭제 ********************/
		}else if("delete".equals(action)) {
			System.out.println("[삭제]");
			PhoneDao phoneDao = new PhoneDao();
			int personId = Integer.parseInt(request.getParameter("id"));
			int count = phoneDao.personDelete(personId);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		/****************** 수정폼 ********************/	
		}else if("updateForm".equals(action)) {
			System.out.println("[수정폼]");
			int id = Integer.parseInt(request.getParameter("id"));
			
			PhoneDao phoneDao = new PhoneDao();
			PersonVo upDateone = phoneDao.getPerson(id);
			
			request.setAttribute("upDateOne", upDateone);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			rd.forward(request, response);
			
		/******************  수정  ********************/	
		}else if("update".equals(action)) {
			System.out.println("[수정]");
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PhoneDao phoneDao = new PhoneDao();
			PersonVo personVo = new PersonVo(id,name,hp,company);
			
			System.out.println(personVo);
			phoneDao.personUpdate(personVo);
			response.sendRedirect("/phonebook2/pbc?action=list");
		}
		
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
