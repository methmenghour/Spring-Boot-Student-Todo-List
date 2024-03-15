package com.menghour.student.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface PageUtil {
	int DEFAULT_PAGE_SIZE_LIMIT =5;
	int DEFAULT_PAGE_NUMBER = 1;
	String PAGE_SIZE_LIMIT = "_limit";
	String PAGE_NUMBER = "_page";
	
	static Pageable getPageable(int pageNumber, int pageSize) {
		if(pageNumber<DEFAULT_PAGE_NUMBER) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		if (pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE_LIMIT;
		}
		Pageable pageable= PageRequest.of(pageNumber-1, pageSize,Sort.by("id").descending());
		
		return pageable;
	}
}
