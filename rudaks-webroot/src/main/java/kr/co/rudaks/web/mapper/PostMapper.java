package kr.co.rudaks.web.mapper;

import java.util.HashMap;
import java.util.List;

import kr.co.rudaks.web.bean.AttachFileForm;
import kr.co.rudaks.web.bean.GuestbookForm;
import kr.co.rudaks.web.bean.PostForm;

public interface PostMapper
{   
    public Integer selectLastInsertId();
    public List<HashMap<String, Object>> selectCategoryList();
    public List<HashMap<String, Object>> selectCategoryListByCount();
    public HashMap<String, Object> selectCategoryByCategory(String category);
    public PostForm selectPost(int postId);
    public List<AttachFileForm> selectAttachFileList(int postId);
    public List<PostForm> selectRecentPostList(int count);
    public int selectPostListCount(PostForm postForm);
    public List<PostForm> selectPostList(PostForm postForm);
    
	public int insertPost(PostForm postForm);
	public int updatePost(PostForm postForm);
	public int deletePost(int id);
	
	public Integer insertAttachFile(AttachFileForm attachFileForm);
	public Integer deleteAttachFileByPostId(int postId);
	
	public PostForm selectPostBySeq(int seq);
	
	public Integer selectNextSeq(String seqName);
	
	public Integer insertGuestbook(GuestbookForm guestbookForm);
	
	public Integer selectGuestbookListCount(GuestbookForm guestbookForm);
	public List<GuestbookForm> selectGuestbookList(GuestbookForm guestbookForm);
	
	public GuestbookForm selectGuestbook(int id);
	
	public Integer updateGuestbook(GuestbookForm guestbookForm);
	public Integer deleteGuestbook(GuestbookForm guestbookForm);
	
}
