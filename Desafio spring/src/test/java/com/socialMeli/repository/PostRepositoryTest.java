package com.socialMeli.repository;

import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.model.PostModel;
import com.socialMeli.model.ProductModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class PostRepositoryTest {
   final PostRepository postRepository = new PostRepository();

   @Test
    public void createModel() throws ModelAlreadyExists, ModelNotExists {
       PostModel postModel = PostModel.builder().id(10).build();
       postRepository.insert(postModel);

       Assertions.assertEquals(10, postRepository.findById(10).getId());
   }

   @Test
   public void modify() throws ModelAlreadyExists, ModelNotExists {
      PostModel postModel = PostModel.builder().id(10).detail(ProductModel.builder().build()).build();
      postRepository.insert(postModel);

      postModel.getDetail().setNotes("A note");
      postRepository.modify(postModel);
      Assertions.assertEquals("A note", postRepository.findById(10).getDetail().getNotes());
   }

   @Test
   public void delete() throws ModelNotExists, ModelAlreadyExists {
      PostModel postModel = PostModel.builder().userId(10).build();
      postRepository.insert(postModel);
      postRepository.delete(postModel);
      try{
         postRepository.findById(10);
         Assertions.fail();
      }catch (ModelNotExists e){
         Assertions.assertTrue(true);
      }
   }

   @BeforeEach
   public void deleteFileTest(){
      final String FILE_NAME = "./post.json";
      File file;
      file = new File(FILE_NAME);
      if (file.delete()) {
         System.out.println("File deleted: " + file.getName());
      }
   }

}
