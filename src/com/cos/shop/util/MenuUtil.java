package com.cos.shop.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cos.shop.model.Category;
import com.cos.shop.repository.CategoryRepository;

public class MenuUtil {
	
	public static List<String> getRootMenus() {
		CategoryRepository categoryRepository = CategoryRepository.getInstance();
		List<String> allRootMenus = categoryRepository.findRootAll();
		
		return allRootMenus;
	}
	
	
	public static List<Category> getAllCategories() {
		CategoryRepository categoryRepository = CategoryRepository.getInstance();
		List<Category> allCategories = categoryRepository.findAll();
		
		return allCategories;
	}
	
	public static Map<String,Map<String,Integer>> prepareMenuMap() {
		// Map<String,List<String>> menuMap = new HashMap<>();
		MenuMap menuMap = new MenuMap();
		
		// List<String> allRootMenus = getRootMenus();
		List<Category> allCategories = getAllCategories();
		
		// List<Category>의 모든 카테고리들을 for로 돌리면서 addCategory()를 차례로 호출해 주고 나면
		// menuMap 객체는 완벽히 세팅이 끝난다
		for (Category category : allCategories) {
			String rootCategory = category.getRoot_category();
			String subCategory = category.getSub_category();
			int categoryNum = category.getId();
			
			menuMap.addCategory(rootCategory, subCategory, categoryNum);
		}
		
		return menuMap.getMap();
	}
	
}


// 아래 클래스는 자바의 정석 컬렉션류 파트의 전화번호부 예제(Map 안에 Map 사용)를 응용해서
// 간단히 만들어 본 것임
class MenuMap {
	
	// 아래 맵 객체는 Map<대분류,Map<소분류,카테고리id>>의 구성임.
	// model 패키지의 Category 클래스 참조 (id,root_category,sub_category)
	private Map<String,Map<String,Integer>> menus = new HashMap<>();
	

	public void addCategory(String rootCategory, String subCategory, Integer categoryNum) {
		addRootCategory(rootCategory);
		
		Map<String,Integer> map = menus.get(rootCategory);
		map.put(subCategory, categoryNum);
	}
	
	private void addRootCategory(String rootCategory) {
		if (!menus.containsKey(rootCategory)) {
			menus.put(rootCategory, new HashMap<String,Integer>());
		}
	}
	
	public Map<String,Map<String,Integer>> getMap() {
		return menus;
	}

}