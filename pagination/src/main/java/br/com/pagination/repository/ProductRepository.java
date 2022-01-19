package br.com.pagination.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pagination.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    /**
     * 
     * @param price (valor de produto, usado na filtragem)
     * @param pageable (objeto que parametiza paginação, tamanho e em que página)
     * @return lista de objetos paginados
    */
    Page<Product> findProductByPrice(double price, Pageable pageable);

    /**
     * 
     * @param name (nome do produto, usado na filtragem)
     * @param pageable (objeto que parametiza paginação, tamanho e em que página)
     * @return lista de objetos paginados
    */
    Page<Product> findProductByName(String name, Pageable pageable);
    
    /**
     * 
     * @param name (nome do produto, usado na filtragem)
     * @param price (valor de produto, usado na filtragem)
     * @param pageable (objeto que parametiza paginação, tamanho e em que página)
     * @return lista de objetos paginados filtrados pelo nome e preço
     */
    @Query("FROM Product p " +
    "WHERE LOWER(p.name) like %:name% " +
    "AND LOWER(p.price) = :price")
    Page<Product> findProductsByNamePrice(@Param("name") String name, @Param("price") Double price, Pageable pageable);
}
