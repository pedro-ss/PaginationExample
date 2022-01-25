package br.com.pagination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pagination.model.Product;
import br.com.pagination.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	/**
	 * 
	 * @param name  (nome usado para filtragem)
	 * @param price price (preço usado para filtragem)
	 * @param page  (qual página será exibida)
	 * @param size  (quantidade de itens por página)
	 * @return Page de produtos paginados
	 */
	public Page<Product> getProductsPaginated(String name, Double price, int page, int size) {
		// selecionando a pagina e a quantidades de itens
		Pageable pageSize = PageRequest.of(page-1, size);
		
		if (!name.isBlank() && price != null) {
			// se nenhum dos dois for vazio, utiliza os dois.
			return repository.findProductsByNamePrice(name, price, pageSize);
		} else if (price != null) {
			// se houver preco, filtrar por preco
			return repository.findProductByPrice(price, pageSize);
		} else {
			// filtra por name
			return repository.findProductByName(name, pageSize);
		}
	}

	/**
	 * 
	 * @param product (Objeto de retorno e usado para filtragem)
	 * @param page    (qual página será exibida)
	 * @param size    (quantidade de itens por página)
	 * @return Page de produtos paginados
	 */
	public Page<Product> getProducts(Product product, int page, int size) {
		PageRequest request = PageRequest.of(page-1, size);
		Page<Product> produtos;

		if ( product.getName() != null || product.getPrice() != null) {
			produtos = getProductsPaginated(product.getName(), product.getPrice(), page, size);
		} else {
			produtos = repository.findAll(request);
		}

		return produtos;
	}
}
