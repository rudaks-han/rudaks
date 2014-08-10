package kr.co.rudaks.web.validator;

import java.util.regex.Pattern;

import kr.co.rudaks.web.WebPublic;
import kr.co.rudaks.web.bean.PostForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PostModifyValidator implements Validator 
{
	public boolean supports(Class<?> clazz)
	{
		return clazz.equals(PostForm.class);
	}
	
	public void validate(Object obj, Errors errors)
	{
		PostForm postForm = (PostForm)obj;
		if (postForm != null)
		{	
		    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "required.category", "카테고리를 선택하세요.");
		    //ValidationUtils.rejectIfEmpty(errors, "username", "required.username", "이름을 입력하세요.");
		    
		    if (postForm.getUsername().length() < 2)
		    {
		        errors.rejectValue("username", "required.username", "이름을 입력하세요.");
		    }
		    
		    Pattern emailPattern = Pattern.compile(WebPublic.EMAIL_REGEXT_PATTERN);
		    if (!emailPattern.matcher(postForm.getEmail().trim()).matches())
		    {
		        errors.rejectValue("email", "required.email", "유효한 이메일을 입력하세요.");
		    }
		    
		    ValidationUtils.rejectIfEmpty(errors, "title", "required.title", "제목을 입력하세요.");
		    ValidationUtils.rejectIfEmpty(errors, "content", "required.content", "내용을 입력하세요.");
		}		
	}
}
