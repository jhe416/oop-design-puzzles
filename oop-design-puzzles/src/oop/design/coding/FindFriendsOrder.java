package oop.design.coding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * given two api findFriends and findMyOrder find my friends's order
 * 1. supports friends have friends.
 */
public class FindFriendsOrder {
	public FindFriendsOrder() {}
	public List<String> findFriends(String name){
		return new ArrayList<>();
	}
	
	public List<Order> findMyOrder(String name){
		return new ArrayList<>();
	}
	
	public List<Order> findMyFriendsOrder(String name) {
		//List<Order> list = findOrderBfs(name); find on bfs
		List<Order> result = new ArrayList<>();
		Set<String> visited = new HashSet<>();
		for(String friendName : findFriends(name)) {
			if(visited.contains(friendName)) continue;
			result.addAll(findOrderDfs(friendName,visited));
		}
		return result;
	}
	
	public List<Order> findOrderDfs(String friendName,Set<String> visited){
		if(visited.contains(friendName)) return new ArrayList<>();	
		visited.add(friendName);
		
		List<Order> list = new ArrayList<>();
		for(String name : findFriends(friendName)) {
			List<Order> hisOrder = findOrderDfs(name,visited);
			list.addAll(hisOrder);
		}
		list.addAll(findMyOrder(friendName));
		return list;
	}
	
	public List<Order> findOrderBfs(String name){
		Queue<String> q = new LinkedList<>();
		Set<String> visited = new HashSet<>(); //skip mutual friends
		for(String friend : findFriends(name)) q.offer(friend);
		
		List<Order> result = new ArrayList<>();
		while(!q.isEmpty()) {
			for(int k=q.size();k>0;k--) {
				String friendName = q.poll();
				if(visited.contains(friendName)) continue;
				result.addAll(findMyOrder(friendName));
				visited.add(friendName);
				
				for(String friend : findFriends(friendName)) q.offer(friend);
			}
		}
		
		return result;
	}
}

class Order{
	public long id;
	public String product;
	public String productType;
	public long price;
	public Order() {}
}
