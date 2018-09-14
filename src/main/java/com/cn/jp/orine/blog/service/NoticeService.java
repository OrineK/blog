package com.cn.jp.orine.blog.service;

import com.cn.jp.orine.blog.Exception.BusinessException;
import com.cn.jp.orine.blog.constant.ResultMsg;
import com.cn.jp.orine.blog.dao.NoticeDao;
import com.cn.jp.orine.blog.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NoticeService {

    @Resource
    private NoticeDao noticeDao;

    public void save(Notice notice) {
        notice.setCreateTime(new Date());
        notice.setUpdateTime(new Date());
        List<Notice> notices = noticeDao.findAll();
        if (notices.size() > 5) {
            throw new BusinessException("通知最多设置为5条");
        }
        try {
            notice.setShowOrders(notices.size() + 1);
            noticeDao.save(notice);
        }catch (Exception e) {
            throw new BusinessException(ResultMsg.SAVE_ERROR);
        }
    }

    public void update(Notice notice) {
        notice.setUpdateTime(new Date());
        try {
            noticeDao.save(notice);
        }catch (Exception e) {
            throw new BusinessException(ResultMsg.UPDATE_ERROR);
        }
    }

    public void delete(Notice notice) {
        try {
            noticeDao.delete(notice);
        }catch (Exception e) {
            throw new BusinessException(ResultMsg.DELETE_ERROR);
        }
    }

    public List<Notice> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "showOrders");
        return noticeDao.findAllBySort(sort);
    }
}
