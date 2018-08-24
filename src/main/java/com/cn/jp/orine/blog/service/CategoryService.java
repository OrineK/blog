package com.cn.jp.orine.blog.service;

import com.cn.jp.orine.blog.Exception.BusinessException;
import com.cn.jp.orine.blog.constant.ResultMsg;
import com.cn.jp.orine.blog.dao.CategoryDao;
import com.cn.jp.orine.blog.model.Category;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryDao categoryDao;

    public void addCategory(Category category) {
        try {
            categoryDao.save(category);
        }catch (Exception e) {
            throw new BusinessException(ResultMsg.SAVE_ERROR);
        }
    }

    public void editCategory(Category orig) {
        try {
            categoryDao.save(orig);
        }catch (Exception e) {
            throw new BusinessException(ResultMsg.UPDATE_ERROR);
        }
    }

    /**
     * 分类列表 分类不会太多，不用分页
     * @return
     */
    public List<Category> list() {
        return categoryDao.findAll();
    }
}
