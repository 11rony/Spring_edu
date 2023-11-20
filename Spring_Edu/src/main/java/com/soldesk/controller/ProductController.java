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

//@SuppressWarnings : ������ ��� ������
//unchecked : �������� ���� ������ ���� ��� ����
//rawtypes : ���׸�(������ ���Ŀ� �������� �ʰ�, �ϳ��� ���� ���� �ٸ� ������ Ÿ�Ե��� ���� �� ����)�� ����ϴ� 
//Ŭ���� �Ű� ������ ��Ư���� ���� ��� ����
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
	 * @PathVariable : url ������ ������ �Ҵ�ޱ� ���� ������̼� {id} �ڸ��� �ش� ������̼��� ����� ������ �����Ͱ�
	 * �Ҵ�ȴ�
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity findProductById(@PathVariable("id") String id) {
		System.out.println("Request Method : GET");
		ProductVO product = productMapper.findProductById(id);
		if (product == null) {
			return new ResponseEntity("��ǰ�� �������� �ʽ��ϴ�", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(product, HttpStatus.OK);
	}

	@PostMapping(value = "/products")
	public ResponseEntity registerProduct(ProductVO productVO) {
		System.out.println("Request Method : POST");
		productMapper.registerProduct(productVO);
		return new ResponseEntity(productVO.getId() + " " + productVO.getName() + " ��ǰ��ϿϷ�", HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity deleteProduct(@PathVariable String id) {
		System.out.println("Request Method : DELETE");
		// if (productMapper.deleteProduct(0)) {
		// return new ResponseEntity(id+"��ǰ ���̵� ���� ��ǰ�� ���� ���� �Ұ�",
		// HttpStatus.NOT_FOUND);
		// }
		productMapper.deleteProduct(id);
		return new ResponseEntity(id + " id ��ǰ���������Ϸ�", HttpStatus.OK);
	}

	@PutMapping("/products")
	public ResponseEntity updateProduct(ProductVO productVO) {
		System.out.println("Request Method : PUT " + productVO);
		int result = productMapper.updateProduct(productVO);
		if (result == 0) {
			return new ResponseEntity(productVO.getId() + "�� ��ǰ ���̵� ���� ��ǰ�� ���� �����Ұ�", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(productVO.getId() + " id ��ǰ���������Ϸ�", HttpStatus.OK);
	}
}