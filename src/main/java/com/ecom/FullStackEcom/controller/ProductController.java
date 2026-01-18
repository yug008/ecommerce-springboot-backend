package com.ecom.FullStackEcom.controller;

import com.ecom.FullStackEcom.Model.Product;
import com.ecom.FullStackEcom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService service;

    @Autowired                                                //constructor injection
    public ProductController(ProductService service){
        this.service = service;
    }

    @RequestMapping("/")
    public String greet(){
        return "Hello user !";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable int id){
        Product product = service.getProductByID(id);
        if(product!=null){
            return ResponseEntity.ok(product);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)                //we are sending data of multiple/different types (JSON+File), so we used 'value' and 'consumes'
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
       try{
           Product prod = service.addProduct(product,imageFile);
           return new ResponseEntity<>(prod, HttpStatus.CREATED);
       }
       catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/products/{productID}/image")
    public ResponseEntity<byte[]> getImageByProductID(@PathVariable int productID){
        Product p = service.getProductByID(productID);
        byte[]imageFile = p.getImage();

        return ResponseEntity.ok().contentType(MediaType.valueOf(p.getImageType())).body(imageFile);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProducts(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {
        Product prod = service.updateProducts(id,product,imageFile);

        if(prod!=null){
            return new ResponseEntity<>("Updated!",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not updated",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProducts(@PathVariable int id){
        Product prod = service.getProductByID(id);
        if(prod!=null){
            service.deleteProducts(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Not Deleted",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product>products = service.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }




}
