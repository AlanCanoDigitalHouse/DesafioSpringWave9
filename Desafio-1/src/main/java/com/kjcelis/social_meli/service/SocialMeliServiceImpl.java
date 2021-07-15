package com.kjcelis.social_meli.service;

import com.kjcelis.social_meli.controllers.UserController;
import com.kjcelis.social_meli.dto.BuyerDTO;
import com.kjcelis.social_meli.dto.PostDTO;
import com.kjcelis.social_meli.dto.SellerDTO;
import com.kjcelis.social_meli.dto.response.BuyerListPostResDTO;
import com.kjcelis.social_meli.dto.response.SellerListPpostResDTO;
import com.kjcelis.social_meli.repositories.buyer.BuyerRepository;
import com.kjcelis.social_meli.repositories.seller.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.*;

import static java.time.temporal.ChronoUnit.WEEKS;

@Service
public class SocialMeliServiceImpl implements SocialMeliService {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    public SocialMeliServiceImpl(BuyerRepository buyerRepository, SellerRepository sellerRepository) {
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }


    public void initInfo() {
        try {
            buyerRepository.initListBuyer();
            sellerRepository.initListSeller();
        } catch (Exception e) {
            logger.error("initInfo()", e.getMessage());
        }
    }


    @Override
    public String followSeller(Integer userId, Integer userIdToFollow) {
        String message;
        if (sellerRepository.findSellerById(userIdToFollow).isPresent() && buyerRepository.findBuyerById(userId).isPresent()) {
            buyerRepository.saveFollowS(userId, sellerRepository.findSellerById(userIdToFollow));
            sellerRepository.saveFollowB(userIdToFollow, buyerRepository.findBuyerById(userId));
            message = "Status code " + HttpStatus.OK.toString();
        } else {
            message = "Status code " + HttpStatus.NOT_FOUND.toString();
        }
        return message;

    }


    @Override
    public SellerDTO getCountUsersFV(Integer userId) {
        SellerDTO sellerDTO;
        SellerDTO sellerDTO1 = null;
        try {
            sellerDTO = sellerRepository.findSellerById(userId).get();
            sellerDTO1 = new SellerDTO(sellerDTO.getUserId(), sellerDTO.getUserName(), sellerDTO.getFollowers_count());

        } catch (Exception e) {
            logger.error("EL usuario no es valido en getCountUsersFV()", e.getMessage());
        }
        return sellerDTO1;
    }


    @Override
    public SellerDTO getListFollowS(Integer userId, String order) {
        SellerDTO sellerDTO;
        SellerDTO sellerDTO1 = null;
        try {
            sellerDTO = sellerRepository.findSellerById(userId).get();
            ArrayList<BuyerDTO> buyerDTOS = sellerDTO.getFollowers();
            if (order != null) {
                if (order.equals("name_asc")) {
                    Collections.sort(buyerDTOS, Comparator.comparing(BuyerDTO::getUserName).reversed());
                }
                if (order.equals("name_desc")) {
                    Collections.sort(buyerDTOS, Comparator.comparing(BuyerDTO::getUserName));
                }
            }
            sellerDTO1 = new SellerDTO(sellerDTO.getUserId(), sellerDTO.getUserName(), sellerDTO.getFollowers());
        } catch (Exception e) {
            logger.error("Error parametros invalidos en getListFollowS()", e.getMessage());
        }
        return sellerDTO1;
    }


    @Override
    public String saveNewPost(PostDTO postDTO) {
        String message;
        try {
            sellerRepository.savePost(sellerRepository.findSellerById(postDTO.getUserId()), postDTO);
            message = "Status code " + HttpStatus.OK.toString();
        } catch (Exception e) {
            message = "Status code " + HttpStatus.NOT_FOUND.toString();
        }
        return message;
    }


    public BuyerDTO getListFollowedB(Integer userId, String order) {
        BuyerDTO buyerDTO;
        BuyerDTO buyerDTO1 = null;
        try {
            buyerDTO = buyerRepository.findBuyerById(userId).get();
            ArrayList<SellerDTO> sellerDTOS = buyerDTO.getFollowed();
            if (order != null) {
                if (order.equals("name_asc")) {
                    Collections.sort(sellerDTOS, Comparator.comparing(SellerDTO::getUserName).reversed());
                }
                if (order.equals("name_desc")) {
                    Collections.sort(sellerDTOS, Comparator.comparing(SellerDTO::getUserName));
                }
            }
            buyerDTO1 = new BuyerDTO(buyerDTO.getUserId(), buyerDTO.getUserName(), buyerDTO.getFollowed());
        } catch (Exception e) {
            logger.error("Error parametros invalidos en getListFollowedB()", e.getMessage());
        }


        return buyerDTO1;
    }

