package com.tuantran.CarShowroom.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.Map;

@Component
public class PageSizeUtils {
    public static Pageable getPageable(Map<String, String> params) throws MissingServletRequestParameterException {
        String pageStr = params.get("page");
        String sizeStr = params.get("size");

        if (pageStr == null || !pageStr.matches("\\d+")) {
            throw new MissingServletRequestParameterException("page", "Integer");
        }
        if (sizeStr == null || !sizeStr.matches("\\d+")) {
            throw new MissingServletRequestParameterException("size", "Integer");
        }

        int page = Integer.parseInt(pageStr);
        int size = Integer.parseInt(sizeStr);
        String sort = params.getOrDefault("sort", "id");
        String direction = params.getOrDefault("direction", "desc");

        // Convert 1-based to 0-based
        page = Math.max(page - 1, 0);
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        return PageRequest.of(page, size, Sort.by(sortDirection, sort));
    }

}
