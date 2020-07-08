package oop.design.api.limiter;

import java.util.*;

interface Throttle{
	public boolean isValid(long time);
}

public class ApiLimiter implements Throttle{
	Queue<Long> q = new LinkedList();
	final int LIMIT_TIME = 10000;//10s
	final int HIT_TIME = 3;//3times in 10s;

	public boolean isValid(long time) {
		while(!q.isEmpty() && time - q.peek() >= LIMIT_TIME)q.poll();
		
		if(q.size() < HIT_TIME) {
			q.offer(time);
			return true;
		}
		
		return false;
	}
}

class Authenticate{
	private final Map<Long,Throttle> map = new HashMap<>();
	
	public void authenticate(Long userId) {
		if(!map.containsKey(userId)) {
			throw new RuntimeException("user not authenticated");
		}
		
		Throttle throttle = map.get(userId);
		
		if(!throttle.isValid(System.currentTimeMillis())){
			throw new RuntimeException("user reaching throttle limit");
		}
	}
}
