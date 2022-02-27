package com.coderscampus.Assignment_9.web;

import java.util.List;
import java.util.stream.Collectors;

import com.coderscampus.Assignment_9.domain.Recipe;
import com.coderscampus.Assignment_9.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RecipeController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/gluten_free")
	public List<Recipe> getGlutenFree () {
		return fileService.getAllRecipes()
						  .stream()
						  .filter(Recipe::getGlutenFree)
						  .collect(Collectors.toList());
	}
	
	@GetMapping("/vegan")
	public List<Recipe> getVegan () {
		return fileService.getAllRecipes()
						  .stream()
						  .filter(Recipe::getVegan)
						  .collect(Collectors.toList());
	}
	@GetMapping("/vegan_and_gluten_free")
	public List<Recipe> getVegan_GlutenFree () {
		return fileService.getAllRecipes()
						  .stream()
						  .filter(recipe -> recipe.getVegan() && recipe.getGlutenFree())
						  .collect(Collectors.toList());
	}

	@GetMapping("/vegetarian")
	public List<Recipe> getVegetarian () {
		return fileService.getAllRecipes()
				  .stream()
				  .filter(Recipe::getVegetarian)
				  .collect(Collectors.toList());
	}

	@GetMapping("/all_recipes")
	public List<Recipe> getAllRecipes () {
		return fileService.getAllRecipes();
	}

}
