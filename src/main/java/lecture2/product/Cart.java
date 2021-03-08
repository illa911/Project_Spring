package lecture2.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("")
public class Cart {
    private ProductRepository repository;
    private List<Product> productList = new ArrayList<>();

    public Cart Cart() {
        return new Cart();
    }

    @Autowired
    public void getProductRepository(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    public void addProductToCart(int idProduct) {
        productList.add(repository.getProductById(idProduct));
    }

    public void removeProductToCart(int idProduct) {
        productList.remove(repository.getProductById(idProduct));
    }

    @Override
    public String toString() {
        return productList.stream()
                .map(Product::getTitle)
                .collect(Collectors.joining("::", "В корзине: [", "]"));
    }

}
