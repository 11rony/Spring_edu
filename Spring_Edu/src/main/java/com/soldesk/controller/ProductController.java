package com.soldesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldesk.beans.ProductVO;
import com.soldesk.database.MapperInterface;

//@SuppressWarnings : 컴파일 경고를 무시함
//unchecked : 검증되지 않은 연산자 관련 경고 억제
//rawtypes : 제네릭(데이터 형식에 의존하지 않고, 하나의 값이 여러 다른 데이터 타입들을 가질 수 있음)을 사용하는 
//클래스 매개 변수가 불특정일 때의 경고 억제
@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController // @Controller + @ResponseBody
public class ProductController {

	@Autowired
	private MapperInterface productMapper;

	@GetMapping("/products")
	public List getAllProductList() {
		System.out.println("Request Method : GET");
		return productMapper.getAllProductList();
	}

	/*
	 * @PathVariable : url 정보를 변수로 할당받기 위한 어노테이션 {id} 자리에 해당 어노테이션이 선언된 변수로 데이터가
	 * 할당된다
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity findProductById(@PathVariable("id") String id) {
		System.out.println("Request Method : GET");
		ProductVO product = productMapper.findProductById(id);
		if (product == null) {
			return new ResponseEntity("상품이 존재하지 않습니다", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(product, HttpStatus.OK);
	}

	@PostMapping(value = "/products")
	public ResponseEntity registerProduct(ProductVO productVO) {
		System.out.println("Request Method : POST");
		productMapper.registerProduct(productVO);
		return new ResponseEntity(productVO.getId() + " " + productVO.getName() + " 상품등록완료", HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity deleteProduct(@PathVariable String id) {
		System.out.println("Request Method : DELETE");
		// if (productMapper.deleteProduct(0)) {
		// return new ResponseEntity(id+"상품 아이디에 대한 상품이 없어 삭제 불가",
		// HttpStatus.NOT_FOUND);
		// }
		productMapper.deleteProduct(id);
		return new ResponseEntity(id + " id 상품정보삭제완료", HttpStatus.OK);
	}

	@PutMapping("/products")
	public ResponseEntity updateProduct(ProductVO productVO) {
		System.out.println("Request Method : PUT " + productVO);
		int result = productMapper.updateProduct(productVO);
		if (result == 0) {
			return new ResponseEntity(productVO.getId() + "번 상품 아이디에 대한 상품이 없어 수정불가", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(productVO.getId() + " id 상품정보수정완료", HttpStatus.OK);
	}
}