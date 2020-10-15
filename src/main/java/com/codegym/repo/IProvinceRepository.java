package com.codegym.repo;

import com.codegym.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProvinceRepository extends PagingAndSortingRepository<Province,Long> {

}
