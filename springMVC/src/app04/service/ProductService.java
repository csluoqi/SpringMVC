package app04.service;

import app04.domain.Product;

public interface ProductService
{
    Product add(Product product);
    Product get(long id);
}
