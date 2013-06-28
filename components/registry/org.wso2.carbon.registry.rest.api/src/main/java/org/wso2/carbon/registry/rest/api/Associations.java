
/*
 * Copyright (c) 2013, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.registry.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.registry.core.Association;
import org.wso2.carbon.registry.core.exceptions.RegistryException;
import org.wso2.carbon.registry.rest.api.model.AssociationModel;

/**
 * This class retrieved the associations of the requested resource. 
 */
@Path("/associations")
public class Associations extends PaginationCalculation<Association> {

	protected Log log = LogFactory.getLog(Associations.class);
	protected String path = null;
	protected String associationType = null;
	/**
	 * This method takes the following parameters: 
	 * @param resourcePath - path of the resource, 
	 * @param type - type of association, 
	 * @param start - start page number, 
	 * @param size - number of records to be retrieved
	 * @param username - username of the enduser, 
	 * @param tenantD - tenantID of the enduser belongs to the username
	 *  This method returns the array of AssociationModel. 
	 */
	@GET
	@Produces("application/json")
	public Response getAssociationsOnAResource(@QueryParam("path")String resourcePath,
			@QueryParam("type") String type,@QueryParam("start") int start,
			@QueryParam("size")int size,@QueryParam("username")String username,@QueryParam("tenantid")String tenantID) {
		
		if(RestPathPaginationValidation.validate(resourcePath, start, size)== -1){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if(super.createUserRegistry(username,tenantID)== null){ 
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		boolean exist;
		path = resourcePath;
		associationType = type;
		try {
			exist = userRegistry.resourceExists(resourcePath);
			if (exist){
				log.debug("processing the pagination result");
				//return getAssociationsPaginationIndex(start, size, resourcePath, type);
				return displayPaginatedResult(start,size);
			} else{
				log.debug("resource not found");
				//if resource not found
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (RegistryException e) {
			log.error("User does not have required permission to access the resource", e);
			//if user is not authorized to access the resource
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}		
	}
	/**
	 * this method returns the array of Association for the given resource.  
	 */
	@SuppressWarnings("finally")
	protected Association[] getResult(){
		Association[] result = null;
		try{
			if (associationType != null){
				//returns the given type of associations for the given resource
				result = userRegistry.getAssociations(path,associationType);
			} else {
				//returns all the associations of the given resource
				result = userRegistry.getAllAssociations(path);
			}
		} catch(RegistryException e){
			log.error("User is not authrorized to read the associations on the given resource",e);
		} finally{
			return result;
		}
		
	}
	/**
	 * This method bind the array of paginated association objects   
	 */
	protected Response display(Association[] associationArr,int begin, int end){
		AssociationModel[] message = new AssociationModel[end-begin+1];
		for (int i = 0,j = begin; i < message.length && j <= end; i++,j++){
			message[i] = new AssociationModel(associationArr[j]);
		}
		return Response.ok(message).build();
	}
}