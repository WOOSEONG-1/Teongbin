package com.ssafy.teongbin.log.service;

import com.ssafy.teongbin.log.entity.Category;
import com.ssafy.teongbin.log.entity.Categorylog;
import com.ssafy.teongbin.log.repository.CategoryRepository;
import com.ssafy.teongbin.log.repository.CategorylogRepository;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategorylogService {
    private final CategorylogRepository categorylogRepository;
    private final TrashcanRepository trashcanRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager em;

    public Long join(String serialNumber, Long categoryId) {
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow(()->new IllegalArgumentException("해당 ID의 카테고리를 찾을 수 없습니다."));
        Category category = new Category();
        category.setName("캔");
        em.persist(category);
        Trashcan trashcan = trashcanRepository.findBySerialNumber(serialNumber);
        Categorylog categorylog = new Categorylog();
        categorylog.setCategory(category);
        categorylog.setTrashcan(trashcan);
        categorylogRepository.save(categorylog);
        return categorylog.getId();

    }
}
