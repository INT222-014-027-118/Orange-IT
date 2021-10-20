package INT222.Controllers;


import INT222.Exceptions.NotFoundException;

import INT222.Exceptions.NotFoundNameException;
import INT222.Exceptions.SameProductNameException;
import INT222.Models.*;
import INT222.Repositories.*;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductHomeRepository productHomeRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductSpecValueRepository productSpecValueRepository;

    @Autowired
    private ProductHasAttributeRepository productHasAttributeRepository;
    //Get all Products
    @GetMapping("/list")
    public List<ProductsHome> getProduct() {
        return productHomeRepository.findAll();
    }

    //Get Products by Category
    @GetMapping("getByCategory/{category}")
    public List<ProductsHome> getProductHomeByCategory(@PathVariable("category") String category) {
        List<ProductsHome> productsByCategory = new ArrayList<ProductsHome>();
        List<ProductsHome> products = productHomeRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            List<Categories> categories = products.get(i).getCatergories();
            for (int j = 0; j < categories.size(); j++) {
                if(categories.get(j).getCategory().toLowerCase().equals(category.toLowerCase())){
                    productsByCategory.add(products.get(i));
                }
            }
        }
        return productsByCategory;

    }


    @GetMapping("/{id}")
    public Optional<Products> getProductById(@PathVariable(value = "id") long id) {
        if (this.productRepository.existsById(id)) {
            return productRepository.findById(id);
        } else
            throw new NotFoundException(id);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id) {

        if (this.productRepository.existsById(id)) {
//           long num =0;
//           Products products =  productRepository.getById(id);
//           List<Images> images = products.getImages();
//            for (int i = 0; i < images.size(); i++) {
//                Images image = imageRepository.getById(images.get(i).getId());
//                image.setProduct_id(num);
//                imageRepository.save(image);
//
//            }
//            List<ProductsHasAttributes> productsHasAttributes = products.getProductsHasAttributes();
//            for (int i = 0; i < productsHasAttributes.size(); i++) {
//                ProductsHasAttributes productSpecValue = productsHasAttributes.get(i);
//                productSpecValue.setProduct_id(num);
//                productSpecValueRepository.save(productSpecValue);
//
//            }

    this.deleteProductHasAttribute(id);
    this.deleteProductImage(id);
    this.productRepository.deleteById(id);
        } else
            throw new NotFoundException(id);
    }

    @PostMapping("/add")
    public Optional<Products> addProduct(@RequestBody Products products) {
            if (productRepository.existsByProductName(products.getProductName())) {
                throw new SameProductNameException(products.getProductName());
            }

            else
            products.setId(productRepository.findTopByOrderByIdDesc().getId()+1);
             List<Images> images =  products.getImages();
             List<ProductsHasAttributes> productsHasAttributes = products.getProductsHasAttributes();
        for (int i = 0; i < images.size(); i++) {
            images.get(i).setId(imageRepository.findTopByOrderByIdDesc().getId()+1+i);
        }
        for (int i = 0; i < productsHasAttributes.size(); i++) {
            productsHasAttributes.get(i).setId(productHasAttributeRepository.findTopByOrderByIdDesc().getId()+1+i);
            productsHasAttributes.get(i).setProductId(products.getId());
        }
            productRepository.save(products);

          return getProductById(products.getId());

    }

    @GetMapping("getByName/{name}")
    public List<Products> getProductByName(@PathVariable("name") String name) {
        return productRepository.findAllByProductNameContaining(name);
    }

//        Edit Product
    @PutMapping("/update")
    public void editProduct(@RequestBody Products products) {
        if (productRepository.existsByProductName( products.getProductName()) && productRepository.existsById(products.getId())) {
            productRepository.save(products);
        }
        else throw new NotFoundException(products.getId());
    }

    @GetMapping("/page/{pageNo}/{pageSize}")
    public Page<ProductsHome> productList(@PathVariable("pageNo") int pageNo,
                                      @PathVariable("pageSize") int pageSize
                                      ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ProductsHome> list = productHomeRepository.findAll(pageable);
        return  list;
    }




    @GetMapping("getByBrandName/{brandName}")
    public List<Products> getProductByBrandName(@PathVariable("brandName") String name) {

        if(productRepository.existsProductsByBrandNameContaining(name)){
            return productRepository.findAllByBrandNameContaining(name);
        }
        else throw new NotFoundNameException(name);
    }

    @PutMapping("/editAttribute")
    public void editProduct(@RequestBody ProductsHasAttributes productsHasAttributes) {
        productHasAttributeRepository.save(productsHasAttributes);
    }

    @GetMapping("stock/{id}")
    public int getProductByBrandName(@PathVariable long id) {
        return productHomeRepository.getById(id).getQuantityStock();

    }


    @DeleteMapping("/deleteP/{id}")
    public void deleteProductHasAttribute(@PathVariable long id) {
        productHasAttributeRepository.deleteByProductId(id);

    }

    @DeleteMapping("/deleteI/{id}")
    public void deleteProductImage(@PathVariable long id) {
        imageRepository.deleteByProductId(id);

    }

//    @GetMapping("geta/list")
//    public List<ProductsHasAttributes> getProductByBrandName() {
// return productHasAttributeRepository.findAll();
//
//    }


}
