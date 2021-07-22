package challenge1springboot.socialmeli.repositories;

import challenge1springboot.socialmeli.entities.Sale;
import challenge1springboot.socialmeli.globalconstants.Reference;
import challenge1springboot.socialmeli.utils.JSONReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SaleRepository {
    public Sale save(int userId, int id_post, int product_id, boolean hasPromo, double discount) throws IOException {

        Sale sale = new Sale(userId, id_post, product_id, hasPromo, discount);
        List<Sale> sales = loadFromJSON();
        sales.add(sale);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(ResourceUtils.getFile(Reference.PATH_RESOURCE_SALE), sales);

        return sale;
    }

    public List<Sale> loadFromJSON() throws IOException {
        File file = JSONReader.readJSONFile(Reference.PATH_RESOURCE_SALE);
        return mapObject(file);
    }

    public int getSaleByUser(int userId) throws IOException {
        return (int) loadFromJSON().stream().filter(sale -> sale.getUserId() == userId).count();
    }

    // return only the sales that are enabled at the moment : hasPromo = true
    public List<Sale> listSalesPublishedByUser(int userId) {
        try {
            return loadFromJSON()
                    .stream()
                    .filter(sale -> sale.getUserId() == userId && sale.isInSale())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<Sale> mapObject(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Sale>> typeReference = new TypeReference<>() {
        };
        return objectMapper.readValue(file, typeReference);
    }
}
