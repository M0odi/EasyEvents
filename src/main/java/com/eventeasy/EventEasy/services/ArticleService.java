package com.eventeasy.EventEasy.services;

import com.eventeasy.EventEasy.models.Article;
import com.eventeasy.EventEasy.models.Event;
import com.eventeasy.EventEasy.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getAllArticle(){
        return articleRepository.findAll();
    }

    public Article getArticleById(Integer id){
        return articleRepository.getReferenceById(id);
    }
    public void save(Article article) {
        articleRepository.save(article);
    }
    public void deleteArticleById(Integer id){
        articleRepository.deleteById(id);
    }

}
