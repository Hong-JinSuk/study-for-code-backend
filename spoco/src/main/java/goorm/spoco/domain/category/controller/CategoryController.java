package goorm.spoco.domain.category.controller;

import goorm.spoco.domain.category.domain.Category;
import goorm.spoco.domain.category.service.CategoryService;
import goorm.spoco.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/{studyId}/create")
    public BaseResponse createCategory(
            @RequestBody Category category,
            @PathVariable Long studyId
    ) {
        categoryService.save(category, studyId);
        return BaseResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("카테고리 생성")
                .results(List.of(category))
                .build();
    }
}