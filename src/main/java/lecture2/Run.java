package lecture2;

import lecture2.product.Cart;
import lecture2.product.Product;
import lecture2.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class Run implements CommandLineRunner, BeanPostProcessor {
    private Cart cart;
    private final ProductRepository productRepository;

    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 5; i++) {
            productRepository.addProduct(new Product(i, "товар" + i, (i + 1) * 5));
        }
    }
    @Autowired
    public Run(Cart cart, ProductRepository productRepository) {
        this.cart = cart;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        InputStream in = System.in;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {
            String input;
            while (true) {
                System.out.println("Выберите товар для добавления в корзину, пример: add 1 \nили: del 1 для удаления товара ");
                input = bufferedReader.readLine().trim();
                switch (input.split(" ")[0]) {
                    case "add":
                        System.out.println("add");
                        cart.addProductToCart(Integer.parseInt(input.split(" ")[1]));
                        break;
                    case "del":
                        System.out.println("del");
                        cart.removeProductToCart(Integer.parseInt(input.split(" ")[1]));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + input);
                }
                System.out.println(cart.toString());
            }
        }
    }
}