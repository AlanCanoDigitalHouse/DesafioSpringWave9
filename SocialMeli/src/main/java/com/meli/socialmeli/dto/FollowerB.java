package com.meli.socialmeli.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowerB implements Comparable<FollowerB>{
        private Integer userId;
        private String userName;

        public Integer getUserId() {
                return userId;
        }

        public void setUserId(Integer userId) {
                this.userId = userId;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        @Override
        public int compareTo(FollowerB u) {
                if (getUserName() == null || u.getUserName() == null) {
                        return 0;
                }
                return getUserName().compareTo(u.getUserName());
        }

}
