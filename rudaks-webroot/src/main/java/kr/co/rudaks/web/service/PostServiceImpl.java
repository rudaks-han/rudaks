package kr.co.rudaks.web.service;

import java.util.HashMap;
import java.util.List;

import kr.co.rudaks.web.WebConfig;
import kr.co.rudaks.web.bean.AttachFileForm;
import kr.co.rudaks.web.bean.GuestbookForm;
import kr.co.rudaks.web.bean.PostForm;
import kr.co.rudaks.web.mapper.PostMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService
{
	private final static Logger logger = (Logger) LoggerFactory.getLogger(PostServiceImpl.class);
	
	@Autowired
	PostMapper postMapper;
	
	/**
	 * 입력됬던 마지막 id값 가져오기
	 */
	public Integer selectLastInsertId()
	{
	    return postMapper.selectLastInsertId();
	}
	
	/**
	 * 카테고리 목록 가져오기
	 */
	@Cacheable("categoryCache")
	public List<HashMap<String, Object>> selectCategoryList()
	{
	    return postMapper.selectCategoryList();
	}
	
	@Cacheable("postCache")
    public List<HashMap<String, Object>> selectCategoryListByCount()
    {
        return postMapper.selectCategoryListByCount();
    }
	
	@Cacheable("postCache")
    public HashMap<String, Object> selectCategoryByCategory(String category)
    {
        return postMapper.selectCategoryByCategory(category);
    }
	
	/**
	 * Post 정보 가져오기
	 */
	@Cacheable("postCache")
	public PostForm selectPost(int id)
    {
        return postMapper.selectPost(id);
    }
	
	/**
	 * 첨부파일 리스트 가져오기
	 */
	//@Cacheable("postCache")
	public List<AttachFileForm> selectAttachFileList(int postId)
	{
	    return postMapper.selectAttachFileList(postId);
	}
	
	/**
	 * 최근 등록된 post 가져오기
	 */
	@Cacheable("postCache")
	public List<PostForm> selectRecentPostList(int count)
	{
	    return postMapper.selectRecentPostList(count);
	}
	
	/**
     * 최근 등록된 post 개수 가져오기
     */
	//@Cacheable(value = "postCache", key = "#p0.id")
    public int selectPostListCount(PostForm postForm)
    {
        return postMapper.selectPostListCount(postForm);
    }
    
	/**	
     * 최근 등록된 post 가져오기
     */
	@Cacheable(value = "postCache", key="#p0")
    public List<PostForm> selectPostList(PostForm postForm)
    {
        return postMapper.selectPostList(postForm);
    }  
	 
	/**
	 * Post 입력
	 */
	@CacheEvict(value = "postCache", allEntries = true)
	public Integer insertPost(PostForm postForm)
	{
	    postMapper.insertPost(postForm);
	    
	    int postId = postMapper.selectLastInsertId();
	    
	    // 첨부파일 입력
	    if (postForm.getAttachFileList() != null && postForm.getAttachFileList().size() > 0)
	    {
	        List<AttachFileForm> attachFileList = postForm.getAttachFileList();
	        for (int i=0; i<attachFileList.size(); i++)
	        {
	            AttachFileForm attachFileForm = attachFileList.get(i);
	            attachFileForm.setPostId(postId);
	            postMapper.insertAttachFile(attachFileForm);
	        }
	    }
		return postId;
	}
	
	@CacheEvict(value = "postCache", allEntries = true)
	public Integer updatePost(PostForm postForm)
    {
        postMapper.updatePost(postForm);
        
        postMapper.deleteAttachFileByPostId(postForm.getId());
        
        // 첨부파일 입력
        if (postForm.getAttachFileList() != null && postForm.getAttachFileList().size() > 0)
        {
            List<AttachFileForm> attachFileList = postForm.getAttachFileList();
            for (int i=0; i<attachFileList.size(); i++)
            {
                AttachFileForm attachFileForm = attachFileList.get(i);
                attachFileForm.setPostId(postForm.getId());
                postMapper.insertAttachFile(attachFileForm);
            }
        }
        return postForm.getId();
    }
	
	@CacheEvict(value = "postCache", allEntries = true)
	public Integer deletePost(int id)
	{
	    postMapper.deletePost(id);
	    
	    postMapper.deleteAttachFileByPostId(id);
	    
	    return 1;
	}
	
	@Cacheable("postCache")
	public PostForm selectPostBySeq(int seq)
    {
        return postMapper.selectPostBySeq(seq);
    }
	
	public Integer selectNextSeq(String seqName)
	{
	    return postMapper.selectNextSeq(seqName);
	}
	
	@CacheEvict(value = "guestbookCache", allEntries = true)
	public Integer insertGuestbook(GuestbookForm guestbookForm)
	{
	    return postMapper.insertGuestbook(guestbookForm);
	}
	
	//@Cacheable("guestbookCache")
	public Integer selectGuestbookListCount(GuestbookForm guestbookForm)
    {
        return postMapper.selectGuestbookListCount(guestbookForm);
    }
	
	@Cacheable("guestbookCache")
	public List<GuestbookForm> selectGuestbookList(GuestbookForm guestbookForm)
    {
        return postMapper.selectGuestbookList(guestbookForm);
    }
	
	@Cacheable("guestbookCache")
	public GuestbookForm selectGuestbook(int id)
	{
	    return postMapper.selectGuestbook(id);
	}
	
	@CacheEvict(value = "guestbookCache", allEntries = true)
	public Integer updateGuestbook(GuestbookForm guestbookForm)
	{
	    return postMapper.updateGuestbook(guestbookForm);
	}
	
	@CacheEvict(value = "guestbookCache", allEntries = true)
	public Integer deleteGuestbook(GuestbookForm guestbookForm)
    {
        return postMapper.deleteGuestbook(guestbookForm);
    }
}
