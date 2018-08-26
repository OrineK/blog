package com.cn.jp.orine.blog.service;

import com.cn.jp.orine.blog.Exception.BusinessException;
import com.cn.jp.orine.blog.constant.ResultMsg;
import com.cn.jp.orine.blog.dao.ArticleDao;
import com.cn.jp.orine.blog.dao.CategoryDao;
import com.cn.jp.orine.blog.dao.TagDao;
import com.cn.jp.orine.blog.model.Article;
import com.cn.jp.orine.blog.model.Category;
import com.cn.jp.orine.blog.model.Tag;
import com.cn.jp.orine.blog.utils.BeanUitl;
import com.cn.jp.orine.blog.utils.DateUtil;
import com.cn.jp.orine.blog.utils.StringUtil;
import com.cn.jp.orine.blog.vo.ArticleAddReq;
import com.cn.jp.orine.blog.vo.ArticleEditReq;
import com.cn.jp.orine.blog.vo.ArticleQueryReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private TagDao tagDao;

    public void addArticle(ArticleAddReq req) {
        Article article = new Article();
        BeanUitl.copyProperties(req, article);
        Category category = categoryDao.findCategoryById(req.getCategoryId());
        article.setCategory(category);
        List<Tag> tags = new ArrayList<>();
        for (String name : req.getTagNames()) {
            Tag tag = tagDao.findTagByName(name);
            if (tag == null) {
                tag = new Tag();
                tag.setName(name);
                try {
                    tagDao.save(tag);
                }catch (Exception e) {
                    throw new BusinessException(ResultMsg.SAVE_ERROR);
                }
            }
            tags.add(tag);
        }
        article.setTags(tags);
        try {
            articleDao.save(article);
        }catch (Exception e) {
            throw new BusinessException(ResultMsg.SAVE_ERROR);
        }
    }

    public void updateArticle(ArticleEditReq req) {
        Article article = articleDao.getOne(req.getId());
        BeanUitl.copyProperties(article, req);
        Category category = categoryDao.findCategoryById(req.getCategoryId());
        article.setCategory(category);
        List<Tag> tags = new ArrayList<>();
        for (Long id:req.getTagIds()) {
            tags.add(tagDao.getOne(id));
        }
        article.setTags(tags);
        article.setUpdateTime(DateUtil.getNow());
        try {
            articleDao.save(article);
        }catch (Exception e) {
            throw new BusinessException(ResultMsg.UPDATE_ERROR);
        }
    }

    public void deleteArticle(Long id) {
        Article article = articleDao.getOne(id);
        if (article == null) {
            throw new BusinessException(ResultMsg.SYSTEM_FAIL);
        }
        try {
            articleDao.delete(article);
        }catch (Exception e) {
            throw new BusinessException(ResultMsg.DELETE_ERROR);
        }
    }

    public Article getOneById(Long id) {
        return articleDao.getOne(id);
    }

    public Page<Article> findList(ArticleQueryReq req) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(req.getCurrentPage(), req.getPageSize(), sort);
        return articleDao.findAll(this.getWhereClause(req), pageable);
    }


    /**
     * 私有方法 -用于查询
     *
     * @param req
     * @return
     */
    private Specification<Article> getWhereClause(ArticleQueryReq req) {
        return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtil.isObjEmpty(req.getTitle())) {
                    predicates.add(cb.like(root.get("title").as(String.class), "%" + req.getTitle() + "%"));
                }
                Predicate[] pre = new Predicate[predicates.size()];
                return query.where(predicates.toArray(pre)).getRestriction();
            }
        };
    }
}
