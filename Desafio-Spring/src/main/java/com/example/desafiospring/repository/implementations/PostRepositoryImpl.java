package com.example.desafiospring.repository.implementations;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.responses.PostResponseDTO;
import com.example.desafiospring.DTOS.responses.PromoPostResponseDTO;
import com.example.desafiospring.entities.PostEntity;
import com.example.desafiospring.exceptions.general.DBNotAvailableException;
import com.example.desafiospring.repository.interfaces.PostRepository;
import com.example.desafiospring.repository.interfaces.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {

    public static final java.lang.String POSTS_DB_ROUTE = "classpath:static/posts.json";

    private static final AtomicInteger autoIncrement = new AtomicInteger(getNextIDFromDB());

    ProductRepository productRepository;

    public PostRepositoryImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Integer addPost(NewPostRequestDTO newPostRequestDTO, Integer productId) {
        Integer postId = autoIncrement.getAndIncrement();
        addPostToDB(new PostEntity(postId,
                newPostRequestDTO.getUserId(),
                newPostRequestDTO.getDate(),
                productId,
                newPostRequestDTO.getCategory(),
                newPostRequestDTO.getPrice(),
                newPostRequestDTO.getHasPromo(),
                newPostRequestDTO.getDiscount()));
        return postId;
    }

    @Override
    public List<PostResponseDTO> getRecentPostsOf(List<Integer> userIds, String order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Comparator<PostEntity> c = getDateComparator(order, formatter);
        return getDatabasePosts().stream()
                .filter(p -> userIds.contains(p.getUserId()))
                .filter(p -> {
                    long days = ChronoUnit.DAYS.between(LocalDate.parse(p.getDate(), formatter), LocalDate.now());
                    return days <= 14 && days >= 0;
                })
                .sorted(c)
                .map(pe -> new PostResponseDTO(
                        pe.getPostId(),
                        pe.getDate(),
                        productRepository.getProductResponseDTO(pe.getProductId()),
                        pe.getCategory(),
                        pe.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Set<Integer> getPromoProductIDs(Integer userId) {
        return getDatabasePosts().stream()
                .filter(p -> Boolean.TRUE.equals(p.getHasPromo()) &&
                        p.getUserId().equals(userId))
                .map(PostEntity::getProductId).collect(Collectors.toSet());
    }

    @Override
    public List<PromoPostResponseDTO> getPromoPostsOf(Integer userId, String order) {
        Comparator<PostEntity> c = getProductNameComparator(order);
        return getDatabasePosts().stream()
                .filter(p -> userId.equals(p.getUserId()) && Boolean.TRUE.equals(p.getHasPromo()))
                .sorted(c)
                .map(pe -> new PromoPostResponseDTO(
                        pe.getPostId(),
                        pe.getDate(),
                        productRepository.getProductResponseDTO(pe.getProductId()),
                        pe.getCategory(),
                        pe.getPrice(),
                        pe.getHasPromo(),
                        pe.getDiscount()))
                .collect(Collectors.toList());
    }

    private Comparator<PostEntity> getProductNameComparator(String order) {
        Comparator<PostEntity> c = Comparator.comparing(p -> productRepository.getProductNameByID(p.getProductId()));
        return "name_desc".equals(order) ? c.reversed() : c;
    }

    private Comparator<PostEntity> getDateComparator(String order, DateTimeFormatter formatter) {
        Comparator<PostEntity> c = Comparator.comparing(p -> LocalDate.parse(p.getDate(), formatter));
        return "date_asc".equals(order) ? c : c.reversed();
    }

    private void addPostToDB(PostEntity post) {
        List<PostEntity> dbElements = getDatabasePosts();
        dbElements.add(post);
        overWritePostsDB(dbElements);
    }

    private void overWritePostsDB(List<PostEntity> postsToWrite) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(ResourceUtils.getFile(POSTS_DB_ROUTE), postsToWrite);
        } catch (IOException e) {
            throw new DBNotAvailableException("Error writing to DB", e);
        }
    }

    private static List<PostEntity> getDatabasePosts() {
        File file;
        try {
            file = ResourceUtils.getFile(POSTS_DB_ROUTE);
        } catch (Exception e) {
            throw new DBNotAvailableException("Error finding DB", e);
        }
        return loadFromJSON(file);
    }

    private static List<PostEntity> loadFromJSON(File file) throws DBNotAvailableException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PostEntity>> typeReference = new TypeReference<>() {
        };
        List<PostEntity> posts;
        try {
            posts = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            throw new DBNotAvailableException("Error reading DB", e);
        }
        return posts;
    }

    private static int getNextIDFromDB() {
        int i = 1;
        if (getDatabasePosts().size() > 0) {
            //noinspection OptionalGetWithoutIsPresent
            i = getDatabasePosts().stream().max(Comparator.comparing(PostEntity::getPostId)).get().getPostId() + 1;
        }
        return i;
    }
}
