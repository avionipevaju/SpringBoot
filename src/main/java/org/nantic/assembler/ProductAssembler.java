package org.nantic.assembler;

import org.nantic.dto.DTOProduct;
import org.nantic.entites.Product;

public class ProductAssembler {
	
	public static DTOProduct createDTO(Product entity) {
		DTOProduct productDTO = new DTOProduct();
		
		productDTO.setId(entity.getId());
		productDTO.setName(entity.getName());
		productDTO.setPrice(entity.getPrice());
		
		return productDTO;
	}

}
