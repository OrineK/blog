package com.cn.jp.orine.blog.service;

import com.alibaba.fastjson.JSON;
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
import java.util.Date;
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

    public void updateArticle(ArticleAddReq req) {
        Article article = articleDao.getOne(req.getId());
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

    public ArticleEditReq getArticleEditInfoById(Long id) {
        Article article = articleDao.getOne(id);
        ArticleEditReq req = new ArticleEditReq();
        BeanUitl.copyProperties(article, req);
        List<String> tagNames = new ArrayList<>();
        for (Tag tag : article.getTags()) {
            tagNames.add(tag.getName());
        }
        String tagStr = tagNames.toString();
        req.setTagNames(tagStr.substring(1, tagStr.length() - 1));
        return req;
    }

    public Article getAticleById(Long id) {
        return articleDao.getOne(id);
    }

    public Page<Article> findList(ArticleQueryReq req) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(req.getCurrentPage(), req.getPageSize(), sort);
        return articleDao.findAll(this.getWhereClause(req), pageable);
    }

    public void addClickNum(Article article) {
        article.setUpdateTime(new Date());
        article.setClickNum(article.getClickNum() + 1);
        articleDao.save(article);
    }

    public void addStartNum(Article article) {
        article.setUpdateTime(new Date());
        article.setStartNum(article.getStartNum() + 1);
        articleDao.save(article);
    }

    /**
     * 查询id的前一条信息
     * @param id
     * @return
     */
    public Article findBeforeById(Long id) {
        return articleDao.findFirstByIdBeforeOrderByIdDesc(id);
    }

    /**
     * 查询id的后一条信息
     * @param id
     * @return
     */
    public Article findAfterById(Long id) {
        return articleDao.findFirstByIdAfterOrderByIdAsc(id);
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
