package oop.design.coding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinuxFind {

	
	public List<File> findFileDfs(Directory dict, Filter filter){
		
		List<File> res = new ArrayList<>();
		for(Directory data : dict.dictories) {
			List<File> list = findFileDfs(data,filter);
			res.addAll(list);
		}
		
		for(File file : dict.files) {
			if(filter.canApply(file)) {
				res.add(file);
			}
		}
		
		return res;
	}
	
	public List<File> findFilesBfs(Directory dict, Filter filter) {
		Queue<Directory> q = new LinkedList<>();
		q.offer(dict);
		List<File> res = new ArrayList<>();

		while(!q.isEmpty()) {
			for(int k=q.size();k>0;k--) {
				Directory data = q.poll();

				for(Directory d : data.dictories) {
					q.offer(d);
				}

				for(File file : data.files) {
					if(filter.canApply(file)) {
						res.add(file);
					}
				}

			}
		}

		return res;
	}
}

class NotFilters implements Filter{
	List<Filter> filters;
	
	public NotFilters(List<Filter> filters) {
		this.filters = filters;
	}
	
	public boolean canApply(File file) {
		for(Filter filter : filters) {
			if(filter.canApply(file)) return false;
		}
		return true;
	}
}

class OrFilters implements Filter{
	List<Filter> filters;
	
	public OrFilters(List<Filter> filters) {
		this.filters = filters;
	}
	
	public boolean canApply(File file) {
		for(Filter filter : filters) {
			if(filter.canApply(file)) return true;
		}
		return false;
	}
}

class AndFilters implements Filter{
	List<Filter> filters;
	
	public AndFilters(List<Filter> filters) {
		this.filters = filters;
	}
	
	public boolean canApply(File file) {
		for(Filter filter : filters) {
			if(!filter.canApply(file)) return false;
		}
		return true;
	}
}

class NameFilter implements Filter{
	public String name;	
	public NameFilter(String name) {
		this.name = name;
	}
	
	public boolean canApply(File file) {
		return file.name.equals(this.name);
	}
}

class SizeFilter implements Filter{
	public int size;
	public SizeFilter(int size) {
		this.size = size;
	}
	
	public boolean canApply(File file) {
		return file.size<=this.size;
	}
}
interface Filter{
	boolean canApply(File file);
}

class TypeFilter implements Filter{
	public String type;	
	public TypeFilter(String type) {
		this.type = type;
	}
	
	public boolean canApply(File file) {
		return file.type.equals(this.type);
	}
}

class File{
	public int size;
	public String name;
	public String type;
	public File() {};
}

class Directory{
	public List<File> files = new ArrayList<>();
	public List<Directory> dictories = new ArrayList<>();
	public Directory() {}
	
}