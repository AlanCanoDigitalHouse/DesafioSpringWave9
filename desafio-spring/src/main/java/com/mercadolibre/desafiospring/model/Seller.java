package com.mercadolibre.desafiospring.model;

import com.mercadolibre.desafiospring.exception.user.AlreadyFollowingException;
import com.mercadolibre.desafiospring.exception.user.NotAFollowerException;
import lombok.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

@Getter
@Setter
public class Seller extends User {
    private List<Client> followers;
    private List<Post> posts;
    private List<Post> promoPosts;

    public Seller(int id, String name) {
        super(id, name);
        this.followers = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.promoPosts = new ArrayList<>();
    }

    public void addFollower(Client client) throws AlreadyFollowingException {
        if (this.followers.contains(client)) {
            throw new AlreadyFollowingException();
        }

        this.followers.add(client);
    }

    public int getNumberOfFollowers() {
        return this.followers.size();
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public void addPromoPost(Post post) {
        this.promoPosts.add(post);
    }

    public List<Post> getLastsPosts() {
        Date twoWeeksAgo = Date.from(
                LocalDate.now().minusWeeks(2).atStartOfDay(
                        ZoneId.systemDefault()).toInstant());

        return Stream.concat(this.posts.stream(),
                this.promoPosts.stream()).filter((post) -> {
            return post.getDate().compareTo(twoWeeksAgo) > 0;
        }).collect(Collectors.toList());
    }

    public void removeFollower(Client client) throws NotAFollowerException {
        if (!followers.contains(client)) {
            throw new NotAFollowerException();
        }

        this.followers.remove(client);
    }

    public List<Client> getSortedFollowers(String order) {
        return this.followers.stream().sorted(User.sortings.get(order))
                .collect(Collectors.toList());
    }

    public List<Post> getSortedPromoPosts(String order) {
        return this.promoPosts.stream().sorted(Post.sortings.get(order))
                .collect(Collectors.toList());
    }
}
