package com.news.controller;

import com.news.model.News;
import com.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/news")
public class NewsController {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @RequestMapping("/addNews_input")
    public String addNewsInput() {
        return "addNews";
    }

    @PostMapping("/addNews")
    public String addNews(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "tag", defaultValue = "всякое") String tag,
            @RequestParam(name = "description") String description,
            Model model
    ) {
        News news = new News();
        news.setTitle(title);
        news.setTag(tag);
        news.setDescription(description);

        newsRepository.save(news);
        Iterable<News> all = newsRepository.findAll();
        model.addAttribute("news", all);

        return "showNews";
    }

    @RequestMapping("/showAllNews")
    public String showAllNews(Model model) {
        Iterable<News> allNews = newsRepository.findAll();

        model.addAttribute("news", allNews);

        return "showNews";
    }
}
