package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Province;

public interface IProvinceService {
    Iterable<Province> findAll();
    Province findById(Long Id);
    void save(Province province);
    void remove(Long id);
}
