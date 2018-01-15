package org.nantic.rest.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nantic.assembler.ProductAssembler;
import org.nantic.dao.ProductRepository;
import org.nantic.dao.TransactionRepository;
import org.nantic.dao.UserRepository;
import org.nantic.dto.DTOProduct;
import org.nantic.entites.Product;
import org.nantic.entites.Transaction;
import org.nantic.entites.User;
import org.nantic.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected TransactionRepository transactionRepository;

    @Autowired
    protected UserRepository userRepository;

    private void setCorsHeaders(HttpServletResponse res) {
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        res.setHeader("Access-Control-Max-Age", "1209600");
    }

    @GetMapping
    public List<DTOProduct> getProducts(HttpServletResponse res){
        setCorsHeaders(res);
        List<DTOProduct> productDTOs = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            productDTOs.add(ProductAssembler.createDTO(product));
        });
        return productDTOs;
    }

    @RequestMapping(value="/store", method = RequestMethod.OPTIONS)
    public void storeProductOptions( HttpServletResponse res){
        setCorsHeaders(res);
    }


    @PostMapping("/store")
    public String storeProducts(HttpServletRequest req, HttpServletResponse res){
        setCorsHeaders(res);
        try {
            JSONObject jsonObject = JSONUtil.requestToJSONArray(req);
            String jsonObjectReal = (String) jsonObject.get("Method");
            JSONArray array = new JSONArray(jsonObjectReal);
            Date date = new Date();
            Long time = date.getTime();
            ArrayList<Transaction> transactionList = new ArrayList<>();
            for (Object object : array) {
                JSONObject temp = (JSONObject) object;
                Transaction trans = new Transaction();
                Product prod = new Product();
                prod.setId(temp.getInt("id"));
                prod.setName(temp.getString("name"));
                prod.setPrice(temp.getInt("price"));
                List<User> users = userRepository.findAll();
                User use = null;
                for (User currentUser:users) {
                    if (currentUser.getUsername().equals(temp.getString("user"))) {
                        use = currentUser;
                        break;
                    }
                }
                trans.setProductBean(prod);
                trans.setQuantity(temp.getInt("quantity"));
                trans.setUser(use);
                trans.setRecieptId(time.toString());
                trans.setDate(date.toString());
                trans.setTotal(temp.getInt("quantity") * temp.getInt("price"));
                transactionRepository.save(trans);
                transactionList.add(trans);
            }
////            controlerRemote.storeTransactions(transactionList);
//            for(Transaction trans:transactionList) {
//                transactionRepository.save(trans);
//            }

            return "{\"status\":\"OK\"}";
        } catch (IOException e) {
            return "{\"status\":\"FAIL\"}";
        }
    }

}
