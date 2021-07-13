package com.socialMeli.repository;

import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.model.PostModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class PostRepositoryTest {
   PostRepository postRepository = new PostRepository();

   @Test
    public void createModel() throws ModelAlreadyExists, ModelNotExists {
       PostModel postModel = new PostModel();
       postModel.setId(10);
       postRepository.insert(postModel);

       Assertions.assertEquals(10, postRepository.findById(10).getId());
   }

   @Test
   public void modify() throws ModelAlreadyExists, ModelNotExists {
      PostModel postModel = new PostModel();
      postModel.setId(10);
      postRepository.insert(postModel);

      postModel.setNotes("A note");
      postRepository.modify(postModel);
      Assertions.assertEquals("A note", postRepository.findById(10).getNotes());
   }

   @Test
   public void delete() throws ModelNotExists, ModelAlreadyExists {
      PostModel postModel = new PostModel();
      postModel.setId(10);
      postRepository.insert(postModel);
      postRepository.delete(postModel);
      try{
         postRepository.findById(10);
         Assertions.assertTrue(false);
      }catch (ModelNotExists e){
         Assertions.assertTrue(true);
      }
   }

   @BeforeEach
   public void deleteFileTest(){
      final String FILE_NAME = "./post.json";
      File file = null;
      file = new File(FILE_NAME);
      if (file.delete()) {
         System.out.println("File deleted: " + file.getName());
      }
   }

}
