package edu.uw.data.lecture6.controller;

import edu.uw.data.lecture6.dao.*;
import edu.uw.data.lecture6.model.*;
import edu.uw.data.lecture6.service.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller

public class HomeController {

	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
    BookDao bookDao;

    @Autowired
    BookService bookService;

    @RequestMapping(value={"/","/home","/index"})

	public ModelAndView homePage() {
        log.info("home");
		ModelAndView mav = new ModelAndView();
        List<Book> books = bookDao.findAll();
        mav.addObject("books", books);
		mav.setViewName("home");
		return mav;
	}

    @RequestMapping("/home2")
    public ModelAndView homePage2() {
        log.info("home2");
        ModelAndView mav = new ModelAndView();
        List<Book> books = bookDao.findAll();
        mav.addObject("books", books);
        mav.setViewName("home");
        return mav;
    }
}