package com.hellcow.testBook.api.config;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("resource")
@Singleton
public class ResouceManager {
	//	Jersey will inject proxy of Security Context
  @Context
	SecurityContext securityContext;

  @GET
  public String getUserPrincipal() {
      return securityContext.getUserPrincipal().getName();
  }
}
