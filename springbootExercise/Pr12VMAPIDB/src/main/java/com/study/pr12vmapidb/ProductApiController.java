package com.study.pr12vmapidb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class ProductApiController {
    final ProductRepository productRepository;

    // 상품 전체 조회
    @PostMapping("/")
    @ResponseBody
    public List<Product> main() {
        List<Product> productList = productRepository.findAll();

        return productList;
    }

    // 상품 추가
    @PostMapping("/add-action")
    @ResponseBody
    public List<Product> add(@RequestBody AddReqDTO addReqDTO) {
        Product product = Product.builder()
                .name(addReqDTO.getName())
                .price(addReqDTO.getPrice())
                .limitDate(addReqDTO.getLimitDate())
                .build();
        productRepository.save(product);

        List<Product> productList = productRepository.findAll();
        return productList;
    }

    // 수정할 상품 불러오기
    @GetMapping("/edit")
    public String viewEditForm(@RequestParam Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);

        model.addAttribute("product", product);
        return "editProductForm";
    }

    // 상품 수정
    @PostMapping("/edit-action")
    @ResponseBody
    public List<Product> updateProduct(@RequestBody ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId()).orElse(null);
        product.updateProduct(productDTO.getName(), productDTO.getPrice(),productDTO.getLimitDate());
        productRepository.save(product);

        List<Product> productList = productRepository.findAll();
        return productList;
    }

    // 상품 삭제
    @PostMapping("/delete")
    @ResponseBody
    public List<Product> delete(@RequestBody Map<String, Long> idMap) {
        Long id = idMap.get("id");
        Product product = productRepository.findById(id).orElse(null);
        productRepository.delete(product);

        List<Product> productList = productRepository.findAll();
        return productList;
    }
}
