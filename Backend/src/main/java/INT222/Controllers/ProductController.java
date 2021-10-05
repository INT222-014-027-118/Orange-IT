package INT222.Controllers;


import INT222.Exceptions.NotFoundException;

import INT222.Exceptions.NotFoundNameException;
import INT222.Exceptions.SameProductNameException;
import INT222.Models.Images;
import INT222.Models.ProductSpecValues;
import INT222.Models.Products;
import INT222.Models.ProductsHome;
import INT222.Repositories.ImageRepository;
import INT222.Repositories.ProductHomeRepository;
import INT222.Repositories.ProductRepository;

import INT222.Repositories.ProductSpecValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.sql.ClientInfoStatus;
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

    //Get all Product
    @GetMapping("/list")
    public List<ProductsHome> getProduct() {
        return productHomeRepository.findAll();
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

long num =0;
           Products products =  productRepository.getById(id);
           List<Images> images = products.getImages();
            for (int i = 0; i < images.size(); i++) {
                Images image = imageRepository.getById(images.get(i).getId());
                image.setProduct_id(num);
                imageRepository.save(image);

            }
            List<ProductSpecValues> productSpecValues = products.getProductSpecValues();
            for (int i = 0; i < productSpecValues.size(); i++) {
                ProductSpecValues productSpecValue = productSpecValues.get(i);
                productSpecValue.setProduct_id(num);
                productSpecValueRepository.save(productSpecValue);

            }

    this.productRepository.deleteById(id);
        } else
            throw new NotFoundException(id);
    }

    @PostMapping("/add")
    public Products addProduct(@RequestBody Products products) {


            if (productRepository.existsByProductName(products.getProductName())) {

                throw new SameProductNameException(products.getProductName());
            }else
            products.setId(productRepository.findTopByOrderByIdDesc().getId()+1);
             List<Images> images =  products.getImages();
        for (int i = 0; i < images.size(); i++) {
            images.get(i).setId(imageRepository.findTopByOrderByIdDesc().getId()+1+i);
        }
            productRepository.save(products);
        System.out.println(productRepository.findTopByOrderByIdDesc().getId()+1);
          return products;

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

//    @RequestMapping("/product")
//    public List<Products> productList(@RequestParam(defaultValue = "0") Integer pageNo,
//                              @RequestParam(defaultValue = "8") Integer pageSize,
//                              @RequestParam(defaultValue = "msrp") String sortBy,
//                              Model model) {
//        Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        Page<Products> pageResult = productRepository.findAll(paging);
//        model.addAttribute("products",pageResult.getContent() );
//        return  pageResult;
//    }




    @GetMapping("getByBrandName/{brandName}")
    public List<Products> getProductByBrandName(@PathVariable("brandName") String name) {

        if(productRepository.existsProductsByBrandNameContaining(name)){
            return productRepository.findAllByBrandNameContaining(name);
        }
        else throw new NotFoundNameException(name);
    }





}
