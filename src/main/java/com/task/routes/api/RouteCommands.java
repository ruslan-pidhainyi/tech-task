package com.task.routes.api;

//separation commands/queries make sense from sec perspective,
//since probably addition/future edition will be performed by different actor, than read queries
public interface RouteCommands {

	//void might be replaced with self contained "Result", that provide info why specific connection can not be defined
	void add(Connection connection);

}
