package com.mercado_libre.bootcamp.spring.desafio.services.user;

import com.mercado_libre.bootcamp.spring.desafio.dtos.SellerDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowedListResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowedProductResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.models.Post;
import com.mercado_libre.bootcamp.spring.desafio.models.Seller;
import com.mercado_libre.bootcamp.spring.desafio.models.User;
import com.mercado_libre.bootcamp.spring.desafio.repositories.user.UserRepositoryImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.relation.RelationServiceImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.strategies.SortUserStrategy;
import com.mercado_libre.bootcamp.spring.desafio.utils.PostUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepositoryImpl userRepository;

    private RelationServiceImpl relationService;

    private final SortUserStrategy sortUserStrategy;

    @Override
    public User getUser(int userid) {
        return userRepository.getUser(userid);
    }

    @Override
    public FollowedListResponseDTO getFollowedList(int userId, String order) {

        User follower = userRepository.getUser(userId);

        List<User> sellers = (List<User>) (List<?>) relationService.getFollowedSellers(follower);

        sortUserStrategy.getImplementation(order).sort(sellers);

        List<SellerDTO> sellersDTO = sellers.stream()
                .map(SellerDTO::new)
                .collect(Collectors.toList());

        return new FollowedListResponseDTO(follower.getUserId(), follower.getUserName(), sellersDTO);
    }

    @Override
    public FollowedProductResponseDTO getFollowedProducts(int userId, String order) {

        User user = userRepository.getUser(userId);

        LocalDate dateTwoWeeksFromNow = LocalDate.now().minusWeeks(2);

        List<Post> posts = relationService.getFollowedSellers(user).stream()
                .map(Seller::getPosts) // obtengo todos los posts
                .flatMap(List::stream) // vuelco todos los post en una lista
                .filter(x -> x.getDate().isAfter(dateTwoWeeksFromNow)) // filtro aquellos posts de las ultimas semanas
                .collect(Collectors.toList());

        PostUtils.sortPostsByParam(posts, order);

        return new FollowedProductResponseDTO(userId, posts);
    }
}
