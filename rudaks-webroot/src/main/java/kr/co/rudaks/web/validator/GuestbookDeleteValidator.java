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
public class GuestbookDeleteValidator implements Validator 
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
		    if (guestbookForm.getPassword().length() < 4)
            {
                errors.rejectValue("password", "required.password", "비밀번호를 입력하세요.");
            }
		}		
	}
}
