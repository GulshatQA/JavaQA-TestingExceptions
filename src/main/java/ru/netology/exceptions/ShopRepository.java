package ru.netology.exceptions;

import java.net.IDN;

public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        products = addToArray(products, product);
    }

    //метод получения всех сохранённых Product
    public Product[] findAll() {
        return products;
    }

    // В методе удаления removeById сначала проверяйте, есть ли элемент.
    // Для этого прямо из метода removeById вызывайте метод findById:
    // если результат null, тогда выкидывайте исключение NotFoundException
    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    //метод findById, предназначенный для поиска товара в репозитории по его ID.
    //Так, он должен принимать параметр ID искомого товара, пробегаться по всем товарам репозитория
    //и сверять их ID с искомым, в случае совпадения делать return этого товара.
    //Если же, пробежав все товары репозитория, ни один подходящий найден не был,
    //то есть цикл закончился без вызова return внутри него, то следует сделать return null.
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
