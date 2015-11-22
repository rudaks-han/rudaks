package kr.co.rudaks.web.service;

import java.util.HashMap;
import java.util.List;

import kr.co.rudaks.web.bean.AttachFileForm;
import kr.co.rudaks.web.bean.GuestbookForm;
import kr.co.rudaks.web.bean.PostForm;

public interface IPostService
{
    Integer selectLastInsertId();
    List<HashMap<String, Object>> selectCategoryList();
    List<HashMap<String, Object>> selectCategoryListByCount();
    HashMap<String, Object> selectCategoryByCategory(String category);
    PostForm selectPost(int id);
    List<AttachFileForm> selectAttachFileList(int postId);
    List<PostForm> selectRecentPostList(int count);
    int selectPostListCount(PostForm postForm);
    List<PostForm> selectPostList(PostForm postForm);
    
    Integer insertPost(PostForm postForm);
    Integer updatePost(PostForm postForm);
    Integer deletePost(int id);
    PostForm selectPostBySeq(int seq);
    
    Integer selectNextSeq(String seqName);
    
    // guestbook
    Integer insertGuestbook(GuestbookForm guestbookForm);
    
    Integer selectGuestbookListCount(GuestbookForm guestbookForm);
    List<GuestbookForm> selectGuestbookList(GuestbookForm guestbookForm);
    GuestbookForm selectGuestbook(int id);
    Integer updateGuestbook(GuestbookForm guestbookForm);
    Integer deleteGuestbook(GuestbookForm guestbookForm);
}
