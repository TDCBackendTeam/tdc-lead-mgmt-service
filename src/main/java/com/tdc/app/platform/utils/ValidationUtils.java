package com.tdc.app.platform.utils;

import static java.util.regex.Pattern.compile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationUtils {
	
	 public static final int PAGE_SIZE_MAX_LIMIT = 100;
	 
	 public static final String FORM_DATE_FORMAT = "yyyy-MM-dd";
	 
  public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  public static final String PHONE_PATTERN =
      "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";

  public static final String URL_PATTERN =
      "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|\\+[^/]+|\\d{21}|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|\\+[^/]+|\\d{21}]";

  public static final String TIME_24_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

  public static final String TIME_12_REGEX = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";

  /**
   * Validate email with regular expression
   * 
   * @param email email for validation
   * @return true valid email, false invalid email
   */
  public static boolean validateEmail(String email) {
    if (StringUtils.isBlank(email)) {
      return false;
    }
    Pattern pattern = compile(EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /**
   * Validate phone with regular expression
   * 
   * @param phone Phone for validation
   * @return true valid phone, false invalid phone
   */
  public static boolean validatePhone(String phone) {
    Pattern pattern = compile(PHONE_PATTERN);
    Matcher matcher = pattern.matcher(phone);
    return matcher.matches();
  }

  public static boolean validatePhoneNumber(String phoneNo) {
    return //
    // validate phone numbers of format "1234567890"
    phoneNo.matches("\\d{10}") ||
    // validating phone number with -, . or spaces
        phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}") ||
        // validating phone number with extension length from 3 to 5
        phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}") ||
        // validating phone number where area code is in braces ()
        phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}");
  }

  /**
   * Validate url with regular expression
   * 
   * @param url URL for validation
   * @return true valid url, false invalid url
   */
  public static boolean validateUrl(String url) {
    Pattern pattern = compile(URL_PATTERN);
    Matcher matcher = pattern.matcher(url);
    return matcher.matches();
  }

  public static int getValidPage(Integer page, Integer pageSize) {
    if (page != null && page <= 0) {
    //  throw new ServiceException(ErrorCode.INVALID_PAGE, page);
    }
    if (pageSize != null && (pageSize < 1 || pageSize > PAGE_SIZE_MAX_LIMIT)) {
     // throw new ServiceException(ErrorCode.INVALID_PAGE_SIZE, pageSize, 1, PAGE_SIZE_MAX_LIMIT);
    }
    return page == null ? 0 : page - 1;
  }

  public static boolean isDateValid(String date) {
    if (StringUtils.isNotBlank(date)) {
      try {
    	 // DateTimeUtils.convertStringToLocalDate(date, FORM_DATE_FORMAT);
      } catch (Exception e) {
        log.error(" Date cannot be parsed!", e.getMessage());
        return false;
      }
    }
    return true;
  }

  public static boolean isEndDateTimeStartDateTimeSame(String issueDate, String expiryDate) {
    if (StringUtils.isNotBlank(issueDate) && StringUtils.isNotBlank(expiryDate)) {
      try {
       // var localIssueDate = DateTimeUtils.convertStringToLocalDate(issueDate, FORM_DATE_FORMAT);
       // var localExpiryDate = DateTimeUtils.convertStringToLocalDate(expiryDate, FORM_DATE_FORMAT);
        return true;//localIssueDate.isBefore(localExpiryDate);
      } catch (Exception e) {
        log.error("Issue date or Expiry date cannot be parsed!", e);
      }
    }
    return true;
  }

}
