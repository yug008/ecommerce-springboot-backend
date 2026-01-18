package com.ecom.FullStackEcom.Service;

import com.ecom.FullStackEcom.Model.Product;
import com.ecom.FullStackEcom.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Component
public class ProductService {

    private ProductRepo repo;

    @Autowired                                         //constructor injection
    public ProductService(ProductRepo repo){
        this.repo = repo;
    }

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product getProductByID(int id){
        return repo.findById(id).orElseThrow(()->new RuntimeException("Product not found."));
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {            //The repository layer methods saves only entities (Only JSON not File). MultipartFile is NOT an entity. So we extract all FILE data into the entity(JSON object) and save only the entity.
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImage(imageFile.getBytes());
        return repo.save(product);                               //here the File data (image) is extracted using JSON data object (product) and is stored in JSON data objects (imageName,imageDate). Thus File data is extracted into JSON data.Thus, all the image data (File data) is stored in 'product'(JSON data object)
    }

    public Product updateProducts(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImage(imageFile.getBytes());
        product.setImageType(imageFile.getContentType());
        product.setImageName(imageFile.getOriginalFilename());

       return repo.save(product);
    }

    public void deleteProducts(int id){
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword){
        return repo.searchProducts(keyword);
    }
}
