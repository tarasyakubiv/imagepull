package com.tarasyakubiv.demo.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.tarasyakubiv.demo.domain.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {
	
	Set<Image> findByThreadNumberInOrderByTimeAsc(Set<String> threadNumbers);

}
