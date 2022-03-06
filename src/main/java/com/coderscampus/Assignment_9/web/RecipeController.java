package com.coderscampus.Assignment_9.web;

import java.util.List;
import com.coderscampus.Assignment_9.domain.Recipe;
import com.coderscampus.Assignment_9.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RecipeController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/gluten-free")
	public List<Recipe> getList_GlutenFree () {
		return fileService.getGlutenFree();
	}
	
	@GetMapping("/vegan")
	public List<Recipe> getList_Vegan () {
		return fileService.getVegan();
	}
	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> getList_Vegan_GlutenFree () {
		return fileService.getVeganGlutenFree();
	}

	@GetMapping("/vegetarian")
	public List<Recipe> getList_Vegetarian () {
		return fileService.getVegetarian();
	}

	@GetMapping("/all-recipes")
	public List<Recipe> getAllRecipes () {
		return fileService.getAllRecipes();
	}

}
