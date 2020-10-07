package com.shoppingmall.web;

import com.shoppingmall.service.CategoryService;
import com.shoppingmall.dto.CategoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {

    private final CategoryService categoryService;

    // 1차 카테고리 추가 (관리자 권한)
    @PostMapping("/v1/categories/first")
    public ResponseEntity<?> addFirstCategory(@RequestBody @Valid CategoryRequestDto.firstCategory firstCategory,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(categoryService.addFirstCategory(firstCategory));
    }

    // 2차 카테고리 추가 (관리자 권한)
    @PostMapping("/v1/categories/second")
    public ResponseEntity<?> addSecondCategory(@RequestBody @Valid CategoryRequestDto.secondCategory secondCategory,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(categoryService.addSecondCategory(secondCategory));
    }

    // 1차,2차 카테고리 수정 (관리자 권한)
    @PutMapping("/v1/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequestDto categoryDto,
                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(categoryService.updateCategory(id, categoryDto));
    }
}
