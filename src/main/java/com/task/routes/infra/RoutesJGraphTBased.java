package com.task.routes.infra;

import com.task.routes.api.Connection;
import com.task.routes.api.CountryCode;
import com.task.routes.api.Route;
import com.task.routes.api.RouteCommands;
import com.task.routes.api.RouteQueries;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.stereotype.Service;

@Service
class RoutesJGraphTBased implements RouteCommands, RouteQueries {

	//storing data in memory inside @bean will fail in multi tenant environment and in general that's something to avoid,
	//but for mvp purposes that good enough for MVP
	//probably in prod graph database will be used
	//in general "find a path" fit's intro problems that can be solved using graph
	//for prod neo4j might be considered as a long term solution
	//algorithm for "route" will differ depending on additional properties
	//since we don't have weight BFS would give as the shortest path
	//if we would have weight of edges Dijkstra shortest path would be more suitable.

	private final Graph<String, DefaultEdge> routes = new SimpleGraph<>(DefaultEdge.class);

	@Override
	public void add(Connection connection) {
		if (!routes.containsVertex(connection.left.value)) {
			routes.addVertex(connection.left.value);
		}
		if (!routes.containsVertex(connection.right.value)) {
			routes.addVertex(connection.right.value);
		}
		routes.addEdge(connection.left.value, connection.right.value);
	}

	@Override
	public Optional<Route> findBetween(CountryCode origin, CountryCode destination) {
		//this will depends on more specific requirements, maybe we don't wont to allow look for non existing countries
		if (!routes.containsVertex(origin.value) || !routes.containsVertex(destination.value)) {
			return Optional.empty();
		}
		GraphPath<String, DefaultEdge> bfsShortestPath = new BFSShortestPath<>(routes).getPath(origin.value, destination.value);
		if (Objects.isNull(bfsShortestPath) || bfsShortestPath.getVertexList().isEmpty()) {
			return Optional.empty();
		}
		List<CountryCode> borderCrossings = bfsShortestPath.getVertexList().stream()
				.map(CountryCode::new)
				.collect(Collectors.toList());
		return Optional.of(new Route(borderCrossings, origin, destination));
	}
}
