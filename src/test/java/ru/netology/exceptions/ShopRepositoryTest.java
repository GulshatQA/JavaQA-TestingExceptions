package ru.netology.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    Product product1 = new Product(15, "apple", 94);
    Product product2 = new Product(16, "orange", 147);
    Product product3 = new Product(19, "grape", 105);

    // проверяет успешность удаления существующего элемента
    @Test
    public void testRemoveByID() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.removeById(product1.getId());

        Product[] expected = {product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    // генерация NotFoundException при попытке удаления несуществующего элемента
    @Test
    public void testRemoveNotId() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(8);
        });
    }
}
