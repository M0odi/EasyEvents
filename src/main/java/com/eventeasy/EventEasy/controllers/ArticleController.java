package com.eventeasy.EventEasy.controllers;

import com.eventeasy.EventEasy.models.Article;
import com.eventeasy.EventEasy.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/articles-list")
    public String articlesList(Map<String, Object> model) {
        model.put("articles", articleService.getAllArticle());
        return "list-articles";
    }

    @PostMapping("/create-article")
    public String createArticle(
            @RequestParam String name, @RequestParam String author, Map<String, Object> model
    ) {
        var article = new Article();
        article.setName(name);
        article.setAuthor(author);
        articleService.save(article);
        model.put("articles", articleService.getAllArticle());
        return "list-articles";
    }
    @DeleteMapping("/remove-articles/{id}")
    public String removeEventById(
            @PathVariable Integer id
    ){
        articleService.removeArticleById(id);
        return "create-event-template";
    }

}
