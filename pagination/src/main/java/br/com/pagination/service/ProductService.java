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
     * @param pageSize (tamanho da página a ser exibida)
     * @param price (preço usado para filtragem)
     * @return Page de produtos paginados
     */
    public Page<Product> getProductsByPrice(Pageable pageSize, Double price) {
   
        // retornando todos os produtos por preço e paginados
        return repository.findProductByPrice(price, pageSize);
    }

    /**
     * 
     * @param pageSize (tamanho da página a ser exibida)
     * @param name (nome usado para filtragem)
     * @return Page de produtos paginados
     */
    public Page<Product> getProductsByName(Pageable pageSize, String name) {

        // retornando todos os produtos por nome e paginados
        return repository.findProductByName(name, pageSize);
    }

    /**
     * 
     * @param name  (nome usado para filtragem)
     * @param price price (preço usado para filtragem)
     * @param page (qual página será exibida)
     * @param size (quantidade de itens por página)
     * @return
     */
    public Page<Product> getProductsPaginated(String name, Double price, int page, int size) {

        // selecionando a pagina e a quantidades de itens
        Pageable pageSize = PageRequest.of(page, size);

        if(name.isBlank()){
            // se o nome for vazio, usa o preço.
            return getProductsByPrice(pageSize, price);
        } else if(price == null) {
            // se o preço for vazio, usa o nome.
            return getProductsByName(pageSize, name);
        }
        // se nenhum dos dois for vazio, utiliza os dois.
        return repository.findProductsByNamePrice(name, price, pageSize);
    }
}
