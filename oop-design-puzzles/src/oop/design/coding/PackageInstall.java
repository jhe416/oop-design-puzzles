package oop.design.coding;

import java.util.*;
public class PackageInstall {
	Installer installer;
	public void install(Package pack) {
		Set<Package> visit = new HashSet<>();
		dfsInstall(pack,visit);
	}
	
	private void dfsInstall(Package pack, Set<Package> visited) {
		if(visited.contains(pack)) return;
		visited.add(pack);
		
		for(Package p : pack.getDep()) {
			dfsInstall(p,visited);
		}
		
		installer.installPayLoad(pack.getPayload());
	}
	
}

interface Installer{
	public void installPayLoad(Payload payload);
}
class Package{
	private Payload payload;
	Payload getPayload() {
		return payload;
	}
	List<Package> getDep(){
		return new ArrayList<>();
	}
}

class Payload{
	
}