    @Override
    public BuyerListPostResDTO getPostListB(Integer userId, String order) {
        BuyerDTO buyerDTO;
        ArrayList<PostDTO> posts = new ArrayList<>();
        BuyerListPostResDTO buyerListPostResDTO = null;
        try {
            buyerDTO = buyerRepository.findBuyerById(userId).get();
            ArrayList<SellerDTO> sellerDTOS = buyerDTO.getFollowed();
            for (SellerDTO s : sellerDTOS) {
                ArrayList<PostDTO> posts2 = sellerRepository.findSellerById(s.getUserId()).get().getPosts();
                for (PostDTO p : posts2) {
                    if (weeksBetween(p.getDate()) <= 2) {
                        posts.add(p);
                    }
                }
            }
            Collections.sort(posts, Comparator.comparing(PostDTO::getDate).reversed());
            orderParam(order, posts);
            buyerListPostResDTO = new BuyerListPostResDTO(buyerDTO.getUserId(), posts);
        } catch (Exception e) {
            logger.error("Error parametros invalidos en getPostListB()", e.getMessage());
        }
        return buyerListPostResDTO;
    }

    private void orderParam(String order, ArrayList<PostDTO> posts) {
        if (order != null) {
            if (order.equals("date_desc")) {
                Collections.sort(posts, Comparator.comparing(PostDTO::getDate).reversed());
            }
            if (order.equals("date_asc")) {
                Collections.sort(posts, Comparator.comparing(PostDTO::getDate));
            }
        }
    }

    @Override
    public String unfollowSeller(Integer userId, Integer userIdToUnfollow) {
        String message = "";
        try {
            BuyerDTO buyerDTO = buyerRepository.findBuyerById(userId).get();
            buyerRepository.unfollowSelle(buyerDTO, userIdToUnfollow);
            SellerDTO sellerDTO = sellerRepository.findSellerById(userIdToUnfollow).get();
            sellerRepository.unfollowedBuye(sellerDTO, userId);
            message = "The buyer stopped following the seller";

        } catch (Exception e) {
            logger.error("Error parametros invalidos en unfollowSeller()", e.getMessage());
        }

        return message;
    }

    @Override
    public String saveNewPromoPost(PostDTO pPostDTO) {
        String message;
        try {
            sellerRepository.savePromoPost(sellerRepository.findSellerById(pPostDTO.getUserId()), pPostDTO);
            message = "Status code " + HttpStatus.OK.toString();
        } catch (Exception e) {
            message = "Status code " + HttpStatus.NOT_FOUND.toString();
            logger.error("Error parametros invalidos en saveNewPromoPost()", e.getMessage());
        }
        return message;
    }


    public SellerListPpostResDTO getCountPromoSeller(Integer userId) {
        SellerDTO sellerDTO;
        SellerListPpostResDTO sellerListPpostResDTO = null;
        try {
            sellerDTO = sellerRepository.findSellerById(userId).get();
            sellerListPpostResDTO = new SellerListPpostResDTO(sellerDTO.getUserId(), sellerDTO.getUserName(), sellerDTO.getPromoproducts_count());
        } catch (Exception e) {
            logger.error("Error parametros invalidos en getCountPromoSeller()", e.getMessage());
        }

        return sellerListPpostResDTO;
    }

    @Override
    public SellerListPpostResDTO getListProdSeller(Integer userId) {
        SellerDTO sellerDTO;
        SellerListPpostResDTO sellerListPpostResDTO = null;
        try {
            sellerDTO = sellerRepository.findSellerById(userId).get();
            sellerListPpostResDTO = new SellerListPpostResDTO(sellerDTO.getUserId(), sellerDTO.getUserName(), sellerDTO.getPost_promo());
        } catch (Exception e) {
            logger.error("Error parametros invalidos en getListProdSeller()", e.getMessage());
        }
        return sellerListPpostResDTO;
    }

    public static long weeksBetween(Date date1) {
        Long numw = null;
        try {
            Date date2 = new Date();
            numw = WEEKS.between(date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } catch (Exception e) {
            logger.error("Error parametros invalidos en weeksBetween()", e.getMessage());
        }

        return numw;
    }

}

