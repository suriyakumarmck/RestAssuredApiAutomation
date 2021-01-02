package tests;

import FrameworkCore.LoggerHook;
import io.restassured.response.Response;
import library.CommentApiHelper;
import library.PostApiHelper;
import library.UserApiHelper;
import models.Comment;
import models.Post;
import models.User;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.EmailUtils;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class UserPostCommentsPositiveTest {

    private Response allCommentsOnUserPostsResponse;
    private int userId;
    private List<Post> allPostsByUser;
    private List<Comment> allCommentsOnUserPosts;
    private Logger log = LoggerHook.log;

    @Test
    public void validateGetAllUsers() {
        UserApiHelper.getAllUsers()
                .then()
                .assertThat()
                .body("size()", greaterThan(0));
    }

    @Test
    @Parameters({"userName"})
    public void validateGetUser(String userName) {
        List<User> users = Arrays.asList(UserApiHelper.getUser(userName).getBody().as(User[].class));
        if (users.size() > 0) {
            userId = users.get(0).getId();
            log.info("userId for the username " + userName + " = " + userId);
        } else
            Assert.fail("No user found with username = " + userName);
    }

    @Test(dependsOnMethods = "validateGetUser")
    public void validateGetPostsByUserId() {
        allPostsByUser = Arrays.asList(PostApiHelper.getPosts(userId).getBody().as(Post[].class));
        allPostsByUser.forEach(post -> Assert.assertTrue(post.getUserId()==userId));
    }

    @Test(dependsOnMethods = "validateGetPostsByUserId")
    public void validatePostCommentsEmail() {
        allPostsByUser.forEach(post -> {
            int postId = post.getId();
            allCommentsOnUserPostsResponse = CommentApiHelper.getCommentsOnPost(postId);
            allCommentsOnUserPosts = Arrays.asList(allCommentsOnUserPostsResponse.getBody().as(Comment[].class));
            allCommentsOnUserPosts.forEach(comment -> {
                Assert.assertTrue(EmailUtils.isValidEmailAddress(comment.getEmail()));
            });
        });
    }

    @Test(dependsOnMethods = {"validateGetUser"})
    @Parameters({"postTitle", "postBody"})
    public void validateCreatePost(String postTitle, String postBody) {
        PostApiHelper.createPost(postTitle, postBody, userId)
                .then()
                .assertThat()
                .body("title", equalTo(postTitle))
                .body("body", equalTo(postBody))
                .body("userId", equalTo(userId));
    }

    @Test(dependsOnMethods = {"validateGetUser"})
    @Parameters({"updatedPostTitle", "fakePostId"})
    public void validateUpdatePost(String updatedPostTitle, String postId) {
        PostApiHelper.updatePost("title", updatedPostTitle, postId)
                .then()
                .assertThat()
                .body("title", equalTo(updatedPostTitle));
    }

    @Test(dependsOnMethods = {"validateGetUser"})
    @Parameters({"fakePostId"})
    public void validateDeletePost(String postId) {
        PostApiHelper.deletePost(postId)
                .then()
                .assertThat()
                .body(equalTo("{}"));
    }

}
