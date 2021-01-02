package tests;

import library.CommentApiHelper;
import library.PostApiHelper;
import library.UserApiHelper;
import models.Comment;
import models.Post;
import models.User;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class UserPostCommentsNegativeTest {

    @Test
    @Parameters({"invalidUserName"})
    public void validateGetUserWithInvalidNameReturnsAnEmptyArray(String invalidUserName) {
        List<User> users = Arrays.asList(UserApiHelper.getUser(invalidUserName).getBody().as(User[].class));
        Assert.assertEquals("One or more users found with name " + invalidUserName, users.size(), 0);
    }

    @Test
    @Parameters({"invalidUserId"})
    public void validateGetPostsByInvalidUserIdReturnsAnEmptyArray(String invalidUserId) {
        int invalidUserIdInt = Integer.parseInt(invalidUserId);
        List<Post> posts = Arrays.asList(PostApiHelper.getPosts(invalidUserIdInt).getBody().as(Post[].class));
        Assert.assertEquals("No Post has been found under invalid userId " + invalidUserIdInt, posts.size(), 0);
    }

    @Test
    @Parameters({"invalidPostId"})
    public void validateGetPostCommentsByInvalidPostIdReturnsAnEmptyArray(String invalidPostId) {
        int invalidPostIdInt = Integer.parseInt(invalidPostId);
        List<Comment> comments = Arrays.asList(CommentApiHelper.getCommentsOnPost(invalidPostIdInt).getBody().as(Comment[].class));
        Assert.assertEquals("No comments found on the post with invalid postId " + invalidPostId, comments.size(), 0);
    }

}
