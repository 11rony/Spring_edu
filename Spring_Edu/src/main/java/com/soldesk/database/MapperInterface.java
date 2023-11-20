package com.soldesk.database;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soldesk.beans.ProductVO;

public interface MapperInterface {

	@Insert("insert into RestFulDB(id, name, maker, price) values(#{id}, #{name}, #{maker}, #{price})")
	void registerProduct(ProductVO productvo);

	@Select("select id, name, maker,price from RestFulDB")
	List<ProductVO> getAllProductList();

	@Select("select id, name, maker,price from RestFulDB where id=#{value} ")
	ProductVO findProductById(String value);

	@Delete("delete from RestFulDB where id=#{value}")
	void deleteProduct(String value);

	@Update("update RestFulDB set name=#{name},maker=#{maker},price=#{price} where id=#{id}")
	int updateProduct(ProductVO productvo);
}
