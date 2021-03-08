package lecture2.product;

import lecture2.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepository {
    public List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int index) {
        return this.products.get(index);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {

        return products.stream()
                .map(Product::getTitle)
                .collect(Collectors.joining("::", "Продукты: [", "]"));
    }
}