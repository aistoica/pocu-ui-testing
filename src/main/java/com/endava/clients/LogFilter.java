package com.endava.clients;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.util.HashSet;

public class LogFilter implements Filter {

	@Override
	public Response filter( FilterableRequestSpecification request, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext ) {

		RequestPrinter.print( request, request.getMethod(), request.getURI(), LogDetail.ALL, new HashSet<>(), System.out, true );
		Response response = filterContext.next( request, filterableResponseSpecification );
		response.prettyPeek();
		return response;
	}
}
