package kr.co.rudaks.web.validator;

import java.util.regex.Pattern;

import kr.co.rudaks.web.WebPublic;
import kr.co.rudaks.web.bean.GuestbookForm;
import kr.co.rudaks.web.bean.PostForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class GuestbookNewValidator implements Validator 
{
	public boolean supports(Class<?> clazz)
	{
		return clazz.equals(PostForm.class);
	}
	
	public void validate(Object obj, Errors errors)
	{
		GuestbookForm guestbookForm = (GuestbookForm)obj;
		if (guestbookForm != null)
		{
		    if (guestbookForm.getUsername() == null || guestbookForm.getUsername().length() < 2)
		    {
		        errors.rejectValue("username", "required.username", "이름을 입력하세요.");
		    }
		    
		    Pattern emailPattern = Pattern.compile(WebPublic.EMAIL_REGEXT_PATTERN);
		    if (guestbookForm.getEmail() == null || !emailPattern.matcher(guestbookForm.getEmail().trim()).matches())
		    {
		        errors.rejectValue("email", "required.email", "유효한 이메일을 입력하세요.");
		    }
		    
		    if (guestbookForm.getPassword() == null || guestbookForm.getPassword().length() < 4)
            {
                errors.rejectValue("password", "required.password", "비밀번호를 입력하세요.");
            }
		    
		    ValidationUtils.rejectIfEmpty(errors, "comment", "required.comment", "내용을 입력하세요.");
		}		
	}
}
